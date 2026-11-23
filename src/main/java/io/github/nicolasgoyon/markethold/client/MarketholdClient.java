package io.github.nicolasgoyon.markethold.client;

import io.github.nicolasgoyon.markethold.Markethold;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

/** Client entry point of Markethold. The game loads this class only on the client. */
@Mod(value = Markethold.MOD_ID, dist = Dist.CLIENT)
public final class MarketholdClient {
    public MarketholdClient(IEventBus modEventBus, ModContainer container) {
        // Config screen in the mod list. Server values are editable only in a world.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
