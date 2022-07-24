package com.daylight.civilization_fire.common.content.item.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

/**
 * Civilization fire's item stack handler, used to limit incoming items.
 * The item stack handler requires a predicate to determine whether
 * imcoming items are valid. Consider using {@link ItemStackHandler} if
 * you need a item stack handler without limits.
 *
 * @author Heckerpowered
 */
@FieldsAreNonnullByDefault
@MethodsReturnNonnullByDefault
public final class CivilizationFireItemStackHandler extends ItemStackHandler {

    /**
     * Determine whether an item is valid.
     */
    private final BiPredicate<Integer, ItemStack> predicate;

    /**
     * Callbacks when contents changed, and ArrayList is spatially continuous.
     */
    private final List<Consumer<Integer>> onContentsChanged = new ArrayList<>();

    /**
     * Create a new item stack handler with the given predicate,
     * the predicate cannot be null, if you want to create a new
     * item stack handler without limits, consider using,
     * {@link ItemStackHandler}, This function will throw {@link NullPointerException}
     * if the predicate is null.
     *
     * @param size The size of the item stack handler
     * @param predicate BiPredicate to determine whether an item is valid
     * @see ItemStackHandler
     */
    public CivilizationFireItemStackHandler(final int size,
            @Nonnull @CheckForNull final BiPredicate<Integer, ItemStack> predicate) {
        super(size);
        this.predicate = Objects.requireNonNull(predicate);
    }

    /**
     * Add a callback when contents changed.
     *
     * @param handler Called when contents changed.
     * @return {@code this}
     */
    public final CivilizationFireItemStackHandler onContentsChanged(@Nonnull final Consumer<Integer> handler) {
        onContentsChanged.add(handler);
        return this;
    }

    @Override
    protected final void onContentsChanged(final int slot) {
        for (final var handler : onContentsChanged) {
            handler.accept(slot);
        }

        super.onContentsChanged(slot);
    }

    @Override
    public final boolean isItemValid(@Nonnegative int slot, ItemStack stack) {
        return predicate.test(slot, stack);
    }

    /**
     * Does not trigger the "onContentsChanged" event, but still checks the validity of the slot.
     *
     * @param slot The slot to set.
     * @param stack The item stack to put into the slot.
     */
    public final void setStackInSlotUnchecked(@Nonnegative final int slot, @Nonnull final ItemStack stack) {
        validateSlotIndex(slot);
        stacks.set(slot, stack);
    }

    /**
     * Get all stack in the item stack handler.
     *
     * @return All stack in the item stack handler.
     */
    public final NonNullList<ItemStack> getStacks() {
        return stacks;
    }
}
