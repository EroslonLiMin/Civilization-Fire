package com.daylight.civilization_fire.common.content.item.armor;

import com.daylight.civilization_fire.client.model.armor.BalsamPearArmorModel;
import com.daylight.civilization_fire.client.model.armor.CucumberRocketArmorModel;
import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.item.material.VegetableMaterials;
import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber
public class CucumberRocketArmor extends ArmorItem {
    public CucumberRocketArmor(EquipmentSlot slot) {
        super(VegetableMaterials.HIGH_VEGETABLE, slot, new Properties().tab(CivilizationFireTab.ADD_MODE_TAB));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(new TextComponent(I18n.get("cucumber_rocket_armor.hover_text")));
    }

    //Update
    @SubscribeEvent
    public static void onCucumberRocketArmorUpDate(LivingEvent.LivingUpdateEvent updateEvent) {
        if (!(updateEvent.getEntityLiving().getItemBySlot(EquipmentSlot.FEET).getItem() instanceof CucumberRocketArmor) ||  !updateEvent.getEntityLiving().getLevel().isClientSide()) {
            return;
        }
        LivingEntity livingEntity = updateEvent.getEntityLiving();
        Vec3 vec3 = livingEntity.getDeltaMovement();
        if(!livingEntity.getPersistentData().getBoolean("isCucumber") && livingEntity.isShiftKeyDown()) {
            livingEntity.setDeltaMovement(vec3.x * 5 , vec3.y * 3, vec3.z * 5);
            livingEntity.getPersistentData().putBoolean("isCucumber",true);
        }
        if(vec3.y <= 0.1 && !livingEntity.isShiftKeyDown()){
            livingEntity.getPersistentData().remove("isCucumber");
        }
    }


    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String layer) {
        return CivilizationFire.MODID + ":textures/armor/cucumber_rocket_armor_feet.png";
    }



    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
        //抄mcr的傻缺代码（
        consumer.accept(new IItemRenderProperties() {
            @Override
            public HumanoidModel<LivingEntity> getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                CucumberRocketArmorModel<LivingEntity> cucumberRocketArmorModel = new CucumberRocketArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(CucumberRocketArmorModel.LAYER_LOCATION));
                HumanoidModel<LivingEntity> armorModel = new HumanoidModel<>(new ModelPart(Collections.emptyList(),
                        Map.of(
                                "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_leg", cucumberRocketArmorModel.rightLeg,
                                "left_leg", cucumberRocketArmorModel.leftLeg)));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        });
    }
}