package com.daylight.civilization_fire.item.agriculture;

import com.daylight.civilization_fire.init.ModGroup;
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
        super(new Properties().tab(ModGroup.AGRICULTURE_CREATIVE_MODE_TAB).durability(durability));
        this.onPress = onPress;
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Entity entity = onPress.withEntity(useOnContext.getLevel(),useOnContext.getItemInHand());
        entity.setPos(useOnContext.getClickedPos().getX(),useOnContext.getClickedPos().getY(),useOnContext.getClickedPos().getZ());
        useOnContext.getLevel().addFreshEntity(entity);
        return super.useOn(useOnContext);
    }

    public interface WithEntity {
        Entity withEntity(Level level, ItemStack itemStack);
    }
}
