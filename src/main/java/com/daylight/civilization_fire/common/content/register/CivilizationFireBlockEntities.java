package com.daylight.civilization_fire.common.content.register;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.block.agriculture.AgricultureEnchantmentBlockTable;
import com.daylight.civilization_fire.common.content.block.agriculture.PlantBlock;
import com.daylight.civilization_fire.common.content.block.agriculture.TreePlant;
import com.daylight.civilization_fire.common.content.block.cooking.CasseroleBlock;
import com.daylight.civilization_fire.common.content.block.cooking.FoodSteamerBlock;
import com.daylight.civilization_fire.common.content.block.cooking.IronPotBlock;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CivilizationFireBlockEntities {
        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
                .create(ForgeRegistries.BLOCK_ENTITIES, CivilizationFire.MODID);

        public static final RegistryObject<BlockEntityType<PlantBlock.PlantBlockEntity>> PLANT_BLOCK_ENTITY = BLOCK_ENTITIES
                .register("plant_block_entity", () -> BlockEntityType.Builder
                        .of(PlantBlock.PlantBlockEntity::new,
                                CivilizationFireBlocks.RICE_PLANT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.GLUTINOUS_RICE_PLANT.plantBlockRegistry
                                        .get(),
                                CivilizationFireBlocks.YELLOW_RICE_PLANT.plantBlockRegistry
                                        .get(),
                                CivilizationFireBlocks.SORGHUM_PLANT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.CORN_PLANT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.SWEET_POTATO_PLANT.plantBlockRegistry
                                        .get(),
                                CivilizationFireBlocks.TARO_PLANT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.BROAD_BEAN_PLANT.plantBlockRegistry
                                        .get(),
                                CivilizationFireBlocks.YOUNG_SOYBEAN_PLANT.plantBlockRegistry
                                        .get(),
                                CivilizationFireBlocks.CAROB_PLANT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.LENTIL_HORN_PLANT.plantBlockRegistry
                                        .get(),
                                CivilizationFireBlocks.WHITE_BEAN_HORN_PLANT.plantBlockRegistry
                                        .get(),
                                CivilizationFireBlocks.PEANUT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.SHALLOT_PLANT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.GREEN_CHINESE_ONION_PLANT.plantBlockRegistry
                                        .get(),
                                CivilizationFireBlocks.EGGPLANT_PLANT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.CELERY_PLANT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.CUCUMBER_PLANT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.WAX_GOURD_PLANT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.BALSAM_PEAR_PLANT.plantBlockRegistry
                                        .get(),
                                CivilizationFireBlocks.TOWEL_GOURD_PLANT.plantBlockRegistry
                                        .get(),
                                CivilizationFireBlocks.SOYBEAN_PLANT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.GINGER_PLANT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.TERNIP_PLANT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.WATER_RADISH_PLANT.plantBlockRegistry
                                        .get(),
                                CivilizationFireBlocks.TOMATOES_PLANT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.ONION_PLANT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.HOT_PEPPER_PLANT.plantBlockRegistry
                                        .get(),
                                CivilizationFireBlocks.PEPPER_PLANT.plantBlockRegistry.get(),
                                CivilizationFireBlocks.FENNEL_PLANT.plantBlockRegistry.get())
                        .build(null));

        public static final RegistryObject<BlockEntityType<TreePlant.TreeSaplingBlockEntity>> TREE_SAPLING_BLOCK_ENTITY = BLOCK_ENTITIES
                .register("tree_sapling_block_entity",
                        () -> BlockEntityType.Builder
                                .of(TreePlant.TreeSaplingBlockEntity::new,
                                        CivilizationFireBlocks.SICHUAN_PEPPER_TREE_SAPLING
                                                .get(),
                                        CivilizationFireBlocks.CINNAMON_TREE_SAPLING
                                                .get(),
                                        CivilizationFireBlocks.FRAGRANT_TREE_SAPLING
                                                .get())
                                .build(null));

        public static final RegistryObject<BlockEntityType<IronPotBlock.IronPotBlockEntity>> IRON_POT_BLOCK_ENTITY = BLOCK_ENTITIES
                .register("iron_pot_block_entity",
                        () -> BlockEntityType.Builder
                                .of(IronPotBlock.IronPotBlockEntity::new,
                                        CivilizationFireBlocks.IRON_POT_BLOCK.get())
                                .build(null));
        public static final RegistryObject<BlockEntityType<FoodSteamerBlock.FoodSteamerBlockEntity>> FOOD_STEAMER_BLOCK_ENTITY = BLOCK_ENTITIES
                        .register("food_steamer_block_entity",
                                        () -> BlockEntityType.Builder
                                                        .of(FoodSteamerBlock.FoodSteamerBlockEntity::new,
                                                                        CivilizationFireBlocks.FOOD_STEAMER_BLOCK.get())
                                                        .build(null));
        public static final RegistryObject<BlockEntityType<CasseroleBlock.CasseroleBlockEntity>> CASSEROLE_BLOCK_ENTITY = BLOCK_ENTITIES
                        .register("casserole_block_entity",
                                        () -> BlockEntityType.Builder
                                                        .of(CasseroleBlock.CasseroleBlockEntity::new,
                                                                        CivilizationFireBlocks.CASSEROLE_BLOCK.get())
                                                        .build(null));

        public static final RegistryObject<BlockEntityType<AgricultureEnchantmentBlockTable.AgricultureEnchantmentTableBlockEntity>> AGRICULTURE_ENCHANTMENT_TABLE_BLOCK_ENTITY = BLOCK_ENTITIES
                        .register("agriculture_enchantment_table_block_entity",
                                        () -> BlockEntityType.Builder
                                                        .of(AgricultureEnchantmentBlockTable.AgricultureEnchantmentTableBlockEntity::new,
                                                                        CivilizationFireBlocks.AGRICULTURE_ENCHANTMENT_TABLE
                                                                                        .get())
                                                        .build(null));
}
