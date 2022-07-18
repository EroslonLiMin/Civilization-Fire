package com.daylight.civilization_fire.common.content.datapack;

import com.daylight.civilization_fire.client.screen.BotContainerScreen;
import com.daylight.civilization_fire.common.content.entity.bot.Bot;
import com.daylight.civilization_fire.common.content.menu.BotMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;


public class ClientBotScreenOpenPacket {
    private final int containerId;
    private final int entityId;

    public ClientBotScreenOpenPacket(int pContainerId, int pEntityId) {
        this.containerId = pContainerId;
        this.entityId = pEntityId;
    }

    public ClientBotScreenOpenPacket(FriendlyByteBuf pBuffer) {
        this.containerId = pBuffer.readUnsignedByte();
        this.entityId = pBuffer.readInt();
    }

    public static void encode(ClientBotScreenOpenPacket message, FriendlyByteBuf buf) {
        buf.writeInt(message.containerId);
        buf.writeInt(message.entityId);
    }

    public static ClientBotScreenOpenPacket decode(FriendlyByteBuf buf) {
        return new ClientBotScreenOpenPacket(buf.readInt(), buf.readInt());
    }


    public static void handle(ClientBotScreenOpenPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        if (context.getDirection().getReceptionSide().isClient()) {
            context.enqueueWork(() -> {
                Minecraft minecraft = Minecraft.getInstance();
                Player player = minecraft.player;
                if(player != null) {
                    Entity entity = player.level.getEntity(message.getEntityId());
                    if (entity instanceof Bot bot) {
                        LocalPlayer localplayer = minecraft.player;
                        BotMenu botMenu = new BotMenu(message.getContainerId(), localplayer.getInventory(), bot);
                        localplayer.containerMenu = botMenu;
                        minecraft.setScreen(new BotContainerScreen(botMenu, localplayer.getInventory(), new TextComponent("civilization_fire.bot_menu")));
                    }
                }
            });
        }
    }

    public int getContainerId() {
        return this.containerId;
    }


    public int getEntityId() {
        return this.entityId;
    }

}
