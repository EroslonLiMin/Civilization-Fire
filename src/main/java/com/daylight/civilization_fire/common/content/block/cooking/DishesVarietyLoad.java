package com.daylight.civilization_fire.common.content.block.cooking;

import com.daylight.civilization_fire.common.content.item.cooking.DishesVarietyItem;
import com.daylight.civilization_fire.common.content.recipe.CookingDishesType;
import com.daylight.civilization_fire.common.content.register.CivilizationFireBlocks;
import com.daylight.civilization_fire.common.content.register.CivilizationFireItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class DishesVarietyLoad {
    /**
     * Map of plant block to dishesVariety item.
     * @author Eroslon_DayLight
     */
    public static final Map<ResourceKey<Item>, ResourceKey<Block>> DISHES_VARIETY_BLOCK_MAP = new HashMap<>();
    /**
     * Reversed "DISHES_VARIETY_BLOCK_MAP"
     * @author Eroslon_DayLight
     */
    public static final Map<ResourceKey<Block>, ResourceKey<Item>> BLOCK_PLANT_MAP = new HashMap<>();

    public String name;//菜品名称
    public final RegistryObject<Block> dishesVarietyBlockRegistry;
    public final RegistryObject<Item> dishesVarietyItemRegistry;

    public DishesVarietyLoad(String name, CookingDishesType cookingDishesType, int nutrition, int saturationMod) {
        this.name = name;
        this.dishesVarietyBlockRegistry = CivilizationFireBlocks.BLOCKS.register(name, () -> new DishesVarietyBlock(cookingDishesType));
        this.dishesVarietyItemRegistry = CivilizationFireItems.ITEMS.register(name, () -> new DishesVarietyItem(dishesVarietyBlockRegistry.get(), nutrition, saturationMod, cookingDishesType));
        DISHES_VARIETY_BLOCK_MAP.put(dishesVarietyItemRegistry.getKey(), dishesVarietyBlockRegistry.getKey());
        BLOCK_PLANT_MAP.put(dishesVarietyBlockRegistry.getKey(), dishesVarietyItemRegistry.getKey());
    }
}
