package com.daylight.civilization_fire.common.content.entity.agriculture;

import com.daylight.civilization_fire.common.content.block.agriculture.SoilBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;

public abstract class IrrigationEntity extends PathfinderMob implements IAnimatable, IAnimationTickable {
    private BlockPos setPos;
    protected IrrigationEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public abstract int getIrrigationLevel();

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
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
    }

}
