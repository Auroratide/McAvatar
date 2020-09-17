package mcavatar.bukkit.block

import mcavatar.PacketSender
import mcavatar.math.Ratio
import mcavatar.minecraft.BlockBreakAnimation
import mcavatar.minecraft.Packet
import org.bukkit.block.Block
import org.bukkit.entity.Player

fun Block.showBreakage(ratio: Ratio, player: Player) =
    PacketSender().send(player, Packet.BlockBreakAnimation(player, this, ratio.numberInRange(0..9)))

val Block.center get() =
    location.add(0.5, 0.5, 0.5)