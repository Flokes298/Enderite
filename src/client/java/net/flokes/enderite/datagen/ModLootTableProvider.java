package net.flokes.enderite.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.flokes.enderite.block.ModBlocks;
import net.flokes.enderite.item.ModItems;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.ENDERITE_BLOCK);
        addDrop(ModBlocks.ENDERITE_ORE, oreDrops(ModBlocks.ENDERITE_ORE, ModItems.ENDERITE_SHARD));
    }
}
