package com.daylight.civilization_fire.client.screen;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.register.CivilizationFireItems;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.advancements.AdvancementTab;
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

import java.awt.*;

public class FireScreen extends Screen {
    public FireScreen() {
        super(new TextComponent(""));
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        RenderSystem.setShaderTexture(0, CivilizationFire.resource("textures/gui/fire_screen.png"));
        blit(pPoseStack, this.width / 2 - 76, this.height/ 2 - 76, 0, 0, 152, 152, 152, 152);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    protected void init() {
        super.init();
        for(int i = 0;i < 5;i++) {
            int finalI = i;
            this.addRenderableWidget(new Button(this.width / 2 - 76 + switchPos(i)[0], this.height / 2 - 76 +  + switchPos(i)[1], 16, 16, new TextComponent(""), (button) -> {
                this.minecraft.setScreen(new AdvancementsScreen(this.minecraft.player.connection.getAdvancements()));
                changeAdvancementsScreen(switchStr(finalI));
            }) {
                @Override
                public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
                    this.isHovered = pMouseX >= this.x && pMouseY >= this.y && pMouseX < this.x + this.width && pMouseY < this.y + this.height;
                    Minecraft.getInstance().getItemRenderer().renderGuiItem(switchStack(finalI),this.x,this.y);
                    if(this.isHoveredOrFocused()){
                        RenderSystem.setShaderTexture(0, CivilizationFire.resource("textures/gui/fire_shadow.png"));
                        RenderSystem.enableBlend();
                        RenderSystem.defaultBlendFunc();
                        RenderSystem.enableDepthTest();
                        blit(pPoseStack, this.x, this.y, 0, 0, 16, 16, 16, 16);
                    }
                    drawCenteredString(pPoseStack, font, switchStack(finalI).getDisplayName().getString(), this.x + this.width / 2, this.y + 16, switchColor(finalI).getRGB());
                }
            });
        }
    }

    public String switchStr(int index){
        return switch (index) {
            case 0 -> "advancements.cooking_fire_base.title";
            case 1 -> "advancements.dragon_channel_base.title";
            case 2 -> "advancements.shennong_add_fire.title";
            case 3 -> "advancements.agricultural_spot_base.title";
            default -> "advancements.shennong_fire_base.title";
        };
    }

    public int[] switchPos(int index){
        return switch (index) {
            case 0 -> new int[]{32, 47};
            case 1 -> new int[]{37, 99 - 16};
            case 2 -> new int[]{104, 46};
            case 3 -> new int[]{104, 105 - 20};
            default -> new int[]{68, 68};
        };
    }

    public ItemStack switchStack(int index){
        return switch (index) {
            case 0 -> new ItemStack(CivilizationFireItems.ESOPHAGUS_CHANNEL_FIRE.get());
            case 1 -> new ItemStack(CivilizationFireItems.DRAGON_CHANNEL_FIRE.get());
            case 2 -> new ItemStack(CivilizationFireItems.SHENNONG_CHANNEL_FIRE.get());
            case 3 -> new ItemStack(CivilizationFireItems.AGRICULTURAL_SPOT_FIRE.get());
            default -> new ItemStack(CivilizationFireItems.GRAIN_SHENNONG_CHANNEL_FIRE.get());
        };
    }

    public Color switchColor(int index){
        return switch (index){
            case 0 -> new Color(212, 169, 109);
            case 1 -> new Color(36, 57, 128);
            case 2 -> new Color(158, 100, 45);
            case 3 -> new Color(43, 113, 66);
            default -> new Color(212, 66, 52);
        };
    }
    //改变成就面板选线
    public void changeAdvancementsScreen(String component){
        if(this.minecraft.screen instanceof AdvancementsScreen advancementsScreen){
            for(AdvancementTab advancementtab : advancementsScreen.tabs.values()) {
                TranslatableComponent translatableComponent = (TranslatableComponent) advancementtab.getTitle();
                if(component.equals(translatableComponent.getKey())){
                    advancementsScreen.advancements.setSelectedTab(advancementtab.getAdvancement(), true);
                }
            }
        }
    }


}
