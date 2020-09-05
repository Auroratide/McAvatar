package mcavatar.earth

import mcavatar.*
import mcavatar.bukkit.material.*
import mcavatar.math.Ratio
import mcavatar.minecraft.*
import mcavatar.scheduler.*
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDamageEvent
import java.lang.Float.max

class Burrow(private val scheduler: Scheduler, private val player: Player, private val block: Block) {
    private val timeToBreak = 4.ticks * max(block.type.hardness, 1.0f)
    lateinit var task: Task

    fun execute() {
        task = scheduler.runAfter(timeToBreak) {
            block.playSound { broken }
            block.breakNaturally()
        } + scheduler.onEachTickFor(timeToBreak) {
            PacketSender().send(player, Packet.BlockBreakAnimation(player, block, Breakage.from(Ratio(it, timeToBreak.toTicks().toInt()))))
        }
    }

    fun cancel() = task.cancel()

    class Listener(private val scheduler: Scheduler) : org.bukkit.event.Listener {
        private val burrowTasks = mutableMapOf<Player, Burrow>()

        @EventHandler fun start(e: BlockDamageEvent) {
            if (e.block.properties().has<earthy>() && !e.itemInHand.properties().has<tool>()) {
                burrowTasks[e.player] = Burrow(scheduler, e.player, e.block).also {
                    it.execute()
                }
            }
        }

        @PacketHandler fun cancelDig(packet: PacketPlayInBlockDig, player: Player) {
            if (packet.digType() == EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
                burrowTasks[player]?.cancel()
            }
        }
    }
}
