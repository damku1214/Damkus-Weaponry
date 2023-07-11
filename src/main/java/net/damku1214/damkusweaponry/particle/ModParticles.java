package net.damku1214.damkusweaponry.particle;

import net.damku1214.damkusweaponry.DamkusWeaponry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, DamkusWeaponry.MOD_ID);

    public static final RegistryObject<SimpleParticleType> BINDING_OF_WISDOM_MAIN_PARTICLES =
            PARTICLE_TYPES.register("binding_of_wisdom_main_particles", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> BINDING_OF_WISDOM_EXTRA1_PARTICLES =
            PARTICLE_TYPES.register("binding_of_wisdom_extra1_particles", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> BINDING_OF_WISDOM_EXTRA2_PARTICLES =
            PARTICLE_TYPES.register("binding_of_wisdom_extra2_particles", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> BINDING_OF_WISDOM_BG_PARTICLES =
            PARTICLE_TYPES.register("binding_of_wisdom_bg_particles", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
