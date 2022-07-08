package com.daylight.civilization_fire.common.content.block.agriculture;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class PsionicEarthFire extends BaseEntityBlock {
    public PsionicEarthFire() {
        super(Properties.of(Material.FIRE, MaterialColor.FIRE).noCollission().instabreak().lightLevel((p_152605_) -> {
            return 15;
        }).sound(SoundType.WOOL));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return null;
    }

}
