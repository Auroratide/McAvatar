package mcavatar

import org.bukkit.plugin.java.JavaPlugin

class McAvatar : JavaPlugin() {
    override fun onEnable() {
        logger.info("onEnable is called!")
    }

    override fun onDisable() {
        logger.info("onDisable is called!")
    }
}