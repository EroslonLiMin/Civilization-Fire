package com.daylight.civilization_fire.common.content.block.cooking;

import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
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

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand,
            BlockHitResult pHit) {
        IronPotBlockEntity ironPotBlockEntity = (IronPotBlockEntity) pLevel.getBlockEntity(pPos);
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if (itemStack.getItem() != Items.AIR && (itemStack.getItem() instanceof PlantItem.PlantFruitItem
                || itemStack.getItem() instanceof PlantItem.PlantBlockItem)) {
            assert ironPotBlockEntity != null;
            ironPotBlockEntity.cookingStacks.add(itemStack);
            pPlayer.getItemInHand(pHand).shrink(1);
        }

        return InteractionResult.PASS;
    }

    //tick
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
                                                                  BlockEntityType<T> entityType) {
        return createTickerHelper(entityType, CivilizationFireBlockEntities.IRON_POT_BLOCK_ENTITY.get(),
                CookingBlockEntity::tick);
    }

    public static class IronPotBlockEntity extends CookingBlockEntity {
        public float cookingHeight;//烹饪时候菜品落下的高度(颠锅

        public IronPotBlockEntity(BlockPos pos, BlockState state) {
            super(CivilizationFireBlockEntities.IRON_POT_BLOCK_ENTITY.get(), pos, state);
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
            if(cookingBlockEntity.cookingHeight > 0){
                cookingBlockEntity.cookingHeight -= 0.01;
            }
            for (int i = 0; i < cookingBlockEntity.cookingStacks.size(); i++) {

                poseStack.pushPose();
                //乱七八糟的处理...e
                double v1 = 1.25 - 0.1;
                double v2 = 0.5 - 0.1;
                poseStack.translate(v1, i * 0.03 + (cookingBlockEntity.cookingHeight + (i / 100.0)), v2);
                poseStack.scale(0.5F, 0.5F, 0.5F);
                poseStack.mulPose(Vector3f.XP.rotationDegrees(90));
                poseStack.translate(-v1, -(i * 0.03), -(v2));
                BakedModel bakedModel = itemRenderer.getModel(cookingBlockEntity.cookingStacks.get(i),
                        cookingBlockEntity.getLevel(), null, 0);
                itemRenderer.render(cookingBlockEntity.cookingStacks.get(i), ItemTransforms.TransformType.FIXED, true,
                        poseStack, multiBufferSource, combinedLightIn, combinedOverlayIn, bakedModel);
                poseStack.popPose();
            }
        }
    }
}