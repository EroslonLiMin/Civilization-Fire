package com.daylight.civilization_fire.common.content.item.agriculture;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.block.agriculture.PlantBlock;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//种子物品
public interface PlantItem {
    //种植植物
    class PlantFruitItem extends Item implements PlantItem {

        public PlantFruitItem(CreativeModeTab creativeModeTab, boolean canEat, float... eatingValue) {
            //实现以下food，有点草...
            super(new Properties().stacksTo(16).tab(creativeModeTab)
                    .food(canEat
                            ? new FoodProperties.Builder().nutrition((int) eatingValue[0]).saturationMod(eatingValue[1])
                            .build()
                            : null));
        }

    }

    class PlantBlockItem extends BlockItem implements PlantItem {
        public PlantBlockItem(CreativeModeTab creativeModeTab, Block block, boolean canEat, float... eatingValue) {
            super(block,
                    new Properties().stacksTo(16).tab(creativeModeTab).food(canEat
                            ? new FoodProperties.Builder().nutrition((int) eatingValue[0]).saturationMod(eatingValue[1])
                            .build()
                            : null));
        }

        //重新修订放置处理
        public InteractionResult useOn(UseOnContext useOnContext) {
            Level level = useOnContext.getLevel();
            BlockState aboveState = level.getBlockState(useOnContext.getClickedPos().above());
            //判断一下是否符合需要的方块条件
            if (aboveState.getBlock() == Blocks.AIR
                    && level.getBlockState(useOnContext.getClickedPos()).getMaterial() == Material.DIRT) {
                return super.useOn(useOnContext);
            }
            return InteractionResult.PASS;
        }

        /*信息预览：
            种植模式
            种植的阶段
            成熟需要的时间
            所能种植的方块
         */
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            PlantBlock plantBlock = (PlantBlock) this.getBlock();
            pTooltipComponents.add(new TextComponent(I18n.get("plant_item.hover_text.sp")));
            pTooltipComponents.add(new TextComponent(I18n.get("plant_item.hover_text.plant_mode",I18n.get(CivilizationFire.MODID + "." + plantBlock.plantMode.name))));
            pTooltipComponents.add(new TextComponent(I18n.get("plant_item.hover_text.plant_stage",plantBlock.stageLevel)));
            pTooltipComponents.add(new TextComponent(I18n.get("plant_item.hover_text.plant_mature_tick",plantBlock.matureTick)));
            StringBuilder stringBuilder = new StringBuilder();
            for(String str:plantBlock.plantBlocks){
                stringBuilder.append(I18n.get("block." + CivilizationFire.MODID + "." + str.split(":")[1])).append("...");
            }
            pTooltipComponents.add(new TextComponent(I18n.get("plant_item.hover_text.plant_blocks",stringBuilder.toString())));
            pTooltipComponents.add(new TextComponent(I18n.get("plant_item.hover_text.sp")));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    }
}
