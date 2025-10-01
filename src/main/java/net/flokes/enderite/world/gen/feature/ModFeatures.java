package net.flokes.enderite.world.gen.feature;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ModFeatures implements ModInitializer {

    public static final Feature<DefaultFeatureConfig> ENDERITE_CRYSTAL = register("enderite_crystal", new EnderiteCrystalFeature(DefaultFeatureConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> FLOATING_ENDERITE_CRYSTALS = register("floating_enderite_crystals", new FloatingEnderiteCrystalsFeature(DefaultFeatureConfig.CODEC));

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registries.FEATURE, name, feature);
    }

    @Override
    public void onInitialize() {

    }
}
