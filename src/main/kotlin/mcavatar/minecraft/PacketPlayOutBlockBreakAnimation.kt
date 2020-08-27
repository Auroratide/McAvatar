package mcavatar.minecraft

import net.minecraft.server.v1_16_R1.BlockPosition
import org.bukkit.block.Block
import org.bukkit.entity.Player

typealias PacketPlayOutBlockBreakAnimation = net.minecraft.server.v1_16_R1.PacketPlayOutBlockBreakAnimation

data class Breakage(val value: Int)

fun Packet.BlockBreakAnimation(player: Player, block: Block, breakage: Breakage) =
    PacketPlayOutBlockBreakAnimation(player.entityId, BlockPosition(block.x, block.y, block.z), breakage.value)