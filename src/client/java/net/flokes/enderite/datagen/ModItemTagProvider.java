package net.flokes.enderite.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.flokes.enderite.block.ModBlocks;
import net.flokes.enderite.item.ModItems;
import net.flokes.enderite.util.ModTags;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // --- Vanilla tags ---

        valueLookupBuilder(ModTags.Items.ENDERITE_TOOL_MATERIALS)
                .add(ModItems.ENDERITE_INGOT);

        valueLookupBuilder(ModTags.Items.REPAIRS_ENDERITE_ARMOR)
                .add(ModItems.ENDERITE_INGOT);

        valueLookupBuilder(ItemTags.SWORDS)
                .add(ModItems.ENDERITE_SWORD);
        valueLookupBuilder(ItemTags.PICKAXES)
                .add(ModItems.ENDERITE_PICKAXE);
        valueLookupBuilder(ItemTags.SHOVELS)
                .add(ModItems.ENDERITE_SHOVEL);
        valueLookupBuilder(ItemTags.AXES)
                .add(ModItems.ENDERITE_AXE);
        valueLookupBuilder(ItemTags.HOES)
                .add(ModItems.ENDERITE_HOE);

        valueLookupBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItems.ENDERITE_HELMET);
        valueLookupBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItems.ENDERITE_CHESTPLATE);
        valueLookupBuilder(ItemTags.LEG_ARMOR)
                .add(ModItems.ENDERITE_LEGGINGS);
        valueLookupBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItems.ENDERITE_BOOTS);

        valueLookupBuilder(ItemTags.GAZE_DISGUISE_EQUIPMENT)
                .add(ModItems.ENDERITE_HELMET);

        valueLookupBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.ENDERITE_INGOT);

        // --- Fabric conventional tags ---

        valueLookupBuilder(ConventionalItemTags.GEMS)
                .add(ModItems.ENDERITE_SHARD);

        valueLookupBuilder(ConventionalItemTags.INGOTS)
                .add(ModItems.ENDERITE_INGOT);

        valueLookupBuilder(ConventionalItemTags.ORES)
                .add(ModBlocks.ENDERITE_ORE.asItem());

        valueLookupBuilder(ConventionalItemTags.RAW_MATERIALS)
                .add(ModItems.RAW_ENDERITE);
    }
}
