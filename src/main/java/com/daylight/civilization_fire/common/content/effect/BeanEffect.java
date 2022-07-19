package com.daylight.civilization_fire.common.content.effect;

import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.awt.*;
import java.util.List;


public class BeanEffect extends MobEffect {
    public BeanEffect() {
        super(MobEffectCategory.NEUTRAL, new Color(201, 199, 199).getRGB());
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 25 * 5, pAmplifier));
        List<Entity> list = pLivingEntity.getLevel().getEntitiesOfClass(Entity.class,pLivingEntity.getBoundingBox().inflate(5), (entity)-> entity.isAlive() && entity != pLivingEntity);
        for(Entity entity : list){
            entity.hasImpulse = true;
            Vec3 vec3 = entity.getDeltaMovement();
            Vec3 vec31 = (new Vec3(-Mth.sin(entity.getYRot() * ((float) Math.PI / 180F)), 0.0D, Mth.cos(entity.getYRot() * ((float) Math.PI / 180F)))).normalize().scale(0.8);
            entity.setDeltaMovement(vec3.x / 2.0D - vec31.x,  vec3.y, vec3.z / 2.0D - vec31.z);
        }
    }
}
