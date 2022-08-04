package com.daylight.civilization_fire.common.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.Collections;
import java.util.List;

public class HasDropBlock extends Block {
    public int level;
    public HasDropBlock(int level,Properties pProperties) {
        super(pProperties);
        this.level = level;
    }


    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
        if (player.getInventory().getSelected().getItem() instanceof TieredItem tieredItem)
            return tieredItem.getTier().getLevel() >= level;
        return false;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }

    public abstract static class HasDropBlockBaseEntity extends BaseEntityBlock{
        public int level;
        public HasDropBlockBaseEntity(int level,Properties pProperties) {
            super(pProperties);
            this.level = level;
        }


        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof TieredItem tieredItem)
                return tieredItem.getTier().getLevel() >= level;
            return false;
        }

        @Override
        public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
            List<ItemStack> dropsOriginal = super.getDrops(state, builder);
            if (!dropsOriginal.isEmpty())
                return dropsOriginal;
            return Collections.singletonList(new ItemStack(this, 1));
        }
    }
}
