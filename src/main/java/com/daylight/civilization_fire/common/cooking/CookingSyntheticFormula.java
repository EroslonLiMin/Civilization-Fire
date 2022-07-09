package com.daylight.civilization_fire.common.cooking;

import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
import com.daylight.civilization_fire.common.content.item.cooking.CondimentItem;

//烹饪合成配方
public class CookingSyntheticFormula {
    public PlantItem[] needCookingItems;
    public CondimentItem[] needCondimentItems;
    public String name;//配方名称

    public CookingSyntheticFormula() {
    }

    public CookingSyntheticFormula setNeedCookingItems(PlantItem... plantItems) {
        this.needCookingItems = plantItems;
        return this;
    }

    public CookingSyntheticFormula setNeedCondimentItems(CondimentItem... condimentItems) {
        this.needCondimentItems = condimentItems;
        return this;
    }
}
