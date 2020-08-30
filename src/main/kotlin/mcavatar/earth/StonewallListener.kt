package mcavatar.earth

import mcavatar.material.axe
import mcavatar.material.has
import mcavatar.material.properties
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDamageEvent

class StonewallListener : Listener {
    @EventHandler fun placeWall(e: BlockDamageEvent) {
        if (e.itemInHand.properties().has<axe>()) {
            e.block.world.getBlockAt(e.block.getRelative(BlockFace.UP).location).type = Material.COBBLESTONE
        }
    }
}