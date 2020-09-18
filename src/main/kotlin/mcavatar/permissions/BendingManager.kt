package mcavatar.permissions

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class BendingManager(private val permissions: Permissions) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val action = Action.from(args[0])
        val player = sender.server.getPlayer(args[1])!!
        val element = args.getOrNull(2)?.let {
            Bending.valueOf(it)
        }

        when (action) {
            Action.Assign -> assign(player, element!!)
            Action.Revoke -> revoke(player, element!!)
            Action.View -> view(sender, player)
        }

        return true
    }

    private fun assign(player: Player, element: Bending) {
        permissions.modify(player) {
            assign(element.permission)
        }
    }

    private fun revoke(player: Player, element: Bending) {
        permissions.modify(player) {
            revoke(element.permission)
        }
    }

    private fun view(sender: CommandSender, player: Player) {
        val abilities = permissions.get(player).joinToString(", ") { when(it) {
            Permissions.Cli.bending -> "Worldbending"
            else -> "${it.name.capitalize()}bending"
        } }

        sender.sendMessage("${player.name} can use: $abilities")
    }

    companion object {
        const val command = "bending"
    }
}

private enum class Action {
    Assign, Revoke, View;

    companion object {
        fun from(value: String) = valueOf(value.toLowerCase().capitalize())
    }
}
