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

class DigListener(private val scheduler: Scheduler) : Listener {
    private val breakTasks = mutableMapOf<Player, Task>()

    @EventHandler fun startDig(e: BlockDamageEvent) {
        if (e.block.properties().has<earthy>() && !e.itemInHand.properties().has<tool>()) {
            breakTasks[e.player] = scheduler.runAfter(e.block.timeToBreak) {
                e.block.playSound { broken }
                e.block.breakNaturally()
            } + scheduler.onEachTickFor(e.block.timeToBreak) {
                PacketSender().send(e.player, Packet.BlockBreakAnimation(e.player, e.block, Breakage.from(Ratio(it, e.block.timeToBreak.toTicks().toInt()))))
            }
        }
    }

    fun cancelDig(packet: PacketPlayInBlockDig, player: Player) {
        if (packet.digType() == EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
            breakTasks[player]?.cancel()
        }
    }

    private val Block.timeToBreak get() = 4.ticks * max(type.hardness, 1.0f)
}
