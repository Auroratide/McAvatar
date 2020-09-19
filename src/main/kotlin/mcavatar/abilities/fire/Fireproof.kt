package mcavatar.abilities.fire

import mcavatar.abilities.Ability
import mcavatar.permissions.Bending
import mcavatar.scheduler.Scheduler
import mcavatar.scheduler.toTicks
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.time.Duration
import java.util.*
import kotlin.random.Random

class Fireproof(player: Player, private val scheduler: Scheduler, private val previousFireproof: Fireproof?, private val event: EntityDamageEvent) : Ability(player, Bending.Fire) {
    private val fireTickLength = Duration.ofMillis(1000) // documented on wiki
    private val strengthBuffer = fireTickLength.dividedBy(4) // clutch moments
    private var strengthEffect: PotionEffect? = null

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

    class Listener(private val scheduler: Scheduler) : org.bukkit.event.Listener {
        private val effects = mutableMapOf<UUID, Fireproof>()

        @EventHandler fun onDamage(e: EntityDamageEvent) {
            if (e.entity is Player) effects[e.entity.uniqueId] =
                Fireproof(e.entity as Player, scheduler, effects[e.entity.uniqueId], e).also {
                    it.execute()
                }
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
        val previousEffect = previousFireproof?.strengthEffect
        val currentEffect = getPotionEffect(PotionEffectType.INCREASE_DAMAGE)

        // prevent the strength effect from stacking on top of itself over many ticks
        val amplifier = previousEffect?.amplifier ?: 1 + (currentEffect?.amplifier ?: -1)

        strengthEffect = PotionEffect(
            PotionEffectType.INCREASE_DAMAGE,
            (fireTickLength + strengthBuffer).toTicks().toInt(),
            amplifier
        )

        // give the buffer a buffer to prevent race condition, where the effect is gone before it actually ended
        scheduler.runAfter(fireTickLength + strengthBuffer.multipliedBy(2)) {
            strengthEffect = null
        }

        addPotionEffect(strengthEffect!!)
    }
}