package com.daylight.civilization_fire.common.content.item.armor;

import com.daylight.civilization_fire.client.model.armor.RookieHeadArmorModel;
import com.daylight.civilization_fire.common.content.item.material.VegetableMaterials;
import com.daylight.civilization_fire.common.content.register.CivilizationFireEffect;
import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;


@Mod.EventBusSubscriber
public class RookieArmor extends ArmorItem{
    public RookieArmor(EquipmentSlot slot) {
        super(VegetableMaterials.LOW_VEGETABLE, slot, new Properties().tab(CivilizationFireTab.ADD_MODE_TAB));
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        super.onUsingTick(stack,player,count);
        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING));
        player.addEffect(new MobEffectInstance(CivilizationFireEffect.VEGETABLE_EFFECT.get()));
    }

    //受伤事件处理
    @SubscribeEvent
    public static void onRookieArmorOnHurt(LivingHurtEvent hurtEvent) {
        if(hurtEvent.getEntityLiving().getItemBySlot(EquipmentSlot.FEET).getItem() instanceof RookieArmor) {
            if (hurtEvent.getSource().isFall()) {
                hurtEvent.setCanceled(true);
            }
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String layer) {
        return "textures/armor/rookie_armor_head.png";
    }


    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(ArmorRender.INSTANCE);
        super.initializeClient(consumer);
    }

    private static final class ArmorRender implements IItemRenderProperties {
        private static final ArmorRender INSTANCE = new ArmorRender();

        @NotNull
        @Override
        public Model getBaseArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
            EntityModelSet models = Minecraft.getInstance().getEntityModels();
            return new RookieHeadArmorModel<>(models.bakeLayer(RookieHeadArmorModel.LAYER_LOCATION));
        }
    }
}
