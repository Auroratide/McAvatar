package mcavatar

import mcavatar.minecraft.EnumPlayerDigType
import mcavatar.minecraft.PacketPlayInBlockDig
import mcavatar.minecraft.digType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.scheduler.BukkitTask

class DigListener(private val scheduler: Scheduler) : Listener {
    private val breakTasks = mutableMapOf<Player, BukkitTask>()

    @EventHandler fun startDig(e: BlockDamageEvent) {
        breakTasks[e.player] = scheduler.runAfter(10.ticks) {
            e.block.breakNaturally()
        }
    }

    fun cancelDig(packet: PacketPlayInBlockDig, player: Player) {
        if (packet.digType() == EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
            breakTasks[player]?.cancel()
        }
    }
}