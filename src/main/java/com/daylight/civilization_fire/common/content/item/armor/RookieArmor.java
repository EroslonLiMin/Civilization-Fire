package com.daylight.civilization_fire.common.content.item.armor;

import com.daylight.civilization_fire.client.model.armor.RookieHeadArmorModel;
import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.item.material.VegetableMaterials;
import com.daylight.civilization_fire.common.content.register.CivilizationFireEffect;
import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@Mod.EventBusSubscriber
public class RookieArmor extends ArmorItem{
    public RookieArmor(EquipmentSlot slot) {
        super(VegetableMaterials.LOW_VEGETABLE, slot, new Properties().tab(CivilizationFireTab.ADD_MODE_TAB));
    }

    //受伤事件处理
    @SubscribeEvent
    public static void onRookieArmorOnUpDate(LivingEvent.LivingUpdateEvent updateEvent) {
        if(updateEvent.getEntityLiving().getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof RookieArmor) {
            updateEvent.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,80));
            updateEvent.getEntityLiving().addEffect(new MobEffectInstance(CivilizationFireEffect.VEGETABLE_EFFECT.get(),80));
        }
    }

    //受伤事件处理
    @SubscribeEvent
    public static void onRookieArmorOnHurt(LivingHurtEvent hurtEvent) {
        if(hurtEvent.getEntityLiving().getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof RookieArmor) {
            if (hurtEvent.getSource().isFall()) {
                hurtEvent.setCanceled(true);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(new TextComponent(I18n.get("rookie_armor.hover_text")));
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String layer) {
        return CivilizationFire.MODID + ":textures/armor/rookie_armor_hat.png";
    }



    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
        //抄mcr的傻缺代码（
        consumer.accept(new IItemRenderProperties() {
            @Override
            public HumanoidModel<LivingEntity> getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel<LivingEntity> armorModel = new HumanoidModel<>(new ModelPart(Collections.emptyList(),
                        Map.of(
                                "head", new RookieHeadArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(RookieHeadArmorModel.LAYER_LOCATION)).head,
                                "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        });
    }
}
