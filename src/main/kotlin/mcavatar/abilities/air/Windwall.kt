package mcavatar.abilities.air

import mcavatar.abilities.Ability
import mcavatar.bukkit.entity.located
import mcavatar.bukkit.entity.onGround
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
import org.bukkit.util.Vector
import kotlin.math.cos
import kotlin.math.sin

class Windwall(private val scheduler: Scheduler, private val event: PlayerInteractEvent) : Ability(event.player, Bending.Air) {
    private val effectDistance = 4
    private val pushForce = 0.075

    private val world = player.world

    override fun preconditions() = with(event) {
        trigger { hasItem() && item!!.properties().has<hoe>() }
        trigger { action in listOf(Action.RIGHT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR) }
        trigger { hand == EquipmentSlot.HAND }
        trigger { !hasBlock() || !(clickedBlock?.type?.isInteractable ?: false) }
        trigger { player located onGround }
    }

    override fun action(): Unit = with(event) {
        scheduler.onEachTickFor(4.ticks) {
//            particles()

            world.livingEntities.forEach {
                if (it != player && it.location.distance(player.location) <= effectDistance) {
                    it.velocity += (it.location.toVector() - player.location.toVector()).normalize() scaleBy pushForce
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
        // TODO just the front hemisphere of the sphere
        val r = 3.25
        for (i in 0..9) {
            val t = Math.PI / 10 * i
            for (j in 0..19) {
                val p = 2 * Math.PI / 20 * j
                val location = player.location.toVector() + Vector(r * sin(t) * cos(p), r * sin(t) * sin(p), r * cos(t))
                world.spawnParticle(Particle.CLOUD, location.toLocation(world), 1, 0.0, 0.0, 0.0, 0.1)
            }
        }
    }
}