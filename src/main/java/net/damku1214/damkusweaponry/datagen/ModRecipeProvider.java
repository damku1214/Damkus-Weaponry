package net.damku1214.damkusweaponry.datagen;

import net.damku1214.damkusweaponry.block.ModBlocks;
import net.damku1214.damkusweaponry.item.ModItems;
import net.damku1214.damkusweaponry.potion.ModPotions;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JADE.get())
                .define('E', Items.EMERALD)
                .define('S', Items.NETHER_STAR)
                .pattern(" E ")
                .pattern("ESE")
                .pattern(" E ")
                .unlockedBy("has_nether_star", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.NETHER_STAR).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JADE_HANDLE.get())
                .define('J', ModItems.JADE.get())
                .pattern("J")
                .pattern("J")
                .unlockedBy("has_jade", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.JADE.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JADE_VINE_SICKLE.get())
                .define('H', ModItems.JADE_HANDLE.get())
                .define('I', Items.IRON_INGOT)
                .pattern("IIH")
                .pattern(" H ")
                .pattern("H  ")
                .unlockedBy("has_jade_handle", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.JADE_HANDLE.get()).build()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TEHONITE_UPPGRADE_SMITHING_TEMPLATE.get())
                .define('H', Items.HONEYCOMB)
                .define('T', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .pattern(" H ")
                .pattern("HTH")
                .pattern(" H ")
                .unlockedBy("has_netherite_upgrade_smithing_template", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE).build()))
                .save(consumer);

        oreSmelting(consumer, List.of(ModItems.RAW_TEHONITE.get()), RecipeCategory.MISC,
                ModItems.TEHONITE.get(), 0.7f, 200, "tehonite");

        oreBlasting(consumer, List.of(ModItems.RAW_TEHONITE.get()), RecipeCategory.MISC,
                ModItems.TEHONITE.get(), 0.7f, 100, "tehonite");

        oreSmelting(consumer, List.of(ModBlocks.TEHONITE_ORE.get()), RecipeCategory.MISC,
                ModItems.TEHONITE.get(), 0.7f, 200, "tehonite");

        oreBlasting(consumer, List.of(ModBlocks.TEHONITE_ORE.get()), RecipeCategory.MISC,
                ModItems.TEHONITE.get(), 0.7f, 100, "tehonite");

        oreSmelting(consumer, List.of(ModBlocks.DEEPSLATE_TEHONITE_ORE.get()), RecipeCategory.MISC,
                ModItems.TEHONITE.get(), 0.7f, 200, "tehonite");

        oreBlasting(consumer, List.of(ModBlocks.DEEPSLATE_TEHONITE_ORE.get()), RecipeCategory.MISC,
                ModItems.TEHONITE.get(), 0.7f, 100, "tehonite");

        oreSmelting(consumer, List.of(ModItems.TEHONITE.get()), RecipeCategory.MISC,
                ModItems.MOLTEN_TEHONITE.get(), 0.7f, 400, "tehonite");

        oreBlasting(consumer, List.of(ModItems.TEHONITE.get()), RecipeCategory.MISC,
                ModItems.MOLTEN_TEHONITE.get(), 0.7f, 100, "tehonite");

        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, ModItems.TEHONITE.get(), RecipeCategory.MISC,
                ModBlocks.TEHONITE_BLOCK.get());
    }
}
