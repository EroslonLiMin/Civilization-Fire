package com.daylight.civilization_fire.common.content.block.agriculture;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import net.minecraftforge.registries.ForgeRegistries;

import org.jetbrains.annotations.Nullable;

import com.daylight.civilization_fire.common.content.register.CivilizationFireBlockEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlantBlock extends BaseEntityBlock {

    public enum PlantModel {
        DestroyModel, //直接打破模式
        PickingModel,//摘下模式
    }

    public static final IntegerProperty GROW_UP_STATE = IntegerProperty.create("state", 0, 7);//生长阶段

    //方块属性
    public ResourceLocation fruitID;
    public PlantModel plantModel;//种植模式
    public int stageLevel;//种植的阶段
    public int matureTick;//成熟需要的时间
    public String[] plantBlocks;//所能种植的方块
    public int roundItem;

    public PlantBlock(PlantModel plantModel, ResourceLocation fruitID, int stageLevel, int matureTick,
            String[] plantBlocks, int roundItem) {
        super(Properties.of(Material.PLANT).noCollission().strength(0));
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
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand,
            BlockHitResult pHit) {
        //判断一下是否为摘取类型
        PlantBlockEntity plantBlockEntity = (PlantBlockEntity) pLevel.getBlockEntity(pPos);
        if (plantBlockEntity != null) {
            if (plantModel == PlantModel.PickingModel && plantBlockEntity.growingState == this.stageLevel) {
                plantBlockEntity.growingState -= 1;
                plantBlockEntity.growingTick -= this.matureTick / this.stageLevel * 2;
                giveFruitItem(pPlayer);
            }
        }
        if (!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND) {
            ((PlantBlock) pState.getBlock()).growUpState(pLevel, pPos,
                    Math.min(plantBlockEntity.growingState + 1, this.stageLevel));
        }

        return InteractionResult.PASS;
    }

    //给予果实物品
    public void giveFruitItem(Player pPlayer) {
        ItemStack fruitStack = new ItemStack(ForgeRegistries.ITEMS.getHolder(fruitID).get().value());
        fruitStack.setCount(new Random().nextInt(roundItem) + 1);
        pPlayer.addItem(fruitStack);
    }

    //生长一个阶段
    public void growUpState(Level level, BlockPos pos, int state) {
        BlockState blockState = level.getBlockState(pos);
        PlantBlockEntity plantBlockEntity = (PlantBlockEntity) level.getBlockEntity(pos);
        plantBlockEntity.growingState = state;
        plantBlockEntity.growingTick = (int) (this.matureTick * ((this.stageLevel + 0.0F) / (this.stageLevel - state)));
        level.setBlock(pos, blockState.setValue(GROW_UP_STATE, state), Block.UPDATE_ALL);
        level.gameEvent(GameEvent.BLOCK_PLACE, pos);
    }

    public List<Block> getPlantBlock() {
        List<Block> plantList = new ArrayList<>();
        for (String str : this.plantBlocks) {
            plantList.add(ForgeRegistries.BLOCKS.getHolder(new ResourceLocation(str)).get().value());
        }
        return plantList;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PlantBlockEntity(pos, state);
    }

    //tick
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
            BlockEntityType<T> entityType) {
        return createTickerHelper(entityType, CivilizationFireBlockEntities.PLANT_BLOCK_ENTITY.get(),
                level.isClientSide ? null : PlantBlockEntity::serverTick);
    }

    //方块实体 储存成长数据
    public static class PlantBlockEntity extends BlockEntity {
        public int growingTick;//已经生长的时间
        public int growingState;//当前生长的阶段
        public int stopGrowingTick;//停止生长的时间

        public PlantBlockEntity(BlockPos blockPos, BlockState blockState) {
            super(CivilizationFireBlockEntities.PLANT_BLOCK_ENTITY.get(), blockPos, blockState);
        }

        //tick进行刷新
        public static void serverTick(Level level, BlockPos pos, BlockState blockState,
                PlantBlockEntity plantBlockEntity) {
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
                    } else
                        add = random.nextInt(2);
                    if (add > 1)
                        plantBlockEntity.growingTick += add;
                    else
                        plantBlockEntity.stopGrowingTick += 1;
                    //判断是否到达了阶段性时期
                    if (plantBlock.matureTick / plantBlock.stageLevel
                            * plantBlockEntity.growingState > plantBlockEntity.growingTick
                            && plantBlock.stageLevel > plantBlockEntity.growingState) {
                        level.setBlock(pos, blockState.setValue(GROW_UP_STATE, plantBlockEntity.growingState + 1),
                                Block.UPDATE_ALL);
                        level.gameEvent(GameEvent.BLOCK_PLACE, pos);
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

        public void load(CompoundTag nbt) {
            super.load(nbt);
            this.stopGrowingTick = nbt.getInt("stopGrowingTick");
            this.growingState = nbt.getInt("growingState");
            this.growingTick = nbt.getInt("growingTick");
        }

        protected void saveAdditional(CompoundTag compoundTag) {
            super.saveAdditional(compoundTag);
            compoundTag.putInt("stopGrowingTick", this.stopGrowingTick);
            compoundTag.putInt("growingState", this.growingState);
            compoundTag.putInt("growingTick", this.growingTick);
        }

        //服务端处理（好像多此一举了
        /*@Override
        public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
            this.handleUpdateTag(Objects.requireNonNull(pkt.getTag()));
        }
        @Override
        public CompoundTag getUpdateTag() {
            CompoundTag compoundTag = super.getUpdateTag();
            compoundTag.putInt("stopGrowingTick",this.stopGrowingTick);
            compoundTag.putInt("growingState",this.growingState);
            compoundTag.putInt("growingTick",this.growingTick);
            return compoundTag;
        }
        @Nullable
        @Override
        public Packet<ClientGamePacketListener> getUpdatePacket() {
            return ClientboundBlockEntityDataPacket.create(this);
        }
        @Override
        public void handleUpdateTag(CompoundTag nbt) {
            this.stopGrowingTick = nbt.getInt("stopGrowingTick");
            this.growingState = nbt.getInt("growingState");
            this.growingTick = nbt.getInt("growingTick");
        }*/
    }
}