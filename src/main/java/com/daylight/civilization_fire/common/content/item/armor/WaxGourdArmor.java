package com.daylight.civilization_fire.common.content.item.armor;

import com.daylight.civilization_fire.client.model.armor.RookieHeadArmorModel;
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
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber
public class WaxGourdArmor extends ArmorItem {
    public WaxGourdArmor(EquipmentSlot pSlot) {
        super(VegetableMaterials.HIGH_VEGETABLE, pSlot, new Properties().tab(CivilizationFireTab.ADD_MODE_TAB));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(new TextComponent(I18n.get("wax_gourd.hover_text")));
    }

    //受伤事件处理
    @SubscribeEvent
    public static void onWaxGourdArmorOnHurt(LivingHurtEvent hurtEvent) {
        Entity entity = hurtEvent.getSource().getEntity();
        if(entity != null){
            hurtEvent.setAmount(hurtEvent.getAmount() * 0.5F);
            entity.hasImpulse = true;
            Vec3 vec3 = entity.getDeltaMovement();
            Vec3 vec31 = (new Vec3(-Mth.sin(entity.getYRot() * ((float) Math.PI / 180F)), 0.0D, Mth.cos(entity.getYRot() * ((float) Math.PI / 180F)))).normalize().scale(0.8);
            entity.setDeltaMovement(vec3.x / 2.0D - vec31.x,  vec3.y, vec3.z / 2.0D - vec31.z);
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String layer) {
        return CivilizationFire.MODID + ":textures/armor/wax_gourd_armor_body.png";
    }



    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
        //抄mcr的傻缺代码（
        consumer.accept(new IItemRenderProperties() {
            @Override
            public HumanoidModel<LivingEntity> getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                WaxGourdArmorModel<LivingEntity> waxGourdArmorModel = new WaxGourdArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(WaxGourdArmorModel.LAYER_LOCATION));
                HumanoidModel<LivingEntity> armorModel = new HumanoidModel<>(new ModelPart(Collections.emptyList(),
                        Map.of("head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "body", waxGourdArmorModel.body,
                                "right_arm", waxGourdArmorModel.rightarm,
                                "left_arm", waxGourdArmorModel.leftarm,
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
