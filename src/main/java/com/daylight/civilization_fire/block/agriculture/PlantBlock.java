package com.daylight.civilization_fire.block.agriculture;

import com.daylight.civilization_fire.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PlantBlock extends BaseEntityBlock {


    public enum PlantModel {
        DestroyModel,//直接打破模式
        PickingModel,//摘下模式
        LikePumpkin,//像南瓜一样？
    }

    public static final IntegerProperty GROW_UP_STATE = IntegerProperty.create("state", 0, 5);//生长阶段


    //方块属性
    public ResourceLocation fruitID;
    public PlantModel plantModel;//种植模式
    public int stageLevel;//种植的阶段
    public float matureTick;//成熟需要的时间
    public String[] plantBlocks;//所能种植的方块
    public int roundItem;

    public PlantBlock(PlantModel plantModel, ResourceLocation fruitID, int stageLevel, float matureTick, String[] plantBlocks,int roundItem) {
        super(Properties.of(Material.PLANT).noCollission().strength(1));
        this.fruitID = fruitID;
        this.plantModel = plantModel;
        this.stageLevel = stageLevel;
        this.matureTick = matureTick;
        this.plantBlocks = plantBlocks;
        this.roundItem = roundItem;
        this.registerDefaultState(this.stateDefinition.any().setValue(GROW_UP_STATE, 0));

    }

    

    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    //添加属性
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(GROW_UP_STATE);
    }

    //处理一下直接破坏类型的植物
    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player,
                                       boolean willHarvest, FluidState fluid) {
        PlantBlockEntity plantBlockEntity = (PlantBlockEntity) level.getBlockEntity(pos);
        if (plantBlockEntity != null) {
            if (plantModel != PlantModel.PickingModel && plantBlockEntity.growingState == this.stageLevel) {
                giveFruitItem(player);
            }
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    //处理一下摘取类型的植物
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        //判断一下是否为摘取类型
        PlantBlockEntity plantBlockEntity = (PlantBlockEntity) pLevel.getBlockEntity(pPos);
        if (plantBlockEntity != null) {
            if (plantModel == PlantModel.PickingModel && plantBlockEntity.growingState == this.stageLevel) {
                plantBlockEntity.growingState -= 2;
                plantBlockEntity.growingTick -= this.matureTick / this.stageLevel * 2;
                giveFruitItem(pPlayer);
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }


    //给予果实物品
    public void giveFruitItem(Player pPlayer) {
        Random random = new Random();//随机一下
        ItemStack fruitStack = new ItemStack(Registry.ITEM.get(this.fruitID));
        fruitStack.setCount(random.nextInt(roundItem) + 1);
        pPlayer.addItem(new ItemStack(Registry.ITEM.get(this.fruitID)));
    }

    //生长一个阶段
    public static void GrowUpState(PlantBlock plantBlock, Level level, BlockPos pos, int state) {
        PlantBlockEntity plantBlockEntity = (PlantBlockEntity) level.getBlockEntity(pos);

    }

    public List<Block> getPlantBlock(){
        List<Block> plantList = new ArrayList<>();
        for(String str : this.plantBlocks){
            plantList.add(Registry.BLOCK.get(new ResourceLocation(str)));
        }
        return plantList;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PlantBlockEntity(pos,state);
    }

    //tick
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> entityType) {
        return createTickerHelper(entityType, BlockEntityRegistry.PLANT_BLOCK_ENTITY.get(), level.isClientSide ? null : PlantBlockEntity::serverTick);
    }

    //方块实体 储存成长数据
    public static class PlantBlockEntity extends BlockEntity {
        public int growingTick;//已经生长的时间
        public int growingState;//当前生长的阶段
        public int stopGrowingTick;//停止生长的时间

        public PlantBlockEntity(BlockPos blockPos, BlockState blockState) {
            super(BlockEntityRegistry.PLANT_BLOCK_ENTITY.get(), blockPos, blockState);
        }

        //tick进行刷新
        public static void serverTick(Level level, BlockPos pos, BlockState blockState, PlantBlockEntity plantBlockEntity) {
            BlockState state = level.getBlockState(pos.below());//获取下方方块
            PlantBlock plantBlock = ((PlantBlock) blockState.getBlock());
            if (plantBlockEntity.stopGrowingTick < 500) {
                //判断当前下方方块是否适合种植
                if (SoilBlock.isSuitability(state)) {
                    Random random = new Random();//随即一下
                    //还有火焰处理 如果符合对应需要的block，就增加生长进度，没用就停止生长直到枯萎
                    int add = 1;
                    if (plantBlock.getPlantBlock().contains(state.getBlock())) {
                        add += 1 + random.nextInt(5);
                    } else add = random.nextInt(2);
                    if (add > 1) plantBlockEntity.growingTick += add;
                    else plantBlockEntity.stopGrowingTick += 1;
                    //判断是否到达了阶段性时期
                    if (plantBlock.matureTick / plantBlock.stageLevel * plantBlockEntity.growingState > plantBlockEntity.growingTick && plantBlock.stageLevel > plantBlockEntity.growingState) {
                        level.setBlock(pos, blockState.setValue(GROW_UP_STATE, plantBlockEntity.growingState + 1), Block.UPDATE_ALL);
                        level.gameEvent(GameEvent.BLOCK_PLACE, pos);
                        if (plantBlock.plantModel == PlantModel.LikePumpkin && plantBlockEntity.growingState == plantBlock.stageLevel) {
                            level.setBlock(pos, Registry.BLOCK.get(plantBlock.fruitID).defaultBlockState(), Block.UPDATE_ALL);
                            level.gameEvent(GameEvent.BLOCK_PLACE, pos);
                        }
                    }

                } else {
                    plantBlockEntity.stopGrowingTick += 1;
                }
            } else {
                //如果停止生长太久就直接枯萎
                level.setBlock(pos, Blocks.DEAD_BUSH.defaultBlockState(), Block.UPDATE_ALL);
                level.gameEvent(GameEvent.BLOCK_PLACE, pos);
            }
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            this.stopGrowingTick = nbt.getInt("stopGrowingTick");
            this.growingState = nbt.getInt("growingState");
            this.growingState = nbt.getInt("growingState");
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putInt("stopGrowingTick",this.stopGrowingTick);
            compoundTag.putInt("growingState",this.growingState);
            compoundTag.putInt("growingState",this.growingState);
            return compoundTag;
        }
    }
}