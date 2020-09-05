package mcavatar.earth

import mcavatar.PacketSender
import mcavatar.bukkit.block.perpendicular
import mcavatar.bukkit.inventory.item
import mcavatar.bukkit.material.axe
import mcavatar.bukkit.material.has
import mcavatar.bukkit.material.playSound
import mcavatar.bukkit.material.properties
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

class StonewallListener : Listener {
    @EventHandler fun placeWall(e: BlockDamageEvent) {
        if (e.itemInHand.properties().has<axe>() && e.player.attackCooldown >= 0.9f) {
            val left = e.player.facing.perpendicular()
            val wall = wall(e.block, left)

            val cobblestone = e.player.inventory.item(Material.COBBLESTONE)

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
}