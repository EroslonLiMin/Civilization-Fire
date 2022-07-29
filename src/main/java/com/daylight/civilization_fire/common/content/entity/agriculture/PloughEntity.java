package com.daylight.civilization_fire.common.content.entity.agriculture;

import com.daylight.civilization_fire.common.content.block.agriculture.SoilBlock;
import com.daylight.civilization_fire.common.content.item.agriculture.EntityItem;
import com.daylight.civilization_fire.common.util.CivilizationFireUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

//犁车实体
public abstract class PloughEntity extends PathfinderMob {
    //石犁
    public static class StonePloughEntity extends PloughEntity {
        public StonePloughEntity(EntityType<StonePloughEntity> entityTypeIn, Level level) {
            super(entityTypeIn, level);
        }

        @Override
        public int getPloughLevel() {
            return 1;
        }

        public static AttributeSupplier.Builder createAttributes() {
            return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.1D)
                    .add(Attributes.KNOCKBACK_RESISTANCE, 0.2D);
        }
    }

    //铁犁
    public static class IronPloughEntity extends PloughEntity {
        public IronPloughEntity(EntityType<IronPloughEntity> entityTypeIn, Level level) {
            super(entityTypeIn, level);
        }

        @Override
        public int getPloughLevel() {
            return 2;
        }

        public static AttributeSupplier.Builder createAttributes() {
            return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.2D)
                    .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);
        }
    }

    //曲辕犁
    public static class CurvilinearPloughEntity extends PloughEntity {
        public CurvilinearPloughEntity(EntityType<CurvilinearPloughEntity> entityTypeIn, Level level) {
            super(entityTypeIn, level);
        }

        @Override
        public int getPloughLevel() {
            return 3;
        }

        public static AttributeSupplier.Builder createAttributes() {
            return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 25.0D).add(Attributes.MOVEMENT_SPEED, 0.35D)
                    .add(Attributes.KNOCKBACK_RESISTANCE, 1D);
        }
    }

    //耕种次数
    public int useTimes = 0;
    public EntityItem entityItem;//耕种物品

    public PloughEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    public abstract int getPloughLevel();

    @Override
    public void tick() {
        boolean isPlough = false;
        //获取一下方块并且经行耕种处理
        for(int x = this.getBlockX() - getPloughLevel();x < this.getBlockX() + getPloughLevel();x++) {
            for (int y = this.getBlockY() - getPloughLevel(); y < this.getBlockY() + getPloughLevel(); y++) {
                for (int z = this.getBlockZ() - getPloughLevel(); z < this.getBlockZ() + getPloughLevel(); z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    BlockState state = this.level.getBlockState(pos);
                    //判断是否为soilBlock
                    if (state.getBlock() instanceof SoilBlock) {
                        //判断是否含有value
                        if (state.hasProperty(SoilBlock.BE_PLOUGHED) && !state.getValue(SoilBlock.BE_PLOUGHED)) {
                            this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.HOE_TILL,
                                    this.getSoundSource(), 0.8F, 0.8F + this.level.random.nextFloat() * 0.4F, false);
                            level.setBlock(pos, state.setValue(SoilBlock.BE_PLOUGHED, true), Block.UPDATE_ALL);
                            level.gameEvent(GameEvent.BLOCK_PLACE, pos);
                            isPlough = true;
                        }
                    }
                }
            }
        }
        if (isPlough) {
            addPloughTimes();
        }
        if (getPloughTimes() >= getPloughLevel() * 10000) {
            this.discard();
            this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ITEM_BREAK,
                    this.getSoundSource(), 0.8F, 0.8F + this.level.random.nextFloat() * 0.4F, false);
        }
        super.tick();
    }

    @Override
    public InteractionResult interactAt(Player player, Vec3 pVec, InteractionHand hand) {
        if (player.isShiftKeyDown()) {
            ItemStack itemStack = new ItemStack(this.entityItem);
            itemStack.setDamageValue(this.getPloughTimes());
            player.addItem(itemStack);
            //删除它
            this.discard();
        }
        return super.interactAt(player, pVec, hand);
    }

    //获取与设置
    public int getPloughTimes() {
        return useTimes;
    }

    public void setPloughTimes(int times) {
        this.useTimes = times;
    }

    public void addPloughTimes() {
        useTimes += 1;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        this.entityItem = (Item.byId(compoundTag.getInt("entityItem")) instanceof EntityItem
                ? (EntityItem) Item.byId(compoundTag.getInt("entityItem"))
                : null);
        this.useTimes = compoundTag.getInt("useTimes");
        super.readAdditionalSaveData(compoundTag);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putInt("entityItem", Item.getId(entityItem));
        compoundTag.putInt("useTimes", useTimes);
        super.addAdditionalSaveData(compoundTag);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

}