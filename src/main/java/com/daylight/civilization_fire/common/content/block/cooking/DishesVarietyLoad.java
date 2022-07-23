package com.daylight.civilization_fire.common.content.block.cooking;

import com.daylight.civilization_fire.common.content.item.cooking.DishesVarietyItem;
import com.daylight.civilization_fire.common.content.recipe.CookingDishesType;
import com.daylight.civilization_fire.common.content.register.CivilizationFireBlocks;
import com.daylight.civilization_fire.common.content.register.CivilizationFireItems;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;


public class DishesVarietyLoad {
    public String name;//菜品名称
    public final RegistryObject<Block> dishesVarietyBlockRegistry;
    public final RegistryObject<Item> dishesVarietyItemRegistry;

    public DishesVarietyLoad(String name, CookingDishesType cookingDishesType, int nutrition, int saturationMod) {
        this.name = name;
        this.dishesVarietyBlockRegistry = CivilizationFireBlocks.BLOCKS.register(name, () -> new DishesVarietyBlock(cookingDishesType));
        this.dishesVarietyItemRegistry = CivilizationFireItems.ITEMS.register(name, () -> new DishesVarietyItem(dishesVarietyBlockRegistry.get(), nutrition, saturationMod, cookingDishesType));
    }
}
