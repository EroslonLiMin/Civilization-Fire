package com.daylight.civilization_fire.registry;

import com.daylight.civilization_fire.entity.model.CurvilinearPloughModel;
import com.daylight.civilization_fire.entity.model.IronPloughModel;
import com.daylight.civilization_fire.entity.model.StonePloughModel;
import com.daylight.civilization_fire.entity.renderer.PloughEntityRenderer;
import com.daylight.civilization_fire.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EntityRendererRegistry {
    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypeRegistry.STONE_PLOUGH_ENTITY.get(),(data) -> new PloughEntityRenderer<>(data, new ResourceLocation(Utils.MOD_ID, "textures/entity/stone_plough_entity.png"), new StonePloughModel(data.bakeLayer(StonePloughModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityTypeRegistry.IRON_PLOUGH_ENTITY.get(),(data) -> new PloughEntityRenderer<>(data, new ResourceLocation(Utils.MOD_ID, "textures/entity/iron_plough_entity.png"), new IronPloughModel(data.bakeLayer(IronPloughModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityTypeRegistry.CURVILINEAR_PLOUGH_ENTITY.get(),(data) -> new PloughEntityRenderer<>(data, new ResourceLocation(Utils.MOD_ID, "textures/entity/curvilinear_plough_entity.png"), new CurvilinearPloughModel(data.bakeLayer(CurvilinearPloughModel.LAYER_LOCATION))));
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(StonePloughModel.LAYER_LOCATION, StonePloughModel::createBodyLayer);
        event.registerLayerDefinition(IronPloughModel.LAYER_LOCATION, IronPloughModel::createBodyLayer);
        event.registerLayerDefinition(CurvilinearPloughModel.LAYER_LOCATION, CurvilinearPloughModel::createBodyLayer);
    }
}
