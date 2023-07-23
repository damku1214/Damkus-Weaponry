package net.damku1214.damkusweaponry.potion;

import net.damku1214.damkusweaponry.DamkusWeaponry;
import net.damku1214.damkusweaponry.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, DamkusWeaponry.MOD_ID);

    public static final RegistryObject<Potion> STICKY_FEET_POTION = POTIONS.register("sticky_feet_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.STICKY_FEET.get(), 300, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
