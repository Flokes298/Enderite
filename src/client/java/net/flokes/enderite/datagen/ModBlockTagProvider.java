package net.flokes.enderite.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.flokes.enderite.block.ModBlocks;
import net.flokes.enderite.util.ModTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // --- Vanilla tags ---

        valueLookupBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.ENDERITE_BLOCK);

        valueLookupBuilder(BlockTags.DRAGON_IMMUNE)
                .add(ModBlocks.ENDERITE_ORE)
                .add(ModBlocks.ENDERITE_BLOCK);

        valueLookupBuilder(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.ENDERITE_BLOCK)
                .add(ModBlocks.ENDERITE_ORE);

        valueLookupBuilder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL);
        valueLookupBuilder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL);
        valueLookupBuilder(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL);
        // TODO: INCORRECT FOR COPPER TOOLS
        valueLookupBuilder(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL);
        valueLookupBuilder(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .addTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL);

        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.ENDERITE_BLOCK)
                .add(ModBlocks.ENDERITE_ORE);

        // --- Fabric conventional tags ---

        valueLookupBuilder(ConventionalBlockTags.ORES)
                .add(ModBlocks.ENDERITE_ORE);
    }
}
