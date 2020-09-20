package mcavatar.math

import org.bukkit.util.Vector

class Axes(frontDirection: Vector) {
    val front = frontDirection.normalize()
    val left = (front cross Vector(0, 1, 0)).normalize()
    val up = (front cross left).normalize()
    val back = front.negate()
    val right = left.negate()
    val down = up.negate()
}