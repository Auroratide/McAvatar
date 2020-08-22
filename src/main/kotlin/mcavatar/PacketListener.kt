package mcavatar

import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PacketListener(config: Interceptors.() -> Unit) : Listener {
    val readInterceptors = mutableListOf<Any.() -> Unit>()
    val writeInterceptors = mutableListOf<Any.() -> Unit>()

    init {
        Interceptors().config()
    }

    @EventHandler fun onJoin(e: PlayerJoinEvent) {
        val pipeline = (e.player as CraftPlayer).handle.playerConnection.networkManager.channel.pipeline()
        pipeline.addBefore("packet_handler", e.player.name, object : ChannelDuplexHandler() {
            override fun channelRead(context: ChannelHandlerContext, packet: Any) {
                readInterceptors.forEach { packet.it() }
                super.channelRead(context, packet)
            }

            override fun write(context: ChannelHandlerContext, packet: Any, promise: ChannelPromise) {
                writeInterceptors.forEach { packet.it() }
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
        inline fun <reified T> read(crossinline action: T.() -> Unit) {
            readInterceptors.add {
                if (this is T) action()
            }
        }

        inline fun <reified T> write(crossinline action: T.() -> Unit) {
            writeInterceptors.add {
                if (this is T) action()
            }
        }
    }
}