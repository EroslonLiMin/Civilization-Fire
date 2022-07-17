package com.daylight.civilization_fire.common.content.entity.bot;

import com.daylight.civilization_fire.common.content.block.agriculture.SoilBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class FarmingBot extends Bot {
    public BlockPos targetPos;

    public FarmingBot(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected final void registerGoals() {
        //
        // Make guardian bot look around randomly.
        //
        goalSelector.addGoal(3, new RandomLookAroundGoal(this));

        //
        // Make guardian bot attack the enemy who attacks him.
        //
        targetSelector.addGoal(1, new HurtByTargetGoal(this));

    }

    @Override
    public void tick() {
        super.tick();
        if (targetPos == null) {
            for (int x = this.getBlockX() - 10; x < this.getBlockX() + 10; x++) {
                for (int y = this.getBlockY() - 10; y < this.getBlockY() + 10; y++) {
                    for (int z = this.getBlockZ() - 10; z < this.getBlockZ() + 10; z++) {
                        BlockState blockState = this.level.getBlockState(new BlockPos(x, y, z));
                        if (blockState.hasProperty(SoilBlock.BE_PLOUGHED)
                                && !blockState.getValue(SoilBlock.BE_PLOUGHED)) {
                            targetPos = new BlockPos(x, y, z);
                        }
                    }
                }
            }
        }
        if (this.targetPos != null) {
            this.getNavigation().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), this.followLeashSpeed());
            if (targetPos.distSqr(this.getOnPos()) < 2) {
                SoilBlock.setPloughed(null, this.level, this.targetPos, true);
            }
        }
    }

    /**
     * Create a new attribute supplier for the bot.
     *
     * @return
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D).add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }

    @Override
    public int getMaxEnergy() {
        return 0;
    }
}
