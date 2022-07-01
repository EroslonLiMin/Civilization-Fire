package com.daylight.civilization_fire.item.agriculture;

import com.daylight.civilization_fire.entity.agriculture.PloughEntity;
import com.daylight.civilization_fire.init.ModGroup;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class WhipItem extends Item {
    public PloughEntity ploughEntity;
    public WhipItem() {
        super(new Properties().tab(ModGroup.AGRICULTURE_CREATIVE_MODE_TAB).durability(1000).stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        return super.useOn(useOnContext);
    }
}
