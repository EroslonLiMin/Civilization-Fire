package com.daylight.civilization_fire.common.content.block.agriculture;

import com.daylight.civilization_fire.common.content.block.HasDropBlock;
import com.daylight.civilization_fire.common.content.item.agriculture.tool.WateringToolItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.energy.CapabilityEnergy;

public class WellBlock extends HasDropBlock {

    public WellBlock() {
        super(2,Properties.of(Material.STONE).noOcclusion().strength(0.6F).sound(SoundType.GRAVEL)
                .requiresCorrectToolForDrops());
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand,
            BlockHitResult pHit) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if (itemStack.getItem() instanceof WateringToolItem) {
            itemStack.getCapability(CapabilityEnergy.ENERGY)
                    .ifPresent(e -> e.receiveEnergy(e.getMaxEnergyStored() - e.getEnergyStored(), false));
        }

        return InteractionResult.PASS;
    }

}
