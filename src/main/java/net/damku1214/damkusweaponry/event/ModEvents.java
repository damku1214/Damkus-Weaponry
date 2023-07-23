package net.damku1214.damkusweaponry.event;

import net.damku1214.damkusweaponry.DamkusWeaponry;
import net.damku1214.damkusweaponry.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
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
    }
}
