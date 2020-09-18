package mcavatar.permissions

import mcavatar.logger
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.plugin.Plugin

class BendingManager(private val permissions: Permissions) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        logger().info(args.joinToString(", "))
        val action = Action.valueOf(args[0])
        val player = sender.server.getPlayer(args[1])!!

        permissions.modify(player) {
            if (action == Action.assign)
                assign(Permissions.Abilities.earth)
            else if (action == Action.revoke)
                revoke(Permissions.Abilities.earth)
        }

        sender.sendMessage("You called $action for $player")

        return false
    }

    companion object {
        const val command = "bending"
    }
}

private enum class Action {
    assign, revoke, view
}