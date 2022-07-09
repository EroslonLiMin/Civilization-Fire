package com.daylight.civilization_fire.common.network.synchronization;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;

/**
 * Entity data serializers.
 * @author Heckerpowered
 */
public final class CivilizationFireEntityDataSerializers {
    private CivilizationFireEntityDataSerializers() {
    }

    /**
     * 64-bit integer serializer.
     * @author Heckerpowered
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
