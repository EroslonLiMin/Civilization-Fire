package com.daylight.civilization_fire.common.content.item.agriculture;

import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

//能召唤实体的物品
public class EntityItem extends Item {
    public WithEntity onPress;//生成出来的实体

    public EntityItem(int durability, WithEntity onPress) {
        super(new Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB).durability(durability));
        this.onPress = onPress;
    }


    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        if (!useOnContext.getLevel().isClientSide() && useOnContext.getPlayer()!= null) {
            Entity entity = onPress.withEntity(useOnContext.getLevel(), useOnContext.getItemInHand());
            BlockPos pos = new BlockPos(useOnContext.getClickedPos().getX(), useOnContext.getClickedPos().getY() + 1,
                    useOnContext.getClickedPos().getZ());
            entity.setPos(pos.getX(), pos.getY(), pos.getZ());
            entity.setXRot(useOnContext.getPlayer().getXRot());
            entity.setYRot(useOnContext.getPlayer().getYRot());
            useOnContext.getLevel().addFreshEntity(entity);
        }
        return super.useOn(useOnContext);
    }

    public interface WithEntity {
        Entity withEntity(Level level, ItemStack itemStack);
    }
}
