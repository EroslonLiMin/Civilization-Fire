package com.daylight.civilization_fire.common.network.packet.severbound;

import javax.annotation.Nonnull;

import com.daylight.civilization_fire.client.screen.agriculture.AgricultureAnvilScreen;
import com.daylight.civilization_fire.common.content.menu.agriculture.AgricultureAnvilMenu;
import com.daylight.civilization_fire.common.network.CivilizationFirePacket;

import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent.Context;

/**
 * The serverbound rename item packet.
 *
 * @author Heckerpowered
 * @see AgricultureAnvilScreen
 */
@FieldsAreNonnullByDefault
@MethodsReturnNonnullByDefault
public final class ServerboundRenameItemPacket implements CivilizationFirePacket {
    private final String name;

    public ServerboundRenameItemPacket(@Nonnull final String name) {
        this.name = name;
    }

    @Override
    public final void handle(@Nonnull final Context context) {
        //
        // Check if the sender is available.
        //
        final var sender = context.getSender();
        if (sender.containerMenu != null && sender.containerMenu instanceof AgricultureAnvilMenu menu) {
            menu.setItemName(name);
        }
    }

    @Override
    public final void encode(@Nonnull final FriendlyByteBuf buffer) {
        buffer.writeUtf(name);
    }

    public static final ServerboundRenameItemPacket decode(@Nonnull final FriendlyByteBuf buffer) {
        return new ServerboundRenameItemPacket(buffer.readUtf());
    }

}
