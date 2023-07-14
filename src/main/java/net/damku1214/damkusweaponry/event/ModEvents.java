package net.damku1214.damkusweaponry.event;

import net.damku1214.damkusweaponry.DamkusWeaponry;
import net.damku1214.damkusweaponry.effect.ModEffects;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
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
    }
}
