package com.daylight.civilization_fire.common.content.register.event;

import com.daylight.civilization_fire.common.content.register.CivilizationFireBlocks;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

//处理一下渲染
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CivilizationFireRenderTypeRegister {
        @SubscribeEvent
        public static void onRenderTypeSetup(final FMLClientSetupEvent event) {
                event.enqueueWork(() -> {
                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.RICE_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.GLUTINOUS_RICE_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.YELLOW_RICE_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.SORGHUM_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.CORN_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.SWEET_POTATO_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.TARO_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.BROAD_BEAN_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.YOUNG_SOYBEAN_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.CAROB_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.LENTIL_HORN_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.WHITE_BEAN_HORN_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.PEANUT.plantBlockRegistry.get(),
                                RenderType.translucent());

                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.SHALLOT_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.GREEN_CHINESE_ONION_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.EGGPLANT_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.CELERY_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.CUCUMBER_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.WAX_GOURD_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.BALSAM_PEAR_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.TOWEL_GOURD_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.SOYBEAN_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.GINGER_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.TERNIP_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.WATER_RADISH_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());

                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.TOMATOES_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.ONION_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.HOT_PEPPER_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.PEPPER_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.FENNEL_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(
                                CivilizationFireBlocks.BIG_CHINESE_CABBAGE_PLANT.plantBlockRegistry.get(),
                                RenderType.translucent());

                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.CINNAMON_TREE_LEAF.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.FRAGRANT_TREE_LEAF.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.SICHUAN_PEPPER_TREE_LEAF.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.SICHUAN_PEPPER_TREE_SAPLING.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.CINNAMON_TREE_SAPLING.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.FRAGRANT_TREE_SAPLING.get(),
                                RenderType.translucent());

                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.IRON_POT_BLOCK.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.COOKING_BENCH_BLOCK.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.PLATE.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.BOWL.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.WELL_BLOCK.get(),
                                RenderType.translucent());
                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.JUICER_BLOCK.get(),
                                RenderType.translucent());

                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.CHINESE_CABBAGE_SOUP.dishesVarietyBlockRegistry.get(),
                                RenderType.translucent());

                        ItemBlockRenderTypes.setRenderLayer(CivilizationFireBlocks.SHENNONG_FIRE_PORTAL.get(),
                                RenderType.translucent());
                });
        }
}
