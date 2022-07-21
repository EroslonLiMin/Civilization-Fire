package com.daylight.civilization_fire.common.content.register;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CivilizationFireEffect {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,
            CivilizationFire.MODID);

    public static final RegistryObject<MobEffect> VEGETABLE_EFFECT = EFFECTS.register("vegetable_effect", VegetableEffect::new);
    public static final RegistryObject<MobEffect> EGGPLANT_EFFECT = EFFECTS.register("eggplant_effect", EggplantEffect::new);
    public static final RegistryObject<MobEffect> BEAN_EFFECT = EFFECTS.register("bean_effect", BeanEffect::new);
    public static final RegistryObject<MobEffect> TOMATO_EFFECT = EFFECTS.register("tomato_effect", TomatoEffect::new);
    public static final RegistryObject<MobEffect> CARROT_EFFECT = EFFECTS.register("carrot_effect", CarrotEffect::new);
    public static final RegistryObject<MobEffect> GINGER_EFFECT = EFFECTS.register("ginger_effect", GingerEffect::new);
    public static final RegistryObject<MobEffect> PUMPKIN_EFFECT = EFFECTS.register("pumpkin_effect", PumpkinEffect::new);
    public static final RegistryObject<MobEffect> CELERY_EFFECT = EFFECTS.register("celery_effect", CeleryEffect::new);
}
