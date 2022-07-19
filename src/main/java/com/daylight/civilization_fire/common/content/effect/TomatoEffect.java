package com.daylight.civilization_fire.common.content.effect;

import com.daylight.civilization_fire.common.content.register.CivilizationFireEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;
import java.util.Random;

@Mod.EventBusSubscriber
public class TomatoEffect extends MobEffect {
    public TomatoEffect() {
        super(MobEffectCategory.BENEFICIAL, new Color(142, 34, 34).getRGB());
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 25 * 5, pAmplifier));
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 25 * 5, pAmplifier));
    }

    //受伤事件处理
    @SubscribeEvent
    public static void onVegetableEffectOnHurt(LivingHurtEvent hurtEvent) {
        if(hurtEvent.getEntityLiving().hasEffect(CivilizationFireEffect.TOMATO_EFFECT.get())){
            float random = new Random().nextInt((int) (hurtEvent.getAmount() * 100)) / 100.0F;
            hurtEvent.setAmount(hurtEvent.getAmount() - random);
            hurtEvent.getEntityLiving().setHealth(hurtEvent.getEntityLiving().getHealth() + random);
        }
    }
}
