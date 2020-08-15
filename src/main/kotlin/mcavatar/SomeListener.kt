package mcavatar

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDamageEvent

class SomeListener : Listener {
    @EventHandler fun convert(e: BlockDamageEvent) {
        if(e.itemInHand.type == Material.AIR) {
            e.block.breakNaturally()
        }
    }
}