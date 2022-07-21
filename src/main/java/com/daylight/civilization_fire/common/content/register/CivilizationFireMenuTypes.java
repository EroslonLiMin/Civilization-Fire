package com.daylight.civilization_fire.common.content.register;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.block.agriculture.AgricultureEnchantmentTableBlock.AgricultureEnchantmentTableBlockEntity;
import com.daylight.civilization_fire.common.content.block.cooking.CasseroleBlock;
import com.daylight.civilization_fire.common.content.block.cooking.FoodSteamerBlock;
import com.daylight.civilization_fire.common.content.block.cooking.IronPotBlock;
import com.daylight.civilization_fire.common.content.block.cooking.JuicerBlock;
import com.daylight.civilization_fire.common.content.menu.BotMenu;
import com.daylight.civilization_fire.common.content.menu.agriculture.AgricultureEnchantmentMenu;
import com.daylight.civilization_fire.common.content.menu.cooking.CasseroleMenu;
import com.daylight.civilization_fire.common.content.menu.cooking.FoodSteamerMenu;
import com.daylight.civilization_fire.common.content.menu.cooking.IronPotMenu;
import com.daylight.civilization_fire.common.content.menu.cooking.JuicerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CivilizationFireMenuTypes {
        public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS,
                        CivilizationFire.MODID);

        public static final RegistryObject<MenuType<IronPotMenu>> IRON_POT_MENU = MENUS.register("iron_pot_menu",
                        () -> IForgeMenuType.create((id, inv, data) -> new IronPotMenu(inv, id,
                                        (IronPotBlock.IronPotBlockEntity) inv.player.getLevel()
                                                        .getBlockEntity(data.readBlockPos()))));
        public static final RegistryObject<MenuType<FoodSteamerMenu>> FOOD_STEAMER_MENU = MENUS.register(
                        "food_steamer_menu",
                        () -> IForgeMenuType.create((id, inv, data) -> new FoodSteamerMenu(inv, id,
                                        (FoodSteamerBlock.FoodSteamerBlockEntity) inv.player.getLevel()
                                                        .getBlockEntity(data.readBlockPos()))));
        public static final RegistryObject<MenuType<CasseroleMenu>> CASSEROLE_MENU = MENUS.register("casserole_block",
                        () -> IForgeMenuType.create((id, inv, data) -> new CasseroleMenu(inv, id,
                                        (CasseroleBlock.CasseroleBlockEntity) inv.player.getLevel()
                                                        .getBlockEntity(data.readBlockPos()))));
        public static final RegistryObject<MenuType<JuicerMenu>> JUICER_MENU =
                MENUS.register("juice_menu", () -> IForgeMenuType.create((id, inv, data) ->
                        new JuicerMenu(id,inv, (JuicerBlock.JuicerBlockEntity) inv.player.getLevel().getBlockEntity(data.readBlockPos()))));
        /**
         * Agriculture enchantment table's menu, which is opened when the player right-clicks the block.
         * @author Heckerpowered
         */
        public static final RegistryObject<MenuType<AgricultureEnchantmentMenu>> AGRICULTURE_ENCHANTMENT_MENU = MENUS
                        .register("agriculture_enchantment_menu", () -> IForgeMenuType.create((id, inv,
                                        data) -> new AgricultureEnchantmentMenu(id, inv,
                                                        (AgricultureEnchantmentTableBlockEntity) inv.player.level
                                                                        .getBlockEntity(data.readBlockPos()))));

}
