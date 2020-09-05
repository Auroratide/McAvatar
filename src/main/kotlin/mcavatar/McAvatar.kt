package mcavatar

import mcavatar.earth.Burrow
import mcavatar.earth.Stonewall
import mcavatar.scheduler.Scheduler
import org.bukkit.plugin.java.JavaPlugin

class McAvatar : JavaPlugin() {
    override fun onEnable() {
        logger.info("onEnable is called!")
        val scheduler = Scheduler(this, server.scheduler)
        val digListener = Burrow.Listener(scheduler)
        val stonewallListener = Stonewall.Listener()

        server.pluginManager.registerEvents(digListener, this)
        server.pluginManager.registerEvents(stonewallListener, this)
        server.pluginManager.registerEvents(PacketListener {
            inbound(digListener::cancelDig)
        }, this)
    }

    override fun onDisable() {
        logger.info("onDisable is called!")
    }
}