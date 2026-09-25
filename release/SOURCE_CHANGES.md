# Changes from upstream

Baseline: [embeddedt/ModernFix, branch 26.1](https://github.com/embeddedt/ModernFix/tree/1111ec6fd21a8d13e9b2fb468db3d000b951b6b3),
commit `1111ec6fd21a8d13e9b2fb468db3d000b951b6b3`.
All changes below are MeridianFix work dated 2026-09-25. Codex generated the changes
at the maintainer's request; upstream authors are not responsible for this fork.
The complete file-level diff can be reproduced with `git diff BASELINE HEAD`.

## Independent behavior changes

1. `resources/ZipPackIndex.java`: optional overlay prefix, exact prefix filtering and
   prefixed ZipEntry reads; reject malformed/truncated central directories, multi-disk
   and unsupported ZIP64 indexes; validate directory ranges and canonical entry paths;
   reject empty resource queries; iterative tree freezing and traversal avoid stack
   exhaustion. No zip-bomb or arbitrary archive-security guarantee is claimed.
2. `common/mixin/perf/resourcepacks/FilePackResourcesMixin.java`: uses the pack prefix,
   remembers index failure to avoid repeated attempts, and resets index/failure on close.
   It falls back to the normal ZIP implementation on index failure. Remains beta-tier.
3. `util/AtomicConfigFile.java` and `core/config/ModernFixEarlyConfig.java`: write sibling
   temporary file with Properties-compatible encoding, close before atomic replacement,
   preserve existing symlinks, clean temporary files on failure, and report unsupported
   atomic moves without truncating the previous config.
4. `util/SingleThreadedWorkerService.java`: validate null tasks and reject submissions
   after shutdown even from within the worker; retain valid reentrant execution.
5. `common/mixin/perf/ticking_chunk_alloc/ChunkAccessMixin.java`: use 26.3's
   `structureReferences` field; cache an unmodifiable live view even when initially
   empty instead of returning a permanently empty snapshot.

## Required 26.3 port changes

- `worldgen_allocation/SurfaceRulesMixin`: target the five new material-condition
  anonymous evaluators and MaterialRuleContext.LazyYCondition.
- `worldgen_allocation/ClimateParameterListMixin`: redirect the private `(List,int)`
  constructor, retain children-per-node, and pass it to delayed RTree construction.
- `compact_mojang_registries/VanillaRegistriesMixin`: cache `createWorldLookup` only;
  avoid treating the new context-dependent reloadable lookup as a global constant.
- `cache_strongholds/ConcentricRingsStructurePlacementMixin`: update inherited
  superclass enum descriptor to AbstractSpreadingStructurePlacement. Preserve the
  existing embeddedt/GPT-5.3-Codex credit; no claim of new stronghold algorithm design.
- `sprite_borders/GuiMixin`: ExperienceBar target and RenderPearl pipeline descriptor.
- `world_screen_skipped/WorldSelectionListMixin`, configuration screens, OptionList:
  Gui owns screen access; Language.has replaces removed I18n.exists; Blaze3D opens links.
- `feature/measure_time/MinecraftMixin`: read overlay from Gui rather than removed field.
- `suspend_integrated_server_during_load/IntegratedServerMixin`: NotificationManager
  constructor argument forwarded.
- `feature/registry_event_progress/BlockCallbacksMixin`: hook the SolidDebugger-wrapped
  `lambda$onBake$0` that now performs cache initialization and completion.
- `ModernFix.java`: explicit unofficial fork identity and side-safe audit exit path.
- Access transformers: remove 36 entries whose class is absent from 26.3; original
  removed rules are recorded in doc/upstream/removed-26.3-access-transformers.txt.

## Retired implementations

Sources are retained verbatim under `src/retired/java`, outside the compiled tree:
old SurfaceRules optimization family (including TerraBlender adapter) and its
`world/gen` helpers; NoiseChunk.wrap caching; old surface biome supplier and sequence
implementations; RecipeManager.apply exception hook; unused GLFW loading-screen helper;
obsolete development authentication workaround. See src/retired/README.md for reasons.
This fork does not claim to retain every feature from older ModernFix releases.

## Build, metadata and dependencies

Pin Minecraft 26.3/NeoForge 26.3.0.16-beta/Java 25, ModDevGradle 2.0.147 and AP Mixin API.
Keep Gradle 9.2.1; increase wrapper download timeout. Remove unused legacy/Fabric
runtime properties, unused compile-only JEI/LDLib/SuperMartijnCore/CoFH/ResourcefulLib/
KubeJS/TerraBlender dependencies and stale JEI minimum-version metadata. Retain only
CTM and spark compile-only APIs actually referenced; they are not bundled or claimed
26.3-compatible. Fabric Loader is used by the build-time annotation processor only.
Move unused buildSrc version discovery to doc/upstream. Use independent alpha version,
archive name, manifest title, website and issue links. Keep modernfix internal ID to
prevent co-installation with upstream. Remove official upstream publishing tasks and
replace CI with a read-only build/test/artifact workflow. Include notices in both JARs,
ordered archives and sourcesJar; enable NeoForge JUnit bootstrap and four audit tasks.

## Tests, documentation and provenance

Add 12 component regression tests, isolated test runner and static Mixin inventory.
Preserve upstream README/CONTRIBUTING/buildSrc and complete Git history. Add attribution,
contributors list, Apache-2.0 license for inherited Guava routines, lwjgl3ify notices,
release metadata, test limits and policy checklist. Retain original icon only as
upstream asset, not fork branding; no AI artwork. No upstream license grant was narrowed.

## Changed tracked files (relative to baseline)

```text
M	.github/workflows/gradle.yml
M	.gitignore
M	CONTRIBUTING.md
M	README.md
M	annotation-processor/build.gradle
M	build.gradle.kts
D	buildSrc/build.gradle.kts
D	buildSrc/src/main/kotlin/GitVersionSource.kt
M	gradle.properties
M	gradle/wrapper/gradle-wrapper.properties
M	src/main/java/org/embeddedt/modernfix/ModernFix.java
M	src/main/java/org/embeddedt/modernfix/common/mixin/bugfix/sprite_borders/GuiMixin.java
M	src/main/java/org/embeddedt/modernfix/common/mixin/bugfix/world_screen_skipped/WorldSelectionListMixin.java
D	src/main/java/org/embeddedt/modernfix/common/mixin/devenv/MinecraftMixin.java
M	src/main/java/org/embeddedt/modernfix/common/mixin/feature/measure_time/MinecraftMixin.java
M	src/main/java/org/embeddedt/modernfix/common/mixin/feature/registry_event_progress/BlockCallbacksMixin.java
M	src/main/java/org/embeddedt/modernfix/common/mixin/perf/cache_strongholds/ConcentricRingsStructurePlacementMixin.java
M	src/main/java/org/embeddedt/modernfix/common/mixin/perf/compact_mojang_registries/VanillaRegistriesMixin.java
D	src/main/java/org/embeddedt/modernfix/common/mixin/perf/datapack_reload_exceptions/RecipeManagerMixin.java
D	src/main/java/org/embeddedt/modernfix/common/mixin/perf/optimize_surface_rules/BiomeConditionSourceMixin.java
D	src/main/java/org/embeddedt/modernfix/common/mixin/perf/optimize_surface_rules/BiomeManagerAccessor.java
D	src/main/java/org/embeddedt/modernfix/common/mixin/perf/optimize_surface_rules/NamespacedSurfaceRuleSourceMixin.java
D	src/main/java/org/embeddedt/modernfix/common/mixin/perf/optimize_surface_rules/NoiseBasedChunkGeneratorMixin.java
D	src/main/java/org/embeddedt/modernfix/common/mixin/perf/optimize_surface_rules/SurfaceRulesContextMixin.java
D	src/main/java/org/embeddedt/modernfix/common/mixin/perf/optimize_surface_rules/SurfaceSystemMixin.java
M	src/main/java/org/embeddedt/modernfix/common/mixin/perf/resourcepacks/FilePackResourcesMixin.java
M	src/main/java/org/embeddedt/modernfix/common/mixin/perf/suspend_integrated_server_during_load/IntegratedServerMixin.java
M	src/main/java/org/embeddedt/modernfix/common/mixin/perf/ticking_chunk_alloc/ChunkAccessMixin.java
M	src/main/java/org/embeddedt/modernfix/common/mixin/perf/worldgen_allocation/ClimateParameterListMixin.java
D	src/main/java/org/embeddedt/modernfix/common/mixin/perf/worldgen_allocation/NoiseChunkMixin.java
D	src/main/java/org/embeddedt/modernfix/common/mixin/perf/worldgen_allocation/SequenceRuleMixin.java
D	src/main/java/org/embeddedt/modernfix/common/mixin/perf/worldgen_allocation/SurfaceRulesContextMixin.java
M	src/main/java/org/embeddedt/modernfix/common/mixin/perf/worldgen_allocation/SurfaceRulesMixin.java
M	src/main/java/org/embeddedt/modernfix/core/config/ModernFixEarlyConfig.java
D	src/main/java/org/embeddedt/modernfix/neoforge/util/AsyncLoadingScreen.java
M	src/main/java/org/embeddedt/modernfix/resources/ZipPackIndex.java
M	src/main/java/org/embeddedt/modernfix/screen/ModernFixConfigScreen.java
M	src/main/java/org/embeddedt/modernfix/screen/ModernFixOptionInfoScreen.java
M	src/main/java/org/embeddedt/modernfix/screen/OptionList.java
M	src/main/java/org/embeddedt/modernfix/util/SingleThreadedWorkerService.java
D	src/main/java/org/embeddedt/modernfix/world/gen/ChunkBiomeLookup.java
D	src/main/java/org/embeddedt/modernfix/world/gen/ExtendedSurfaceContext.java
D	src/main/java/org/embeddedt/modernfix/world/gen/PositionalBiomeGetter.java
D	src/main/java/org/embeddedt/modernfix/world/gen/PrefetchingBlockColumn.java
M	src/main/resources/META-INF/accesstransformer.cfg
M	src/main/resources/META-INF/neoforge.mods.toml
```

New source: AtomicConfigFile.java; three test classes under src/test/java;
retired source archive, provenance files, release documents and scripts listed above.
