package com.daylight.civilization_fire.common.content.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.awt.*;


public class PumpkinEffect extends MobEffect {
    public PumpkinEffect() {
        super(MobEffectCategory.BENEFICIAL, new Color(102, 56, 6).getRGB());
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        MobEffectInstance[] mobEffectInstances = pLivingEntity.getActiveEffects().toArray(new MobEffectInstance[0]);
        for (MobEffectInstance mobEffectInstance : mobEffectInstances) {
            if (!mobEffectInstance.getEffect().isBeneficial()) {
                pLivingEntity.removeEffect(mobEffectInstance.getEffect());
            }
        }
    }

}
