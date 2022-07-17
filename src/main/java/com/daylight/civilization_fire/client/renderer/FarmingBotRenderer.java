package com.daylight.civilization_fire.client.renderer;

import com.daylight.civilization_fire.client.model.FarmingBotModel;
import com.daylight.civilization_fire.common.content.entity.bot.FarmingBot;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FarmingBotRenderer extends GeoEntityRenderer<FarmingBot> {
    public FarmingBotRenderer(EntityRendererProvider.Context context) {
        super(context, new FarmingBotModel());
    }
}
