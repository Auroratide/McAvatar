package mcavatar.material

import mcavatar.logger
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.block.Block

// Future reference in case this gets out of hand:
// https://www.spigotmc.org/threads/play-correct-break-sound-for-a-block-1-14.402215/

data class BlockSounds(
    val broken: Sound?
)

fun Block.playSound(getSound: BlockSounds.() -> Sound?) {
    val sound = sounds.getSound()

    if (sound != null) {
        world.playSound(location, sound, 1.0f, 1.0f)
    } else {
        logger().warning("Attempted sound for $type, but no sounds were found. Actual block: $this")
    }
}

val Block.sounds get() = when(type) {
    Material.STONE -> BlockSounds(broken = Sound.BLOCK_STONE_BREAK)
    else -> BlockSounds(broken = null)
}
