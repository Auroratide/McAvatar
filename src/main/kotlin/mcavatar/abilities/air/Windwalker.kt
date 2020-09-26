package mcavatar.abilities.air

import mcavatar.abilities.Ability
import mcavatar.bukkit.material.has
import mcavatar.bukkit.material.hoe
import mcavatar.bukkit.material.properties
import mcavatar.permissions.Bending
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
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
        trigger { !player.isOnGround } // TODO do not rely on client-reporting field

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
            if (uses.containsKey(e.player.uniqueId) && e.player.isOnGround)
                uses.remove(e.player.uniqueId)
        }
    }

    private fun usedAtMostTwice() = previousUse?.previousUse == null
}