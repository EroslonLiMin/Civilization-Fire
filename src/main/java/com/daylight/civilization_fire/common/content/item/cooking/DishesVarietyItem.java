package com.daylight.civilization_fire.common.content.item.cooking;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DishesVarietyItem extends Item {
    public DishesVarietyItem(int nutrition,int saturationMod) {
        super(new Properties().food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturationMod).build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {

        return super.finishUsingItem(itemStack, level, livingEntity);
    }
}
