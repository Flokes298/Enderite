package net.flokes.enderite.item;

import net.flokes.enderite.Enderite;
import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.List;

public class EnderiteUpgradeSmithingTemplateItem extends SmithingTemplateItem {

    // GUI Text
    private static final Formatting DESCRIPTION_FORMATTING = Formatting.BLUE;

    private static final Text ENDERITE_UPGRADE_APPLIES_TO_TEXT = Text.translatable(
                    Util.createTranslationKey("item", Identifier.of(Enderite.MOD_ID, "smithing_template.enderite_upgrade.applies_to"))
            )
            .formatted(DESCRIPTION_FORMATTING);
    private static final Text ENDERITE_UPGRADE_INGREDIENTS_TEXT = Text.translatable(
                    Util.createTranslationKey("item", Identifier.of(Enderite.MOD_ID, "smithing_template.enderite_upgrade.ingredients"))
            )
            .formatted(DESCRIPTION_FORMATTING);
    private static final Text ENDERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(
            Util.createTranslationKey("item", Identifier.of(Enderite.MOD_ID, "smithing_template.enderite_upgrade.base_slot_description"))
    );
    private static final Text ENDERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(
            Util.createTranslationKey("item", Identifier.of(Enderite.MOD_ID, "smithing_template.enderite_upgrade.additions_slot_description"))
    );



    public EnderiteUpgradeSmithingTemplateItem(Item.Settings settings) {
        super(ENDERITE_UPGRADE_APPLIES_TO_TEXT,
                ENDERITE_UPGRADE_INGREDIENTS_TEXT,
                ENDERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
                ENDERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                getUpgradeEmptyBaseSlotTextures(),
                getUpgradeEmptyAdditionsSlotTextures(),
                settings
        );
    }



    // Empty slot textures
    private static List<Identifier> getUpgradeEmptyBaseSlotTextures() {
        return List.of(
                Identifier.ofVanilla("container/slot/helmet"),
                Identifier.ofVanilla("container/slot/sword"),
                Identifier.ofVanilla("container/slot/chestplate"),
                Identifier.ofVanilla("container/slot/pickaxe"),
                Identifier.ofVanilla("container/slot/leggings"),
                Identifier.ofVanilla("container/slot/axe"),
                Identifier.ofVanilla("container/slot/boots"),
                Identifier.ofVanilla("container/slot/hoe"),
                Identifier.ofVanilla("container/slot/shovel")
        );
    }
    private static List<Identifier> getUpgradeEmptyAdditionsSlotTextures() {
        return List.of(Identifier.ofVanilla("container/slot/ingot"));
    }

}
