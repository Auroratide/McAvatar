package mcavatar.bukkit.command

import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import kotlin.reflect.KProperty

abstract class CommandRunner(protected val sender: CommandSender, protected val args: Array<out String>) {
    protected abstract fun onCommand(): Boolean

    fun execute(): Boolean {
        return try {
            onCommand()
        } catch(e: InvalidCommandArgument) {
            sender.sendMessage("${ChatColor.RED}ERROR: ${e.message}")
            false
        } catch(e: MissingArgument) {
            sender.sendMessage("${ChatColor.RED}ERROR: ${e.message}")
            false
        }
    }

    protected inline fun <reified E : Enum<E>> enum(arg: Int): ArgumentDelegate<E> {
        return ArgumentDelegate(arg) { value ->
            try {
                enumValueOf(value.toLowerCase().capitalize())
            } catch (e: IllegalArgumentException) {
                invalid("Must be one of ${enumValues<E>().joinToString(", ") { it.name.toLowerCase() }}")
            }
        }
    }

    protected fun player(arg: Int): ArgumentDelegate<Player> {
        return ArgumentDelegate(arg) {
            sender.server.getPlayerExact(it) ?: invalid("Cannot find player: $it")
        }
    }

    class InvalidCommandArgument(context: ArgumentContext, details: String) : RuntimeException(
        "Invalid value for $context\n  $details"
    )
    class MissingArgument(context: ArgumentContext) : RuntimeException(
        "Missing value for $context"
    )

    inner class ArgumentDelegate<T>(private val index: Int, private val f: ArgumentContext.(String) -> T) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): T = with(ArgumentContext(property.name, index)) {
            if (args.size <= index)
                throw MissingArgument(this)

            return f(args[index])
        }
    }

    class ArgumentContext(val name: String, val index: Int) {
        fun invalid(details: String): Nothing =
            throw InvalidCommandArgument(this, details)

        override fun toString() = "$name (argument ${index + 1})"
    }
}
