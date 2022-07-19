package com.daylight.civilization_fire.common.content.block.agriculture;

import javax.annotation.Nonnull;

import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
import com.daylight.civilization_fire.common.content.item.handler.CivilizationFireItemStackHandler;
import com.daylight.civilization_fire.common.content.menu.agriculture.AgricultureEnchantmentMenu;
import com.daylight.civilization_fire.common.content.register.CivilizationFireBlockEntities;

import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;

/**
 * Argiculture enchantment table
 * @author Heckerpowered
 */
@FieldsAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AgricultureEnchantmentTableBlock extends BaseEntityBlock {

    /**
     * Create a new agriculture enchantment table block
     */
    public AgricultureEnchantmentTableBlock() {
        super(Properties.of(Material.STONE, MaterialColor.COLOR_RED).requiresCorrectToolForDrops().lightLevel(v -> 7));
    }

    @Nonnull
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AgricultureEnchantmentTableBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos location, Player player, InteractionHand hand,
            BlockHitResult hit) {
        //
        // More safety :)
        //
        if (player instanceof ServerPlayer serverPlayer) {
            //
            // Open gui
            //
            NetworkHooks.openGui(serverPlayer, (AgricultureEnchantmentTableBlockEntity) level.getBlockEntity(location),
                    location);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    /**
     * Agriculture enchantment table block entity.
     * @author Heckerpowered
     */
    public static class AgricultureEnchantmentTableBlockEntity extends BlockEntity implements MenuProvider {
        /**
        * Agriculture enchanting requires crops.
        * This handler is used to handle the crops.
        */
        public final ItemStackHandler cropsItemStackHandler = new CivilizationFireItemStackHandler(4,
                (slot, itemStack) -> itemStack.getItem() instanceof PlantItem.PlantFruitItem);

        /**
         * Input item stack handler, only enchantable item can put into this slot.
         */
        public final ItemStackHandler inpuItemStackHandler = new CivilizationFireItemStackHandler(1,
                (slot, itemStack) -> itemStack.isEnchantable());

        /**
         * Create new agriculture enchantment table block entity.
         *
         * @param location The location of agriculture enchantment table block.
         * @param blockState The block state of agriculture enchantment table block.
         */
        public AgricultureEnchantmentTableBlockEntity(final BlockPos location, final BlockState blockState) {
            super(CivilizationFireBlockEntities.AGRICULTURE_ENCHANTMENT_TABLE_BLOCK_ENTITY.get(), location, blockState);
        }

        @Override
        public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
            return new AgricultureEnchantmentMenu(containerId, playerInventory, this);
        }

        @Override
        public Component getDisplayName() {
            return new TranslatableComponent("block.civilization_fire.agriculture_enchantment_table.display_name");
        }
    }
}
