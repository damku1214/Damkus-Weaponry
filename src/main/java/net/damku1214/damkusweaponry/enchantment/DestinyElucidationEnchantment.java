package net.damku1214.damkusweaponry.enchantment;

import net.damku1214.damkusweaponry.effect.ModEffects;
import net.damku1214.damkusweaponry.particle.ModParticles;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class DestinyElucidationEnchantment extends Enchantment {
    public DestinyElucidationEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        if (!pAttacker.level().isClientSide) {
            ServerLevel world = ((ServerLevel) pAttacker.level());
            int affectChance = getRandomNumber();
            if (affectChance <= pLevel - 1) {
                ((LivingEntity)pTarget).addEffect(new MobEffectInstance(ModEffects.SKANDHAS_CURSE.get(), 100, 0));
                world.sendParticles(ModParticles.DESTINY_ELUCIDATION_1_PARTICLES.get(), pTarget.getX(), pTarget.getY(0.5D),
                        pTarget.getZ(), 5, 0.1, 0.1, 0.1, 0.25D);
                world.sendParticles(ModParticles.DESTINY_ELUCIDATION_2_PARTICLES.get(), pTarget.getX(), pTarget.getY(0.5D),
                        pTarget.getZ(), 5, 0.1, 0.5, 0.1, 0.25D);
            }
        }
        super.doPostAttack(pAttacker, pTarget, pLevel);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    private int getRandomNumber() {
        int min = 0;
        int max = 199;
        return (int)Math.floor(Math.random() * (max - min + 1) + min);
    }
}
