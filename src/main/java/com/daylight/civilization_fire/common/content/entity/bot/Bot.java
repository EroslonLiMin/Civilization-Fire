package com.daylight.civilization_fire.common.content.entity.bot;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.daylight.civilization_fire.common.CivilizationFire;
import com.daylight.civilization_fire.common.network.synchronization.CivilizationFireEntityDataSerializers;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * All bot's base class.
 * @author Heckerpowered
 */
public abstract class Bot extends PathfinderMob {
    /**
     * Synchornize bot's energy.
     * @author Heckerpowered
     */
    private static final EntityDataAccessor<Long> DATA_ENERGY = SynchedEntityData.defineId(
            GuardianBot.class, CivilizationFireEntityDataSerializers.LONG);

    public Bot(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    /**
     * Get the energy of the bot.
     * @return the energy of the bot, always positive.
     * @author Heckerpowered
     */
    public final @Nonnegative long getEnergy() {
        return getEntityData().get(DATA_ENERGY);
    }

    /**
     * Set the energy of the bot.
     * @param energy Energy amount to be set, cannot be negative.
     * check for unsigned, do not check unsigned before call.
     * @author Heckerpowered
     */
    public final void setEnergy(@Nonnegative @CheckForSigned long energy) {
        if (energy < 0) {
            //
            // Whenever possible, we should log an error and continue to run, rather than crash.
            //
            CivilizationFire.LOGGER.error("Energy cannot be negative: {}", energy);
            energy = 0;
        }

        getEntityData().set(DATA_ENERGY, energy);
    }

    /**
     * Get the max energy of the bot.
     * @return the max energy of the bot, always poostive.
     * @author Heckerpowered
     */
    public abstract @Nonnegative long getMaxEnergy();

    /**
     * Get the energy needed per tick.
     * @return energy per tick.
     */
    public @Nonnegative long getEnergyCost() {
        return 1;
    }

    /**
    * Determine which part of the bot the player clicked on.
    *
    * @param location The location player clicked on.
    * @return The slot player clicked on.
    * @see ArmorStand.getClickedSlot(Vec3)
    */
    protected final @Nonnull EquipmentSlot getClickedSlot(@Nonnull final Vec3 location) {
        final var isBaby = isBaby();
        final var y = isBaby ? location.y * 2.0D : location.y;
        final var slot = EquipmentSlot.FEET;

        if (y >= 0.1D && y < 0.1D + (isBaby ? 0.8D : 0.45D) && hasItemInSlot(slot)) {
            return EquipmentSlot.FEET;
        } else if (y >= 0.9D + (isBaby ? 0.3D : 0.0D) && y < 0.9D + (isBaby ? 1.0D : 0.7D)
                && hasItemInSlot(EquipmentSlot.CHEST)) {
            return EquipmentSlot.CHEST;
        } else if (y >= 0.4D && y < 0.4D + (isBaby ? 1.0D : 0.8D) && hasItemInSlot(EquipmentSlot.LEGS)) {
            return EquipmentSlot.LEGS;
        } else if (y >= 1.6D && hasItemInSlot(EquipmentSlot.HEAD)) {
            return EquipmentSlot.HEAD;
        } else if (!hasItemInSlot(EquipmentSlot.MAINHAND) && hasItemInSlot(EquipmentSlot.OFFHAND)) {
            return EquipmentSlot.OFFHAND;
        } else {
            return EquipmentSlot.MAINHAND;
        }
    }

    @Override
    public final void aiStep() {
        if (energyAvailable()) {
            //
            // If there is no energy, make the AI stop working.
            //
            super.aiStep();
        } else {
            //
            // setEnergy tests parameter is unsigned, so we don't need to check it.
            //
            setEnergy(getEnergy() - getEnergyCost());
        }
    }

    protected final boolean energyAvailable() {
        return getEnergy() != 0;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        //
        // Define energy serializer.
        //
        entityData.define(DATA_ENERGY, 0L);
    }
}
