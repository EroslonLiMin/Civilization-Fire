package com.daylight.civilization_fire.common.util;

import java.util.Optional;

import javax.annotation.Nonnull;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.block.agriculture.PlantBlock;
import com.daylight.civilization_fire.common.content.block.agriculture.PlantLoad;
import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem.PlantFruitItem;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

/**
 * Utility for civilization-fire
 * @author Heckerpowered
 */
public final class CivilizationFireUtil {
    private CivilizationFireUtil() {
    }

    /**
     * Map PlantFruitItem to PlantBlock
     * @param item Plant fruit.
     * @return PlantBlock, may be empty.
     * @author Heckerpowered
     */
    @Nonnull
    public static final Optional<PlantBlock> asPlantBlock(@Nonnull final PlantFruitItem item) {
        final var itemResourceKey = ForgeRegistries.ITEMS.getResourceKey(item);
        if (itemResourceKey.isPresent()) {
            CivilizationFire.LOGGER.error("Cannot find item's resource key: {}", item);
            return Optional.empty();
        }

        final var blockResourceKey = PlantLoad.PLANT_BLOCK_MAP.get(itemResourceKey.get());
        if (blockResourceKey == null) {
            CivilizationFire.LOGGER.error("Cannot find block's resource key: {}", itemResourceKey);
            return Optional.empty();
        }

        final var holder = ForgeRegistries.BLOCKS.getHolder(blockResourceKey);
        if (holder.isPresent()) {
            CivilizationFire.LOGGER.error("Cannot find block's holder: {}", holder);
            return Optional.empty();
        }

        return Optional.of((PlantBlock) holder.get().value());
    }

    /**
     * Map PlantBlock to PlantFruitItem
     * @param block Plant fruit.
     * @return PlantBlock, may be empty.
     * @author Heckerpowered
     */
    @Nonnull
    public static final @NotNull Optional<Holder<Item>> asPlantItem(@Nonnull final PlantBlock block) {
        final var blockResourceKey = ForgeRegistries.BLOCKS.getResourceKey(block);
        if (blockResourceKey.isPresent()) {
            CivilizationFire.LOGGER.error("Cannot find block's resource key: {}", block);
            return Optional.empty();
        }

        final var itemResourceKey = PlantLoad.BLOCK_PLANT_MAP.get(blockResourceKey.get());
        if (itemResourceKey == null) {
            CivilizationFire.LOGGER.error("Cannot find block's resource key: {}", blockResourceKey);
            return Optional.empty();
        }


        return ForgeRegistries.ITEMS.getHolder(itemResourceKey);
    }

    /**
     * Get plant grow time.
     * @param item Plant fruit.
     * @return grow time(ticks), may be empty.
     * @author Heckerpowered
     */
    @Nonnull
    public static final Optional<Integer> getPlantGrowTime(@Nonnull final PlantFruitItem item) {
        final var plantBlock = asPlantBlock(item);
        if (plantBlock.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(plantBlock.get().matureTick);
    }
}