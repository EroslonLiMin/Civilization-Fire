package com.daylight.civilization_fire.common.content.block.agriculture;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.daylight.civilization_fire.common.content.menu.agriculture.AgricultureEnchantmentMenu;
import com.daylight.civilization_fire.common.content.register.CivilizationFireBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;

/**
 * Argiculture enchantment table
 * @author Heckerpowered
 */
public class AgricultureEnchantmentBlockTable extends BaseEntityBlock {

    public AgricultureEnchantmentBlockTable() {
        super(Properties.of(Material.STONE, MaterialColor.COLOR_RED).requiresCorrectToolForDrops().lightLevel(v -> 7));
    }

    @Nonnull
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AgricultureEnchantmentTableBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos location, Player player, InteractionHand hand,
            BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(level, location));
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState blockState, Level level, BlockPos location) {
        return new SimpleMenuProvider((containerId, playerInventory, player) -> {
            return new AgricultureEnchantmentMenu(containerId, playerInventory);
        }, new TranslatableComponent("container.enchant"));
    }

    /**
     * Agriculture enchantment table block entity.
     */
    public static class AgricultureEnchantmentTableBlockEntity extends BlockEntity {
        public AgricultureEnchantmentTableBlockEntity(BlockPos location, BlockState blockState) {
            super(CivilizationFireBlockEntities.AGRICULTURE_ENCHANTMENT_TABLE_BLOCK_ENTITY.get(), location, blockState);
        }
    }
}
