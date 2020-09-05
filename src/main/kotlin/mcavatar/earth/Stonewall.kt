package mcavatar.earth

import mcavatar.PacketSender
import mcavatar.bukkit.block.perpendicular
import mcavatar.bukkit.inventory.item
import mcavatar.bukkit.material.axe
import mcavatar.bukkit.material.has
import mcavatar.bukkit.material.playSound
import mcavatar.bukkit.material.properties
import mcavatar.bukkit.titles.actionBar
import mcavatar.minecraft.EnumTitleAction
import mcavatar.minecraft.Packet
import mcavatar.minecraft.Title
import mcavatar.minecraft.chatText
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

class Stonewall(private val player: Player, private val block: Block) {
    private val left = player.facing.perpendicular()
    private val cobblestone = player.inventory.item(Material.COBBLESTONE)

    fun execute() {
        when {
            block.getRelative(BlockFace.UP).type.isSolid ->
                player.actionBar.warn("Must use on ground!")
            cobblestone.count() == 0 ->
                player.actionBar.warn("Not enough blocks!")
            else ->
                wall().forEach {
                    if (canCobblify(it)) {
                        cobblify(it)
                        cobblestone.removeOne()
                    }
                }
        }
    }

    class Listener : org.bukkit.event.Listener {
        @EventHandler fun placeWall(e: BlockDamageEvent) {
            if (e.itemInHand.properties().has<axe>() && e.player.attackCooldown >= 0.9f) {
                Stonewall(e.player, e.block).execute()
            }
        }
    }

    private fun canCobblify(block: Block) =
        !block.type.isSolid &&
        cobblestone.count() > 0

    private fun cobblify(block: Block) {
        block.type = Material.COBBLESTONE
        block.playSound { placed }
        repeat(10) {
            val location = Location(block.world, block.location.x + Random.nextDouble(0.0, 1.0), block.location.y + Random.nextDouble(0.0, 1.0), block.location.z + Random.nextDouble(0.0, 1.0))
            block.world.spawnParticle(Particle.BLOCK_DUST, location, 1, block.blockData)
        }
    }

    private fun wall(): List<Block> {
        val u = block.getRelative(BlockFace.UP)
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
