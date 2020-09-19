package mcavatar.abilities.fire

import mcavatar.PacketSender
import mcavatar.abilities.Ability
import mcavatar.minecraft.Animation
import mcavatar.minecraft.ClientAnimation
import mcavatar.minecraft.Packet
import mcavatar.permissions.Bending
import mcavatar.scheduler.toTicks
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import java.time.Duration

class Firesweep(private val event: PlayerInteractEvent) : Ability(event.player, Bending.Fire) {
    private val cooldown = Duration.ofMillis(1500)

    override fun preconditions() = with(event) {
        trigger { hasItem() }
        trigger { action in listOf(Action.RIGHT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR) }
        trigger { hand == EquipmentSlot.HAND }
        trigger { !hasBlock() || !(clickedBlock?.type?.isInteractable ?: false) }
        trigger { item!!.type == Material.FIRE_CHARGE }
        trigger { player.getCooldown(item!!.type) <= 0 }
    }

    override fun action() = with(event) {
        PacketSender().send(player, Packet.Animation(player, ClientAnimation.SWING_MAIN_ARM))

        player.sendMessage("You used firesweep")

        player.setCooldown(item!!.type, cooldown.toTicks().toInt())
    }

    class Listener : org.bukkit.event.Listener {
        @EventHandler fun sweep(e: PlayerInteractEvent) =
            Firesweep(e).execute()
    }
}