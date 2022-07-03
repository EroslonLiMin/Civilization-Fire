package com.daylight.civilization_fire.registry;

import com.daylight.civilization_fire.block.agriculture.PaddySoilBlock;
import com.daylight.civilization_fire.block.agriculture.PlantBlock;
import com.daylight.civilization_fire.block.agriculture.PlantLoad;
import com.daylight.civilization_fire.block.agriculture.SoilBlock;
import com.daylight.civilization_fire.init.ModGroup;
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
    public static final RegistryObject<Block> MYCELIAL_SOIL_BLOCK = BLOCKS.register("mycelial_soil_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).strength(0.6F).sound(SoundType.GRAVEL).requiresCorrectToolForDrops().noOcclusion()));//菌丝


    //植物
    //////粮食作物
    /*大米*/
    public static final PlantLoad RICE_PLANT = new PlantLoad("rice", 7, 2000.00F, false, new String[]{"civilization_fire:loam_block", "civilization_fire:paddy_soil_block"}, false, PlantBlock.PlantModel.DestroyModel, ModGroup.GRAIN_CROPS_CREATIVE_MODE_TAB, 2);
    /*糯米*/
    public static final PlantLoad GLUTINOUS_RICE_PLANT = new PlantLoad("glutinous_rice", 7, 2000.00F, false, new String[]{"civilization_fire:loam_block", "civilization_fire:paddy_soil_block"}, false, PlantBlock.PlantModel.DestroyModel, ModGroup.GRAIN_CROPS_CREATIVE_MODE_TAB, 2);
    /*黄米*/
    public static final PlantLoad YELLOW_RICE_PLANT = new PlantLoad("yellow_rice", 7, 2000.00F, false, new String[]{"", "civilization_fire:sand_block"}, false, PlantBlock.PlantModel.DestroyModel, ModGroup.GRAIN_CROPS_CREATIVE_MODE_TAB, 2);
    /*高粱*/
    public static final PlantLoad SORGHUM_PLANT = new PlantLoad("sorghum", 7, 2000.00F, false, new String[]{"civilization_fire:loam_block"}, false, PlantBlock.PlantModel.DestroyModel, ModGroup.GRAIN_CROPS_CREATIVE_MODE_TAB, 2);
    /*玉米*/
    public static final PlantLoad CORN_PLANT = new PlantLoad("corn", 7, 2000.00F, false, new String[]{"civilization_fire:loam_block", "civilization_fire:paddy_soil_block"}, false, PlantBlock.PlantModel.DestroyModel, ModGroup.GRAIN_CROPS_CREATIVE_MODE_TAB, 2);
    /*红薯*/
    public static final PlantLoad SWEET_POTATO_PLANT = new PlantLoad("sweet_potato", 3, 2000.00F, false, new String[]{"civilization_fire:sand_block"}, false, PlantBlock.PlantModel.DestroyModel, ModGroup.GRAIN_CROPS_CREATIVE_MODE_TAB, 2);
    /*芋头*/
    public static final PlantLoad TARO_PLANT = new PlantLoad("taro", 3, 2000.00F, false, new String[]{"civilization_fire:sand_block"}, false, PlantBlock.PlantModel.DestroyModel, ModGroup.GRAIN_CROPS_CREATIVE_MODE_TAB, 2);
    /*黄豆*/
    public static final PlantLoad BROAD_BEAN_PLANT = new PlantLoad("broad_bean", 3, 2000.00F, false, new String[]{"civilization_fire:clay_block"}, false, PlantBlock.PlantModel.PickingModel, ModGroup.GRAIN_CROPS_CREATIVE_MODE_TAB, 5);
    /*红豆*/
    public static final PlantLoad YOUNG_SOYBEAN_PLANT = new PlantLoad("young_soybean", 3, 2000.00F, false, new String[]{"civilization_fire:loam_block,civilization_fire:clay_block,civilization_fire:sand_block"}, false, PlantBlock.PlantModel.PickingModel, ModGroup.GRAIN_CROPS_CREATIVE_MODE_TAB, 5);
    /*绿豆*/
    public static final PlantLoad CAROB_PLANT = new PlantLoad("carob", 3, 2000.00F, false, new String[]{"civilization_fire:loam_block,civilization_fire:clay_block,civilization_fire:sand_block"}, false, PlantBlock.PlantModel.PickingModel, ModGroup.GRAIN_CROPS_CREATIVE_MODE_TAB, 8);
    /*黑豆*/
    public static final PlantLoad LENTIL_HORN_PLANT = new PlantLoad("lentil_horn", 3, 2000.00F, false, new String[]{"civilization_fire:loam_block,civilization_fire:sand_block"}, false, PlantBlock.PlantModel.PickingModel, ModGroup.GRAIN_CROPS_CREATIVE_MODE_TAB, 8);
    /*蚕豆*/
    public static final PlantLoad WHITE_BEAN_HORN_PLANT = new PlantLoad("white_bean_horn", 3, 2000.00F, false, new String[]{"civilization_fire:loam_block,civilization_fire:sand_block"}, false, PlantBlock.PlantModel.PickingModel, ModGroup.GRAIN_CROPS_CREATIVE_MODE_TAB, 8);
    /*毛豆角*/
    public static final PlantLoad PEANUT = new PlantLoad("peanut", 3, 2000.00F, false, new String[]{"civilization_fire:sand_block"}, true, PlantBlock.PlantModel.DestroyModel, ModGroup.GRAIN_CROPS_CREATIVE_MODE_TAB, 12, 2, 4);


    //////蔬菜作物
    /*小葱*/
    public static final PlantLoad SHALLOT_PLANT = new PlantLoad("shallot", 3, 1200, true, new String[]{"civilization_fire:sand_block"}, true, PlantBlock.PlantModel.DestroyModel, ModGroup.VEGETABLE_CROPS_CREATIVE_MODE_TAB, 1, 1, 0);
    /*大葱*/
    public static final PlantLoad GREEN_CHINESE_ONION_PLANT = new PlantLoad("green_chinese_onion", 3, 1200, false, new String[]{"civilization_fire:sand_block"}, true, PlantBlock.PlantModel.DestroyModel, ModGroup.VEGETABLE_CROPS_CREATIVE_MODE_TAB, 1, 1, 0);
    /*茄子*/
    public static final PlantLoad EGGPLANT_PLANT = new PlantLoad("eggplant", 4, 2000.00F, true, new String[]{"civilization_fire:loam_block,civilization_fire:sand_block"}, false, PlantBlock.PlantModel.PickingModel, ModGroup.VEGETABLE_CROPS_CREATIVE_MODE_TAB, 3);
    /*芹菜*/
    public static final PlantLoad CELERY_PLANT = new PlantLoad("celery", 3, 1500, true, new String[]{"civilization_fire:loam_block", "civilization_fire:clay_block"}, false, PlantBlock.PlantModel.PickingModel, ModGroup.VEGETABLE_CROPS_CREATIVE_MODE_TAB, 1);
    /*黄瓜*/
    public static final PlantLoad CUCUMBER_PLANT = new PlantLoad("cucumber", 2, 2000.00F, true, new String[]{"civilization_fire:loam_block"}, true, PlantBlock.PlantModel.LikePumpkin, ModGroup.VEGETABLE_CROPS_CREATIVE_MODE_TAB, 5, 3, 3);
    /*冬瓜*/
    public static final PlantLoad WAX_GOURD_PLANT = new PlantLoad("wax_gourd", 4, 2000.00F, true, new String[]{"civilization_fire:loam_block,civilization_fire:sand_block"}, false, PlantBlock.PlantModel.LikePumpkin, ModGroup.VEGETABLE_CROPS_CREATIVE_MODE_TAB, 8);
    /*苦瓜*/
    public static final PlantLoad BALSAM_PEAR_PLANT = new PlantLoad("balsam_pear", 2, 2000.00F, true, new String[]{"civilization_fire:loam_block,civilization_fire:clay_block,civilization_fire:sand_block"}, true, PlantBlock.PlantModel.LikePumpkin, ModGroup.VEGETABLE_CROPS_CREATIVE_MODE_TAB, 5, 3, 3);
    /*丝瓜*/
    public static final PlantLoad TOWEL_GOURD_PLANT = new PlantLoad("towel_gourd", 2, 2000.00F, true, new String[]{"civilization_fire:sand_block"}, false, PlantBlock.PlantModel.LikePumpkin, ModGroup.VEGETABLE_CROPS_CREATIVE_MODE_TAB, 5);
    /*香菇*/
    public static final PlantLoad SOYBEAN_PLANT = new PlantLoad("soybean", 4, 2000.00F, false, new String[]{"civilization_fire:loam_block,civilization_fire:clay_block"}, false, PlantBlock.PlantModel.PickingModel, ModGroup.VEGETABLE_CROPS_CREATIVE_MODE_TAB, 1);
    /*姜*/
    public static final PlantLoad GINGER_PLANT = new PlantLoad("ginger", 3, 2000.00F, false, new String[]{"civilization_fire:loam_block,civilization_fire:sand_block"}, false, PlantBlock.PlantModel.DestroyModel, ModGroup.VEGETABLE_CROPS_CREATIVE_MODE_TAB, 1);
    /*白萝卜*/
    public static final PlantLoad TERNIP_PLANT = new PlantLoad("ternip", 4, 2000.00F, true, new String[]{"civilization_fire:sand_block"}, true, PlantBlock.PlantModel.DestroyModel, ModGroup.VEGETABLE_CROPS_CREATIVE_MODE_TAB, 3, 3, 3.6F);
    /*水萝卜*/
    public static final PlantLoad WATER_RADISH_PLANT = new PlantLoad("water_radish", 4, 2000.00F, true, new String[]{"civilization_fire:sand_block"}, true, PlantBlock.PlantModel.DestroyModel, ModGroup.VEGETABLE_CROPS_CREATIVE_MODE_TAB, 1, 2, 2);
    /*西红柿*/
    public static final PlantLoad TOMATOES_PLANT = new PlantLoad("tomatoes", 4, 2000.00F, true, new String[]{"civilization_fire:loam_block,civilization_fire:sand_block"}, true, PlantBlock.PlantModel.PickingModel, ModGroup.VEGETABLE_CROPS_CREATIVE_MODE_TAB, 1, 2, 0);
    /*洋葱*/
    public static final PlantLoad ONION_PLANT = new PlantLoad("onion", 4, 2000.00F, true, new String[]{"civilization_fire:sand_block"}, true, PlantBlock.PlantModel.DestroyModel, ModGroup.VEGETABLE_CROPS_CREATIVE_MODE_TAB, 1, 2, 0);

    //////香料作物
    /*辣椒*/
    public static final PlantLoad HOT_PEPPER_PLANT = new PlantLoad("hot_pepper", 5, 2000.00F, true, new String[]{"civilization_fire:loam_block,civilization_fire:clay_block,civilization_fire:sand_block"}, true, PlantBlock.PlantModel.PickingModel, ModGroup.SPICE_CROPS_CREATIVE_MODE_TAB, 2, 0, 0);
    /*尖椒*/
    public static final PlantLoad PEPPER_PLANT = new PlantLoad("pepper", 3, 1500, true, new String[]{"civilization_fire:loam_block"}, true, PlantBlock.PlantModel.PickingModel, ModGroup.SPICE_CROPS_CREATIVE_MODE_TAB, 2, 1, 0);
    /*茴香*/
    public static final PlantLoad FENNEL_PLANT = new PlantLoad("fennel", 3, 2000.00F, true, new String[]{"civilization_fire:loam_block"}, false, PlantBlock.PlantModel.DestroyModel, ModGroup.SPICE_CROPS_CREATIVE_MODE_TAB, 1);

//    /*菜心*/public static final PlantLoad CHINESE_CABBAGE_PLANT = new PlantLoad("chinese_cabbage", 5, 800, true,
//            new String[]{"civilization_fire:loam_block", "civilization_fire:clay_block", "civilization_fire" +
//                    ":sand_block"}, false, PlantBlock.PlantModel.DestroyModel, 2);
//    /*茼蒿*/public static final PlantLoad CHRYSANTHEMUM_CORONARUIUM_PLANT = new PlantLoad("chrysanthemum_coronarium", 8
//            , 2000.00F, false, new String[]{"civilization_fire:loam_block"}, false, PlantBlock.PlantModel.DestroyModel
//            , 2);
//    /*苋菜*/public static final PlantLoad THREE_COLORED_AMARANTH_PLANT = new PlantLoad("three_colored_amaranth", 5,
//            2000.00F, true, new String[]{"civilization_fire:loam_block,civilization_fire:sand_block"}, false,
//            PlantBlock.PlantModel.DestroyModel, 2);
//    /*花椰菜*/public static final PlantLoad CAULIFLOWER_PLANT = new PlantLoad("cauliflower", 5, 2000.00F, true,
//            new String[]{"civilization_fire:sand_block"}, false, PlantBlock.PlantModel.DestroyModel, 2);
//    /*菠菜*/public static final PlantLoad SPINACH_PLANT = new PlantLoad("spinach", 5, 2000.00F, true, new String[]{
//            "civilization_fire:loam_block"}, false, PlantBlock.PlantModel.DestroyModel, 1);
//
//    /*黄花菜*/public static final PlantLoad DAY_LILY_PLANT = new PlantLoad("day_lily", 5, 2000.00F, true, new String[]{
//            "civilization_fire:loam_block,civilization_fire:clay_block"}, false, PlantBlock.PlantModel.DestroyModel, 2);
//    /*大白菜*/public static final PlantLoad BIG_CHINESE_CABBAGE_PLANT = new PlantLoad("big_chinese_cabbage", 5, 2000.00F
//            , true, new String[]{"civilization_fire:loam_block,civilization_fire:clay_block," +
//            "civilization_fire:sand_block"}, false, PlantBlock.PlantModel.DestroyModel, 2);
//
//
//    /*心里美*/public static final PlantLoad SWEET_PINK_FLESHED_RADISH_PLANT = new PlantLoad("sweet_pink_fleshed_radish",
//            5, 2000.00F, true, new String[]{"civilization_fire:clay_block,civilization_fire:sand_block"}, true,
//            PlantBlock.PlantModel.DestroyModel, 4, 3, 3.6F);
//    /*葫芦*/public static final PlantLoad GOURD_PLANT = new PlantLoad("gourd", 5, 2000.00F, true, new String[]{
//            "civilization_fire:sand_block"}, false, PlantBlock.PlantModel.LikePumpkin, 5);
//    /* /*木耳public static final PlantLoad FLAMMULINA_VELUTIPES_PLANT = new PlantLoad("flammulina_velutipes", 4,
//             2000.00F, false, new String[]{"podzolic_soil_block,civilization_fire:mycelial_soil_block"}, false,
//             PlantBlock.PlantModel.OnTree, 1);
//     /*银耳public static final PlantLoad OYSTER_MUSHROOM_PLANT = new PlantLoad("oyster_mushroom", 4, 2000.00F, false,
//             new String[]{"podzolic_soil_block,civilization_fire:mycelial_soil_block"}, false,
//             PlantBlock.PlantModel.OnTree, 3);*/
//    /*杏鲍菇*/public static final PlantLoad RED_BEAN_PLANT = new PlantLoad("red_bean", 5, 2000.00F, true, new String[]{
//            "civilization_fire:loam_block,civilization_fire:sand_block"}, false, PlantBlock.PlantModel.PickingModel, 3);
//    /*金针菇*/public static final PlantLoad MUNG_BEAN_PLANT = new PlantLoad("mung_bean", 5, 2000.00F, true,
//            new String[]{"civilization_fire:sand_block"}, false, PlantBlock.PlantModel.PickingModel, 3);
//    /*平菇*/public static final PlantLoad BLACK_SOYA_BEAN_PLANT = new PlantLoad("black_soya_bean", 5, 2000.00F, true,
//            new String[]{"civilization_fire:loam_block,civilization_fire:clay_block,civilization_fire:sand_block"},
//            false, PlantBlock.PlantModel.PickingModel, 3);
}
