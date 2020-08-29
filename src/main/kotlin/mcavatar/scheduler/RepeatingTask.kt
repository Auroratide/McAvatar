package mcavatar.scheduler

import org.bukkit.scheduler.BukkitScheduler

class RepeatingTask(private val bukkitScheduler: BukkitScheduler, val id: Int) : Task {
    override val cancelled: Boolean
        get() = !bukkitScheduler.isCurrentlyRunning(id)

    override fun cancel() {
        bukkitScheduler.cancelTask(id)
    }
}