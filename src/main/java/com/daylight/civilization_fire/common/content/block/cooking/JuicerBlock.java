package com.daylight.civilization_fire.common.content.block.cooking;

import com.daylight.civilization_fire.common.content.item.agriculture.VegetableJuiceItem;
import com.daylight.civilization_fire.common.content.menu.cooking.JuicerMenu;
import com.daylight.civilization_fire.common.content.register.CivilizationFireBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class JuicerBlock extends BaseEntityBlock {
    public JuicerBlock() {
        super(Properties.of(Material.WOOD).strength(1F).noOcclusion().sound(SoundType.WOOD)
                .requiresCorrectToolForDrops());
    }

    //修改渲染类型

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand,
                                 BlockHitResult pHit) {
        if (!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND) {
            JuicerBlockEntity juicerBlockEntity = (JuicerBlockEntity) pLevel.getBlockEntity(pPos);
            NetworkHooks.openGui((ServerPlayer) pPlayer, juicerBlockEntity, pPos);
        }
        return InteractionResult.SUCCESS;
    }

    //tick
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
                                                                  BlockEntityType<T> entityType) {
        return createTickerHelper(entityType, CivilizationFireBlockEntities.JUICER_BLOCK_ENTITY.get(),
                JuicerBlockEntity::tick);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new JuicerBlockEntity(pPos,pState);
    }

    public static class JuicerBlockEntity extends BlockEntity implements MenuProvider {
        public final ItemStackHandler addStackHandler = new ItemStackHandler(5);
        public final ItemStackHandler outputItemStackHandler = new ItemStackHandler(1);
        public boolean output;
        public VegetableJuiceItem vegetableJuiceItem;
        public JuicerBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
            super(CivilizationFireBlockEntities.JUICER_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        }

        public static void tick(Level level, BlockPos pos, BlockState blockState,
                                JuicerBlockEntity juicerBlockEntity) {
            //如果被拿了就设置为ItemStack.EMPTY
            if(juicerBlockEntity.vegetableJuiceItem != null) {
                if(!juicerBlockEntity.vegetableJuiceItem.vegetable.equals(juicerBlockEntity.addStackHandler.getStackInSlot(0).getItem().getRegistryName().getPath())){
                    juicerBlockEntity.outputItemStackHandler.setStackInSlot(0,ItemStack.EMPTY);
                    juicerBlockEntity.vegetableJuiceItem = null;
                    juicerBlockEntity.output = false;
                }
                if (juicerBlockEntity.output && juicerBlockEntity.outputItemStackHandler.getStackInSlot(0) == ItemStack.EMPTY) {
                    juicerBlockEntity.addStackHandler.getStackInSlot(0).shrink(1);
                    juicerBlockEntity.addStackHandler.getStackInSlot(1).shrink(1);
                    juicerBlockEntity.output = false;
                }
            }
            //判断一下是否符合条件
            if(juicerBlockEntity.addStackHandler.getStackInSlot(1).getItem() == Items.GLASS_BOTTLE && !juicerBlockEntity.output){
                for(VegetableJuiceItem vegetableJuiceItem : VegetableJuiceItem.VEGETABLE_JUICE_ITEM_LIST){
                    if(vegetableJuiceItem.vegetable.equals(juicerBlockEntity.addStackHandler.getStackInSlot(0).getItem().getRegistryName().getPath())){
                        juicerBlockEntity.output = true;
                        juicerBlockEntity.outputItemStackHandler.setStackInSlot(0,new ItemStack(vegetableJuiceItem));
                        juicerBlockEntity.vegetableJuiceItem = vegetableJuiceItem;
                        break;
                    }
                }
            }
        }

        //以下同步与加载
        public void load(CompoundTag nbt) {
            super.load(nbt);
            addStackHandler.deserializeNBT(nbt.getCompound("addStackHandler"));
        }

        protected void saveAdditional(CompoundTag compoundTag) {
            super.saveAdditional(compoundTag);
            compoundTag.put("addStackHandler", this.addStackHandler.serializeNBT());
        }

        @Override
        public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
            this.handleUpdateTag(Objects.requireNonNull(pkt.getTag()));
        }

        @Override
        public CompoundTag getUpdateTag() {
            CompoundTag compoundTag = super.getUpdateTag();
            compoundTag.put("addStackHandler", this.addStackHandler.serializeNBT());
            return compoundTag;
        }

        @javax.annotation.Nullable
        @Override
        public Packet<ClientGamePacketListener> getUpdatePacket() {
            return ClientboundBlockEntityDataPacket.create(this);
        }

        @Override
        public void handleUpdateTag(CompoundTag nbt) {
            addStackHandler.deserializeNBT(nbt.getCompound("addStackHandler"));
        }

        @Override
        public Component getDisplayName() {
            return new TextComponent("juicer_menu.display.name");
        }

        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
            return new JuicerMenu(pContainerId,pPlayerInventory,this);
        }
    }
}
