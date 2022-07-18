package com.daylight.civilization_fire.common.content.entity.bot;

import com.daylight.civilization_fire.common.content.block.agriculture.SoilBlock;
import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
import com.daylight.civilization_fire.common.util.CivilizationFireUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class FarmingBot extends Bot implements IAnimatable, IAnimationTickable {
    public BlockPos targetPos;
    private static final EntityDataAccessor<Integer> WORK_TIME = SynchedEntityData.defineId(FarmingBot.class,
            EntityDataSerializers.INT);

    public FarmingBot(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(WORK_TIME,0);
    }

    public void setWorkTime(int workTime){
        this.entityData.set(WORK_TIME,workTime);
    }

    public int getWorkTime(){
        return this.entityData.get(WORK_TIME);
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
        if (this.getEnergy() > 100) {
            if (targetPos == null && getWorkTime() <= 0) {
                int range = 5 + this.getCorrespondingAbilityAddLevel();
                for (int x = this.getBlockX() - range; x < this.getBlockX() + range; x++) {
                    for (int y = this.getBlockY() - 1; y < this.getBlockY() + 3; y++) {
                        for (int z = this.getBlockZ() - range; z < this.getBlockZ() + range; z++) {
                            BlockState blockState = this.level.getBlockState(new BlockPos(x, y, z));
                            if (blockState.hasProperty(SoilBlock.BE_PLOUGHED) && !blockState.getValue(SoilBlock.BE_PLOUGHED)) {
                                targetPos = new BlockPos(x, y, z);
                            }
                        }
                    }
                }
            }
            if (this.targetPos != null) {
                this.getNavigation().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), this.followLeashSpeed() * (1 + this.getCorrespondingAbilityAddLevel()));
                double dir = Math.sqrt(Math.pow(targetPos.getX()-this.getOnPos().getX(),2)+Math.pow(targetPos.getY()-this.getOnPos().getY(),2) +Math.pow(targetPos.getZ()-this.getOnPos().getZ(),2));
                if (dir <= 4 && getWorkTime() <= 0) {
                    SoilBlock.setPloughed(null, this.level, this.targetPos, true);
                    this.setEnergy(this.getEnergy() - 100);
                    this.setWorkTime(25);
                }
            }
        }
        if(this.getWorkTime() > 0){
            this.setWorkTime(this.getWorkTime() - 1);
            if(this.getWorkTime() - 1 == 0){
                this.targetPos = null;
                this.setWorkTime(0);
            }
        }
        updateAttributes();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("workTime",getWorkTime());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        setWorkTime(pCompound.getInt("workTime"));
    }

    public void updateAttributes(){
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(30 * (1 + this.getMCAttributeAddLevel()));
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.25 * (1 + this.getMCAttributeAddLevel()));
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
        return 100000 * (1 + this.getEnergyAddLevel());
    }

    @Override
    public final InteractionResult interactAt(Player player, Vec3 location, InteractionHand hand) {
        if(player.isShiftKeyDown() && !player.level.isClientSide()){
            openBotInventory((ServerPlayer) player,this);
            return InteractionResult.SUCCESS;
        }
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
        } else {
            player.addItem(this.writeEntityItemStack());
        }
        return super.interactAt(player, location, hand);
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<FarmingBot> controller = new AnimationController<>(this, "controller", 0,
                this::predicate);
        data.addAnimationController(controller);
    }

    private final AnimationFactory factory = new AnimationFactory(this);
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event) {
        if(this.getWorkTime() > 0){
            event.getController()
                    .setAnimation(new AnimationBuilder().addAnimation("animation.model.work", true));
        }else if (event.isMoving()) {
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
