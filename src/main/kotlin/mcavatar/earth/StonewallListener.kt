package mcavatar.earth

import mcavatar.PacketSender
import mcavatar.logger
import mcavatar.material.axe
import mcavatar.material.has
import mcavatar.material.playSound
import mcavatar.material.properties
import mcavatar.minecraft.EnumTitleAction
import mcavatar.minecraft.Packet
import mcavatar.minecraft.Title
import mcavatar.minecraft.chatText
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.inventory.ItemStack

private typealias ItemInInventory = Map<Int, ItemStack>

class StonewallListener : Listener {
    @EventHandler fun placeWall(e: BlockDamageEvent) {
        if (e.itemInHand.properties().has<axe>() && e.player.attackCooldown >= 0.9f) {
            val left = e.player.facing.perpendicular()
            val wall = wall(e.block, left)

            val cobblestone: ItemInInventory = e.player.inventory.all(Material.COBBLESTONE)

            var allBlocksPlaced = true
            wall.forEach {
                if (!it.type.isSolid && cobblestone.count() > 0) {
                    it.type = Material.COBBLESTONE
                    it.playSound { placed }
                    e.player.world.spawnParticle(Particle.BLOCK_DUST, it.location, 10, it.blockData)
                    cobblestone.removeOne()
                } else {
                    allBlocksPlaced = false
                }
            }

            if (!allBlocksPlaced) {
                PacketSender().send(e.player, Packet.Title(EnumTitleAction.ACTIONBAR, chatText("Not enough blocks!"), 1, 20, 1))
            }
        }
    }

    private fun wall(origin: Block, left: BlockFace): List<Block> {
        val u = origin.getRelative(BlockFace.UP)
        val ul = u.getRelative(left)
        val ur = u.getRelative(left.oppositeFace)
        val uu = u.getRelative(BlockFace.UP)
        val uul = uu.getRelative(left)
        val uur = uu.getRelative(left.oppositeFace)
        val uuu = uu.getRelative(BlockFace.UP)
        val uuul = uuu.getRelative(left)
        val uuur = uuu.getRelative(left.oppositeFace)

        return listOf(u, ul, ur, uu, uul, uur, uuu, uuul, uuur)
    }

    private fun ItemInInventory.count() =
        values.sumBy { it.amount }

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