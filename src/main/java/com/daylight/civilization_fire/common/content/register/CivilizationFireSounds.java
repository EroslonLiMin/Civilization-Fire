package com.daylight.civilization_fire.common.content.register;

import com.daylight.civilization_fire.common.CivilizationFire;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Sounds register.
 * @author Heckerpowered
 */
public final class CivilizationFireSounds {
    private CivilizationFireSounds() {
    }

    /**
     * Sounds deferred register.
     */
    public static final DeferredRegister<SoundEvent> DEFERRED_REGISTER = DeferredRegister
            .create(ForgeRegistries.SOUND_EVENTS, CivilizationFire.MODID);

    public static final RegistryObject<SoundEvent> NEVER_GONNA_GIVE_YOU_UP = DEFERRED_REGISTER.register(
            "never_gonna_give_you_up", () -> new SoundEvent(CivilizationFire.resource("never_gonna_give_you_up")));
}
