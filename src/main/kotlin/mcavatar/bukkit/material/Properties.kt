package mcavatar.bukkit.material

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
    Material.GRASS_BLOCK to listOf(earthy),
    Material.COARSE_DIRT to listOf(earthy),
    Material.GRAVEL to listOf(earthy),

    Material.STONE to listOf(earthy),
    Material.STONE_SLAB to listOf(earthy),
    Material.STONE_STAIRS to listOf(earthy),
    Material.SMOOTH_STONE to listOf(earthy),
    Material.SMOOTH_STONE_SLAB to listOf(earthy),
    Material.STONE_BRICKS to listOf(earthy),
    Material.STONE_BRICK_SLAB to listOf(earthy),
    Material.STONE_BRICK_STAIRS to listOf(earthy),
    Material.STONE_BRICK_WALL to listOf(earthy),
    Material.CHISELED_STONE_BRICKS to listOf(earthy),
    Material.CRACKED_STONE_BRICKS to listOf(earthy),
    Material.MOSSY_STONE_BRICKS to listOf(earthy),
    Material.MOSSY_STONE_BRICK_SLAB to listOf(earthy),
    Material.MOSSY_STONE_BRICK_STAIRS to listOf(earthy),
    Material.MOSSY_STONE_BRICK_WALL to listOf(earthy),

    Material.COBBLESTONE to listOf(earthy),
    Material.COBBLESTONE_SLAB to listOf(earthy),
    Material.COBBLESTONE_STAIRS to listOf(earthy),
    Material.COBBLESTONE_WALL to listOf(earthy),
    Material.MOSSY_COBBLESTONE to listOf(earthy),
    Material.MOSSY_COBBLESTONE_SLAB to listOf(earthy),
    Material.MOSSY_COBBLESTONE_STAIRS to listOf(earthy),
    Material.MOSSY_COBBLESTONE_WALL to listOf(earthy),

    Material.BLACKSTONE to listOf(earthy),
    Material.BLACKSTONE_SLAB to listOf(earthy),
    Material.BLACKSTONE_STAIRS to listOf(earthy),
    Material.BLACKSTONE_WALL to listOf(earthy),
    Material.POLISHED_BLACKSTONE to listOf(earthy),
    Material.POLISHED_BLACKSTONE_BRICKS to listOf(earthy),
    Material.POLISHED_BLACKSTONE_BRICK_SLAB to listOf(earthy),
    Material.POLISHED_BLACKSTONE_BRICK_STAIRS to listOf(earthy),
    Material.POLISHED_BLACKSTONE_BRICK_WALL to listOf(earthy),
    Material.POLISHED_BLACKSTONE_SLAB to listOf(earthy),
    Material.POLISHED_BLACKSTONE_STAIRS to listOf(earthy),
    Material.POLISHED_BLACKSTONE_WALL to listOf(earthy),
    Material.CRACKED_POLISHED_BLACKSTONE_BRICKS to listOf(earthy),

    Material.ANDESITE to listOf(earthy),
    Material.ANDESITE_SLAB to listOf(earthy),
    Material.ANDESITE_STAIRS to listOf(earthy),
    Material.ANDESITE_WALL to listOf(earthy),
    Material.POLISHED_ANDESITE to listOf(earthy),
    Material.POLISHED_ANDESITE_SLAB to listOf(earthy),
    Material.POLISHED_ANDESITE_STAIRS to listOf(earthy),

    Material.GRANITE to listOf(earthy),
    Material.GRANITE_SLAB to listOf(earthy),
    Material.GRANITE_STAIRS to listOf(earthy),
    Material.GRANITE_WALL to listOf(earthy),
    Material.POLISHED_GRANITE to listOf(earthy),
    Material.POLISHED_GRANITE_SLAB to listOf(earthy),
    Material.POLISHED_GRANITE_STAIRS to listOf(earthy),

    Material.DIORITE to listOf(earthy),
    Material.DIORITE_SLAB to listOf(earthy),
    Material.DIORITE_STAIRS to listOf(earthy),
    Material.DIORITE_WALL to listOf(earthy),
    Material.POLISHED_DIORITE to listOf(earthy),
    Material.POLISHED_DIORITE_SLAB to listOf(earthy),
    Material.POLISHED_DIORITE_STAIRS to listOf(earthy),

    Material.SAND to listOf(earthy),
    Material.SANDSTONE to listOf(earthy),
    Material.SANDSTONE_SLAB to listOf(earthy),
    Material.SANDSTONE_STAIRS to listOf(earthy),
    Material.SANDSTONE_WALL to listOf(earthy),
    Material.CHISELED_SANDSTONE to listOf(earthy),
    Material.CUT_SANDSTONE to listOf(earthy),
    Material.CUT_SANDSTONE_SLAB to listOf(earthy),
    Material.SMOOTH_SANDSTONE to listOf(earthy),
    Material.SMOOTH_SANDSTONE_SLAB to listOf(earthy),
    Material.SMOOTH_SANDSTONE_STAIRS to listOf(earthy),
    Material.RED_SAND to listOf(earthy),
    Material.RED_SANDSTONE to listOf(earthy),
    Material.RED_SANDSTONE_SLAB to listOf(earthy),
    Material.RED_SANDSTONE_STAIRS to listOf(earthy),
    Material.RED_SANDSTONE_WALL to listOf(earthy),
    Material.CHISELED_RED_SANDSTONE to listOf(earthy),
    Material.CUT_RED_SANDSTONE to listOf(earthy),
    Material.CUT_RED_SANDSTONE_SLAB to listOf(earthy),
    Material.SMOOTH_RED_SANDSTONE to listOf(earthy),
    Material.SMOOTH_RED_SANDSTONE_SLAB to listOf(earthy),
    Material.SMOOTH_RED_SANDSTONE_STAIRS to listOf(earthy),

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