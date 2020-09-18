package mcavatar.bukkit.entity

import org.bukkit.Sound
import org.bukkit.entity.HumanEntity

object HumanEntitySounds {
    val shielding = Sound.ITEM_SHIELD_BLOCK
}

fun HumanEntity.playSound(getSound: HumanEntitySounds.() -> Sound) =
    world.playSound(location, HumanEntitySounds.getSound(), 1.0f, 0.75f)
