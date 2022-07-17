package com.daylight.civilization_fire.common.content.menu;

import com.daylight.civilization_fire.common.content.entity.bot.Bot;
import com.daylight.civilization_fire.common.content.item.agriculture.BotAddItem;
import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class BotMenu extends CivilizationBaseMenu {
    public final Bot bot;
    public final Inventory inventory;
    public ItemStackHandler addItemStackHandler = new ItemStackHandler(4);

    public BotMenu(int pContainerId, Inventory inventory, Bot bot) {
        super(null, pContainerId, inventory);
        this.inventory = inventory;
        this.bot = bot;
        addSlots();
    }

    public void addSlots() {

        this.layoutPlayerInventorySlots(100, 33);
        //add slots
        for (int i = 0; i < 3; i++) {
            this.addSlot(new SlotItemHandler(addItemStackHandler, i, 18 + i * 18, 116) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return super.mayPlace(stack) && stack.getItem() instanceof BotAddItem;
                }
                {
                    if(this.getSlotIndex() == 0) this.set(bot.getEnergyAdd());
                    else if(this.getSlotIndex() == 1) this.set(bot.getMCAttributeAdd());
                    else this.set(bot.getCorrespondingAbilityAdd());
                }

                @Override
                public void set(@NotNull ItemStack stack) {
                    super.set(stack);
                    if(this.getSlotIndex() == 0) bot.setEnergyAdd(stack);
                    else if(this.getSlotIndex() == 1) bot.setMcAttributeAdd(stack);
                    else bot.setCorrespondingAbilityAdd(stack);
                }
            });
        }
        this.addSlot(new SlotItemHandler(addItemStackHandler,3,72,98){
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return super.mayPlace(stack) && stack.getItem() instanceof PlantItem;
            }
            {
                this.set(bot.getFruitAdd());
            }

            @Override
            public void set(@NotNull ItemStack stack) {
                super.set(stack);
                bot.setFruitAdd(stack);
            }
        });
    }
}