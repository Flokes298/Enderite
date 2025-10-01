package net.flokes.enderite.block;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.flokes.enderite.Enderite;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.function.Function;

public class ModBlocks implements ModInitializer {

    public static final Block ENDERITE_BLOCK = register(
            "enderite_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .requiresTool()
                    .strength(50.0F, 1200.0F)
                    .sounds(BlockSoundGroup.METAL),
            true
    );
    public static final Block ENDERITE_ORE = register(
            "enderite_ore",
            settings -> new ExperienceDroppingBlock(UniformIntProvider.create(14, 21), settings),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true
    );



    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        // Create registry key for block
        RegistryKey<Block> blockKey = keyOfBlock(name);
        // Create block instance
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        if (shouldRegisterItem) {
            // Create registry key for item
            RegistryKey<Item> itemKey = keyOfItem(name);
            // Create item instance
            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            // Register item
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        // Register block
        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Enderite.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Enderite.MOD_ID, name));
    }


    @Override
    public void onInitialize() {
        Enderite.LOGGER.info("Registering Enderite Mod Blocks.");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(itemGroup -> {
            itemGroup.add(ModBlocks.ENDERITE_BLOCK);
            itemGroup.add(ModBlocks.ENDERITE_ORE);
        });
    }
}
