package mcavatar

import mcavatar.abilities.earth.BoulderToss
import mcavatar.abilities.earth.Burrow
import mcavatar.abilities.earth.Stonewall
import mcavatar.abilities.fire.Fireproof
import mcavatar.permissions.BendingManager
import mcavatar.permissions.Permissions
import mcavatar.scheduler.Scheduler
import org.bukkit.plugin.java.JavaPlugin

class McAvatar : JavaPlugin() {
    override fun onEnable() {
        logger.info("McAvatar enabled")

        val scheduler = Scheduler(this, server.scheduler)
        val permissions = Permissions(this)

        val burrow = Burrow.Listener(scheduler)
        val stonewall = Stonewall.Listener()
        val boulderToss = BoulderToss.Listener(scheduler)

        val fireproof = Fireproof.Listener()

        server.pluginManager.registerEvents(burrow, this)
        server.pluginManager.registerEvents(stonewall, this)
        server.pluginManager.registerEvents(boulderToss, this)
        server.pluginManager.registerEvents(fireproof, this)
        server.pluginManager.registerEvents(permissions, this)

        getCommand(BendingManager.command)?.setExecutor(BendingManager(permissions)) ?: error("Failed to find ${BendingManager.command}")

        server.pluginManager.registerEvents(PacketListener {
            inbound(burrow::cancelDig)
        }, this)
    }

    override fun onDisable() {
        logger.info("McAvatar disabled")
    }
}