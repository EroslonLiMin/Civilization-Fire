package com.daylight.civilization_fire.registry;

import com.daylight.civilization_fire.entity.agriculture.PloughEntity;
import com.daylight.civilization_fire.util.Utils;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypeRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Utils.MOD_ID);
    public static final RegistryObject<EntityType<PloughEntity.StonePloughEntity>> STONE_PLOUGH_ENTITY = ENTITY_TYPES.register("stone_plough_entity", () -> EntityType.Builder.of(PloughEntity.StonePloughEntity::new, MobCategory.MISC).sized(0.5F, 1.5F).clientTrackingRange(10).fireImmune().build("stone_plough_entity"));
    public static final RegistryObject<EntityType<PloughEntity.IronPloughEntity>> IRON_PLOUGH_ENTITY = ENTITY_TYPES.register("iron_plough_entity", () -> EntityType.Builder.of(PloughEntity.IronPloughEntity::new, MobCategory.MISC).sized(0.5F, 1.5F).clientTrackingRange(10).fireImmune().build("iron_plough_entity"));
    public static final RegistryObject<EntityType<PloughEntity.CurvilinearPloughEntity>> CURVILINEAR_PLOUGH_ENTITY = ENTITY_TYPES.register("curvilinear_entity", () -> EntityType.Builder.of(PloughEntity.CurvilinearPloughEntity::new, MobCategory.MISC).sized(0.5F, 1.5F).clientTrackingRange(10).build("curvilinear_entity"));


}
