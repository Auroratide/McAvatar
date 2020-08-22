package mcavatar

import org.bukkit.plugin.java.JavaPlugin

class McAvatar : JavaPlugin() {
    override fun onEnable() {
        logger.info("onEnable is called!")
        server.pluginManager.registerEvents(SomeListener(), this)
        server.pluginManager.registerEvents(PacketListener {
            read<Any> {
                logger.info(toString())
            }
        }, this)
    }

    override fun onDisable() {
        logger.info("onDisable is called!")
    }
}