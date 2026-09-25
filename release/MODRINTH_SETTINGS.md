# Modrinth settings — development draft, NOT cleared for upload

- Project type: Mod.
- Name: MeridianFix. Check name/slug availability manually.
- Minecraft version: 26.3 (Java Edition), Alpha. Build and Mixin audits pass; gameplay gates remain.
- Loader: NeoForge only. Fabric was researched but is not implemented in this fork.
- Version type: Alpha; never Stable for this snapshot.
- Client environment: Optional. Server environment: Optional. Both sides pass startup Mixin audits; standalone installations and gameplay require manual verification.
- Required runtime: Java 25, NeoForge 26.3.0.16-beta or a separately tested 26.3 version.
- Embedded mod ID: modernfix. Do not install alongside upstream ModernFix or another fork using that ID.
- License: LGPL-3.0-or-later, preserving the upstream “or later” grant. LICENSE includes LGPL v3 and GPL v3.
- Proposed tags: optimization, utility. These describe purpose, not measured speed gains.
- Source: https://github.com/TheSocialNetwork35/MeridianFix (use the exact published release commit/tag).
- Issues: https://github.com/TheSocialNetwork35/MeridianFix/issues (verify enabled).
- Icon/gallery: none supplied. Do not upload AI-generated or AI-edited images.

## Dependencies

NeoForge is the loader requirement: tested 26.3.0.16-beta; metadata range
`[26.3.0.16-beta,26.4)`. Java 25 required. No other mod is required and no Fabric API.
Mark the original **ModernFix** project as **incompatible**: both use `modernfix`.
The only retained compile-only mod APIs are spark (Curse file 6225208) and CTM
(Curse file 5587515). Neither is bundled or verified for 26.3; do not recommend those
old artifacts for a 26.3 instance. Their conditional integrations and inherited
CoFH/Refined Storage/Cyclic targets remain untested. Remove none of the disclosures
merely because an optional integration is inactive. Determine exact optional
version relationships after testing a compatible build. JEI and other unused
compile dependencies and the stale JEI minimum-version entry have been removed.
The Fabric Loader dependency belongs only to the annotation processor, not runtime.

## Contains Derivative Content: YES

Suggested disclosure text:

> Unofficial fork of ModernFix by embeddedt and contributors: https://github.com/embeddedt/ModernFix. Based on branch 26.1 at 1111ec6fd21a8d13e9b2fb468db3d000b951b6b3, under LGPL-3.0-or-later. Reuses the upstream codebase, configuration system (itself derived from Sodium), resources and build infrastructure. MeridianFix changes concern 26.3 porting, ZIP index reliability, configuration writes and executor lifecycle. Full notices and change details are supplied in ATTRIBUTION.md and SOURCE_CHANGES.md. Not endorsed by upstream.

## Contains AI-generated content: YES

Select **AI-generated code** and **AI-generated text**. Do not select AI-generated
assets or AI functionality for this work: no such assets were generated, and the
mod does not call generative AI in-game.

Suggested disclosure text:

> Codex generated MeridianFix's new implementation changes, regression tests and project-page text. Upstream ConcentricRingsStructurePlacementMixin already credits embeddedt and GPT-5.3-Codex; that notice remains. No complete upstream AI provenance audit was performed. No AI imagery or generative-AI runtime feature was added.

**Publication blocker:** Modrinth assesses the additions to a fork separately.
Those additions here are principally AI output. Disclosing them does not establish
eligibility for public publication. Obtain an explicit determination from Modrinth
or develop qualifying substantive human-authored work before public submission.
Unlisted availability is also not guaranteed. Do not hide or relabel this provenance.

## Other disclosures

- **Contains Telemetry: YES; consent model: Opt-in.** Inherited spark profiling can
  upload recorded profile data to the configured spark/Bytebin backend after the
  user enables profiling and `-Dmodernfix.allowSparkProfiling=true`. Profiling is
  disabled by default; `-Dmodernfix.profileSaveToFile=true` writes locally instead.
  Profiles include thread samples and may include environment metadata; no anonymity
  promise. MANUAL: inspect the exact spark build/backend and its privacy policy
  before enabling or advertising this untested optional integration.
- Contains Advertisements: **NO for this snapshot**. Historical YourKit acknowledgement
  is preserved in upstream documentation, not presented as a new sponsorship.
- Contains Paid Features: **NO**. No paid gate added or identified.
- AI functionality: **NO**. AI-generated assets: **NO for known included content**;
  no new artwork generated. MANUAL: verify any subsequently added assets' provenance.
- External System Interactions: **NO beyond expected config/profile output and
  user-triggered browser links identified in this review**. No new external control
  added. MANUAL: reassess integrated dependencies before changing this setting.
- Photosensitivity warning: no designed flashing feature identified or added;
  **NO for this snapshot**, reassess any added media/render feature.
- MANUAL: compare current upstream disclosures and the final integrated dependencies.
- Keep the explicit unofficial identity and avoid unsupported benchmarks/compatibility.

Reviewed 2026-09-25 against:
[Content Rules](https://modrinth.com/legal/rules),
[Content Disclosures](https://support.modrinth.com/en/articles/16567675-content-disclosures),
[Disclosure and Usage of AI](https://support.modrinth.com/en/articles/16551575-disclosure-and-usage-of-ai).

### Inherited AI attribution

The upstream `ConcentricRingsStructurePlacementMixin` already explicitly credits
`embeddedt, GPT-5.3-Codex` in its author notice. That notice is preserved. The AI-code
disclosure therefore covers both Codex's new fork changes and this explicitly
identified inherited contribution; no claim is made that all upstream code is free
of AI involvement. A complete upstream provenance audit has not been performed.
