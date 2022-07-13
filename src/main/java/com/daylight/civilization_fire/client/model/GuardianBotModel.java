package com.daylight.civilization_fire.client.model;


import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.entity.bot.GuardianBot;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class GuardianBotModel extends AnimatedTickingGeoModel<GuardianBot> {

	@Override
	public ResourceLocation getModelLocation(GuardianBot object) {
		return CivilizationFire.resource("models/entity/guardian_bot.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(GuardianBot object) {
		return CivilizationFire.resource("textures/entity/guardian_bot.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(GuardianBot animatable) {
		return CivilizationFire.resource("animation/guardian_bot.animation.json");
	}
}