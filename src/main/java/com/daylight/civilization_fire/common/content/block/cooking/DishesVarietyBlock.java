package com.daylight.civilization_fire.common.content.block.cooking;

import com.daylight.civilization_fire.data.cooking.CookingDishesType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class DishesVarietyBlock extends Block {
    public CookingDishesType cookingDishesType;//装盘方式
    public DishesVarietyBlock(CookingDishesType cookingDishesType) {
        super(Properties.of(Material.EGG).noOcclusion().strength(0.6F)
                .requiresCorrectToolForDrops());
        this.cookingDishesType = cookingDishesType;
    }
}
