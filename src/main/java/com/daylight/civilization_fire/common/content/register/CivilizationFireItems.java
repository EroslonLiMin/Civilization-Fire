package com.daylight.civilization_fire.common.content.register;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.entity.agriculture.BucketsWaterwheelEntity;
import com.daylight.civilization_fire.common.content.entity.agriculture.KeelWaterwheelEntity;
import com.daylight.civilization_fire.common.content.entity.agriculture.PloughEntity;
import com.daylight.civilization_fire.common.content.entity.bot.FarmingBot;
import com.daylight.civilization_fire.common.content.entity.bot.GuardianBot;
import com.daylight.civilization_fire.common.content.entity.bot.MiningBot;
import com.daylight.civilization_fire.common.content.item.GunpowderPepper;
import com.daylight.civilization_fire.common.content.item.ScallionCrownBladeItem;
import com.daylight.civilization_fire.common.content.item.agriculture.*;
import com.daylight.civilization_fire.common.content.item.agriculture.tool.ModHoeingToolItem;
import com.daylight.civilization_fire.common.content.item.agriculture.tool.WateringToolItem;
import com.daylight.civilization_fire.common.content.item.armor.*;
import com.daylight.civilization_fire.common.content.item.cooking.CondimentItem;

import com.daylight.civilization_fire.common.content.item.cooking.SpatulaItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//物品注册
public class CivilizationFireItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            CivilizationFire.MODID);

    //ADD
    public static final RegistryObject<RookieArmor> ROOKIE_ARMOR_HAT  = ITEMS.register("rookie_armor_hat",
            () -> new RookieArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<GunpowderPepper> GUNPOWDER_PEPPER  = ITEMS.register("gunpowder_pepper",GunpowderPepper::new);
    public static final RegistryObject<WaxGourdArmor> WAX_GOURD_ARMOR_BODY  = ITEMS.register("wax_gourd_armor_body",() -> new WaxGourdArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<SuperTaroArmor> SUPER_TARO_ARMOR_PANTS  = ITEMS.register("super_taro_armor_pants",() -> new SuperTaroArmor(EquipmentSlot.LEGS));
    public static final RegistryObject<BalsamPearArmor> BALSAM_PEAR_ARMOR_HAT  = ITEMS.register("balsam_pear_armor_hat",() -> new BalsamPearArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<CucumberRocketArmor> CUCUMBER_ROCKET_ARMOR_FEET  = ITEMS.register("cucumber_rocket_armor_feet",() -> new CucumberRocketArmor(EquipmentSlot.FEET));
    public static final RegistryObject<MushroomArmor> MUSHROOM_ARMOR_REGISTRY_OBJECT  = ITEMS.register("mushroom_armor_head",() -> new MushroomArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<ScallionCrownBladeItem> SCALLION_CROWN_BLADE_ITEM  = ITEMS.register("scallion_crown_blade_item", ScallionCrownBladeItem::new);


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
    public static final RegistryObject<Item> JUICER_BLOCK = ITEMS.register("juicer_block",
            () -> new BlockItem(CivilizationFireBlocks.JUICER_BLOCK.get(),
                    new Item.Properties().tab(CivilizationFireTab.ADD_MODE_TAB)));
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
    public static final RegistryObject<Item> IRON_SPATULA = ITEMS.register("iron_spatula",
            () -> new SpatulaItem(5000));
    public static final RegistryObject<Item> WOODEN_SPATULA = ITEMS.register("wooden_spatula",
            () -> new SpatulaItem(20000));
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
    //FIRE
    public static final RegistryObject<Item> DRAGON_CHANNEL_FIRE = ITEMS.register("dragon_channel_fire",
            () -> new Item(
                    new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> AGRICULTURAL_SPOT_FIRE = ITEMS.register("agricultural_spot_fire",
            () -> new Item(
                    new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> SHENNONG_CHANNEL_FIRE = ITEMS.register("shennong_channel_fire",
            () -> new Item(
                    new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> GRAIN_CHANNEL_FIRE = ITEMS.register("grain_channel_fire",
            () -> new Item(
                    new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> ESOPHAGUS_CHANNEL_FIRE = ITEMS.register("esophagus_channel_fire",
            () -> new Item(
                    new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> GRAIN_SHENNONG_CHANNEL_FIRE = ITEMS.register("grain_shennong_channel_fire",
            () -> new Item(
                    new Item.Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB)));

    //植物
    public static final RegistryObject<Item> CINNAMON_BARK = CivilizationFireItems.ITEMS.register(
            "cinnamon_bark_fruit",
            () -> new PlantItem.PlantFruitItem(CivilizationFireTab.SPICE_CROPS_CREATIVE_MODE_TAB, false));
    public static final RegistryObject<Item> FRAGRANT = CivilizationFireItems.ITEMS.register("fragrant_fruit",
            () -> new PlantItem.PlantFruitItem(CivilizationFireTab.SPICE_CROPS_CREATIVE_MODE_TAB, false));
    public static final RegistryObject<Item> SICHUAN_PEPPER = CivilizationFireItems.ITEMS.register(
            "sichuan_pepper_fruit",
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
                stonePloughEntity.setPloughTimes(itemStack.getDamageValue());
                return stonePloughEntity;
            }, CivilizationFireTab.COMPONENT_MODE_TAB));
    public static final RegistryObject<Item> IRON_PLOUGH_ENTITY_ITEM = ITEMS.register("iron_plough_entity_item",
            () -> new EntityItem(20000, (level, itemStack) -> {
                PloughEntity.IronPloughEntity stonePloughEntity = new PloughEntity.IronPloughEntity(
                        CivilizationEntityTypes.IRON_PLOUGH_ENTITY.get(), level);
                stonePloughEntity.entityItem = (EntityItem) itemStack.getItem();
                stonePloughEntity.setPloughTimes(itemStack.getDamageValue());
                return stonePloughEntity;
            }, CivilizationFireTab.COMPONENT_MODE_TAB));
    public static final RegistryObject<Item> CURVILINEAR_PLOUGH_ENTITY_ITEM = ITEMS
            .register("curvilinear_plough_entity_item", () -> new EntityItem(30000, (level, itemStack) -> {
                PloughEntity.CurvilinearPloughEntity stonePloughEntity = new PloughEntity.CurvilinearPloughEntity(
                        CivilizationEntityTypes.CURVILINEAR_PLOUGH_ENTITY.get(), level);
                stonePloughEntity.entityItem = (EntityItem) itemStack.getItem();
                stonePloughEntity.setPloughTimes(itemStack.getDamageValue());
                return stonePloughEntity;
            }, CivilizationFireTab.COMPONENT_MODE_TAB));

    public static final RegistryObject<Item> KEEL_WATERWHEEL_ENTITY_ITEM = ITEMS
            .register("keel_waterwheel_entity_item", () -> new EntityItem(50000000, (level, itemStack) -> {
                KeelWaterwheelEntity keelWaterwheelEntity = new KeelWaterwheelEntity(
                        CivilizationEntityTypes.KEEL_WATER_WHEEL_ENTITY.get(), level);
                keelWaterwheelEntity.entityItem = (EntityItem) itemStack.getItem();
                keelWaterwheelEntity.setUseTimes(itemStack.getDamageValue());
                return keelWaterwheelEntity;
            }, CivilizationFireTab.COMPONENT_MODE_TAB));

    public static final RegistryObject<Item> BUCKETS_WATERWHEEL_ENTITY_ITEM = ITEMS
            .register("buckets_waterwheel_entity_item",
                    () -> new EntityItem(100000000, (level, itemStack) -> {
                        BucketsWaterwheelEntity bucketsWaterwheelEntity = new BucketsWaterwheelEntity(
                                CivilizationEntityTypes.BUCKETS_WATER_WHEEL_ENTITY
                                        .get(),
                                level);
                        bucketsWaterwheelEntity.entityItem = (EntityItem) itemStack.getItem();
                        bucketsWaterwheelEntity.setUseTimes(itemStack.getDamageValue());
                        return bucketsWaterwheelEntity;
                    }, CivilizationFireTab.COMPONENT_MODE_TAB));

    public static final RegistryObject<Item> FARMING_BOT_ENTITY_ITEM = ITEMS
            .register("farming_bot_entity_item",
                    () -> new EntityItem(1, (level, itemStack) -> {
                        FarmingBot farmingBot = new FarmingBot(CivilizationEntityTypes.FARMING_BOT_ENTITY.get(), level);
                        farmingBot.readDataFromItemStack(itemStack);
                        return farmingBot;
                    }, CivilizationFireTab.COMPONENT_MODE_TAB));
    public static final RegistryObject<Item> GUARDING_BOT_ENTITY_ITEM = ITEMS
            .register("guarding_bot_entity_item",
                    () -> new EntityItem(1, (level, itemStack) -> {
                        GuardianBot guardianBot = new GuardianBot(CivilizationEntityTypes.GUARDIAN_BOT.get(), level);
                        guardianBot.readDataFromItemStack(itemStack);
                        return guardianBot;
                    }, CivilizationFireTab.COMPONENT_MODE_TAB));
    public static final RegistryObject<Item> MINING_BOT_ENTITY_ITEM = ITEMS
            .register("mining_bot_entity_item",
                    () -> new EntityItem(1, (level, itemStack) -> {
                        MiningBot miningBot = new MiningBot(CivilizationEntityTypes.MINING_BOT.get(), level);
                        miningBot.readDataFromItemStack(itemStack);
                        return miningBot;
                    }, CivilizationFireTab.COMPONENT_MODE_TAB));

    //bot add
    public static final RegistryObject<Item> ENERGY_ADD_LEVEL_1 = ITEMS.register("energy_add_level_1", () -> new BotAddItem(1, BotAddItem.BotAddType.EnergyAdd));
    public static final RegistryObject<Item> ENERGY_ADD_LEVEL_2 = ITEMS.register("energy_add_level_2", () -> new BotAddItem(2, BotAddItem.BotAddType.EnergyAdd));
    public static final RegistryObject<Item> ENERGY_ADD_LEVEL_3 = ITEMS.register("energy_add_level_3", () -> new BotAddItem(2, BotAddItem.BotAddType.EnergyAdd));

    public static final RegistryObject<Item> MC_ATTRIBUTE_ADD_LEVEL_1 = ITEMS.register("mc_attribute_add_level_1", () -> new BotAddItem(1, BotAddItem.BotAddType.MCAttributeAdd));
    public static final RegistryObject<Item> MC_ATTRIBUTE_ADD_LEVEL_2 = ITEMS.register("mc_attribute_add_level_2", () -> new BotAddItem(2, BotAddItem.BotAddType.MCAttributeAdd));
    public static final RegistryObject<Item> MC_ATTRIBUTE_ADD_LEVEL_3 = ITEMS.register("mc_attribute_add_level_3", () -> new BotAddItem(3, BotAddItem.BotAddType.MCAttributeAdd));

    public static final RegistryObject<Item> CORRESPONDING_ADD_LEVEL_1 = ITEMS.register("corresponding_add_level_1", () -> new BotAddItem(1, BotAddItem.BotAddType.CorrespondingAbilityAdd));
    public static final RegistryObject<Item> CORRESPONDING_ADD_LEVEL_2 = ITEMS.register("corresponding_add_level_2", () -> new BotAddItem(2, BotAddItem.BotAddType.CorrespondingAbilityAdd));
    public static final RegistryObject<Item> CORRESPONDING_ADD_LEVEL_3 = ITEMS.register("corresponding_add_level_3", () -> new BotAddItem(3, BotAddItem.BotAddType.CorrespondingAbilityAdd));

    //汁水
    public static final RegistryObject<Item> VEGETABLE_JUICE = ITEMS.register("vegetable_juice", () -> new VegetableJuiceItem("big_chinese_cabbage_fruit",() -> new MobEffectInstance(CivilizationFireEffect.VEGETABLE_EFFECT.get(), 30 * 25, 1)));
    public static final RegistryObject<Item> EGGPLANT_JUICE = ITEMS.register("eggplant_juice", () -> new VegetableJuiceItem("eggplant_fruit",() -> new MobEffectInstance(CivilizationFireEffect.EGGPLANT_EFFECT.get(), 30 * 25, 1)));
    public static final RegistryObject<Item> BEAN_JUICE = ITEMS.register("bean_juice", () -> new VegetableJuiceItem("broad_bean_fruit",() -> new MobEffectInstance(CivilizationFireEffect.BEAN_EFFECT.get(), 30 * 25, 1)));
    public static final RegistryObject<Item> TOMATO_JUICE = ITEMS.register("tomato_juice", () -> new VegetableJuiceItem("tomatoes_fruit",() -> new MobEffectInstance(CivilizationFireEffect.TOMATO_EFFECT.get(), 30 * 25, 1)));
    public static final RegistryObject<Item> CARROT_JUICE = ITEMS.register("carrot_juice", () -> new VegetableJuiceItem("carrot",() -> new MobEffectInstance(CivilizationFireEffect.CARROT_EFFECT.get(), 30 * 25, 1)));
    public static final RegistryObject<Item> GINGER_JUICE = ITEMS.register("ginger_juice", () -> new VegetableJuiceItem("ginger_item",() -> new MobEffectInstance(CivilizationFireEffect.GINGER_EFFECT.get(), 30 * 25, 1)));
    public static final RegistryObject<Item> PUMPKIN_JUICE = ITEMS.register("pumpkin_juice", () -> new VegetableJuiceItem("pumpkin",() -> new MobEffectInstance(CivilizationFireEffect.PUMPKIN_EFFECT.get(), 30 * 25, 1)));
    public static final RegistryObject<Item> CELERY_JUICE = ITEMS.register("celery_juice", () -> new VegetableJuiceItem("celery_fruit",() -> new MobEffectInstance(CivilizationFireEffect.CELERY_EFFECT.get(), 30 * 25, 1)));
    public static final RegistryObject<Item> ASSORTED_VEGETABLES_JUICE = ITEMS.register("assorted_vegetables_juice", () -> new VegetableJuiceItem(new Item.Properties().tab(CivilizationFireTab.ADD_MODE_TAB).stacksTo(1).food(new FoodProperties.Builder().alwaysEat().saturationMod(0.5F).nutrition(1).
            effect(()->new MobEffectInstance(CivilizationFireEffect.CELERY_EFFECT.get(), 30 * 25, 1), 0.125F).
            effect(()->new MobEffectInstance(CivilizationFireEffect.EGGPLANT_EFFECT.get(), 30 * 25, 1), 0.125F).
            effect(()->new MobEffectInstance(CivilizationFireEffect.BEAN_EFFECT.get(), 30 * 25, 1), 0.125F).
            effect(()->new MobEffectInstance(CivilizationFireEffect.TOMATO_EFFECT.get(), 30 * 25, 1), 0.125F).
            effect(()->new MobEffectInstance(CivilizationFireEffect.VEGETABLE_EFFECT.get(), 30 * 25, 1), 0.125F).
            effect(()->new MobEffectInstance(CivilizationFireEffect.CARROT_EFFECT.get(), 30 * 25, 1), 0.125F).
            effect(()->new MobEffectInstance(CivilizationFireEffect.GINGER_EFFECT.get(), 30 * 25, 1), 0.125F).
            effect(()->new MobEffectInstance(CivilizationFireEffect.PUMPKIN_EFFECT.get(), 30 * 25, 1), 0.125F).
            build())));

}