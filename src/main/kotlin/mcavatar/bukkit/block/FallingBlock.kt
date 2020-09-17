package mcavatar.bukkit.block

import org.bukkit.Particle
import org.bukkit.entity.FallingBlock

fun FallingBlock.particlize(numberOfParticles: Int) =
    world.spawnParticle(Particle.BLOCK_DUST, location, numberOfParticles, blockData)