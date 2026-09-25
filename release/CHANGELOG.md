# MeridianFix changelog

## 0.1.0-alpha.1+mc26.3 — 2026-09-25

First unofficial MeridianFix development alpha, based on ModernFix's `26.1` branch
at `1111ec6fd21a8d13e9b2fb468db3d000b951b6b3`.

- Port material-condition, GUI, XP-bar, language, browser, server constructor,
  registry bootstrap and NeoForge callback targets to Minecraft 26.3.
- Preserve the 26.3 biome search tree branching parameter during lazy construction.
- Fix the cached structure-reference map so an initially empty view stays live.
- Isolate resource-pack overlay indexes; validate ZIP directory structure and paths;
  avoid recursive traversal; remember failed indexing until pack close.
- Replace configuration files atomically after writing, preserving existing files
  on write failure and following existing symbolic links.
- Reject nested executor submissions after shutdown.
- Retire incompatible or obsolete upstream implementations, preserving their sources.
- Remove unused compile dependencies and 36 access-transformer rules for absent classes.
- Add 12 regression tests, NeoForge test bootstrapping and four Mixin audit runs.
- Package LGPL/GPL, Apache notices, source, fork attribution and honest AI disclosures.

Validation: build, 12 integrated tests, and stable/beta client/server Mixin audits pass.
No gameplay, world-save, third-party modpack or performance claim is made.
Public Modrinth submission is blocked by the AI-origin rule for fork additions;
see PUBLISH_CHECKLIST.md. There is no fabricated upstream release history here.
