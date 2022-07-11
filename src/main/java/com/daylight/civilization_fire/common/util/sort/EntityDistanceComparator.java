package com.daylight.civilization_fire.common.util.sort;

import java.util.Comparator;

import javax.annotation.Nonnull;

import net.minecraft.world.entity.Entity;

public final class EntityDistanceComparator implements Comparator<Entity> {
    private final Entity entity;

    public EntityDistanceComparator(@Nonnull final Entity entity) {
        this.entity = entity;
    }

    @Override
    public int compare(Entity o1, Entity o2) {
        //
        // Sort by distance
        //
        final double distance1 = entity.position().distanceTo(o1.position());
        final double distance2 = entity.position().distanceTo(o2.position());
        if (distance1 < distance2) {
            return -1;
        } else if (distance1 > distance2) {
            return 1;
        } else {
            return 0;
        }
    }

}
