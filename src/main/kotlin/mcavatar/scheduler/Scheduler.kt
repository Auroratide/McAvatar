package mcavatar.scheduler

import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitScheduler
import java.time.Duration
import java.util.concurrent.atomic.AtomicInteger

class Scheduler(private val plugin: Plugin, private val scheduler: BukkitScheduler) {
    fun runAfter(duration: Duration, task: () -> Unit): Task =
        ScheduledTask(scheduler.runTaskLater(plugin, task, duration.toTicks()))

    fun onEachTickFor(duration: Duration, task: (tick: Int) -> Unit): Task {
        val tick = AtomicInteger(0)
        val repeatingTask = RepeatingTask(scheduler, scheduler.scheduleSyncRepeatingTask(plugin, {
            task(tick.getAndIncrement())
        }, 0, 1))

        val futureCancelTask = runAfter(duration) {
            scheduler.cancelTask(repeatingTask.id)
        }

        return repeatingTask + futureCancelTask
    }
}
