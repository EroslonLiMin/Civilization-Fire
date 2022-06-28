package com.daylight.civilization_fire.item.agriculture;

import com.daylight.civilization_fire.init.ModGroup;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

//种子物品
public class PlantItem {
    //种植植物
    public static class PlantSeedItem extends Item {
        public ResourceLocation stateID;//种植方块的ID

        public PlantSeedItem(ResourceLocation stateID, boolean canEat, float... eatingValue) {
            //实现以下food，有点草...
            super(new Properties().stacksTo(16).tab(ModGroup.AGRICULTURE_CREATIVE_MODE_TAB).food(canEat ?
                    new FoodProperties.Builder().nutrition((int) eatingValue[0]).saturationMod(eatingValue[1]).build() : null));
            this.stateID = stateID;
        }

        //处理种植
        @Override
        public InteractionResult useOn(UseOnContext useOnContext) {
            Level level = useOnContext.getLevel();
            BlockPos abovePos = useOnContext.getClickedPos().above();
            BlockState aboveState = level.getBlockState(abovePos);
            //玩家不能不存在!
            if (useOnContext.getPlayer() != null) {
                //判断一下是否符合需要的方块条件
                if (aboveState.getBlock() == Blocks.AIR) {
                    level.setBlock(abovePos, Registry.BLOCK.get(stateID).defaultBlockState(), Block.UPDATE_ALL);
                    level.gameEvent(useOnContext.getPlayer(), GameEvent.BLOCK_PLACE, abovePos);
                    useOnContext.getPlayer().getItemInHand(useOnContext.getHand()).shrink(1);
                    //播放音效
                    useOnContext.getPlayer().playSound(SoundEvents.CROP_PLANTED, 1.0F, 1.0F);
                }
            }
            return super.useOn(useOnContext);
        }
    }

    public static class PlantBlockItem extends BlockItem {
        public PlantBlockItem(Block block, boolean canEat, float... eatingValue) {
            super(block, new Properties().stacksTo(16).tab(ModGroup.AGRICULTURE_CREATIVE_MODE_TAB).food(canEat ?
                    new FoodProperties.Builder().nutrition((int) eatingValue[0]).saturationMod(eatingValue[1]).build() : null));
        }

        //重新修订放置处理
        public InteractionResult useOn(UseOnContext useOnContext) {
            Level level = useOnContext.getLevel();
            BlockPos abovePos = useOnContext.getClickedPos().above();
            BlockState aboveState = level.getBlockState(abovePos);
            //判断一下是否符合需要的方块条件
            if (aboveState.getBlock() == Blocks.AIR) {
                return super.useOn(useOnContext);
            }
            return InteractionResult.PASS;
        }
    }
}
