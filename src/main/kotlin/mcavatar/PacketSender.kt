package mcavatar

import mcavatar.bukkit.handle
import mcavatar.bukkit.playerConnection
import mcavatar.minecraft.OutboundPacket
import org.bukkit.entity.Player

class PacketSender {
    fun send(player: Player, packet: OutboundPacket) {
        player.handle.playerConnection.sendPacket(packet)
    }
}