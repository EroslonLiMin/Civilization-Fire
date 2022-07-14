package com.daylight.civilization_fire.common.content.register;


import com.daylight.civilization_fire.common.content.recipe.CookingDishesType;
import com.daylight.civilization_fire.common.content.recipe.CookingRecipe;
import com.daylight.civilization_fire.common.content.recipe.CookingTool;

//合成列表
public class CivilizationCookingRecipes {
    public CivilizationCookingRecipes(){}

    /*清蒸江团*/
    public static final CookingRecipe STEAMED_SICHUAN_FISH = new CookingRecipe(CookingDishesType.Plate, "steamed_sichuan_fish", CookingTool.FoodSteamer).setNeedCondimentItems("sauce","salt").setNeedCookingItems("salmon","soybean_fruit","shallot_fruit","hot_pepper_fruit","pepper_fruit");
    /*回锅肉*/
    public static final CookingRecipe SICHUAN_COOKED_PORK = new CookingRecipe(CookingDishesType.Plate, "sichuan_cooked_pork", CookingTool.IronPot).setNeedCondimentItems("cooking_wine","salt").setNeedCookingItems("ginger_item","sichuan_pepper_fruit","pepper_fruit","porkchop");
    /*开水白菜*/
    public static final CookingRecipe CHINESE_CABBAGE_SOUP = new CookingRecipe(CookingDishesType.Plate, "chinese_cabbage_soup",CookingTool.Casserole).setNeedCookingItems("big_chinese_cabbage_fruit","porkchop","beef","chicken","bone");
    /*鱼香茄子*/
    public static final CookingRecipe FISH_FLAVORED_EGGPLANT = new CookingRecipe(CookingDishesType.Plate, "fish_flavored_eggplant",CookingTool.IronPot).setNeedCondimentItems("oil","salt","sauce,").setNeedCookingItems("eggplant_fruit","salmon","sichuan_pepper_fruit","pepper_fruit");
    /*干炒牛河*/
    public static final CookingRecipe STIR_FRIED_BEEF = new CookingRecipe(CookingDishesType.Plate, "stir_fried_beef",CookingTool.IronPot).setNeedCondimentItems("oil","salt","sauce").setNeedCookingItems("carob_fruit","beef").setCanChangeCookingItems(new CookingRecipe.CanChangeCookingItem("carob_fruit","broad_bean_fruit","young_soybean_fruit","lentil_horn_fruit","white_bean_horn_item","peanut_item"));
    /*酿豆腐*/
    public static final CookingRecipe STUFFED_BEAN_CURD = new CookingRecipe(CookingDishesType.Plate, "stuffed_bean_curd",CookingTool.FoodSteamer).setNeedCondimentItems("salt","oil").setNeedCookingItems("carob_fruit","porkchop").setCanChangeCookingItems(new CookingRecipe.CanChangeCookingItem("carob_fruit","broad_bean_fruit","young_soybean_fruit","lentil_horn_fruit","white_bean_horn_item","peanut_item"));
    /*三及第*/
    public static final CookingRecipe HAKKA_SUOP = new CookingRecipe(CookingDishesType.Bowl, "hakka_suop",CookingTool.Casserole).setNeedCondimentItems().setNeedCookingItems("salt").setNeedCookingItems("porkchop");
    /*胡萝卜玉米排骨汤*/
    public static final CookingRecipe CARROT_CORN_PORK_SOUP = new CookingRecipe(CookingDishesType.Bowl, "carrot_corn_pork_soup",CookingTool.Casserole).setNeedCookingItems("porkchop","water_radish_fruit","corn_item");
    /*萝卜丸*/
    public static final CookingRecipe RABBIS_MESS = new CookingRecipe(CookingDishesType.Plate, "rabbis_mess",CookingTool.FoodSteamer).setNeedCondimentItems("oil","salt","sauce").setNeedCookingItems("ternip_fruit");
    /*酿苦瓜*/
    public static final CookingRecipe STUFFED_BITTER_MELON = new CookingRecipe(CookingDishesType.Plate, "stuffed_bitter_melon",CookingTool.Casserole).setNeedCondimentItems("sauce","salt").setNeedCookingItems("balsam_pear_fruit","ginger_fruit","porkchop");
    /*九转大肠*/
    public static final CookingRecipe BRAISED_INTESTINES_BROWN_SAUCE = new CookingRecipe(CookingDishesType.Plate, "braised_intestines_brown_sauce",CookingTool.IronPot).setNeedCondimentItems("oil","salt","vinegar").setNeedCookingItems("carob_fruit","cinnamon_bark_fruit","porkchop").setCanChangeCookingItems(new CookingRecipe.CanChangeCookingItem("carob_fruit","broad_bean_fruit","young_soybean_fruit","lentil_horn_fruit","white_bean_horn_item","peanut_item"));
    /*扬州炒饭*/
    public static final CookingRecipe YANGZHOU_FRIED_RICE = new CookingRecipe(CookingDishesType.Plate, "yangzhou_fried_rice",CookingTool.IronPot).setNeedCookingItems("rice_item","egg","porkchop","water_radish_fruit","carob_fruit").setNeedCondimentItems("oil","salt","sauce").setCanChangeCookingItems(new CookingRecipe.CanChangeCookingItem("rice_item","glutinous_rice_item","yellow_rice_item","sorghum_item")).setCanChangeCookingItems(new CookingRecipe.CanChangeCookingItem("carob_fruit","broad_bean_fruit","young_soybean_fruit","lentil_horn_fruit","white_bean_horn_item","peanut_item"));
    /*番茄炒鸡蛋*/
    public static final CookingRecipe SCRAMBLED_TOMATO_EGG = new CookingRecipe(CookingDishesType.Plate, "scrambled_tomato_egg",CookingTool.IronPot).setNeedCondimentItems("oil","salt","sauce","vinegar").setNeedCookingItems("tomatoes_fruit","egg");
    /*白米饭*/
    public static final CookingRecipe RICE = new CookingRecipe(CookingDishesType.Plate, "rice",CookingTool.FoodSteamer).setNeedCookingItems("rice_item").setCanChangeCookingItems(new CookingRecipe.CanChangeCookingItem("rice_item","glutinous_rice_item","yellow_rice_item","sorghum_item"));
    /*杂粮饭*/
    public static final CookingRecipe COARSE_GRAIN_RICE = new CookingRecipe(CookingDishesType.Plate, "coarse_grain_rice",CookingTool.FoodSteamer).setNeedCookingItems("rice_item","glutinous_rice_item","yellow_rice_item","sorghum_item");
    /*芹菜炒肉*/
    public static final CookingRecipe CELERY_FRIED_MEAT = new CookingRecipe(CookingDishesType.Plate, "celery_fried_meat",CookingTool.IronPot).setNeedCondimentItems("oil","salt","sauce").setNeedCookingItems("celery_fruit","porkchop");
    /*酸辣黄瓜*/
    public static final CookingRecipe HOT_SOUR_CUCUMBER = new CookingRecipe(CookingDishesType.Plate, "hot_sour_cucumber",CookingTool.IronPot).setNeedCondimentItems("vinegar","salt").setNeedCookingItems("cucumber_fruit","porkchop");
    /*玉米冬瓜汤*/
    public static final CookingRecipe CORN_GOURD_SOUP = new CookingRecipe(CookingDishesType.Bowl, "corn_gourd_soup",CookingTool.Casserole).setNeedCondimentItems("salt").setNeedCookingItems("corn_item","wax_gourd_fruit");
    /*丝瓜炒蛋*/
    public static final CookingRecipe TOWEL_GOURD_EGG = new CookingRecipe(CookingDishesType.Plate, "towel_gourd_egg",CookingTool.IronPot).setNeedCondimentItems("oil","salt","sauce").setNeedCookingItems("towel_gourd_fruit","egg");
    /*卤鸡蛋*/
    public static final CookingRecipe HALOGEN_EGG = new CookingRecipe(CookingDishesType.Plate, "halogen_egg",CookingTool.Casserole).setNeedCookingItems("egg","fennel_fruit","fragrant_fruit","cinnamon_bark_fruit");
    /*卤肉*/
    public static final CookingRecipe POT_STEWED_MEAT = new CookingRecipe(CookingDishesType.Plate, "pot_stewed_meat",CookingTool.Casserole).setNeedCookingItems("fennel_fruit","fragrant_fruit","cinnamon_bark_fruit","porkchop","chicken","beef");


}
