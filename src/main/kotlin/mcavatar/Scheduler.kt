package mcavatar

import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitScheduler
import java.time.Duration

const val MILLIS_IN_TICK = 50L
val Int.ticks get() = Duration.ofMillis(this * MILLIS_IN_TICK)
val Long.ticks get() = Duration.ofMillis(this * MILLIS_IN_TICK)
fun Duration.toTicks() = toMillis() / MILLIS_IN_TICK

class Scheduler(private val plugin: Plugin, private val scheduler: BukkitScheduler) {
    fun runAfter(duration: Duration, task: () -> Unit) =
        scheduler.runTaskLater(plugin, task, duration.toTicks())
}