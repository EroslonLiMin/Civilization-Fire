package com.daylight.civilization_fire.common.content.recipe;

import com.daylight.civilization_fire.common.CivilizationFire;
import net.minecraft.world.item.Item;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

//烹饪合成配方
public class CookingRecipe {
    //COOKING_RECIPE MAP
    public static final Map<String, CookingRecipe> COOKING_RECIPE_MAP = new HashMap<>();
    public String[] needCookingItems;//合成需要的物品
    public String[] needCondimentItems;//合成需要的调料
    public CookingDishesType cookingDishesType;//方式
    public String cookingItem;//合成出来的物品
    public CookingTool cookingTool;//烹饪工具
    public CanChangeCookingItem[] canChangeCookingItems = new CanChangeCookingItem[0];

    public CookingRecipe(CookingDishesType cookingDishesType, String cookingItem, CookingTool cookingTool) {
        this.cookingDishesType = cookingDishesType;
        this.cookingItem = cookingItem;
        this.cookingTool = cookingTool;
        COOKING_RECIPE_MAP.put(cookingItem, this);
    }

    //设置需要的烹饪农作物
    public CookingRecipe setNeedCookingItems(String... plantItems) {
        this.needCookingItems = plantItems;
        return this;
    }

    //设置烹饪需要的调料
    public CookingRecipe setNeedCondimentItems(String... condimentItems) {
        this.needCondimentItems = condimentItems;
        return this;
    }

    public Item getCookingItem() {
        return ForgeRegistries.ITEMS.getHolder(CivilizationFire.resource(cookingItem)).get().value();
    }

    //设置可以替代的食材
    public CookingRecipe setCanChangeCookingItems(CanChangeCookingItem... canChanges) {
        this.canChangeCookingItems = canChanges;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder needCookingItemsStr = new StringBuilder();
        for (String str : this.needCookingItems) {
            needCookingItemsStr.append(str).append("-");
        }
        StringBuilder needCondimentItemsStr = new StringBuilder();
        for (String str : this.needCondimentItems) {
            needCondimentItemsStr.append(str).append("-");
        }
        return "needCookingItemsStr" + needCookingItemsStr.toString() + "\n"
                + "needCondimentItemsStr:" + needCondimentItemsStr.toString();
    }

    public boolean isComplianceItemsWithMenu(ItemStackHandler cookingItems, ItemStackHandler condimentItems) {
        List<String> cookingItemList = new ArrayList<>();
        for (int i = 0; i < cookingItems.getSlots(); i++) {
            cookingItemList
                    .add(Objects.requireNonNull(cookingItems.getStackInSlot(i).getItem().getRegistryName()).getPath());
        }
        List<String> condimentItemList = new ArrayList<>();
        for (int i = 0; i < condimentItems.getSlots(); i++) {
            condimentItemList.add(
                    Objects.requireNonNull(condimentItems.getStackInSlot(i).getItem().getRegistryName()).getPath());
        }
        return isComplianceNeedCondimentItems(condimentItemList) && isComplianceNeedCookingItems(cookingItemList);
    }

    public boolean isComplianceNeedCondimentItems(List<String> strings) {
        for (String str : this.needCondimentItems) {
            if (!strings.contains(str))
                return false;
        }
        return true;
    }

    public boolean isComplianceNeedCookingItems(List<String> strings) {
        for (String str : this.needCookingItems) {
            if (!strings.contains(str))
                return false;
        }
        return true;
    }

    public boolean isCanChangeItem(String str, String itemStr) {
        for (CanChangeCookingItem canChangeCookingItem : this.canChangeCookingItems) {
            if (!canChangeCookingItem.canChangeItem(str, itemStr))
                return false;
        }
        return true;
    }

    public static class CanChangeCookingItem {
        public String cookingItem;
        public List<String> canChanges;

        public CanChangeCookingItem(String cookingItem, String... canChanges) {
            this.cookingItem = cookingItem;
            this.canChanges = Arrays.asList(canChanges);
        }

        public boolean canChangeItem(String str, String itemStr) {
            if (str.equals(cookingItem)) {
                return canChanges.contains(itemStr);
            }
            return false;
        }
    }
}
