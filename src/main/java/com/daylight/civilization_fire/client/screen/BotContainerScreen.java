package com.daylight.civilization_fire.client.screen;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.entity.bot.Bot;
import com.daylight.civilization_fire.common.content.menu.BotMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class BotContainerScreen extends BaseContainerScreen<BotMenu> {
    public Bot bot;

    public BotContainerScreen(BotMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle, CivilizationFire.resource("textures/gui/bot_screen.png"));
        this.bot = pMenu.bot;
        this.imageWidth = 268;
        this.imageHeight = 138;

    }

    int mouseX;
    int mouseY;

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.mouseX = pMouseX;
        this.mouseY = pMouseY;
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(matrixStack, partialTicks, mouseX, mouseY);
        RenderSystem.setShaderTexture(0, CivilizationFire.resource("textures/gui/bot_screen_energy.png"));
        blit(matrixStack, leftPos + 7, topPos + 27, 0, 0, 8, (int) (104 * ((bot.getEnergy() + 0.0) / bot.getMaxEnergy())), 8, 104);
        InventoryScreen.renderEntityInInventory(leftPos + 50, topPos + 100, 30, (float) (leftPos + 50) - this.mouseX, (float) (topPos + 100 - 50) - this.mouseY, bot);
    }
}