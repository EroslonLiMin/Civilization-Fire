package com.daylight.civilization_fire.common.content.entity.bot;

import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
import com.daylight.civilization_fire.common.content.util.CivilizationFireUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * Guardian Bot, attack hostile creatures(Monster) automatically,
 * wandering in a 5x5 range, can be equipped.
 * @author Heckerpowered
 */
public final class GuardianBot extends Bot {
    public GuardianBot(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected final void registerGoals() {
        //
        // Make guardian bot wandering in a 5x5 range with speed 0.9.
        //
        goalSelector.addGoal(1, new MoveTowardsTargetGoal(this, 0.9D, 5.0F));

        //
        // Make guardian bot attack the enemy who is selected by the bot.
        // Bot's attack range is only 1, bot will follow the target if he doesn't see the target.
        //
        // Guardian bot's movement speed will increase from 0.9 to 1.0 when following the target
        //
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));

        //
        // Make guardian bot attack enemies, guardian bot can select
        // enemies as targets without seeing or reaching them.
        //
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 5,
                false, false, e -> e instanceof Enemy));

        //
        // Make guardian bot attack the enemy who attacks him.
        //
        targetSelector.addGoal(2, new HurtByTargetGoal(this));

        //
        // Register other goals.
        //
        super.registerGoals();
    }

    @Override
    public final boolean canAttackType(EntityType<?> entityType) {
        //
        // Make guardian bot not attack player.
        //
        return entityType != EntityType.PLAYER;
    }

    @Override
    public final InteractionResult interactAt(Player player, Vec3 location, InteractionHand hand) {
        if (player.isSpectator()) {
            //
            // Spectators cannot interact with bots.
            //
            return InteractionResult.SUCCESS;
        } else if (player.level.isClientSide) {
            //
            // Do not process in client side.
            //
            return InteractionResult.CONSUME;
        }

        if (player.isShiftKeyDown()) {
            //
            // Press shift to open the gui.
            //
            // TODO: Open gui.
            //
            return InteractionResult.SUCCESS;
        }

        final var item = player.getItemInHand(hand);
        final var equipmentSlot = Mob.getEquipmentSlotForItem(item);

        if (item.isEmpty()) {
            //
            // Take off the equipment from the bot.
            //

            if (getEnergy() <= getMaxEnergy() && item.getItem() instanceof PlantItem.PlantFruitItem fruit) {
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
                    item.shrink(1);
                    return InteractionResult.SUCCESS;
                } else {
                    return InteractionResult.FAIL;
                }
            }

            final var slot = getClickedSlot(location);
            if (hasItemInSlot(slot)) {
                final var itemInSlot = getItemBySlot(slot);
                player.setItemInHand(hand, itemInSlot);
                setItemSlot(slot, ItemStack.EMPTY);
            } else {
                return InteractionResult.FAIL;
            }
        } else {
            //
            // Equipping the bot.
            //

            //
            // Determine whether the slot already has an item.
            //
            if (hasItemInSlot(equipmentSlot)) {
                //
                // Swap items in this slot with items in the player's hand.
                //
                final var swapItem = getItemBySlot(equipmentSlot);
                player.setItemInHand(hand, swapItem);
                setItemSlot(equipmentSlot, item);
            } else {
                //
                // Duplicate the item in the player's hand.
                // It is possible for a player to have more than 1 piece of equipment on hand.
                //
                final var equipmentItem = item.copy();
                equipmentItem.setCount(1);

                setItemSlot(equipmentSlot, equipmentItem);

                //
                // Reduce the number of player items by 1
                //
                item.shrink(1);
            }
        }

        return super.interactAt(player, location, hand);
    }

    @Override
    public long getMaxEnergy() {
        return 10000;
    }
}
