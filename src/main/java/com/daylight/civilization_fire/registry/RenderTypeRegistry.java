package com.daylight.civilization_fire.registry;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

//处理一下渲染
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderTypeRegistry {
    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.RICE_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.GLUTINOUS_RICE_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.YELLOW_RICE_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SORGHUM_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.CORN_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SWEET_POTATO_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.TARO_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.BROAD_BEAN_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.YOUNG_SOYBEAN_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.CAROB_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.LENTIL_HORN_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.WHITE_BEAN_HORN_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.PEANUT.plantBlockRegistry.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SHALLOT_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.GREEN_CHINESE_ONION_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.EGGPLANT_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.CELERY_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.CUCUMBER_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.WAX_GOURD_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.BALSAM_PEAR_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.TOWEL_GOURD_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SOYBEAN_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.GINGER_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.TERNIP_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.WATER_RADISH_PLANT.plantBlockRegistry.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.TOMATOES_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.ONION_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.HOT_PEPPER_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.PEPPER_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.FENNEL_PLANT.plantBlockRegistry.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.BIG_CHINESE_CABBAGE_PLANT.plantBlockRegistry.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.CINNAMON_TREE_LEAF.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.FRAGRANT_TREE_LEAF.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SICHUAN_PEPPER_TREE_LEAF.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SICHUAN_PEPPER_TREE_SAPLING.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.CINNAMON_TREE_SAPLING.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.FRAGRANT_TREE_SAPLING.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.CASSEROLE_BLOCK.get(), RenderType.translucent());
        });
    }
}
