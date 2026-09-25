# Developing MeridianFix

This is an unofficial ModernFix fork. Use Java 25 and the checked-in Gradle wrapper.
Run `./gradlew --no-daemon build` to compile, run regression tests, and package artifacts.
Run `./gradlew runAuditClient` separately to audit client mixins; a successful compile
alone is not proof that mixin targets apply. Dedicated-server/world/reload testing
is also required before claiming 26.3 support.

Do not publish under the upstream ModernFix project IDs. No automated publishing
is configured. Read release/PUBLISH_CHECKLIST.md before distributing a release.
Keep original notices and attribute all modified code. New contributions use the
same LGPL-3.0-or-later license unless an existing file requires otherwise.
Disclose AI contributions accurately; do not attribute generated code to upstream.
The original contributor guide is retained in doc/upstream/CONTRIBUTING.md.
