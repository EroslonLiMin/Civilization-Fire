package com.daylight.civilization_fire.common.content.entity.bot;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnegative;

/**
 * Bot that work with energy.
 * @author Heckerpowered
 */
public interface EnergyBot {
    /**
     * Get the energy of the bot.
     * @return the energy of the bot, always positive.
     * @author Heckerpowered
     */
    @Nonnegative
    public int getEnergy();

    /**
     * Set the energy of the bot.
     * @param energy Energy amount to be set, cannot be negative.
     * @author Heckerpowered
     */
    public void setEnergy(@Nonnegative @CheckForSigned int energy);

    /**
     * Get the max energy of the bot.
     * @return the max energy of the bot, always poostive.
     * @author Heckerpowered
     */
    @Nonnegative
    public int getMaxEnergy();
}
