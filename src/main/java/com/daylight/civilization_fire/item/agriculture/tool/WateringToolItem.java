package com.daylight.civilization_fire.item.agriculture.tool;

import com.daylight.civilization_fire.block.agriculture.PaddySoilBlock;
import com.daylight.civilization_fire.block.agriculture.SoilBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

//浇水工具
public class WateringToolItem extends Item {
    public int capacity;//容量

    public WateringToolItem(Properties properties, int capacity) {
        super(properties);
        this.capacity = capacity;
    }

    //cap处理，添加了一个withWater数据
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt)
    {
        return new ICapabilityProvider()
        {
            private final LazyOptional<IEnergyStorage> lazyOptional = LazyOptional.of(() -> new IEnergyStorage()
            {
                @Override
                public int receiveEnergy(int maxReceive, boolean simulate)
                {
                    int energy = this.getEnergyStored();
                    int diff = Math.min(this.getMaxEnergyStored() - energy, maxReceive);
                    if (!simulate)
                    {
                        stack.getOrCreateTag().putInt("withWater", energy + diff);
                    }
                    return diff;
                }

                @Override
                public int extractEnergy(int maxExtract, boolean simulate)
                {
                    int energy = this.getEnergyStored();
                    int diff = Math.min(energy, maxExtract);
                    if (!simulate)
                    {
                        stack.getOrCreateTag().putInt("withWater", energy - diff);
                    }
                    return diff;
                }

                @Override
                public int getEnergyStored()
                {
                    if (stack.hasTag())
                    {
                        int energy = Objects.requireNonNull(stack.getTag()).getInt("withWater");
                        return Math.max(0, Math.min(this.getMaxEnergyStored(), energy));
                    }
                    return 0;
                }

                @Override
                public int getMaxEnergyStored()
                {
                    return ((WateringToolItem)stack.getItem()).capacity;
                }

                @Override
                public boolean canExtract()
                {
                    return true;
                }

                @Override
                public boolean canReceive()
                {
                    return true;
                }
            });

            @NotNull
            @Override
            public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                boolean isEnergy = Objects.equals(cap, CapabilityEnergy.ENERGY);
                return isEnergy ? this.lazyOptional.cast() : LazyOptional.empty();
            }
        };
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        ItemStack itemStack = player.getItemInHand(hand);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        itemStack.getCapability(CapabilityEnergy.ENERGY).ifPresent(e ->
        {
            BlockPos pos = blockhitresult.getBlockPos();
            BlockState blockState = level.getBlockState(pos);
            if (blockState.getBlock() instanceof BucketPickup && !(blockState.getBlock() instanceof SoilBlock)) {
                e.receiveEnergy(500, false);
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
                player.playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
            } else if (blockState.getBlock() instanceof SoilBlock && e.getEnergyStored() > 100) {
                if (blockState.hasProperty(SoilBlock.BE_WATERED) && (blockState.hasProperty(SoilBlock.BE_PLOUGHED) ? blockState.getValue(SoilBlock.BE_PLOUGHED) : true)) {
                    SoilBlock.setWatered(player, level, pos, true);
                    e.extractEnergy(100, false);
                    player.playSound(SoundEvents.BUCKET_EMPTY, 1.0F, 1.0F);
                }
            }
            //基于冷却
            player.getCooldowns().addCooldown(this, 10);
            itemStack.setDamageValue(itemStack.getDamageValue() + 1);
        });
        return super.use(level, player, hand);
    }



    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, components, tooltipFlag);
        itemStack.getCapability(CapabilityEnergy.ENERGY).ifPresent(e ->
        {
            String msg = e.getEnergyStored() + " Water / " + e.getMaxEnergyStored() + " Water";
            components.add(new TextComponent(msg).setStyle(Style.EMPTY.withBold(true).withColor(ChatFormatting.BLUE)));
        });
    }
}
