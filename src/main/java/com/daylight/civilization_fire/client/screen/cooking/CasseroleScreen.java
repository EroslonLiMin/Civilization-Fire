package com.daylight.civilization_fire.client.screen.cooking;

import com.daylight.civilization_fire.client.screen.BaseContainerScreen;
import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.block.cooking.CasseroleBlock;
import com.daylight.civilization_fire.common.content.menu.cooking.CasseroleMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CasseroleScreen extends BaseContainerScreen<CasseroleMenu> {
    public final CasseroleBlock.CasseroleBlockEntity casseroleBlockEntity;
    public CasseroleScreen(CasseroleMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle, CivilizationFire.resource("textures/screen/casserole_screen.png"));
        this.imageWidth = 216;
        this.imageHeight = 216;
        this.casseroleBlockEntity = pMenu.blockEntity;
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(matrixStack, partialTicks, mouseX, mouseY);
        RenderSystem.setShaderTexture(0,CivilizationFire.resource("textures/screen/cooking_fire.png"));
        blit(matrixStack, leftPos + 46, topPos + 116, 0,0, (int) (124 * (casseroleBlockEntity.cookingFire / 1000.0)), 8 ,124, 8);
    }
}