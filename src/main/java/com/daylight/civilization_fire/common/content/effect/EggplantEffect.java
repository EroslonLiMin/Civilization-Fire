package com.daylight.civilization_fire.common.content.effect;

import com.daylight.civilization_fire.common.content.register.CivilizationFireEffect;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;

@Mod.EventBusSubscriber
public class EggplantEffect extends MobEffect {
    public EggplantEffect() {
        super(MobEffectCategory.BENEFICIAL, new Color(89, 32, 113).getRGB());
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 25 * 5, pAmplifier));
    }



    @SubscribeEvent
    public static void onEggplantEffectOnAttackEvent(LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity attackEntity) {
            if (attackEntity.hasEffect(CivilizationFireEffect.EGGPLANT_EFFECT.get())) {
                event.getEntityLiving().knockback(0.8F, Mth.sin(attackEntity.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(attackEntity.getYRot() * ((float) Math.PI / 180F)));
            }
        }
    }
}
