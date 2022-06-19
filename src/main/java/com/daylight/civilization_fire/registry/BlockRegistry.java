package com.daylight.civilization_fire.registry;

import com.daylight.civilization_fire.block.agriculture.PaddySoilBlock;
import com.daylight.civilization_fire.block.agriculture.PlantBlock;
import com.daylight.civilization_fire.block.agriculture.PlantLoad;
import com.daylight.civilization_fire.block.agriculture.SoilBlock;
import com.daylight.civilization_fire.util.Utils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Utils.MOD_ID);

    //土地类型
    public static final RegistryObject<Block> LOAM_BLOCK = BLOCKS.register("loam_block", SoilBlock::new);
    public static final RegistryObject<Block> CLAY_BLOCK = BLOCKS.register("clay_block", SoilBlock::new);
    public static final RegistryObject<Block> SAND_BLOCK = BLOCKS.register("sand_block", SoilBlock::new);
    public static final RegistryObject<Block> PADDY_SOIL_BLOCK = BLOCKS.register("paddy_soil_block", PaddySoilBlock::new);//水田
    public static final RegistryObject<Block> MYCELIAL_SOIL_BLOCK = BLOCKS.register("mycelial_soil_block", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).strength(0.6F).sound(SoundType.GRAVEL).requiresCorrectToolForDrops().noOcclusion()));//菌丝

    //植物方块
    public static final PlantLoad RICE_PLANT = new PlantLoad("rice",8,2000.00F ,false,new String[]{"civilization_fire:loam_block","civilization_fire:paddy_soil_block"},false,PlantBlock.PlantModel.DestroyModel,1);

}
