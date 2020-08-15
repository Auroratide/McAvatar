import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import java.net.URI

plugins {
    java
    kotlin("jvm") version "1.3.72"
    idea
}

group = "com.auroratide"
version = "1.0-SNAPSHOT"

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
    implementation("org.spigotmc:spigot-api:1.16.2-R0.1-SNAPSHOT")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
}
