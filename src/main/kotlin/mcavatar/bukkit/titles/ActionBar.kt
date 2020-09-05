package mcavatar.bukkit.titles

import mcavatar.PacketSender
import mcavatar.minecraft.EnumTitleAction
import mcavatar.minecraft.Packet
import mcavatar.minecraft.Title
import mcavatar.minecraft.chatText
import org.bukkit.entity.Player

val Player.actionBar get() = ActionBar(this)

class ActionBar(private val player: Player) {
    fun warn(text: String) {
        PacketSender().send(player, Packet.Title(EnumTitleAction.ACTIONBAR, chatText(text), 1, 20, 1))
    }
}