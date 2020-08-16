package mcavatar

import net.minecraft.server.v1_16_R1.*
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDamageEvent

class SomeListener : Listener {
    @EventHandler fun convert(e: BlockDamageEvent) {
        if(e.itemInHand.type == Material.AIR) {
            e.player.sendMessage("Start send")
            val connection = (e.player as CraftPlayer).handle.playerConnection
            val title = ChatColor.translateAlternateColorCodes('&', "Hello &lThere&r")
            val text = IChatBaseComponent.ChatSerializer.a("""{"text": "$title"}""")
            val packet = PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, text, 1, 80, 1)
            connection.sendPacket(packet)
            e.player.sendMessage("End send")
        }
    }
}