package org.bukkit.buildtools

import org.gradle.api.Project
import org.gradle.kotlin.dsl.the

open class BuildToolsExtension(private val project: Project) {
    var minecraftVersion: String? = null
    var location: String = "spigot"
    var maxMem: String = "1G"
}

val Project.buildTools get() = the<BuildToolsExtension>()
fun Project.buildTools(configure: BuildToolsExtension.() -> Unit) = configure(buildTools)