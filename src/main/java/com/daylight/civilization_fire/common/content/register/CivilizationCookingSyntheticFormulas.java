package com.daylight.civilization_fire.common.content.register;

import com.daylight.civilization_fire.data.cooking.CookingDishesType;
import com.daylight.civilization_fire.data.cooking.CookingSyntheticFormula;
import com.daylight.civilization_fire.data.cooking.CookingTool;

//合成列表
public class CivilizationCookingSyntheticFormulas {

    /*清蒸江团*/
    public static final CookingSyntheticFormula STEAMED_SICHUAN_FISH = new CookingSyntheticFormula(CookingDishesType.Plate, "steamed_sichuan_fish", CookingTool.FoodSteamer).setNeedCondimentItems("sauce","salt").setNeedCookingItems("minecraft:fish","soybean","shallot","hot_pepper","pepper");
    /*回锅肉*/
    public static final CookingSyntheticFormula SICHUAN_COOKED_PORK = new CookingSyntheticFormula(CookingDishesType.Plate, "sichuan_cooked_pork",CookingTool.IronPot).setNeedCondimentItems("cooking_wine","salt").setNeedCookingItems("ginger","sichuan_pepper","pepper","minecraft:porkchop");
    /*开水白菜*/
    public static final CookingSyntheticFormula CHINESE_CABBAGE_SOUP = new CookingSyntheticFormula(CookingDishesType.Plate, "chinese_cabbage_soup",CookingTool.Casserole).setNeedCookingItems("big_chinese_cabbage","minecraft:porkchop","minecraft:beef","minecraft:chicken","minecraft:bone");
    /*鱼香茄子*/
    public static final CookingSyntheticFormula FISH_FLAVORED_EGGPLANT = new CookingSyntheticFormula(CookingDishesType.Plate, "fish_flavored_eggplant",CookingTool.IronPot).setNeedCondimentItems("oil","salt","sauce,").setNeedCookingItems("eggplant","minecraft:fish","sichuan_pepper","pepper");
    /*干炒牛河*/
    public static final CookingSyntheticFormula STIR_FRIED_BEEF = new CookingSyntheticFormula(CookingDishesType.Plate, "stir_fried_beef",CookingTool.IronPot).setNeedCondimentItems("oil","salt","sauce").setNeedCookingItems("carob","minecraft:beef").setCanChangeCookingItems(new CookingSyntheticFormula.CanChangeCookingItem("carob","broad_bean","young_soybean","lentil_horn","white_bean_horn","peanut"));
    /*酿豆腐*/
    public static final CookingSyntheticFormula STUFFED_BEAN_CURD = new CookingSyntheticFormula(CookingDishesType.Plate, "stuffed_bean_curd",CookingTool.FoodSteamer).setNeedCondimentItems("salt","oil").setNeedCookingItems("carob","minecraft:porkchop").setCanChangeCookingItems(new CookingSyntheticFormula.CanChangeCookingItem("carob","broad_bean","young_soybean","lentil_horn","white_bean_horn","peanut"));
    /*三及第*/
    public static final CookingSyntheticFormula HAKKA_SUOP = new CookingSyntheticFormula(CookingDishesType.Bowl, "hakka_suop",CookingTool.Casserole).setNeedCondimentItems().setNeedCookingItems("salt").setNeedCookingItems("minecraft:porkchop");
    /*胡萝卜玉米排骨汤*/
    public static final CookingSyntheticFormula CARROT_CORN_PORK_SOUP = new CookingSyntheticFormula(CookingDishesType.Bowl, "carrot_corn_pork_soup",CookingTool.Casserole).setNeedCookingItems("minecraft:porkchop","water_radish","corn");
    /*萝卜丸*/
    public static final CookingSyntheticFormula RABBIS_MESS = new CookingSyntheticFormula(CookingDishesType.Plate, "rabbis_mess",CookingTool.FoodSteamer).setNeedCondimentItems("oil","salt","sauce").setNeedCookingItems("ternip");
    /*酿苦瓜*/
    public static final CookingSyntheticFormula STUFFED_BITTER_MELON = new CookingSyntheticFormula(CookingDishesType.Plate, "stuffed_bitter_melon",CookingTool.Casserole).setNeedCondimentItems("sauce","salt").setNeedCookingItems("balsam_pear","ginger","minecraft:porkchop");
    /*九转大肠*/
    public static final CookingSyntheticFormula BRAISED_INTESTINES_BROWN_SAUCE = new CookingSyntheticFormula(CookingDishesType.Plate, "braised_intestines_brown_Sauce",CookingTool.IronPot).setNeedCondimentItems("oil","salt","vinegar").setNeedCookingItems();
    /*扬州炒饭*/
    public static final CookingSyntheticFormula YANGZHOU_FRIED_RICE = new CookingSyntheticFormula(CookingDishesType.Plate, "yangzhou_fried_rice",CookingTool.IronPot).setNeedCookingItems();
    /*番茄炒鸡蛋*/
    public static final CookingSyntheticFormula SCRAMBLED_TOMATO_EGG = new CookingSyntheticFormula(CookingDishesType.Plate, "scrambled_tomato_egg",CookingTool.IronPot).setNeedCondimentItems("oil","salt","sauce","vinegar").setNeedCookingItems();
    /*白米饭*/
    public static final CookingSyntheticFormula RICE = new CookingSyntheticFormula(CookingDishesType.Plate, "rice",CookingTool.FoodSteamer).setNeedCookingItems();
    /*杂粮饭*/
    public static final CookingSyntheticFormula COARSE_GRAIN_RICE = new CookingSyntheticFormula(CookingDishesType.Plate, "coarse_grain_rice",CookingTool.FoodSteamer).setNeedCookingItems();
    /*芹菜炒肉*/
    public static final CookingSyntheticFormula CELERY_FRIED_MEAT = new CookingSyntheticFormula(CookingDishesType.Plate, "celery_fried_meat",CookingTool.IronPot).setNeedCondimentItems("oil","salt","sauce").setNeedCookingItems();
    /*酸辣黄瓜*/
    public static final CookingSyntheticFormula HOT_SOUR_CUCUMBER = new CookingSyntheticFormula(CookingDishesType.Plate, "hot_sour_cucumber",CookingTool.IronPot).setNeedCondimentItems("vinegar","salt").setNeedCookingItems();
    /*玉米冬瓜汤*/
    public static final CookingSyntheticFormula CORN_GOURD_SOUP = new CookingSyntheticFormula(CookingDishesType.Bowl, "corn_gourd_soup",CookingTool.Casserole).setNeedCondimentItems("salt").setNeedCookingItems();
    /*丝瓜炒蛋*/
    public static final CookingSyntheticFormula TOWEL_GOURD_EGG = new CookingSyntheticFormula(CookingDishesType.Plate, " towel_gourd_egg",CookingTool.IronPot).setNeedCondimentItems("oil","salt","sauce").setNeedCookingItems();
    /*卤鸡蛋*/
    public static final CookingSyntheticFormula HALOGEN_EGG = new CookingSyntheticFormula(CookingDishesType.Plate, "halogen_egg",CookingTool.Casserole).setNeedCookingItems();
    /*卤肉*/
    public static final CookingSyntheticFormula POT_STEWED_MEAT = new CookingSyntheticFormula(CookingDishesType.Plate, "pot_stewed_meat",CookingTool.Casserole).setNeedCookingItems();


}
