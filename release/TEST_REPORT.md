# Validation — 2026-09-25

Minecraft 1.20.1; Forge 47.4.0; Java 17; macOS arm64, Apple M2.
Build: PASS. Regression tests: **12 passed, 0 failures/errors/skipped**.
Tests are component-level JVM tests; they do not bootstrap a Forge game server.

Mixin audit runs: client and dedicated server, both standard and beta tiers, PASS.
Audits force-load eligible targets and exit during startup. They run in the development
classpath, not a clean installed-JAR launcher instance. Optional absent mods and disabled
or experimental-tier features are outside this coverage. Full gameplay/world creation,
terrain generation, save/reopen, join/disconnect, modpack compatibility, other operating
systems and GPUs remain MANUAL. No performance measurements were made.

Evidence: build/audit logs and JUnit XML under evidence/. Third-party compile-only APIs
are not proof of compatibility and are not embedded. Minecraft asset/authentication
network warnings and upstream Gradle/deprecation warnings may appear; inspect logs
before changing the toolchain. Atomic replacement is not a power-loss guarantee.
