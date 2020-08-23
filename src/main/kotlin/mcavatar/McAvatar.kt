package mcavatar

import mcavatar.minecraft.EnumPlayerDigType
import mcavatar.minecraft.PacketPlayInBlockDig
import mcavatar.minecraft.digType
import org.bukkit.plugin.java.JavaPlugin

class McAvatar : JavaPlugin() {
    override fun onEnable() {
        logger.info("onEnable is called!")
        val scheduler = Scheduler(this, server.scheduler)
//        server.pluginManager.registerEvents(SomeListener(), this)
        server.pluginManager.registerEvents(DigListener(scheduler), this)
        server.pluginManager.registerEvents(PacketListener {
            inbound<PacketPlayInBlockDig> {
                if (digType() == EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
                    logger.info("${it.name} has stopped destroying a block!")
                }
            }
        }, this)
    }

    override fun onDisable() {
        logger.info("onDisable is called!")
    }
}