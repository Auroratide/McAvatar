import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import java.net.URI

plugins {
    id("com.github.johnrengelman.shadow").version("6.0.0")
    java
    kotlin("jvm") version "1.4.0"
    idea
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
//    implementation("org.spigotmc:spigot:1.16.2-R0.1-SNAPSHOT")
    // Needed for packet stuff; TODO Figure out how to make this better
    implementation(files("spigot/spigot-1.16.1.jar"))

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
    into("spigot/plugins")
}

// NOTE: Requires a spigot server already built in the spigot folder
tasks.register<Exec>("run") {
    dependsOn("stageJar")
    workingDir = file("spigot")
    commandLine = listOf("java", "-Xms1G", "-Xmx1G", "-XX:+UseConcMarkSweepGC", "-jar", "spigot-1.16.1.jar", "nogui")
}