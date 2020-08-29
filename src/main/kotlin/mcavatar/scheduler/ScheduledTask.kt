package mcavatar.scheduler

import org.bukkit.scheduler.BukkitTask

class ScheduledTask(private val task: BukkitTask) : Task {
    override val cancelled: Boolean
        get() = task.isCancelled

    override fun cancel() {
        task.cancel()
    }
}