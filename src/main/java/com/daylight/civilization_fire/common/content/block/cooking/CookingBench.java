package com.daylight.civilization_fire.common.content.block.cooking;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Arrays;
import java.util.Random;

import com.daylight.civilization_fire.common.util.CivilizationFireUtil;
import org.jetbrains.annotations.Nullable;

public class CookingBench extends Block {
    public static final IntegerProperty BENCH_STATE = IntegerProperty.create("bench", 0, 8);//阶段
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public CookingBench() {
        super(Properties.of(Material.STONE).randomTicks().noOcclusion().strength(2F).sound(SoundType.STONE)
                .requiresCorrectToolForDrops());
        this.registerDefaultState(this.stateDefinition.any().setValue(BENCH_STATE, 0).setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = context.getLevel().getBlockState(blockpos);
        if (blockstate.is(this)) {
            return blockstate
                    .setValue(FACING,
                            Arrays.stream(context.getNearestLookingDirections()).filter(
                                    direction -> direction.getAxis().isHorizontal()).findFirst()
                                    .orElse(Direction.NORTH));
        } else {
            return this
                    .defaultBlockState().setValue(FACING,
                            Arrays.stream(context.getNearestLookingDirections()).filter(
                                    direction -> direction.getAxis().isHorizontal()).findFirst()
                                    .orElse(Direction.NORTH));
        }
    }
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        int bench = state.getValue(BENCH_STATE);
        if (bench >= 2 && random.nextBoolean()) {
            setBenchState(null, level, pos, (bench - 1) >= 2 ? bench - 1 : 0);
        }
    }

    //添加属性
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(BENCH_STATE).add(FACING);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return (state.getValue(BENCH_STATE) >= 2) ? 1 : 0;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
            BlockHitResult result) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.getItem() instanceof BlockItem
                && ((BlockItem) itemStack.getItem()).getBlock().defaultBlockState().getMaterial() == Material.WOOD) {
            itemStack.shrink(1);
            setBenchState(player, level, pos, 1);
            return InteractionResult.SUCCESS;
        } else if (itemStack.getItem() == Items.FLINT_AND_STEEL && state.getValue(BENCH_STATE) == 1) {
            CivilizationFireUtil.hurtItem(itemStack, player, hand, 1);
            setBenchState(player, level, pos, 8);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public static void setBenchState(Player player, Level level, BlockPos pos, int b) {
        BlockState blockState = level.getBlockState(pos);
        level.setBlock(pos, blockState.setValue(BENCH_STATE, b), Block.UPDATE_ALL);
        level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
    }
}
