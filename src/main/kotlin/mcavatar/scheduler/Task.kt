package mcavatar.scheduler

interface Task {
    val cancelled: Boolean
    fun cancel()

    operator fun plus(other: Task): Task =
        CompositeTask(this, other)
}