# MeridianFix

**Unofficial ModernFix fork, maintained separately by TheSocialNetwork35.**
Not endorsed by embeddedt, the ModernFix contributors, Mojang, or Microsoft.

Development alpha for **Minecraft 26.3 / NeoForge 26.3.0.16-beta / Java 25**.
Build, 12 tests and stable/beta client/server Mixin audits pass. Gameplay and
modpack compatibility remain unverified. See
[release/PUBLISH_CHECKLIST.md](release/PUBLISH_CHECKLIST.md) for current blockers.

Fork changes focus on resource ZIP validation, bounded-stack index traversal,
configuration replacement that preserves the previous file when writing fails,
and executor shutdown correctness. Code and regression tests are in this repository.

Original project: [embeddedt/ModernFix](https://github.com/embeddedt/ModernFix).
The upstream README, credits and acknowledgements are preserved under
[doc/upstream](doc/upstream). All original copyright notices remain in source.
The complete upstream Git history is retained. License: **LGPL-3.0-or-later**;
[LICENSE](LICENSE) contains both LGPL v3 and GPL v3 texts.

Codex generated the new fork implementation and publishing text. This disclosure
covers MeridianFix changes; inherited AI credit is retained in ATTRIBUTION.md.
Modrinth's rules assess the original additions of an AI-assisted fork separately;
this development snapshot is not cleared for public Modrinth publication.

Build with Java 25: `./gradlew --no-daemon build`.
