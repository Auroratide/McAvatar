package mcavatar.earth

import mcavatar.Ability
import mcavatar.PacketSender
import mcavatar.bukkit.block.perpendicular
import mcavatar.bukkit.inventory.item
import mcavatar.bukkit.material.*
import mcavatar.minecraft.Animation
import mcavatar.minecraft.ClientAnimation
import mcavatar.minecraft.Packet
import mcavatar.scheduler.toTicks
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import java.time.Duration
import kotlin.random.Random

class Stonewall(event: PlayerInteractEvent) : Ability<PlayerInteractEvent>(event, event.player) {
    private val block get() = event.clickedBlock!!
    private val left = event.player.facing.perpendicular()
    private val cobblestone = event.player.inventory.item(Material.COBBLESTONE)

    override fun preconditions() = with(event) {
        trigger { hasBlock() && hasItem() }
        trigger { action == Action.RIGHT_CLICK_BLOCK && hand == EquipmentSlot.HAND }
        trigger { item!!.properties().has<axe>() }
        trigger { player.getCooldown(item!!.type) <= 0 }

        requirement("Must use on ground!") {
            !block.getRelative(BlockFace.UP).type.isSolid
        }

        requirement("Not enough blocks!") {
            cobblestone.count() > 0
        }
    }

    override fun action() = with(event) {
        PacketSender().send(player, Packet.Animation(player, ClientAnimation.SWING_MAIN_ARM))

        wall().forEach {
            if (canCobblify(it)) {
                cobblify(it)
                cobblestone.removeOne()
            }
        }

        materialsWith<axe>().forEach {
            player.setCooldown(it, Duration.ofMillis(900).toTicks().toInt())
        }
    }

    class Listener : org.bukkit.event.Listener {
        @EventHandler fun placeWall(e: PlayerInteractEvent) =
            Stonewall(e).execute()
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