package com.daylight.civilization_fire.common.content.block.cooking;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class CookingBench extends Block {
    public static final IntegerProperty BENCH_STATE = IntegerProperty.create("bench", 0, 8);//阶段

    public CookingBench() {
        super(Properties.of(Material.STONE).randomTicks().noOcclusion().strength(2F).sound(SoundType.STONE)
                .requiresCorrectToolForDrops());
        this.registerDefaultState(this.stateDefinition.any().setValue(BENCH_STATE, 0));
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        int bench = state.getValue(BENCH_STATE);
        if (bench > 2 && random.nextBoolean()) {
            setBenchState(null, level, pos, (bench - 1) > 2 ? bench - 1 : 0);
        }
    }

    //添加属性
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(BENCH_STATE);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
            BlockHitResult result) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.getItem() instanceof BlockItem
                && ((BlockItem) itemStack.getItem()).getBlock().defaultBlockState().getMaterial() == Material.WOOD) {
            itemStack.shrink(1);
            setBenchState(player, level, pos, 1);
        } else if (itemStack.getItem() == Items.FLINT_AND_STEEL && state.getValue(BENCH_STATE) == 1) {
            itemStack.setDamageValue(itemStack.getDamageValue() + 1);
            setBenchState(player, level, pos, 8);
        }
        return InteractionResult.PASS;
    }

    public static void setBenchState(Player player, Level level, BlockPos pos, int b) {
        BlockState blockState = level.getBlockState(pos);
        level.setBlock(pos, blockState.setValue(BENCH_STATE, b), Block.UPDATE_ALL);
        level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
    }
}
