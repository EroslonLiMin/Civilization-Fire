package com.daylight.civilization_fire.registry;

import com.daylight.civilization_fire.block.agriculture.PlantBlock;
import com.daylight.civilization_fire.util.Utils;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {
        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Utils.MOD_ID);
        public static final RegistryObject<BlockEntityType<PlantBlock.PlantBlockEntity>> PLANT_BLOCK_ENTITY = BLOCK_ENTITIES.register("plant_block_entity", () -> BlockEntityType.Builder.of(PlantBlock.PlantBlockEntity::new, BlockRegistry.RICE_PLANT.plantBlockRegistry.get()).build(null));


}
