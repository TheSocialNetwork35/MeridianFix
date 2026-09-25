# Forge 26.3 adapter

Based on the existing LGPL MeridianFix 26.3 port. This target uses ForgeGradle 7,
Forge 66.0.3 and EventBus 7; it is a distinct artifact, not a relabeled NeoForge JAR.
NeoForge-specific dynamic-model loading, capability compaction, entity event-stack
patches, registry progress, debug overlay branding and integrated-server suspension
are excluded from this Forge alpha; original implementations are retained under
src/retired/forge26-java. Common game patches and the five reliability improvements
remain. This exclusion is not a claim that Forge already fixes those issues.
See final TEST_REPORT.md for verified behavior and untested features.

This alpha also does not wire the NeoForge-specific stdout replacement, developer
large-registry injection, missing-performance-mod warning dialog or client disconnect
debug-cache cleanup. It uses Forge-native command, server lifecycle, recipe/tag,
render-tick and config-screen events. Block-state debug generation uses the vanilla
Block.BLOCK_STATE_REGISTRY after Forge removed GameData.getBlockStateIDMap. The shared
ZIP access class needs an explicit Forge access-transformer rule. Dynamic model loader
callbacks are not advertised as active. Do not imply feature equivalence with NeoForge.

Development verification needs ForgeGradle's merged source-set output; otherwise the
same game types can be loaded through different classloaders. macOS client runs set
-XstartOnFirstThread and AWT headless mode for SDL. The dedicated-server audit uses
an opt-in Main injection that bootstraps and audits transformations, then exits before
starting a game server or opening a world. It does not accept or modify the EULA and
is not evidence of a full server lifecycle test. Normal launches are unaffected.

The beta dynamic-language mixin targets Forge’s three-argument appendFrom and
two-argument ClientLanguage constructor. NeoForge’s extra component-map arguments
do not exist in Forge 66. Both standard and beta client/server audits exercise the
applicable transformation targets; this is not a language-reload gameplay test.
