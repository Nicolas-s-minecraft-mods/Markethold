# Markethold design

Markethold is a mod for Minecraft 1.21.1 (NeoForge). It changes the balance of the trades and of the loot, with a list of blocked enchantments.

## State

The mod loads in the game. It has no function yet. All the sections after this one are **planned work**: they come from the open issues. The rules move from the module `balance` of Folkhold (#3), with the same behavior.

## Planned rules

- **No Mending in the trades**: the villagers do not sell the blocked enchantments.
- **No Mending in fishing**: the fishing loot does not give the blocked enchantments.
- **List of blocked enchantments**: the server configuration has the list. By default, the list has Mending.

## Planned compatibility

Markethold has its own mod id, so the config file has a new name. A server of Folkhold Balance must copy its list of blocked enchantments to the new config file. The release notes give the steps.
