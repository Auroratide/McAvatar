package mcavatar.scheduler

class CompositeTask(private val first: Task, private val second: Task) : Task {
    override val cancelled: Boolean
        get() = first.cancelled && second.cancelled

    override fun cancel() {
        first.cancel()
        second.cancel()
    }
}