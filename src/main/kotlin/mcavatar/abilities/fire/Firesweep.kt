package mcavatar.abilities.fire

import mcavatar.PacketSender
import mcavatar.abilities.Ability
import mcavatar.bukkit.block.perpendicular
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
import java.time.Duration
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.roundToInt
import kotlin.random.Random

class Firesweep(private val event: PlayerInteractEvent) : Ability(event.player, Bending.Fire) {
    private val cooldown = Duration.ofMillis(1500)
    private val igniteChance = Percent(40.0)

    private val front = player.facing
    private val left = front.perpendicular()
    private val right = left.oppositeFace

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

        player.sendMessage("You used firesweep")
        cone().forEach {
            if (it.type == Material.AIR && igniteChance.random())
                it.type = Material.FIRE
        }

        player.setCooldown(item!!.type, cooldown.toTicks().toInt())
    }

    class Listener : org.bukkit.event.Listener {
        @EventHandler fun sweep(e: PlayerInteractEvent) =
            Firesweep(e).execute()
    }

    private fun cone(): List<Block> {
        val f = player.location.block.getRelative(front)
        val frontLine = (1..5).map {
            f.getRelative(front, it)
        }

        val half = floor(frontLine.size / 2.0).roundToInt()
        return frontLine.flatMapIndexed { index, block ->
            line(block, -abs(index - half) + half + 1)
        }.flatMap {
            column(it)
        }
    }

    private fun line(center: Block, length: Int) =
        listOf(center) +
            (1..length).map { center.getRelative(left, it) } +
            (1..length).map { center.getRelative(right, it) }

    private fun column(center: Block) =
        listOf(center, center.getRelative(BlockFace.UP), center.getRelative(BlockFace.DOWN))
}