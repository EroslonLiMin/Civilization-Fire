package com.daylight.civilization_fire.common.content.registry.event;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.registry.CivilizationFireItems;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

//物品Properties
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CivilizationFireRegister {
    @SubscribeEvent
    public static void propertyOverrideRegistry(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> ItemProperties.register(CivilizationFireItems.WOOD_BUCKET.get(),
                CivilizationFire.resource("with_water"), (itemStack, level, livingEntity, i) -> {
                    LazyOptional<IEnergyStorage> lazyOptional = itemStack.getCapability(CapabilityEnergy.ENERGY);
                    return lazyOptional.map(e -> (float) e.getEnergyStored() / e.getMaxEnergyStored()).orElse(0.0F);
                }));
    }
}