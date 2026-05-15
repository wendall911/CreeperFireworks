# CreeperFireworks — Project Context

## What This Is
A mod that replaces creeper griefing with a configurable set of options — block damage,
entity damage, and more. Fireworks still fire even when mob griefing is disabled.
Inspired by the abandoned Creeper Confetti mod.

License: MIT

## Project Structure
Multi-loader: `Common/` + `NeoForge/` (+ `Forge/` on 1.20.1 branch) + `Fabric/`

## Branch Convention
| Branch | Modloaders        |
|--------|-------------------|
| 1.20.1 | Forge + Fabric    |
| 1.21.1 | NeoForge + Fabric |
| 26.1   | NeoForge + Fabric |

Maintained: 1.20.1, 1.21.1, 26.1

## Dependencies
- WhiteNoise (jarJar/include)

## Distribution
Side: both (clientRequired = true, serverRequired = true)

## Release Process
Follow the standard wendall911 release process in
`../docs/minecraft/MINECRAFT_DEVELOPMENT_NOTES.md`.
