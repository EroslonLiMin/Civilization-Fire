package com.daylight.civilization_fire.common.content.register;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.entity.agriculture.PloughEntity;
import com.daylight.civilization_fire.common.content.entity.bot.GuardianBot;

import com.daylight.civilization_fire.common.content.entity.bot.MiningBot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CivilizationEntityTypes {
        public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(
                ForgeRegistries.ENTITIES, CivilizationFire.MODID);

        public static final RegistryObject<EntityType<PloughEntity.StonePloughEntity>> STONE_PLOUGH_ENTITY = ENTITY_TYPES
                .register("stone_plough_entity",
                        () -> EntityType.Builder
                                .of(PloughEntity.StonePloughEntity::new, MobCategory.MISC)
                                .sized(0.5F, 1.5F)
                                .clientTrackingRange(10).fireImmune()
                                .build("stone_plough_entity"));

        public static final RegistryObject<EntityType<PloughEntity.IronPloughEntity>> IRON_PLOUGH_ENTITY = ENTITY_TYPES
                .register("iron_plough_entity",
                        () -> EntityType.Builder
                                .of(PloughEntity.IronPloughEntity::new, MobCategory.MISC)
                                .sized(0.5F, 1.5F)
                                .clientTrackingRange(10).fireImmune()
                                .build("iron_plough_entity"));

        public static final RegistryObject<EntityType<PloughEntity.CurvilinearPloughEntity>> CURVILINEAR_PLOUGH_ENTITY = ENTITY_TYPES
                .register("curvilinear_entity",
                        () -> EntityType.Builder
                                .of(PloughEntity.CurvilinearPloughEntity::new, MobCategory.MISC)
                                .sized(0.5F, 1.5F).clientTrackingRange(10)
                                .build("curvilinear_entity"));

        public static final RegistryObject<EntityType<GuardianBot>> GUARDIAN_BOT = ENTITY_TYPES
                .register("guardian_bot",
                        () -> EntityType.Builder
                                .of(GuardianBot::new, MobCategory.MISC)
                                .sized(1.4F, 2.7F).clientTrackingRange(10)
                                .build("guardian_bot"));

        public static final RegistryObject<EntityType<MiningBot>> MINING_BOT = ENTITY_TYPES
                .register("mining_bot",
                        () -> EntityType.Builder
                                .of(MiningBot::new, MobCategory.MISC)
                                .sized(1.4F, 2.7F).clientTrackingRange(10)
                                .build("mining_bot"));
}
