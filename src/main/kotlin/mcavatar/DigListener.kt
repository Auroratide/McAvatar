package mcavatar

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDamageEvent

class DigListener(private val scheduler: Scheduler) : Listener {
    @EventHandler fun startDig(e: BlockDamageEvent) {
        scheduler.runAfter(40.ticks) {
            e.block.breakNaturally()
        }
    }
}