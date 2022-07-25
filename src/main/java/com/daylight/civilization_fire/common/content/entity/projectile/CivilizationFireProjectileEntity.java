package com.daylight.civilization_fire.common.content.entity.projectile;

import com.daylight.civilization_fire.common.content.entity.bot.FarmingBot;
import com.daylight.civilization_fire.common.content.register.CivilizationEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class CivilizationFireProjectileEntity extends AbstractArrow implements ItemSupplier {
    private static final EntityDataAccessor<ItemStack> ENTITY_ITEM_STACK = SynchedEntityData.defineId(FarmingBot.class,
            EntityDataSerializers.ITEM_STACK);

    public CivilizationFireProjectileEntity(EntityType<? extends AbstractArrow> type, Level world) {
        super(type, world);
    }

    public CivilizationFireProjectileEntity(EntityType<? extends CivilizationFireProjectileEntity> type, double x, double y, double z, Level world) {
        super(type, x, y, z, world);
    }

    public CivilizationFireProjectileEntity(EntityType<? extends CivilizationFireProjectileEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    public CivilizationFireProjectileEntity(PlayMessages.SpawnEntity spawnEntity, Level level) {
        super(CivilizationEntityTypes.CIVILIZATION_FIRE_PROJECTILE_ENTITY.get(),level);
    }


    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setEntityItemStack(ItemStack.of(pCompound.getCompound("entityItemStack")));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.put("entityItemStack",getEntityItemStack().serializeNBT());
    }

    public void setEntityItemStack(ItemStack stack){
        this.entityData.set(ENTITY_ITEM_STACK,stack);
    }

    public ItemStack getEntityItemStack(){
        return this.entityData.get(ENTITY_ITEM_STACK);
    }
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem() {
        return this.entityData.get(ENTITY_ITEM_STACK);
    }

    @Override
    protected ItemStack getPickupItem() {
        return this.entityData.get(ENTITY_ITEM_STACK);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ENTITY_ITEM_STACK,ItemStack.EMPTY);
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity) {
        super.doPostHurtEffects(entity);
        entity.setArrowCount(entity.getArrowCount() - 1);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.inGround)
            this.discard();
        if(getEntityItemStack() == ItemStack.EMPTY){
            this.kill();
        }
    }

    public static CivilizationFireProjectileEntity shoot(Level world, LivingEntity entity, float power, double damage, int knockback,ItemStack stack) {
        CivilizationFireProjectileEntity civilizationFireProjectileEntity = new CivilizationFireProjectileEntity(CivilizationEntityTypes.CIVILIZATION_FIRE_PROJECTILE_ENTITY.get(), entity, world);
        civilizationFireProjectileEntity.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
        civilizationFireProjectileEntity.setSilent(true);
        civilizationFireProjectileEntity.setEntityItemStack(stack);
        civilizationFireProjectileEntity.setCritArrow(false);
        civilizationFireProjectileEntity.setBaseDamage(damage);
        civilizationFireProjectileEntity.setKnockback(knockback);
        world.addFreshEntity(civilizationFireProjectileEntity);
        return civilizationFireProjectileEntity;
    }

    public static CivilizationFireProjectileEntity shoot(LivingEntity entity, LivingEntity target,ItemStack stack) {
        CivilizationFireProjectileEntity civilizationFireProjectileEntity = new CivilizationFireProjectileEntity(CivilizationEntityTypes.CIVILIZATION_FIRE_PROJECTILE_ENTITY.get(), entity, entity.level);
        double dx = target.getX() - entity.getX();
        double dy = target.getY() + target.getEyeHeight() - 1.1;
        double dz = target.getZ() - entity.getZ();
        civilizationFireProjectileEntity.shoot(dx, dy - civilizationFireProjectileEntity.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1f * 2, 12.0F);
        civilizationFireProjectileEntity.setSilent(true);
        civilizationFireProjectileEntity.setEntityItemStack(stack);
        civilizationFireProjectileEntity.setBaseDamage(5);
        civilizationFireProjectileEntity.setKnockback(5);
        civilizationFireProjectileEntity.setCritArrow(false);
        entity.level.addFreshEntity(civilizationFireProjectileEntity);
        return civilizationFireProjectileEntity;
    }

}
