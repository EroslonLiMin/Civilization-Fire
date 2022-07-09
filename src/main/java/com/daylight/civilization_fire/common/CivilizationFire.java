package com.daylight.civilization_fire.common;

import java.util.Random;

import javax.annotation.Nonnull;

import com.daylight.civilization_fire.common.content.register.CivilizationEntityTypes;
import com.daylight.civilization_fire.common.content.register.CivilizationFireBlockEntities;
import com.daylight.civilization_fire.common.content.register.CivilizationFireBlocks;
import com.daylight.civilization_fire.common.content.register.CivilizationFireItems;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CivilizationFire.MODID)
public class CivilizationFire {

    public static final String MODID = "civilization_fire";
    public static final Random RANDOM = new Random(114514);

    public CivilizationFire() {
        //加载植物
        initialize_content(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private static final void initialize_content(@Nonnull final IEventBus eventBus) {
        CivilizationFireBlocks.BLOCKS.register(eventBus);
        CivilizationFireItems.ITEMS.register(eventBus);
        CivilizationFireBlockEntities.BLOCK_ENTITIES.register(eventBus);
        CivilizationEntityTypes.ENTITY_TYPES.register(eventBus);
    }

    @Nonnull
    public static final ResourceLocation resource(@Nonnull final String path) {
        return new ResourceLocation(MODID, path);
    }
}
