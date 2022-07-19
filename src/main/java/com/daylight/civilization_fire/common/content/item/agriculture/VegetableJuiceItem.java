package com.daylight.civilization_fire.common.content.item.agriculture;

import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VegetableJuiceItem extends Item {
    public VegetableJuiceItem(java.util.function.Supplier<MobEffectInstance> effectIn) {
        super(new Properties().tab(CivilizationFireTab.ADD_MODE_TAB).stacksTo(1).food(new FoodProperties.Builder().alwaysEat().saturationMod(1).nutrition(1).effect(effectIn, 1).build()));
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TextComponent(I18n.get(this.getRegistryName().getPath() + ".hover_text")));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}