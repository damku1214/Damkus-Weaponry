package net.damku1214.damkusweaponry.datagen;

import net.damku1214.damkusweaponry.block.ModBlocks;
import net.damku1214.damkusweaponry.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.TEHONITE_BLOCK.get());

        add(ModBlocks.TEHONITE_ORE.get(),
                (block -> createOreDrop(ModBlocks.TEHONITE_ORE.get(), ModItems.RAW_TEHONITE.get())));
        add(ModBlocks.DEEPSLATE_TEHONITE_ORE.get(),
                (block -> createOreDrop(ModBlocks.DEEPSLATE_TEHONITE_ORE.get(), ModItems.RAW_TEHONITE.get())));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
