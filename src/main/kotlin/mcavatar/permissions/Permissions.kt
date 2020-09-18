package mcavatar.permissions

import mcavatar.logger
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.permissions.PermissionAttachment
import org.bukkit.plugin.Plugin
import java.io.Closeable
import java.util.*

class Permissions(private val plugin: Plugin) : Listener {
    private val logger = logger()
    private val config get() = plugin.config.getConfigurationSection("permissions") ?: plugin.config.createSection("permissions")
    private val attachments = mutableMapOf<UUID, PermissionAttachment>()

    fun modify(player: Player, config: PermissionModifier.() -> Unit) {
        if (!attachments.containsKey(player.uniqueId)) {
            logger.warning("Upon modification, player ${player.name} (${player.uniqueId}) did not have a permission attachment; adding it.")
            attachments[player.uniqueId] = player.addAttachment(plugin)
        }

        PermissionModifier(player, attachments[player.uniqueId]!!).use(config)
    }

    fun get(player: Player) =
        player.effectivePermissions
            .filter { it.value }
            .map { it.permission }
            .mapNotNull { raw ->
                permissions.find { raw == it.qualifiedName }
            }

    object Abilities {
        val earth = Bending.Earth.permission
        val fire = Bending.Fire.permission
        val water = Bending.Water.permission
        val air = Bending.Air.permission
    }

    object Cli {
        val bending = Permission("cli", "bending")
    }

    val permissions = listOf(Abilities.earth, Abilities.fire, Abilities.water, Abilities.air, Cli.bending)

    @EventHandler fun onPlayerJoin(e: PlayerJoinEvent) = with(e) {
        if (attachments.containsKey(player.uniqueId)) {
            logger.warning("Upon joining, player ${player.name} (${player.uniqueId}) already had a permission attachment; removing it.")
            attachments[player.uniqueId]?.remove()
        }

        attachments[player.uniqueId] = player.addAttachment(plugin).also { attachment ->
            config.getStringList(player.permissionConfigKey).forEach { permission ->
                logger.info("Applying $permission to ${player.name}")
                attachment.setPermission(permission, true)
            }
        }
    }

    @EventHandler fun onPlayerQuit(e: PlayerQuitEvent) = with(e) {
        if (!attachments.containsKey(player.uniqueId)) {
            logger.warning("Upon quitting, player ${player.name} (${player.uniqueId}) did not have a permission attachment.")
        }

        attachments[player.uniqueId]?.remove()?.also {
            attachments.remove(player.uniqueId)
        }
    }

    inner class PermissionModifier(private val player: Player, private val attachment: PermissionAttachment) : Closeable {
        fun assign(permission: Permission): PermissionModifier {
            logger.info("Assigning ${permission.qualifiedName} to ${player.name} (${player.uniqueId})")
            attachment.setPermission(permission.qualifiedName, true)
            return this
        }

        fun revoke(permission: Permission): PermissionModifier {
            logger.info("Revoking ${permission.qualifiedName} from ${player.name} (${player.uniqueId})")
            attachment.unsetPermission(permission.qualifiedName)
            return this
        }

        override fun close() {
            logger.info("Start: Saving permissions to config")

            config.set(player.permissionConfigKey, attachment.permissions.filter { it.value }.keys.toTypedArray())
            plugin.saveConfig()
            plugin.reloadConfig()

            logger.info("Done: Saving permissions to config")
        }
    }

    private val Player.permissionConfigKey get() = uniqueId.toString()
}
