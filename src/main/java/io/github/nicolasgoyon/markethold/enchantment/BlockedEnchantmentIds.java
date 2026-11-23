package io.github.nicolasgoyon.markethold.enchantment;

import io.github.nicolasgoyon.markethold.Markethold;
import io.github.nicolasgoyon.markethold.config.MarketholdConfig;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

/** Checks the ids and the tags of the config key {@code enchantments} of the section {@code blockedEnchantments}. */
public final class BlockedEnchantmentIds {
    private BlockedEnchantmentIds() {
    }

    /**
     * Logs a warning for each id or tag of the config that is not in the enchantment registry, for example a typo.
     * The server calls this method when it loads or reloads its config.
     */
    public static void onConfigLoad(ModConfigEvent.Loading event) {
        checkIds(event);
    }

    /** Same as {@link #onConfigLoad(ModConfigEvent.Loading)}, after a change of the config file. */
    public static void onConfigReload(ModConfigEvent.Reloading event) {
        checkIds(event);
    }

    private static void checkIds(ModConfigEvent event) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (event.getConfig().getSpec() != MarketholdConfig.SPEC || server == null) {
            return;
        }
        Registry<Enchantment> registry = server.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        EnchantmentFilter filter = BlockedEnchantments.filter();
        for (String id : filter.ids()) {
            ResourceLocation location = ResourceLocation.tryParse(id);
            if (location == null || !registry.containsKey(location)) {
                Markethold.LOGGER.warn("Blocked enchantments: the enchantment {} does not exist", id);
            }
        }
        for (String tag : filter.tags()) {
            ResourceLocation location = ResourceLocation.tryParse(tag);
            if (location == null || registry.getTag(TagKey.create(Registries.ENCHANTMENT, location)).isEmpty()) {
                Markethold.LOGGER.warn("Blocked enchantments: the enchantment tag #{} does not exist", tag);
            }
        }
    }
}
