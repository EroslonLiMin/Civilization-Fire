package com.daylight.civilization_fire.common.content.item.armor;

import com.daylight.civilization_fire.client.model.armor.BalsamPearArmorModel;
import com.daylight.civilization_fire.client.model.armor.RookieHeadArmorModel;
import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.item.material.VegetableMaterials;
import com.daylight.civilization_fire.common.content.register.CivilizationFireEffect;
import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber
public class BalsamPearArmor extends ArmorItem {
    public BalsamPearArmor(EquipmentSlot slot) {
        super(VegetableMaterials.NORMAL_VEGETABLE, slot, new Properties().tab(CivilizationFireTab.ADD_MODE_TAB));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(new TextComponent(I18n.get("balsam_pear_armor.hover_text")));
    }

    //受伤事件处理
    @SubscribeEvent
    public static void onBalsamPearArmorOnUpDate(LivingEvent.LivingUpdateEvent updateEvent) {
        LivingEntity livingEntity = updateEvent.getEntityLiving();
        if(livingEntity.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof BalsamPearArmor && !livingEntity.getLevel().isClientSide()) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,80));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,80));
            if(livingEntity.getDeltaMovement().y > 0){
                BlockState blockState = livingEntity.getLevel().getBlockState(livingEntity.getOnPos().above(1));
                if(blockState.getBlock() != Blocks.AIR){
                    livingEntity.getLevel().setBlock(livingEntity.getOnPos().above(2),Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                    livingEntity.gameEvent(GameEvent.BLOCK_PLACE, livingEntity.getOnPos().above(1));
                }
            }
        }
    }


    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String layer) {
        return CivilizationFire.MODID + ":textures/armor/balsam_pear_armor_hat.png";
    }



    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
        //抄mcr的傻缺代码（
        consumer.accept(new IItemRenderProperties() {
            @Override
            public HumanoidModel<LivingEntity> getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                BalsamPearArmorModel<LivingEntity> balsamPearArmorModel = new BalsamPearArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BalsamPearArmorModel.LAYER_LOCATION));
                HumanoidModel<LivingEntity> armorModel = new HumanoidModel<>(new ModelPart(Collections.emptyList(),
                        Map.of(
                                "head", balsamPearArmorModel.head,
                                "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                "left_leg",new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        });
    }
}
