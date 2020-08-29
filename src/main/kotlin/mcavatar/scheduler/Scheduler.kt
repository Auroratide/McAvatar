package mcavatar.scheduler

import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitScheduler
import java.time.Duration

class Scheduler(private val plugin: Plugin, private val scheduler: BukkitScheduler) {
    fun runAfter(duration: Duration, task: () -> Unit): Task =
        ScheduledTask(scheduler.runTaskLater(plugin, task, duration.toTicks()))

    fun onEachTickFor(duration: Duration, task: () -> Unit) {
        scheduler.scheduleSyncRepeatingTask(plugin, task, 0, 1).also { taskId ->
            runAfter(duration) {
                scheduler.cancelTask(taskId)
            }
        }
    }
}
