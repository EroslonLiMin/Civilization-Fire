package com.daylight.civilization_fire.client.screen.agriculture;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

import com.daylight.civilization_fire.client.screen.BaseContainerScreen;
import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.block.agriculture.AgricultureEnchantmentTableBlock;
import com.daylight.civilization_fire.common.content.menu.agriculture.AgricultureEnchantmentMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

/**
 * Argiculture enchantment screen, open when the player right-clicks the agriculture enchantment table.
 *
 * @author Heckerpowered
 * @see AgricultureEnchantmentTableBlock
 */
@OnlyIn(Dist.CLIENT)
public final class AgricultureEnchantmentScreen extends BaseContainerScreen<AgricultureEnchantmentMenu> {

    /**
     * The ResourceLocation containg the Enchantment content backrgound texture location.
     */
    private static final ResourceLocation ENCHANTING_CONTENT_LOCATION = CivilizationFire
            .resource("textures/screen/agriculture_enchantment_content.png");

    public AgricultureEnchantmentScreen(AgricultureEnchantmentMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, CivilizationFire.resource("textures/gui/agriculture_enchantment_screen.png"));

        //
        // Set the image's size.
        //
        imageHeight = imageWidth = 216;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        super.render(poseStack, mouseX, mouseY, partialTick);
        for (int i = 0; i < 6; i = -~i) {
            renderContentBackground(poseStack, i);
        }
    }

    /**
     * Render enchantment content's background.
     *
     * @param poseStack Matrix stack.
     * @param index Index of the enchantment content, starts from 0;
     */
    private void renderContentBackground(@Nonnull final PoseStack poseStack, final int index) {
        RenderSystem.setShaderTexture(0, ENCHANTING_CONTENT_LOCATION);
        int height = 18;
        if (index == 5) {
            height = 10;
        } else if (index > 5) {
            height = 0;
        }

        blit(poseStack, leftPos + 76, topPos + 16 + 18 * index, 0, 0, 106, height, 106, 18);
    }
}
