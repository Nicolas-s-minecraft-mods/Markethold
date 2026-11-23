package io.github.nicolasgoyon.markethold.config;

import io.github.nicolasgoyon.markethold.enchantment.BlockedEnchantments;
import io.github.nicolasgoyon.markethold.enchantment.EnchantmentFilter;
import java.util.List;
import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Server configuration of Markethold: file {@code markethold-server.toml} in the {@code serverconfig} folder of each
 * world.
 */
public final class MarketholdConfig {
    static final String KEY_PREFIX = "markethold.configuration.";

    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.ConfigValue<List<? extends String>> BLOCKED_ENCHANTMENTS;
    public static final ModConfigSpec.BooleanValue BLOCKED_IN_TRADES;
    public static final ModConfigSpec.BooleanValue BLOCKED_CLEAN_EXISTING_OFFERS;
    public static final ModConfigSpec.IntValue BLOCKED_REROLL_ATTEMPTS;
    public static final ModConfigSpec.BooleanValue BLOCKED_IN_FISHING;
    public static final ModConfigSpec.BooleanValue BLOCKED_IN_CHEST_LOOT;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.comment("Blocked enchantments: enchantments that villagers do not sell and that fishing does not give.")
                .translation(KEY_PREFIX + "blockedEnchantments")
                .push("blockedEnchantments");
        BLOCKED_ENCHANTMENTS = builder
                .comment("Enchantment ids (\"minecraft:mending\") or enchantment tags (\"#minecraft:treasure\").")
                .translation(KEY_PREFIX + "enchantments")
                .defineListAllowEmpty("enchantments", BlockedEnchantments.DEFAULT, () -> "minecraft:mending",
                        EnchantmentFilter::isValidEntry);
        BLOCKED_IN_TRADES = builder
                .comment("Villagers and wandering traders do not make new offers with a blocked enchantment.")
                .translation(KEY_PREFIX + "inTrades")
                .define("inTrades", true);
        BLOCKED_CLEAN_EXISTING_OFFERS = builder
                .comment("When a villager or a wandering trader loads, each blocked enchantment in its offers changes",
                        "to a random enchantment of the tag #minecraft:tradeable. Needs inTrades.")
                .translation(KEY_PREFIX + "cleanExistingOffers")
                .define("cleanExistingOffers", true);
        BLOCKED_REROLL_ATTEMPTS = builder
                .comment("Number of new attempts for an offer with a blocked enchantment. When all the attempts fail,",
                        "the villager takes a different offer.")
                .translation(KEY_PREFIX + "rerollAttempts")
                .defineInRange("rerollAttempts", 10, 0, 100);
        BLOCKED_IN_FISHING = builder
                .comment("Each blocked enchantment in the loot of fishing changes to a random enchantment of the tag",
                        "#minecraft:on_random_loot.")
                .translation(KEY_PREFIX + "inFishing")
                .define("inFishing", true);
        BLOCKED_IN_CHEST_LOOT = builder
                .comment("Each blocked enchantment in the loot of chests changes to a random enchantment of the tag",
                        "#minecraft:on_random_loot.")
                .translation(KEY_PREFIX + "inChestLoot")
                .define("inChestLoot", false);
        builder.pop();

        SPEC = builder.build();
    }

    private MarketholdConfig() {
    }
}
