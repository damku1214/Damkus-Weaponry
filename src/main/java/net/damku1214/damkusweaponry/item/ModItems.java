package net.damku1214.damkusweaponry.item;

import net.damku1214.damkusweaponry.DamkusWeaponry;
import net.damku1214.damkusweaponry.item.custom.JadeVineSickleItem;
import net.damku1214.damkusweaponry.item.custom.TehoniteBowItem;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DamkusWeaponry.MOD_ID);

    private static final ResourceLocation EMPTY_SLOT_BOW = new ResourceLocation("damkusweaponry", "item/empty_slot_bow");
    private static final ResourceLocation EMPTY_SLOT_MOLTEN_TEHONITE = new ResourceLocation("damkusweaponry", "item/empty_slot_molten_tehonite");
    private static final Component TEHONITE_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.tehonite_upgrade.applies_to"))).withStyle(ChatFormatting.BLUE);
    private static final Component TEHONITE_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.tehonite_upgrade.ingredients"))).withStyle(ChatFormatting.BLUE);
    private static final Component TEHONITE_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation("tehonite_upgrade"))).withStyle(ChatFormatting.GRAY);
    private static final Component TEHONITE_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.tehonite_upgrade.base_slot_description")));
    private static final Component TEHONITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.tehonite_upgrade.additions_slot_description")));


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
    public static final RegistryObject<Item> TEHONITE_BOW= ITEMS.register("tehonite_bow",
            () -> new TehoniteBowItem(new Item.Properties().durability(650)));
    public static final RegistryObject<Item> TEHONITE_UPPGRADE_SMITHING_TEMPLATE = ITEMS.register("tehonite_upgrade_smithing_template",
            () -> new SmithingTemplateItem(TEHONITE_UPGRADE_APPLIES_TO, TEHONITE_UPGRADE_INGREDIENTS, TEHONITE_UPGRADE, TEHONITE_UPGRADE_BASE_SLOT_DESCRIPTION,
                    TEHONITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, List.of(EMPTY_SLOT_BOW), List.of(EMPTY_SLOT_MOLTEN_TEHONITE)));
    public static final RegistryObject<Item> STICKY_FEET_POTION_SAMPLE = ITEMS.register("sticky_feet_potion_sample",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
