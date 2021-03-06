package mcavatar

import mcavatar.abilities.air.Windwalker
import mcavatar.abilities.air.Windwall
import mcavatar.abilities.earth.BoulderToss
import mcavatar.abilities.earth.Burrow
import mcavatar.abilities.earth.Stonewall
import mcavatar.abilities.fire.Fireproof
import mcavatar.abilities.fire.Firesweep
import mcavatar.permissions.AlterBending
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

        val fireproof = Fireproof.Listener(scheduler)
        val firesweep = Firesweep.Listener()

        val windwalker = Windwalker.Listener()
        val windwall = Windwall.Listener(scheduler)

        server.pluginManager.registerEvents(burrow, this)
        server.pluginManager.registerEvents(stonewall, this)
        server.pluginManager.registerEvents(boulderToss, this)

        server.pluginManager.registerEvents(fireproof, this)
        server.pluginManager.registerEvents(firesweep, this)

        server.pluginManager.registerEvents(windwalker, this)
        server.pluginManager.registerEvents(windwall, this)

        server.pluginManager.registerEvents(permissions, this)

        getCommand(AlterBending.command)?.setExecutor(AlterBending.Executor(permissions)) ?: error("Failed to find ${AlterBending.command}")

        server.pluginManager.registerEvents(PacketListener {
            inbound(burrow::cancelDig)
        }, this)
    }

    override fun onDisable() {
        logger.info("McAvatar disabled")
    }
}