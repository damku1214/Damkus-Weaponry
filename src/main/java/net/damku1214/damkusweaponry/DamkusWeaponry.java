package net.damku1214.damkusweaponry;

import net.damku1214.damkusweaponry.block.ModBlocks;
import net.damku1214.damkusweaponry.effect.ModEffects;
import net.damku1214.damkusweaponry.enchantment.ModEnchantments;
import net.damku1214.damkusweaponry.item.ModCreativeModeTabs;
import net.damku1214.damkusweaponry.item.ModItems;
import net.damku1214.damkusweaponry.particle.ModParticles;
import net.damku1214.damkusweaponry.potion.ModPotions;
import net.damku1214.damkusweaponry.sound.ModSounds;
import net.damku1214.damkusweaponry.util.BetterBrewingRecipe;
import net.damku1214.damkusweaponry.util.ModItemProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
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

        ModCreativeModeTabs.register(eventBus);
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModSounds.register(eventBus);
        ModEffects.register(eventBus);
        ModParticles.register(eventBus);
        ModEnchantments.register(eventBus);
        ModPotions.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::addCreative);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.LEAPING,
                    Items.HONEYCOMB, ModPotions.STICKY_FEET_POTION.get()));
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.JADE);
            event.accept(ModItems.JADE_HANDLE);
            event.accept(ModItems.TEHONITE_UPPGRADE_SMITHING_TEMPLATE);
            event.accept(ModItems.TEHONITE);
            event.accept(ModItems.RAW_TEHONITE);
            event.accept(ModItems.MOLTEN_TEHONITE);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.JADE_VINE_SICKLE);
            event.accept(ModItems.TEHONITE_BOW);
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.TEHONITE_BLOCK);
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.TEHONITE_ORE);
            event.accept(ModBlocks.DEEPSLATE_TEHONITE_ORE);
        }
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ModItemProperties.addCustomItemProperties();
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
