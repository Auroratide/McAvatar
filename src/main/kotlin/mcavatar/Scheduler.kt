package mcavatar

import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitScheduler
import java.time.Duration

const val TICKS_IN_SECOND = 20L
val Int.ticks get() = Duration.ofMillis(this * (1000 / TICKS_IN_SECOND))
val Long.ticks get() = Duration.ofMillis(this * (1000 / TICKS_IN_SECOND))
fun Duration.toTicks() = toSeconds() * TICKS_IN_SECOND

class Scheduler(private val plugin: Plugin, private val scheduler: BukkitScheduler) {
    fun runAfter(duration: Duration, task: () -> Unit) {
        scheduler.runTaskLater(plugin, task, duration.toTicks())
    }
}