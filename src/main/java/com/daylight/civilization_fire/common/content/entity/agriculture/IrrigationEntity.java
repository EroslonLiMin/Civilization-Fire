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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;

public abstract class IrrigationEntity extends PathfinderMob implements IAnimatable, IAnimationTickable {
    private BlockPos setPos;
    public int useTimes;
    public EntityItem entityItem;//翻车物品

    protected IrrigationEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public abstract int getIrrigationLevel();

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    public InteractionResult interactAt(Player player, Vec3 pVec, InteractionHand hand) {
        if (player.isShiftKeyDown()) {
            ItemStack itemStack = new ItemStack(this.entityItem);
            itemStack.setDamageValue(this.getUseTimes());
            player.addItem(itemStack);
            //删除它
            this.discard();
        }
        return super.interactAt(player, pVec, hand);
    }

    //获取与设置
    public int getUseTimes() {
        return this.useTimes;
    }

    public void setUseTimes(int times) {
        this.useTimes = times;
    }

    public void addUseTimes() {
        useTimes += 1;
    }


    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putInt("entityItem", Item.getId(entityItem));
        compoundTag.putInt("useTimes", useTimes);
        super.addAdditionalSaveData(compoundTag);
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
    public void tick() {
        super.tick();
        if(this.setPos == null){
            //判断位置
            for(int x = this.getBlockX() - 1;x < this.getBlockX() + 1;x++){
                for(int y = this.getBlockY() - 1;y < this.getBlockY() + 1;y++){
                    for(int z = this.getBlockZ() - 1;z < this.getBlockZ() + 1;z++){
                        if(this.level.getBlockState(new BlockPos(x,y,z)).getBlock() instanceof BucketPickup){
                            setPos = new BlockPos(x,y,z);
                        }
                    }
                }
            }
        }
        if(setPos == null){
            this.kill();
        }
        if(setPos != null) this.setPos(setPos.getX(),setPos.getY(),setPos.getZ());
        //灌溉
        for(int x = this.getBlockX() - getIrrigationLevel();x < this.getBlockX() + getIrrigationLevel();x++){
            for(int y = this.getBlockY() - getIrrigationLevel();y < this.getBlockY() + getIrrigationLevel();y++){
                for(int z = this.getBlockZ() - getIrrigationLevel();z < this.getBlockZ() + getIrrigationLevel();z++){
                    BlockState blockState = this.level.getBlockState(new BlockPos(x,y,z));
                    boolean is = blockState.hasProperty(SoilBlock.BE_PLOUGHED) ? blockState.getValue(SoilBlock.BE_PLOUGHED) : true;
                    if(is && blockState.hasProperty(SoilBlock.BE_WATERED)){
                        SoilBlock.setWatered(null,level,new BlockPos(x,y,z),true);
                    }
                }
            }
        }
        addUseTimes();
        if (getUseTimes() >= getIrrigationLevel() * 1000000) {
            this.discard();
            this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ITEM_BREAK,
                    this.getSoundSource(), 0.8F, 0.8F + this.level.random.nextFloat() * 0.4F, false);
        }
    }

}
