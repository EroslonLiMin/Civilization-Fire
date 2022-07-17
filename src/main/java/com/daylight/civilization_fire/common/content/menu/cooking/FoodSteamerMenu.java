package com.daylight.civilization_fire.common.content.menu.cooking;

import com.daylight.civilization_fire.common.content.block.cooking.FoodSteamerBlock;
import com.daylight.civilization_fire.common.content.item.cooking.CondimentItem;
import com.daylight.civilization_fire.common.content.register.CivilizationFireMenuTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class FoodSteamerMenu extends CivilizationCookingMenu<FoodSteamerBlock.FoodSteamerBlockEntity> {
    public FoodSteamerMenu(Inventory inventory, int pContainerId,
            FoodSteamerBlock.FoodSteamerBlockEntity foodSteamerBlockEntity) {
        super(CivilizationFireMenuTypes.FOOD_STEAMER_MENU.get(), pContainerId, inventory, foodSteamerBlockEntity);

    }

    public void addSlots() {
        layoutPlayerInventorySlots(28, 134);
        //添加农作物格子
        for (int i = 0; i < 4; ++i) {
            this.addSlot(this.blockEntity.cookingStacksItemStackHandler, i, 73 + i * 18, 31);
        }
        for (int i = 0; i < 5; ++i) {
            this.addSlot(this.blockEntity.cookingStacksItemStackHandler, 4 + i, 64 + i * 18, 49);
        }
        //添加调料
        for (int i = 0; i < 5; i++) {
            this.addSlot(new SlotItemHandler(this.blockEntity.addCondimentItemStackHandler, i, 64 + i * 18, 76) {
                //只能放置调料
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return stack.getItem() instanceof CondimentItem && super.mayPlace(stack);
                }
            });
        }

        this.addSlot(this.blockEntity.toolsItemStackHandler, 0, 162, 76);
        this.addSlot(this.blockEntity.outputItemStackHandler, 0, 100, 13);
    }

}