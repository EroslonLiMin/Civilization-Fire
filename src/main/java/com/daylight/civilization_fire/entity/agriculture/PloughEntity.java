package com.daylight.civilization_fire.entity.agriculture;

import com.daylight.civilization_fire.block.agriculture.SoilBlock;
import com.daylight.civilization_fire.item.agriculture.EntityItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
public abstract class PloughEntity extends LivingEntity {
    //石犁
    public static class StonePloughEntity extends PloughEntity {
        public StonePloughEntity(EntityType<StonePloughEntity> entityTypeIn, Level level) {
            super(entityTypeIn, level);
        }

        @Override
        public int getPloughLevel() {
            return 1;
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
    }


    //耕种次数
    private static final EntityDataAccessor<Integer> PLOUGH_TIMES = SynchedEntityData.defineId(PloughEntity.class, EntityDataSerializers.INT);
    public EntityItem entityItem;//耕种物品
    public boolean isFollow;//是否被牵着
    public LivingEntity followEntity;//跟随的实体

    public PloughEntity(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }


    //属性处理
    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D);
    }

    public abstract int getPloughLevel();

    @Override
    public void tick() {
        super.tick();
        boolean isPlough = false;
        //处理一下跟随
        if(isFollow) {
            if (followEntity == null) isFollow = false;
            Vec3 vec3 = followEntity.getViewVector(3);
            this.moveTo(followEntity.xOld + vec3.x * 2, followEntity.yOld, followEntity.zOld - vec3.z * 1, followEntity.getXRot(), this.getYRot());
            followEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 25, 4 - this.getPloughLevel()));
            this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 25, 5 - this.getPloughLevel()));
        }
        //获取一下方块并且经行耕种处理
        BlockPos pos = new BlockPos(this.getBlockX(), this.getBlockY() - 1, this.getBlockZ());
        BlockState state = this.level.getBlockState(pos);
        //判断是否为soilBlock
        if (state.getBlock() instanceof SoilBlock) {
            //判断是否含有value
            if (state.hasProperty(SoilBlock.BE_PLOUGHED) && !state.getValue(SoilBlock.BE_PLOUGHED)) {
                this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.HOE_TILL, this.getSoundSource(), 0.8F, 0.8F + this.level.random.nextFloat() * 0.4F, false);
                level.setBlock(pos, state.setValue(SoilBlock.BE_PLOUGHED, true), Block.UPDATE_ALL);
                level.gameEvent(GameEvent.BLOCK_PLACE, pos);
                isPlough = true;
            }
        }
        if (isPlough) {
            addPloughTimes();
        }
        if (getPloughTimes() >= getPloughLevel() * 10000) {
            this.discard();
            this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ITEM_BREAK, this.getSoundSource(), 0.8F, 0.8F + this.level.random.nextFloat() * 0.4F, false);
        }
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if(player.isShiftKeyDown()){
            ItemStack itemStack = new ItemStack(this.entityItem);
            itemStack.setDamageValue(getPloughLevel() * 10000 - this.getPloughTimes());
            player.addItem(itemStack);
            //删除它
            this.discard();
        } else {
            this.isFollow = true;
            this.followEntity = player;
        }
        return super.interact(player, hand);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PLOUGH_TIMES,0);
    }

    //获取与设置
    public int getPloughTimes(){
        return this.entityData.get(PLOUGH_TIMES);
    }

    public void setPloughTimes(int times){
        this.entityData.set(PLOUGH_TIMES,times);
    }

    public void addPloughTimes(){
        this.entityData.set(PLOUGH_TIMES,getPloughLevel() + 1);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        this.entityData.set(PLOUGH_TIMES,compoundTag.getInt("plough_times"));
        this.entityItem = (Item.byId(compoundTag.getInt("entityItem")) instanceof EntityItem ? (EntityItem) Item.byId(compoundTag.getInt("entityItem")) : null);
        super.readAdditionalSaveData(compoundTag);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putInt("plough_times",getPloughLevel());
        compoundTag.putInt("ploughItem",Item.getId(entityItem));
        super.addAdditionalSaveData(compoundTag);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return NonNullList.withSize(0, ItemStack.EMPTY);
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot equipmentSlot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(EquipmentSlot equipmentSlot, ItemStack itemStack) {
        //tool
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }


}