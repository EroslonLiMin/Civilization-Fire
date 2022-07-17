package com.daylight.civilization_fire.common.content.entity.agriculture;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BucketsWaterwheelEntity extends IrrigationEntity {
    public BucketsWaterwheelEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public int getIrrigationLevel() {
        return 10;
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<BucketsWaterwheelEntity> controller = new AnimationController<>(this, "controller", 0,
                this::predicate);
        data.addAnimationController(controller);
    }

    //属性处理
    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 50)
                .add(Attributes.FOLLOW_RANGE, 40.0D);
    }

    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event) {
        if (this.level.getBlockState(this.getOnPos().above()).getBlock() instanceof BucketPickup) {
            event.getController()
                    .setAnimation(new AnimationBuilder().addAnimation("animation.model.running", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public int tickTimer() {
        return this.tickCount;
    }
}
