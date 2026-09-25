plugins {
    id("net.neoforged.moddev") version("2.0.147")
}

val minecraft_version = rootProject.properties["minecraft_version"].toString()

group = "org.embeddedt"

version = "0.1.0-alpha.1+mc26.3"

base.archivesName = "meridianfix-neoforge"

neoForge {
    enable {
        version = rootProject.properties["forge_version"].toString()
        isDisableRecompilation = System.getenv("CI") == "true"
    }

    rootProject.properties["parchment_version"]?.let { parchmentVer ->
        parchment {
            minecraftVersion = rootProject.properties["parchment_mc_version"].toString()
            mappingsVersion = parchmentVer.toString()
        }
    }

    runs {
        configureEach {
            systemProperty("modernfix.auditMixinsAtStart", "true")
        }
        create("client") {
            client()
        }
        create("server") {
            server()
        }
        create("auditServerBeta") {
            server()
            jvmArguments.addAll("-Dmodernfix.auditAndExit=true", "-Dmodernfix.config.stability_level=BETA")
        }
        create("auditClientBeta") {
            client()
            jvmArguments.addAll("-Dmodernfix.auditAndExit=true", "-Dmodernfix.config.stability_level=BETA", "-Djava.awt.headless=true")
        }
        create("auditServer") {
            server()
            jvmArguments.add("-Dmodernfix.auditAndExit=true")
        }
        create("auditClient") {
            client()
            jvmArguments.addAll("-Dmodernfix.auditAndExit=true", "-Djava.awt.headless=true")
        }
    }

    mods {
        create("modernfix") {
            sourceSet(sourceSets.main.get())
        }
    }
}

tasks.named<Jar>("jar") {
    manifest.attributes(mapOf(
        "Specification-Version" to "1",
        "Implementation-Title" to "MeridianFix (unofficial ModernFix fork)",
        "Implementation-Version" to version
    ))
}

java {
    val curSourceCompatLevel = JavaVersion.VERSION_25
    sourceCompatibility = curSourceCompatLevel
    targetCompatibility = curSourceCompatLevel
}

repositories {
    exclusiveContent {
        forRepository {
            maven("https://cursemaven.com")
        }
        filter {
            includeGroup("curse.maven")
        }
    }
}

val embed by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
    isTransitive = true
}

dependencies {
    implementation(project(":annotations"))
    embed(project(":annotations"))
    annotationProcessor(project(path = ":annotation-processor", configuration = "shadow"))

    compileOnly("curse.maven:spark-361579:${rootProject.properties["spark_version"].toString()}")
    compileOnly("curse.maven:ctm-267602:${rootProject.properties["ctm_version"].toString()}")
}

tasks.named<Jar>("jar") {
    from(embed.map { if (it.isDirectory) it else zipTree(it) })
}

val checkDanglingMixinPackageInfo by tasks.registering {
    val mixinDir = file("src/main/java/org/embeddedt/modernfix/common/mixin")
    inputs.dir(mixinDir)
    doLast {
        val dangling = mutableListOf<File>()
        mixinDir.walkTopDown()
            .filter { it.name == "package-info.java" }
            .forEach { pkgInfo ->
                val dir = pkgInfo.parentFile
                val hasOtherJava = dir.listFiles { f -> f.name != "package-info.java" && f.extension == "java" }?.isNotEmpty() == true
                val hasSubpackage = dir.listFiles { f -> f.isDirectory }?.isNotEmpty() == true
                if (!hasOtherJava && !hasSubpackage) {
                    dangling += pkgInfo
                }
            }
        if (dangling.isNotEmpty()) {
            throw GradleException(
                "Dangling package-info.java files found (no sibling classes or subpackages):\n" +
                dangling.joinToString("\n") { "  ${it.relativeTo(projectDir)}" }
            )
        }
    }
}

// For the AP
tasks.withType<JavaCompile>().configureEach {
    if (!name.lowercase().contains("test")) {
        dependsOn(checkDanglingMixinPackageInfo)
        options.compilerArgs.addAll(
            listOf(
                "-ArootProject.name=${rootProject.name}",
                "-Aproject.name=${project.name}"
            )
        )
    }
    // Show more errors when porting
    options.compilerArgs.addAll(listOf("-Xmaxerrs", "1000"))
}

sourceSets {
    main {
        resources.srcDir(
            layout.buildDirectory.dir("generated/sources/annotationProcessor/java/main/resources")
        )
    }
}

tasks.named<ProcessResources>("processResources") {
    dependsOn(tasks.named("compileJava"))

    inputs.property("version", project.version)

    filesMatching("META-INF/neoforge.mods.toml") {
        expand("version" to project.version)
    }
}

// MeridianFix modification, 2026-09-25: reproducible artifacts with license/source bundles.
java { withSourcesJar() }
tasks.withType<AbstractArchiveTask>().configureEach {
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true
}
tasks.named<Jar>("jar") {
    from("LICENSE", "ATTRIBUTION.md", "THIRD_PARTY_NOTICES.md")
    from("licenses") { into("licenses") }
}
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.13.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
tasks.test { useJUnitPlatform() }

tasks.named<Jar>("sourcesJar") {
    dependsOn(tasks.named("compileJava"))
    from("LICENSE", "ATTRIBUTION.md", "THIRD_PARTY_NOTICES.md")
    from("licenses") { into("licenses") }
}

neoForge {
    unitTest {
        enable()
        testedMod = mods.getByName("modernfix")
    }
}
