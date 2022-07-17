package com.daylight.civilization_fire.common.content.entity.bot;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.daylight.civilization_fire.common.CivilizationFire;

import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

/**
 * Mining bot, automatic mining after giving ore.
 * @author Heckerpowered
 */
@FieldsAreNonnullByDefault
@MethodsReturnNonnullByDefault
public final class MiningBot extends Bot implements IAnimatable, IAnimationTickable {

    /**
     * Worker thread to find ores in one chunk.
     */
    @Nullable
    private Thread worker;

    /**
     * Locked ore location.
     */
    @Nonnull
    private Optional<BlockPos> target;

    /**
     * After the worker thread finds the location of the ore, it calls lock.wait(),
     * until the ore is mined, then it calls lock.notify().
     */
    @Nonnull
    private Object lock;

    public MiningBot(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        target = Optional.empty();
        lock = new Object();
    }

    @Override
    public int getMaxEnergy() {
        return 10000;
    }

    @Override
    public void tick() {
        super.tick();

        if (level.isClientSide) {
            //
            // Process in server.
            //
            return;
        }

        if (energyAvailable()) {
            if (worker == null) {
                //
                // It is coroutine... in some ways?
                //
                worker = new Thread(() -> {
                    final var chunkPosition = chunkPosition();
                    final var maxX = chunkPosition.getMaxBlockX();
                    final var maxZ = chunkPosition.getMaxBlockZ();
                    final var maxY = level.getMaxBuildHeight();

                    for (int x = chunkPosition.getMinBlockX(); x < maxX; x = -~x) {
                        for (int z = chunkPosition.getMinBlockZ(); z < maxZ; z = -~z) {
                            for (int y = level.getMinBuildHeight(); y < maxY; y = -~y) {
                                //
                                // Is it thread safe ?
                                //
                                final var blockState = level.getBlockState(new BlockPos(x, y, z));
                                if (blockState.is(Tags.Blocks.ORES)) {
                                    target = Optional.of(new BlockPos(x, y, z));
                                    try {
                                        lock.wait();
                                    } catch (InterruptedException e) {
                                        //
                                        // Clears interrupt state.
                                        //
                                        CivilizationFire.LOGGER.error("Lock released.", e);
                                    }
                                }
                            }
                        }
                    }

                    //
                    // Clears current worker so that new worker will be created next tick.
                    //
                    worker = null;
                });
            } else if (target.isPresent()) {
                //
                // Try to move to target location.
                //
                final var location = target.get();
                CivilizationFire.LOGGER.debug("Ore location: {}", location.toString());
                target = Optional.empty();
            } else {
                lock.notifyAll();
            }
        }
    }

    /**
     * Create a new attribute supplier for the bot.
     * @return
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D).add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<MiningBot> controller = new AnimationController<>(this, "controller", 0,
                this::predicate);
        data.addAnimationController(controller);
    }

    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event) {
        if (this.swinging) {
            event.getController()
                    .setAnimation(new AnimationBuilder().addAnimation("animation.model.attack", true));
        } else if (event.isMoving()) {
            event.getController()
                    .setAnimation(new AnimationBuilder().addAnimation("animation.model.move", true));
        } else {
            event.getController()
                    .setAnimation(new AnimationBuilder().addAnimation("animation.model.stand", false));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public int tickTimer() {
        return this.tickCount;
    }

}
