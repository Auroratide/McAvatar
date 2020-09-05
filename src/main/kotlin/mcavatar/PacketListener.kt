package mcavatar

import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PacketListener(config: Interceptors.() -> Unit) : Listener {
    val inbound = mutableListOf<Any.(Player) -> Unit>()
    val outbound = mutableListOf<Any.(Player) -> Unit>()

    init {
        Interceptors().config()
    }

    @EventHandler fun onJoin(e: PlayerJoinEvent) {
        val pipeline = (e.player as CraftPlayer).handle.playerConnection.networkManager.channel.pipeline()
        pipeline.addBefore("packet_handler", e.player.name, object : ChannelDuplexHandler() {
            override fun channelRead(context: ChannelHandlerContext, packet: Any) {
                inbound.forEach { packet.it(e.player) }
                super.channelRead(context, packet)
            }

            override fun write(context: ChannelHandlerContext, packet: Any, promise: ChannelPromise) {
                outbound.forEach { packet.it(e.player) }
                super.write(context, packet, promise)
            }
        })
    }

    @EventHandler fun onLeave(e: PlayerQuitEvent) {
        val channel = (e.player as CraftPlayer).handle.playerConnection.networkManager.channel
        channel.eventLoop().submit {
            channel.pipeline().remove(e.player.name)
        }
    }

    inner class Interceptors {
        inline fun <reified T> inbound(crossinline action: T.(Player) -> Unit) {
            inbound.add {
                if (this is T) action(it)
            }
        }

        inline fun <reified T> outbound(crossinline action: T.(Player) -> Unit) {
            outbound.add {
                if (this is T) action(it)
            }
        }
    }
}