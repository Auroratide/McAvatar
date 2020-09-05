package mcavatar.minecraft

import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer
import org.bukkit.entity.Player

typealias PacketPlayOutAnimation = net.minecraft.server.v1_16_R1.PacketPlayOutAnimation
enum class ClientAnimation(val value: Int) {
    SWING_MAIN_ARM(0),
    TAKE_DAMAGE(1),
    LEAVE_BED(2),
    SWING_OFFHAND(3),
    CRITICAL_EFFECT(4),
    MAGIC_CRITICAL_EFFECT(5)
}

fun Packet.Animation(player: Player, animation: ClientAnimation) =
    PacketPlayOutAnimation((player as CraftPlayer).handle, animation.value)