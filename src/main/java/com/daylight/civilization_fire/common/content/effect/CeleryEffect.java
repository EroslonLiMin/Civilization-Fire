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

@Mod.EventBusSubscriber
public class CeleryEffect extends MobEffect {
    public CeleryEffect() {
        super(MobEffectCategory.BENEFICIAL, new Color(47, 120, 64).getRGB());
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 5 * 25, pAmplifier));
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 5 * 25, pAmplifier));
    }

    //受伤事件处理
    @SubscribeEvent
    public static void onCeleryEffectOnHurt(LivingHurtEvent hurtEvent) {
        if (hurtEvent.getEntityLiving().hasEffect(CivilizationFireEffect.CELERY_EFFECT.get())) {
            if (hurtEvent.getSource().isFall()) {
                hurtEvent.setCanceled(true);
            }
        }
    }
}
