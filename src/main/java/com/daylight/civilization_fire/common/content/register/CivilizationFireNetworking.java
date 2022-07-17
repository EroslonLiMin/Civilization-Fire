package com.daylight.civilization_fire.common.content.register;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.content.datapck.ClientBotScreenOpenPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CivilizationFireNetworking {
    public static final String VERSION = "1.0";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(CivilizationFire.MODID, "network"),
            () -> VERSION, it -> it.equals(VERSION), it -> it.equals(VERSION));
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessage() {
        CHANNEL.registerMessage(0, ClientBotScreenOpenPacket.class, ClientBotScreenOpenPacket::encode, ClientBotScreenOpenPacket::decode, ClientBotScreenOpenPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_CLIENT));

    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(CivilizationFireNetworking::registerMessage);
    }
}
