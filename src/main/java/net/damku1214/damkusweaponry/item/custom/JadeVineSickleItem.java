package net.damku1214.damkusweaponry.item.custom;

import net.damku1214.damkusweaponry.effect.ModEffects;
import net.damku1214.damkusweaponry.particle.ModParticles;
import net.damku1214.damkusweaponry.sound.ModSounds;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

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
            nearbyEntities.forEach(m -> m.hurt(DamageSource.MAGIC, 5));
            nearbyEntities.forEach(m -> m.addEffect(new MobEffectInstance(ModEffects.SKANDHAS_CURSE.get(), 500, 0)));
            // Play sound
            level.playSound((Player)null, entity.getX(), entity.getY(), entity.getZ(), ModSounds.JADE_VINE_SICKLE_USE_SHORT.get(),
                    SoundSource.PLAYERS, 1.0F, 1.0F);
            // Particle method
            jadeVineSickleShortParticles(level, entity);
            // Add cooldown
            player.getCooldowns().addCooldown(this, 100);
        } else {
            // Listing nearby entities, pValue = radius
            List<LivingEntity> nearbySkandhaEntities = level.getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), player,
                    player.getBoundingBox().inflate(6));
            // Hurt enemies on list
            nearbySkandhaEntities.forEach(m -> m.hurt(DamageSource.MAGIC, 7));
            // Hurt enemies with Skandha's Curse again (extra damage)
            nearbySkandhaEntities.stream().filter(m -> m.hasEffect(ModEffects.SKANDHAS_CURSE.get())).forEach(m -> m.setHealth(m.getHealth() - 1));
            // Play sound
            level.playSound((Player)null, entity.getX(), entity.getY(), entity.getZ(), ModSounds.JADE_VINE_SICKLE_USE_LONG.get(),
                    SoundSource.PLAYERS, 1.0F, 1.0F);
            // Add cooldown
            player.getCooldowns().addCooldown(this, 160);
        }
    }

    private void jadeVineSickleShortParticles(Level level, LivingEntity entity) {
        // Loop of particles going in a circle, i = angle, angle 0 = player look direction (not sure)
        for (int i = 140; i > -100; -- i) {
            // Number multiplying the sin/cos at the very start = circle's radius
            double d0 = (double)(5.3 * -Mth.sin((entity.getYRot() + i) * ((float)Math.PI / 180F)));
            double d1 = (double)5.3 * Mth.cos((entity.getYRot() + i) * ((float)Math.PI / 180F));
            double d2 = (double)(5 * -Mth.sin((entity.getYRot() + i) * ((float)Math.PI / 180F)));
            double d3 = (double)5 * Mth.cos((entity.getYRot() + i) * ((float)Math.PI / 180F));
            double d4 = (double)(4.7 * -Mth.sin((entity.getYRot() + i) * ((float)Math.PI / 180F)));
            double d5 = (double)4.7 * Mth.cos((entity.getYRot() + i) * ((float)Math.PI / 180F));
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

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }
}
