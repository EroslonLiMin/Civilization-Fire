package com.daylight.civilization_fire.common.content.item.agriculture;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.block.agriculture.PlantLoad;
import com.daylight.civilization_fire.common.content.register.CivilizationFireTab;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class SeedBagItem extends Item {
    public SeedBagItem(){
        super(new Properties().tab(CivilizationFireTab.AGRICULTURE_CREATIVE_MODE_TAB).stacksTo(1));
    }

    //种子袋子，随机获得种子
    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        if(useOnContext.getPlayer() != null) {
            Random random = new Random();
            int randomTimes = random.nextInt(5) + 2;
            for (int i = 0; i < randomTimes; i++) {
                ResourceKey<Block> resourceKey = (ResourceKey<Block>) PlantLoad.FRUIT_BLOCK_MAP.values().toArray()[random.nextInt(PlantLoad.FRUIT_BLOCK_MAP.values().size())];
                ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(CivilizationFire.resource(resourceKey.location().getPath() + "_item")));
                itemStack.setCount(random.nextInt(15) + 1);
                useOnContext.getPlayer().addItem(itemStack);
                useOnContext.getItemInHand().shrink(1);
            }
        }
        return super.useOn(useOnContext);
    }
}
