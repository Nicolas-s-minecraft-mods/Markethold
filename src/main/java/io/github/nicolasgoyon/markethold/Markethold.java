package io.github.nicolasgoyon.markethold;

import com.mojang.logging.LogUtils;
import io.github.nicolasgoyon.markethold.config.MarketholdConfig;
import io.github.nicolasgoyon.markethold.enchantment.BlockedEnchantmentIds;
import io.github.nicolasgoyon.markethold.enchantment.VillagerOffers;
import io.github.nicolasgoyon.markethold.registry.ModLootModifiers;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

/** The Markethold mod: balance of the trades and of the loot, with a list of blocked enchantments. */
@Mod(Markethold.MOD_ID)
public final class Markethold {
    /** The id of the mod. */
    public static final String MOD_ID = "markethold";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Markethold(IEventBus modEventBus, ModContainer container) {
        container.registerConfig(ModConfig.Type.SERVER, MarketholdConfig.SPEC);
        ModLootModifiers.register(modEventBus);
        modEventBus.addListener(BlockedEnchantmentIds::onConfigLoad);
        modEventBus.addListener(BlockedEnchantmentIds::onConfigReload);
        // Lowest priority: the filter also wraps the trade listings that other mods add.
        NeoForge.EVENT_BUS.addListener(EventPriority.LOWEST, VillagerOffers::onVillagerTrades);
        NeoForge.EVENT_BUS.addListener(EventPriority.LOWEST, VillagerOffers::onWandererTrades);
        NeoForge.EVENT_BUS.addListener(VillagerOffers::onEntityJoin);
        NeoForge.EVENT_BUS.addListener(VillagerOffers::onConversion);
        LOGGER.debug("{} {} starts", MOD_ID, container.getModInfo().getVersion());
    }
}
