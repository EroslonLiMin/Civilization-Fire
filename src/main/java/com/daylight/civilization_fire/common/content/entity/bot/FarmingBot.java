package com.daylight.civilization_fire.common.content.entity.bot;

import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
import com.daylight.civilization_fire.common.util.CivilizationFireUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FarmingBot extends Bot {
    public FarmingBot(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    /**
     * Create a new attribute supplier for the bot.
     *
     * @return
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D).add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    @Override
    public int getMaxEnergy() {
        return 0;
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
        final var stack = player.getItemInHand(hand);
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
                }
            } else {
                return InteractionResult.FAIL;
            }
        }
        return super.interactAt(player, location, hand);
    }
}
