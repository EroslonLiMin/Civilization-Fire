package com.daylight.civilization_fire.creativetab;

import com.daylight.civilization_fire.registry.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class AgricultureCreativeModeTab extends CreativeModeTab {

    public AgricultureCreativeModeTab() {
        super("agriculture_creative_mode_tab");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemRegistry.CLAY_BLOCK_BLOCK.get());
    }
}
