package com.daylight.civilization_fire.common.content.item.agriculture;

import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

//能召唤实体的物品
public class EntityItem extends Item {
    public WithEntity onPress;//生成出来的实体

    public EntityItem(int durability, WithEntity onPress) {
        super(new Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB).durability(durability));
        this.onPress = onPress;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide()) {
            Entity entity = onPress.withEntity(pLevel, pPlayer.getItemInHand(pUsedHand));
            Vec3 vec3 = pPlayer.getViewVector(1);
            BlockPos pos = new BlockPos(pPlayer.getX() + vec3.x * 2, pPlayer.getEyeY(),
                    pPlayer.getZ() + vec3.z * 2);
            entity.setPos(pos.getX(), pos.getY(), pos.getZ());
            entity.setXRot(pPlayer.getXRot());
            entity.setYRot(pPlayer.getYRot());
            pLevel.addFreshEntity(entity);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public interface WithEntity {
        Entity withEntity(Level level, ItemStack itemStack);
    }
}
