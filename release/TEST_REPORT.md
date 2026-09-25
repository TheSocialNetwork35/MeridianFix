# Validation report — 2026-09-25

Target: Minecraft Java Edition 26.3, NeoForge 26.3.0.16-beta, Java 25.
Local host: macOS arm64, Apple M2. JDK: Eclipse Temurin 25.0.4.1+1.
Build: Gradle wrapper 9.2.1, ModDevGradle 2.0.147.

## Completed

- `./gradlew --no-daemon build`: successful, including the distributable and sources JARs.
- NeoForge-bootstrapped JUnit: **12 tests, 0 failures, 0 errors, 0 skipped**.
  Seven ZIP-index tests, three configuration-write tests, two executor lifecycle tests.
- The same component tests passed in an isolated harness against actual 26.3 libraries.
- `runAuditServer`, `runAuditClient`, `runAuditServerBeta`, `runAuditClientBeta`: successful.
  The audit force-loads eligible Mixin targets and exits during mod setup. Stable and
  beta feature levels are separate runs. Client startup initialized SDL and the M2
  graphics backend; final runs used OpenGL. Earlier stable audit also initialized Vulkan.
- License packaging, loader/game metadata, source inventory and archive integrity
  checked during packaging. SHA256SUMS records the final release files.

Raw final output and JUnit XML are in evidence/. Static mixin-inventory.json lists
source declarations only: it does not mark absent optional-mod targets as tested.
Runtime evidence is the successful audit log, not the static inventory.

## What these checks do not establish

- No complete title-screen/resource reload, world creation, terrain generation,
  stronghold placement, join/disconnect, save/reopen, dedicated-server play or soak test.
- No broad compatibility with spark, CTM, CoFH Core, Refined Storage, Cyclic, shaders,
  resource packs or modpacks. Conditional targets for absent mods are skipped by design.
- No Windows/Linux runtime, other GPUs or other NeoForge versions were tested locally.
- Audits use the development classpath; installation of the packaged JAR in a clean
  launcher instance is a required manual release check. CI was configured, not used
  as evidence of a successful hosted run.
- No quantitative performance measurements, memory reduction or FPS claims.
- Beta ZIP indexing is opt-in through the upstream feature-level configuration;
  the beta audit validates injection, not every resource-pack interaction.
- EXPERIMENTAL-tier features and disabled optional options are outside these audit runs.

## Resolved porting failures

Initial compile/audit attempts exposed obsolete GUI/render, SurfaceRules, registry,
server-constructor and authentication APIs. Integrated tests caught the moved
NeoForge block-cache callback and renamed ChunkAccess field. Beta audits caught
an inherited frequency-reduction enum descriptor and the need to target the private
`ParameterList(List,int)` constructor explicitly. All were fixed or retired as
recorded in SOURCE_CHANGES.md; those early failures are not final unresolved errors.

## Remaining warnings and limits

Gradle reports deprecated features (future Gradle 10), native-access notices, and
annotation-processor warnings for absent optional third-party target classes.
NeoForge also warns that LoadingModList.get() is deprecated for removal.
Minecraft reports command ambiguities and the host OS library may not identify
macOS 27's codename. These did not fail the final checks. Do not interpret this as
proof that warnings are harmless on all systems. Updating beyond the pinned toolchain
requires rerunning validation. Atomic saves fail safely on filesystems without atomic
replacement; they are not a power-loss durability guarantee. Optional external spark
profiling retains upstream opt-in upload behavior, described in MODRINTH_SETTINGS.md.
