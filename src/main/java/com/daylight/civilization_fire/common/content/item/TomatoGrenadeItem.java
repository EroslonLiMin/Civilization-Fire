package com.daylight.civilization_fire.common.content.item;

import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

//西红柿手榴弹
public class TomatoGrenadeItem extends Item {
    public TomatoGrenadeItem(Properties pProperties) {
        super(new Properties().tab(CivilizationFireTab.ADD_MODE_TAB).stacksTo(16));
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        return super.useOn(pContext);
    }
}
