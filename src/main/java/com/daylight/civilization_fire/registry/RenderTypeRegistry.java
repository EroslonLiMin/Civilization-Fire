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
        });
    }
}
