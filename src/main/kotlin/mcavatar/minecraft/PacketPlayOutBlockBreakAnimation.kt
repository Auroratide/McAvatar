package mcavatar.minecraft

import net.minecraft.core.BlockPosition
import org.bukkit.block.Block
import org.bukkit.entity.Player

typealias PacketPlayOutBlockBreakAnimation = net.minecraft.network.protocol.game.PacketPlayOutBlockBreakAnimation
fun Packet.BlockBreakAnimation(player: Player, block: Block, breakage: Int) =
    PacketPlayOutBlockBreakAnimation(player.entityId, BlockPosition(block.x, block.y, block.z), breakage)