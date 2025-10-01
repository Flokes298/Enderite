package net.flokes.enderite.item.equipment.trim;

import net.flokes.enderite.Enderite;
import net.minecraft.item.equipment.trim.ArmorTrimAssets;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModTrimMaterials {
    public static final RegistryKey<ArmorTrimMaterial> ENDERITE = RegistryKey.of(
            RegistryKeys.TRIM_MATERIAL, Identifier.of(Enderite.MOD_ID, "enderite")
    );

    public static void bootstrap(Registerable<ArmorTrimMaterial> registry) {
        ArmorTrimAssets assets =  ArmorTrimAssets.of("enderite");
        register(registry, ENDERITE, Style.EMPTY.withColor(TextColor.parse("#dd61dd").getOrThrow()), assets);
    }


    private static void register(
            Registerable<ArmorTrimMaterial> registry,
            RegistryKey<ArmorTrimMaterial> key,
            Style style,
            ArmorTrimAssets assets
    ) {
        Text text = Text.translatable(Util.createTranslationKey("trim_material", key.getValue())).fillStyle(style);
        registry.register(key, new ArmorTrimMaterial(assets, text));
    }
}
