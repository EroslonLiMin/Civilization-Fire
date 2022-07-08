package com.daylight.civilization_fire.block.agriculture;

import com.daylight.civilization_fire.item.agriculture.PlantItem;
import com.daylight.civilization_fire.registry.BlockRegistry;
import com.daylight.civilization_fire.registry.ItemRegistry;
import com.daylight.civilization_fire.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;


//加载植物
public class PlantLoad {
    public String name;//植物名称
    public PlantBlock.PlantModel plantModel;
    public final RegistryObject<Block> plantBlockRegistry;
    public final RegistryObject<Item> plantItemRegistry;
    public RegistryObject<Item> plantFruitRegistry;

    //名字，种植有几个阶段，种植时间，有无种子，种植方块，种植/果实是否可以直接食用，种植模式，
    public PlantLoad(String name, int stageLevel, float matureTick, boolean isDistinguishSeed, String[] blocks, boolean canEat, PlantBlock.PlantModel plantModel, CreativeModeTab creativeModeTab, int round, @Nullable float... eatingData) {
        this.name = name;
        this.plantModel = plantModel;
        //开始处理
        ResourceLocation fruitResourceLocation = new ResourceLocation(Utils.MOD_ID, name + (isDistinguishSeed ? "_fruit" : "_item"));
        //方块处理
        //Get延迟处理
        this.plantBlockRegistry = BlockRegistry.BLOCKS.register(name, () -> new PlantBlock(plantModel, fruitResourceLocation, stageLevel, matureTick, blocks, round));
        if(isDistinguishSeed){
            this.plantFruitRegistry = ItemRegistry.ITEMS.register(name + "_fruit", () -> new PlantItem.PlantFruitItem(creativeModeTab,canEat, eatingData));
        }
        this.plantItemRegistry = ItemRegistry.ITEMS.register(name + "_item", () -> new PlantItem.PlantBlockItem(creativeModeTab,plantBlockRegistry.get(), canEat && !isDistinguishSeed, eatingData));
    }
}