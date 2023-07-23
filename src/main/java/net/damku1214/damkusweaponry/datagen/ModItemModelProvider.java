package net.damku1214.damkusweaponry.datagen;

import net.damku1214.damkusweaponry.DamkusWeaponry;
import net.damku1214.damkusweaponry.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DamkusWeaponry.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.DAMKU);
        handheldItem(ModItems.JADE_VINE_SICKLE);
        simpleItem(ModItems.JADE);
        simpleItem(ModItems.JADE_HANDLE);
        simpleItem(ModItems.TEHONITE_UPPGRADE_SMITHING_TEMPLATE);
        simpleItem(ModItems.TEHONITE);
        simpleItem(ModItems.RAW_TEHONITE);
        simpleItem(ModItems.MOLTEN_TEHONITE);
        simpleItem(ModItems.STICKY_FEET_POTION_SAMPLE);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(DamkusWeaponry.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(DamkusWeaponry.MOD_ID, "item/" + item.getId().getPath()));
    }
}
