package com.daylight.civilization_fire.common.content.effect;

import com.daylight.civilization_fire.common.content.register.CivilizationFireEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.OreBlock;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;
import java.util.Random;

@Mod.EventBusSubscriber
public class VegetableEffect extends MobEffect {
    public VegetableEffect() {
        super(MobEffectCategory.BENEFICIAL, new Color(255, 255, 255).getRGB());
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.LUCK, 5 * 25, pAmplifier));
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    //受伤事件处理
    @SubscribeEvent
    public static void onVegetableEffectOnHurt(LivingHurtEvent hurtEvent) {
        if (hurtEvent.getEntityLiving().hasEffect(CivilizationFireEffect.VEGETABLE_EFFECT.get())) {
            if (new Random().nextBoolean()) {
                hurtEvent.setAmount(0);
            }
        }
    }

    //挖矿处理
    @SubscribeEvent
    public static void onVegetableEffectOnPlayerBreakOre(BlockEvent.BreakEvent event) {
        if (event.getPlayer().hasEffect(CivilizationFireEffect.VEGETABLE_EFFECT.get()) && new Random().nextBoolean()) {
            event.setExpToDrop(event.getExpToDrop() * 2);
            if (event.getState().getBlock() instanceof OreBlock) {
                if (!event.getWorld().isClientSide())
                    event.getWorld().addFreshEntity(new ItemEntity(event.getPlayer().getLevel(), event.getPos().getX(),
                            event.getPos().getY(), event.getPos().getY(), new ItemStack(event.getState().getBlock())));
            }
        }
    }

}
