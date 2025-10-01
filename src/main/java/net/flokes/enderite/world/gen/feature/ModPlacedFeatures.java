package net.flokes.enderite.world.gen.feature;

import net.fabricmc.api.ModInitializer;
import net.flokes.enderite.Enderite;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures implements ModInitializer {

    public static final RegistryKey<PlacedFeature> ENDERITE_CRYSTAL = registerKey("enderite_crystal");
    public static final RegistryKey<PlacedFeature> FLOATING_ENDERITE_CRYSTALS = registerKey("floating_enderite_crystals");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, ENDERITE_CRYSTAL, configuredFeatures.getOrThrow(ModConfiguredFeatures.ENDERITE_CRYSTAL),
                RarityFilterPlacementModifier.of(400),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
        register(context, FLOATING_ENDERITE_CRYSTALS, configuredFeatures.getOrThrow(ModConfiguredFeatures.FLOATING_ENDERITE_CRYSTALS),
                RarityFilterPlacementModifier.of(15000),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.fixed(80), YOffset.fixed(110)),
                BiomePlacementModifier.of()
        );
    }


    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Enderite.MOD_ID, name));
    }

    private static void register(
            Registerable<PlacedFeature> context,
            RegistryKey<PlacedFeature> key,
            RegistryEntry<ConfiguredFeature<?, ?>> configuration,
            List<PlacementModifier> modifiers
    ) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
            Registerable<PlacedFeature> context,
            RegistryKey<PlacedFeature> key,
            RegistryEntry<ConfiguredFeature<?, ?>> configuration,
            PlacementModifier... modifiers
    ) {
        register(context, key, configuration, List.of(modifiers));
    }

    @Override
    public void onInitialize() {

    }
}
