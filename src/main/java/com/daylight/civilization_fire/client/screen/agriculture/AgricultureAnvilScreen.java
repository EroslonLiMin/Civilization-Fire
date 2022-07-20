package com.daylight.civilization_fire.client.screen.agriculture;

import javax.annotation.Nonnull;

import com.daylight.civilization_fire.client.screen.BaseContainerScreen;
import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.menu.agriculture.AgricultureAnvilMenu;

import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * The agriculture anvil screen, open when the player right-clicks the agriculture anvil.
 *
 * @author Heckerpowered
 */
@FieldsAreNonnullByDefault
@MethodsReturnNonnullByDefault
@OnlyIn(Dist.CLIENT)
public final class AgricultureAnvilScreen extends BaseContainerScreen<AgricultureAnvilMenu> {
    /**
     * The ResourceLocation containg the Enchantment content backrgound texture location.
     */
    private static final ResourceLocation ANVIL_LOCATION = CivilizationFire.resource("textures/screen/anvil.png");

    private EditBox name;

    /**
     * Create a new agriculture anvil screen.
     *
     * @param menu The menu of the screen.
     * @param inventory The player's inventory.
     * @param title The title of the screen.
     */
    public AgricultureAnvilScreen(@Nonnull final AgricultureAnvilMenu menu, @Nonnull final Inventory inventory,
            @Nonnull final Component title) {
        super(menu, inventory, title, ANVIL_LOCATION);
    }

    @Override
    protected final void init() {
        super.init();

        //
        // Create a new edit box.
        //
        name = new EditBox(font, 61, 57, 94, 12, new TranslatableComponent("container.repair"));

        //
        // Set the edit box's properties.
        //
        name.setCanLoseFocus(false);
        name.setTextColor(-1);
        name.setTextColorUneditable(-1);
        name.setBordered(false);
        name.setMaxLength(50);
        name.setResponder(this::onNameChanged);
        name.setValue("");
        addWidget(name);
        setInitialFocus(name);

        //
        // Set the edit box editable when the input stack is presented.
        //
        name.setEditable(false);
    }

    /**
     * The callback when the name of the input stack is changed.
     * @param name The new name of the input stack.
     */
    private void onNameChanged(@Nonnull final String name) {

    }
}
