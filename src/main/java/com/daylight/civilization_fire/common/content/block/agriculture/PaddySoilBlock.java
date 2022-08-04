package com.daylight.civilization_fire.common.content.block.agriculture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Random;

//水田
public class PaddySoilBlock extends SoilBlock implements SimpleWaterloggedBlock {
    //含水的
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public PaddySoilBlock() {
        super(Properties.of(Material.DIRT, MaterialColor.QUARTZ).strength(0.5F).sound(SoundType.GRAVEL)
                .requiresCorrectToolForDrops().noOcclusion());
        this.registerDefaultState(
                this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(FACING, Direction.NORTH));//设置states

    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos pos, Random random) {
        //TOOL
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = context.getLevel().getBlockState(blockpos);
        if (blockstate.is(this)) {
            return blockstate
                    .setValue(WATERLOGGED, Boolean.FALSE).setValue(FACING,
                            Arrays.stream(context.getNearestLookingDirections()).filter(
                                    direction -> direction.getAxis().isHorizontal()).findFirst()
                                    .orElse(Direction.NORTH));
        } else {
            FluidState fluidstate = context.getLevel().getFluidState(blockpos);
            return this
                    .defaultBlockState().setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER).setValue(FACING,
                            Arrays.stream(context.getNearestLookingDirections()).filter(
                                    direction -> direction.getAxis().isHorizontal()).findFirst()
                                    .orElse(Direction.NORTH));
        }
    }

    public boolean propagatesSkylightDown(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return !state.getValue(WATERLOGGED);
    }

    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState,
            LevelAccessor levelAccessor, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        return stateIn;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(WATERLOGGED, FACING);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }

}
