package com.daylight.civilization_fire.common.content.item;

import com.daylight.civilization_fire.common.content.entity.projectile.CivilizationFireProjectileEntity;
import com.daylight.civilization_fire.common.content.register.CivilizationFireBlocks;
import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;
import com.daylight.civilization_fire.common.util.CivilizationFireUtil;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ScallionCrownBladeItem extends SwordItem {
    public ScallionCrownBladeItem() {
        super(Tiers.WOOD,2,1,new Properties().tab(CivilizationFireTab.ADD_MODE_TAB).durability(100));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(new TextComponent(I18n.get("scallion_crow.hover_text")));
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if(pAttacker instanceof Player player && !player.getLevel().isClientSide()) {
            System.out.println(true);
            CivilizationFireProjectileEntity.shoot(player.getLevel(),player,5,5,3, new ItemStack(CivilizationFireBlocks.SHALLOT_PLANT.plantItemRegistry.get()));
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }



}
