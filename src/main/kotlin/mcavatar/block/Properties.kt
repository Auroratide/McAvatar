package mcavatar.block

import org.bukkit.Material
import org.bukkit.block.Block

interface BlockProperty
object earthy : BlockProperty

private val properties: Map<Material, List<BlockProperty>> = mapOf(
    Material.DIRT to listOf(earthy),
    Material.STONE to listOf(earthy),
    Material.COBBLESTONE to listOf(earthy),
    Material.ANDESITE to listOf(earthy),
    Material.GRANITE to listOf(earthy),
    Material.DIORITE to listOf(earthy),
    Material.GRAVEL to listOf(earthy),
    Material.SAND to listOf(earthy),
    Material.SANDSTONE to listOf(earthy)
)

fun Block.properties() = properties[type] ?: emptyList()