package com.daylight.civilization_fire.client.model;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.entity.bot.FarmingBot;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class FarmingBotModel extends AnimatedTickingGeoModel<FarmingBot> {

    @Override
    public ResourceLocation getModelLocation(FarmingBot object) {
        return CivilizationFire.resource("geo/farming_bot.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FarmingBot object) {
        return CivilizationFire.resource("textures/entity/farming_bot.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FarmingBot animatable) {
        return CivilizationFire.resource("animations/entity/farming_bot.animation.json");
    }
}
