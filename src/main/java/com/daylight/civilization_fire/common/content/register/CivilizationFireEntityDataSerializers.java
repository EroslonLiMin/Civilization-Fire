package com.daylight.civilization_fire.common.content.register;

import com.daylight.civilization_fire.common.CivilizationFire;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraftforge.registries.DataSerializerEntry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Entity data serializers.
 * @author Heckerpowered
 */
public final class CivilizationFireEntityDataSerializers {
    /**
     * TODO: REGISTER SERIALIZER CORRECTLY
     */
    private CivilizationFireEntityDataSerializers() {
    }

    /**
     * The deferred register of entity data serializer.
     */
    public static final DeferredRegister<DataSerializerEntry> DEFERRED_REGISTER = DeferredRegister.create(
            ForgeRegistries.Keys.DATA_SERIALIZERS, CivilizationFire.MODID);

    public static final RegistryObject<DataSerializerEntry> LONG = DEFERRED_REGISTER.register("long",
            () -> new DataSerializerEntry(Serializers.LONG));

    public static class Serializers {
        private Serializers() {
        }

        /**
         * 64-bit integer serializer.
         */
        public static final EntityDataSerializer<Long> LONG = new EntityDataSerializer<Long>() {

            @Override
            public void write(FriendlyByteBuf buffer, Long value) {
                buffer.writeLong(value);
            }

            @Override
            public Long read(FriendlyByteBuf buffer) {
                return buffer.readLong();
            }

            @Override
            public Long copy(Long value) {
                return value;
            }

        };
    }
}
