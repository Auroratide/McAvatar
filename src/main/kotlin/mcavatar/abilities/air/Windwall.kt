package mcavatar.abilities.air

import mcavatar.abilities.Ability
import mcavatar.bukkit.material.has
import mcavatar.bukkit.material.hoe
import mcavatar.bukkit.material.properties
import mcavatar.math.minus
import mcavatar.math.plus
import mcavatar.math.scaleBy
import mcavatar.permissions.Bending
import mcavatar.scheduler.Scheduler
import mcavatar.scheduler.ticks
import org.bukkit.Particle
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import kotlin.math.cos
import kotlin.math.sin

class Windwall(private val scheduler: Scheduler, private val event: PlayerInteractEvent) : Ability(event.player, Bending.Air) {
    private val world = player.world

    override fun preconditions() = with(event) {
        trigger { hasItem() && item!!.properties().has<hoe>() }
        trigger { action in listOf(Action.RIGHT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR) }
        trigger { hand == EquipmentSlot.HAND }
        trigger { !hasBlock() || !(clickedBlock?.type?.isInteractable ?: false) }
    }

    override fun action(): Unit = with(event) {
        scheduler.onEachTickFor(4.ticks) {
            world.livingEntities.forEach {
                if (it != player && it.location.distance(player.location) <= 4) {
                    it.velocity += (it.location.toVector() - player.location.toVector()).normalize() scaleBy 0.25
                }
            }
        }
    }

    class Listener(private val scheduler: Scheduler) : org.bukkit.event.Listener {
        @EventHandler fun onInteract(e: PlayerInteractEvent) {
            Windwall(scheduler, e).execute()
        }
    }

    private fun particles() {
        for (i in 1..5) {
            val t = Math.PI / i
            for (j in 1..10) {
                val p = 2 * Math.PI / j
                world.spawnParticle(Particle.CLOUD, player.location, 1, sin(t) * cos(p), sin(t) * sin(p), cos(t))
            }
        }
    }
}