package com.daylight.civilization_fire.common.content.tab;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

//农业部门
public class ModCreativeModeTab extends CreativeModeTab {
    public WithItemIcon item;

    public ModCreativeModeTab(String label, WithItemIcon item) {
        super(label);
        this.item = item;
    }

    @Override
    public ItemStack makeIcon() {
        return item.withItemIcon();
    }

    public interface WithItemIcon {
        ItemStack withItemIcon();
    }
}
