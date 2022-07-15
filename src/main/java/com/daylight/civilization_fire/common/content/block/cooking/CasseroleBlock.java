package com.daylight.civilization_fire.common.content.block.cooking;

import com.daylight.civilization_fire.common.content.menu.cooking.CasseroleMenu;
import com.daylight.civilization_fire.common.content.menu.cooking.IronPotMenu;
import com.daylight.civilization_fire.common.content.recipe.CookingTool;
import com.daylight.civilization_fire.common.content.register.CivilizationFireBlockEntities;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

//砂锅
public class CasseroleBlock extends BaseEntityBlock {
    public CasseroleBlock() {
        super(Properties.of(Material.STONE).strength(1F).noOcclusion().sound(SoundType.STONE)
                .requiresCorrectToolForDrops());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CasseroleBlockEntity(pos, state);
    }

    //修改渲染类型
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    //tick
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
                                                                  BlockEntityType<T> entityType) {
        return createTickerHelper(entityType, CivilizationFireBlockEntities.CASSEROLE_BLOCK_ENTITY.get(),
                CookingBlockEntity::tick);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand,
                                 BlockHitResult pHit) {
        if (!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND) {
            CasseroleBlockEntity ironPotBlockEntity = (CasseroleBlockEntity) pLevel.getBlockEntity(pPos);
            NetworkHooks.openGui((ServerPlayer) pPlayer, ironPotBlockEntity, pPos);
        }
        return InteractionResult.SUCCESS;
    }

    public static class CasseroleBlockEntity extends CookingBlockEntity implements MenuProvider {
        public CasseroleBlockEntity(BlockPos pos, BlockState state) {
            super(CivilizationFireBlockEntities.CASSEROLE_BLOCK_ENTITY.get(), pos, state);
        }

        @Override
        public boolean isComplianceCookingTool(CookingTool cookingTool) {
            return cookingTool == CookingTool.Casserole;
        }

        @Override
        public Component getDisplayName() {
            return new TranslatableComponent("casserole.block_entity");
        }

        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player player) {
            return new CasseroleMenu(pInventory, pContainerId, this);
        }
    }

}