package mcavatar.minecraft

import org.bukkit.block.Block
import org.bukkit.entity.Player

typealias PacketPlayOutTitle = net.minecraft.server.v1_16_R2.PacketPlayOutTitle
typealias EnumTitleAction = net.minecraft.server.v1_16_R2.PacketPlayOutTitle.EnumTitleAction
typealias IChatBaseComponent = net.minecraft.server.v1_16_R2.IChatBaseComponent

fun chatText(text: String): IChatBaseComponent =
    net.minecraft.server.v1_16_R2.IChatBaseComponent.ChatSerializer.a("""{"text": "$text"}""") as IChatBaseComponent

fun Packet.Title(type: EnumTitleAction, chat: IChatBaseComponent, fadeIn: Int, stay: Int, fadeOut: Int) =
    PacketPlayOutTitle(type, chat, fadeIn, stay, fadeOut)