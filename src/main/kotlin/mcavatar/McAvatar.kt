package mcavatar

import mcavatar.abilities.earth.Burrow
import mcavatar.abilities.earth.Stonewall
import mcavatar.scheduler.Scheduler
import org.bukkit.plugin.java.JavaPlugin

class McAvatar : JavaPlugin() {
    override fun onEnable() {
        logger.info("McAvatar enabled")

        val scheduler = Scheduler(this, server.scheduler)
        val burrow = Burrow.Listener(scheduler)
        val stonewall = Stonewall.Listener()

        server.pluginManager.registerEvents(burrow, this)
        server.pluginManager.registerEvents(stonewall, this)
        server.pluginManager.registerEvents(PacketListener {
            inbound(burrow::cancelDig)
        }, this)
    }

    override fun onDisable() {
        logger.info("McAvatar disabled")
    }
}