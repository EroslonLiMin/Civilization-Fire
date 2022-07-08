package com.daylight.civilization_fire.registry;

import com.daylight.civilization_fire.block.agriculture.PlantBlock;
import com.daylight.civilization_fire.block.agriculture.TreePlant;
import com.daylight.civilization_fire.block.cooking.CasseroleBlock;
import com.daylight.civilization_fire.util.Utils;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {
        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
                        .create(ForgeRegistries.BLOCK_ENTITIES, Utils.MOD_ID);
        public static final RegistryObject<BlockEntityType<PlantBlock.PlantBlockEntity>> PLANT_BLOCK_ENTITY = BLOCK_ENTITIES
                        .register("plant_block_entity", () -> BlockEntityType.Builder
                                        .of(PlantBlock.PlantBlockEntity::new,
                                                        BlockRegistry.RICE_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.GLUTINOUS_RICE_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.YELLOW_RICE_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.SORGHUM_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.CORN_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.SWEET_POTATO_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.TARO_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.BROAD_BEAN_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.YOUNG_SOYBEAN_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.CAROB_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.LENTIL_HORN_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.WHITE_BEAN_HORN_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.PEANUT.plantBlockRegistry.get(),
                                                        BlockRegistry.SHALLOT_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.GREEN_CHINESE_ONION_PLANT.plantBlockRegistry
                                                                        .get(),
                                                        BlockRegistry.EGGPLANT_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.CELERY_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.CUCUMBER_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.WAX_GOURD_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.BALSAM_PEAR_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.TOWEL_GOURD_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.SOYBEAN_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.GINGER_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.TERNIP_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.WATER_RADISH_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.TOMATOES_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.ONION_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.HOT_PEPPER_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.PEPPER_PLANT.plantBlockRegistry.get(),
                                                        BlockRegistry.FENNEL_PLANT.plantBlockRegistry.get())
                                        .build(null));
        public static final RegistryObject<BlockEntityType<TreePlant.TreeSaplingBlockEntity>> TREE_SAPLING_BLOCK_ENTITY = BLOCK_ENTITIES
                        .register("tree_sapling_block_entity",
                                        () -> BlockEntityType.Builder
                                                        .of(TreePlant.TreeSaplingBlockEntity::new,
                                                                        BlockRegistry.SICHUAN_PEPPER_TREE_SAPLING.get(),
                                                                        BlockRegistry.CINNAMON_TREE_SAPLING.get(),
                                                                        BlockRegistry.FRAGRANT_TREE_SAPLING.get())
                                                        .build(null));
        public static final RegistryObject<BlockEntityType<CasseroleBlock.CasseroleBlockEntity>> CASSEROLE_BLOCK_ENTITY = BLOCK_ENTITIES
                        .register("casserole_block_entity",
                                        () -> BlockEntityType.Builder
                                                        .of(CasseroleBlock.CasseroleBlockEntity::new,
                                                                        BlockRegistry.CASSEROLE_BLOCK.get())
                                                        .build(null));
}
