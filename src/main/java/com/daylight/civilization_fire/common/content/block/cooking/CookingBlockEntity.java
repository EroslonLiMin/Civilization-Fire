package com.daylight.civilization_fire.common.content.block.cooking;

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
    public int cookingFire;

    public CookingBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state);
    }

    //tick进行刷新
    public static void tick(Level level, BlockPos pos, BlockState blockState,
            CookingBlockEntity cookingBlockEntity) {
        BlockState belowState = level.getBlockState(pos.below());
        if (belowState.getBlock() instanceof CookingBench) {
            if (belowState.getValue(CookingBench.BENCH_STATE) > 4) {
                cookingBlockEntity.cookingFire = (cookingBlockEntity.cookingFire >= 1000) ? 1000 : cookingBlockEntity.cookingFire + 1;
                level.addParticle(ParticleTypes.SMALL_FLAME, pos.getX() + 0.5, pos.getY()-0.75, pos.getZ(), 0.0D, 0.0D,
                        0.0D);
                level.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5, pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
        if(cookingBlockEntity.cookingFire >= 500){
            cookingBlockEntity.outputItemStackHandler.setStackInSlot(0,cookingBlockEntity.getCookingItemStack());
        }
    }

    public abstract boolean isComplianceCookingTool(CookingTool cookingTool);

    public ItemStack getCookingItemStack(){
        ItemStack itemStack = ItemStack.EMPTY;
        for(CookingRecipe cookingRecipe : CookingRecipe.COOKING_RECIPE_MAP.values()){
            if(this.isComplianceCookingTool(cookingRecipe.cookingTool)){
                if(cookingRecipe.isComplianceItemsWithMenu(this.cookingStacksItemStackHandler, this.addCondimentItemStackHandler)){
                    itemStack = new ItemStack(cookingRecipe.getCookingItem());
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
        loadOthersCompoundTag(nbt.getCompound("saveOthersCompoundTag"));
    }

    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.put("cookingStacksItemStackHandler", this.cookingStacksItemStackHandler.serializeNBT());
        compoundTag.put("addCondimentItemStackHandler", this.addCondimentItemStackHandler.serializeNBT());
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
        loadOthersCompoundTag(nbt.getCompound("saveOthersCompoundTag"));
    }
}
