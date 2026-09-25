# Publication checklist — checked 2026-09-25

**Status: buildable development alpha, NOT cleared for public Modrinth publication.**
No Modrinth upload, submission or moderation approval has occurred.

## Rules comparison

| Area | Result | Required action |
|---|---|---|
| Unofficial identity | PASS | Keep the name, fork statement and upstream links. |
| Meaningful changes | IMPLEMENTED; MANUAL REVIEW | Port plus five reliability/correctness areas are documented in SOURCE_CHANGES.md. Modrinth judges sufficient divergence. |
| Licenses and credits | PACKAGED; MANUAL FINAL CHECK | Preserve LICENSE, third-party notices, corresponding source and source URL together with binary. |
| Honest functionality | PASS within stated scope | Do not replace limited validation with untested performance or compatibility claims. |
| AI disclosure | PREPARED | Select AI-generated code and text; preserve inherited credit. |
| Public eligibility of AI fork additions | **BLOCKED** | New contribution is predominantly Codex-generated. Obtain Modrinth's explicit determination or develop qualifying substantive human-authored content. Testing/review or disclosure alone does not change provenance. |
| AI artwork | PASS | None generated or supplied for upload. Do not use AI-generated/edited branding or gallery images. |
| Derivative disclosure | PREPARED | Select YES and enter the supplied attribution. |
| Telemetry disclosure | PREPARED; MANUAL VERIFY | Inherited optional spark uploads require YES / opt-in; confirm backend/privacy details if enabled. |
| Version/dependencies | MANUAL CHECK | Select exactly 26.3 and Forge, Alpha; mark upstream ModernFix incompatible. Do not add untested integrations as requirements. |
| Page accessibility | PASS | English text/Markdown, no image-only explanation. |
| Name/metadata/links | MANUAL CHECK | Verify slug availability, source visibility and issue link before submission. |

## Technical checks before any distribution advertised for ordinary play

- [x] Build distributable and corresponding sources with Java 25.
- [x] Pass 12 JVM component regression tests.
- [x] Pass stable and beta Mixin audits on client and dedicated server.
- [x] Keep original LGPL/GPL text and source notices; include Apache-2.0 notice/license.
- [ ] MANUAL: install final JAR in clean 26.3/Forge instances on client and server.
- [ ] MANUAL: create disposable worlds, generate terrain/strongholds, save/reopen and join
      from a client; test disconnect/reconnect and resource reloads.
- [ ] MANUAL: test base/overlay resource packs with beta options and incompatible packs;
      confirm fallbacks and review other enabled beta options.
- [ ] MANUAL: test desired third-party mods individually; record exact versions. Disabled
      and EXPERIMENTAL features are outside current test coverage.
- [ ] MANUAL: check Windows/Linux and other GPU backends if claiming those environments.
- [ ] MANUAL: read warning/limitations in TEST_REPORT.md; decide whether alpha distribution
      is appropriate. No performance claims without new measurements.

## Upload preparation after the blockers are resolved

- [ ] MANUAL DECISION: resolve public AI eligibility before public submission; unlisted
      distribution is not an automatic exemption or guaranteed approval.
- [ ] MANUAL: recheck current rules and inherited upstream disclosures on upload day.
- [ ] MANUAL: verify ModernFix Reforged name/slug, account ownership and support responsibilities.
- [ ] MANUAL: use MODRINTH_SETTINGS.md and the supplied name/summary/description files;
      accurately retain derivative, AI and optional profiling disclosures.
- [ ] MANUAL: upload the runtime JAR as primary; sources JAR only as designated source
      additional file. Publish complete source ZIP/build scripts beside the binary or
      via an equally accessible exact source link. Do not treat sources JAR alone as
      the complete multi-project build source.
- [ ] MANUAL: preserve LGPL permissions for replacement/modification/reverse engineering;
      do not impose additional restrictions or claim ownership of upstream work.
- [ ] MANUAL: verify SHA256SUMS, exact source revision and access without private credentials.
- [ ] MANUAL: select only supported 26.3 Forge environment and Alpha channel; record
      tested dependency relationships in the version's dependency section.
- [ ] MANUAL: use no artwork, or independently created/permitted non-AI artwork after
      checking its rights. Do not reuse the original icon as misleading fork branding.

Sources checked: [Content Rules](https://modrinth.com/legal/rules) (updated Aug 13, 2026),
[AI policy](https://support.modrinth.com/en/articles/16551575-disclosure-and-usage-of-ai)
(updated Aug 24, 2026), and
[Content Disclosures](https://support.modrinth.com/en/articles/16567675-content-disclosures).
This checklist records preparation and open decisions; it does not assert approval.

- [ ] MANUAL: review the documented Forge-only feature exclusions and keep them on the project page.
- [ ] MANUAL: verify normal installed-JAR lifecycle beyond the audit-only bootstrap, including client/server world startup.
