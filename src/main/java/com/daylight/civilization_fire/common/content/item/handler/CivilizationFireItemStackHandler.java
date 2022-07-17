package com.daylight.civilization_fire.common.content.item.handler;

import java.util.Objects;
import java.util.function.BiPredicate;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
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

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return predicate.test(slot, stack);
    }
}
