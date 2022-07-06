package com.daylight.civilization_fire.block.agriculture;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.Random;

//泥土方块
public class SoilBlock extends Block {
    //创建属性
    public static final BooleanProperty BE_PLOUGHED = BooleanProperty.create("ploughed");//被耕锄
    public static final BooleanProperty BE_WATERED = BooleanProperty.create("watered");//被浇水

    //种植属性 - 基准都为0 - 1

    public SoilBlock() {
        super(Properties.of(Material.DIRT, MaterialColor.QUARTZ).randomTicks().strength(0.6F).sound(SoundType.GRAVEL).requiresCorrectToolForDrops());
        this.registerDefaultState(this.stateDefinition.any().setValue(BE_PLOUGHED, Boolean.FALSE).setValue(BE_WATERED, Boolean.FALSE));//设置states
    }

    public SoilBlock(Properties properties) {
        super(properties);
    }

    /*修改渲染类型—（因为这傻逼玩意卡了一天的我也是傻逼
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }*/

    //添加属性
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(BE_PLOUGHED, BE_WATERED);
    }

    //开启ticking
    public boolean isRandomlyTicking(BlockState p_53961_) {
        return true;
    }

    //处理一下tick
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos pos, Random random) {
        if (blockState.hasProperty(BE_PLOUGHED) && blockState.getValue(BE_PLOUGHED)) {
            if (blockState.hasProperty(BE_WATERED) && blockState.getValue(BE_WATERED)) {

                setWatered(null, serverLevel, pos, random.nextBoolean());
            }
            setPloughed(null, serverLevel, pos, blockState.getValue(BE_WATERED));
        }
    }


    //耕锄
    public static void setPloughed(Player player, Level level, BlockPos pos, boolean b) {
        BlockState blockState = level.getBlockState(pos);
        level.setBlock(pos, blockState.setValue(BE_PLOUGHED, b), Block.UPDATE_ALL);
        level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
    }

    //浇水
    public static void setWatered(Player player, Level level, BlockPos pos, boolean b) {
        BlockState blockState = level.getBlockState(pos);
        level.setBlock(pos, blockState.setValue(BE_WATERED, b), Block.UPDATE_ALL);
        level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
    }

    //判断一个soilBlock是否适合种植
    public static boolean isSuitability(BlockState state){
        return state.getBlock() instanceof SoilBlock && (state.hasProperty(BE_PLOUGHED) ? state.getValue(BE_PLOUGHED) : true) && (state.hasProperty(BE_WATERED) ? state.getValue(BE_WATERED) : true);
    }

    //临近水源
    protected static boolean isNearWater(LevelReader levelReader, BlockPos pos) {
        for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
            if (levelReader.getFluidState(blockpos).is(FluidTags.WATER)) {
                return true;
            }
        }

        return net.minecraftforge.common.FarmlandWaterManager.hasBlockWaterTicket(levelReader, pos);
    }
}