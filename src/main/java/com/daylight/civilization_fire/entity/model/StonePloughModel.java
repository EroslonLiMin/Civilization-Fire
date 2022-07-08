package com.daylight.civilization_fire.entity.model;// Made with Blockbench 4.2.4

// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.daylight.civilization_fire.entity.agriculture.PloughEntity;
import com.daylight.civilization_fire.util.Utils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

//石犁模型
public class StonePloughModel extends EntityModel<PloughEntity.StonePloughEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation(Utils.MOD_ID, "stoneplough"), "main");
	private final ModelPart bb_main;

	public StonePloughModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-1.5F, -1.0F, -7.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
						.texOffs(0, 0).addBox(-1.0F, -1.0F, -9.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(0, 0).addBox(-0.5F, -1.0F, -10.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(0, 0).addBox(-1.0F, -2.0F, -6.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
						.texOffs(0, 0).addBox(-0.5F, -2.0F, -8.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(0, 18).addBox(-5.0F, -15.0F, 10.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		bb_main.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(22, 0).addBox(-0.5F, -12.0F, 0.0F, 1.0F, 12.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.0F, -3.0F, 0.5672F, 0.0F, 0.0F));

		bb_main.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(14, 0).addBox(-1.0F, -4.0F, -0.5F, 2.0F, 6.0F, 2.0F,
						new CubeDeformation(-0.1F)),
				PartPose.offsetAndRotation(0.0F, -13.0F, -13.0F, 1.5708F, 0.0F, 0.0F));

		bb_main.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(14, 0).addBox(-1.0F, -15.0F, 0.0F, 2.0F, 16.0F, 2.0F,
						new CubeDeformation(-0.2F)),
				PartPose.offsetAndRotation(0.0F, -7.0F, 2.0F, 1.1781F, 0.0F, 0.0F));

		bb_main.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(14, 0).addBox(-1.0F, -12.0F, 2.0F, 2.0F, 20.0F, 2.0F,
						new CubeDeformation(-0.1F)),
				PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 26, 22);
	}

	@Override
	public void setupAnim(PloughEntity.StonePloughEntity entity, float limbSwing, float limbSwingAmount,
			float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}