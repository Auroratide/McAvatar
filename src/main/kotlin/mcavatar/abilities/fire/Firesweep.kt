package mcavatar.abilities.fire

import mcavatar.PacketSender
import mcavatar.abilities.Ability
import mcavatar.logger
import mcavatar.math.Percent
import mcavatar.minecraft.Animation
import mcavatar.minecraft.ClientAnimation
import mcavatar.minecraft.Packet
import mcavatar.permissions.Bending
import mcavatar.scheduler.toTicks
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.util.Vector
import java.time.Duration
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.roundToInt

class Firesweep(private val event: PlayerInteractEvent) : Ability(event.player, Bending.Fire) {
    private val cooldown = Duration.ofMillis(1500)
    private val igniteChance = Percent(40.0)
    private val igniteDuration = Duration.ofMillis(4000)

    private val front = player.location.direction.normalize()
    private val left = front.clone().crossProduct(Vector(0, 1, 0)).normalize()
    private val right = left.clone().multiply(-1)
    private val up = front.clone().crossProduct(left).normalize()
    private val down = up.clone().multiply(-1)

//    private val areaOfEffect =
//        BoundingBox.of(
//            player.location.block.getRelative(front, 6).getRelative(left, 2),
//            player.location.block.getRelative(front, 2).getRelative(right, 2)
//        )

    override fun preconditions() = with(event) {
        trigger { hasItem() }
        trigger { action in listOf(Action.RIGHT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR) }
        trigger { hand == EquipmentSlot.HAND }
        trigger { !hasBlock() || !(clickedBlock?.type?.isInteractable ?: false) }
        trigger { item!!.type == Material.FIRE_CHARGE }
        trigger { player.getCooldown(item!!.type) <= 0 }
    }

    override fun action() = with(event) {
        PacketSender().send(player, Packet.Animation(player, ClientAnimation.SWING_MAIN_ARM))

        cone().forEach {
            if (it.type == Material.AIR && igniteChance.random())
                it.type = Material.FIRE
        }

//        player.world.livingEntities.forEach {
//            if (areaOfEffect.overlaps(it.boundingBox)) {
//                it.damage(2.0, player)
//                it.fireTicks = igniteDuration.toTicks().toInt()
//            }
//        }

        --item!!.amount
        player.setCooldown(item!!.type, cooldown.toTicks().toInt())
    }

    class Listener : org.bukkit.event.Listener {
        @EventHandler fun sweep(e: PlayerInteractEvent) =
            Firesweep(e).execute()
    }

    private fun cone(): List<Block> {
        val f = player.location.toVector().getRelative(front)
        val frontLine = (1..5).map {
            f.getRelative(front, it)
        }

        val half = floor(frontLine.size / 2.0).roundToInt()
        return frontLine.flatMapIndexed { index, block ->
            line(block, -abs(index - half) + half + 1)
        }.flatMap {
            column(it)
        }.map {
            it.toLocation(player.world).block
        }
    }

    private fun line(center: Vector, length: Int) =
        listOf(center) +
            (1..length).map { center.getRelative(left, it) } +
            (1..length).map { center.getRelative(right, it) }

    private fun column(center: Vector) =
        listOf(center, center.getRelative(up), center.getRelative(down))

    private fun Vector.getRelative(unitDirection: Vector, amount: Int = 1) =
        clone().add(unitDirection.clone().multiply(amount))
}