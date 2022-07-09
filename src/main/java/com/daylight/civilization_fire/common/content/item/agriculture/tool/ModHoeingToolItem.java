package com.daylight.civilization_fire.common.content.item.agriculture.tool;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;
import java.util.Random;

import com.daylight.civilization_fire.common.content.block.agriculture.SoilBlock;

//耕锄工具
public class ModHoeingToolItem extends Item {
    public int level;//耕锄等级

    public ModHoeingToolItem(Properties properties, int level) {
        super(properties);
        this.level = level;

    }

    //耕地使用~
    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {

        BlockState clickBlock = useOnContext.getLevel().getBlockState(useOnContext.getClickedPos());
        ItemStack itemStack = useOnContext.getItemInHand();
        //判断是否为可耕种方块且在服务端
        if (clickBlock.getBlock() instanceof SoilBlock) {
            if (clickBlock.hasProperty(SoilBlock.BE_PLOUGHED)) {
                //很偷懒得用了nbt
                if (!useOnContext.getLevel().isClientSide()) {
                    CompoundTag compoundTag = itemStack.getOrCreateTagElement("civilization_fire");
                    if (!compoundTag.contains("ploughed")
                            || !BlockPos.of(compoundTag.getLong("pos")).equals(useOnContext.getClickedPos())) {
                        compoundTag.putInt("ploughed", Math.max(new Random().nextInt(4) - level, 1));
                        compoundTag.putLong("pos", useOnContext.getClickedPos().asLong());
                    }
                    int ploughed = compoundTag.getInt("ploughed");
                    compoundTag.putInt("ploughed", ploughed - 1);
                    //System.out.println(ploughed); Test
                    if (ploughed < 0) {
                        SoilBlock.setPloughed(useOnContext.getPlayer(), useOnContext.getLevel(),
                                useOnContext.getClickedPos(), true);
                        compoundTag.remove("ploughed");
                        compoundTag.remove("pos");
                    }
                    //基于冷却

                    //
                    // TODO: Delete Objects.requireNonNull.
                    // useOnContext.getPlayer() will always generate NPE if it is null
                    //
                    Objects.requireNonNull(useOnContext.getPlayer()).getCooldowns().addCooldown(this, 10 - level * 2);
                }
                if (useOnContext.getPlayer() != null) {
                    //播放耕种音效
                    useOnContext.getPlayer().playSound(SoundEvents.HOE_TILL, 1.0F, 1.0F);
                }
                itemStack.setDamageValue(itemStack.getDamageValue() + 1);
            }
        }
        return super.useOn(useOnContext);
    }

}
