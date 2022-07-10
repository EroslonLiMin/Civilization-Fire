package com.daylight.civilization_fire.common.content.entity.bot;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.daylight.civilization_fire.common.CivilizationFire;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;

/**
 * Mining bot, automatic mining after giving ore.
 * @author Heckerpowered
 */
public final class MiningBot extends Bot {

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
    public long getMaxEnergy() {
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
                CivilizationFire.LOGGER.debug("Ore location: ", location.toString());
                target = Optional.empty();
            } else {
                lock.notifyAll();
            }
        }
    }
}
