package com.daylight.civilization_fire.client.model;


import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.entity.bot.MiningBot;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class MiningBotModel extends AnimatedTickingGeoModel<MiningBot> {

	@Override
	public ResourceLocation getModelLocation(MiningBot object) {
		return CivilizationFire.resource("geo/mining_bot.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(MiningBot object) {
		return CivilizationFire.resource("textures/entity/mining_bot.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(MiningBot animatable) {
		return CivilizationFire.resource("animations/entity/mining_bot.animation.json");
	}
}