package mcavatar.bukkit.block

import mcavatar.PacketSender
import mcavatar.math.Ratio
import mcavatar.minecraft.BlockBreakAnimation
import mcavatar.minecraft.Packet
import org.bukkit.Particle
import org.bukkit.block.Block
import org.bukkit.entity.Player
import kotlin.random.Random

fun Block.showBreakage(ratio: Ratio, player: Player) =
    PacketSender().send(player, Packet.BlockBreakAnimation(player, this, ratio.numberInRange(0..9)))

val Block.center get() =
    location.clone().add(0.5, 0.5, 0.5)

fun Block.randomInnerLocation() =
    location.clone().add(Random.nextDouble(0.0, 1.0), Random.nextDouble(0.0, 1.0), Random.nextDouble(0.0, 1.0))

fun Block.particlize(numberOfParticles: Int) = repeat(numberOfParticles) {
    world.spawnParticle(Particle.BLOCK_DUST, randomInnerLocation(), 1, blockData)
}