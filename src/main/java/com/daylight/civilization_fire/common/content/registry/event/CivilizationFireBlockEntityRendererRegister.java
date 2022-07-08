package com.daylight.civilization_fire.common.content.registry.event;

import com.daylight.civilization_fire.common.content.block.cooking.CasseroleBlock;
import com.daylight.civilization_fire.common.content.registry.CivilizationFireBlockEntities;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CivilizationFireBlockEntityRendererRegister {
    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(CivilizationFireBlockEntities.CASSEROLE_BLOCK_ENTITY.get(),
                CasseroleBlock.CasseroleBlockBER::new);
    }
}
