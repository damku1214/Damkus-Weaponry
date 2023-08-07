package net.damku1214.damkusweaponry.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.FireChargeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoulFireChargeItem extends FireChargeItem {
    public SoulFireChargeItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext p_41204_) {
        Level level = p_41204_.getLevel();
        BlockPos blockpos = p_41204_.getClickedPos();
        BlockPos blockpos0 = new BlockPos(blockpos.getX() + 1, blockpos.getY() + 1, blockpos.getZ());
        BlockPos blockpos1 = new BlockPos(blockpos.getX() - 1, blockpos.getY() + 1, blockpos.getZ());
        BlockPos blockpos2 = new BlockPos(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ() + 1);
        BlockPos blockpos3 = new BlockPos(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ() - 1);
        BlockPos blockpos4 = new BlockPos(blockpos.getX() + 2, blockpos.getY() + 1, blockpos.getZ());
        BlockPos blockpos5 = new BlockPos(blockpos.getX() - 2, blockpos.getY() + 1, blockpos.getZ());
        BlockPos blockpos6 = new BlockPos(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ() + 2);
        BlockPos blockpos7 = new BlockPos(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ() - 2);

        BlockState blockstate = level.getBlockState(blockpos);
        boolean flag = false;
        if (!CampfireBlock.canLight(blockstate) && !CandleBlock.canLight(blockstate) && !CandleCakeBlock.canLight(blockstate)) {
            blockpos = blockpos.relative(p_41204_.getClickedFace());
            if (BaseFireBlock.canBePlacedAt(level, blockpos, p_41204_.getHorizontalDirection())) {
                this.playSound(level, blockpos);
                level.setBlockAndUpdate(blockpos, BaseFireBlock.getState(level, blockpos));
                level.gameEvent(p_41204_.getPlayer(), GameEvent.BLOCK_PLACE, blockpos);
                level.setBlockAndUpdate(blockpos0, BaseFireBlock.getState(level, blockpos0));
                level.gameEvent(p_41204_.getPlayer(), GameEvent.BLOCK_PLACE, blockpos0);
                level.setBlockAndUpdate(blockpos1, BaseFireBlock.getState(level, blockpos1));
                level.gameEvent(p_41204_.getPlayer(), GameEvent.BLOCK_PLACE, blockpos1);
                level.setBlockAndUpdate(blockpos2, BaseFireBlock.getState(level, blockpos2));
                level.gameEvent(p_41204_.getPlayer(), GameEvent.BLOCK_PLACE, blockpos2);
                level.setBlockAndUpdate(blockpos3, BaseFireBlock.getState(level, blockpos3));
                level.gameEvent(p_41204_.getPlayer(), GameEvent.BLOCK_PLACE, blockpos3);
                level.setBlockAndUpdate(blockpos4, BaseFireBlock.getState(level, blockpos4));
                level.gameEvent(p_41204_.getPlayer(), GameEvent.BLOCK_PLACE, blockpos4);
                level.setBlockAndUpdate(blockpos5, BaseFireBlock.getState(level, blockpos5));
                level.gameEvent(p_41204_.getPlayer(), GameEvent.BLOCK_PLACE, blockpos5);
                level.setBlockAndUpdate(blockpos6, BaseFireBlock.getState(level, blockpos6));
                level.gameEvent(p_41204_.getPlayer(), GameEvent.BLOCK_PLACE, blockpos6);
                level.setBlockAndUpdate(blockpos7, BaseFireBlock.getState(level, blockpos7));
                level.gameEvent(p_41204_.getPlayer(), GameEvent.BLOCK_PLACE, blockpos7);
                flag = true;
            }
        } else {
            this.playSound(level, blockpos);
            level.setBlockAndUpdate(blockpos, blockstate.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)));
            level.gameEvent(p_41204_.getPlayer(), GameEvent.BLOCK_CHANGE, blockpos);
            flag = true;
        }

        if (flag) {
            p_41204_.getItemInHand().shrink(1);
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.FAIL;
        }
    }
    private void playSound(Level p_41206_, BlockPos p_41207_) {
        RandomSource randomsource = p_41206_.getRandom();
        p_41206_.playSound((Player)null, p_41207_, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, (randomsource.nextFloat() - randomsource.nextFloat()) * 0.2F + 1.0F);
    }
}
