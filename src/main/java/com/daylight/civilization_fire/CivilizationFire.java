package com.daylight.civilization_fire;

import com.daylight.civilization_fire.registry.BlockEntityRegistry;
import com.daylight.civilization_fire.registry.BlockRegistry;
import com.daylight.civilization_fire.registry.ItemRegistry;
import com.daylight.civilization_fire.util.Utils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Utils.MOD_ID)
public class CivilizationFire {
    public CivilizationFire() {
        //加载植物
        BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockEntityRegistry.BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
