package net.flokes.enderite.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
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

        valueLookupBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.ENDERITE_HELMET)
                .add(ModItems.ENDERITE_CHESTPLATE)
                .add(ModItems.ENDERITE_LEGGINGS)
                .add(ModItems.ENDERITE_BOOTS);

        valueLookupBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.ENDERITE_INGOT);
    }
}
