package com.daylight.civilization_fire.common.content.register;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.entity.agriculture.PloughEntity;
import com.daylight.civilization_fire.common.content.item.agriculture.EntityItem;
import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
import com.daylight.civilization_fire.common.content.item.agriculture.SeedBagItem;
import com.daylight.civilization_fire.common.content.item.agriculture.tool.ModHoeingToolItem;
import com.daylight.civilization_fire.common.content.item.agriculture.tool.WateringToolItem;
import com.daylight.civilization_fire.common.content.item.cooking.CondimentItem;

import com.daylight.civilization_fire.common.content.item.cooking.SpatulaItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//物品注册
public class CivilizationFireItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            CivilizationFire.MODID);
    //土壤
    public static final RegistryObject<Item> CLAY_BLOCK = ITEMS.register("clay_block",
            () -> new BlockItem(CivilizationFireBlocks.CLAY_BLOCK.get(),
                    new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> LOAM_BLOCK = ITEMS.register("loam_block",
            () -> new BlockItem(CivilizationFireBlocks.LOAM_BLOCK.get(),
                    new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> SAND_BLOCK = ITEMS.register("sand_block",
            () -> new BlockItem(CivilizationFireBlocks.SAND_BLOCK.get(),
                    new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> PADDY_SOIL = ITEMS.register("paddy_soil_block",
            () -> new BlockItem(CivilizationFireBlocks.PADDY_SOIL_BLOCK.get(),
                    new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> MYCELIAL_SOIL = ITEMS.register("mycelial_soil_block",
            () -> new BlockItem(CivilizationFireBlocks.MYCELIAL_SOIL_BLOCK.get(),
                    new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> WELL_BLOCK = ITEMS.register("well_block",
            () -> new BlockItem(CivilizationFireBlocks.WELL_BLOCK.get(),
                    new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB)));

    //烹饪
    public static final RegistryObject<Item> IRON_POT_BLOCK = ITEMS.register("iron_pot_block",
            () -> new BlockItem(CivilizationFireBlocks.IRON_POT_BLOCK.get(),
                    new Item.Properties().tab(CivilizationFireTab.COOKING_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> FOOD_STEAMER_BLOCK = ITEMS.register("food_steamer_block",
            () -> new BlockItem(CivilizationFireBlocks.FOOD_STEAMER_BLOCK.get(),
                    new Item.Properties().tab(CivilizationFireTab.COOKING_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CASSEROLE_BLOCK = ITEMS.register("casserole_block",
            () -> new BlockItem(CivilizationFireBlocks.CASSEROLE_BLOCK.get(),
                    new Item.Properties().tab(CivilizationFireTab.COOKING_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> FOOD_STEAMER_HAT_BLOCK = ITEMS.register("food_steamer_hat_block",
            () -> new BlockItem(CivilizationFireBlocks.FOOD_STEAMER_HAT_BLOCK.get(),
                    new Item.Properties().tab(CivilizationFireTab.COOKING_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> SALT_ITEM = ITEMS.register("salt", () -> new CondimentItem(20));
    public static final RegistryObject<Item> OIL_ITEM = ITEMS.register("oil", () -> new CondimentItem(15));
    public static final RegistryObject<Item> SAUCE_ITEM = ITEMS.register("sauce", () -> new CondimentItem(10));
    public static final RegistryObject<Item> VINEGAR_ITEM = ITEMS.register("vinegar", () -> new CondimentItem(25));
    public static final RegistryObject<Item> COOKING_WINE_ITEM = ITEMS.register("cooking_wine",
            () -> new CondimentItem(22));
    public static final RegistryObject<Item> COOKING_BENCH_BLOCK = ITEMS.register("cooking_bench_block",
            () -> new BlockItem(CivilizationFireBlocks.COOKING_BENCH_BLOCK.get(),
                    new Item.Properties().tab(CivilizationFireTab.COOKING_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> BOWL = ITEMS.register("bowl",
            () -> new BlockItem(CivilizationFireBlocks.BOWL.get(),
                    new Item.Properties().tab(CivilizationFireTab.COOKING_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> PLATE = ITEMS.register("plate",
            () -> new BlockItem(CivilizationFireBlocks.PLATE.get(),
                    new Item.Properties().tab(CivilizationFireTab.COOKING_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> IRON_SPATULA = ITEMS.register("iron_spatula", () -> new SpatulaItem(5000));
    public static final RegistryObject<Item> WOODEN_SPATULA = ITEMS.register("wooden_spatula", () -> new SpatulaItem(20000));
    //挖掘工具
    public static final RegistryObject<Item> WOOD_HANDLE_PLOUGH = ITEMS.register("wood_handle_plough",
            () -> new ModHoeingToolItem(
                    new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB)
                            .durability(500),
                    0));
    public static final RegistryObject<Item> STONE_HANDLE_PLOUGH = ITEMS.register("stone_handle_plough",
            () -> new ModHoeingToolItem(
                    new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB)
                            .durability(1000),
                    1));
    public static final RegistryObject<Item> IRON_HANDLE_PLOUGH = ITEMS.register("iron_handle_plough",
            () -> new ModHoeingToolItem(
                    new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB)
                            .durability(1500),
                    2));

    public static final RegistryObject<Item> WOOD_BUCKET = ITEMS.register("wood_bucket", () -> new WateringToolItem(
            new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB).durability(200),
            1200));
    public static final RegistryObject<Item> DIPPER = ITEMS.register("dipper", () -> new WateringToolItem(
            new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB).durability(50),
            500));
    public static final RegistryObject<Item> SEED_BAG_ITEM = ITEMS.register("seed_bag", SeedBagItem::new);


    //植物
    public static final RegistryObject<Item> CINNAMON_BARK = CivilizationFireItems.ITEMS.register("cinnamon_bark_fruit",
            () -> new PlantItem.PlantFruitItem(CivilizationFireTab.SPICE_CROPS_CREATIVE_MODE_TAB, false));
    public static final RegistryObject<Item> FRAGRANT = CivilizationFireItems.ITEMS.register("fragrant_fruit",
            () -> new PlantItem.PlantFruitItem(CivilizationFireTab.SPICE_CROPS_CREATIVE_MODE_TAB, false));
    public static final RegistryObject<Item> SICHUAN_PEPPER = CivilizationFireItems.ITEMS.register("sichuan_pepper_fruit",
            () -> new PlantItem.PlantFruitItem(CivilizationFireTab.SPICE_CROPS_CREATIVE_MODE_TAB, false));
    public static final RegistryObject<Item> CINNAMON_TREE_WOOD = ITEMS.register("cinnamon_tree_wood",
            () -> new BlockItem(CivilizationFireBlocks.CINNAMON_TREE_WOOD.get(),
                    new Item.Properties().tab(CivilizationFireTab.SPICE_CROPS_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CINNAMON_TREE_LEAF = ITEMS.register("cinnamon_tree_leaf",
            () -> new BlockItem(CivilizationFireBlocks.CINNAMON_TREE_LEAF.get(),
                    new Item.Properties().tab(CivilizationFireTab.SPICE_CROPS_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CINNAMON_TREE_SAPLING = ITEMS.register("cinnamon_tree_sapling",
            () -> new BlockItem(CivilizationFireBlocks.CINNAMON_TREE_SAPLING.get(),
                    new Item.Properties().tab(CivilizationFireTab.SPICE_CROPS_CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> FRAGRANT_TREE_WOOD = ITEMS.register("fragrant_tree_wood",
            () -> new BlockItem(CivilizationFireBlocks.FRAGRANT_TREE_WOOD.get(),
                    new Item.Properties().tab(CivilizationFireTab.SPICE_CROPS_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> FRAGRANT_TREE_LEAF = ITEMS.register("fragrant_tree_leaf",
            () -> new BlockItem(CivilizationFireBlocks.FRAGRANT_TREE_LEAF.get(),
                    new Item.Properties().tab(CivilizationFireTab.SPICE_CROPS_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> FRAGRANT_TREE_SAPLING = ITEMS.register("fragrant_tree_sapling",
            () -> new BlockItem(CivilizationFireBlocks.FRAGRANT_TREE_SAPLING.get(),
                    new Item.Properties().tab(CivilizationFireTab.SPICE_CROPS_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> SICHUAN_PEPPER_TREE_WOOD = ITEMS.register("sichuan_pepper_tree_wood",
            () -> new BlockItem(CivilizationFireBlocks.SICHUAN_PEPPER_TREE_WOOD.get(),
                    new Item.Properties().tab(CivilizationFireTab.SPICE_CROPS_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> SICHUAN_PEPPER_TREE_LEAF = ITEMS.register("sichuan_pepper_tree_leaf",
            () -> new BlockItem(CivilizationFireBlocks.SICHUAN_PEPPER_TREE_LEAF.get(),
                    new Item.Properties().tab(CivilizationFireTab.SPICE_CROPS_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> SICHUAN_PEPPER_TREE_SAPLING = ITEMS.register(
            "sichuan_pepper_tree_sapling",
            () -> new BlockItem(CivilizationFireBlocks.SICHUAN_PEPPER_TREE_SAPLING.get(),
                    new Item.Properties().tab(CivilizationFireTab.SPICE_CROPS_CREATIVE_MODE_TAB)));

    //召唤实体物品
    public static final RegistryObject<Item> STONE_PLOUGH_ENTITY_ITEM = ITEMS.register("stone_plough_entity_item",
            () -> new EntityItem(10000, (level, itemStack) -> {
                PloughEntity.StonePloughEntity stonePloughEntity = new PloughEntity.StonePloughEntity(
                        CivilizationEntityTypes.STONE_PLOUGH_ENTITY.get(), level);
                stonePloughEntity.entityItem = (EntityItem) itemStack.getItem();
                stonePloughEntity.setPloughTimes(10000 - itemStack.getDamageValue());
                return stonePloughEntity;
            }));
    public static final RegistryObject<Item> IRON_PLOUGH_ENTITY_ITEM = ITEMS.register("iron_plough_entity_item",
            () -> new EntityItem(20000, (level, itemStack) -> {
                PloughEntity.IronPloughEntity stonePloughEntity = new PloughEntity.IronPloughEntity(
                        CivilizationEntityTypes.IRON_PLOUGH_ENTITY.get(), level);
                stonePloughEntity.entityItem = (EntityItem) itemStack.getItem();
                stonePloughEntity.setPloughTimes(20000 - itemStack.getDamageValue());
                return stonePloughEntity;
            }));
    public static final RegistryObject<Item> CURVILINEAR_PLOUGH_ENTITY_ITEM = ITEMS
            .register("curvilinear_plough_entity_item", () -> new EntityItem(30000, (level, itemStack) -> {
                PloughEntity.CurvilinearPloughEntity stonePloughEntity = new PloughEntity.CurvilinearPloughEntity(
                        CivilizationEntityTypes.CURVILINEAR_PLOUGH_ENTITY.get(), level);
                stonePloughEntity.entityItem = (EntityItem) itemStack.getItem();
                stonePloughEntity.setPloughTimes(30000 - itemStack.getDamageValue());
                return stonePloughEntity;
            }));

}
