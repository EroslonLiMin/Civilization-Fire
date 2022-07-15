package com.daylight.civilization_fire.common.content.block.cooking;

import com.daylight.civilization_fire.common.content.item.cooking.DishesVarietyItem;
import com.daylight.civilization_fire.common.content.item.cooking.SpatulaItem;
import com.daylight.civilization_fire.common.content.recipe.CookingRecipe;
import com.daylight.civilization_fire.common.content.recipe.CookingTool;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

import net.minecraftforge.items.ItemStackHandler;

import java.util.*;

public abstract class CookingBlockEntity extends BlockEntity {
    public ItemStackHandler cookingStacksItemStackHandler = new ItemStackHandler(9);
    public final ItemStackHandler addCondimentItemStackHandler = new ItemStackHandler(5);
    public final ItemStackHandler outputItemStackHandler = new ItemStackHandler(1);
    public ItemStackHandler toolsItemStackHandler = new ItemStackHandler(2);
    public int cookingFire;
    public boolean couldTake = false;

    public CookingBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state);
    }

    //tick进行刷新
    public static void tick(Level level, BlockPos pos, BlockState blockState,
            CookingBlockEntity cookingBlockEntity) {
        BlockState belowState = level.getBlockState(pos.below());
        if (belowState.getBlock() instanceof CookingBench) {
            if (belowState.getValue(CookingBench.BENCH_STATE) >= 2) {
                cookingBlockEntity.cookingFire = (cookingBlockEntity.cookingFire >= 1000) ? 1000 : cookingBlockEntity.cookingFire + 1;
                level.addParticle(ParticleTypes.SMALL_FLAME, pos.getX() + 0.5, pos.getY()-0.75, pos.getZ(), 0.0D, 0.0D,
                        0.0D);
                level.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5, pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
        if(cookingBlockEntity.cookingFire >= 500){
            if(!cookingBlockEntity.couldTake) cookingBlockEntity.outputItemStackHandler.setStackInSlot(0,cookingBlockEntity.getCookingItemStack());
            else if(cookingBlockEntity.outputItemStackHandler.getStackInSlot(0) == ItemStack.EMPTY){
                cookingBlockEntity.couldTake = false;
                cookingBlockEntity.cookingFire = 0;
                reduceItemStackHandler(cookingBlockEntity.cookingStacksItemStackHandler);
                damageItemStackHandler(cookingBlockEntity.addCondimentItemStackHandler);
                cookingBlockEntity.toolsItemStackHandler.setStackInSlot(0,ItemStack.EMPTY);
                ItemStack tools2 =  cookingBlockEntity.toolsItemStackHandler.getStackInSlot(1);
                if(tools2.getItem() instanceof SpatulaItem){
                    tools2.setDamageValue(tools2.getDamageValue() + 1);
                }
            }
        }
    }

    //Damage a ItemStackHandler's stacks
    public static void damageItemStackHandler(ItemStackHandler itemStackHandler){
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            int damageValue = itemStackHandler.getStackInSlot(i).getDamageValue();
            if(damageValue + 1 < itemStackHandler.getStackInSlot(i).getMaxDamage()) itemStackHandler.getStackInSlot(i).setDamageValue(damageValue + 1);
        }
    }

    //减少一个ItemStackHandler
    public static void reduceItemStackHandler(ItemStackHandler itemStackHandler) {
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            itemStackHandler.getStackInSlot(i).shrink(1);
        }
    }

    //判断是否符合烹饪工具
    public abstract boolean isComplianceCookingTool(CookingTool cookingTool);

    //获取烹饪的佳肴
    public ItemStack getCookingItemStack(){
        ItemStack itemStack = ItemStack.EMPTY;
        for(CookingRecipe cookingRecipe : CookingRecipe.COOKING_RECIPE_MAP.values()){
            //烹饪工具
            if(this.isComplianceCookingTool(cookingRecipe.cookingTool)){
                //装盘工具
                if(this.toolsItemStackHandler.getStackInSlot(0).getItem() instanceof DishesVarietyItem && ((DishesVarietyItem)this.toolsItemStackHandler.getStackInSlot(0).getItem()).cookingDishesType == cookingRecipe.cookingDishesType)
                //烹饪材料
                if(cookingRecipe.isComplianceItemsWithMenu(this.cookingStacksItemStackHandler, this.addCondimentItemStackHandler)){
                    itemStack = new ItemStack(cookingRecipe.getCookingItem());
                    couldTake = true;
                    break;
                } else {
                    couldTake = false;
                }
            }
        }
        return itemStack;
    }

    public CompoundTag saveOthersCompoundTag(){
        return new CompoundTag();
    }

    public void loadOthersCompoundTag(CompoundTag compoundTag){
        //TOOL
    }

    public void load(CompoundTag nbt) {
        super.load(nbt);
        cookingStacksItemStackHandler.deserializeNBT(nbt.getCompound("cookingStacksItemStackHandler"));
        addCondimentItemStackHandler.deserializeNBT(nbt.getCompound("addCondimentItemStackHandler"));
        toolsItemStackHandler.deserializeNBT(nbt.getCompound("toolsItemStackHandler"));
        loadOthersCompoundTag(nbt.getCompound("saveOthersCompoundTag"));
    }

    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.put("cookingStacksItemStackHandler", this.cookingStacksItemStackHandler.serializeNBT());
        compoundTag.put("addCondimentItemStackHandler", this.addCondimentItemStackHandler.serializeNBT());
        compoundTag.put("toolsItemStackHandler", this.toolsItemStackHandler.serializeNBT());
        compoundTag.put("saveOthersCompoundTag",saveOthersCompoundTag());
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.handleUpdateTag(Objects.requireNonNull(pkt.getTag()));
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag = super.getUpdateTag();
        compoundTag.put("cookingStacksItemStackHandler", this.cookingStacksItemStackHandler.serializeNBT());
        compoundTag.put("addCondimentItemStackHandler", this.addCondimentItemStackHandler.serializeNBT());
        compoundTag.put("toolsItemStackHandler", this.toolsItemStackHandler.serializeNBT());
        compoundTag.put("saveOthersCompoundTag",saveOthersCompoundTag());
        return compoundTag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void handleUpdateTag(CompoundTag nbt) {
        cookingStacksItemStackHandler.deserializeNBT(nbt.getCompound("cookingStacksItemStackHandler"));
        addCondimentItemStackHandler.deserializeNBT(nbt.getCompound("addCondimentItemStackHandler"));
        toolsItemStackHandler.deserializeNBT(nbt.getCompound("toolsItemStackHandler"));
        loadOthersCompoundTag(nbt.getCompound("saveOthersCompoundTag"));
    }
}
