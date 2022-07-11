package com.daylight.civilization_fire.common.content.block.agriculture;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
import com.daylight.civilization_fire.common.content.register.CivilizationFireBlocks;
import com.daylight.civilization_fire.common.content.register.CivilizationFireItems;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

//加载植物
public class PlantLoad {

        /**
         * Map of plant block to plant item.
         * @author Heckerpowered
         */
        public static final Map<ResourceKey<Item>, ResourceKey<Block>> FRUIT_BLOCK_MAP = new HashMap<>();
        /**
         * Reversed "PLANT_BLOCK_MAP"
         * @author Heckerpowered
         */
        public static final Map<ResourceKey<Block>, ResourceKey<Item>> BLOCK_FRUIT_MAP = new HashMap<>();

        public String name;//植物名称
        public PlantBlock.PlantModel plantModel;
        public final RegistryObject<Block> plantBlockRegistry;
        public final RegistryObject<Item> plantItemRegistry;
        public RegistryObject<Item> plantFruitRegistry;

        //名字，种植有几个阶段，种植时间，有无种子，种植方块，种植/果实是否可以直接食用，种植模式，
        public PlantLoad(String name, int stageLevel, int matureTick, boolean isDistinguishSeed, String[] blocks,
                        boolean canEat, PlantBlock.PlantModel plantModel, CreativeModeTab creativeModeTab, int round,
                        @Nullable float... eatingData) {
                this.name = name;
                this.plantModel = plantModel;
                //开始处理
                ResourceLocation fruitResourceLocation = CivilizationFire
                                .resource(name + (isDistinguishSeed ? "_fruit" : "_item"));
                //方块处理
                //Get延迟处理
                this.plantBlockRegistry = CivilizationFireBlocks.BLOCKS.register(name,
                                () -> new PlantBlock(plantModel, fruitResourceLocation, stageLevel, matureTick, blocks,
                                                round));
                if (isDistinguishSeed) {
                        this.plantFruitRegistry = CivilizationFireItems.ITEMS.register(name + "_fruit",
                                        () -> new PlantItem.PlantFruitItem(creativeModeTab, canEat, eatingData));
                }
                this.plantItemRegistry = CivilizationFireItems.ITEMS.register(name + "_item",
                                () -> new PlantItem.PlantBlockItem(creativeModeTab, plantBlockRegistry.get(),
                                                canEat && !isDistinguishSeed, eatingData));

                if (isDistinguishSeed) {
                        FRUIT_BLOCK_MAP.put(plantFruitRegistry.getKey(), plantBlockRegistry.getKey());
                        BLOCK_FRUIT_MAP.put(plantBlockRegistry.getKey(), plantFruitRegistry.getKey());
                }
        }
}