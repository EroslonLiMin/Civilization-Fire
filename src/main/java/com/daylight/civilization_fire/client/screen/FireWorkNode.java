package com.daylight.civilization_fire.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;

import java.util.ArrayList;
import java.util.List;

public class FireWorkNode extends GuiComponent {

    private final List<FireWorkNode> nodes = new ArrayList<>();

    public List<FireWorkNode> getNodes() {
        return nodes;
    }

    public void drawNode(PoseStack poseStack, int offsetX, int offsetY) {
        RenderSystem.setShaderTexture(0, FireWorkScreen.TEXTURE_LOCATION);
        this.blit(poseStack, offsetX, offsetY,  218, 0, 32, 32);

        hLine(poseStack, offsetX, offsetX + 100, offsetY, 0);
    }
}
