package com.daylight.civilization_fire.common.content.register.event;

import com.daylight.civilization_fire.client.screen.BaseContainerScreen;
import com.daylight.civilization_fire.client.screen.cooking.IronPotScreen;
import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.register.CivilizationMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CivilizationFireScreenRegister {
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(CivilizationMenuTypes.IRON_POT_MENU.get(), IronPotScreen::new);
        });
    }
}
