package mcavatar.abilities.fire

import mcavatar.abilities.Ability
import mcavatar.permissions.Bending
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageEvent

class Fireproof(player: Player, private val event: EntityDamageEvent) : Ability(player, Bending.Fire) {
    override fun preconditions() = with(event) {
        trigger { cause == EntityDamageEvent.DamageCause.FIRE_TICK }
    }

    override fun action() = with(event) {
        isCancelled = true
    }

    class Listener : org.bukkit.event.Listener {
        @EventHandler fun onDamage(e: EntityDamageEvent) {
            if (e.entity is Player)
                Fireproof(e.entity as Player, e).execute()
        }
    }
}