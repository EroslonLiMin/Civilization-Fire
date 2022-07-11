package com.daylight.civilization_fire.common.content.item.agriculture.tool;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import java.util.Random;

import com.daylight.civilization_fire.common.content.block.agriculture.SoilBlock;
import com.daylight.civilization_fire.common.util.CivilizationFireUtil;

//耕锄工具
public class ModHoeingToolItem extends Item {
    public int level;//耕锄等级

    public ModHoeingToolItem(Properties properties, int level) {
        super(properties);
        this.level = level;

    }

    //耕地使用~
    @Override
    public InteractionResult useOn(UseOnContext context) {
        final var player = context.getPlayer();
        if (player == null) {
            return InteractionResult.FAIL;
        }

        final var block = context.getLevel().getBlockState(context.getClickedPos());
        final var item = context.getItemInHand();
        //判断是否为可耕种方块且在服务端
        if (block.getBlock() instanceof SoilBlock && block.hasProperty(SoilBlock.BE_PLOUGHED)) {
            //很偷懒得用了nbt

            if (!context.getLevel().isClientSide()) {
                final var tag = item.getOrCreateTagElement("civilization_fire");
                if (!tag.contains("ploughed") || !BlockPos.of(tag.getLong("pos")).equals(context.getClickedPos())) {
                    tag.putInt("ploughed", Math.max(new Random().nextInt(4) - level, 1));
                    tag.putLong("pos", context.getClickedPos().asLong());
                }

                final var ploughed = tag.getInt("ploughed");
                tag.putInt("ploughed", ploughed - 1);
                //System.out.println(ploughed); Test

                if (ploughed < 0) {
                    SoilBlock.setPloughed(player, context.getLevel(), context.getClickedPos(), true);
                    tag.remove("ploughed");
                    tag.remove("pos");
                }
                //基于冷却

                player.getCooldowns().addCooldown(this, 10 - level * 2);
            }

            //播放耕种音效
            player.playSound(SoundEvents.HOE_TILL, 1.0F, 1.0F);

            CivilizationFireUtil.hurtItem(item, player, context.getHand(), EAT_DURATION);
        }
        return super.useOn(context);
    }

}
