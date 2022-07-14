package com.daylight.civilization_fire.common.content.register.event;

import com.daylight.civilization_fire.common.content.block.cooking.IronPotBlock;
import com.daylight.civilization_fire.common.content.register.CivilizationFireBlockEntities;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CivilizationFireBlockEntityRendererRegister {
    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(CivilizationFireBlockEntities.IRON_POT_BLOCK_ENTITY.get(),IronPotBlock.IronPotBER::new);
    }
}
