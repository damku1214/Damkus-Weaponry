package net.damku1214.damkusweaponry.item;

import net.damku1214.damkusweaponry.DamkusWeaponry;
import net.damku1214.damkusweaponry.item.custom.JadeVineSickleItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DamkusWeaponry.MOD_ID);

    public static final RegistryObject<Item> DAMKU = ITEMS.register("damku",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JADE_VINE_SICKLE = ITEMS.register("jade_vine_sickle",
            () -> new JadeVineSickleItem(ModTiers.JADE,-1, 1,
                    new Item.Properties()));
    public static final RegistryObject<Item> JADE = ITEMS.register("jade",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JADE_HANDLE = ITEMS.register("jade_handle",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEHONITE = ITEMS.register("tehonite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_TEHONITE = ITEMS.register("raw_tehonite",
            () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
