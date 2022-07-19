package com.daylight.civilization_fire.common.content.register.event;

import com.daylight.civilization_fire.common.content.entity.agriculture.BucketsWaterwheelEntity;
import com.daylight.civilization_fire.common.content.entity.agriculture.KeelWaterwheelEntity;
import com.daylight.civilization_fire.common.content.entity.agriculture.PloughEntity;
import com.daylight.civilization_fire.common.content.entity.bot.FarmingBot;
import com.daylight.civilization_fire.common.content.entity.bot.GuardianBot;
import com.daylight.civilization_fire.common.content.entity.bot.MiningBot;
import com.daylight.civilization_fire.common.content.register.CivilizationEntityTypes;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CivilizationFireEntityAttributeRegister {
    @SubscribeEvent
    public static void onEntityAttributeCreate(final EntityAttributeCreationEvent event) {
        event.put(CivilizationEntityTypes.CURVILINEAR_PLOUGH_ENTITY.get(), PloughEntity.CurvilinearPloughEntity.createAttributes().build());
        event.put(CivilizationEntityTypes.IRON_PLOUGH_ENTITY.get(), PloughEntity.IronPloughEntity.createAttributes().build());
        event.put(CivilizationEntityTypes.STONE_PLOUGH_ENTITY.get(), PloughEntity.StonePloughEntity.createAttributes().build());
        event.put(CivilizationEntityTypes.GUARDIAN_BOT.get(), GuardianBot.createAttributes().build());
        event.put(CivilizationEntityTypes.MINING_BOT.get(), MiningBot.createAttributes().build());
        event.put(CivilizationEntityTypes.KEEL_WATER_WHEEL_ENTITY.get(), KeelWaterwheelEntity.prepareAttributes().build());
        event.put(CivilizationEntityTypes.BUCKETS_WATER_WHEEL_ENTITY.get(), BucketsWaterwheelEntity.prepareAttributes().build());
        event.put(CivilizationEntityTypes.FARMING_BOT_ENTITY.get(), FarmingBot.createAttributes().build());
    }
}
