package com.daylight.civilization_fire.client.renderer;

import com.daylight.civilization_fire.client.model.KeelWaterwheelModel;
import com.daylight.civilization_fire.client.model.MiningBotModel;
import com.daylight.civilization_fire.common.content.entity.agriculture.KeelWaterwheelEntity;
import com.daylight.civilization_fire.common.content.entity.bot.MiningBot;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class KeelWaterwheelRenderer extends GeoEntityRenderer<KeelWaterwheelEntity> {
    public KeelWaterwheelRenderer(EntityRendererProvider.Context context) {
        super(context, new KeelWaterwheelModel());
    }

}
