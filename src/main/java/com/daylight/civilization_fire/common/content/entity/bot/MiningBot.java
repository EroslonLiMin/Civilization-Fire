package com.daylight.civilization_fire.common.content.entity.bot;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

/**
 * Mining bot, automatic mining after giving ore.
 * @author Heckerpowered
 */
public final class MiningBot extends Bot {

    public MiningBot(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public long getMaxEnergy() {
        return 10000;
    }

}
