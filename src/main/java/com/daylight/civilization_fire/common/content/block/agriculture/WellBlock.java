package com.daylight.civilization_fire.common.content.block.agriculture;

import com.daylight.civilization_fire.common.content.item.agriculture.tool.WateringToolItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.energy.CapabilityEnergy;

import java.util.Random;

public class WellBlock extends Block {
    //装有水的等级
    public static final IntegerProperty WATER_LEVEL = IntegerProperty.create("water_level",0,10);//被浇水

    public WellBlock() {
        super(Properties.of(Material.STONE).randomTicks().strength(0.6F).sound(SoundType.GRAVEL)
                .requiresCorrectToolForDrops());
        this.registerDefaultState(this.stateDefinition.any().setValue(WATER_LEVEL, 0));//设置states
    }

    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.or(Block.box(-16,-16,-16,16,16,16));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.or(Block.box(-16,-16,-16,16,16,16));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if(itemStack.getItem() instanceof WateringToolItem){
            itemStack.getCapability(CapabilityEnergy.ENERGY).ifPresent(e -> {
                int i = (int) Math.ceil(e.getEnergyStored() / 500.0);
                int add = Math.max((pLevel.getBlockState(pPos).getValue(WATER_LEVEL) + i), 10);
                setWaterLevel(pPlayer,pLevel,pPos,add);
                e.extractEnergy(e.getEnergyStored(),false);
            });
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    //耕锄
    public static void setWaterLevel(Player player, Level level, BlockPos pos, int b) {
        BlockState blockState = level.getBlockState(pos);
        level.setBlock(pos, blockState.setValue(WATER_LEVEL, b), Block.UPDATE_ALL);
        level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        int waterLevel = pState.getValue(WATER_LEVEL);
        if(waterLevel > 0) {
            boolean isWatered = false;
            for (int x = pPos.getX() - 2; x < pPos.getX() + 2; x++) {
                for (int z = pPos.getZ() - 2; z < pPos.getZ() + 2; z++) {
                    BlockPos pos = new BlockPos(x, pPos.getY(), z);
                    BlockState state = pLevel.getBlockState(pos);
                    boolean is = state.hasProperty(SoilBlock.BE_PLOUGHED) ? state.getValue(SoilBlock.BE_PLOUGHED) : true;
                    if (state.hasProperty(SoilBlock.BE_WATERED) && !state.getValue(SoilBlock.BE_WATERED) && is) {
                        SoilBlock.setWatered(null, pLevel, pos, true);
                        isWatered = true;
                    }
                }
            }
            if (isWatered) {
                pLevel.setBlock(pPos, pState.setValue(WATER_LEVEL,waterLevel - 1), Block.UPDATE_ALL);
                pLevel.gameEvent(GameEvent.BLOCK_PLACE, pPos);
            }
        }
        super.randomTick(pState, pLevel, pPos, pRandom);
    }


    //添加属性
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(WATER_LEVEL);
    }
}
