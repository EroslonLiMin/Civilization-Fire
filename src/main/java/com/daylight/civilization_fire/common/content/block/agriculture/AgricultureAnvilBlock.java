package com.daylight.civilization_fire.common.content.block.agriculture;

import javax.annotation.Nonnull;

import com.daylight.civilization_fire.common.content.block.HasDropBlock;
import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
import com.daylight.civilization_fire.common.content.item.handler.CivilizationFireItemStackHandler;
import com.daylight.civilization_fire.common.content.menu.agriculture.AgricultureAnvilMenu;
import com.daylight.civilization_fire.common.content.register.CivilizationFireBlockEntities;

import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.NotNull;

/**
 * Agriculture anvil block, used to enchant, repair or rename item with fruits.
 * Compare to vanilla anvil, it only change what is needed to enchant, repair or rename
 * items.
 */
@FieldsAreNonnullByDefault
@MethodsReturnNonnullByDefault
public final class AgricultureAnvilBlock extends HasDropBlock.HasDropBlockBaseEntity implements Fallable {

    /**
     * Container's title, which is displayed in the top of the container.
     */
    private static final Component CONTAINER_TITLE = new TranslatableComponent("container.repair");

    /**
     * Create a new agriculture anvil block.
     */
    public AgricultureAnvilBlock() {
        super(2,BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.METAL).requiresCorrectToolForDrops()
                .strength(5.0F, 1200.0F).sound(SoundType.ANVIL).noOcclusion());
    }
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(@Nonnull final BlockPos location, @Nonnull final BlockState state) {
        return new AgricultureAnvilBlockEntity(location, state);
    }

    @Override
    public InteractionResult use(@Nonnull final BlockState block, @Nonnull final Level level,
                                 @Nonnull final BlockPos location, @Nonnull final Player player, @Nonnull final InteractionHand hand,
                                 @NotNull BlockHitResult hit) {
        if (player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openGui(serverPlayer, (AgricultureAnvilBlockEntity) level.getBlockEntity(location),
                    location);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public void destroy(@Nonnull final LevelAccessor level, @Nonnull final BlockPos location,
            @Nonnull final BlockState block) {
        if (level.getBlockEntity(location) instanceof AgricultureAnvilBlockEntity entity) {

        }
        super.destroy(level, location, block);
    }

    /**
    * Agriculture enchantment table block entity.
    * @author Heckerpowered
    */
    public static class AgricultureAnvilBlockEntity extends BlockEntity implements MenuProvider {
        /**
        * Agriculture enchanting requires crops.
        * This handler is used to handle the crops.
        */
        public final CivilizationFireItemStackHandler cropsItemStackHandler = new CivilizationFireItemStackHandler(4,
                (slot, itemStack) -> itemStack.getItem() instanceof PlantItem.PlantFruitItem);

        /**
         * Input item stack handler, only enchantable item can put into this slot.
         */
        public final CivilizationFireItemStackHandler inpuItemStackHandler = new CivilizationFireItemStackHandler(2,
                (slot, itemStack) -> true);

        /**
         * Create new agriculture enchantment table block entity.
         *
         * @param location The location of agriculture enchantment table block.
         * @param blockState The block state of agriculture enchantment table block.
         */
        public AgricultureAnvilBlockEntity(final BlockPos location, final BlockState blockState) {
            super(CivilizationFireBlockEntities.AGRICULTURE_ANVIL_TABLE_BLOCK_ENTITY.get(), location, blockState);
        }

        @Override
        public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
            return new AgricultureAnvilMenu(containerId, playerInventory, this);
        }

        @Override
        public Component getDisplayName() {
            return CONTAINER_TITLE;
        }

        @Override
        public void setRemoved() {
            Containers.dropContents(level, getBlockPos(), cropsItemStackHandler.getStacks());

            final var location = getBlockPos();
            Containers.dropItemStack(level, location.getX(), location.getY(),
                    location.getZ(), inpuItemStackHandler.getStackInSlot(0));
        }
    }
}
