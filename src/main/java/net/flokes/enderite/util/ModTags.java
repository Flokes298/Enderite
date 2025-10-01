package net.flokes.enderite.util;

import net.flokes.enderite.Enderite;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_NETHERITE_TOOL = createTag("needs_netherite_tool");
        public static final TagKey<Block> NEEDS_ENDERITE_TOOL = createTag("needs_enderite_tool");
        public static final TagKey<Block> INCORRECT_FOR_ENDERITE_TOOL = createTag("incorrect_for_enderite_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Enderite.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> ENDERITE_TOOL_MATERIALS = createTag("enderite_tool_material");
        public static final TagKey<Item> REPAIRS_ENDERITE_ARMOR = createTag("repairs_enderite_armor");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Enderite.MOD_ID, name));
        }
    }
}
