package mcavatar.earth

import mcavatar.*
import mcavatar.minecraft.*
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.scheduler.BukkitTask

class DigListener(private val scheduler: Scheduler) : Listener {
    private val breakTasks = mutableMapOf<Player, BukkitTask>()

    @EventHandler fun startDig(e: BlockDamageEvent) {
        if (e.block.properties().contains(earthy)) {
            breakTasks[e.player] = scheduler.runAfter(10.ticks) {
                e.block.breakNaturally()
            }

            // TODO needs to be cancellable
            var i = 0
            scheduler.onEachTickFor(10.ticks) {
                PacketSender().send(e.player, Packet.BlockBreakAnimation(e.player, e.block, Breakage(i++)))
            }
        }
    }

    fun cancelDig(packet: PacketPlayInBlockDig, player: Player) {
        if (packet.digType() == EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
            breakTasks[player]?.cancel()
        }
    }
}
