package com.daylight.civilization_fire.common.content.menu.agriculture;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.daylight.civilization_fire.common.content.block.agriculture.AgricultureAnvilBlock.AgricultureAnvilBlockEntity;
import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
import com.daylight.civilization_fire.common.content.menu.CivilizationBaseMenu;
import com.daylight.civilization_fire.common.content.register.CivilizationMenuTypes;
import com.daylight.civilization_fire.common.util.CivilizationFireUtil;

import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType.MenuSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Agriculture anvil menu, wich is opened when the player right-clicks the agriculture anvil.
 * <p> Agriculture anvil works as well as vanilla anvil.
 *
 * @see AnvilBlock
 * @see SimpleMenuProvider
 * @author Heckerpowered
 */
@FieldsAreNonnullByDefault
@MethodsReturnNonnullByDefault
public final class AgricultureAnvilMenu extends CivilizationBaseMenu {

    /**
     * Item's name to change.
     * @see StringUtils#isBlank(CharSequence)
     */
    @Nullable
    private String itemName;

    /**
     * Agriculture anvil's block entity.
     */
    private final AgricultureAnvilBlockEntity entity;

    /**
     * Called when slots changed.
     */
    private final List<Consumer<Integer>> onContentsChanged = new ArrayList<>();

    /**
     * Create a new agriculture anvil menu, do not call this constructor manually.
     *
     * @param containerId The container id.
     * @param inventory The player inventory.
     * @see MenuSupplier
     */
    public AgricultureAnvilMenu(final int containerId, @Nonnull final Inventory inventory,
            @Nonnull final AgricultureAnvilBlockEntity entity) {
        super(CivilizationMenuTypes.AGRICULTURE_ANVIL_MENU.get(), containerId, inventory);
        this.entity = entity;

        //
        // Add player's inventory slots.
        //
        layoutPlayerInventorySlots(28, 134);

        //
        // Add 4 fruit input slots and 1 item input slot.
        //

        //
        // Top-left
        //
        addSlot(entity.cropsItemStackHandler, 0, 91, 83);

        //
        // Top-right
        //
        addSlot(entity.cropsItemStackHandler, 1, 109, 83);

        //
        // Bottom-left
        //
        addSlot(entity.cropsItemStackHandler, 2, 91, 101);

        //
        // Bottom-right
        //
        addSlot(entity.cropsItemStackHandler, 3, 109, 101);

        //
        // Add item input slot.
        //
        addSlot(entity.inpuItemStackHandler, 0, 78, 27);

        //
        // Right (result)
        //
        addSlot(new SlotItemHandler(entity.inpuItemStackHandler, 1, 122, 27) {
            /**
            * Check if the stack is allowed to be placed in this slot,
            * used for armor slots as well as furnace fuel.
            * <p> This function always returns false so that player
            * can not place stack in the result slot.
            *
            * @param stack Stack to be placed.
            * @return Always returns {@code false}, because player
            * cannot place items in the result slot.
            */
            @Override
            public final boolean mayPlace(@Nonnull final ItemStack stack) {
                //
                // Reject player to place stack here.
                //
                return false;
            }

            /**
             * Return whether this slot's stack can be taken from this slot.
             *
             * @param player The player who is trying to take the stack.
             * @return Return {@code true} if the stack can be taken, {@code false} otherwise.
             * @see AgricultureAnvilMenu#mayPickup(Player, boolean)
             */
            @Override
            public final boolean mayPickup(@Nonnull final Player player) {
                //
                // Check if the player can pickup the stack.
                //
                return AgricultureAnvilMenu.this.mayPickup(player, hasItem());
            }

            /**
             * Take the stack from this slot.
             *
             * @param player The player who is trying to take the stack.
             * @param stack The stack to be taken.
             */
            @Override
            public final void onTake(@Nonnull final Player player, @Nonnull final ItemStack stack) {
                AgricultureAnvilMenu.this.onTake(player, stack);
            }
        });

        entity.cropsItemStackHandler.onContentsChanged(slot -> {
            onContentsChanged(slot);
        });

        entity.inpuItemStackHandler.onContentsChanged(slot -> {
            onContentsChanged(slot);
        });
    }

    /**
     * Called when contents changed.
     *
     * @param slot The slot that changed.
     */
    private void onContentsChanged(@Nonnegative final int slot) {
        createResult();

        //
        // Active all listeners.
        //
        for (final var handler : onContentsChanged) {
            handler.accept(slot);
        }
    }

    /**
     * Add a callback to be called when contents changed.
     *
     * @param handler The callback to be called.
     */
    public void onContentsChanged(@Nonnull final Consumer<Integer> handler) {
        onContentsChanged.add(handler);
    }

    /**
     * Called when the anvil input slot changes,
     * calculates the new result and puts it in the output slot.
     * <p> Each fruit can provide the same experience as one percent
     * of growth time of the fruit, the item will also be repaired
     * with the same durability value.
     *
     * @see AnvilMenu#setItemName(String)
     * @see ItemCombinerMenu#slotsChanged(Container)
     */
    public final void createResult() {
        //
        // Get input stack.
        //
        final var inputStack = entity.inpuItemStackHandler.getStackInSlot(0);

        //
        // Check if item name is presented.
        //
        final var itemNamePresented = StringUtils.isNotBlank(itemName);

        //
        // Get total growth time of the fruits.
        //
        final var totalGrowthTime = getTotalGrowthTime();

        //
        // Result slot will be set to empty if any of following conditions is true:
        // - input stack is empty.
        // - supplied fruits are less than cosst.
        // - stack is not damaged and hasn't been renamed.
        //
        if ((inputStack.isEmpty() || getTotalCost() > totalGrowthTime || totalGrowthTime == 0
                || inputStack.getDamageValue() == 0) && !itemNamePresented) {
            entity.inpuItemStackHandler.setStackInSlotUnchecked(1, ItemStack.EMPTY);
        } else {
            //
            // Create result stack.
            //
            final var resultStack = inputStack.copy();
            resultStack.setCount(1);
            resultStack.setDamageValue(resultStack.getDamageValue() - toDurability(totalGrowthTime));

            if (itemNamePresented) {
                resultStack.setHoverName(new TextComponent(itemName));
            }

            entity.inpuItemStackHandler.setStackInSlotUnchecked(1, resultStack);
        }

        broadcastChanges();
    }

    /**
     * Get the block entity of the menu.
     *
     * @return {@link AgricultureAnvilBlockEntity} Block entity.
     */
    public final AgricultureAnvilBlockEntity getEntity() {
        return entity;
    }

    /**
    * If player can build instantly (creative mode), or the player has enough experience,
    * they can pick the stack (result) from this slot({@link #resultSlots}).
    * <p> The only exception is the cost is zero, that means the result is empty,
    * if the cost is zero, the player can not pick the stack from the result slot,
    * and the result slot is empty (so idk what the purpose of returing false is).
    * see also {@link AnvilMenu#mayPickup}. <p> However, this is for the vanilla anvil
    * (because i realized i wrote it wrong halfway through, and I didn't want to rewrite it),
    * agriculture anvil do not consume experience, but use crops instead, with a conversion
    * efficiency of 1:100.
    *
    * @param player The player who pick the stack from this slot.
    * @param hasStack Indicates if this slot contains a stack, usually {@link Slot#hasItem()}
    * @return Returns {@code true} if this slot's stack can be taken from this slot.
    */
    private final boolean mayPickup(@Nonnull final Player player, final boolean hasStack) {
        return (player.getAbilities().instabuild || getTotalGrowthTime() >= getTotalCost()) && getTotalCost() > 0;
    }

    /**
     * Take the result stack from this slot.
     *
     * @param player The player who is trying to take the stack.
     * @param stack The stack to be taken.
     */
    private final void onTake(@Nonnull final Player player, @Nonnull final ItemStack stack) {
        //
        // Determine whether player can build instantly (creative mode),
        //
        if (!player.getAbilities().instabuild) {
            //
            // If the player can not build instantly (creative mode), consume fruits.
            //
            consumeFruits(getTotalCost());
        }

        entity.inpuItemStackHandler.getStackInSlot(0).shrink(1);
        entity.inpuItemStackHandler.getStackInSlot(1).shrink(1);
    }

    /**
     * Get total growth time of fruits in the 4 input slots.
     *
     * @return The total growth time of fruits in the 4 input slots.
     */
    public final int getTotalGrowthTime() {
        int totalGrowthTime = 0;
        for (int i = 0; i < 4; i++) {
            //
            // Get stack in the specificed input slot.
            //
            final var stack = entity.cropsItemStackHandler.getStackInSlot(i);

            //
            // Determine whether item is presented in this slot.
            //
            if (!stack.isEmpty()) {
                //
                // Get the item of the class
                //
                final var item = stack.getItem();

                //
                // Determine whether the item is a fruit.
                //
                if (item instanceof PlantItem.PlantFruitItem fruit) {
                    //
                    // Get the fruit's growth time, it is optional, some fruit
                    // may not have growth time, they can not be used as valid input.
                    //
                    final var growTime = CivilizationFireUtil.getPlantGrowTime(fruit);

                    //
                    // Determine whether the fruit has growth time, fruit without
                    // growth time can not be used to repair items.
                    //
                    if (growTime.isPresent()) {
                        //
                        // Add the growth time to the total growth time.
                        //
                        totalGrowthTime += growTime.get() * stack.getCount();
                    }
                }
            }
        }

        return totalGrowthTime;
    }

    /**
     * Convert the growth time to the experience with the conversion efficiency of 1:100.
     *
     * @param growthTime The growth time of the fruit, this parameter cannot be negative.
     * @return The experenice value of the fruits.
     */
    public static final int toExperience(@Nonnegative final int growthTime) {
        return growthTime / 100;
    }

    /**
     * Convert fruit's growth time to item's durability with
     * an effective of 1:100.
     *
     * @param growthTime The growth time of the fruit, this parameter cannot be negative.
     * @return The durability of item.
     */
    public static final int toDurability(@Nonnegative final int growthTime) {
        return growthTime / 100;
    }

    /**
     * Get the total cost to take up the result.
     * @return The total cost to take up the result.
     */
    public final int getTotalCost() {
        int totalCost = 0;

        //
        // Get the input stack.
        //
        final var inputStack = entity.inpuItemStackHandler.getStackInSlot(0);
        if (inputStack.isEmpty() || !inputStack.isDamageableItem()) {
            return 0;
        }

        totalCost += inputStack.getDamageValue() * 100;

        //
        // If the total growth time is less than total cost,
        // set the total cost to total growth time, so that
        // the item won't be fully repaired at a time.
        //
        if (totalCost > getTotalGrowthTime()) {
            totalCost = getTotalGrowthTime();
        }

        //
        // Determine whether the item name is changed.
        //
        if (StringUtils.isNotBlank(itemName)) {
            totalCost += 500;
        }

        return totalCost;
    }

    /**
     * Used by the Anvil GUI to update the Item Name being typed by the player
     *
     * @param name Item's name, this parameter can be null.
     */
    public final void setItemName(@Nullable final String name) {
        itemName = name;
        createResult();
    }

    /**
     * Consume fruits after reparing or renaming the item.
     *
     * @param cost The total cost to take up the result.
     * @see #getTotalCost()
     */
    public final void consumeFruits(@Nonnull int cost) {
        for (int i = 0; i < 4 && cost != 0; i = -~i) {
            //
            // Get the stack in the slot.
            //
            final var stack = entity.cropsItemStackHandler.getStackInSlot(i);

            //
            // Determine whether the stack is empty.
            //
            if (stack.isEmpty()) {
                //
                // Next slot.
                //
                continue;
            }

            //
            // Get the item of the stack.
            //
            final var item = stack.getItem();

            //
            // Determine whether the input is fruit.
            //
            if (item instanceof PlantItem.PlantFruitItem fruit) {
                //
                // Get the fruit's growth time, it is optional, some fruit
                // may not have growth time, they can not be used as valid input.
                //
                final var growTime = CivilizationFireUtil.getPlantGrowTime(fruit);

                //
                // Determine whether the fruit has growth time, fruit without
                // growth time can not be used to repair items.
                //
                if (growTime.isPresent()) {
                    //
                    // Get the true growth time.
                    //
                    final var time = growTime.get();

                    //
                    // Determine how much the fruit to be consumed.
                    //
                    final var currentCost = stack.getCount() * time;
                    if (currentCost <= cost) {
                        cost -= currentCost;
                        stack.setCount(0);
                    } else {
                        stack.shrink(cost / time + 1);
                    }
                }
            }
        }
    }
}
