# ModernFix Reforged

**Unofficial fork of [ModernFix](https://github.com/embeddedt/ModernFix) by embeddedt
and contributors. Not endorsed by its maintainers, Mojang or Microsoft.**

This development alpha for **Minecraft 26.3 / Forge 66.0.3 / Java 25**
adds failure-safe configuration replacement, stricter ZIP directory parsing and
nonrecursive traversal, cached index-failure fallback, executor shutdown correctness,
and cached structure-reference maps that remain live after initially being empty.
Resource-pack overlay indexes are isolated from the base pack and other overlays.

The build and twelve component regression tests pass. Standard and beta client/server
Mixin audits were run as recorded in TEST_REPORT.md. These checks are not full gameplay,
world persistence, modpack compatibility or performance benchmarks.

## Forge-specific scope

This is a native Forge 66.0.3 port using ForgeGradle 7.0.29, Gradle 9.7.1 and
Forge EventBus 7. It retains the common reliability changes and adapts dynamic
language loading to Forge's actual API. This alpha excludes NeoForge-specific dynamic
model loading, capability compaction, entity pose-stack patches, registry progress,
debug-overlay branding and integrated-server suspension. It also omits the NeoForge
stdout replacement, developer large-registry injection, missing-performance-mod
dialog and disconnect debug-cache cleanup. It does not claim feature equivalence
with the NeoForge build. See FORGE_26_PORT.md in the release dossier.

## Installation and compatibility

Use exactly Minecraft 26.3, the tested Forge version, and Java 25.
Place the matching runtime JAR in mods/. Do not install the sources JAR. Remove
upstream ModernFix and other forks sharing mod ID `modernfix` first. Client and server
installation are optional; each side supplies its own functionality. Test in a clean,
disposable instance before using existing worlds. Third-party integrations are unverified.
ZIP indexing is beta-tier; `stability_level=BETA` in config/modernfix-mixins.properties
also enables other beta features. Restart after changing options.

## Credits and license

Original code: embeddedt and ModernFix contributors. Historical configuration code:
Sodium contributors. Inherited TimeFormatter: Guava Authors (Apache-2.0). Inherited
StbStitcher adaptation: eigenraven and GTNewHorizons/lwjgl3ify contributors (LGPL v3).
Fork maintainer: TheSocialNetwork35. Original notices and history remain. The fork
retains LGPL-3.0-or-later; LICENSE includes LGPL v3 and GPL v3. Additional notices
and complete corresponding source accompany the release.
[Source and issues](https://github.com/TheSocialNetwork35/MeridianFix).

## Disclosures and status

Codex generated the fork additions, tests and publishing text. Disclose derivative
content, AI-generated code and AI-generated text. No AI artwork was created; the mod
has no generative-AI runtime feature. Modrinth assesses a fork's additions separately;
public publication is not cleared for this predominantly AI-generated contribution.

Inherited optional spark profiling is disabled by default. Enabling it with a compatible
spark installation and `-Dmodernfix.allowSparkProfiling=true` permits profile uploads
to the configured spark/Bytebin backend. Profiles can contain thread samples and system
metadata; no anonymity promise. `-Dmodernfix.profileSaveToFile=true` selects local output.
This is opt-in telemetry and the integration remains untested in this release.
