package com.daylight.civilization_fire.common.content.item.agriculture;

import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;
import net.minecraft.world.item.Item;

//机器人升级工具
public class BotAddItem extends Item {
    public int level;
    public BotAddType botAddType;
    public enum BotAddType{
        MCAttributeAdd,
        EnergyAdd,
        CorrespondingAbilityAdd
    }

    public BotAddItem(int level,BotAddType addType) {
        super(new Properties().stacksTo(1).tab(CivilizationFireTab.ADD_MODE_TAB));
        this.level = level;
        this.botAddType = addType;
    }
}
