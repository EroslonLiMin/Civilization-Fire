package com.daylight.civilization_fire.common.content.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import java.awt.*;

public class GingerEffect extends MobEffect {
    public GingerEffect() {
        super(MobEffectCategory.BENEFICIAL, new Color(118, 109, 50).getRGB());
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 5 * 25, pAmplifier));
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 5 * 25, pAmplifier));
        pLivingEntity.removeEffect(MobEffects.BLINDNESS);
        pLivingEntity.removeEffect(MobEffects.HUNGER);
    }
}
