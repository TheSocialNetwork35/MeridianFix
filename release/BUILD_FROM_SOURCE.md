# Build from corresponding source

Use the complete source ZIP or exact source revision in RELEASE_PROVENANCE.txt.
Install Java 21, set JAVA_HOME and run `./gradlew --no-daemon build`.
The ZIP includes annotations, annotation processor, wrapper, scripts, resources,
tests and notices. The sources JAR alone is not the complete multi-project source.
Only install the runtime JAR in mods/. Minecraft and loader binaries are downloaded
by the build and not included in the source bundle. Modified builds may replace this
JAR under the included licenses; preserve notices and corresponding-source availability.
