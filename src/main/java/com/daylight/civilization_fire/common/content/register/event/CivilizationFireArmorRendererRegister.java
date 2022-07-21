package com.daylight.civilization_fire.common.content.register.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CivilizationFireArmorRendererRegister {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
    }
}
