package mcavatar.minecraft

// https://wiki.vg/Protocol#Player_Digging

typealias PacketPlayInBlockDig = net.minecraft.network.protocol.game.PacketPlayInBlockDig

private typealias RawEnumPlayerDigType = net.minecraft.network.protocol.game.PacketPlayInBlockDig.EnumPlayerDigType
enum class EnumPlayerDigType(val raw: RawEnumPlayerDigType) {
    StartedDigging(RawEnumPlayerDigType.a),
    CancelledDigging(RawEnumPlayerDigType.b),
    FinishedDigging(RawEnumPlayerDigType.c),
    DropItemStack(RawEnumPlayerDigType.d),
    DropItem(RawEnumPlayerDigType.e),
    ShootArrowOrFinishEating(RawEnumPlayerDigType.f),
    SwapItemInHand(RawEnumPlayerDigType.g);

    companion object {
        fun fromRaw(raw: RawEnumPlayerDigType): EnumPlayerDigType {
            return values().find { it.raw == raw }!!
        }
    }
}

fun PacketPlayInBlockDig.position() = b()
fun PacketPlayInBlockDig.direction() = c()
fun PacketPlayInBlockDig.digType(): EnumPlayerDigType = EnumPlayerDigType.fromRaw(d())