package com.daylight.civilization_fire.common.content.register;

import com.daylight.civilization_fire.common.content.tab.ModCreativeModeTab;

import net.minecraft.world.item.ItemStack;

public final class CivilizationFireTab {
        private CivilizationFireTab() {
        }

        public static final ModCreativeModeTab AGRICULTURE_CREATIVE_MODE_TAB = new ModCreativeModeTab(
                        "agriculture_creative_mode_tab",
                        () -> new ItemStack(CivilizationFireItems.WOOD_HANDLE_PLOUGH.get()));//农业部门
        public static final ModCreativeModeTab GRAIN_CROPS_CREATIVE_MODE_TAB = new ModCreativeModeTab(
                        "grain_crops_creative_mode_tab",
                        () -> new ItemStack(CivilizationFireBlocks.RICE_PLANT.plantItemRegistry.get()));//粮食作物
        public static final ModCreativeModeTab SPICE_CROPS_CREATIVE_MODE_TAB = new ModCreativeModeTab(
                        "spice_crops_creative_mode_tab",
                        () -> new ItemStack(CivilizationFireBlocks.HOT_PEPPER_PLANT.plantFruitRegistry.get()));//香料作物
        public static final ModCreativeModeTab VEGETABLE_CROPS_CREATIVE_MODE_TAB = new ModCreativeModeTab(
                        "vegetable_crops_creative_mode_tab",
                        () -> new ItemStack(CivilizationFireBlocks.TOMATOES_PLANT.plantFruitRegistry.get()));//蔬菜作物
        public static final ModCreativeModeTab COOKING_CREATIVE_MODE_TAB = new ModCreativeModeTab(
                        "cooking_creative_mode_tab",
                        () -> new ItemStack(CivilizationFireBlocks.COOKING_BENCH_BLOCK.get()));//烹饪

}
