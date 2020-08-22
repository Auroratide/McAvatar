package mcavatar

import mcavatar.minecraft.EnumPlayerDigType
import mcavatar.minecraft.PacketPlayInBlockDig
import mcavatar.minecraft.digType
import org.bukkit.plugin.java.JavaPlugin

class McAvatar : JavaPlugin() {
    override fun onEnable() {
        logger.info("onEnable is called!")
        server.pluginManager.registerEvents(SomeListener(), this)
        server.pluginManager.registerEvents(PacketListener {
            read<PacketPlayInBlockDig> {
                if (digType() == EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
                    logger.info("Player has stopped destroying a block!")
                }
            }
        }, this)
    }

    override fun onDisable() {
        logger.info("onDisable is called!")
    }
}