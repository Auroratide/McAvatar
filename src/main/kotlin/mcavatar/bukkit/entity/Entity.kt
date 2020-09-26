package mcavatar.bukkit.entity

import org.bukkit.FluidCollisionMode
import org.bukkit.entity.Entity
import org.bukkit.util.Vector

private const val epsilon = 0.01

interface LocationDescriptor {
    fun isMet(e: Entity): Boolean
}

object inAir : LocationDescriptor {
    override fun isMet(e: Entity) =
        null == e.world.rayTraceBlocks(e.location, Vector(0, -1, 0), epsilon, FluidCollisionMode.ALWAYS, true)
}

object onGround : LocationDescriptor {
    override fun isMet(e: Entity) =
        null != e.world.rayTraceBlocks(e.location, Vector(0, -1, 0), epsilon, FluidCollisionMode.NEVER, true)
}

infix fun Entity.located(description: LocationDescriptor) =
    description.isMet(this)