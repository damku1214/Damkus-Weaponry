package net.damku1214.damkusweaponry.effect;

import net.damku1214.damkusweaponry.event.ModEvents;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class SkandhasCurse extends MobEffect {
    public SkandhasCurse(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
}
