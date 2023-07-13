package net.damku1214.damkusweaponry;

import net.damku1214.damkusweaponry.effect.ModEffects;
import net.damku1214.damkusweaponry.enchantment.ModEnchantments;
import net.damku1214.damkusweaponry.item.ModItems;
import net.damku1214.damkusweaponry.particle.ModParticles;
import net.damku1214.damkusweaponry.sound.ModSounds;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DamkusWeaponry.MOD_ID)
public class DamkusWeaponry {
    public static final String MOD_ID = "damkusweaponry";
    private static final Logger LOGGER = LogManager.getLogger();

    public DamkusWeaponry() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);

        ModSounds.register(eventBus);

        ModEffects.register(eventBus);

        ModParticles.register(eventBus);

        ModEnchantments.register(eventBus);

        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::addCreative);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
