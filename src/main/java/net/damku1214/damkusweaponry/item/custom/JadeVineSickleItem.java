package net.damku1214.damkusweaponry.item.custom;

import net.damku1214.damkusweaponry.effect.ModEffects;
import net.damku1214.damkusweaponry.sound.ModSounds;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;

public class JadeVineSickleItem extends SwordItem {
    public JadeVineSickleItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier,pAttackDamageModifier,pAttackSpeedModifier,pProperties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeRemaining) {
        if (level.isClientSide()) return;
        int chargedFor = getUseDuration(stack) - timeRemaining;
        ServerPlayer player = (ServerPlayer) entity;

        if (chargedFor <= 10) {
            List<LivingEntity> nearbyEntities = level.getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), player,
                    player.getBoundingBox().inflate(5));
            nearbyEntities.forEach(m -> m.hurt(DamageSource.MAGIC, 5));
            nearbyEntities.forEach(m -> m.addEffect(new MobEffectInstance(ModEffects.SKANDHAS_CURSE.get(), 500, 0)));
            level.playSound((Player)null, entity.getX(), entity.getY(), entity.getZ(), ModSounds.JADE_VINE_SICKLE_USE_SHORT.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            player.getCooldowns().addCooldown(this, 100);
        } else {
            List<LivingEntity> nearbySkandhaEntities = level.getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), player,
                    player.getBoundingBox().inflate(10));
            nearbySkandhaEntities.forEach(m -> m.hurt(DamageSource.MAGIC, 7));
            nearbySkandhaEntities.stream().filter(m -> m.hasEffect(ModEffects.SKANDHAS_CURSE.get())).forEach(m -> m.setHealth(m.getHealth() - 1));
            level.playSound((Player)null, entity.getX(), entity.getY(), entity.getZ(), ModSounds.JADE_VINE_SICKLE_USE_LONG.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            player.getCooldowns().addCooldown(this, 160);
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
