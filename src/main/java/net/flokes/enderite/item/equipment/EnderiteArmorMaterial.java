package net.flokes.enderite.item.equipment;

import net.flokes.enderite.Enderite;
import net.flokes.enderite.util.ModTags;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.Map;

public class EnderiteArmorMaterial {
    public static final int BASE_DURABILITY = 42;
    public static final RegistryKey<EquipmentAsset> ENDERITE_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(Enderite.MOD_ID, "enderite"));

    public static final ArmorMaterial INSTANCE = new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    EquipmentType.HELMET, 3,
                    EquipmentType.CHESTPLATE, 8,
                    EquipmentType.LEGGINGS, 6,
                    EquipmentType.BOOTS, 3,
                    EquipmentType.BODY, 11
            ),
            20,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            5.0f,
            0.2f,
            ModTags.Items.REPAIRS_ENDERITE_ARMOR,
            ENDERITE_ARMOR_MATERIAL_KEY
    );
}
