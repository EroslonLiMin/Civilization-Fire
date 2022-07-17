package com.daylight.civilization_fire.common.content.entity.bot;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
import com.daylight.civilization_fire.common.util.CivilizationFireUtil;

import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * All bot's base class.
 * @author Heckerpowered
 */
@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
public abstract class Bot extends PathfinderMob {
    /**
     * Synchornize bot's energy.
     * @author Heckerpowered
     */
    private static final String ENERGY_STRING = "civilization_fire_energy";

    public Bot(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    /**
     * Get the energy of the bot.
     * @return the energy of the bot, always positive.
     * @author Heckerpowered
     */
    public final @Nonnegative long getEnergy() {
        return getPersistentData().getLong(ENERGY_STRING);
    }

    /**
     * Set the energy of the bot.
     *
     * @param energy Energy amount to be set, cannot be negative.
     * check for unsigned, do not check unsigned before call.
     * @author Heckerpowered
     */
    public final void setEnergy(@Nonnegative @CheckForSigned long energy) {
        if (energy < 0) {
            energy = 0;
        }

        getPersistentData().putLong(ENERGY_STRING, energy);
    }

    /**
     * Get the max energy of the bot.
     *
     * @return the max energy of the bot, always poostive.
     * @author Heckerpowered
     */
    public abstract @Nonnegative int getMaxEnergy();

    /**
     * Get the energy needed per tick.
     *
     * @return energy per tick.
     */
    public @Nonnegative int getEnergyCost() {
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
    public void tick() {
        super.tick();
        setEnergy(getEnergy() - getEnergyCost());
    }

    @Override
    public void aiStep() {
        if (energyAvailable() || level.isClientSide) {
            super.aiStep();
        }
    }

    /**
     * Determine if there is energy left, returns true if
     * current energy greater than zero.
     *
     * @return Returns true if current energy greater than zero.
     */
    protected final boolean energyAvailable() {
        return getEnergy() != 0;
    }

    /**
     * Charges the bot with an item, returns InteractionResult.SUCCESS if the item was charged.
     * Return InteractionResult.FAIL if one of the following conditions is true:
     * The item is not a valid energy source (fruit item), the fruit item don't
     * have grow time (The amount of energy charged depends on fruit's grow time),
     * the current energy reaches the bot's maxium energy. The overflow during
     * charging is allowed. e.g. When the current energy plus the energy charged
     * is greater than the maxium energy, the current energy can be greater than
     * the maxium energy. However, you can't charge when the current energy is greater
     * than or equal to the maxium energy.
     *
     * @param stack ItemStack to charge
     * @return Return InteractionResult.SUCCESS if the item was charged, Return
     * InteractionResult.FAIL if the item was not charged.
     */
    protected final InteractionResult charge(@Nonnull final ItemStack stack) {
        final var item = stack.getItem();
        if (item instanceof PlantItem.PlantFruitItem fruit) {
            if (getEnergy() <= getMaxEnergy()) {
                //
                // Charge the bot.
                //
                final var growTime = CivilizationFireUtil.getPlantGrowTime(fruit);
                if (growTime.isPresent()) {
                    //
                    // Allow a small "overflow" when charging,
                    // So we don't need to check the amount of charge.
                    //
                    setEnergy(getEnergy() + growTime.get());
                    stack.shrink(1);
                    return InteractionResult.SUCCESS;
                } else {
                    return InteractionResult.FAIL;
                }
            } else {
                return InteractionResult.FAIL;
            }
        } else {
            return InteractionResult.FAIL;
        }
    }

    @Override
    public InteractionResult interactAt(Player player, Vec3 vec, InteractionHand hand) {
        if (!player.level.isClientSide) {
            return charge(player.getItemInHand(hand));
        }

        return super.interactAt(player, vec, hand);
    }
}
