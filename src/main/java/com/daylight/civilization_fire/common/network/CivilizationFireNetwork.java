package com.daylight.civilization_fire.common.network;

import java.util.Optional;
import java.util.function.Function;

import javax.annotation.Nonnull;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.network.packet.severbound.ServerboundRenameItemPacket;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerLifecycleHooks;

/**
 * The civilization fire network, used to register and send packets.
 *
 * @author Heckerpowered
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class CivilizationFireNetwork {
    /**
    * Registered packet index.
    */
    private static int index = 0;

    private CivilizationFireNetwork() {
    }

    /**
     * Mod version string, which matches the one in the build.gradle
     */
    private static final String VERSION = new StringBuffer().append(CivilizationFire.VERSION.getMajorVersion())
            .append('.').append(CivilizationFire.VERSION.getMinorVersion()).append('.')
            .append(CivilizationFire.VERSION.getBuildNumber()).toString();

    /**
     * Network channel.
     */
    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(CivilizationFire.resource(CivilizationFire.MODID)).clientAcceptedVersions(VERSION::equals)
            .serverAcceptedVersions(VERSION::equals).networkProtocolVersion(() -> VERSION).simpleChannel();

    /**
     * Register packet send to server.
     *
     * @param <T> Packet type.
     * @param type The class of the packet.
     * @param decoder The decoder function of the packet.
     */
    public static final <T extends CivilizationFirePacket> void registerServerboundMessage(@Nonnull final Class<T> type,
            @Nonnull final Function<FriendlyByteBuf, T> decoder) {
        registerMessage(type, decoder, NetworkDirection.PLAY_TO_SERVER);
    }

    /**
     * Register packet send to client.
     *
     * @param <T> Packet type.
     * @param type The class of the packet.
     * @param decoder The decoder function of the packet.
     */
    public static final <T extends CivilizationFirePacket> void registerClientboundMessage(@Nonnull final Class<T> type,
            @Nonnull final Function<FriendlyByteBuf, T> decoder) {
        registerMessage(type, decoder, NetworkDirection.PLAY_TO_CLIENT);
    }

    /**
     * Register packet send to specific direction.
     *
     * @param <T> The type of the packet.
     * @param type The class of the packet.
     * @param decoder The decoder for the packet.
     * @param direction The direction of the packet send to.
     */
    public static final <T extends CivilizationFirePacket> void registerMessage(@Nonnull final Class<T> type,
            @Nonnull final Function<FriendlyByteBuf, T> decoder,
            @Nonnull final NetworkDirection direction) {
        CHANNEL.registerMessage(index++, type, CivilizationFirePacket::encode, decoder,
                CivilizationFirePacket::handle, Optional.of(direction));
    }

    /**
     * Send the packet to the specified player.
     *
     * @param <T> The type of the packet.
     * @param message Message to send.
     * @param player The player to send the packet to, it cannot be fake player.
     */
    public static final <T extends CivilizationFirePacket> void sendTo(@Nonnull final T message,
            @Nonnull final ServerPlayer player) {
        //
        // Validate it is not a fake player, even though none of
        // the code should call this with a fake player.
        //
        if (player instanceof FakePlayer) {
            CivilizationFire.LOGGER.error("Attempt to send packet to fake player {}", player);
            return;
        }

        CHANNEL.sendTo(message, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }

    /**
     * Send the message to everyone connected to the server.
     *
     * @param <T> The type of the packet.
     * @param message Message to send.
     */
    public static final <T extends CivilizationFirePacket> void sendToAll(@Nonnull final T message) {
        CHANNEL.send(PacketDistributor.ALL.noArg(), message);
    }

    /**
     * Send this message to everyone connected to the server if the server has loaded.
     *
     * @param <T> The type of the packet.
     * @param message Message to send.
     */
    public static final <T extends CivilizationFirePacket> void sendToAllIfLoaded(@Nonnull final T message) {
        if (ServerLifecycleHooks.getCurrentServer() != null) {
            //
            // If the server is loaded, send to all players.
            //
            sendToAll(message);
        }
    }

    /**
     * Send the message to everyone within the supplied level.
     *
     * @param <T> The type of the packet.
     * @param message Message to send.
     * @param level The level to send the message to.
     */
    public static final <T extends CivilizationFirePacket> void sendToLevel(@Nonnull final T message,
            ResourceKey<Level> level) {
        CHANNEL.send(PacketDistributor.DIMENSION.with(() -> level), message);
    }

    /**
     * Send the message to the server.
     *
     * @param <T> The type of the packet.
     * @param message Message to send.
     */
    public static final <T extends CivilizationFirePacket> void sendToServer(@Nonnull final T message) {
        CHANNEL.sendToServer(message);
    }

    /**
     * Send to all tracking the entity.
     *
     * @param <T> The type of the packet.
     * @param message Message to send.
     * @param entity Entity tracked.
     */
    public static final <T extends CivilizationFirePacket> void sendToAllTracking(@Nonnull final T message,
            @Nonnull final Entity entity) {
        CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
    }

    /**
     * Send to all tracking the entity and the entity self.
     *
     * @param <T> The type of the packet.
     * @param message Message to send.
     * @param entity Entity tracked.
     */
    public static final <T extends CivilizationFirePacket> void sendToAllTrackingAndSelf(@Nonnull final T message,
            @Nonnull final Entity entity) {
        CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), message);
    }

    /**
     * Send to all tracking the bock entity.
     *
     * @param <T> The type of the packet.
     * @param message Message to send.
     * @param entity Block entity tracked.
     */
    public static final <T extends CivilizationFirePacket> void sendToAllTracking(@Nonnull final T message,
            @Nonnull final BlockEntity entity) {
        sendToAllTracking(message, entity.getLevel(), entity.getBlockPos());
    }

    /**
     * Send to all tracking the chunk.
     *
     * @param <T> The type of the packet.
     * @param message Message to send.
     * @param level The level of the chunk.
     * @param location The location of the block in the chunk.
     */
    public static final <T extends CivilizationFirePacket> void sendToAllTracking(@Nonnull final T message,
            @Nonnull final Level level, @Nonnull final BlockPos location) {
        if (level instanceof ServerLevel serverLevel) {
            //
            // If we have a ServerLevel just directly figure out the ChunkPos to
            // not require looking up the chunk.
            //
            // This provides a decent performance boost over using the packet distributor.
            //

            //
            // Resource leak: '<unassigned Closeable value>' is not closed at this location
            //
            final var serverChunkCache = serverLevel.getChunkSource();
            for (final var player : serverChunkCache.chunkMap.getPlayers(new ChunkPos(location), false)) {
                sendTo(message, player);
            }
        } else {
            //
            // Other wise, fallback to entities tracking the chunk if some mod did
            // something odd and our level is not a ServerLevel.
            //
            CHANNEL.send(PacketDistributor.TRACKING_CHUNK
                    .with(() -> level.getChunk(location.getX() >> 4, location.getZ() >> 4)),
                    message);
        }
    }

    public static final void initialize() {
        registerServerboundMessage(ServerboundRenameItemPacket.class, ServerboundRenameItemPacket::decode);
    }

    /**
     * Common setup event, used to register messages.
     *
     * @param event {@link FMLCommonSetupEvent} common setup event.
     */
    @SubscribeEvent
    public static final void onCommonSetup(@Nonnull final FMLCommonSetupEvent event) {
        event.enqueueWork(CivilizationFireNetwork::initialize);
    }
}
