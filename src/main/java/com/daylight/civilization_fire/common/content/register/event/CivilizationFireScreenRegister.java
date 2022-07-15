package com.daylight.civilization_fire.common.content.register.event;

import com.daylight.civilization_fire.client.screen.cooking.CasseroleScreen;
import com.daylight.civilization_fire.client.screen.cooking.FoodSteamerScreen;
import com.daylight.civilization_fire.client.screen.cooking.IronPotScreen;
import com.daylight.civilization_fire.common.content.register.CivilizationFireMenuTypes;
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
            MenuScreens.register(CivilizationFireMenuTypes.IRON_POT_MENU.get(), IronPotScreen::new);
            MenuScreens.register(CivilizationFireMenuTypes.FOOD_STEAMER_MENU.get(), FoodSteamerScreen::new);
            MenuScreens.register(CivilizationFireMenuTypes.CASSEROLE_MENU.get(), CasseroleScreen::new);
        });
    }
}
