package com.daylight.civilization_fire.client.screen.cooking;

import com.daylight.civilization_fire.client.screen.BaseContainerScreen;
import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.menu.cooking.IronPotMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class IronPotScreen extends BaseContainerScreen<IronPotMenu> {
    public IronPotScreen(IronPotMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle, CivilizationFire.resource("textures/gui/iron_pot_screen.png"));
    }
}
