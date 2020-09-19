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
import kotlin.random.Random

class Fireproof(player: Player, private val event: EntityDamageEvent) : Ability(player, Bending.Fire) {
    private val fireTickLength = Duration.ofMillis(1000) // documented on wiki

    override fun preconditions() = with(event) {
        trigger {
            cause == EntityDamageEvent.DamageCause.FIRE_TICK ||
            cause == EntityDamageEvent.DamageCause.FIRE ||
            cause == EntityDamageEvent.DamageCause.LAVA
        }
    }

    override fun action() = with(event) {
        preventFireDamage()

        player.lengthenFireDuration()
        player.strengthen()
    }

    class Listener : org.bukkit.event.Listener {
        @EventHandler fun onDamage(e: EntityDamageEvent) {
            if (e.entity is Player)
                Fireproof(e.entity as Player, e).execute()
        }
    }

    private fun Player.lengthenFireDuration() {
        // % change of increasing duration; can't just add half the fire tick duration due to how fire ticks behave
        // fire ticks always happen on ticks divisible by 20, not every 20 ticks; there's a subtle difference
        if (event.cause == EntityDamageEvent.DamageCause.FIRE_TICK && Random.nextBoolean())
            fireTicks += fireTickLength.toTicks().toInt()
    }

    private fun preventFireDamage() {
        // Still take damage from direct fire and lava, for balancing reasons
        event.isCancelled = event.cause == EntityDamageEvent.DamageCause.FIRE_TICK
    }

    private fun Player.strengthen() {
        addPotionEffect(PotionEffect(
            PotionEffectType.INCREASE_DAMAGE,
            (fireTickLength + Duration.ofMillis(250)).toTicks().toInt(),
            0)
        )
    }
}