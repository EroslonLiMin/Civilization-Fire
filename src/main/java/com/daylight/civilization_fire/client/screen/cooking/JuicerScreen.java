package com.daylight.civilization_fire.client.screen.cooking;

import com.daylight.civilization_fire.client.screen.BaseContainerScreen;
import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.menu.cooking.JuicerMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class JuicerScreen extends BaseContainerScreen<JuicerMenu> {
    public JuicerScreen(JuicerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle, CivilizationFire.resource("textures/screen/juicer_screen.png"));
        this.imageWidth = this.imageHeight = 216;
    }
}
