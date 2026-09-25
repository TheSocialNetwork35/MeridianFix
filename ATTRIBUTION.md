# Attribution and license provenance

ModernFix Reforged is an **unofficial fork** of [ModernFix](https://github.com/embeddedt/ModernFix),
created by **embeddedt and the ModernFix contributors**. It is not endorsed by those
authors, Mojang, or Microsoft. Fork maintainer: TheSocialNetwork35.

Baseline: upstream branch `26.1`, commit `1111ec6fd21a8d13e9b2fb468db3d000b951b6b3`.
The full upstream Git history is retained. `release/UPSTREAM_CONTRIBUTORS.txt` lists
commit authors recorded at this baseline; source comments and historical commits
also credit work that cannot be inferred from the commit-author list alone.

## License

The original LICENSE grants **LGPL-3.0-or-later** and includes the full LGPL v3 and
GPL v3 texts. It is retained byte-for-byte. Existing source-specific notices remain
in force. Fork additions are offered under LGPL-3.0-or-later; they do not change
rights or conditions on upstream code. No extra redistribution restrictions apply.
Distribute complete corresponding source and build scripts alongside any binary,
with a stable source link for the exact version, and preserve all notices.

## Reused work

The starting source tree, mixins, configuration system, annotations, annotation
processor, build infrastructure, translations and existing resources come from
ModernFix. The upstream README attributes the configuration system to Sodium,
used under LGPL-3.0; that credit is preserved. Some fixes derive from Forge PRs
and other projects: their in-file notices and history remain authoritative.
`doc/upstream/README.md` preserves the original acknowledgements, including YourKit.
The original icon remains an upstream source asset; it is not selected as ModernFix Reforged
branding and no new icon, logo or gallery artwork has been generated.

## Changes

See `release/SOURCE_CHANGES.md` and the Git diff against the baseline for the exact
fork modifications. Modified implementation files carry dated ModernFix Reforged notices.
Package names and the `modernfix` mod ID are retained for integration and conflict
detection; display metadata identifies this fork separately.

## AI provenance

Codex generated the new fork code, regression tests and publishing text in this
session, at the user's request. This statement applies to the fork additions;
known inherited AI attribution is recorded below. No complete upstream AI
provenance audit has been performed. No generative AI runs in-game.

## Porting references

API review used NeoForged's [26.3 migration primer](https://docs.neoforged.net/primer/docs/26.3/)
by ChampionAsh5357 and contributors (CC BY 4.0), and the
[Fabric 26.3 announcement](https://fabricmc.net/2026/09/15/263.html).
These are references, not bundled runtime dependencies or copied implementations.

### Inherited AI attribution

The upstream `ConcentricRingsStructurePlacementMixin` already explicitly credits
`embeddedt, GPT-5.3-Codex` in its author notice. That notice is preserved. The AI-code
disclosure therefore covers both Codex's new fork changes and this explicitly
identified inherited contribution; no claim is made that all upstream code is free
of AI involvement. A complete upstream provenance audit has not been performed.

## Additional upstream sources

See THIRD_PARTY_NOTICES.md for Guava formatting routines (Apache-2.0) and the
lwjgl3ify StbStitcher adaptation (LGPL v3). Their existing source notices are retained.

## Forge 26.3 port

This branch additionally derives from MeridianFix commit
f797aa54988c5c200ac85e2a4d541ced3c39b0ed. Native Forge 66.0.3/ForgeGradle 7.0.29
loader, lifecycle and EventBus 7 adapters were written by Codex on 2026-09-25.
NeoForge-specific implementations were preserved outside the compiled source tree
under src/retired/forge26-java. See doc/FORGE_26_PORT.md for exclusions.
Forge API documentation and its MDK examples were consulted; Forge/Minecraft
runtime code is not redistributed inside this mod JAR.
