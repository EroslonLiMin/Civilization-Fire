package com.daylight.civilization_fire.common.content.menu.agriculture;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.daylight.civilization_fire.common.content.block.agriculture.AgricultureAnvil;
import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
import com.daylight.civilization_fire.common.content.register.CivilizationMenuTypes;
import com.daylight.civilization_fire.common.util.CivilizationFireUtil;

import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.MenuType.MenuSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;

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
public final class AgricultureAnvilMenu extends ItemCombinerMenu {

    /**
     * Item's name to change.
     * @see StringUtils#isBlank(CharSequence)
     */
    @Nullable
    private String itemName;

    /**
     * Experience needed to enchant, rename or repair the item.
     * <p> Ignore if the player can build instantly.
     * @see Abilities#instabuild
     */
    private final DataSlot cost = DataSlot.standalone();

    /**
     * Create a new agriculture anvil menu, do not call this constructor manually.
     *
     * @param containerId The container id.
     * @param playerInventory The player inventory.
     * @see MenuSupplier
     */
    public AgricultureAnvilMenu(final int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, ContainerLevelAccess.NULL);
    }

    /**
     * Create a new agriculture anvil menu.
     *
     * @param containerId The container id.
     * @param playerInventory The player's inventory {@link Player#getInventory()}
     * @param access The container level access {@link AnvilBlock#getMenuProvider(BlockState, Level, BlockPos)}
     * @see AgricultureAnvil#getMenuProvider(BlockState, Level, BlockPos)
    */
    public AgricultureAnvilMenu(final int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(CivilizationMenuTypes.AGRICULTURE_ANVIL_MENU.get(), containerId, playerInventory, access);
        addDataSlot(cost);
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
    @Override
    public void createResult() {
        //
        // Get input item.
        //
        final var left = inputSlots.getItem(0);
        if (left.isEmpty()) {
            //
            // Clear output slot.
            //
            resultSlots.setItem(0, ItemStack.EMPTY);
        } else {
            //
            // Copy the primary input item.
            //
            final var leftCopy = left.copy();

            //
            // Get the secondary input item stack (maybe enchantment books or fruits)
            //
            final var right = inputSlots.getItem(1);

            //
            // Determine whether the secondary input item stack is empty,
            // if it is, we only need to change item's name, otherwise
            // we need to repair or enchant it.
            //
            if (!right.isEmpty()) {
                final var leftItem = left.getItem();

                //
                // Determine whether the primary input item stack has durability,
                // and it has been damaged. It also needs a fruit in the secondary
                // input slot as repair cost.
                //
                if (leftItem instanceof PlantItem.PlantFruitItem fruit && leftCopy.isDamageableItem()
                        && leftCopy.getDamageValue() > 0) {
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
                        leftCopy.setDamageValue(leftCopy.getDamageValue() - growTime.get() / 100);
                    }
                }

                //
                // Get all enchantmetns on the item.
                //
                final var enchantments = EnchantmentHelper.getEnchantments(right);

                //
                // Apply enchantments on primary input item.
                //
                for (@Nullable
                final var enchantment : enchantments.keySet()) {
                    //
                    // Enchantment may be null (unusually)
                    //
                    if (enchantment == null) {
                        continue;
                    }

                    //
                    // Get enchantment level, minimum 0, and determine whether current
                    // enchantment level is greater than its maxium enchantment level.
                    //
                    final var enchantmentLevel = Math.max(Math.max(0, enchantments.get(enchantment)),
                            enchantment.getMaxLevel());

                    //
                    // Determine whether the enchantment is presented.
                    //
                    if (enchantmentLevel > 0 && enchantment.canEnchant(leftCopy)) {
                        //
                        // Determine whether current enchantment level is greater than
                        // its maxium enchantment level.
                        //
                        leftCopy.enchant(enchantment, enchantmentLevel);
                    }
                }
            }
        }

        if (StringUtils.isBlank(itemName)) {

        }
    }

    @Override
    protected boolean mayPickup(Player pPlayer, boolean pHasStack) {
        return false;
    }

    @Override
    protected void onTake(Player p_150601_, ItemStack p_150602_) {

    }

    @Override
    protected boolean isValidBlock(BlockState pState) {
        return false;
    }

}
