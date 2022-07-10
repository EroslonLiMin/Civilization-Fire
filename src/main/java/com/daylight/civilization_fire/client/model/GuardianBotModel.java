package com.daylight.civilization_fire.client.model;// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.daylight.civilization_fire.common.content.entity.bot.GuardianBot;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class GuardianBotModel extends EntityModel<GuardianBot> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "custom_model"), "main");
	private final ModelPart zong;

	public GuardianBotModel(ModelPart root) {
		this.zong = root.getChild("zong");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition zong = partdefinition.addOrReplaceChild("zong", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition body = zong.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 0).addBox(-6.0F, -3.0F, -3.0F, 2.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(4.0F, -3.0F, -3.0F, 2.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(30, 34).addBox(-3.0F, 6.0F, -1.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -28.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(27, 17).mirror().addBox(-3.0F, -2.0F, -2.0F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 2.0F, -4.0F, 0.0F, -0.3054F, -0.3054F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(27, 17).addBox(-4.0F, -2.0F, -2.0F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 2.0F, -4.0F, 0.0F, 0.3054F, 0.3054F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(34, 2).addBox(-1.0F, -5.0F, -3.0F, 2.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 1.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -3.0F, -6.0F, 8.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -1.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition head = zong.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-4.5F, -8.5F, -4.5F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -29.0F, 0.0F));

		PartDefinition rightarm = zong.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(0, 56).addBox(-10.0F, -6.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(-0.1F))
		.texOffs(52, 0).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -26.0F, 2.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition rightarm2 = rightarm.addOrReplaceChild("rightarm2", CubeListBuilder.create().texOffs(68, 0).addBox(-3.0F, -1.0F, -3.0F, 5.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(65, 16).addBox(-1.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 7.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition jian = rightarm2.addOrReplaceChild("jian", CubeListBuilder.create().texOffs(58, 86).addBox(-1.0F, -4.5F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(52, 64).addBox(-2.0F, -12.5F, -4.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(54, 30).addBox(-1.0F, -26.5F, -3.0F, 2.0F, 20.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 8.5F, 1.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r5 = jian.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(60, 56).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -26.5F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rightleg = zong.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(49, 14).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -15.0F, 2.0F));

		PartDefinition rightleg2 = rightleg.addOrReplaceChild("rightleg2", CubeListBuilder.create().texOffs(0, 76).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 14.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 43).addBox(-2.0F, 9.9F, -6.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 35).addBox(-1.0F, 8.0F, -7.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 9.0F, 0.0F));

		PartDefinition leftleg = zong.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(49, 14).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -15.0F, 2.0F));

		PartDefinition leftleg2 = leftleg.addOrReplaceChild("leftleg2", CubeListBuilder.create().texOffs(0, 76).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 14.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 43).addBox(-2.0F, 9.9F, -6.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 35).addBox(-1.0F, 8.0F, -7.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 9.0F, 0.0F));

		PartDefinition pao = zong.addOrReplaceChild("pao", CubeListBuilder.create().texOffs(34, 74).addBox(-3.0F, -16.0F, 3.0F, 6.0F, 16.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(36, 48).addBox(-2.0F, -4.0F, -1.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -26.0F, 6.0F));

		PartDefinition leftarm = zong.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 56).mirror().addBox(0.0F, -6.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(-0.1F)).mirror(false)
		.texOffs(52, 0).addBox(0.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -26.0F, 2.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition leftarm2 = leftarm.addOrReplaceChild("leftarm2", CubeListBuilder.create().texOffs(68, 0).mirror().addBox(-2.0F, -1.0F, -3.0F, 5.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(65, 16).mirror().addBox(-3.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 7.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		return LayerDefinition.create(meshdefinition, 96, 96);
	}

	@Override
	public void setupAnim(GuardianBot entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		zong.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}