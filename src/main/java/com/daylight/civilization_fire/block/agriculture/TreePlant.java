package com.daylight.civilization_fire.block.agriculture;

import com.daylight.civilization_fire.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//数目植物
public class TreePlant {
    public interface TreeHappened {
        void happened(Player player, Level level, BlockPos pos, Object happened);
    }

    //Tree的木
    public static class TreeWood extends Block {
        public static final BooleanProperty BE_SKINNED = BooleanProperty.create("skinned");//被剥皮
        public TreeHappened treeHappened;
        public boolean canBeSkinned;

        public TreeWood(boolean canBeSkinned,TreeHappened... happened) {
            super(Properties.of(Material.WOOD).strength(1F).sound(SoundType.WOOD).requiresCorrectToolForDrops());
            this.registerDefaultState(this.stateDefinition.any().setValue(BE_SKINNED, Boolean.FALSE));//设置states
            this.treeHappened = happened == null || happened.length <= 0 ? null : happened[0];
            this.canBeSkinned = canBeSkinned;
        }

        //处理一下剥皮
        @Override
        public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
            if (pPlayer.getItemInHand(pHand).getItem() instanceof AxeItem && this.canBeSkinned) {
                pPlayer.getItemInHand(pHand).setDamageValue(pPlayer.getItemInHand(pHand).getDamageValue() + 1);
                setSkinned(pPlayer, pLevel, pPos, true);
            }
            return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        }

        //添加属性
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
            stateBuilder.add(BE_SKINNED);
        }


        //剥皮
        public static void setSkinned(Player player, Level level, BlockPos pos, boolean b) {
            BlockState blockState = level.getBlockState(pos);
            level.setBlock(pos, blockState.setValue(BE_SKINNED, b), Block.UPDATE_ALL);
            level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
            ((TreeWood) blockState.getBlock()).treeHappened.happened(player, level, pos, b);
        }

    }

    //Tree的叶子
    public static class TreeLeaf extends Block {
        public static final BooleanProperty IS_BLOOM = BooleanProperty.create("bloom");//开花了
        public TreeHappened treeHappened;

        public TreeLeaf(TreeHappened... happened) {
            super(Properties.of(Material.LEAVES).randomTicks().noCollission().strength(0.2F).sound(SoundType.AZALEA_LEAVES).requiresCorrectToolForDrops());
            this.registerDefaultState(this.stateDefinition.any().setValue(IS_BLOOM, Boolean.FALSE));//设置states
            this.treeHappened = happened == null || happened.length <= 0 ? null : happened[0];
        }

        //处理一下剥皮
        @Override
        public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
            if (pPlayer.getItemInHand(pHand).getItem() == Items.SHEARS) {
                pPlayer.getItemInHand(pHand).setDamageValue(pPlayer.getItemInHand(pHand).getDamageValue() + 1);
                pLevel.setBlock(pPos,Blocks.AIR.defaultBlockState(),Block.UPDATE_ALL);
                pLevel.gameEvent(GameEvent.BLOCK_PLACE, pPlayer);
                if(treeHappened != null) ((TreeLeaf) pState.getBlock()).treeHappened.happened(pPlayer, pLevel, pPos, null);
            }
            return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        }


        //处理一下tick
        public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos pos, Random random) {
            if (!blockState.getValue(IS_BLOOM)) {
                setBloom(null, serverLevel, pos, true);
            }
        }

        //添加属性
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
            stateBuilder.add(IS_BLOOM);
        }

        //剥皮
        public static void setBloom(Player player, Level level, BlockPos pos, boolean b) {
            BlockState blockState = level.getBlockState(pos);
            level.setBlock(pos, blockState.setValue(IS_BLOOM, b), Block.UPDATE_ALL);
            level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
        }
    }

    //树苗
    public static class TreeSapling extends BaseEntityBlock {
        public String[] plantBlocks;//所能种植的方块
        public String treeWood;//植物木头
        public String treeLeaf;//植物树叶

        public TreeSapling(String[] plantBlocks,String treeWood,String treeLeaf) {
            super(Properties.of(Material.PLANT).strength(0.1F).noCollission().sound(SoundType.AZALEA_LEAVES));
            this.plantBlocks = plantBlocks;
            this.treeWood = treeWood;
            this.treeLeaf = treeLeaf;
        }

        public RenderShape getRenderShape(BlockState state) {
            return RenderShape.MODEL;
        }

        @Nullable
        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new TreeSaplingBlockEntity(pos,state);
        }

        public List<Block> getPlantBlock() {
            List<Block> plantList = new ArrayList<>();
            for (String str : this.plantBlocks) {
                plantList.add(Registry.BLOCK.get(new ResourceLocation(str)));
            }
            return plantList;
        }

        public void place(Level level, BlockPos pos) {
            TreeWood treeWood = (TreeWood) Registry.BLOCK.get(new ResourceLocation(this.treeWood));
            TreeLeaf treeLeaf = (TreeLeaf) Registry.BLOCK.get(new ResourceLocation(this.treeLeaf));
            for (int y = pos.getY(); y < pos.getY() + 5; y++) {
                level.setBlock(pos.atY(y), treeWood.defaultBlockState(), Block.UPDATE_ALL);
            }
            for (int n = pos.getY() + 2; n < pos.getY() + 6; n++) {
                level.setBlock(pos.atY(n).east(), treeLeaf.defaultBlockState(), Block.UPDATE_ALL);
                level.setBlock(pos.atY(n).west(), treeLeaf.defaultBlockState(), Block.UPDATE_ALL);
                level.setBlock(pos.atY(n).north(), treeLeaf.defaultBlockState(), Block.UPDATE_ALL);
                level.setBlock(pos.atY(n).south(), treeLeaf.defaultBlockState(), Block.UPDATE_ALL);
                if (n == pos.getY() + 5) {
                    level.setBlock(pos.atY(n), treeLeaf.defaultBlockState(), Block.UPDATE_ALL);
                }
            }
            //level.gameEvent(GameEvent.BLOCK_PLACE, pos);
        }

        //tick
        public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> entityType) {
            return createTickerHelper(entityType, BlockEntityRegistry.TREE_SAPLING_BLOCK_ENTITY.get(), level.isClientSide ? null : TreeSaplingBlockEntity::serverTick);
        }

    }

    //树苗BlockEntity
    public static class TreeSaplingBlockEntity extends BlockEntity {
        public int growTicks;//生长时间
        public int stopTicks;//停止生长时间

        public TreeSaplingBlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityRegistry.TREE_SAPLING_BLOCK_ENTITY.get(), pos, state);
        }

        public static void serverTick(Level level, BlockPos pos, BlockState blockState, TreeSaplingBlockEntity treeSaplingBlockEntity) {
            TreeSapling treeSapling = (TreeSapling) blockState.getBlock();
            treeSaplingBlockEntity.growTicks+=1;
            if(treeSaplingBlockEntity.growTicks >=100){
                treeSapling.place(level, pos);
            }
            /*
            BlockState belowState = level.getBlockState(pos.below());
            if (SoilBlock.isSuitability(belowState) && treeSapling.getPlantBlock().contains(belowState.getBlock())) {
                treeSaplingBlockEntity.growTicks += 1;
                if (treeSaplingBlockEntity.growTicks > 1000) {
                    treeSapling.place(level, pos);
                }
            } else {
                treeSaplingBlockEntity.stopTicks += 1;
                if (treeSaplingBlockEntity.stopTicks > 100) {
                    level.setBlock(pos, Blocks.DEAD_BUSH.defaultBlockState(), Block.UPDATE_ALL);
                    level.gameEvent(GameEvent.BLOCK_PLACE, pos);
                }
            }*/
        }

        public void load(CompoundTag nbt) {
            super.load(nbt);
            this.growTicks = nbt.getInt("growTicks");
            this.stopTicks = nbt.getInt("stopTicks");
        }

        protected void saveAdditional(CompoundTag compoundTag) {
            super.saveAdditional(compoundTag);
            compoundTag.putInt("growTicks", this.growTicks);
            compoundTag.putInt("stopTicks", this.stopTicks);
        }

    }
}
