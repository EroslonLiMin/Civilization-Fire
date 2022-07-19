package com.daylight.civilization_fire.common.content.register;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.effect.BeanEffect;
import com.daylight.civilization_fire.common.content.effect.EggplantEffect;
import com.daylight.civilization_fire.common.content.effect.TomatoEffect;
import com.daylight.civilization_fire.common.content.effect.VegetableEffect;
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
}
