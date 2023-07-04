package net.damku1214.damkusweaponry.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SkandhasCurse extends MobEffect {
    public SkandhasCurse(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (isDurationEffectTick(0, 1)) {
            pLivingEntity.hurt(DamageSource.MAGIC, pAmplifier * 3);
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        if (pDuration > 0) {
            return true;
        } else {
            return false;
        }
    }
}
