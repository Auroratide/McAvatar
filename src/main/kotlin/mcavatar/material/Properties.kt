package mcavatar.material

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

interface MaterialProperty
object earthy : MaterialProperty
interface tool : MaterialProperty
object pickaxe : tool
object axe : tool
object hoe : tool
object shovel : tool
object sword : tool

private val properties: Map<Material, List<MaterialProperty>> = mapOf(
    Material.DIRT to listOf(earthy),
    Material.STONE to listOf(earthy),
    Material.COBBLESTONE to listOf(earthy),
    Material.ANDESITE to listOf(earthy),
    Material.GRANITE to listOf(earthy),
    Material.DIORITE to listOf(earthy),
    Material.GRAVEL to listOf(earthy),
    Material.SAND to listOf(earthy),
    Material.SANDSTONE to listOf(earthy),

    Material.WOODEN_PICKAXE to listOf(pickaxe),
    Material.STONE_PICKAXE to listOf(pickaxe),
    Material.IRON_PICKAXE to listOf(pickaxe),
    Material.GOLDEN_PICKAXE to listOf(pickaxe),
    Material.DIAMOND_PICKAXE to listOf(pickaxe),
    Material.NETHERITE_PICKAXE to listOf(pickaxe),

    Material.WOODEN_AXE to listOf(axe),
    Material.STONE_AXE to listOf(axe),
    Material.IRON_AXE to listOf(axe),
    Material.GOLDEN_AXE to listOf(axe),
    Material.DIAMOND_AXE to listOf(axe),
    Material.NETHERITE_AXE to listOf(axe),

    Material.WOODEN_HOE to listOf(hoe),
    Material.STONE_HOE to listOf(hoe),
    Material.IRON_HOE to listOf(hoe),
    Material.GOLDEN_HOE to listOf(hoe),
    Material.DIAMOND_HOE to listOf(hoe),
    Material.NETHERITE_HOE to listOf(hoe),

    Material.WOODEN_SHOVEL to listOf(shovel),
    Material.STONE_SHOVEL to listOf(shovel),
    Material.IRON_SHOVEL to listOf(shovel),
    Material.GOLDEN_SHOVEL to listOf(shovel),
    Material.DIAMOND_SHOVEL to listOf(shovel),
    Material.NETHERITE_SHOVEL to listOf(shovel),

    Material.WOODEN_SWORD to listOf(sword),
    Material.STONE_SWORD to listOf(sword),
    Material.IRON_SWORD to listOf(sword),
    Material.GOLDEN_SWORD to listOf(sword),
    Material.DIAMOND_SWORD to listOf(sword),
    Material.NETHERITE_SWORD to listOf(sword),
)

fun Block.properties() = properties[type] ?: emptyList()
fun ItemStack.properties() = properties[type] ?: emptyList()
inline fun <reified T> List<MaterialProperty>.has() = find { it is T } != null