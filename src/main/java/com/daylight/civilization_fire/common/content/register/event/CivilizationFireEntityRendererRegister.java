package com.daylight.civilization_fire.common.content.register.event;

import com.daylight.civilization_fire.client.model.CurvilinearPloughModel;
import com.daylight.civilization_fire.client.model.IronPloughModel;
import com.daylight.civilization_fire.client.model.StonePloughModel;
import com.daylight.civilization_fire.client.model.armor.*;
import com.daylight.civilization_fire.client.renderer.*;
import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.register.CivilizationEntityTypes;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CivilizationFireEntityRendererRegister {
        @SubscribeEvent
        public static void onRegisterRenderer(final RegisterRenderers event) {
                event.registerEntityRenderer(CivilizationEntityTypes.STONE_PLOUGH_ENTITY.get(),
                                (data) -> new PloughEntityRenderer<>(data,
                                                CivilizationFire.resource("textures/entity/stone_plough_entity.png"),
                                                new StonePloughModel(data.bakeLayer(StonePloughModel.LAYER_LOCATION))));
                event.registerEntityRenderer(CivilizationEntityTypes.IRON_PLOUGH_ENTITY.get(),
                                (data) -> new PloughEntityRenderer<>(data,
                                                CivilizationFire.resource("textures/entity/iron_plough_entity.png"),
                                                new IronPloughModel(data.bakeLayer(IronPloughModel.LAYER_LOCATION))));
                event.registerEntityRenderer(CivilizationEntityTypes.CURVILINEAR_PLOUGH_ENTITY.get(),
                                (data) -> new PloughEntityRenderer<>(data,
                                                CivilizationFire.resource(
                                                                "textures/entity/curvilinear_plough_entity.png"),
                                                new CurvilinearPloughModel(data
                                                                .bakeLayer(CurvilinearPloughModel.LAYER_LOCATION))));
                event.registerEntityRenderer(CivilizationEntityTypes.GUARDIAN_BOT.get(), GuardianBotRenderer::new);
                event.registerEntityRenderer(CivilizationEntityTypes.MINING_BOT.get(), MiningBotRenderer::new);
                event.registerEntityRenderer(CivilizationEntityTypes.KEEL_WATER_WHEEL_ENTITY.get(), KeelWaterwheelRenderer::new);
                event.registerEntityRenderer(CivilizationEntityTypes.BUCKETS_WATER_WHEEL_ENTITY.get(), BucketsWaterwheelRenderer::new);
                event.registerEntityRenderer(CivilizationEntityTypes.FARMING_BOT_ENTITY.get(), FarmingBotRenderer::new);
                event.registerEntityRenderer(CivilizationEntityTypes.CIVILIZATION_FIRE_PROJECTILE_ENTITY.get(), ThrownItemRenderer::new);

        }

        @SubscribeEvent
        public static void onRegisterLayers(final RegisterLayerDefinitions event) {
                event.registerLayerDefinition(StonePloughModel.LAYER_LOCATION, StonePloughModel::createBodyLayer);
                event.registerLayerDefinition(IronPloughModel.LAYER_LOCATION, IronPloughModel::createBodyLayer);
                event.registerLayerDefinition(CurvilinearPloughModel.LAYER_LOCATION,
                                CurvilinearPloughModel::createBodyLayer);
                event.registerLayerDefinition(RookieHeadArmorModel.LAYER_LOCATION,
                        RookieHeadArmorModel::createBodyLayer);
                event.registerLayerDefinition(WaxGourdArmorModel.LAYER_LOCATION,
                        WaxGourdArmorModel::createBodyLayer);
                event.registerLayerDefinition(SuperTaroArmorModel.LAYER_LOCATION,
                        SuperTaroArmorModel::createBodyLayer);
                event.registerLayerDefinition(BalsamPearArmorModel.LAYER_LOCATION,
                        BalsamPearArmorModel::createBodyLayer);
                event.registerLayerDefinition(CucumberRocketArmorModel.LAYER_LOCATION,
                        CucumberRocketArmorModel::createBodyLayer);
                event.registerLayerDefinition(MushroomArmorModel.LAYER_LOCATION,
                        MushroomArmorModel::createBodyLayer);
        }
}
