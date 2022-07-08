package com.daylight.civilization_fire.block.cooking;

import com.daylight.civilization_fire.item.cooking.CondimentItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.*;

public class CookingBlockEntity extends BlockEntity {
    public List<ItemStack> cookingStacks = new ArrayList<>();
    public Map<CondimentItem,Boolean> addCondimentItem = new HashMap<>();
    public int cookingTime;

    public CookingBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state);
    }

    public void load(CompoundTag nbt) {
        super.load(nbt);
        cookingStacks.clear();
        ListTag listTag = nbt.getList("cookingStacks", 10);
        for (int i = 0; i < listTag.size(); i++) {
            cookingStacks.add(ItemStack.of(listTag.getCompound(i)));
        }
        addCondimentItem.clear();
        ListTag addCondimentItemListTag = nbt.getList("addCondimentItem", 10);
        for (int i = 0; i < addCondimentItemListTag.size(); i++) {
            CompoundTag tag = addCondimentItemListTag.getCompound(i);
            addCondimentItem.put((CondimentItem) Item.byId(tag.getInt("key")),tag.getBoolean("is"));
        }
    }

    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        ListTag listTag = new ListTag();
        for (ItemStack itemStack : this.cookingStacks) {
            listTag.add(itemStack.serializeNBT());
        }
        compoundTag.put("cookingStacks", listTag);
        ListTag addCondimentItemListTag = new ListTag();
        addCondimentItem.forEach((key,value) ->{
            CompoundTag tag = new CompoundTag();
            tag.putInt("key", Item.getId(key));
            tag.putBoolean("is",value);
            addCondimentItemListTag.add(tag);
        });
        compoundTag.put("addCondimentItem", addCondimentItemListTag);
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
        ListTag addCondimentItemListTag = new ListTag();
        addCondimentItem.forEach((key,value) ->{
            CompoundTag tag = new CompoundTag();
            tag.putInt("key", Item.getId(key));
            tag.putBoolean("is",value);
            addCondimentItemListTag.add(tag);
        });
        compoundTag.put("addCondimentItem", addCondimentItemListTag);
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
        addCondimentItem.clear();
        ListTag addCondimentItemListTag = nbt.getList("addCondimentItem", 10);
        for (int i = 0; i < addCondimentItemListTag.size(); i++) {
            CompoundTag tag = addCondimentItemListTag.getCompound(i);
            addCondimentItem.put((CondimentItem) Item.byId(tag.getInt("key")),tag.getBoolean("is"));
        }
    }
}
