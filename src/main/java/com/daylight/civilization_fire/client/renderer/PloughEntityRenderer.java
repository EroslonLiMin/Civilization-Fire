package com.daylight.civilization_fire.client.renderer;

import com.daylight.civilization_fire.common.content.entity.agriculture.PloughEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PloughEntityRenderer<T extends PloughEntity, M extends EntityModel<T>> extends MobRenderer<T, M> {
    public final ResourceLocation resourceLocation;//贴图资源文件

    public PloughEntityRenderer(EntityRendererProvider.Context context, ResourceLocation resourceLocation,
            M entityModel) {
        super(context, entityModel, 0.5F);
        this.resourceLocation = resourceLocation;
    }

    @Override
    protected boolean shouldShowName(T entity) {
        return false;
    }

    @Override
    public ResourceLocation getTextureLocation(PloughEntity ploughEntity) {
        return resourceLocation;
    }
}
