package mcavatar.abilities.fire

import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageEvent

class Fireproof {
    class Listener : org.bukkit.event.Listener {
        @EventHandler fun onDamage(e: EntityDamageEvent) {
            if (e.cause == EntityDamageEvent.DamageCause.FIRE_TICK)
                e.isCancelled = true
        }
    }
}