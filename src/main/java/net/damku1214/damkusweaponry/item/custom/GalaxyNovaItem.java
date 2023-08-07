package net.damku1214.damkusweaponry.item.custom;

import net.damku1214.damkusweaponry.particle.ModParticles;
import net.damku1214.damkusweaponry.sound.ModSounds;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class GalaxyNovaItem extends SwordItem {
    public GalaxyNovaItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier,pAttackDamageModifier,pAttackSpeedModifier,pProperties);
    }
    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level level, LivingEntity entity, int timeRemaining) {
        if (level.isClientSide()) return;
        ServerPlayer player = (ServerPlayer) entity;
        // Assign field chargedFor
        int chargedFor = getUseDuration(pStack) - timeRemaining;
        // Spawn markers
        if (chargedFor >= 118 && chargedFor < 182) {
            spawnMarkers(entity, 0, level, player);
            pStack.setDamageValue(pStack.getDamageValue() + 20);
            player.getCooldowns().addCooldown(this, 200);
        } else if (chargedFor >= 182 && chargedFor < 246) {
            spawnMarkers(entity, 1, level, player);
            pStack.setDamageValue(pStack.getDamageValue() + 30);
            player.getCooldowns().addCooldown(this, 300);
        } else if (chargedFor >= 246 && chargedFor < 341) {
            spawnMarkers(entity, 2, level, player);
            pStack.setDamageValue(pStack.getDamageValue() + 40);
            player.getCooldowns().addCooldown(this, 400);
        }
        // Get fields for hands & air
        ItemStack air = new ItemStack(Items.AIR);
        System.out.println(pStack.getDamageValue());
        // Break item if durability is negative
        if (pStack.getDamageValue() > 1999) {
            hurtAndBreak(1, entity, (entity1) -> {
                entity1.broadcastBreakEvent(entity.getUsedItemHand());
            }, pStack);
        }
    }

    private  <T extends LivingEntity> void hurtAndBreak(int amount, T entity, Consumer<T> entity1, ItemStack pStack) {
        amount = pStack.getItem().damageItem(pStack, amount, entity, entity1);
        if (pStack.hurt(amount, entity.getRandom(), entity instanceof ServerPlayer ? (ServerPlayer) entity : null)) {
            entity1.accept(entity);
            Item item = pStack.getItem();
            pStack.shrink(1);
            if (entity instanceof Player) {
                ((Player) entity).awardStat(Stats.ITEM_BROKEN.get(item));
            }
            pStack.setDamageValue(0);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int timeRemaining) {
        if (level.isClientSide()) return;
        ServerPlayer player = (ServerPlayer) entity;
        int chargedFor = getUseDuration(stack) - timeRemaining;
        switch (chargedFor) {
            // Start
            case (1) -> level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ARMOR_EQUIP_NETHERITE,
                        SoundSource.PLAYERS, 1.0F, 0.5F);
            // Finish rotating
            case (52) -> level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.IRON_GOLEM_HURT,
                    SoundSource.PLAYERS, 1.0F, 0.5F);
            // Lower (Cloud)
            case (98) -> {
                galaxyLowerParticles(level, player);
                level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.FIRE_EXTINGUISH,
                        SoundSource.PLAYERS, 1.0F, 1.0F);
            }
            // Charge 0 (Blue)
            case (118) -> {
                galaxyChargeParticles(level, player, ModParticles.GALAXY_NOVA_CHARGE_0);
                level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.BEACON_ACTIVATE,
                        SoundSource.PLAYERS, 1.0F, 1.0F);
                level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ZOMBIE_VILLAGER_CURE,
                        SoundSource.PLAYERS, 1.0F, 2.0F);
            }
            // Charge 1 (Red)
            case (182) -> {
                galaxyChargeParticles(level, player, ModParticles.GALAXY_NOVA_CHARGE_1);
                level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.BEACON_ACTIVATE,
                        SoundSource.PLAYERS, 1.0F, 1.5F);
                level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ZOMBIE_VILLAGER_CURE,
                        SoundSource.PLAYERS, 1.0F, 1.5F);
            }

            // Charge 2 (Purple)
            case (246) -> {
                galaxyChargeParticles(level, player, ModParticles.GALAXY_NOVA_CHARGE_2);
                level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.BEACON_ACTIVATE,
                        SoundSource.PLAYERS, 1.0F, 2.0F);
                level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ZOMBIE_VILLAGER_CURE,
                        SoundSource.PLAYERS, 1.0F, 1.0F);
            }

            // Failure
            case (340) -> {
                galaxyExplode(level, entity);
                level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.GENERIC_EXPLODE,
                        SoundSource.PLAYERS, 1.0F, 0.5F);
                level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.LIGHTNING_BOLT_IMPACT,
                        SoundSource.PLAYERS, 1.0F, 0.5F);
                level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.IRON_GOLEM_DEATH,
                        SoundSource.PLAYERS, 1.0F, 0.5F);
                stack.setDamageValue(stack.getDamageValue() + 100);
                player.getCooldowns().addCooldown(this, 600);
            }
        }
        super.onUseTick(level, entity, stack, timeRemaining);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.damkusweaponry.galaxy_nova.tooltip.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.damkusweaponry.before_shift.tooltip"));
        }
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 341;
    }

    private void galaxyChargeParticles(Level level, LivingEntity entity, RegistryObject<SimpleParticleType> particle) {
        ((ServerLevel) level).sendParticles(particle.get(), entity.getX(), entity.getY(0.5D),
                entity.getZ(), 15, 0.5, 0.5, 0.5, 0.25D);
    }
    private void galaxyLowerParticles(Level level, LivingEntity entity) {
        ((ServerLevel) level).sendParticles(ParticleTypes.CLOUD, entity.getX(), entity.getY(1.0D),
                entity.getZ(), 10, 0.5, 0.5, 0.5, 0.1D);
    }
    private void galaxyExplodeParticles(Level level, LivingEntity entity) {
        ((ServerLevel) level).sendParticles(ParticleTypes.EXPLOSION, entity.getX(), entity.getY(1.0D),
                entity.getZ(), 500, 5, 5, 5, 0.0D);
    }

    private void galaxyExplode(Level level, LivingEntity entity) {
        ServerPlayer player = (ServerPlayer) entity;
        List<LivingEntity> nearbyEntities = level.getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), player,
                player.getBoundingBox().inflate(7));
        nearbyEntities.forEach(m -> m.hurt(m.damageSources().explosion(player, player), 50));
        player.hurt(player.damageSources().explosion(player, player), 50);
        galaxyExplodeParticles(level, player);
    }
    
    private void spawnMarkers(LivingEntity entity, int chargeLevel, Level level, Player player) {
        ServerLevel serverlevel = (ServerLevel)entity.level();
        ServerPlayer serverPlayer = ((ServerPlayer) player);
        // Initial positioning
        Vec3 position = entity.position();
        BlockPos blockPos = entity.blockPosition();
        // Particle delta
        double d = 0.7 + chargeLevel * 0.1;
        // Count of how many markers should be placed
        int markerCount = 60 + chargeLevel * 30;
        for (int i = 0; i < markerCount; i ++) {
            // i = length between each marker
            // Alter spawn position
            Vec3 spawnPosition = new Vec3(position.x + dx(entity, horizontalDistanceFromEntity(entity, 3 + i)),
                    position.y + 1 + verticalDistanceFromEntity(entity, 3 + i),
                    position.z + dz(entity, horizontalDistanceFromEntity(entity, 3 + i)));
            // Show particles (Negates particle range cap !!! IMPORTANT !!!)
            ((ServerLevel)level).sendParticles(serverPlayer, ModParticles.SHORT_END_ROD.get(), true, spawnPosition.x,
                    spawnPosition.y, spawnPosition.z, 2 * (chargeLevel + 1), d, d, d, 0.0D);
            // Show particles 2 (differ by charge level)
            switch (chargeLevel) {
                case (0) -> {
                    ((ServerLevel)level).sendParticles(serverPlayer, ModParticles.GALAXY_NOVA_EXPLOSION_0_EXTRA.get(), true, spawnPosition.x,
                            spawnPosition.y, spawnPosition.z, 2, d, d, d, 0.0D);
                    ((ServerLevel)level).sendParticles(serverPlayer, ModParticles.GALAXY_NOVA_EXPLOSION_0.get(), true, spawnPosition.x,
                            spawnPosition.y, spawnPosition.z, 1, 0, 0, 0, 0.0D);
                }
                case (1) -> {
                    ((ServerLevel)level).sendParticles(serverPlayer, ModParticles.GALAXY_NOVA_EXPLOSION_1_EXTRA.get(), true, spawnPosition.x,
                            spawnPosition.y, spawnPosition.z, 4, d, d, d, 0.0D);
                    ((ServerLevel)level).sendParticles(serverPlayer, ModParticles.GALAXY_NOVA_EXPLOSION_1.get(), true, spawnPosition.x,
                            spawnPosition.y, spawnPosition.z, 1, 0, 0, 0, 0.0D);
                }
                case (2) -> {
                    ((ServerLevel)level).sendParticles(serverPlayer, ModParticles.GALAXY_NOVA_EXPLOSION_2_EXTRA.get(), true, spawnPosition.x,
                            spawnPosition.y, spawnPosition.z, 8, d, d, d, 0.0D);
                    ((ServerLevel)level).sendParticles(serverPlayer, ModParticles.GALAXY_NOVA_EXPLOSION_2.get(), true, spawnPosition.x,
                            spawnPosition.y, spawnPosition.z, 1, 0, 0, 0, 0.0D);
                }
            }
            // Spawn marker
            Marker damageMarker = EntityType.MARKER.spawn(serverlevel, blockPos, MobSpawnType.COMMAND);
            if (damageMarker != null) {
                damageMarker.moveTo(spawnPosition);
                markersActivate(damageMarker, level, chargeLevel, player);
            }
        }
        switch (chargeLevel) {
            case (0) -> level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSounds.GALAXY_NOVA_SHOOT.get(),
                    SoundSource.PLAYERS, 1.0F, 1.5F);
            case (1) -> level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSounds.GALAXY_NOVA_SHOOT.get(),
                    SoundSource.PLAYERS, 1.0F, 1.25F);
            case (2) -> level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSounds.GALAXY_NOVA_SHOOT.get(),
                    SoundSource.PLAYERS, 1.0F, 1.0F);
        }
        ((ServerLevel) level).sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, entity.getX(), entity.getY(1.0D),
                entity.getZ(), 50, 0, 0, 0, 0.005 + (chargeLevel + 1) * 0.01);
    }

    private void markersActivate(Marker marker, Level level, int chargeLevel, Player player) {
        // Detect entities in range of markers
        List<LivingEntity> nearbyGalaxyEntities = level.getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), player,
                marker.getBoundingBox().inflate(3 + chargeLevel));
        // Damage targets
        nearbyGalaxyEntities.forEach(m -> m.hurt(m.damageSources().explosion(player, player), 50 + 25 * chargeLevel));
        // Remove markers
        marker.remove(Entity.RemovalReason.KILLED);
    }

    private double dx(LivingEntity entity, double horizontalDistanceFromEntity) {
        return (horizontalDistanceFromEntity * -Mth.sin(entity.getYRot() * ((float)Math.PI / 180F)));
    }
    private double dz(LivingEntity entity, double horizontalDistanceFromEntity) {
        return horizontalDistanceFromEntity * Mth.cos(entity.getYRot() * ((float)Math.PI / 180F));
    }

    private double verticalDistanceFromEntity(LivingEntity entity, double distanceFromEntity) {
        return distanceFromEntity * -Mth.sin(entity.getXRot() * ((float)Math.PI / 180F));
    }
    private double horizontalDistanceFromEntity(LivingEntity entity, double distanceFromEntity) {
        return distanceFromEntity * Mth.cos(entity.getXRot() * ((float)Math.PI / 180F));
    }
}
