package com.daylight.civilization_fire.common.content.menu.cooking;

import com.daylight.civilization_fire.common.content.block.cooking.CookingBlockEntity;
import com.daylight.civilization_fire.common.content.menu.CivilizationBaseMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.items.ItemStackHandler;

public abstract class CivilizationCookingMenu<T extends CookingBlockEntity> extends CivilizationBaseMenu {
    public final T blockEntity;
    public final ItemStackHandler addCondimentItemStackHandler = new ItemStackHandler(5);

    public CivilizationCookingMenu(MenuType<?> type, int pContainerId, Inventory inventory, T blockEntity) {
        super(type, pContainerId, inventory);
        this.blockEntity = blockEntity;
        addSlots();
    }

    abstract void addSlots();

    //    public CookingRecipe getCookingRecipe(){
    //
    //    }
}
