# Version settings — development alpha, publication not cleared

- Project type: Mod; name: ModernFix Reforged.
- Minecraft version: **26.3 only**.
- Loader: **Forge only**, tested 66.0.3; Java 25.
- Release channel: **Alpha**.
- Environment: **Client and server** → **Optional on both, works best when installed on both sides**.
- Version title: contents of VERSION_TITLE.txt.
- Version number: contents of VERSION_NUMBER.txt. The loader suffix distinguishes the
  Modrinth record; the embedded version is 0.1.0-alpha.2+mc26.3.
- License: **LGPL-3.0-or-later**, with included component notices.
- Tags: optimization, utility. No measured performance gain is claimed.
- Dependencies: no other mod required; mark original ModernFix incompatible because of
  duplicate mod ID. Exact Forge loader/game requirement is in the JAR metadata.
  Optional integrations were not tested; do not advertise or require them on that basis.
- Contains Derivative Content: **YES**. Text: “Unofficial fork of ModernFix by embeddedt
  and contributors, https://github.com/embeddedt/ModernFix, LGPL-3.0-or-later. Retains
  upstream implementation and credits; adds configuration, ZIP index, executor and live
  map-view reliability fixes. See ATTRIBUTION.md and SOURCE_CHANGES.md.”
- Contains AI-generated content: **YES — code and text**. Text: “Codex generated the new
  fork changes, regression tests and publishing drafts. Existing upstream notices are
  retained. No complete upstream AI provenance audit was performed.”
- AI-generated assets: none newly created; no artwork supplied. AI runtime functionality: NO.
- Contains Telemetry: **YES / Opt-in**, for inherited optional spark profile uploads;
  describe flags/backend as in MODRINTH_DESCRIPTION.md. Verify the chosen spark build's
  backend and privacy policy before enabling it.
- Paid features: NO. New advertisements: NO. No additional external system control or
  designed flashing feature was added. Review applicable upstream disclosures manually.
- Source: exact branch/revision in RELEASE_PROVENANCE.txt; repository is
  https://github.com/TheSocialNetwork35/ModernFix-Reforged. Issues: same URL plus /issues.

Do not mark other game versions or loaders on this JAR. Public Modrinth eligibility
remains blocked by the AI-origin rule; accurate disclosure alone does not resolve it.

Known inherited AI attribution: upstream ConcentricRingsStructurePlacementMixin
credits embeddedt and GPT-5.3-Codex. Preserve this notice and disclose it alongside
the new Codex-written changes. Review Forge exclusions before describing features.
