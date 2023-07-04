package net.damku1214.damkusweaponry.effect;

import net.damku1214.damkusweaponry.DamkusWeaponry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DamkusWeaponry.MOD_ID);

    public static final RegistryObject<MobEffect> SKANDHAS_CURSE = MOB_EFFECTS.register("skandhas_curse",
            () -> new SkandhasCurse(MobEffectCategory.HARMFUL, 16730698));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
