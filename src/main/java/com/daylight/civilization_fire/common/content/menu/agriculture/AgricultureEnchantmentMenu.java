package com.daylight.civilization_fire.common.content.menu.agriculture;

import com.daylight.civilization_fire.common.content.block.agriculture.AgricultureEnchantmentTableBlock.AgricultureEnchantmentTableBlockEntity;
import com.daylight.civilization_fire.common.content.menu.CivilizationBaseMenu;
import com.daylight.civilization_fire.common.content.register.CivilizationFireMenuTypes;

import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.entity.player.Inventory;

/**
 * Agriculture enchantment table's menu, which is opened when the player right-clicks the block.
 * Used to handle gui's interaction logic.
 *
 * @author Heckerpowered
 * @see com.daylight.civilization_fire.common.content.block.agriculture.AgricultureEnchantmentTableBlock
 * @see com.daylight.civilization_fire.common.content.block.agriculture.AgricultureEnchantmentTableBlock.AgricultureEnchantmentTableBlockEntity
 * @see com.daylight.civilization_fire.client.screen.agriculture.AgricultureEnchantmentScreen
 * @see com.daylight.civilization_fire.common.content.block.agriculture.AgricultureEnchantmentTableBlock#getMenuProvider(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.Level, net.minecraft.core.BlockPos)
 */
@FieldsAreNonnullByDefault
@MethodsReturnNonnullByDefault
public final class AgricultureEnchantmentMenu extends CivilizationBaseMenu {
    /**
     * Agriculture enchantment table's block entity.
     */
    private final AgricultureEnchantmentTableBlockEntity entity;

    public AgricultureEnchantmentMenu(final int containerId, final Inventory inventory,
            final AgricultureEnchantmentTableBlockEntity entity) {
        super(CivilizationFireMenuTypes.AGRICULTURE_ENCHANTMENT_MENU.get(), containerId, inventory);
        this.entity = entity;

        //
        // Add player's inventory, the parameter is the top left
        // corner of the image of the inventory.
        //
        layoutPlayerInventorySlots(28, 134);

        //
        // Add 4 slots to handle crops input, and 1 slot to handle item input,
        // the input item must be enchantable, the item stack handler will
        // check the validation of the item. See also "CivilizationFireItemStackHandler".
        //

        //
        // Top-left
        //
        addSlot(entity.cropsItemStackHandler, 0, 28, 74);

        //
        // Top-right
        //
        addSlot(entity.cropsItemStackHandler, 1, 46, 74);

        //
        // Bottom-right
        //
        addSlot(entity.cropsItemStackHandler, 2, 46, 92);

        //
        // Bottom-left
        //
        addSlot(entity.cropsItemStackHandler, 3, 28, 92);

        //
        // Add input slot to handle item input, the input item must be enchantable.
        //
        addSlot(entity.inpuItemStackHandler, 0, 44 - 7, 39 - 8);
    }

    /**
     * Get agriculture enchantment's block entity.
     * @return Block entity.
     */
    public final AgricultureEnchantmentTableBlockEntity getEntity() {
        return entity;
    }
}
