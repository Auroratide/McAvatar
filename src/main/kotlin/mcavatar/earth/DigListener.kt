package mcavatar.earth

import mcavatar.*
import mcavatar.block.earthy
import mcavatar.block.playSound
import mcavatar.block.properties
import mcavatar.minecraft.*
import mcavatar.scheduler.Scheduler
import mcavatar.scheduler.Task
import mcavatar.scheduler.ticks
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDamageEvent

class DigListener(private val scheduler: Scheduler) : Listener {
    private val breakTasks = mutableMapOf<Player, Task>()

    @EventHandler fun startDig(e: BlockDamageEvent) {
        if (e.block.properties().contains(earthy)) {
            breakTasks[e.player] = scheduler.runAfter(10.ticks) {
                e.block.playSound { broken }
                e.block.breakNaturally()
            } + scheduler.onEachTickFor(10.ticks) {
                PacketSender().send(e.player, Packet.BlockBreakAnimation(e.player, e.block, Breakage(it)))
            }
        }
    }

    fun cancelDig(packet: PacketPlayInBlockDig, player: Player) {
        if (packet.digType() == EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
            breakTasks[player]?.cancel()
        }
    }
}
