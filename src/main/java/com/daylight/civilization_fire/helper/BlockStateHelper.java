package com.daylight.civilization_fire.helper;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

//方块状态助手
public class BlockStateHelper {
    //获取对应状态
    public static Object getState(BlockState state, Property property, Object base) {
        if (!state.hasProperty(property)) {
            return base;
        }
        return state.getOptionalValue(property);
    }
}
