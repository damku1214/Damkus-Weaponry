package net.damku1214.damkusweaponry.event;

import net.damku1214.damkusweaponry.DamkusWeaponry;
import net.damku1214.damkusweaponry.effect.ModEffects;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

public class ModEvents {

    @Mod.EventBusSubscriber(modid = DamkusWeaponry.MOD_ID)
    static class ForgeEvents {
        @SubscribeEvent
        public static void skandhaHit(LivingDamageEvent event) {

            float skandhaDamage;

            if (event.getEntityLiving().getMaxHealth() < 50) {
                skandhaDamage = event.getEntityLiving().getMaxHealth() / 10;
            } else if (event.getEntityLiving().getMaxHealth() < 100) {
                skandhaDamage = event.getEntityLiving().getMaxHealth() / 20;
            } else {
                skandhaDamage = event.getEntityLiving().getMaxHealth() / 50;
            }

            if (event.getEntityLiving().hasEffect(ModEffects.SKANDHAS_CURSE.get())) {
                event.getEntityLiving().setHealth(event.getEntityLiving().getHealth() - skandhaDamage);
            }
        }
    }
}
