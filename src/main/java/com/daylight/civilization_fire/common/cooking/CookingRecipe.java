package com.daylight.civilization_fire.common.cooking;

import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
import com.daylight.civilization_fire.common.content.item.cooking.CondimentItem;

//烹饪合成配方
public class CookingRecipe {
    public PlantItem[] needCookingItems;
    public CondimentItem[] needCondimentItems;
    public String name;//配方名称

    public CookingRecipe() {
    }

    public CookingRecipe setNeedCookingItems(PlantItem... plantItems) {
        this.needCookingItems = plantItems;
        return this;
    }

    public CookingRecipe setNeedCondimentItems(CondimentItem... condimentItems) {
        this.needCondimentItems = condimentItems;
        return this;
    }
}
