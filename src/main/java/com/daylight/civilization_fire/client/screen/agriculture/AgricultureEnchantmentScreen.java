package com.daylight.civilization_fire.client.screen.agriculture;

import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.daylight.civilization_fire.client.screen.BaseContainerScreen;
import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.block.agriculture.AgricultureEnchantmentTableBlock;
import com.daylight.civilization_fire.common.content.menu.agriculture.AgricultureEnchantmentMenu;
import com.mojang.blaze3d.vertex.PoseStack;

/**
 * Argiculture enchantment screen, open when the player right-clicks the agriculture enchantment table.
 *
 * @author Heckerpowered
 * @see AgricultureEnchantmentTableBlock
 */
@OnlyIn(Dist.CLIENT)
public final class AgricultureEnchantmentScreen extends BaseContainerScreen<AgricultureEnchantmentMenu> {
    private final Button sliderButton = new Button(187, 15, 10, 18,
            new TranslatableComponent("button.civilization_fire.agriculture_enchantment_screen.slider"), v -> {
            });

    public AgricultureEnchantmentScreen(AgricultureEnchantmentMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, CivilizationFire.resource("textures/gui/agriculture_enchantment_screen.png"));

        //
        // Set the image's size.
        //
        imageHeight = imageWidth = 216;
    }

    @Override
    protected void init() {

        super.init();
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        sliderButton.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }
}
