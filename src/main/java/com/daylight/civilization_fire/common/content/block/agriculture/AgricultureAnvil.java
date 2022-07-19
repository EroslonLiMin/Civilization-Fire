package com.daylight.civilization_fire.common.content.block.agriculture;

import com.daylight.civilization_fire.common.content.menu.agriculture.AgricultureAnvilMenu;

import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;

/**
 * Agriculture anvil block, used to enchant, repair or rename item with fruits.
 * Compare to vanilla anvil, it only change what is needed to enchant, repair or rename
 * items.
 */
@FieldsAreNonnullByDefault
@MethodsReturnNonnullByDefault
public final class AgricultureAnvil extends AnvilBlock {
    private static final Component CONTAINER_TITLE = new TranslatableComponent("container.repair");

    /**
     * Create a new agriculture anvil block.
     */
    public AgricultureAnvil() {
        super(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.METAL).requiresCorrectToolForDrops()
                .strength(5.0F, 1200.0F).sound(SoundType.ANVIL));
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos location) {
        return new SimpleMenuProvider((containerId, inventory, player) -> {
            return new AgricultureAnvilMenu(containerId, inventory, ContainerLevelAccess.create(level, location));
        }, CONTAINER_TITLE);
    }
}
