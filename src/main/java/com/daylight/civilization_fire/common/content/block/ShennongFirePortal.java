package com.daylight.civilization_fire.common.content.block;

import com.daylight.civilization_fire.common.content.register.CivilizationFireItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Arrays;
import java.util.Random;

public class ShennongFirePortal extends HasDropBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public ShennongFirePortal() {
        super(4,Properties.of(Material.WOOD).strength(3).lightLevel((level)->15).requiresCorrectToolForDrops().noCollission().noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Block.box(0.0D, 0, 0.0D, 16.0D, 32.0D, 16.0D);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack handItemStack = pPlayer.getItemInHand(pHand);
        if(handItemStack.getItem() == Items.DIAMOND){
            pPlayer.addItem(new ItemStack(CivilizationFireItems.GRAIN_SHENNONG_CHANNEL_FIRE.get()));
            handItemStack.shrink(1);
        }
        if(handItemStack.getItem() == Items.DIRT){
            pPlayer.addItem(randomDirtRandom());
            handItemStack.shrink(1);
        } else if(handItemStack.getItem() instanceof HoeItem){
            ItemStack itemStack = new ItemStack(CivilizationFireItems.AGRICULTURAL_SPOT_FIRE.get());
            itemStack.setCount(switchLevel((HoeItem) handItemStack.getItem()) + 1);
            pPlayer.addItem(itemStack);
            handItemStack.shrink(1);
        } else if(handItemStack.getItem() == Items.WATER_BUCKET){
            pPlayer.addItem(new ItemStack(CivilizationFireItems.DRAGON_CHANNEL_FIRE.get()));
            handItemStack.shrink(1);
        } else if(handItemStack.getItem() == Items.GOLD_INGOT){
            pPlayer.addItem(new ItemStack(CivilizationFireItems.SEED_BAG_ITEM.get()));
            handItemStack.shrink(1);
        }else if(handItemStack.getItem() == Items.COOKED_BEEF){
            pPlayer.addItem(new ItemStack(CivilizationFireItems.ESOPHAGUS_CHANNEL_FIRE.get()));
            handItemStack.shrink(1);
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    public int switchLevel(TieredItem diggerItem){
        return diggerItem.getTier().getLevel();
    }

    public ItemStack randomDirtRandom(){
        return switch (new Random().nextInt(6)) {
            case 2 -> new ItemStack(CivilizationFireItems.CLAY_BLOCK.get());
            case 3 -> new ItemStack(CivilizationFireItems.SAND_BLOCK.get());
            case 4 -> new ItemStack(CivilizationFireItems.PADDY_SOIL.get());
            case 5 -> new ItemStack(CivilizationFireItems.MYCELIAL_SOIL.get());
            default -> new ItemStack(CivilizationFireItems.LOAM_BLOCK.get());
        };
    }

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

    //添加属性
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING);
    }
}
