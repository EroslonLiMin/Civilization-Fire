package com.daylight.civilization_fire.client.renderer;

import com.daylight.civilization_fire.client.model.GuardianBotModel;
import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.entity.bot.GuardianBot;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GuardianBotRenderer extends GeoEntityRenderer<GuardianBot> {
    public GuardianBotRenderer(EntityRendererProvider.Context context) {
        super(context, new GuardianBotModel());
    }

    @Override
    public ResourceLocation getTextureLocation(GuardianBot guardianBot) {
        return CivilizationFire.resource("textures/entity/guardian_bot.png");
    }
}
