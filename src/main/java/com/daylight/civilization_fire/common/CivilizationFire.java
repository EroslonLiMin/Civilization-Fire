package com.daylight.civilization_fire.common;


import javax.annotation.Nonnull;

import com.daylight.civilization_fire.common.content.register.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CivilizationFire.MODID)
public class CivilizationFire {

    /**
     * Define mod id in a common place for everything to reference
     */
    public static final String MODID = "civilization_fire";

    /**
     * Directly reference a slf4j logger
     * @author Heckerpowered
     */
    public static final Logger LOGGER = LogUtils.getLogger();

    public CivilizationFire() {
        initialize_content(FMLJavaModLoadingContext.get().getModEventBus());

    }

    private static final void initialize_content(@Nonnull final IEventBus eventBus) {
        CivilizationFireBlocks.BLOCKS.register(eventBus);
        CivilizationFireItems.ITEMS.register(eventBus);
        CivilizationFireBlockEntities.BLOCK_ENTITIES.register(eventBus);
        CivilizationEntityTypes.ENTITY_TYPES.register(eventBus);
        CivilizationFireMenuTypes.MENUS.register(eventBus);
        CivilizationFireSounds.DEFERRED_REGISTER.register(eventBus);
        new CivilizationCookingRecipes();
    }

    @Nonnull
    public static final ResourceLocation resource(@Nonnull final String path) {
        return new ResourceLocation(MODID, path);
    }
}
