package mcavatar.permissions

import mcavatar.bukkit.command.CommandRunner
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class AlterBending(private val permissions: Permissions, sender: CommandSender, args: Array<out String>) : CommandRunner(sender, args) {
    private val action by enum<Action>(0)
    private val player by player(1)

    override fun onCommand(): Boolean {
        when (action) {
            Action.Assign -> assign()
            Action.Revoke -> revoke()
            Action.View -> view()
        }

        return true
    }

    class Executor(private val permissions: Permissions) : CommandExecutor {
        override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>) =
            AlterBending(permissions, sender, args).execute()
    }

    private fun assign() {
        val element by enum<Bending>(2)
        permissions.modify(player) {
            assign(element.permission)
        }

        sender.sendMessage("${ChatColor.BLUE}Assigned ${element.name}bending to ${player.name}")
    }

    private fun revoke() {
        val element by enum<Bending>(2)
        permissions.modify(player) {
            revoke(element.permission)
        }

        sender.sendMessage("${ChatColor.BLUE}Revoked ${element.name}bending from ${player.name}")
    }

    private fun view() {
        val abilities = permissions.get(player).joinToString(", ") { when(it) {
            Permissions.Cli.bending -> "Worldbending"
            else -> "${it.name.capitalize()}bending"
        } }

        sender.sendMessage("${ChatColor.BLUE}${player.name} can use: ${ChatColor.WHITE}$abilities")
    }

    companion object {
        const val command = "bending"
    }
}

private enum class Action {
    Assign, Revoke, View
}
