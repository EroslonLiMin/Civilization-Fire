package com.daylight.civilization_fire.common.content.item;

import com.daylight.civilization_fire.client.screen.FireScreen;
import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ShennongFireScroll extends Item {
    public ShennongFireScroll() {
        super(new Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(new TranslatableComponent("shennong_fire_scroll_hover_text_0"));
        pTooltipComponents.add(new TranslatableComponent("shennong_fire_scroll_hover_text_1"));
        pTooltipComponents.add(new TranslatableComponent("shennong_fire_scroll_hover_text_2"));
        pTooltipComponents.add(new TranslatableComponent("shennong_fire_scroll_hover_text_3"));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pLevel.isClientSide()){
            Minecraft.getInstance().setScreen(new FireScreen());
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
