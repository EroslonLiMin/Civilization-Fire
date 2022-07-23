package com.daylight.civilization_fire.common.content.item;

import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

public class GunpowderPepper extends Item {
    public GunpowderPepper() {
        super(new Properties().tab(CivilizationFireTab.ADD_MODE_TAB).stacksTo(16).food(new FoodProperties.Builder().alwaysEat().nutrition(3).saturationMod(3).fast().build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        pLevel.explode(pLivingEntity,pLivingEntity.getX(),pLivingEntity.getY(),pLivingEntity.getZ(),2, Explosion.BlockInteraction.NONE);
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
