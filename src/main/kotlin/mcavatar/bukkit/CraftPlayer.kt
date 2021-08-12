package mcavatar.bukkit

import net.minecraft.network.NetworkManager
import net.minecraft.server.level.EntityPlayer
import net.minecraft.server.network.PlayerConnection
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer
import org.bukkit.entity.Player

val Player.handle get() = (this as CraftPlayer).handle
val EntityPlayer.playerConnection get() = b
val PlayerConnection.networkManager get() = a
val NetworkManager.channel get() = k
