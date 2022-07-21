package com.daylight.civilization_fire.common.content.menu.cooking;

import com.daylight.civilization_fire.common.content.block.cooking.JuicerBlock;
import com.daylight.civilization_fire.common.content.menu.CivilizationBaseMenu;
import com.daylight.civilization_fire.common.content.register.CivilizationFireMenuTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class JuicerMenu extends CivilizationBaseMenu {
    public JuicerBlock.JuicerBlockEntity juicerBlockEntity;
    public JuicerMenu(int pContainerId, Inventory inventory,
                      JuicerBlock.JuicerBlockEntity blockEntity) {
        super(CivilizationFireMenuTypes.JUICER_MENU.get(), pContainerId, inventory);
        this.juicerBlockEntity = blockEntity;
        addSlots();
    }


    void addSlots() {
        layoutPlayerInventorySlots(28, 134);
        this.addSlot(this.juicerBlockEntity.addStackHandler,0,100,28);
        this.addSlot(new SlotItemHandler(this.juicerBlockEntity.addStackHandler,1,100,49){
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.getItem() == Items.GLASS_BOTTLE;
            }
        });

        //output
        this.addSlot(new SlotItemHandler(this.juicerBlockEntity.outputItemStackHandler,0,100,104){
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
        });
    }
}
