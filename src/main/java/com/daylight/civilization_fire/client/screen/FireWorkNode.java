package com.daylight.civilization_fire.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.FastColor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FireWorkNode extends GuiComponent {

    private FireWorkNode parent;
    private final List<FireWorkNode> nodes = new ArrayList<>();

    public void addNode(FireWorkNode node) {
        node.parent = this;
        nodes.add(node);
    }

    public void drawNode(PoseStack poseStack, int offsetX, int offsetY) {
        RenderSystem.setShaderTexture(0, FireWorkScreen.TEXTURE_LOCATION);
        this.blit(poseStack, offsetX, offsetY,  218, 0, 32, 32);

        if(parent != null) {
            hLine(poseStack, offsetX - 2, offsetX - (2 + 10) - (parent.nodes.indexOf(this) == 0 ? 1 : 0), offsetY + 15, FastColor.ARGB32.color(255, 175, 137, 60));
            hLine(poseStack, offsetX - 2, offsetX - (2 + 10), offsetY + 16, FastColor.ARGB32.color(255, 235, 207, 150));
            hLine(poseStack, offsetX - 2, offsetX - (2 + 10) - (parent.nodes.indexOf(this) == (parent.nodes.size() - 1) ? 1 : 0), offsetY + 17, FastColor.ARGB32.color(255, 175, 137, 60));
        }

        int leafNodeCount = this.getLeafCount();
        if(nodes.size() > 0) {
            hLine(poseStack, offsetX + 32 + 2, offsetX + 32 + 2 + 10, offsetY + 15, FastColor.ARGB32.color(255, 175, 137, 60));
            hLine(poseStack, offsetX + 32 + 2, offsetX + 32 + 2 + 10, offsetY + 16, FastColor.ARGB32.color(255, 235, 207, 150));
            hLine(poseStack, offsetX + 32 + 2, offsetX + 32 + 2 + 10, offsetY + 17, FastColor.ARGB32.color(255, 175, 137, 60));

            vLine(poseStack, offsetX + 44, offsetY + 15, offsetY + 16 - ((leafNodeCount * 32) / 2) + 14, FastColor.ARGB32.color(255, 175, 137, 60));
            vLine(poseStack, offsetX + 44, offsetY + 16 + ((leafNodeCount * 32) / 2) - 14, offsetY + 17, FastColor.ARGB32.color(255, 175, 137, 60));

            vLine(poseStack, offsetX + 45, offsetY + 16 + ((leafNodeCount * 32) / 2) - 15, offsetY + 16 - ((leafNodeCount * 32) / 2) + 15, FastColor.ARGB32.color(255, 235, 207, 150));

            vLine(poseStack, offsetX + 46, offsetY + 16 + ((leafNodeCount * 32) / 2) - 15, offsetY + 16 - ((leafNodeCount * 32) / 2) + 15, FastColor.ARGB32.color(255, 175, 137, 60));
        }
        for (int i = 0; i < nodes.size(); i++) {
            FireWorkNode fireWorkNode = nodes.get(i);
            int leafCount = getLeafCount(i);
            fireWorkNode.drawNode(poseStack, offsetX + 32 + 2 + 10 + 2 + 10 + 2, (offsetY + 16) - ((leafNodeCount * 32) / 2) + (32 * leafCount));
        }
    }

    public int getLeafCount(int index) {
        int leafCount = 0;
        for (int i = 0; i < index; i++) {
            FireWorkNode node = this.nodes.get(i);
            if (node.nodes.size() > 0) {
                leafCount += node.getLeafCount();
            } else {
                leafCount++;
            }
        }
        return leafCount;
    }

    public int getLeafCount() {
        int leafCount = 0;
        for (FireWorkNode node : this.nodes) {
            if(node.nodes.size() > 0) {
                leafCount += node.getLeafCount();
            } else {
                leafCount++;
            }
        }
        return leafCount;
    }
}
