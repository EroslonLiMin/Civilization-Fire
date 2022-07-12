package com.daylight.civilization_fire.common.content.block.cooking;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

import com.daylight.civilization_fire.common.content.item.cooking.CondimentItem;
import net.minecraftforge.items.ItemStackHandler;

import java.util.*;

public class CookingBlockEntity extends BlockEntity {
    public ItemStackHandler cookingStacks = new ItemStackHandler();
    public Map<CondimentItem, Boolean> addCondimentItem = new HashMap<>();
    public int cookingTime;

    public CookingBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state);
    }

    //tick进行刷新
    public static void tick(Level level, BlockPos pos, BlockState blockState,
            CookingBlockEntity plantBlockEntity) {
        BlockState belowState = level.getBlockState(pos.below());
        if (belowState.getBlock() instanceof CookingBench) {
            if (belowState.getValue(CookingBench.BENCH_STATE) > 4) {
                plantBlockEntity.cookingTime += 1;
                level.addParticle(ParticleTypes.SMALL_FLAME, pos.getX() + 0.5, pos.getY()-0.75, pos.getZ(), 0.0D, 0.0D,
                        0.0D);
                level.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5, pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public CompoundTag saveOthersCompoundTag(){
        return new CompoundTag();
    }

    public void loadOthersCompoundTag(CompoundTag compoundTag){
        //TOOL
    }

    public void load(CompoundTag nbt) {
        super.load(nbt);
        cookingStacks.deserializeNBT(nbt.getCompound("cookingStacks"));
        addCondimentItem.clear();
        ListTag addCondimentItemListTag = nbt.getList("addCondimentItem", 10);
        for (int i = 0; i < addCondimentItemListTag.size(); i++) {
            CompoundTag tag = addCondimentItemListTag.getCompound(i);
            addCondimentItem.put((CondimentItem) Item.byId(tag.getInt("key")), tag.getBoolean("is"));
        }
        loadOthersCompoundTag(nbt.getCompound("saveOthersCompoundTag"));
    }

    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.put("cookingStacks", this.cookingStacks.serializeNBT());
        ListTag addCondimentItemListTag = new ListTag();
        addCondimentItem.forEach((key, value) -> {
            CompoundTag tag = new CompoundTag();
            tag.putInt("key", Item.getId(key));
            tag.putBoolean("is", value);
            addCondimentItemListTag.add(tag);
        });
        compoundTag.put("addCondimentItem", addCondimentItemListTag);
        compoundTag.put("saveOthersCompoundTag",saveOthersCompoundTag());
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.handleUpdateTag(Objects.requireNonNull(pkt.getTag()));
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag = super.getUpdateTag();
        compoundTag.put("cookingStacks", this.cookingStacks.serializeNBT());
        ListTag addCondimentItemListTag = new ListTag();
        addCondimentItem.forEach((key, value) -> {
            CompoundTag tag = new CompoundTag();
            tag.putInt("key", Item.getId(key));
            tag.putBoolean("is", value);
            addCondimentItemListTag.add(tag);
        });
        compoundTag.put("addCondimentItem", addCondimentItemListTag);
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
        cookingStacks.deserializeNBT(nbt.getCompound("cookingStacks"));
        addCondimentItem.clear();
        ListTag addCondimentItemListTag = nbt.getList("addCondimentItem", 10);
        for (int i = 0; i < addCondimentItemListTag.size(); i++) {
            CompoundTag tag = addCondimentItemListTag.getCompound(i);
            addCondimentItem.put((CondimentItem) Item.byId(tag.getInt("key")), tag.getBoolean("is"));
        }
        loadOthersCompoundTag(nbt.getCompound("saveOthersCompoundTag"));
    }
}
