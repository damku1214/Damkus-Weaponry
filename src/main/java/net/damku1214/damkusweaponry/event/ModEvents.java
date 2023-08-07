package net.damku1214.damkusweaponry.event;

import net.damku1214.damkusweaponry.DamkusWeaponry;
import net.damku1214.damkusweaponry.effect.ModEffects;
import net.damku1214.damkusweaponry.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
            ItemStack air = new ItemStack(Items.AIR);
            Item mainhand = entity.getItemInHand(InteractionHand.MAIN_HAND).getItem();
            Item offhand = entity.getItemInHand(InteractionHand.OFF_HAND).getItem();
            Item capacitor = ModItems.OVERCHARGED_CAPACITOR.get();
            if (mainhand.equals(capacitor) || offhand.equals(capacitor)) {
                entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.GENERIC_EXPLODE,
                        SoundSource.PLAYERS, 1.0F, 1F);
                entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.SHIELD_BREAK,
                        SoundSource.PLAYERS, 1.0F, 1F);
                ((ServerLevel)entity.level()).sendParticles(ParticleTypes.EXPLOSION, entity.getX(), entity.getY(0.5D),
                        entity.getZ(), 30, 0.2, 0.2, 0.2, 0.0D);
                entity.hurt(entity.damageSources().explosion(entity, entity), 30);
            } if (mainhand.equals(capacitor)) {
                entity.setItemInHand(InteractionHand.MAIN_HAND, air);
            } else if (offhand.equals(capacitor)) {
                entity.setItemInHand(InteractionHand.OFF_HAND, air);
            }
        }
    }
}
