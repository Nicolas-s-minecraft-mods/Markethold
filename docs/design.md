# Markethold design

Markethold is a mod for Minecraft 1.21.1 (NeoForge). It changes the balance of the trades and of the loot, with a list of blocked enchantments. The mod needs no other mod.

## Blocked enchantments

Villagers do not sell the blocked enchantments, and fishing does not give them. The default list has Mending only (constant `BlockedEnchantments.DEFAULT`, config key `enchantments`).

### Trades

Classes: `FilteredListing`, `VillagerOffers`.

- The result of a trade offer never has a blocked enchantment, as an enchantment or as a stored enchantment of a book.
- The filter wraps each trade listing of the villagers and of the wandering trader, also the listings of other mods. The listener has the lowest priority, so it runs after the listeners of the other mods.
- When an offer has a blocked enchantment, the listing makes the offer again, up to `rerollAttempts` times. When all the attempts fail, the villager takes an offer from a different listing.
- When a villager or a wandering trader joins a level, each blocked enchantment in its offers changes to a random enchantment of `#minecraft:tradeable`. Each enchantment of the tag has the same chance.
- The new enchantment is compatible with the other enchantments of the item. For an item that is not a book, the item supports the new enchantment. The level stays the same, in the range of the new enchantment.
- A vanilla book offer (one stored enchantment for emeralds) gets a new price from the vanilla formula (`BookOffers`). The uses, the demand and the other values of the offer stay the same.
- A villager with no offers yet does not change: the filter does not make its offers.
- A cured zombie villager gets its offers after it joins the level. The mod cleans these offers after the conversion.
- The trades of the experiment "Villager Trade Rebalance" do not go through the NeoForge trade events. The filter does not see their new offers. The offers change when the villager joins a level again.

### Loot

Class: `BlockedEnchantmentsLootModifier`, a global loot modifier (`markethold:blocked_enchantments`).

- Fishing: each blocked enchantment in the fishing loot changes to a random enchantment of `#minecraft:on_random_loot`, with the rules of the trades. The weights of the enchantments give the chance of each new enchantment, as in the vanilla loot functions.
- The loot modifier reads the id of the table that the game rolls: `minecraft:gameplay/fishing`, not its nested table `gameplay/fishing/treasure` (`LootScope`).
- Chest loot does not change by default: Mending stays an exploration reward. The option `inChestLoot` applies the rule to each table with a path that starts with `chests/`, in all namespaces.
- The loot modifier does not change the number of items or their types.
- The enchanting table never gives Mending (tag `#minecraft:in_enchanting_table`). The mod does not change it.

| Source of Mending | Vanilla | Markethold default |
| --- | --- | --- |
| Librarian, new offer | yes | no (new attempt) |
| Librarian, existing offer | yes | no (enchantment replaced) |
| Fishing | yes | no (enchantment replaced) |
| Chests | yes | yes |
| Enchanting table | no | no |

### Check of the list

When the server loads its config, it logs a warning for each id or tag of `enchantments` that does not exist (`BlockedEnchantmentIds`).

## Configuration

Section `blockedEnchantments` of `markethold-server.toml`, in the `serverconfig` folder of each world. The config screen of the mod list shows the same values, in English and in French.

| Value | Default | Meaning |
| --- | --- | --- |
| `enchantments` | `["minecraft:mending"]` | Enchantment ids or tags (`#namespace:path`) |
| `inTrades` | true | Villagers and wandering traders make no new offers with a blocked enchantment |
| `cleanExistingOffers` | true | Replace the blocked enchantments in the offers of a villager that joins a level. Needs `inTrades` |
| `rerollAttempts` | 10 | New attempts for an offer with a blocked enchantment |
| `inFishing` | true | Replace the blocked enchantments in the fishing loot |
| `inChestLoot` | false | Replace the blocked enchantments in the chest loot (tables `chests/...`) |

## Worlds of Folkhold Balance

Markethold has no items, no entities and no saved data. A world of Folkhold Balance loses nothing. The rules have the same section and the same values, in a config file with a new name. To keep a changed list on a server:

1. Stop the server.
2. Open `serverconfig/folkhold_balance-server.toml` of the world.
3. Copy the values of the section `[blockedEnchantments]` to the same section of `serverconfig/markethold-server.toml`. If this file does not exist, start the server one time with Markethold to make it.
4. Start the server.

Do not use Markethold and the blocked enchantments of Folkhold Balance in the same game. The two filters then do the same work two times.

## Tests

- JUnit (`src/test`): the filter of the config entries (`EnchantmentFilterTest`), the price of a book offer (`BookOffersTest`) and the groups of loot tables (`LootScopeTest`).
- GameTest (`src/gametest`, template `markethold:bench`): `BlockedEnchantmentGameTests`. The tests check the filter of all the listings, 1,000 book offers and the clean of the existing offers. They also check a cured zombie villager, the wandering trader, 1,000 rolls of the fishing table and the chest loot.
