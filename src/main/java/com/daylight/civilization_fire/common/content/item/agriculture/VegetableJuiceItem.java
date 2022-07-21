package com.daylight.civilization_fire.common.content.item.agriculture;

import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

//蔬菜汁物品
public class VegetableJuiceItem extends Item {
    public static final List<VegetableJuiceItem> VEGETABLE_JUICE_ITEM_LIST = new ArrayList<>();
    public String vegetable;
    public VegetableJuiceItem(String vegetable,java.util.function.Supplier<MobEffectInstance> effectIn) {
        super(new Properties().tab(CivilizationFireTab.ADD_MODE_TAB).stacksTo(1).food(new FoodProperties.Builder().alwaysEat().saturationMod(0.5F).nutrition(1).effect(effectIn, 1).build()));
        this.vegetable = vegetable;
        VEGETABLE_JUICE_ITEM_LIST.add(this);
    }

    public VegetableJuiceItem(Properties properties){
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        //使用完成的时候返回瓶子
        if(pLivingEntity instanceof Player player){
            player.addItem(new ItemStack(Items.GLASS_BOTTLE));
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }

    //修改一下喝的声音
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