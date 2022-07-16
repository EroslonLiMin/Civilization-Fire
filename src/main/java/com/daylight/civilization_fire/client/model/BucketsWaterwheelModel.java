package com.daylight.civilization_fire.client.model;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.entity.agriculture.BucketsWaterwheelEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class BucketsWaterwheelModel extends AnimatedTickingGeoModel<BucketsWaterwheelEntity> {
    @Override
    public ResourceLocation getModelLocation(BucketsWaterwheelEntity object) {
        return CivilizationFire.resource("geo/buckets_waterwheel.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BucketsWaterwheelEntity object) {
        return CivilizationFire.resource("textures/entity/buckets_waterwheel.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BucketsWaterwheelEntity animatable) {
        return CivilizationFire.resource("animations/entity/buckets_waterwheel.animation.json");
    }
}
