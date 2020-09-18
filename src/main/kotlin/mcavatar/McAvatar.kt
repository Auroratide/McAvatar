package mcavatar

import mcavatar.abilities.earth.BoulderToss
import mcavatar.abilities.earth.Burrow
import mcavatar.abilities.earth.Stonewall
import mcavatar.abilities.fire.Fireproof
import mcavatar.commands.Bending
import mcavatar.scheduler.Scheduler
import org.bukkit.plugin.java.JavaPlugin

class McAvatar : JavaPlugin() {
    override fun onEnable() {
        logger.info("McAvatar enabled")

        val scheduler = Scheduler(this, server.scheduler)
        val burrow = Burrow.Listener(scheduler)
        val stonewall = Stonewall.Listener()
        val boulderToss = BoulderToss.Listener(scheduler)

        val fireproof = Fireproof.Listener()

        server.pluginManager.registerEvents(burrow, this)
        server.pluginManager.registerEvents(stonewall, this)
        server.pluginManager.registerEvents(boulderToss, this)
        server.pluginManager.registerEvents(fireproof, this)

        getCommand(Bending.command)?.setExecutor(Bending(this)) ?: error("Failed to find ${Bending.command}")

        server.pluginManager.registerEvents(PacketListener {
            inbound(burrow::cancelDig)
        }, this)
    }

    override fun onDisable() {
        logger.info("McAvatar disabled")
    }
}