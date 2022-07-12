package com.daylight.civilization_fire.common.content.menu.cooking;

import com.daylight.civilization_fire.common.content.block.cooking.IronPotBlock;
import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
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
        //添加3*3方格农作物
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.addSlot(new SlotItemHandler(this.blockEntity.cookingStacks, j + i * 3, 30 + j * 18, 17 + i * 18){
                    //只能放置农作物
                    @Override
                    public boolean mayPlace(@NotNull ItemStack stack) {
                        return stack.getItem() instanceof PlantItem && super.mayPlace(stack);
                    }
                });
            }
        }

        //添加调料
        for (int i = 0;i < 5;i++){
            this.addSlot(new SlotItemHandler(addCondimentItemStackHandler,i,50,100 + i * 18){
                //只能放置调料
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return stack.getItem() instanceof CondimentItem && super.mayPlace(stack);
                }
            });
        }

        this.addSlot(this.blockEntity.toolsItemStackHandler,0,10,10);
        this.addSlot(this.blockEntity.toolsItemStackHandler,1,10,28);
    }
}
