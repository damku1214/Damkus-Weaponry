package net.damku1214.damkusweaponry.event;

import net.damku1214.damkusweaponry.DamkusWeaponry;
import net.damku1214.damkusweaponry.effect.ModEffects;
import net.damku1214.damkusweaponry.item.ModItems;
import net.damku1214.damkusweaponry.item.custom.OverchargedCapacitorItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

public class ModEvents {

    @Mod.EventBusSubscriber(modid = DamkusWeaponry.MOD_ID)
    static class ForgeEvents {
        @SubscribeEvent
        public static void skandhaHit(LivingDamageEvent event) {

            float skandhaDamage;

            if (event.getEntity().getMaxHealth() <= 50) {
                skandhaDamage = event.getEntity().getMaxHealth() / 10;
            } else if (event.getEntity().getMaxHealth() <= 100) {
                skandhaDamage = event.getEntity().getMaxHealth() / 20;
            } else if (event.getEntity().getMaxHealth() <= 250) {
                skandhaDamage = event.getEntity().getMaxHealth() / 50;
            } else {
                skandhaDamage = event.getEntity().getMaxHealth() / 100;
            }

            if (event.getEntity().hasEffect(ModEffects.SKANDHAS_CURSE.get())) {
                event.getEntity().setHealth(event.getEntity().getHealth() - skandhaDamage);
            }
        }

        @SubscribeEvent
        public static void stickyFeetTick(LivingEvent.LivingTickEvent event) {
            Vec3 vec3 = event.getEntity().getDeltaMovement();
            BlockPos playerPosition = event.getEntity().blockPosition();
            BlockState state = event.getEntity().level().getBlockState(playerPosition.below(1));
            if (event.getEntity().hasEffect(ModEffects.STICKY_FEET.get())) {
                if (vec3.y > 0 && !state.isAir()) {
                    event.getEntity().setDeltaMovement(new Vec3(vec3.x, -vec3.y, vec3.z));
                }
            }
        }

        @SubscribeEvent
        public static void stickyFeetJump(LivingEvent.LivingJumpEvent event) {
            Vec3 vec3 = event.getEntity().getDeltaMovement();
            if (event.getEntity().hasEffect(ModEffects.STICKY_FEET.get())) {
                if (vec3.y > 0) {
                    event.getEntity().setDeltaMovement(new Vec3(vec3.x, -vec3.y, vec3.z));
                }
            }
        }

        @SubscribeEvent
        public static void capacitorExplode(LivingDamageEvent event) {
            LivingEntity entity = event.getEntity();
            ServerPlayer player = (ServerPlayer) entity;
            ItemStack air = new ItemStack(Items.AIR);
            Level level = entity.level();
            Item mainhand = entity.getItemInHand(InteractionHand.MAIN_HAND).getItem();
            Item offhand = entity.getItemInHand(InteractionHand.OFF_HAND).getItem();
            if (mainhand instanceof OverchargedCapacitorItem || offhand instanceof OverchargedCapacitorItem) {
                entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.GENERIC_EXPLODE,
                        SoundSource.PLAYERS, 1.0F, 1F);
                entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.SHIELD_BREAK,
                        SoundSource.PLAYERS, 1.0F, 1F);
                ((ServerLevel)entity.level()).sendParticles(ParticleTypes.EXPLOSION, entity.getX(), entity.getY(0.5D),
                        entity.getZ(), 30, 0.2, 0.2, 0.2, 0.0D);
                List<LivingEntity> nearbyEntities = level.getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), player,
                        player.getBoundingBox().inflate(2));
                nearbyEntities.forEach(m -> m.hurt(m.damageSources().explosion(player, player), 30));
                player.hurt(player.damageSources().explosion(player, player), 30);
            } if (mainhand instanceof OverchargedCapacitorItem) {
                entity.setItemInHand(InteractionHand.MAIN_HAND, air);
            } else if (offhand instanceof OverchargedCapacitorItem) {
                entity.setItemInHand(InteractionHand.OFF_HAND, air);
            }
        }
    }
}
