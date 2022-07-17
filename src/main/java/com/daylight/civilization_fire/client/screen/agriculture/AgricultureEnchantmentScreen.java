package com.daylight.civilization_fire.client.screen.agriculture;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.daylight.civilization_fire.client.screen.BaseContainerScreen;
import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.block.agriculture.AgricultureEnchantmentBlockTable;
import com.daylight.civilization_fire.common.content.menu.agriculture.AgricultureEnchantmentMenu;

/**
 * Argiculture enchantment screen, open when the player right-clicks the agriculture enchantment table.
 *
 * @author Heckerpowered
 * @see AgricultureEnchantmentBlockTable
 */
@OnlyIn(Dist.CLIENT)
public final class AgricultureEnchantmentScreen extends BaseContainerScreen<AgricultureEnchantmentMenu> {

    public AgricultureEnchantmentScreen(AgricultureEnchantmentMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, CivilizationFire.resource("textures/gui/agriculture_enchantment_screen.png"));
        this.imageWidth = 216;
        this.imageHeight = 216;
    }

}
