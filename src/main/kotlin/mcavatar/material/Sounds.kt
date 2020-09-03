package mcavatar.material

import mcavatar.logger
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.block.Block

// Future reference in case this gets out of hand:
// https://www.spigotmc.org/threads/play-correct-break-sound-for-a-block-1-14.402215/

data class BlockSounds(
    val broken: Sound?,
    val placed: Sound?
)

private val grassSounds = BlockSounds(broken = Sound.BLOCK_GRASS_BREAK, placed = Sound.BLOCK_GRASS_PLACE)
private val gravelSounds = BlockSounds(broken = Sound.BLOCK_GRAVEL_BREAK, placed = Sound.BLOCK_GRAVEL_PLACE)
private val sandSounds = BlockSounds(broken = Sound.BLOCK_SAND_BREAK, placed = Sound.BLOCK_SAND_PLACE)
private val stoneSounds = BlockSounds(broken = Sound.BLOCK_STONE_BREAK, placed = Sound.BLOCK_STONE_PLACE)

private val sounds: Map<Material, BlockSounds> = mapOf(
    Material.DIRT to gravelSounds,
    Material.GRASS_BLOCK to grassSounds,
    Material.COARSE_DIRT to gravelSounds,
    Material.GRAVEL to gravelSounds,

    Material.STONE to stoneSounds,
    Material.STONE_SLAB to stoneSounds,
    Material.STONE_STAIRS to stoneSounds,
    Material.SMOOTH_STONE to stoneSounds,
    Material.SMOOTH_STONE_SLAB to stoneSounds,
    Material.STONE_BRICKS to stoneSounds,
    Material.STONE_BRICK_SLAB to stoneSounds,
    Material.STONE_BRICK_STAIRS to stoneSounds,
    Material.STONE_BRICK_WALL to stoneSounds,
    Material.CHISELED_STONE_BRICKS to stoneSounds,
    Material.CRACKED_STONE_BRICKS to stoneSounds,
    Material.MOSSY_STONE_BRICKS to stoneSounds,
    Material.MOSSY_STONE_BRICK_SLAB to stoneSounds,
    Material.MOSSY_STONE_BRICK_STAIRS to stoneSounds,
    Material.MOSSY_STONE_BRICK_WALL to stoneSounds,

    Material.COBBLESTONE to stoneSounds,
    Material.COBBLESTONE_SLAB to stoneSounds,
    Material.COBBLESTONE_STAIRS to stoneSounds,
    Material.COBBLESTONE_WALL to stoneSounds,
    Material.MOSSY_COBBLESTONE to stoneSounds,
    Material.MOSSY_COBBLESTONE_SLAB to stoneSounds,
    Material.MOSSY_COBBLESTONE_STAIRS to stoneSounds,
    Material.MOSSY_COBBLESTONE_WALL to stoneSounds,

    Material.BLACKSTONE to stoneSounds,
    Material.BLACKSTONE_SLAB to stoneSounds,
    Material.BLACKSTONE_STAIRS to stoneSounds,
    Material.BLACKSTONE_WALL to stoneSounds,
    Material.POLISHED_BLACKSTONE to stoneSounds,
    Material.POLISHED_BLACKSTONE_BRICKS to stoneSounds,
    Material.POLISHED_BLACKSTONE_BRICK_SLAB to stoneSounds,
    Material.POLISHED_BLACKSTONE_BRICK_STAIRS to stoneSounds,
    Material.POLISHED_BLACKSTONE_BRICK_WALL to stoneSounds,
    Material.POLISHED_BLACKSTONE_SLAB to stoneSounds,
    Material.POLISHED_BLACKSTONE_STAIRS to stoneSounds,
    Material.POLISHED_BLACKSTONE_WALL to stoneSounds,
    Material.CRACKED_POLISHED_BLACKSTONE_BRICKS to stoneSounds,

    Material.ANDESITE to stoneSounds,
    Material.ANDESITE_SLAB to stoneSounds,
    Material.ANDESITE_STAIRS to stoneSounds,
    Material.ANDESITE_WALL to stoneSounds,
    Material.POLISHED_ANDESITE to stoneSounds,
    Material.POLISHED_ANDESITE_SLAB to stoneSounds,
    Material.POLISHED_ANDESITE_STAIRS to stoneSounds,

    Material.GRANITE to stoneSounds,
    Material.GRANITE_SLAB to stoneSounds,
    Material.GRANITE_STAIRS to stoneSounds,
    Material.GRANITE_WALL to stoneSounds,
    Material.POLISHED_GRANITE to stoneSounds,
    Material.POLISHED_GRANITE_SLAB to stoneSounds,
    Material.POLISHED_GRANITE_STAIRS to stoneSounds,

    Material.DIORITE to stoneSounds,
    Material.DIORITE_SLAB to stoneSounds,
    Material.DIORITE_STAIRS to stoneSounds,
    Material.DIORITE_WALL to stoneSounds,
    Material.POLISHED_DIORITE to stoneSounds,
    Material.POLISHED_DIORITE_SLAB to stoneSounds,
    Material.POLISHED_DIORITE_STAIRS to stoneSounds,

    Material.SAND to sandSounds,
    Material.SANDSTONE to stoneSounds,
    Material.SANDSTONE_SLAB to stoneSounds,
    Material.SANDSTONE_STAIRS to stoneSounds,
    Material.SANDSTONE_WALL to stoneSounds,
    Material.CHISELED_SANDSTONE to stoneSounds,
    Material.CUT_SANDSTONE to stoneSounds,
    Material.CUT_SANDSTONE_SLAB to stoneSounds,
    Material.SMOOTH_SANDSTONE to stoneSounds,
    Material.SMOOTH_SANDSTONE_SLAB to stoneSounds,
    Material.SMOOTH_SANDSTONE_STAIRS to stoneSounds,
    Material.RED_SAND to sandSounds,
    Material.RED_SANDSTONE to stoneSounds,
    Material.RED_SANDSTONE_SLAB to stoneSounds,
    Material.RED_SANDSTONE_STAIRS to stoneSounds,
    Material.RED_SANDSTONE_WALL to stoneSounds,
    Material.CHISELED_RED_SANDSTONE to stoneSounds,
    Material.CUT_RED_SANDSTONE to stoneSounds,
    Material.CUT_RED_SANDSTONE_SLAB to stoneSounds,
    Material.SMOOTH_RED_SANDSTONE to stoneSounds,
    Material.SMOOTH_RED_SANDSTONE_SLAB to stoneSounds,
    Material.SMOOTH_RED_SANDSTONE_STAIRS to stoneSounds,
)

fun Block.playSound(getSound: BlockSounds.() -> Sound?) {
    val sound = sounds().getSound()

    if (sound != null) {
        world.playSound(location, sound, 1.0f, 0.75f)
    } else {
        logger().warning("Attempted sound for $type, but no sounds were found. Actual block: $this")
    }
}

fun Block.sounds() = sounds[type] ?: BlockSounds(broken = null, placed = null)
