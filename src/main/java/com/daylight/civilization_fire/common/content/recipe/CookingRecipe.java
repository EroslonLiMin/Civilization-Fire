package com.daylight.civilization_fire.data.cooking;


//烹饪合成配方
public class CookingSyntheticFormula {
    public String[] needCookingItems;//合成需要的物品
    public String[] needCondimentItems;//合成需要的调料
    public CookingDishesType cookingDishesType;//方式
    public String cookingItem;//合成出来的物品
    public CookingTool cookingTool;//烹饪工具
    public CanChangeCookingItem[] canChangeCookingItems;

    public CookingSyntheticFormula(CookingDishesType cookingDishesType,String cookingItem,CookingTool cookingTool){
        this.cookingDishesType = cookingDishesType;
        this.cookingItem = cookingItem;
        this.cookingTool = cookingTool;
    }

    public CookingSyntheticFormula setNeedCookingItems(String... plantItems){
        this.needCookingItems = plantItems;
        return this;
    }

    public CookingSyntheticFormula setNeedCondimentItems(String... condimentItems){
        this.needCondimentItems = condimentItems;
        return this;
    }

    public CookingSyntheticFormula setCanChangeCookingItems(CanChangeCookingItem... canChanges){
        this.canChangeCookingItems = canChanges;
        return this;
    }

    public static class CanChangeCookingItem{
        public String cookingItem;
        public String[] canChanges;
        public CanChangeCookingItem(String cookingItem,String... canChanges){
            this.cookingItem = cookingItem;
            this.canChanges = canChanges;
        }
    }
}
