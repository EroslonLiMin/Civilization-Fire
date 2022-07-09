package com.daylight.civilization_fire.common.content.recipe;


//烹饪合成配方
public class CookingRecipe {
    public String[] needCookingItems;//合成需要的物品
    public String[] needCondimentItems;//合成需要的调料
    public CookingDishesType cookingDishesType;//方式
    public String cookingItem;//合成出来的物品
    public CookingTool cookingTool;//烹饪工具
    public CanChangeCookingItem[] canChangeCookingItems;

    public CookingRecipe(CookingDishesType cookingDishesType, String cookingItem, CookingTool cookingTool){
        this.cookingDishesType = cookingDishesType;
        this.cookingItem = cookingItem;
        this.cookingTool = cookingTool;
    }

    public CookingRecipe setNeedCookingItems(String... plantItems){
        this.needCookingItems = plantItems;
        return this;
    }

    public CookingRecipe setNeedCondimentItems(String... condimentItems){
        this.needCondimentItems = condimentItems;
        return this;
    }

    public CookingRecipe setCanChangeCookingItems(CanChangeCookingItem... canChanges){
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
