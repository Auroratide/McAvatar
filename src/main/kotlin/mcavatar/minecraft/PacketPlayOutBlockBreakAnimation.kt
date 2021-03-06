package mcavatar.minecraft

import net.minecraft.server.v1_16_R2.BlockPosition
import org.bukkit.block.Block
import org.bukkit.entity.Player

typealias PacketPlayOutBlockBreakAnimation = net.minecraft.server.v1_16_R2.PacketPlayOutBlockBreakAnimation

fun Packet.BlockBreakAnimation(player: Player, block: Block, breakage: Int) =
    PacketPlayOutBlockBreakAnimation(player.entityId, BlockPosition(block.x, block.y, block.z), breakage)