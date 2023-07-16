package net.damku1214.damkusweaponry.item;

import net.damku1214.damkusweaponry.DamkusWeaponry;
import net.damku1214.damkusweaponry.item.custom.JadeVineSickleItem;
import net.minecraft.world.item.*;
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
    public static final RegistryObject<Item> MOLTEN_TEHONITE = ITEMS.register("molten_tehonite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ULTIMATE_TEHONITE = ITEMS.register("ultimate_tehonite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ULTIMATE_TEHONITE_SWORD = ITEMS.register("ultimate_tehonite_sword",
            () -> new SwordItem(ModTiers.ULTIMATE_TEHONITE,8, -2.2f,
                    new Item.Properties()));
    public static final RegistryObject<Item> ULTIMATE_TEHONITE_PICKAXE = ITEMS.register("ultimate_tehonite_pickaxe",
            () -> new PickaxeItem(ModTiers.ULTIMATE_TEHONITE,5, -2.8f,
                    new Item.Properties()));
    public static final RegistryObject<Item> ULTIMATE_TEHONITE_AXE = ITEMS.register("ultimate_tehonite_axe",
            () -> new AxeItem(ModTiers.ULTIMATE_TEHONITE,10, -2.8f,
                    new Item.Properties()));
    public static final RegistryObject<Item> ULTIMATE_TEHONITE_SHOVEL = ITEMS.register("ultimate_tehonite_shovel",
            () -> new ShovelItem(ModTiers.ULTIMATE_TEHONITE,5.5F, -2.8f,
                    new Item.Properties()));
    public static final RegistryObject<Item> ULTIMATE_TEHONITE_HOE = ITEMS.register("ultimate_tehonite_hoe",
            () -> new HoeItem(ModTiers.ULTIMATE_TEHONITE,0, 1f,
                    new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
