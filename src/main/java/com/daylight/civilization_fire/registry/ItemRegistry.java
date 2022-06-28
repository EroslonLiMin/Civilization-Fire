package com.daylight.civilization_fire.registry;

import com.daylight.civilization_fire.init.ModGroup;
import com.daylight.civilization_fire.item.agriculture.tool.ModHoeingToolItem;
import com.daylight.civilization_fire.item.agriculture.tool.WateringToolItem;
import com.daylight.civilization_fire.util.Utils;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

//物品注册
public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);
    //土壤
    public static final RegistryObject<Item> CLAY_BLOCK_BLOCK = ITEMS.register("clay_block", () -> new BlockItem(BlockRegistry.CLAY_BLOCK.get(), new Item.Properties().tab(ModGroup.AGRICULTURE_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> LOAM_BLOCK_BLOCK = ITEMS.register("loam_block", () -> new BlockItem(BlockRegistry.LOAM_BLOCK.get(), new Item.Properties().tab(ModGroup.AGRICULTURE_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> SAND_BLOCK_BLOCK = ITEMS.register("sand_block", () -> new BlockItem(BlockRegistry.SAND_BLOCK.get(), new Item.Properties().tab(ModGroup.AGRICULTURE_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> PADDY_SOIL_BLOCK = ITEMS.register("paddy_soil_block", () -> new BlockItem(BlockRegistry.PADDY_SOIL_BLOCK.get(), new Item.Properties().tab(ModGroup.AGRICULTURE_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> MYCELIAL_SOIL_BLOCK = ITEMS.register("mycelial_soil_block", () -> new BlockItem(BlockRegistry.MYCELIAL_SOIL_BLOCK.get(), new Item.Properties().tab(ModGroup.AGRICULTURE_CREATIVE_MODE_TAB)));

    //挖掘工具
    public static final RegistryObject<Item> WOOD_HANDLE_PLOUGH = ITEMS.register("wood_handle_plough", () -> new ModHoeingToolItem(new Item.Properties().tab(ModGroup.AGRICULTURE_CREATIVE_MODE_TAB).durability(500),0));
    public static final RegistryObject<Item> STONE_HANDLE_PLOUGH = ITEMS.register("stone_handle_plough", () -> new ModHoeingToolItem(new Item.Properties().tab(ModGroup.AGRICULTURE_CREATIVE_MODE_TAB).durability(1000),1));
    public static final RegistryObject<Item> IRON_HANDLE_PLOUGH = ITEMS.register("iron_handle_plough", () -> new ModHoeingToolItem(new Item.Properties().tab(ModGroup.AGRICULTURE_CREATIVE_MODE_TAB).durability(1500),2));

    public static final RegistryObject<Item> WOOD_BUCKET = ITEMS.register("wood_bucket", () -> new WateringToolItem(new Item.Properties().tab(ModGroup.AGRICULTURE_CREATIVE_MODE_TAB).durability(200),1200));
}
