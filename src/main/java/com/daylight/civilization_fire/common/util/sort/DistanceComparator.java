package com.daylight.civilization_fire.common.util.sort;

import java.util.Comparator;

import javax.annotation.Nonnull;

import net.minecraft.world.phys.Vec3;

public class DistanceComparator implements Comparator<Vec3> {
    private final Vec3 location;

    public DistanceComparator(@Nonnull final Vec3 location) {
        this.location = location;
    }

    @Override
    public int compare(Vec3 o1, Vec3 o2) {
        //
        // Sort by distance
        //
        final double distance1 = location.distanceTo(o1);
        final double distance2 = location.distanceTo(o2);
        if (distance1 < distance2) {
            return -1;
        } else if (distance1 > distance2) {
            return 1;
        } else {
            return 0;
        }
    }
}
