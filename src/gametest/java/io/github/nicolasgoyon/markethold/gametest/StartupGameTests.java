package io.github.nicolasgoyon.markethold.gametest;

import io.github.nicolasgoyon.markethold.Markethold;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

/** In-game tests for the start of the mod. */
@GameTestHolder(Markethold.MOD_ID)
@PrefixGameTestTemplate(false)
public final class StartupGameTests {
    private StartupGameTests() {
    }

    /** The server loads the mod. */
    @GameTest(template = "empty", timeoutTicks = 20)
    public static void modIsLoaded(GameTestHelper helper) {
        helper.assertTrue(ModList.get().isLoaded(Markethold.MOD_ID), "The mod must be loaded");
        helper.succeed();
    }
}
