package com.daylight.civilization_fire.client.model;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.entity.agriculture.KeelWaterwheelEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class KeelWaterwheelModel extends AnimatedTickingGeoModel<KeelWaterwheelEntity> {
    @Override
    public ResourceLocation getModelLocation(KeelWaterwheelEntity object) {
        return CivilizationFire.resource("geo/keel_waterwheel.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(KeelWaterwheelEntity object) {
        return CivilizationFire.resource("textures/entity/keel_waterwheel.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(KeelWaterwheelEntity animatable) {
        return CivilizationFire.resource("animations/entity/keel_waterwheel.animation.json");
    }
}
