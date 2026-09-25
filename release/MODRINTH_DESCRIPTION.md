# MeridianFix

**An unofficial, independently maintained fork of [ModernFix](https://github.com/embeddedt/ModernFix) by embeddedt and contributors.** MeridianFix is not endorsed by the ModernFix team, Mojang, or Microsoft.

> Development alpha for Minecraft Java Edition 26.3 and NeoForge. Publication is on hold: check the accompanying test report and publishing checklist. This page is a draft, not evidence of Modrinth approval.

## What this fork changes

MeridianFix focuses on resource-pack and configuration reliability while adapting the upstream codebase to 26.3:

- **Overlay-aware ZIP indexing:** resource-pack overlay directories are indexed separately, so base resources and unrelated overlays are not exposed through an overlay index.
- **Stricter archive handling:** malformed central-directory records cause the indexing path to fall back instead of presenting an incomplete index. An indexing failure is remembered until the pack closes, avoiding repeated attempts during that lifecycle.
- **Deep ZIP directory support:** index freezing and resource enumeration use explicit work queues instead of recursive traversal.
- **Safer configuration replacement:** configuration content is written to a temporary file before an atomic replacement. A write failure preserves the previous configuration; filesystems without atomic replacement report a save failure. This is not a promise of protection against every hardware or power failure.
- **Live structure-reference views:** a cached view obtained from an empty chunk remains connected to later reference additions.
- **Worker lifecycle correctness:** nested submissions after shutdown are rejected while supported nested submissions before shutdown still complete.
- **26.3 integration changes:** updated material-condition targets, preservation of the biome-search tree's new branching parameter, GUI/language/browser API changes, the XP-bar rendering target, server constructor changes, and NeoForge's block-cache progress hook.

Some old upstream implementations were retired because their target APIs disappeared or equivalent behavior is already present in 26.3. This is not a promise that every historical ModernFix patch is present. The exact differences are documented in `SOURCE_CHANGES.md`.

## Minecraft 26.3 and validation

The configured target is **Minecraft 26.3**, **NeoForge 26.3.0.16-beta**, and **Java 25**. Fabric and Forge are not supported by this artifact. Other Minecraft versions are rejected by its metadata.

The build succeeds, all 12 tests pass inside the NeoForge test environment, and client/server Mixin audits pass at both stable and beta feature levels. The same isolated regression suite also passed against actual 26.3 game libraries, including overlay isolation, damaged ZIP input, a 6,000-level directory tree, failed configuration writes, symbolic links and executor shutdown. Refer to `TEST_REPORT.md` for the final build and runtime results and their limits. These tests do not establish general modpack compatibility or measurable performance gains.

The ZIP index remains part of upstream's experimental feature tier. Enabling `stability_level=BETA` in `config/modernfix-mixins.properties` also enables other beta-tier features; review that configuration before testing. Restart the game after changing options.

## Installation

After resolving the publication and validation gates:

1. Use Minecraft Java Edition 26.3 with Java 25 and the tested NeoForge version.
2. Remove upstream ModernFix and any other fork using the `modernfix` mod ID from this instance.
3. Place the MeridianFix NeoForge JAR in the instance's `mods` folder.
4. Start with a disposable test instance and check its log and configuration before using an existing world.

The internal `modernfix` ID, configuration path and integration packages are retained deliberately. Loader metadata and startup messages identify MeridianFix as an unofficial fork.

## Compatibility

No broad compatibility claim is made for third-party mods. Optional integrations inherited from upstream have not all been verified on 26.3. Do not infer compatibility from an integration class being present in the source. Report MeridianFix issues to the fork repository, with the exact game, loader and mod versions; do not send fork-specific reports to the original maintainers.

No benchmarks, FPS gains, memory percentages or universal stability guarantees are claimed.

## Optional profiling uploads

Inherited spark profiling is disabled by default. If enabled with a compatible spark installation and `-Dmodernfix.allowSparkProfiling=true`, recorded profile data is uploaded to the configured spark/Bytebin service. Profiling data can contain thread samples and environment metadata; do not assume it is anonymous. `-Dmodernfix.profileSaveToFile=true` selects local output instead. This optional integration has not been tested on 26.3.

## Credits, source and license

- **embeddedt and all ModernFix contributors:** the original mod, most of this codebase, its configuration infrastructure, resources and fixes.
- **Sodium contributors:** historical LGPL configuration code attributed by ModernFix.
- **The Guava Authors:** formatting routines adapted in upstream `TimeFormatter`; the corresponding Apache-2.0 notice and license are included.
- **eigenraven and GTNewHorizons contributors:** inherited LGPL lwjgl3ify texture-stitcher adaptation.
- **TheSocialNetwork35:** fork maintainer.

ModernFix's original notices, Git history and acknowledgements are retained. See `ATTRIBUTION.md`, `THIRD_PARTY_NOTICES.md`, and the preserved upstream documentation for details.

The fork retains **LGPL-3.0-or-later**. The supplied `LICENSE` contains LGPL v3 and GPL v3. Corresponding source and build scripts must accompany any distributed release. [Fork source](https://github.com/TheSocialNetwork35/MeridianFix) · [Original ModernFix](https://github.com/embeddedt/ModernFix).

## AI disclosure and publication status

Codex generated this fork's new implementation, tests and project-page draft. This disclosure concerns MeridianFix additions and is not a claim about the original authors' AI usage. No AI-generated logo, icon or gallery image was created, and the mod has no generative-AI runtime feature.

The appropriate Modrinth disclosures are **Derivative Content**, **AI-generated code** and **AI-generated text**. These disclosures do not by themselves make an AI-generated fork eligible for public publication: Modrinth assesses the fork's additions separately. Public submission remains blocked pending resolution of that rule.

### Inherited AI attribution

The upstream `ConcentricRingsStructurePlacementMixin` already explicitly credits
`embeddedt, GPT-5.3-Codex` in its author notice. That notice is preserved. The AI-code
disclosure therefore covers both Codex's new fork changes and this explicitly
identified inherited contribution; no claim is made that all upstream code is free
of AI involvement. A complete upstream provenance audit has not been performed.
