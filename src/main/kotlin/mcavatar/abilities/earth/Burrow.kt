package mcavatar.abilities.earth

import mcavatar.*
import mcavatar.abilities.Ability
import mcavatar.bukkit.block.showBreakage
import mcavatar.bukkit.material.*
import mcavatar.math.Ratio
import mcavatar.minecraft.*
import mcavatar.permissions.Bending
import mcavatar.scheduler.*
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockDamageEvent
import java.lang.Float.max

class Burrow(private val scheduler: Scheduler, private val event: BlockDamageEvent) : Ability(event.player, Bending.Earth) {
    private val timeToBreak = 4.ticks * max(event.block.type.hardness, 0.75f)
    private var task: Task? = null

    override fun preconditions() = with(event) {
        trigger { block.properties().has<earthy>() }
        trigger { !itemInHand.properties().has<tool>() }
    }

    override fun action() = with(event) {
        task = scheduler.runAfter(timeToBreak) {
            block.playSound { broken }
            block.breakNaturally()
        } + scheduler.onEachTickFor(timeToBreak) {
            block.showBreakage(Ratio(it, timeToBreak.toTicks().toInt()), player)
        }
    }

    fun cancel() = task?.cancel()

    class Listener(private val scheduler: Scheduler) : org.bukkit.event.Listener {
        private val burrowTasks = mutableMapOf<Player, Burrow>()

        @EventHandler fun start(e: BlockDamageEvent) {
            burrowTasks[e.player] = Burrow(scheduler, e).also { it.execute() }
        }

        @PacketHandler fun cancelDig(packet: PacketPlayInBlockDig, player: Player) {
            if (packet.digType() == EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
                burrowTasks[player]?.cancel()
            }
        }
    }
}
