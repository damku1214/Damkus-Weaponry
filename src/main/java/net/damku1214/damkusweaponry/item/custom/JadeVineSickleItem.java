package net.damku1214.damkusweaponry.item.custom;

import net.damku1214.damkusweaponry.effect.ModEffects;
import net.damku1214.damkusweaponry.particle.ModParticles;
import net.damku1214.damkusweaponry.sound.ModSounds;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JadeVineSickleItem extends SwordItem {
    public JadeVineSickleItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier,pAttackDamageModifier,pAttackSpeedModifier,pProperties);
    }

    // Set using animation to, you guessed it, like a bow.
    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    // When stop holding right click
    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeRemaining) {
        if (level.isClientSide()) return;
        int chargedFor = getUseDuration(stack) - timeRemaining;
        ServerPlayer player = (ServerPlayer) entity;

        if (chargedFor <= 10) {
            // Listing nearby entities, pValue = radius
            List<LivingEntity> nearbyEntities = level.getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), player,
                    player.getBoundingBox().inflate(4));
            // Hurt & apply Skandha's Curse on enemies on list
            nearbyEntities.forEach(m -> m.hurt(m.damageSources().magic(), 5));
            nearbyEntities.forEach(m -> m.addEffect(new MobEffectInstance(ModEffects.SKANDHAS_CURSE.get(), 500, 0)));
            // Play sound
            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSounds.JADE_VINE_SICKLE_USE_SHORT.get(),
                    SoundSource.PLAYERS, 1.0F, 1.0F);
            // Particle method
            bindingOfWisdomParticles(level, entity);
            // Add cooldown
            player.getCooldowns().addCooldown(this, 100);
        } else {
            // Listing nearby entities, pValue = radius
            List<LivingEntity> nearbySkandhaEntities = level.getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), player,
                    player.getBoundingBox().inflate(6));
            // Hurt enemies on list
            nearbySkandhaEntities.forEach(m -> m.hurt(m.damageSources().magic(), 7));
            // Hurt enemies with Skandha's Curse again (extra damage)
            nearbySkandhaEntities.stream().filter(m -> m.hasEffect(ModEffects.SKANDHAS_CURSE.get())).forEach(m -> m.setHealth(m.getHealth() - 4));
            // Play sound
            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSounds.JADE_VINE_SICKLE_USE_LONG.get(),
                    SoundSource.PLAYERS, 1.0F, 1.0F);
            //Particle method
            triKarmaBacklashParticles(level, entity);
            // Add cooldown
            player.getCooldowns().addCooldown(this, 160);
        }
    }

    private void bindingOfWisdomParticles(Level level, LivingEntity entity) {
        // Loop of particles going in a circle, i = angle, angle 0 = player look direction (not sure)
        for (int i = 140; i > -100; -- i) {
            // Number multiplying the sin/cos at the very start = circle's radius
            double d0 = (5.3 * -Mth.sin((entity.getYRot() + i) * ((float)Math.PI / 180F)));
            double d1 = 5.3 * Mth.cos((entity.getYRot() + i) * ((float)Math.PI / 180F));
            double d2 = (5 * -Mth.sin((entity.getYRot() + i) * ((float)Math.PI / 180F)));
            double d3 = 5 * Mth.cos((entity.getYRot() + i) * ((float)Math.PI / 180F));
            double d4 = (4.7 * -Mth.sin((entity.getYRot() + i) * ((float)Math.PI / 180F)));
            double d5 = 4.7 * Mth.cos((entity.getYRot() + i) * ((float)Math.PI / 180F));
            if (i % 7 == 0) {
                ((ServerLevel)level).sendParticles(ModParticles.BINDING_OF_WISDOM_BG_PARTICLES.get(), entity.getX() + d2, entity.getY((0.5D) + (double) (-(i - 140)) / 400),
                        entity.getZ() + d3, 1, 0, 0, 0, 0.0D);
            }
            ((ServerLevel)level).sendParticles(ModParticles.BINDING_OF_WISDOM_MAIN_PARTICLES.get(), entity.getX() + d0, entity.getY((0.5D) + (double) (-(i - 140)) / 400),
                    entity.getZ() + d1, 5, 0, 0, 0, 0.0D);
            ((ServerLevel)level).sendParticles(ModParticles.BINDING_OF_WISDOM_MAIN_PARTICLES.get(), entity.getX() + d4, entity.getY((0.5D) + (double) (-(i - 140)) / 400),
                    entity.getZ() + d5, 5, 0, 0, 0, 0.0D);
            if (i % 40 == 0) {
                ((ServerLevel)level).sendParticles(ModParticles.BINDING_OF_WISDOM_EXTRA1_PARTICLES.get(), entity.getX() + d0, entity.getY((0.5D) + (double) (-(i - 140)) / 400),
                        entity.getZ() + d1, 2, 1, 0.5, 1, 0.0D);
                ((ServerLevel)level).sendParticles(ModParticles.BINDING_OF_WISDOM_EXTRA2_PARTICLES.get(), entity.getX() + d0, entity.getY((0.5D) + (double) (-(i - 140)) / 400),
                        entity.getZ() + d1, 2, 1, 0.5, 1, 0.0D);
            }
        }
    }

    private void triKarmaBacklashParticles(Level level, LivingEntity entity) {
        for (int r = 0; r < 5; r ++) {
            switch (r) {
                case (0) -> {
                    for (int i = 0; i < 360; i ++) {
                        double d0 = (4 * -Mth.sin(i * ((float)Math.PI / 180F)));
                        double d1 = 4 * Mth.cos(i * ((float)Math.PI / 180F));
                        ((ServerLevel)level).sendParticles(ModParticles.TRI_KARMA_BACKLASH_EXTRA1_PARTICLES.get(), entity.getX() + d0, entity.getY(0.5D),
                                entity.getZ() + d1, 1, 0, 0, 0, 0.0D);
                    }
                }
                case (1) -> {
                    for (int i = 0; i < 360; i ++) {
                        double d0 = (4.5 * -Mth.sin(i * ((float)Math.PI / 180F)));
                        double d1 = 4.5 * Mth.cos(i * ((float)Math.PI / 180F));
                        ((ServerLevel)level).sendParticles(ModParticles.TRI_KARMA_BACKLASH_EXTRA2_PARTICLES.get(), entity.getX() + d0, entity.getY(0.5D),
                                entity.getZ() + d1, 1, 0, 0, 0, 0.0D);
                    }
                }
                case (2) -> {
                    for (int i = 0; i < 360; i ++) {
                        double d0 = (5 * -Mth.sin(i * ((float)Math.PI / 180F)));
                        double d1 = 5 * Mth.cos(i * ((float)Math.PI / 180F));
                        ((ServerLevel)level).sendParticles(ModParticles.TRI_KARMA_BACKLASH_EXTRA3_PARTICLES.get(), entity.getX() + d0, entity.getY(0.5D),
                                entity.getZ() + d1, 1, 0, 0, 0, 0.0D);
                    }
                }
                case (3) -> {
                    for (int i = 0; i < 360; i ++) {
                        double d0 = (5.5 * -Mth.sin(i * ((float)Math.PI / 180F)));
                        double d1 = 5.5 * Mth.cos(i * ((float)Math.PI / 180F));
                        ((ServerLevel)level).sendParticles(ModParticles.TRI_KARMA_BACKLASH_EXTRA4_PARTICLES.get(), entity.getX() + d0, entity.getY(0.5D),
                                entity.getZ() + d1, 1, 0, 0, 0, 0.0D);
                    }
                }
                case (4) -> {
                    for (int i = 0; i < 360; i ++) {
                        double d0 = (6 * -Mth.sin(i * ((float)Math.PI / 180F)));
                        double d1 = 6 * Mth.cos(i * ((float)Math.PI / 180F));
                        ((ServerLevel)level).sendParticles(ModParticles.TRI_KARMA_BACKLASH_MAIN_PARTICLES.get(), entity.getX() + d0, entity.getY(0.5D),
                                entity.getZ() + d1, 1, 0, 0, 0, 0.0D);
                        if (i % 30 == 0) {
                            ((ServerLevel) level).sendParticles(ModParticles.BINDING_OF_WISDOM_EXTRA1_PARTICLES.get(), entity.getX() + d0, entity.getY(0.5D),
                                    entity.getZ() + d1, 1, 1, 0.5, 1, 0.0D);
                            ((ServerLevel) level).sendParticles(ModParticles.BINDING_OF_WISDOM_EXTRA2_PARTICLES.get(), entity.getX() + d0, entity.getY(0.5D),
                                    entity.getZ() + d1, 1, 1, 0.5, 1, 0.0D);
                        }
                    }
                    ((ServerLevel) level).sendParticles(ModParticles.BINDING_OF_WISDOM_EXTRA1_PARTICLES.get(), entity.getX(), entity.getY(0.5D),
                            entity.getZ(), 10, 0.5, 0.5, 0.5, 0.5D);
                    ((ServerLevel) level).sendParticles(ModParticles.BINDING_OF_WISDOM_EXTRA2_PARTICLES.get(), entity.getX(), entity.getY(0.5D),
                            entity.getZ(), 10, 0.5, 0.5, 0.5, 0.5D);
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.damkusweaponry.jade_vine_sickle.tooltip.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.damkusweaponry.jade_vine_sickle.tooltip"));
        }
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }
}
