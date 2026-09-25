# Validation — 2026-09-25

Minecraft 26.3; Forge 66.0.3; Java 25; ForgeGradle 7.0.29; Gradle 9.7.1; macOS arm64, Apple M2.
Build: PASS. Regression tests: **12 passed, 0 failures/errors/skipped**.
Tests are component-level JVM tests; they do not bootstrap a Forge game server.

Mixin audit runs: client and dedicated server, both standard and beta tiers, PASS.
Audits force-load eligible targets and exit during startup. They run in the development
classpath, not a clean installed-JAR launcher instance. Optional absent mods and disabled
or experimental-tier features are outside this coverage. Full gameplay/world creation,
terrain generation, save/reopen, join/disconnect, modpack compatibility, other operating
systems and GPUs remain MANUAL. No performance measurements were made.

Dedicated-server audits bootstrap Minecraft and Forge mod setup, then exit before
opening a world or starting a server. They neither accept nor modify the EULA.
Resolved during porting: separate output classloader conflicts, macOS SDL startup
flags, early registry access and NeoForge-only language method signatures.

Evidence: build/audit/audit-beta logs and JUnit XML under evidence/. Third-party compile-only APIs
are not proof of compatibility and are not embedded. Minecraft asset/authentication
network warnings and upstream Gradle/deprecation warnings may appear; inspect logs
before changing the toolchain. Atomic replacement is not a power-loss guarantee.
