package mcavatar.math

import org.bukkit.util.Vector

operator fun Vector.plus(other: Vector) =
    clone().add(other)

operator fun Vector.minus(other: Vector) =
    clone().subtract(other)

infix fun Vector.scaleBy(factor: Int) =
    clone().multiply(factor)

infix fun Vector.scaleBy(factor: Double) =
    clone().multiply(factor)

fun Vector.negate() =
    clone() scaleBy -1

infix fun Vector.dot(other: Vector) =
    dot(other)

infix fun Vector.cross(other: Vector) =
    clone().crossProduct(other)