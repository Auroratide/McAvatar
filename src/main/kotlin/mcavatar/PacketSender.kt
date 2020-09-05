package mcavatar

import mcavatar.minecraft.OutboundPacket
import mcavatar.minecraft.PacketPlayOutBlockBreakAnimation
import net.minecraft.server.v1_16_R2.BlockPosition
import org.bukkit.block.Block
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer
import org.bukkit.entity.Player

class PacketSender {
    fun send(player: Player, packet: OutboundPacket) {
        (player as CraftPlayer).handle.playerConnection.sendPacket(packet)
    }
}