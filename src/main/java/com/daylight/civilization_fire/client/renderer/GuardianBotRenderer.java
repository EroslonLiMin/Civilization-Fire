package com.daylight.civilization_fire.client.renderer;

import com.daylight.civilization_fire.client.model.GuardianBotModel;
import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.entity.bot.GuardianBot;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class GuardianBotRenderer extends LivingEntityRenderer<GuardianBot, GuardianBotModel> {
    public GuardianBotRenderer(EntityRendererProvider.Context context, GuardianBotModel guardianBotModel, float shadow) {
        super(context, guardianBotModel, shadow);
    }

    @Override
    public ResourceLocation getTextureLocation(GuardianBot guardianBot) {
        return CivilizationFire.resource("textures/entity/guardian_bot.png");
    }
}
