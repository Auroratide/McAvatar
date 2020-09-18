package mcavatar.permissions

import org.bukkit.entity.Player

fun Player.hasBending(bending: Bending) =
    hasPermission(bending.permission.qualifiedName)