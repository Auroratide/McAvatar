package mcavatar.math

import kotlin.math.roundToInt

data class Ratio(val numerator: Int, val denominator: Int) {
    fun numberInRange(range: IntRange): Int =
        (numerator.toDouble() / denominator.toDouble() * (range.last - range.first) + range.first).roundToInt()
}