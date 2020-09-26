package mcavatar.abilities.air

import mcavatar.abilities.Ability
import mcavatar.bukkit.material.has
import mcavatar.bukkit.material.hoe
import mcavatar.bukkit.material.properties
import mcavatar.math.scaleBy
import mcavatar.permissions.Bending
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

class Windwalker(private val event: PlayerInteractEvent) : Ability(event.player, Bending.Air) {
    override fun preconditions() = with(event) {
        trigger { hasItem() && item!!.properties().has<hoe>() }
        trigger { action in listOf(Action.RIGHT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR) }
        trigger { hand == EquipmentSlot.HAND }
        trigger { !hasBlock() || !(clickedBlock?.type?.isInteractable ?: false) }
        trigger { !player.isOnGround } // TODO do not rely on client-reporting field
    }

    override fun action() {
        player.velocity = player.location.direction.normalize()
    }

    class Listener : org.bukkit.event.Listener {
        @EventHandler fun onInteract(e: PlayerInteractEvent) {
            Windwalker(e).execute()
        }
    }
}