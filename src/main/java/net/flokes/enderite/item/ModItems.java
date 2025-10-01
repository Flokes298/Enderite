package net.flokes.enderite.item;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.flokes.enderite.Enderite;
import net.flokes.enderite.block.ModBlocks;
import net.flokes.enderite.item.equipment.EnderiteArmorMaterial;
import net.flokes.enderite.item.equipment.trim.ModTrimMaterials;
import net.flokes.enderite.util.ModTags;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.function.Function;

public class ModItems implements ModInitializer {
    // Enderite item group:
    public static final RegistryKey<ItemGroup> ENDERITE_ITEM_GROUP_KEY =
            RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Enderite.MOD_ID, "item_group"));
    public static final ItemGroup ENDERITE_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.ENDERITE_SHARD))
            .displayName(Text.translatable("itemGroup.enderite"))
            .build();

    // Enderite tool material
    public static final ToolMaterial ENDERITE_TOOL_MATERIAL = new ToolMaterial(
            ModTags.Blocks.INCORRECT_FOR_ENDERITE_TOOL,
            2811,
            17.0F,
            5.0F,
            18,
            ModTags.Items.ENDERITE_TOOL_MATERIALS
    );




    // region Registered mod items
    public static final Item ENDERITE_SHARD = register(
            "enderite_shard",
            Item::new,
            new Item.Settings()
    );
    public static final Item RAW_ENDERITE = register(
            "raw_enderite",
            Item::new,
            new Item.Settings()
    );
    public static final Item ENDERITE_INGOT = register(
            "enderite_ingot",
            Item::new,
            new Item.Settings().trimMaterial(ModTrimMaterials.ENDERITE)
    );
    public static final Item ENDERITE_UPGRADE_SMITHING_TEMPLATE = register(
            "enderite_upgrade_smithing_template",
            EnderiteUpgradeSmithingTemplateItem::new,
            new Item.Settings().rarity(Rarity.UNCOMMON)
    );

    // Enderite Tools
    public static final Item ENDERITE_SWORD = register(
            "enderite_sword",
            Item::new,
            new Item.Settings().sword(ModItems.ENDERITE_TOOL_MATERIAL, 3f, -2.4f).fireproof()
    );
    public static final Item ENDERITE_PICKAXE = register(
            "enderite_pickaxe",
            Item::new,
            new Item.Settings().pickaxe(ModItems.ENDERITE_TOOL_MATERIAL, 1f, -2.8f).fireproof()
    );
    public static final Item ENDERITE_SHOVEL = register(
            "enderite_shovel",
            settings -> new ShovelItem(ModItems.ENDERITE_TOOL_MATERIAL, 1.5f, -3.0f, settings),
            new Item.Settings().fireproof()
    );
    public static final Item ENDERITE_AXE = register(
            "enderite_axe",
            settings -> new AxeItem(ModItems.ENDERITE_TOOL_MATERIAL, 5f, -3f, settings),
            new Item.Settings().fireproof()
    );
    public static final Item ENDERITE_HOE = register(
            "enderite_hoe",
            settings -> new HoeItem(ModItems.ENDERITE_TOOL_MATERIAL, 0f, -3f, settings),
            new Item.Settings().fireproof()
    );

    // Enderite Armor
    public static final Item ENDERITE_HELMET = register(
            "enderite_helmet",
            Item::new,
            new Item.Settings().armor(EnderiteArmorMaterial.INSTANCE, EquipmentType.HELMET).fireproof()
    );
    public static final Item ENDERITE_CHESTPLATE = register(
            "enderite_chestplate",
            Item::new,
            new Item.Settings().armor(EnderiteArmorMaterial.INSTANCE, EquipmentType.CHESTPLATE).fireproof()
    );
    public static final Item ENDERITE_LEGGINGS = register(
            "enderite_leggings",
            Item::new,
            new Item.Settings().armor(EnderiteArmorMaterial.INSTANCE, EquipmentType.LEGGINGS).fireproof()
    );
    public static final Item ENDERITE_BOOTS = register(
            "enderite_boots",
            Item::new,
            new Item.Settings().armor(EnderiteArmorMaterial.INSTANCE, EquipmentType.BOOTS).fireproof()
    );
    // endregion




    private static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create item key
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Enderite.MOD_ID, name));
        // Create item instance
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        // Register item
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    @Override
    public void onInitialize() {
        Enderite.LOGGER.info("Registering Enderite Mod Items.");

        // Register Enderite item group
        Registry.register(Registries.ITEM_GROUP, ENDERITE_ITEM_GROUP_KEY, ENDERITE_ITEM_GROUP);

        // Add items to ENDERITE item group
        ItemGroupEvents.modifyEntriesEvent(ENDERITE_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(ModItems.ENDERITE_SHARD);
            itemGroup.add(ModItems.RAW_ENDERITE);
            itemGroup.add(ModItems.ENDERITE_INGOT);
            itemGroup.add(ModBlocks.ENDERITE_BLOCK);
            itemGroup.add(ModBlocks.ENDERITE_ORE);
            itemGroup.add(ModItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE);
            itemGroup.add(ModItems.ENDERITE_SWORD);
            itemGroup.add(ModItems.ENDERITE_PICKAXE);
            itemGroup.add(ModItems.ENDERITE_SHOVEL);
            itemGroup.add(ModItems.ENDERITE_AXE);
            itemGroup.add(ModItems.ENDERITE_HOE);
            itemGroup.add(ModItems.ENDERITE_HELMET);
            itemGroup.add(ModItems.ENDERITE_CHESTPLATE);
            itemGroup.add(ModItems.ENDERITE_LEGGINGS);
            itemGroup.add(ModItems.ENDERITE_BOOTS);
        });

        // Add items to COMBAT item group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(itemGroup -> {
            itemGroup.add(ModItems.ENDERITE_SWORD);
            itemGroup.add(ModItems.ENDERITE_AXE);
            itemGroup.add(ModItems.ENDERITE_HELMET);
            itemGroup.add(ModItems.ENDERITE_CHESTPLATE);
            itemGroup.add(ModItems.ENDERITE_LEGGINGS);
            itemGroup.add(ModItems.ENDERITE_BOOTS);
        });

        // Add items to TOOLS item group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(itemGroup -> {
            itemGroup.add(ModItems.ENDERITE_PICKAXE);
            itemGroup.add(ModItems.ENDERITE_SHOVEL);
            itemGroup.add(ModItems.ENDERITE_AXE);
            itemGroup.add(ModItems.ENDERITE_HOE);
        });

        // Add items to INGREDIENTS item group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(itemGroup -> {
            itemGroup.add(ModItems.ENDERITE_SHARD);
            itemGroup.add(ModItems.RAW_ENDERITE);
            itemGroup.add(ModItems.ENDERITE_INGOT);
            itemGroup.add(ModItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE);
        });
    }
}
