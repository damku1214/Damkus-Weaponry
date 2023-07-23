package net.damku1214.damkusweaponry.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TehoniteBowItem extends BowItem {
    public TehoniteBowItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void releaseUsing(ItemStack p_40667_, Level p_40668_, LivingEntity p_40669_, int p_40670_) {
        p_40670_ = getUseDuration(p_40667_) - 16;
        super.releaseUsing(p_40667_, p_40668_, p_40669_, p_40670_);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.damkusweaponry.tehonite_bow.tooltip.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.damkusweaponry.before_shift.tooltip"));
        }
    }
}
