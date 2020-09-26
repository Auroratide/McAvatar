package mcavatar.abilities.air

import mcavatar.abilities.Ability
import mcavatar.bukkit.entity.inAir
import mcavatar.bukkit.entity.located
import mcavatar.bukkit.entity.onGround
import mcavatar.bukkit.material.has
import mcavatar.bukkit.material.hoe
import mcavatar.bukkit.material.properties
import mcavatar.logger
import mcavatar.permissions.Bending
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.inventory.EquipmentSlot
import java.util.*

class Windwalker(private val event: PlayerInteractEvent, private val previousUse: Windwalker?) : Ability(event.player, Bending.Air) {
    override fun preconditions() = with(event) {
        trigger { hasItem() && item!!.properties().has<hoe>() }
        trigger { action in listOf(Action.RIGHT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR) }
        trigger { hand == EquipmentSlot.HAND }
        trigger { !hasBlock() || !(clickedBlock?.type?.isInteractable ?: false) }
        trigger { player located inAir }

        requirement("Can only use twice before landing!") {
            usedAtMostTwice()
        }
    }

    override fun action() {
        player.velocity = player.location.direction.normalize()
    }

    class Listener : org.bukkit.event.Listener {
        private val uses = mutableMapOf<UUID, Windwalker>()

        @EventHandler fun onInteract(e: PlayerInteractEvent) {
            val use = Windwalker(e, uses[e.player.uniqueId])
            if (use.execute())
                uses[e.player.uniqueId] = use
        }

        @EventHandler fun onVelocity(e: PlayerMoveEvent) {
            if (uses.containsKey(e.player.uniqueId) && e.player located onGround)
                uses.remove(e.player.uniqueId)
        }

        @EventHandler fun onDamage(e: EntityDamageEvent) {
            val entity = e.entity
            if (entity is Player)
                FallDamageReduction(entity, e).execute()
        }
    }

    private fun usedAtMostTwice() = previousUse?.previousUse == null

    private class FallDamageReduction(player: Player, private val event: EntityDamageEvent) : Ability(player, Bending.Air) {
        override fun preconditions() = with(event) {
            trigger { cause == EntityDamageEvent.DamageCause.FALL }
        }

        override fun action() = with(event) {
            damage = 0.75 * damage - 3.0
            if (damage <= 0)
                isCancelled = true
        }
    }
}