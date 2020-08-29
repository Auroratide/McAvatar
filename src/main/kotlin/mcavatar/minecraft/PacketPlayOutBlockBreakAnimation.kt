package mcavatar.minecraft

import mcavatar.math.Ratio
import net.minecraft.server.v1_16_R1.BlockPosition
import org.bukkit.block.Block
import org.bukkit.entity.Player

typealias PacketPlayOutBlockBreakAnimation = net.minecraft.server.v1_16_R1.PacketPlayOutBlockBreakAnimation

data class Breakage(val value: Int) {
    companion object {
        fun from(ratio: Ratio) = Breakage(ratio.numberInRange(0..9))
    }
}

fun Packet.BlockBreakAnimation(player: Player, block: Block, breakage: Breakage) =
    PacketPlayOutBlockBreakAnimation(player.entityId, BlockPosition(block.x, block.y, block.z), breakage.value)