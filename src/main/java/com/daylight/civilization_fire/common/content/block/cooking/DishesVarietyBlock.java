package com.daylight.civilization_fire.common.content.block.cooking;

import com.daylight.civilization_fire.common.content.block.HasDropBlock;
import com.daylight.civilization_fire.common.content.recipe.CookingDishesType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class DishesVarietyBlock extends HasDropBlock {
    public CookingDishesType cookingDishesType;//装盘方式
    public DishesVarietyBlock(CookingDishesType cookingDishesType) {
        super(0,Properties.of(Material.EGG).noCollission().strength(0.6F)
                .requiresCorrectToolForDrops());
        this.cookingDishesType = cookingDishesType;
    }
}
