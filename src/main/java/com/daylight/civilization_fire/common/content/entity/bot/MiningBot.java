package com.daylight.civilization_fire.common.content.entity.bot;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.daylight.civilization_fire.common.CivilizationFire;

import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
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
     * This mutex is used to interrupt the worker thread after
     * any ore is found, after the worker thread finds the location of the ore,
     * it calls lock.wait() to interrupt worker thread, until the ore is mined,
     * then it calls lock.notify() to awake the worker thread.
     */
    private Object lock = new Object();

    /**
     * Worker thread to find ores in one chunk.
     */
    @Nullable
    private Thread worker;

    /**
     * Locked ore location.
     */
    @Nonnull
    private Optional<BlockPos> target = Optional.empty();

    public MiningBot(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public int getMaxEnergy() {
        return 10000;
    }

    /**
     * Mining bot won't process logical when following condition is true,
     * The bot is on the client side, or there is no energy left.
     * if the worker thread is not presented yet, it will create one,
     * the worker thread will try to find any ore in bot's chunk,
     * after any ore is found, it will call lock.wait() to interrupt
     * the worker thread, until the ore is mined, then it will call
     * lock.notify() to awake worker thread.
     */
    @Override
    public void tick() {
        super.tick();

        if (level.isClientSide) {
            //
            // Process in server.
            //
            return;
        }

        if (energyAvailable() && this.getEnergy() > 100) {
            if (worker == null) {
                //
                // It is coroutine... in some ways?
                //
                worker = new Thread(() -> {
                    final var chunkPosition = chunkPosition();
                    final var maxX = chunkPosition.getMaxBlockX();
                    final var maxZ = chunkPosition.getMaxBlockZ();
                    final var maxY = level.getMaxBuildHeight();

                    for (int x = chunkPosition.getMinBlockX(); x <= maxX; x = -~x) {
                        for (int z = chunkPosition.getMinBlockZ(); z <= maxZ; z = -~z) {
                            for (int y = level.getMinBuildHeight(); y <= maxY; y = -~y) {
                                //
                                // Is it thread safe ?
                                //
                                final var blockState = level.getBlockState(new BlockPos(x, y, z));
                                if (blockState.is(Tags.Blocks.ORES)) {
                                    target = Optional.of(new BlockPos(x, y, z));

                                    CivilizationFire.LOGGER.debug("Ore location: {}", target.get().toString());

                                    //
                                    // Hold the lock, so that we can wait until the ore is mined.
                                    //
                                    synchronized (lock) {
                                        try {
                                            //
                                            // Wait until the ore is mined.
                                            //
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
                    }

                    //
                    // Clears current worker so that new worker will be created next tick.
                    //
                    worker = null;
                });

                //
                // Start worker thread.
                //
                worker.start();
            } else if (target.isPresent()) {
                //
                // Get the ore's location.
                //
                final var location = target.get();
                final var blockState = level.getBlockState(location);

                if (level instanceof ServerLevel serverLevel) {
                    for (final var drop : blockState
                            .getDrops(new LootContext.Builder(serverLevel).withRandom(level.random).withParameter(
                                    LootContextParams.TOOL, ItemStack.EMPTY)
                                    .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(location))
                                    .withOptionalParameter(LootContextParams.BLOCK_ENTITY,
                                            level.getBlockEntity(location))
                                    .withParameter(LootContextParams.THIS_ENTITY, this)
                                    .withParameter(LootContextParams.BLOCK_STATE, blockState))) {
                        //
                        // Drop item, create a new item entity.
                        //
                        final var itemEntity = new ItemEntity(serverLevel, getX(), getY(), getZ(), drop);

                        //
                        // Spawn entity in the level.
                        //
                        serverLevel.addFreshEntity(itemEntity);
                        this.setEnergy(this.getEnergy() - 100);
                    }

                    //
                    // Destroy the block.
                    //
                    serverLevel.destroyBlock(location, false);

                    target = Optional.empty();
                } else {
                    CivilizationFire.LOGGER.info("level not instanceof ServerLevel, this should be impossible.");
                }
            } else {
                //
                // Hold the lock, so that we can call lock.notifyAll() to
                // awake the worker thread.
                //
                synchronized (lock) {
                    lock.notifyAll();
                }
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
