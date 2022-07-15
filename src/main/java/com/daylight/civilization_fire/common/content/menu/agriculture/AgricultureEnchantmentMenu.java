package com.daylight.civilization_fire.common.content.menu.agriculture;

import com.daylight.civilization_fire.common.content.menu.CivilizationBaseMenu;
import com.daylight.civilization_fire.common.content.register.CivilizationFireMenuTypes;

import net.minecraft.world.entity.player.Inventory;

/**
 * Agriculture enchantment table's menu, which is opened when the player right-clicks the block.
 * Used to handle gui's interaction logic.
 *
 * @author Heckerpowered
 * @see com.daylight.civilization_fire.common.content.block.agriculture.AgricultureEnchantmentBlockTable
 * @see com.daylight.civilization_fire.common.content.block.agriculture.AgricultureEnchantmentBlockTable.AgricultureEnchantmentTableBlockEntity
 * @see com.daylight.civilization_fire.client.screen.agriculture.AgricultureEnchantmentScreen
 * @see com.daylight.civilization_fire.common.content.block.agriculture.AgricultureEnchantmentBlockTable#getMenuProvider(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.Level, net.minecraft.core.BlockPos)
 */
public final class AgricultureEnchantmentMenu extends CivilizationBaseMenu {

    public AgricultureEnchantmentMenu(int containerId, Inventory inventory) {
        super(CivilizationFireMenuTypes.AGRICULTURE_ENCHANTMENT_MENU.get(), containerId, inventory);
    }
}
