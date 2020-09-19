package mcavatar.abilities.fire

import mcavatar.abilities.Ability
import mcavatar.permissions.Bending
import mcavatar.scheduler.toTicks
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.time.Duration

class Fireproof(player: Player, private val event: EntityDamageEvent) : Ability(player, Bending.Fire) {
    override fun preconditions() = with(event) {
        trigger {
            cause == EntityDamageEvent.DamageCause.FIRE_TICK ||
            cause == EntityDamageEvent.DamageCause.FIRE ||
            cause == EntityDamageEvent.DamageCause.LAVA
        }
    }

    override fun action() = with(event) {
        isCancelled = cause == EntityDamageEvent.DamageCause.FIRE_TICK

        player.strengthen()
    }

    class Listener : org.bukkit.event.Listener {
        @EventHandler fun onDamage(e: EntityDamageEvent) {
            if (e.entity is Player)
                Fireproof(e.entity as Player, e).execute()
        }
    }

    private fun Player.strengthen() {
        addPotionEffect(PotionEffect(
            PotionEffectType.INCREASE_DAMAGE,
            Duration.ofMillis(1250).toTicks().toInt(),
            0)
        )
    }
}