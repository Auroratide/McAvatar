package mcavatar.commands

import mcavatar.logger
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.plugin.Plugin

class Bending(private val plugin: Plugin) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        logger().info(args.joinToString(", "))
        val action = Action.valueOf(args[0])
        val player = sender.server.getPlayer(args[1])!!

        val attachment = player.addAttachment(plugin)
        attachment.setPermission("mcavatar.abilities.earth", true)

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