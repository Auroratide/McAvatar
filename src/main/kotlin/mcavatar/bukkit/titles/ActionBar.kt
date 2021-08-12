package mcavatar.bukkit.titles

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.entity.Player

val Player.actionBar get() = ActionBar(this)

class ActionBar(private val player: Player) {
    fun warn(text: String) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(text))
    }
}