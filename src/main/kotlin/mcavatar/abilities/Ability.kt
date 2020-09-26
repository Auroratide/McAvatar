package mcavatar.abilities

import mcavatar.bukkit.titles.actionBar
import mcavatar.permissions.Bending
import mcavatar.permissions.hasBending
import org.bukkit.entity.Player
import org.bukkit.event.Event

private typealias Condition = Pair<() -> Boolean, () -> Unit>
private fun Condition.isMet() = first()
private fun Condition.onFailure() = second()

abstract class Ability(protected val player: Player, private val element: Bending) {
    private val preconditions = mutableListOf<Condition>()
    protected abstract fun preconditions()
    protected abstract fun action()

    fun execute(): Boolean {
        trigger { player.hasBending(element) }
        preconditions()

        val failedPrecondition = preconditions.find { !it.isMet() }
        return if (failedPrecondition == null) {
            action()
            true
        } else {
            failedPrecondition.onFailure()
            false
        }
    }

    protected fun trigger(predicate: () -> Boolean) {
        preconditions.add(Pair(predicate, {}))
    }

    protected fun requirement(warning: String, predicate: () -> Boolean) {
        preconditions.add(Pair(predicate, {
            player.actionBar.warn(warning)
        }))
    }
}