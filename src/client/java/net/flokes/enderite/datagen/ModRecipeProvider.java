package net.flokes.enderite.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.flokes.enderite.block.ModBlocks;
import net.flokes.enderite.item.ModItems;
import net.flokes.enderite.util.ModTags;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {

            // Helper method for Enderite upgrade recipes
            public void offerEnderiteUpgradeRecipe(Item input, RecipeCategory category, Item result) {
                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(ModItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.ofItem(input),
                                this.ingredientFromTag(ModTags.Items.ENDERITE_TOOL_MATERIALS),
                                category,
                                result
                        )
                        .criterion(hasItem(ModItems.ENDERITE_INGOT), this.conditionsFromTag(ModTags.Items.ENDERITE_TOOL_MATERIALS))
                        .offerTo(this.exporter, getItemPath(result) + "_smithing");
            }

            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                // Enderite Block Compression
                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.ENDERITE_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDERITE_BLOCK.asItem());

                // Raw Enderite Recipe
                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.ENDERITE_SHARD, RecipeCategory.MISC, ModItems.RAW_ENDERITE);

                // Enderite Ingot Smelting
                offerSmelting(List.of(ModItems.RAW_ENDERITE), RecipeCategory.MISC, ModItems.ENDERITE_INGOT, 7.0f, 200, "enderite");
                offerBlasting(List.of(ModItems.RAW_ENDERITE), RecipeCategory.MISC, ModItems.ENDERITE_INGOT, 7.0f, 200, "enderite");

                // Enderite Upgrade Template Duplication
                offerSmithingTemplateCopyingRecipe(ModItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE, Items.CRYING_OBSIDIAN);

                // Enderite Smithing Upgrades
                offerEnderiteUpgradeRecipe(Items.NETHERITE_SWORD, RecipeCategory.COMBAT, ModItems.ENDERITE_SWORD);
                offerEnderiteUpgradeRecipe(Items.NETHERITE_PICKAXE, RecipeCategory.TOOLS, ModItems.ENDERITE_PICKAXE);
                offerEnderiteUpgradeRecipe(Items.NETHERITE_SHOVEL, RecipeCategory.TOOLS, ModItems.ENDERITE_SHOVEL);
                offerEnderiteUpgradeRecipe(Items.NETHERITE_AXE, RecipeCategory.TOOLS, ModItems.ENDERITE_AXE);
                offerEnderiteUpgradeRecipe(Items.NETHERITE_HOE, RecipeCategory.TOOLS, ModItems.ENDERITE_HOE);
                offerEnderiteUpgradeRecipe(Items.NETHERITE_HELMET, RecipeCategory.COMBAT, ModItems.ENDERITE_HELMET);
                offerEnderiteUpgradeRecipe(Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, ModItems.ENDERITE_CHESTPLATE);
                offerEnderiteUpgradeRecipe(Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, ModItems.ENDERITE_LEGGINGS);
                offerEnderiteUpgradeRecipe(Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, ModItems.ENDERITE_BOOTS);
            }
        };
    }

    @Override
    public String getName() {
        return "ModRecipeProvider";
    }
}
