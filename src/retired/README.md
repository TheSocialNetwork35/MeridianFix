# Retired upstream implementation (not compiled)

These LGPL-3.0-or-later sources are preserved verbatim from the upstream baseline
for provenance and future investigation. They are not active MeridianFix features.

- `AsyncLoadingScreen`: unused GLFW context helper; 26.3 uses SDL.
- `datapack_reload_exceptions/RecipeManagerMixin`: old `RecipeManager.apply` reload
  hook no longer exists; recipe decoding is now part of registry loading.
- `worldgen_allocation/NoiseChunkMixin`: `wrap` and its backing cache were removed.
- `worldgen_allocation/SequenceRuleMixin`: 26.3 compiles material sequences to an
  array-backed evaluator; the old iterator-allocation patch is obsolete.
- `worldgen_allocation/SurfaceRulesContextMixin`: 26.3 stores a lazily fetched biome
  holder directly; the former per-update supplier allocation is absent.
- `optimize_surface_rules/*`: old SurfaceRules/SurfaceSystem integration is not
  valid for 26.3. Vanilla BiomeCondition now contains possible-biome hoisting.
  The associated TerraBlender integration has not been verified and is not active.

See release/SOURCE_CHANGES.md for the complete fork inventory. Do not copy these
files back into src/main without checking targets and behavior for the game version.

Additional 26.3 retirements:
- `devenv/MinecraftMixin`: development-only Yggdrasil service workaround targeting
  authentication APIs removed from 26.3; not a user-facing fix.
- `world/gen/*`: helper interfaces and prefetch implementation used exclusively by
  the retired surface-rule optimization; no active consumers remain.
