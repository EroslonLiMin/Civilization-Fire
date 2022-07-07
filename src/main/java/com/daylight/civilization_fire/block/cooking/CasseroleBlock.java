package com.daylight.civilization_fire.block.cooking;

import com.daylight.civilization_fire.registry.BlockEntityRegistry;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//砂锅
public class CasseroleBlock extends BaseEntityBlock {
    public CasseroleBlock() {
        super(Properties.of(Material.STONE).strength(1F).noOcclusion().sound(SoundType.ANVIL).requiresCorrectToolForDrops());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CasseroleBlockEntity(pos,state);
    }
    //修改渲染类型
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        CasseroleBlockEntity casseroleBlockEntity =(CasseroleBlockEntity) pLevel.getBlockEntity(pPos);
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if(itemStack.getItem() != Items.AIR) {
            assert casseroleBlockEntity != null;
            casseroleBlockEntity.cookingStacks.add(itemStack);
            pPlayer.getItemInHand(pHand).shrink(1);
        }

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    public static class CasseroleBlockEntity extends CookingBlockEntity{
        public int cookingHeight;//烹饪时候菜品落下的高度(颠锅
        public CasseroleBlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityRegistry.CASSEROLE_BLOCK_ENTITY.get(), pos, state);
        }
    }

    //block entity渲染处理
    @OnlyIn(Dist.CLIENT)
    public static class CasseroleBlockBER implements BlockEntityRenderer<CookingBlockEntity>{
        public CasseroleBlockBER(BlockEntityRendererProvider.Context context) {

        }

        //主要是在锅里把玩家放入的菜品都渲染进去
        @Override
        public void render(CookingBlockEntity cookingBlockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int combinedLightIn, int combinedOverlayIn) {
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            for(int i = 0;i < cookingBlockEntity.cookingStacks.size();i++) {
                poseStack.pushPose();
                //乱七八糟的处理...
                double v = i % 2 == 0 ? -(0.15 + (i / 10.0)) : 0.15 + (i / 10.0);
                double v1 = 1.25 + v;
                double v2 = 0.5 + v;
                poseStack.translate(v1,i * 0.03, v2);
                poseStack.scale(0.5F,0.5F,0.5F);
                poseStack.mulPose(Vector3f.XP.rotationDegrees(90));
                poseStack.translate(-v1,-(i * 0.03), -(v2));
                BakedModel bakedModel = itemRenderer.getModel(cookingBlockEntity.cookingStacks.get(i), cookingBlockEntity.getLevel(), null, 0);
                itemRenderer.render(cookingBlockEntity.cookingStacks.get(i), ItemTransforms.TransformType.FIXED, true, poseStack, multiBufferSource, combinedLightIn, combinedOverlayIn, bakedModel);
                poseStack.popPose();
            }
        }
    }
}