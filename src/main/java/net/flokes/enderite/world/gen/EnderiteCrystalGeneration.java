package net.flokes.enderite.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.flokes.enderite.world.gen.feature.ModPlacedFeatures;
import net.minecraft.world.gen.GenerationStep;

public class EnderiteCrystalGeneration {
    public static void generate() {
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.ENDERITE_CRYSTAL
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.SURFACE_STRUCTURES,
                ModPlacedFeatures.FLOATING_ENDERITE_CRYSTALS
        );
    }
}
