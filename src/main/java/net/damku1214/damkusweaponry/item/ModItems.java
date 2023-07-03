package net.damku1214.damkusweaponry.item;

import net.damku1214.damkusweaponry.DamkusWeaponry;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DamkusWeaponry.MOD_ID);

    public static final RegistryObject<Item> JADE = ITEMS.register("jade",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DAMKUS_WEAPONRY)));

    public static final RegistryObject<Item> REFINED_JADE = ITEMS.register("refined_jade",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DAMKUS_WEAPONRY)));

    public static final RegistryObject<Item> JADE_VINE_SICKLE = ITEMS.register("jade_vine_sickle",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DAMKUS_WEAPONRY)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
