package mcavatar.earth

import mcavatar.material.axe
import mcavatar.material.has
import mcavatar.material.properties
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.inventory.ItemStack

private typealias ItemInInventory = Map<Int, ItemStack>

class StonewallListener : Listener {
    @EventHandler fun placeWall(e: BlockDamageEvent) {
        if (e.itemInHand.properties().has<axe>() && e.player.attackCooldown >= 1.0f) {
            val left = e.player.facing.perpendicular()
            val up = BlockFace.UP
            val down = BlockFace.DOWN
            val chain = listOf(up, up, up, left, down, down, left, up, up)

            val cobblestone: ItemInInventory = e.player.inventory.all(Material.COBBLESTONE)

            chain.fold(e.block.getRelative(left.oppositeFace)) { block, dir ->
                block.getRelative(dir).also {
                    if (!it.type.isSolid) {
                        it.type = Material.COBBLESTONE
                        cobblestone.removeOne()
                    }
                }
            }
        }
    }

    private fun ItemInInventory.removeOne() {
        values.find { it.amount > 0 }?.let {
            --it.amount
        }
    }

    private fun BlockFace.perpendicular(): BlockFace {
        return when(this) {
            BlockFace.NORTH -> BlockFace.WEST
            BlockFace.WEST -> BlockFace.SOUTH
            BlockFace.SOUTH -> BlockFace.EAST
            BlockFace.EAST -> BlockFace.NORTH
            BlockFace.NORTH_EAST -> BlockFace.NORTH_WEST
            BlockFace.NORTH_WEST -> BlockFace.SOUTH_WEST
            BlockFace.SOUTH_WEST -> BlockFace.SOUTH_EAST
            BlockFace.SOUTH_EAST -> BlockFace.NORTH_EAST
            BlockFace.NORTH_NORTH_EAST -> BlockFace.WEST_NORTH_WEST
            BlockFace.WEST_NORTH_WEST -> BlockFace.SOUTH_SOUTH_WEST
            BlockFace.SOUTH_SOUTH_WEST -> BlockFace.EAST_SOUTH_EAST
            BlockFace.EAST_SOUTH_EAST -> BlockFace.NORTH_NORTH_EAST
            BlockFace.EAST_NORTH_EAST -> BlockFace.NORTH_NORTH_WEST
            BlockFace.NORTH_NORTH_WEST -> BlockFace.WEST_SOUTH_WEST
            BlockFace.WEST_SOUTH_WEST -> BlockFace.SOUTH_SOUTH_EAST
            BlockFace.SOUTH_SOUTH_EAST -> BlockFace.EAST_NORTH_EAST
            BlockFace.UP -> BlockFace.NORTH
            BlockFace.DOWN -> BlockFace.SOUTH
            BlockFace.SELF -> BlockFace.SELF
        }
    }
}