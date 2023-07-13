package net.damku1214.damkusweaponry.event;

import net.damku1214.damkusweaponry.DamkusWeaponry;
import net.damku1214.damkusweaponry.particle.ModParticles;
import net.damku1214.damkusweaponry.particle.custom.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DamkusWeaponry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.BINDING_OF_WISDOM_MAIN_PARTICLES.get(),
                BindingOfWisdomMainParticles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.BINDING_OF_WISDOM_EXTRA1_PARTICLES.get(),
                BindingOfWisdomExtra1Particles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.BINDING_OF_WISDOM_EXTRA2_PARTICLES.get(),
                BindingOfWisdomExtra2Particles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.BINDING_OF_WISDOM_BG_PARTICLES.get(),
                BindingOfWisdomBgParticles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.TRI_KARMA_BACKLASH_EXTRA1_PARTICLES.get(),
                TriKarmaBacklashExtra1Particles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.TRI_KARMA_BACKLASH_EXTRA2_PARTICLES.get(),
                TriKarmaBacklashExtra2Particles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.TRI_KARMA_BACKLASH_EXTRA3_PARTICLES.get(),
                TriKarmaBacklashExtra3Particles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.TRI_KARMA_BACKLASH_EXTRA4_PARTICLES.get(),
                TriKarmaBacklashExtra4Particles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.TRI_KARMA_BACKLASH_MAIN_PARTICLES.get(),
                TriKarmaBacklashMainParticles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.DESTINY_ELUCIDATION_1_PARTICLES.get(),
                DestinyElucidation1Particles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.DESTINY_ELUCIDATION_2_PARTICLES.get(),
                DestinyElucidation2Particles.Provider::new);
    }
}
