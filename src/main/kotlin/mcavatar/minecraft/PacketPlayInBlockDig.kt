package mcavatar.minecraft

typealias PacketPlayInBlockDig = net.minecraft.server.v1_16_R1.PacketPlayInBlockDig
typealias EnumPlayerDigType = net.minecraft.server.v1_16_R1.PacketPlayInBlockDig.EnumPlayerDigType

fun PacketPlayInBlockDig.position() = b()
fun PacketPlayInBlockDig.direction() = c()
fun PacketPlayInBlockDig.digType(): EnumPlayerDigType = d()