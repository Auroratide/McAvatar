import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import java.net.URI
import org.bukkit.buildtools.BuildToolsPlugin
import org.bukkit.buildtools.buildTools

plugins {
    id("com.github.johnrengelman.shadow").version("6.0.0")
    java
    kotlin("jvm") version "1.4.0"
    idea
}

apply<BuildToolsPlugin>()

buildTools {
    minecraftVersion = "1.16.2"
}

group = "com.auroratide"
version = "1.0-SNAPSHOT"
val artifactName = "${project.name}-$version-all"

tasks {
    withType<Test> {
        useJUnitPlatform()
        testLogging {
            exceptionFormat = TestExceptionFormat.FULL
            events("skipped", "failed")
        }
    }
}

repositories {
    mavenCentral()
    maven {
        url = URI("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(buildTools.spigotServer)

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.22")
}

tasks.named("shadowJar") {
    dependsOn("check")
}

tasks.register<Copy>("stageJar") {
    dependsOn("shadowJar")
    from("build/libs/$artifactName.jar")
    into("${buildTools.location}/plugins")
}

tasks.named("spigotRun") {
    dependsOn("stageJar")
}