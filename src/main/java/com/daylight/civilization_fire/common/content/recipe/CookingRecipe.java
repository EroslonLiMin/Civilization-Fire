package com.daylight.civilization_fire.common.content.recipe;

import com.daylight.civilization_fire.common.CivilizationFire;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
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
    public CanChangeCookingItem[] canChangeCookingItems;

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

    //设置可以替代的食材
    public CookingRecipe setCanChangeCookingItems(CanChangeCookingItem... canChanges) {
        this.canChangeCookingItems = canChanges;
        return this;
    }

    //获取农作物Item
    public Item[] getNeedCondimentItems() {
        Item[] items = new Item[needCookingItems.length];
        for (int i = 0; i < items.length; i++) {
            String str = needCookingItems[i].contains(":") ? needCookingItems[i]
                    : CivilizationFire.MODID + ":" + needCookingItems[i];
            Optional<Holder<Item>> forgeGetItemHolder = ForgeRegistries.ITEMS.getHolder(new ResourceLocation(str));
            if (forgeGetItemHolder.isPresent()) {
                CivilizationFire.LOGGER.error("Cannot find item's resource key");
                return new Item[0];
            }
            items[i] = forgeGetItemHolder.get().value();
        }
        return items;
    }

    //判断是否需要农作物吻合
    public boolean isComplianceCondimentItems(List<Item> addItems) {
        Item[] items = this.getNeedCookingItems();
        for (int i = 0; i < items.length; i++) {
            if (!addItems.contains(items[i])) {
                return isCanChangeItem(needCondimentItems[i], items[i].getRegistryName().getNamespace());
            }
        }
        return true;
    }

    //获取农作物Item
    public Item[] getNeedCookingItems() {
        Item[] items = new Item[needCookingItems.length];
        for (int i = 0; i < items.length; i++) {
            String str = needCookingItems[i].contains(":") ? needCookingItems[i]
                    : CivilizationFire.MODID + ":" + needCookingItems[i];
            Optional<Holder<Item>> forgeGetItemHolder = ForgeRegistries.ITEMS.getHolder(new ResourceLocation(str));
            if (forgeGetItemHolder.isPresent()) {
                CivilizationFire.LOGGER.error("Cannot find item's resource key");
                return new Item[0];
            }
            items[i] = forgeGetItemHolder.get().value();
        }
        return items;
    }

    //判断是否需要农作物吻合
    public boolean isComplianceNeedCookingItems(List<Item> addItems) {
        Item[] items = this.getNeedCookingItems();
        for (int i = 0; i < items.length; i++) {
            if (!addItems.contains(items[i])) {
                return isCanChangeItem(needCookingItems[i], items[i].getRegistryName().getNamespace());
            }
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
                itemStr = !itemStr.contains("civilization_fire:") ? itemStr : itemStr.substring(itemStr.indexOf(":"));
                return canChanges.contains(itemStr);
            }
            return false;
        }
    }
}
