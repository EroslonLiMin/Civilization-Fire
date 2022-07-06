package com.daylight.civilization_fire.block.cooking;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CookingBlockEntity extends BlockEntity {
    public List<ItemStack> cookingStacks = new ArrayList<>();

    public CookingBlockEntity(BlockEntityType<?> p_155228_, BlockPos pos, BlockState state) {
        super(p_155228_, pos, state);
    }

    public void load(CompoundTag nbt) {
        super.load(nbt);
        cookingStacks.clear();
        ListTag listTag = nbt.getList("cookingStacks", 10);
        for (int i = 0; i < listTag.size(); i++) {
            cookingStacks.add(ItemStack.of(listTag.getCompound(i)));
        }
    }

    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        ListTag listTag = new ListTag();
        for (ItemStack itemStack : this.cookingStacks) {
            listTag.add(itemStack.serializeNBT());
        }
        compoundTag.put("cookingStacks", listTag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.handleUpdateTag(Objects.requireNonNull(pkt.getTag()));
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag = super.getUpdateTag();
        ListTag listTag = new ListTag();
        for (ItemStack itemStack : this.cookingStacks) {
            listTag.add(itemStack.serializeNBT());
        }
        compoundTag.put("cookingStacks", listTag);
        return compoundTag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void handleUpdateTag(CompoundTag nbt) {
        cookingStacks.clear();
        ListTag listTag = nbt.getList("cookingStacks", 10);
        for (int i = 0; i < listTag.size(); i++) {
            cookingStacks.add(ItemStack.of(listTag.getCompound(i)));
        }
    }
}
