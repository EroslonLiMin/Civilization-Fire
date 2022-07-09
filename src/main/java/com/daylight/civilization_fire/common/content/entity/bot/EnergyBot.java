package com.daylight.civilization_fire.common.content.entity.bot;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnegative;

public interface EnergyBot {
    /**
     * Get the energy of the bot.
     * @return the energy of the bot, always positive.
     */
    @Nonnegative
    public int getEnergy();

    /**
     * Set the energy of the bot.
     * @param energy Energy amount to be set, cannot be negative.
     */
    public void setEnergy(@Nonnegative @CheckForSigned int energy);
}
