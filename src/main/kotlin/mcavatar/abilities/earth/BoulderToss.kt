package mcavatar.abilities.earth

import mcavatar.abilities.Ability
import mcavatar.bukkit.material.axe
import mcavatar.bukkit.material.has
import mcavatar.bukkit.material.properties
import org.bukkit.Material
import org.bukkit.entity.Damageable
import org.bukkit.entity.Snowball
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.inventory.ItemStack

class BoulderToss(event: BlockDamageEvent) : Ability<BlockDamageEvent>(event, event.player) {
    override fun preconditions() = with(event) {
        trigger { itemInHand.properties().has<axe>() }
        trigger { block.type == Material.COBBLESTONE }
    }

    override fun action(): Unit = with(event) {
        block.type = Material.AIR
        player.launchProjectile(Snowball::class.java).apply {
            item = ItemStack(Material.COBBLESTONE)
            customName = BOULDER
        }
    }

    class Listener : org.bukkit.event.Listener {
        @EventHandler fun toss(e: BlockDamageEvent) = BoulderToss(e).execute()

        @EventHandler fun hit(e: ProjectileHitEvent) {
            val projectile = e.entity
            val hitEntity = e.hitEntity

            if (projectile.customName == BOULDER && hitEntity is Damageable) {
                hitEntity.damage(8.0)
            }
        }
    }
}

private const val BOULDER = "projectile::boulder"