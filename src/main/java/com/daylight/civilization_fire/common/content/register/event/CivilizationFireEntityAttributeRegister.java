package com.daylight.civilization_fire.common.content.register.event;

import com.daylight.civilization_fire.common.content.entity.agriculture.PloughEntity;
import com.daylight.civilization_fire.common.content.register.CivilizationEntityTypes;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CivilizationFireEntityAttributeRegister {
    @SubscribeEvent
    public static void onEntityAttributeCreate(final EntityAttributeCreationEvent event) {
        event.put(CivilizationEntityTypes.CURVILINEAR_PLOUGH_ENTITY.get(), PloughEntity.prepareAttributes().build());
        event.put(CivilizationEntityTypes.IRON_PLOUGH_ENTITY.get(), PloughEntity.prepareAttributes().build());
        event.put(CivilizationEntityTypes.STONE_PLOUGH_ENTITY.get(), PloughEntity.prepareAttributes().build());
    }
}
