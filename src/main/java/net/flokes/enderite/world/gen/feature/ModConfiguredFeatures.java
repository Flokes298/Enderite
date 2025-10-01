package net.flokes.enderite.world.gen.feature;

import net.fabricmc.api.ModInitializer;
import net.flokes.enderite.Enderite;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ModConfiguredFeatures implements ModInitializer {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ENDERITE_CRYSTAL = registerKey("enderite_crystal");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOATING_ENDERITE_CRYSTALS = registerKey("floating_enderite_crystals");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, ENDERITE_CRYSTAL, ModFeatures.ENDERITE_CRYSTAL);
        register(context, FLOATING_ENDERITE_CRYSTALS, ModFeatures.FLOATING_ENDERITE_CRYSTALS);
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Enderite.MOD_ID, name));
    }

    public static void register(
            Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> key, Feature<DefaultFeatureConfig> feature
    ) {
        register(registerable, key, feature, FeatureConfig.DEFAULT);
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register (
            Registerable<ConfiguredFeature<?, ?>> context,
            RegistryKey<ConfiguredFeature<?, ?>> key,
            F feature, FC configuration
    ) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    @Override
    public void onInitialize() {

    }
}
