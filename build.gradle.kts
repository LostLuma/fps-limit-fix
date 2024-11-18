plugins {
    alias(libs.plugins.fabric.loom)
    alias(libs.plugins.ploceus)
}

group = "net.lostluma"
version = project.property("mod_version").toString()

base {
    archivesName = "fps-limit-fix"
}

dependencies {
    minecraft(libs.minecraft)
    modImplementation(libs.fabric.loader)

    mappings("net.ornithemc:feather:b1.7.3-client+build.15:v2")
}

ploceus {
    setGeneration(1)
    clientOnlyMappings()
    disableLibraryUpgrades()
}

loom {
    clientOnlyMinecraftJar()
}

tasks.withType<Jar> {
    from("LICENSE")
}

java {
    withSourcesJar()

    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"

    javaCompiler = javaToolchains.compilerFor {
        languageVersion = JavaLanguageVersion.of(8)
    }
}

tasks.withType<ProcessResources> {
    inputs.property("version", project.property("mod_version"))

    filesMatching("fabric.mod.json") {
        expand(inputs.properties)
    }
}
