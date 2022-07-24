package com.daylight.civilization_fire.common.network;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

/**
 * Packet interface, in order to register packet, define static method
 * "decode(FriendlyByteBuf)" in your packet class.
 *
 * @author Heckerpowered
 */
public interface CivilizationFirePacket {
    /**
     * Handle packet.
     *
     * @param context Network contxt.
     */
    void handle(@Nonnull final NetworkEvent.Context context);

    /**
     * Encode packet.
     *
     * @param buffer Byte buffer.
     */
    void encode(@Nonnull final FriendlyByteBuf buffer);

    /**
     * Handle packet staticly.
     *
     * @param <T> Packet.
     * @param message Content.
     * @param networkContext Network content.
     */
    static <T extends CivilizationFirePacket> void handle(@Nonnull final T message,
            @Nonnull Supplier<NetworkEvent.Context> networkContext) {
        if (message != null) {
            //
            // Message should never be null unless something went horribly wrong decoding.
            // In which case we don't want to try enqueuing handling it, or set the packet
            // as handled
            //
            final var context = networkContext.get();
            context.enqueueWork(() -> message.handle(context));
            context.setPacketHandled(true);
        }
    }
}
