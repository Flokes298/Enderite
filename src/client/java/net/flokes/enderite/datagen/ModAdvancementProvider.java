package net.flokes.enderite.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.flokes.enderite.Enderite;
import net.flokes.enderite.block.ModBlocks;
import net.flokes.enderite.item.ModItems;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry enderiteRoot = Advancement.Builder.create()
                .display(
                        ModBlocks.ENDERITE_ORE,
                        Text.translatable(Util.createTranslationKey("advancements", Identifier.of(Enderite.MOD_ID, "root.title"))),
                        Text.translatable(Util.createTranslationKey("advancements", Identifier.of(Enderite.MOD_ID, "root.description"))),
                        Identifier.ofVanilla("gui/advancements/backgrounds/end"),
                        AdvancementFrame.TASK,
                        false,
                        false,
                        false
                )
                .criterion("got_enderite_shard", InventoryChangedCriterion.Conditions.items(ModItems.ENDERITE_SHARD))
                .build(consumer, Enderite.MOD_ID + "/root");

        AdvancementEntry getEnderiteShard = Advancement.Builder.create()
                .parent(enderiteRoot)
                .display(
                        ModItems.ENDERITE_SHARD,
                        Text.translatable(Util.createTranslationKey("advancements", Identifier.of(Enderite.MOD_ID, "get_enderite_shard.title"))),
                        Text.translatable(Util.createTranslationKey("advancements", Identifier.of(Enderite.MOD_ID, "get_enderite_shard.description"))),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("got_enderite_shard", InventoryChangedCriterion.Conditions.items(ModItems.ENDERITE_SHARD))
                .build(consumer, Enderite.MOD_ID + "/get_enderite_shard");

        AdvancementEntry getEnderiteIngot = Advancement.Builder.create()
                .parent(getEnderiteShard)
                .display(
                        ModItems.ENDERITE_INGOT,
                        Text.translatable(Util.createTranslationKey("advancements", Identifier.of(Enderite.MOD_ID, "get_enderite_ingot.title"))),
                        Text.translatable(Util.createTranslationKey("advancements", Identifier.of(Enderite.MOD_ID, "get_enderite_ingot.description"))),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("got_enderite_ingot", InventoryChangedCriterion.Conditions.items(ModItems.ENDERITE_INGOT))
                .build(consumer, Enderite.MOD_ID + "/get_enderite_ingot");

        AdvancementEntry enderiteArmor = Advancement.Builder.create()
                .parent(getEnderiteIngot)
                .display(
                        ModItems.ENDERITE_CHESTPLATE,
                        Text.translatable(Util.createTranslationKey("advancements", Identifier.of(Enderite.MOD_ID, "enderite_armor.title"))),
                        Text.translatable(Util.createTranslationKey("advancements", Identifier.of(Enderite.MOD_ID, "enderite_armor.description"))),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .rewards(AdvancementRewards.Builder.experience(200))
                .criterion("full_enderite_armor", InventoryChangedCriterion.Conditions.items(
                        ModItems.ENDERITE_HELMET,
                        ModItems.ENDERITE_CHESTPLATE,
                        ModItems.ENDERITE_LEGGINGS,
                        ModItems.ENDERITE_BOOTS))
                .build(consumer, Enderite.MOD_ID + "/enderite_armor");

        AdvancementEntry enderiteTools = Advancement.Builder.create()
                .parent(getEnderiteIngot)
                .display(
                        ModItems.ENDERITE_PICKAXE,
                        Text.translatable(Util.createTranslationKey("advancements", Identifier.of(Enderite.MOD_ID, "enderite_tools.title"))),
                        Text.translatable(Util.createTranslationKey("advancements", Identifier.of(Enderite.MOD_ID, "enderite_tools.description"))),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .rewards(AdvancementRewards.Builder.experience(200))
                .criterion("all_enderite_tools", InventoryChangedCriterion.Conditions.items(
                        ModItems.ENDERITE_SWORD,
                        ModItems.ENDERITE_PICKAXE,
                        ModItems.ENDERITE_AXE,
                        ModItems.ENDERITE_SHOVEL,
                        ModItems.ENDERITE_HOE
                ))
                .build(consumer, Enderite.MOD_ID + "/enderite_tools");
    }
}
