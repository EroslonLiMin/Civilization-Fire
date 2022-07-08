package com.daylight.civilization_fire.registry;


import com.daylight.civilization_fire.block.cooking.CasseroleBlock;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BERRegistry {
    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(BlockEntityRegistry.CASSEROLE_BLOCK_ENTITY.get(), CasseroleBlock.CasseroleBlockBER::new);
    }
}
