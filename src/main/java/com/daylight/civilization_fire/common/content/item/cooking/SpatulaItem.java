package com.daylight.civilization_fire.common.content.item.cooking;

import com.daylight.civilization_fire.common.content.block.cooking.IronPotBlock;
import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;
import com.daylight.civilization_fire.common.util.CivilizationFireUtil;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpatulaItem extends Item {
    public SpatulaItem(int useTimes) {
        super(new Properties().tab(CivilizationFireTab.COOKING_CREATIVE_MODE_TAB).durability(useTimes));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.getBlockEntity(
                context.getClickedPos()) instanceof IronPotBlock.IronPotBlockEntity ironPotBlockEntity) {
            ironPotBlockEntity.cookingHeight += 0.25;
            ironPotBlockEntity.cookingTime += 5;
            CivilizationFireUtil.hurtItem(context.getItemInHand(), context.getPlayer(), context.getHand(), 1);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components,
            TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, components, tooltipFlag);
        components.add(new TextComponent(I18n.get("condiment_item.hover.text",
                itemStack.getMaxDamage() - itemStack.getDamageValue())));
    }
}
