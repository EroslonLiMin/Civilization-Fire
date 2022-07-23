package com.daylight.civilization_fire.common.content.item.armor;

import com.daylight.civilization_fire.client.model.armor.SuperTaroArmorModel;
import com.daylight.civilization_fire.client.model.armor.WaxGourdArmorModel;
import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.item.material.VegetableMaterials;
import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Mth;
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
public class SuperTaroArmor  extends ArmorItem {
    public SuperTaroArmor(EquipmentSlot pSlot) {
        super(VegetableMaterials.NORMAL_VEGETABLE, pSlot, new Properties().tab(CivilizationFireTab.ADD_MODE_TAB));
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(new TextComponent(I18n.get("super_taro.hover_text")));
    }

    //受伤事件处理
    @SubscribeEvent
    public static void onSuperTaroArmorOnUpdate(LivingEvent.LivingUpdateEvent updateEvent) {
        if (!(updateEvent.getEntityLiving().getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof SuperTaroArmor) ||  !updateEvent.getEntityLiving().getLevel().isClientSide()) {
            return;
        }
        LivingEntity livingEntity = updateEvent.getEntityLiving();
        Vec3 vec3 = livingEntity.getDeltaMovement();
        if(!livingEntity.getPersistentData().getBoolean("isSuperTaro")) {
            livingEntity.setDeltaMovement(vec3.x, vec3.y * 1.5, vec3.z);
            livingEntity.getPersistentData().putBoolean("isSuperTaro",true);
        }
        if(vec3.y <= 0.1){
            livingEntity.getPersistentData().remove("isSuperTaro");
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String layer) {
        return CivilizationFire.MODID + ":textures/armor/super_taro_armor_pants.png";
    }



    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
        //抄mcr的傻缺代码（
        consumer.accept(new IItemRenderProperties() {
            @Override
            public HumanoidModel<LivingEntity> getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                SuperTaroArmorModel<LivingEntity> superTaroArmorModel = new SuperTaroArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(SuperTaroArmorModel.LAYER_LOCATION));
                HumanoidModel<LivingEntity> armorModel = new HumanoidModel<>(new ModelPart(Collections.emptyList(),
                        Map.of("head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "body", superTaroArmorModel.body,
                                "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_leg", superTaroArmorModel.rightleg,
                                "left_leg", superTaroArmorModel.leftleg)));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        });
    }
}
