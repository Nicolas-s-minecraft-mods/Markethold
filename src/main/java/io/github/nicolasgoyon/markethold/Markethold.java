package io.github.nicolasgoyon.markethold;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

/** The Markethold mod: balance of the trades and of the loot, with a list of blocked enchantments. */
@Mod(Markethold.MOD_ID)
public final class Markethold {
    /** The id of the mod. */
    public static final String MOD_ID = "markethold";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Markethold(IEventBus modEventBus, ModContainer container) {
        LOGGER.debug("{} {} starts", MOD_ID, container.getModInfo().getVersion());
    }
}
