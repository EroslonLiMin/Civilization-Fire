package com.daylight.civilization_fire.common.content.menu.cooking;

import com.daylight.civilization_fire.common.content.block.cooking.IronPotBlock;
import com.daylight.civilization_fire.common.content.item.cooking.CondimentItem;
import com.daylight.civilization_fire.common.content.register.CivilizationMenuTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class IronPotMenu extends CivilizationCookingMenu<IronPotBlock.IronPotBlockEntity> {
    public IronPotMenu(Inventory inventory, int pContainerId, IronPotBlock.IronPotBlockEntity ironPotBlockEntity) {
        super(CivilizationMenuTypes.IRON_POT_MENU.get(), pContainerId,inventory,ironPotBlockEntity);

    }

    public void addSlots(){
        layoutPlayerInventorySlots(28, 134);
        //添加3*3方格农作物
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.addSlot(this.blockEntity.cookingStacksItemStackHandler, j + i * 3, 82 + j * 18, 17 + i * 18);
            }
        }

        //添加调料
        for (int i = 0;i < 5;i++){
            this.addSlot(new SlotItemHandler(this.blockEntity.addCondimentItemStackHandler,i,64 + i * 18,89){
                //只能放置调料
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return stack.getItem() instanceof CondimentItem && super.mayPlace(stack);
                }
            });
        }

        this.addSlot(this.blockEntity.toolsItemStackHandler,0,20,89);
        this.addSlot(this.blockEntity.toolsItemStackHandler,1,38,89);

        this.addSlot(this.blockEntity.outputItemStackHandler,0,153,35);
    }



}