package com.daylight.civilization_fire.common.content.item.armor;

import com.daylight.civilization_fire.client.model.armor.CucumberRocketArmorModel;
import com.daylight.civilization_fire.client.model.armor.MushroomArmorModel;
import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.item.material.VegetableMaterials;
import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@Mod.EventBusSubscriber
public class MushroomArmor extends ArmorItem {
    public MushroomArmor(EquipmentSlot slot) {
        super(VegetableMaterials.HIGH_VEGETABLE, slot, new Properties().tab(CivilizationFireTab.ADD_MODE_TAB));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(new TextComponent(I18n.get("mushroom_armor.hover_text")));
    }

    //受伤事件处理
    @SubscribeEvent
    public static void onMushroomArmorOnHurt(LivingHurtEvent hurtEvent) {
        if(hurtEvent.getEntityLiving().getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof MushroomArmor) {
            if (hurtEvent.getSource() == DamageSource.LIGHTNING_BOLT || hurtEvent.getSource() == DamageSource.FALLING_STALACTITE || hurtEvent.getSource() == DamageSource.FALLING_BLOCK) {
                hurtEvent.setCanceled(true);
            }
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String layer) {
        return CivilizationFire.MODID + ":textures/armor/mushroom_armor_head.png";
    }


    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
        //抄mcr的傻缺代码（
        consumer.accept(new IItemRenderProperties() {
            @Override
            public HumanoidModel<LivingEntity> getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                MushroomArmorModel<LivingEntity> mushroomArmorModel = new MushroomArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MushroomArmorModel.LAYER_LOCATION));
                HumanoidModel<LivingEntity> armorModel = new HumanoidModel<>(new ModelPart(Collections.emptyList(),
                        Map.of(
                                "head", mushroomArmorModel.head,
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