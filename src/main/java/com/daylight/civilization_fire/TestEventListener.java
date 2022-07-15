package com.daylight.civilization_fire;

import com.daylight.civilization_fire.client.screen.FireWorkScreen;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber
public class TestEventListener {

    @SubscribeEvent
    public static void onKeyDown(InputEvent.KeyInputEvent event) {
        if (event.getKey() == GLFW.GLFW_KEY_K) {
            Minecraft.getInstance().setScreen(new FireWorkScreen());
        }
    }

}
