package com.daylight.civilization_fire.client.screen;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class FireWorkScreen extends Screen {

    public static final ResourceLocation TEXTURE_LOCATION = CivilizationFire
            .resource("textures/gui/fire_work_screen.png");

    private FireWorkTab selectedTab;

    public Map<FireWorkTab, FireWorkNode> nodes = Maps.newLinkedHashMap();
    private static final Component TITLE = new TranslatableComponent("gui.fire_work");

    public FireWorkScreen() {
        super(TITLE);
    }

    @Override
    protected void init() {
        super.init();
        this.selectedTab = new FireWorkTab("test");
        nodes.put(selectedTab, new FireWorkNode());
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        RenderSystem.setShaderTexture(0, TEXTURE_LOCATION);
        poseStack.pushPose();
        int offsetX = (this.width - 216) / 2;
        int offsetY = (this.height - 203) / 2;
        this.blit(poseStack, offsetX, offsetY, 0, 13, 216, 203);
        double guiScale = this.minecraft.getWindow().getGuiScale();
        //RenderSystem.enableScissor((int) ((offsetX + 17) * guiScale), (int) ((offsetY + 94) * guiScale) + 2, (int) (182 * guiScale), (int) (88 * guiScale));

        if (selectedTab != null) {
            nodes.get(selectedTab).drawNode(poseStack, offsetX + (216 / 2) - 16, offsetY + (130 / 2) - 16);
        }

        //RenderSystem.disableScissor();
        poseStack.popPose();
    }

}
