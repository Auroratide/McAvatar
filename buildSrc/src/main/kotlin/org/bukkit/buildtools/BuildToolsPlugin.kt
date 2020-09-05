package org.bukkit.buildtools

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Exec
import org.gradle.kotlin.dsl.creating
import org.gradle.kotlin.dsl.*

class BuildToolsPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        extensions.create("buildTools", BuildToolsExtension::class.java, target)

        repositories {
            ivy {
                name = "Spigot BuildTools"
                url = uri("https://hub.spigotmc.org/jenkins/job/BuildTools")

                patternLayout {
                    artifact("[revision]/artifact/target/[module].[ext]")
                }

                content {
                    includeModule("org.bukkit", "BuildTools")
                }

                metadataSources {
                    artifact()
                }
            }
        }

        val buildToolsCreation by configurations.creating

        dependencies {
            buildToolsCreation("org.bukkit", "BuildTools", version = "lastSuccessfulBuild", ext = "jar")
        }

        tasks.register<Copy>("spigotExtract") {
            from(buildToolsCreation.asPath)
            into(file(buildTools.location))
            rename { "BuildTools.jar" }
        }

        tasks.register<Exec>("spigotBuild") {
            requireNotNull(buildTools.minecraftVersion)
            outputs.file("${buildTools.location}/spigot-${buildTools.minecraftVersion}.jar")

            dependsOn("spigotExtract")
            workingDir = file(buildTools.location)
            commandLine = listOf("java", "-jar", "BuildTools.jar", "--rev", buildTools.minecraftVersion)
        }

        tasks.register<Exec>("spigotRun") {
            dependsOn("spigotBuild")
            workingDir = file(buildTools.location)
            commandLine = listOf("java", "-Xms1G", "-Xmx${buildTools.maxMem}", "-jar", "spigot-${buildTools.minecraftVersion}.jar", "nogui")
        }
    }
}