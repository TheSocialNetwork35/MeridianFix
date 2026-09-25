# Build from corresponding source

Use the complete source ZIP or exact source revision in RELEASE_PROVENANCE.txt.
Install Java 25, set JAVA_HOME and run `./gradlew --no-daemon build`.
The ZIP includes annotations, annotation processor, wrapper, scripts, resources,
tests and notices. The sources JAR alone is not the complete multi-project source.
Only install the runtime JAR in mods/. Minecraft and loader binaries are downloaded
by the build and not included in the source bundle. Modified builds may replace this
JAR under the included licenses; preserve notices and corresponding-source availability.

Pinned toolchain: ForgeGradle 7.0.29, Gradle 9.7.1, Forge 66.0.3.

Mixin checks (each runs server then client, exiting before gameplay):

```sh
./gradlew --no-daemon runServer runClient -PreforgedAudit=true
./gradlew --no-daemon runServer runClient -PreforgedAudit=true -PreforgedFeatureLevel=BETA
```

The standard build runs 12 JVM component tests. Audit flags are for development
verification only. Do not enable early Bootstrap mixin auditing before Forge's
registry/mod setup. No server EULA acceptance is needed for this audit harness.
