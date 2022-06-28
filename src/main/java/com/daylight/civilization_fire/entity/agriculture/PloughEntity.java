package com.daylight.civilization_fire.entity.agriculture;

import com.daylight.civilization_fire.block.agriculture.PloughItem;
import com.daylight.civilization_fire.block.agriculture.SoilBlock;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;
import java.util.stream.Collectors;

//犁车实体
public abstract class PloughEntity extends Entity {
    //耕种次数
    private static final EntityDataAccessor<Integer> PLOUGH_TIMES = SynchedEntityData.defineId(AreaEffectCloud.class, EntityDataSerializers.INT);
    public PloughItem ploughItem;//耕种物品

    public PloughEntity(EntityType<?> entityType, Level level,PloughItem ploughItem) {
        super(entityType, level);
        this.ploughItem = ploughItem;
    }

    public abstract int getPloughLevel();
    @Override
    public void tick() {
        super.tick();
        boolean isPlough = false;
        //获取一下方块并且经行耕种处理
        List<BlockState> blockState = this.level.getBlockStates(this.getBoundingBox().inflate(getPloughLevel())).collect(Collectors.toList());
        for (BlockState state : blockState) {
            //判断是否为soilBlock
            if (state.getBlock() instanceof SoilBlock) {
                //判断是否含有value
                if (state.hasProperty(SoilBlock.BE_PLOUGHED) && state.getValue(SoilBlock.BE_PLOUGHED)) {
                    state.setValue(SoilBlock.BE_PLOUGHED, true);
                    isPlough = true;
                }
            }
        }
        if(isPlough){
            addPloughTimes();
        }
        if(getPloughTimes() >= getPloughTimes() * 10000){
            kill();
        }
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if(player.isShiftKeyDown()){
            ItemStack itemStack = new ItemStack(this.ploughItem);
            itemStack.getOrCreateTag().putInt("plough",this.getPloughTimes());
            player.addItem(itemStack);
            this.kill();
        }
        return super.interact(player, hand);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(PLOUGH_TIMES,0);
    }

    //获取与设置
    public int getPloughTimes(){
        return this.entityData.get(PLOUGH_TIMES);
    }

    public void addPloughTimes(){
        this.entityData.set(PLOUGH_TIMES,getPloughLevel() + 1);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        this.entityData.set(PLOUGH_TIMES,compoundTag.getInt("plough_times"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putInt("plough_times",getPloughLevel());
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}