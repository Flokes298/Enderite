package net.flokes.enderite.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.flokes.enderite.block.ModBlocks;
import net.flokes.enderite.item.ModItems;
import net.flokes.enderite.item.equipment.EnderiteArmorMaterial;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENDERITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENDERITE_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ENDERITE_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_ENDERITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);

        itemModelGenerator.register(ModItems.ENDERITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDERITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDERITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDERITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDERITE_HOE, Models.HANDHELD);

        itemModelGenerator.registerArmor(ModItems.ENDERITE_HELMET,
                EnderiteArmorMaterial.ENDERITE_ARMOR_MATERIAL_KEY,
                ItemModelGenerator.HELMET_TRIM_ID_PREFIX,
                false);
        itemModelGenerator.registerArmor(ModItems.ENDERITE_CHESTPLATE,
                EnderiteArmorMaterial.ENDERITE_ARMOR_MATERIAL_KEY,
                ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX,
                false);
        itemModelGenerator.registerArmor(ModItems.ENDERITE_LEGGINGS,
                EnderiteArmorMaterial.ENDERITE_ARMOR_MATERIAL_KEY,
                ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX,
                false);
        itemModelGenerator.registerArmor(ModItems.ENDERITE_BOOTS,
                EnderiteArmorMaterial.ENDERITE_ARMOR_MATERIAL_KEY,
                ItemModelGenerator.BOOTS_TRIM_ID_PREFIX,
                false);
    }
}
