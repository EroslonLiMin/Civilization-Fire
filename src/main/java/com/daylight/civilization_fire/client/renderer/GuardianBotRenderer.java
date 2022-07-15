package com.daylight.civilization_fire.client.renderer;

import com.daylight.civilization_fire.client.model.GuardianBotModel;
import com.daylight.civilization_fire.common.content.entity.bot.GuardianBot;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GuardianBotRenderer extends GeoEntityRenderer<GuardianBot> {
    public GuardianBotRenderer(EntityRendererProvider.Context context) {
        super(context, new GuardianBotModel());
    }
}
