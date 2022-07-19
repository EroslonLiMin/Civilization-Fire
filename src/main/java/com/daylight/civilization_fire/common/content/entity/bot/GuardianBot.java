package com.daylight.civilization_fire.common.content.entity.bot;

import javax.annotation.Nullable;

import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
import com.daylight.civilization_fire.common.util.CivilizationFireUtil;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

/**
 * Guardian Bot, attack hostile creatures(Monster) automatically,
 * wandering in a 5x5 range, can be equipped.
 * @author Heckerpowered
 */
public final class GuardianBot extends Bot implements IAnimatable, IAnimationTickable {
    /**
     * The target the bot about to attack.
     */
    @Nullable
    private Monster target;

    public GuardianBot(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected final void registerGoals() {
        //
        // Make guardian bot attack the enemy who is selected by the bot.
        // Bot's attack range is only 1, bot will follow the target if he doesn't see the target.
        //
        // Guardian bot's movement speed will increase from 0.9 to 1.0 when following the target
        //
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));

        //
        // Make guardian bot wandering in a 5x5 range with speed 0.9.
        //
        goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 5.0F));

        //
        // Make guardian bot look around randomly.
        //
        goalSelector.addGoal(3, new RandomLookAroundGoal(this));

        //
        // Make guardian bot attack the enemy who attacks him.
        //
        targetSelector.addGoal(1, new HurtByTargetGoal(this));

        //
        // Make guardian bot attack enemies, guardian bot can select
        // enemies as targets without seeing or reaching them.
        //
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, 5,
                false, false, e -> e instanceof Enemy));
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

        if (player.isShiftKeyDown() && !player.level.isClientSide()) {
            //
            // Press shift to open the gui.
            //

            //
            // TODO: Open gui.
            //
            openBotInventory((ServerPlayer) player,this);
            return InteractionResult.SUCCESS;
        }

        final var stack = player.getItemInHand(hand);
        final var equipmentSlot = Mob.getEquipmentSlotForItem(stack);

        if (stack.isEmpty()) {
            //
            // Take off the equipment from the bot.
            //
            final var slot = getClickedSlot(location);
            if (hasItemInSlot(slot)) {
                final var itemInSlot = getItemBySlot(slot);
                player.setItemInHand(hand, itemInSlot);
                setItemSlot(slot, ItemStack.EMPTY);
            } else {
                return InteractionResult.FAIL;
            }
        } else {
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

            //
            // Equipping the bot.
            //

            if (item instanceof ArmorItem || item instanceof DiggerItem
                    || item instanceof SwordItem) {
                //
                // Determine whether the slot already has an item.
                //
                if (hasItemInSlot(equipmentSlot)) {
                    //
                    // Swap items in this slot with items in the player's hand.
                    //
                    final var swapItem = getItemBySlot(equipmentSlot);
                    player.setItemInHand(hand, swapItem);
                    setItemSlot(equipmentSlot, stack);
                } else {
                    //
                    // Duplicate the item in the player's hand.
                    // It is possible for a player to have more than 1 piece of equipment on hand.
                    //
                    final var equipmentItem = stack.copy();
                    equipmentItem.setCount(1);

                    setItemSlot(equipmentSlot, equipmentItem);

                    //
                    // Reduce the number of player items by 1
                    //
                    stack.shrink(1);
                }
            }
        }

        return super.interactAt(player, location, hand);
    }

    @Override
    public int getMaxEnergy() {
        return 10000 * (1 + this.getEnergyAddLevel());
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        final var sourceEntity = source.getEntity();
        if (sourceEntity instanceof Monster monster && distanceToSqr(sourceEntity) <= 25.0D) {
            target = monster;
        }

        return super.hurt(source, amount);
    }

    /**
     * Create a new attribute supplier for the bot.
     * @return
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D).add(Attributes.ATTACK_DAMAGE, 5.0D).add(Attributes.ARMOR,2);
    }

    @Override
    public void tick() {
        super.tick();
        updateAttributes();
    }

    public void updateAttributes(){
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20 * (1 + this.getMCAttributeAddLevel() + this.getCorrespondingAbilityAddLevel()));
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.25 * (1 + this.getMCAttributeAddLevel() + this.getCorrespondingAbilityAddLevel()));
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(5 * (1 + this.getMCAttributeAddLevel() + this.getCorrespondingAbilityAddLevel()));
        this.getAttribute(Attributes.ARMOR).setBaseValue(2 * (1 + this.getMCAttributeAddLevel() + this.getCorrespondingAbilityAddLevel()));
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<GuardianBot> controller = new AnimationController<>(this, "controller", 0,
                this::predicate);
        data.addAnimationController(controller);
    }

    public void aiStep() {
        this.updateSwingTime();
        super.aiStep();
    }

    private final AnimationFactory factory = new AnimationFactory(this);
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event) {
        if(this.swingTime > 0){
            event.getController()
                    .setAnimation(new AnimationBuilder().addAnimation("animation.model.attack", true));
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
