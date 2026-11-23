# Markethold

<img src="docs/markethold_logo.png" alt="Logo of Markethold" width="128">

NeoForge mod: balance of the trades and of the loot, with a list of blocked enchantments.

[![Build](https://img.shields.io/github/actions/workflow/status/Nicolas-s-minecraft-mods/Markethold/build.yml?branch=develop&label=build)](https://github.com/Nicolas-s-minecraft-mods/Markethold/actions/workflows/build.yml)
[![CurseForge](https://img.shields.io/curseforge/dt/1825648?logo=curseforge&label=curseforge)](https://www.curseforge.com/projects/1825648)
![Minecraft 1.21.1](https://img.shields.io/badge/Minecraft-1.21.1-62B47A)
![NeoForge](https://img.shields.io/badge/NeoForge-21.1-E68C37)
[![License: MIT](https://img.shields.io/github/license/Nicolas-s-minecraft-mods/Markethold)](LICENSE)

## State

Villagers do not sell Mending, and fishing does not give it. A list in the server configuration gives the blocked enchantments:

- The villagers and the wandering trader make no new offers with a blocked enchantment.
- In the existing offers, each blocked enchantment changes to a different enchantment.
- In the fishing loot, each blocked enchantment changes to a different enchantment. As an option, the chest loot also changes.

The mod needs no other mod. The rules and the configuration are in [docs/design.md](docs/design.md).

## Worlds of Folkhold Balance

A world of Folkhold Balance can use Markethold for the blocked enchantments. The world loses nothing. The config file has a new name (`markethold-server.toml`). To keep a changed list, copy the section `[blockedEnchantments]` to the new file. The steps are in [docs/design.md](docs/design.md#worlds-of-folkhold-balance).

## Contribute

Read [CONTRIBUTING.md](CONTRIBUTING.md) for the workflow, the build, the tests and the versions.

## License

[MIT](LICENSE)
