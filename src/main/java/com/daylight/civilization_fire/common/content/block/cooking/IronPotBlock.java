package com.daylight.civilization_fire.common.content.block.cooking;

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

import net.minecraft.nbt.CompoundTag;
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
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

//砂锅
public class IronPotBlock extends BaseEntityBlock {
    public IronPotBlock() {
        super(Properties.of(Material.STONE).strength(1F).noOcclusion().sound(SoundType.ANVIL)
                .requiresCorrectToolForDrops());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new IronPotBlockEntity(pos, state);
    }

    //修改渲染类型
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    //tick
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
                                                                  BlockEntityType<T> entityType) {
        return createTickerHelper(entityType, CivilizationFireBlockEntities.IRON_POT_BLOCK_ENTITY.get(),
                CookingBlockEntity::tick);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand,
                                 BlockHitResult pHit) {
        if (!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND) {
            IronPotBlockEntity ironPotBlockEntity = (IronPotBlockEntity) pLevel.getBlockEntity(pPos);
            NetworkHooks.openGui((ServerPlayer) pPlayer, ironPotBlockEntity, pPos);
        }
        return InteractionResult.SUCCESS;
    }

    public static class IronPotBlockEntity extends CookingBlockEntity implements MenuProvider {
        public ItemStackHandler toolsItemStackHandler = new ItemStackHandler(2);
        public IronPotBlockEntity(BlockPos pos, BlockState state) {
            super(CivilizationFireBlockEntities.IRON_POT_BLOCK_ENTITY.get(), pos, state);
        }

        @Override
        public boolean isComplianceCookingTool(CookingTool cookingTool) {
            return cookingTool == CookingTool.IronPot;
        }

        @Override
        public CompoundTag saveOthersCompoundTag() {
            CompoundTag compoundTag = super.saveOthersCompoundTag();
            compoundTag.put("toolsItemStackHandler",toolsItemStackHandler.serializeNBT());
            return compoundTag;
        }

        @Override
        public void loadOthersCompoundTag(CompoundTag compoundTag) {
            toolsItemStackHandler.deserializeNBT(compoundTag.getCompound("toolsItemStackHandler"));
            super.loadOthersCompoundTag(compoundTag);
        }


        @Override
        public Component getDisplayName() {
            return new TranslatableComponent("iron_pot.block_entity");
        }

        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player player) {
            return new IronPotMenu(pInventory, pContainerId, this);
        }
    }

    //block entity渲染处理
    @OnlyIn(Dist.CLIENT)
    public static class CasseroleBlockBER implements BlockEntityRenderer<IronPotBlockEntity> {
        public CasseroleBlockBER(BlockEntityRendererProvider.Context context) {

        }

        //主要是在锅里把玩家放入的菜品都渲染进去
        @Override
        public void render(IronPotBlockEntity cookingBlockEntity, float partialTicks, PoseStack poseStack,
                           MultiBufferSource multiBufferSource, int combinedLightIn, int combinedOverlayIn) {
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            for (int i = 0; i < cookingBlockEntity.cookingStacksItemStackHandler.getSlots(); i++) {

                poseStack.pushPose();
                //乱七八糟的处理...e
                double v1 = 1.25 - 0.1;
                double v2 = 0.5 - 0.1;
                poseStack.translate(v1, i * 0.03 + ( (i / 100.0)), v2);
                poseStack.scale(0.5F, 0.5F, 0.5F);
                poseStack.mulPose(Vector3f.XP.rotationDegrees(90));
                poseStack.translate(-v1, -(i * 0.03), -(v2));
                BakedModel bakedModel = itemRenderer.getModel(cookingBlockEntity.cookingStacksItemStackHandler.getStackInSlot(i),
                        cookingBlockEntity.getLevel(), null, 0);
                itemRenderer.render(cookingBlockEntity.cookingStacksItemStackHandler.getStackInSlot(i), ItemTransforms.TransformType.FIXED, true,
                        poseStack, multiBufferSource, combinedLightIn, combinedOverlayIn, bakedModel);
                poseStack.popPose();
            }
        }
    }
}