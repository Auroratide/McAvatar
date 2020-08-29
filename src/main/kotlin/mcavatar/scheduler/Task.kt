package mcavatar.scheduler

interface Task {
    val cancelled: Boolean
    fun cancel()
}