package io.github.nicolasgoyon.markethold;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MarketholdTest {
    @Test
    void modIdIsMarkethold() {
        assertEquals("markethold", Markethold.MOD_ID);
    }
}
