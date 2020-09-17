package mcavatar.abilities.earth

import mcavatar.abilities.Ability
import mcavatar.bukkit.block.center
import mcavatar.bukkit.block.particlize
import mcavatar.bukkit.material.axe
import mcavatar.bukkit.material.has
import mcavatar.bukkit.material.playSound
import mcavatar.bukkit.material.properties
import mcavatar.logger
import mcavatar.scheduler.Scheduler
import mcavatar.scheduler.Task
import org.bukkit.Material
import org.bukkit.entity.FallingBlock
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.inventory.ItemStack

class BoulderToss(private val scheduler: Scheduler, event: BlockDamageEvent) : Ability<BlockDamageEvent>(event, event.player) {
    private val knockback = 2.0
    private val launch = 1.25
    private val shieldTolerance = 1.333

    private val launchDirection = player.location.direction.normalize()
    private val world = player.world
    private lateinit var collisionTask: Task

    override fun preconditions() = with(event) {
        trigger { itemInHand.properties().has<axe>() }
        trigger { block.type == Material.COBBLESTONE }
        trigger { !world.getBlockAt(block.center.add(launchDirection)).type.isSolid }
    }

    override fun action(): Unit = with(event) {
        tossBoulder().onCollision { boulder, target ->
            boulder.playSound { broken }
            boulder.particlize(8)
            if (target !is HumanEntity || !target.isBlocking(boulder)) {
                target.damage(8.0, player)
                target.velocity = target.velocity.add(boulder.velocity.multiply(knockback))
            }
        }
    }

    class Listener(private val scheduler: Scheduler) : org.bukkit.event.Listener {
        @EventHandler fun toss(e: BlockDamageEvent) = BoulderToss(scheduler, e).execute()
    }

    private fun tossBoulder() = with(event) {
        world.spawnFallingBlock(block.center.add(launchDirection.multiply(0.5)), block.blockData).apply {
            velocity = launchDirection.multiply(launch)
            setHurtEntities(false)
        }.also {
            block.playSound { placed }
            block.particlize(8)
            block.type = Material.AIR
        }
    }

    private fun FallingBlock.onCollision(effect: (FallingBlock, LivingEntity) -> Unit) {
        collisionTask = scheduler.onEachTickWhileAlive(this) {
            world.livingEntities.forEach {
                if (!it.isDead && it.boundingBox.overlaps(this.boundingBox)) {
                    effect(this, it)
                    world.dropItemNaturally(location, ItemStack(blockData.material))
                    remove()
                    collisionTask.cancel()
                }
            }
        }
    }

    private fun HumanEntity.isBlocking(boulder: FallingBlock): Boolean {
        return if (isBlocking) {
            val boulderDirection = boulder.velocity.normalize()
            val targetFacing = location.direction.normalize()

            return boulderDirection.add(targetFacing).length() < shieldTolerance
        } else {
            false
        }
    }
}
