# Corresponding source and rebuilding

Use the complete `meridianfix-0.1.0-alpha.1+mc26.3-source.zip`, or the fork revision
recorded in RELEASE_PROVENANCE.txt. The sources JAR is a convenience; the ZIP also
contains annotations, annotation processor, Gradle wrapper/build scripts, resources,
tests and license files required for the multi-project build.

Install JDK 25, extract into a local directory, set JAVA_HOME, then run:

```sh
chmod +x gradlew
./gradlew --no-daemon build
./gradlew --no-daemon runAuditServer runAuditServerBeta
./gradlew --no-daemon runAuditClient runAuditClientBeta
```

Client audits require a graphical environment. Gradle downloads the pinned loader,
game and build dependencies; they are not redistributed in the source ZIP. Runtime
artifacts are in build/libs. Only install the JAR without `-sources` in mods/.
You may modify and rebuild under the included licenses and replace the mod JAR;
there is no fork-imposed signing or replacement restriction. Keep original notices
and supply corresponding source when distributing your modified version.
