package com.daylight.civilization_fire.common.content.item.cooking;

import com.daylight.civilization_fire.common.content.recipe.CookingDishesType;
import com.daylight.civilization_fire.common.content.register.CivilizationFireItems;
import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

//烹饪料理
public class DishesVarietyItem extends BlockItem {
    public CookingDishesType cookingDishesType;//装盘方式
    public DishesVarietyItem(Block dishesVarietyBlock, int nutrition, int saturationMod, CookingDishesType cookingDishesType) {
        super(dishesVarietyBlock,new Properties().tab(CivilizationFireTab.COOKING_CREATIVE_MODE_TAB).food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturationMod).build()));
        this.cookingDishesType = cookingDishesType;
    }

    //归还碗/盘子
    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if(livingEntity instanceof Player player) {
            player.addItem(new ItemStack(cookingDishesType.equals(CookingDishesType.Bowl) ? CivilizationFireItems.BOWL.get() : CivilizationFireItems.PLATE.get()));
        }
        return super.finishUsingItem(itemStack, level, livingEntity);
    }
}
