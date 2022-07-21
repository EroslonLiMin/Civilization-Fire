package com.daylight.civilization_fire.common.content.entity.bot;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.daylight.civilization_fire.common.content.datapack.ClientBotScreenOpenPacket;
import com.daylight.civilization_fire.common.content.item.agriculture.BotAddItem;
import com.daylight.civilization_fire.common.content.item.agriculture.PlantItem;
import com.daylight.civilization_fire.common.content.menu.BotMenu;
import com.daylight.civilization_fire.common.content.register.CivilizationFireNetworking;
import com.daylight.civilization_fire.common.util.CivilizationFireUtil;
import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;

/**
 * All bot's base class.
 * @author Heckerpowered,Eroslon_Day
 */
@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
public abstract class Bot extends PathfinderMob {

    private static final EntityDataAccessor<Integer> ENERGY = SynchedEntityData.defineId(Bot.class,
            EntityDataSerializers.INT);
    //能力列表
    private static final EntityDataAccessor<ItemStack> ENTITY_ITEM_STACK = SynchedEntityData.defineId(FarmingBot.class,
            EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<ItemStack> CORRESPONDING_ABILITY_ADD = SynchedEntityData.defineId(FarmingBot.class,
            EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<ItemStack> ENERGY_ADD = SynchedEntityData.defineId(FarmingBot.class,
            EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<ItemStack> MC_ATTRIBUTE_ADD = SynchedEntityData.defineId(FarmingBot.class,
            EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<ItemStack> FRUIT_ADD = SynchedEntityData.defineId(FarmingBot.class,
            EntityDataSerializers.ITEM_STACK);

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ENERGY, 0);
        this.entityData.define(CORRESPONDING_ABILITY_ADD, ItemStack.EMPTY);
        this.entityData.define(ENERGY_ADD, ItemStack.EMPTY);
        this.entityData.define(MC_ATTRIBUTE_ADD, ItemStack.EMPTY);
        this.entityData.define(FRUIT_ADD, ItemStack.EMPTY);
        this.entityData.define(ENTITY_ITEM_STACK, ItemStack.EMPTY);
    }

    public Bot(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    public void setEntityItemStack(ItemStack itemStack){
        this.entityData.set(ENTITY_ITEM_STACK,itemStack);
    }

    public ItemStack getEntityItemStack(){
        return this.entityData.get(ENTITY_ITEM_STACK);
    }

    public ItemStack writeEntityItemStack(){
        ItemStack itemStack = this.entityData.get(ENTITY_ITEM_STACK);
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        compoundTag.putInt("energy",this.getEnergy());
        compoundTag.put("corresponding_ability_add",getCorrespondingAbilityAdd().serializeNBT());
        compoundTag.put("energy_add",getEnergyAdd().serializeNBT());
        compoundTag.put("mc_attribute",getMCAttributeAdd().serializeNBT());
        compoundTag.put("fruit_add",getFruitAdd().serializeNBT());
        return itemStack;
    }

    public void readDataFromItemStack(ItemStack itemStack){
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        setCorrespondingAbilityAdd(ItemStack.of(compoundTag.getCompound("corresponding_ability_add")));
        setEnergyAdd(ItemStack.of(compoundTag.getCompound("energy_add")));
        setMcAttributeAdd(ItemStack.of(compoundTag.getCompound("mc_attribute")));
        setFruitAdd(ItemStack.of(compoundTag.getCompound("fruit_add")));
        setEnergy(compoundTag.getInt("energy"));
        setEntityItemStack(itemStack);
    }


    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("energy",getEnergy());
        pCompound.put("corresponding_ability_add",getCorrespondingAbilityAdd().serializeNBT());
        pCompound.put("energy_add",getEnergyAdd().serializeNBT());
        pCompound.put("mc_attribute",getMCAttributeAdd().serializeNBT());
        pCompound.put("fruit_add",getFruitAdd().serializeNBT());
        pCompound.put("entity_item_stack",getEntityItemStack().serializeNBT());
    }



    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        setCorrespondingAbilityAdd(ItemStack.of(pCompound.getCompound("corresponding_ability_add")));
        setEnergyAdd(ItemStack.of(pCompound.getCompound("energy_add")));
        setMcAttributeAdd(ItemStack.of(pCompound.getCompound("mc_attribute")));
        setFruitAdd(ItemStack.of(pCompound.getCompound("fruit_add")));
        setEnergy(pCompound.getInt("energy"));
        setEntityItemStack(ItemStack.of(pCompound.getCompound("entity_item_stack")));
    }

    public ItemStack getFruitAdd() {
        return this.entityData.get(FRUIT_ADD);
    }

    public void setFruitAdd(ItemStack energyAdd) {
        this.entityData.set(FRUIT_ADD, energyAdd);
    }

    public ItemStack getCorrespondingAbilityAdd() {
        return this.entityData.get(CORRESPONDING_ABILITY_ADD);
    }

    public void setCorrespondingAbilityAdd(ItemStack correspondingAbilityAdd) {
        this.entityData.set(CORRESPONDING_ABILITY_ADD, correspondingAbilityAdd);
    }

    public int getCorrespondingAbilityAddLevel(){
        return (getCorrespondingAbilityAdd().getItem() instanceof BotAddItem && ((BotAddItem)getCorrespondingAbilityAdd().getItem()).botAddType == BotAddItem.BotAddType.CorrespondingAbilityAdd) ? ((BotAddItem)getCorrespondingAbilityAdd().getItem()).level : 0;
    }


    public ItemStack getEnergyAdd() {
        return this.entityData.get(ENERGY_ADD);
    }

    public void setEnergyAdd(ItemStack energyAdd) {
        this.entityData.set(ENERGY_ADD, energyAdd);
    }

    public int getEnergyAddLevel(){
        return (getEnergyAdd().getItem() instanceof BotAddItem && ((BotAddItem)getEnergyAdd().getItem()).botAddType == BotAddItem.BotAddType.EnergyAdd) ? ((BotAddItem)getEnergyAdd().getItem()).level : 0;
    }

    public ItemStack getMCAttributeAdd() {
        return this.entityData.get(MC_ATTRIBUTE_ADD);
    }

    public int getMCAttributeAddLevel(){
        return (getMCAttributeAdd().getItem() instanceof BotAddItem && ((BotAddItem)getMCAttributeAdd().getItem()).botAddType == BotAddItem.BotAddType.MCAttributeAdd) ? ((BotAddItem)getMCAttributeAdd().getItem()).level : 0;
    }

    public void setMcAttributeAdd(ItemStack mcAttributeAdd) {
        this.entityData.set(MC_ATTRIBUTE_ADD, mcAttributeAdd);
    }

    /**
     * Get the energy of the bot.
     *
     * @return the energy of the bot, always positive.
     * @author Heckerpowered
     */
    public final @Nonnegative
    int getEnergy() {
        return this.entityData.get(ENERGY);
    }

    /**
     * Set the energy of the bot.
     *
     * @param energy Energy amount to be set, cannot be negative.
     *               check for unsigned, do not check unsigned before call.
     * @author Heckerpowered
     */
    public final void setEnergy(@Nonnegative @CheckForSigned int energy) {

        this.entityData.set(ENERGY, energy);
    }

    /**
     * Get the max energy of the bot.
     *
     * @return the max energy of the bot, always poostive.
     * @author Heckerpowered
     */
    public abstract @Nonnegative
    int getMaxEnergy();

    /**
     * Get the energy needed per tick.
     *
     * @return energy per tick.
     */
    public @Nonnegative
    int getEnergyCost() {
        return 1;
    }

    /**
     * Determine which part of the bot the player clicked on.
     *
     * @param location The location player clicked on.
     * @return The slot player clicked on.
     * @seeArmorStand.getClickedSlot(Vec3)
     */
    protected final @Nonnull
    EquipmentSlot getClickedSlot(@Nonnull final Vec3 location) {
        final var isBaby = isBaby();
        final var y = isBaby ? location.y * 2.0D : location.y;
        final var slot = EquipmentSlot.FEET;

        if (y >= 0.1D && y < 0.1D + (isBaby ? 0.8D : 0.45D) && hasItemInSlot(slot)) {
            return EquipmentSlot.FEET;
        } else if (y >= 0.9D + (isBaby ? 0.3D : 0.0D) && y < 0.9D + (isBaby ? 1.0D : 0.7D)
                && hasItemInSlot(EquipmentSlot.CHEST)) {
            return EquipmentSlot.CHEST;
        } else if (y >= 0.4D && y < 0.4D + (isBaby ? 1.0D : 0.8D) && hasItemInSlot(EquipmentSlot.LEGS)) {
            return EquipmentSlot.LEGS;
        } else if (y >= 1.6D && hasItemInSlot(EquipmentSlot.HEAD)) {
            return EquipmentSlot.HEAD;
        } else if (!hasItemInSlot(EquipmentSlot.MAINHAND) && hasItemInSlot(EquipmentSlot.OFFHAND)) {
            return EquipmentSlot.OFFHAND;
        } else {
            return EquipmentSlot.MAINHAND;
        }
    }

    @Override
    public void tick() {
        super.tick();
        final var item = this.getFruitAdd().getItem();
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
                    this.getFruitAdd().shrink(1);
                }
            }
        }
        setEnergy(getEnergy() - getEnergyCost());
    }

    @Override
    public void aiStep() {
        if (energyAvailable() || level.isClientSide) {
            super.aiStep();
        }
    }

    protected final boolean energyAvailable() {
        return getEnergy() != 0;
    }

    public void openBotInventory(ServerPlayer player, Bot bot) {
        if (player.containerMenu != player.inventoryMenu) {
            player.closeContainer();
        }

        player.nextContainerCounter();
        CivilizationFireNetworking.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new ClientBotScreenOpenPacket(player.containerCounter, bot.getId()));
        player.containerMenu = new BotMenu(player.containerCounter, player.getInventory(), bot);
        player.initMenu(player.containerMenu);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.entity.player.PlayerContainerEvent.Open(player, player.containerMenu));
    }
}