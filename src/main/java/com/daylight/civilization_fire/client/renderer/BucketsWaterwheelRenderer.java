package com.daylight.civilization_fire.client.renderer;

import com.daylight.civilization_fire.client.model.BucketsWaterwheelModel;
import com.daylight.civilization_fire.common.content.entity.agriculture.BucketsWaterwheelEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BucketsWaterwheelRenderer extends GeoEntityRenderer<BucketsWaterwheelEntity> {
    public BucketsWaterwheelRenderer(EntityRendererProvider.Context context) {
        super(context, new BucketsWaterwheelModel());
    }

}
