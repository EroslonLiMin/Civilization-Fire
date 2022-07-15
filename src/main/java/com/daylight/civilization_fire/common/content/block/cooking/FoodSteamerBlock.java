package com.daylight.civilization_fire.common.content.block.cooking;

import com.daylight.civilization_fire.common.content.menu.cooking.FoodSteamerMenu;
import com.daylight.civilization_fire.common.content.recipe.CookingTool;
import com.daylight.civilization_fire.common.content.register.CivilizationFireBlockEntities;
import com.daylight.civilization_fire.common.content.register.CivilizationFireBlocks;
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
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class FoodSteamerBlock extends BaseEntityBlock {
    public FoodSteamerBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE).strength(1F).noOcclusion().sound(SoundType.WOOD)
                .requiresCorrectToolForDrops());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FoodSteamerBlockEntity(pos, state);
    }

    //修改渲染类型
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    //tick
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
            BlockEntityType<T> entityType) {
        return createTickerHelper(entityType, CivilizationFireBlockEntities.FOOD_STEAMER_BLOCK_ENTITY.get(),
                FoodSteamerBlockEntity::tick);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand,
            BlockHitResult pHit) {
        if (!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND) {
            FoodSteamerBlockEntity foodSteamerBlockEntity = (FoodSteamerBlockEntity) pLevel.getBlockEntity(pPos);
            NetworkHooks.openGui((ServerPlayer) pPlayer, foodSteamerBlockEntity, pPos);
        }
        return InteractionResult.SUCCESS;
    }

    public static class FoodSteamerBlockEntity extends CookingBlockEntity implements MenuProvider {
        public FoodSteamerBlockEntity(BlockPos pos, BlockState state) {
            super(CivilizationFireBlockEntities.FOOD_STEAMER_BLOCK_ENTITY.get(), pos, state);
        }

        @Override
        public boolean isComplianceCookingTool(CookingTool cookingTool) {
            return cookingTool == CookingTool.FoodSteamer;
        }

        //tick进行刷新
        public static void tick(Level level, BlockPos pos, BlockState blockState,
                CookingBlockEntity cookingBlockEntity) {
            if (level.getBlockState(pos.above()).getBlock() == CivilizationFireBlocks.FOOD_STEAMER_HAT_BLOCK.get()) {
                CookingBlockEntity.tick(level, pos, blockState, cookingBlockEntity);
            }
        }

        @Override
        public Component getDisplayName() {
            return new TranslatableComponent("food_steamer.block_entity");
        }

        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player player) {
            return new FoodSteamerMenu(pInventory, pContainerId, this);
        }
    }
}
