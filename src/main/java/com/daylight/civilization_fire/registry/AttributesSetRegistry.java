package com.daylight.civilization_fire.registry;

import com.daylight.civilization_fire.entity.agriculture.PloughEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttributesSetRegistry {
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(EntityTypeRegistry.CURVILINEAR_PLOUGH_ENTITY.get(), PloughEntity.prepareAttributes().build());
        event.put(EntityTypeRegistry.IRON_PLOUGH_ENTITY.get(), PloughEntity.prepareAttributes().build());
        event.put(EntityTypeRegistry.STONE_PLOUGH_ENTITY.get(), PloughEntity.prepareAttributes().build());
    }
}
