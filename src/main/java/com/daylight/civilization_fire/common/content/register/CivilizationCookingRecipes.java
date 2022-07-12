package com.daylight.civilization_fire.common.content.register;


import com.daylight.civilization_fire.common.content.recipe.CookingDishesType;
import com.daylight.civilization_fire.common.content.recipe.CookingRecipe;
import com.daylight.civilization_fire.common.content.recipe.CookingTool;

//合成列表
public class CivilizationCookingRecipes {
    public CivilizationCookingRecipes(){}

    /*清蒸江团*/
    public static final CookingRecipe STEAMED_SICHUAN_FISH = new CookingRecipe(CookingDishesType.Plate, "steamed_sichuan_fish", CookingTool.FoodSteamer).setNeedCondimentItems("sauce","salt").setNeedCookingItems("minecraft:fish","soybean","shallot","hot_pepper","pepper");
    /*回锅肉*/
    public static final CookingRecipe SICHUAN_COOKED_PORK = new CookingRecipe(CookingDishesType.Plate, "sichuan_cooked_pork", CookingTool.IronPot).setNeedCondimentItems("cooking_wine","salt").setNeedCookingItems("ginger","sichuan_pepper","pepper","minecraft:porkchop");
    /*开水白菜*/
    public static final CookingRecipe CHINESE_CABBAGE_SOUP = new CookingRecipe(CookingDishesType.Plate, "chinese_cabbage_soup",CookingTool.Casserole).setNeedCookingItems("big_chinese_cabbage","minecraft:porkchop","minecraft:beef","minecraft:chicken","minecraft:bone");
    /*鱼香茄子*/
    public static final CookingRecipe FISH_FLAVORED_EGGPLANT = new CookingRecipe(CookingDishesType.Plate, "fish_flavored_eggplant",CookingTool.IronPot).setNeedCondimentItems("oil","salt","sauce,").setNeedCookingItems("eggplant","minecraft:fish","sichuan_pepper","pepper");
    /*干炒牛河*/
    public static final CookingRecipe STIR_FRIED_BEEF = new CookingRecipe(CookingDishesType.Plate, "stir_fried_beef",CookingTool.IronPot).setNeedCondimentItems("oil","salt","sauce").setNeedCookingItems("carob","minecraft:beef").setCanChangeCookingItems(new CookingRecipe.CanChangeCookingItem("carob","broad_bean","young_soybean","lentil_horn","white_bean_horn","peanut"));
    /*酿豆腐*/
    public static final CookingRecipe STUFFED_BEAN_CURD = new CookingRecipe(CookingDishesType.Plate, "stuffed_bean_curd",CookingTool.FoodSteamer).setNeedCondimentItems("salt","oil").setNeedCookingItems("carob","minecraft:porkchop").setCanChangeCookingItems(new CookingRecipe.CanChangeCookingItem("carob","broad_bean","young_soybean","lentil_horn","white_bean_horn","peanut"));
    /*三及第*/
    public static final CookingRecipe HAKKA_SUOP = new CookingRecipe(CookingDishesType.Bowl, "hakka_suop",CookingTool.Casserole).setNeedCondimentItems().setNeedCookingItems("salt").setNeedCookingItems("minecraft:porkchop");
    /*胡萝卜玉米排骨汤*/
    public static final CookingRecipe CARROT_CORN_PORK_SOUP = new CookingRecipe(CookingDishesType.Bowl, "carrot_corn_pork_soup",CookingTool.Casserole).setNeedCookingItems("minecraft:porkchop","water_radish","corn");
    /*萝卜丸*/
    public static final CookingRecipe RABBIS_MESS = new CookingRecipe(CookingDishesType.Plate, "rabbis_mess",CookingTool.FoodSteamer).setNeedCondimentItems("oil","salt","sauce").setNeedCookingItems("ternip");
    /*酿苦瓜*/
    public static final CookingRecipe STUFFED_BITTER_MELON = new CookingRecipe(CookingDishesType.Plate, "stuffed_bitter_melon",CookingTool.Casserole).setNeedCondimentItems("sauce","salt").setNeedCookingItems("balsam_pear","ginger","minecraft:porkchop");
    /*九转大肠*/
    public static final CookingRecipe BRAISED_INTESTINES_BROWN_SAUCE = new CookingRecipe(CookingDishesType.Plate, "braised_intestines_brown_Sauce",CookingTool.IronPot).setNeedCondimentItems("oil","salt","vinegar").setNeedCookingItems("carob","cinnamon","minecraft:porkchop").setCanChangeCookingItems(new CookingRecipe.CanChangeCookingItem("carob","broad_bean","young_soybean","lentil_horn","white_bean_horn","peanut"));
    /*扬州炒饭*/
    public static final CookingRecipe YANGZHOU_FRIED_RICE = new CookingRecipe(CookingDishesType.Plate, "yangzhou_fried_rice",CookingTool.IronPot).setNeedCookingItems("rice","minecraft:egg","minecraft:porkchop","water_radish","carob").setNeedCondimentItems("oil","salt","sauce").setCanChangeCookingItems(new CookingRecipe.CanChangeCookingItem("rice","glutinous_rice","yellow_rice","sorghum","white_bean_horn","peanut")).setCanChangeCookingItems(new CookingRecipe.CanChangeCookingItem("carob","broad_bean","young_soybean","lentil_horn","white_bean_horn","peanut"));
    /*番茄炒鸡蛋*/
    public static final CookingRecipe SCRAMBLED_TOMATO_EGG = new CookingRecipe(CookingDishesType.Plate, "scrambled_tomato_egg",CookingTool.IronPot).setNeedCondimentItems("oil","salt","sauce","vinegar").setNeedCookingItems("tomatoes","minecraft:egg");
    /*白米饭*/
    public static final CookingRecipe RICE = new CookingRecipe(CookingDishesType.Plate, "rice",CookingTool.FoodSteamer).setNeedCookingItems("rice").setCanChangeCookingItems(new CookingRecipe.CanChangeCookingItem("rice","glutinous_rice","yellow_rice","sorghum","white_bean_horn","peanut"));
    /*杂粮饭*/
    public static final CookingRecipe COARSE_GRAIN_RICE = new CookingRecipe(CookingDishesType.Plate, "coarse_grain_rice",CookingTool.FoodSteamer).setNeedCookingItems("rice","glutinous_rice","yellow_rice","sorghum","white_bean_horn","peanut");
    /*芹菜炒肉*/
    public static final CookingRecipe CELERY_FRIED_MEAT = new CookingRecipe(CookingDishesType.Plate, "celery_fried_meat",CookingTool.IronPot).setNeedCondimentItems("oil","salt","sauce").setNeedCookingItems("celery","minecraft:porkchop");
    /*酸辣黄瓜*/
    public static final CookingRecipe HOT_SOUR_CUCUMBER = new CookingRecipe(CookingDishesType.Plate, "hot_sour_cucumber",CookingTool.IronPot).setNeedCondimentItems("vinegar","salt").setNeedCookingItems("cucumber","minecraft:porkchop");
    /*玉米冬瓜汤*/
    public static final CookingRecipe CORN_GOURD_SOUP = new CookingRecipe(CookingDishesType.Bowl, "corn_gourd_soup",CookingTool.Casserole).setNeedCondimentItems("salt").setNeedCookingItems("corn","wax_gourd");
    /*丝瓜炒蛋*/
    public static final CookingRecipe TOWEL_GOURD_EGG = new CookingRecipe(CookingDishesType.Plate, " towel_gourd_egg",CookingTool.IronPot).setNeedCondimentItems("oil","salt","sauce").setNeedCookingItems("towel_gourd","minecraft:egg");
    /*卤鸡蛋*/
    public static final CookingRecipe HALOGEN_EGG = new CookingRecipe(CookingDishesType.Plate, "halogen_egg",CookingTool.Casserole).setNeedCookingItems("egg","fennel","fragrant_leaf","cinnamon");
    /*卤肉*/
    public static final CookingRecipe POT_STEWED_MEAT = new CookingRecipe(CookingDishesType.Plate, "pot_stewed_meat",CookingTool.Casserole).setNeedCookingItems("fennel","fragrant_leaf","cinnamon","minecraft:porkchop","minecraft:chicken","minecraft:beef");


}
