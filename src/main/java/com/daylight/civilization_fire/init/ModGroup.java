package com.daylight.civilization_fire.init;

import com.daylight.civilization_fire.creativetab.ModCreativeModeTab;
import com.daylight.civilization_fire.registry.BlockRegistry;
import com.daylight.civilization_fire.registry.ItemRegistry;
import net.minecraft.world.item.ItemStack;

public class ModGroup {
    public static final ModCreativeModeTab AGRICULTURE_CREATIVE_MODE_TAB = new ModCreativeModeTab("agriculture_creative_mode_tab", () -> new ItemStack(ItemRegistry.CLAY_BLOCK.get()));//农业部门
    public static final ModCreativeModeTab GRAIN_CROPS_CREATIVE_MODE_TAB = new ModCreativeModeTab("grain_crops_creative_mode_tab", () -> new ItemStack(BlockRegistry.RICE_PLANT.plantItemRegistry.get()));//粮食作物
    public static final ModCreativeModeTab SPICE_CROPS_CREATIVE_MODE_TAB = new ModCreativeModeTab("spice_crops_creative_mode_tab", () -> new ItemStack(BlockRegistry.HOT_PEPPER_PLANT.plantFruitRegistry.get()));//香料作物
    public static final ModCreativeModeTab VEGETABLE_CROPS_CREATIVE_MODE_TAB = new ModCreativeModeTab("vegetable_crops_creative_mode_tab", () -> new ItemStack(BlockRegistry.TOMATOES_PLANT.plantFruitRegistry.get()));//蔬菜作物

}
