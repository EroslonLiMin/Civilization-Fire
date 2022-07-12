package com.daylight.civilization_fire.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;


public class BaseContainerScreen <T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
    private final ResourceLocation GUI;

    public BaseContainerScreen(T pMenu, Inventory pPlayerInventory, Component pTitle, ResourceLocation gui) {
        super(pMenu, pPlayerInventory, pTitle);
        this.GUI = gui;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        this.renderTooltip(pPoseStack, pMouseX, pMouseY);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        this.blit(matrixStack, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);
    }
}