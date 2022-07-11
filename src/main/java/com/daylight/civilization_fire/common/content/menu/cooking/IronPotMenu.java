package com.daylight.civilization_fire.common.content.menu.cooking;

import com.daylight.civilization_fire.common.content.block.cooking.IronPotBlock;
import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
import com.daylight.civilization_fire.common.content.menu.CivilizationBaseMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class IronPotMenu extends CivilizationBaseMenu {
    private final IronPotBlock.IronPotBlockEntity ironPotBlockEntity;
    private final ItemStackHandler addCondimentItemStackHandler = new ItemStackHandler(5);
    protected IronPotMenu(Inventory inventory, int pContainerId, Level level, BlockPos pos) {
        super(null, pContainerId,inventory);
        this.ironPotBlockEntity = (IronPotBlock.IronPotBlockEntity) level.getBlockEntity(pos);
        addSlots();
    }

    void addSlots(){
        //添加3*3方格农作物
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.addSlot(new SlotItemHandler(ironPotBlockEntity.cookingStacks, j + i * 3, 30 + j * 18, 17 + i * 18){
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
            this.addSlot(new SlotItemHandler(addCondimentItemStackHandler,10 + i,50,100 + i * 18));
        }
    }
}
