package mcavatar.math

import kotlin.random.Random

data class Percent(val outOf100: Double) {
    fun random(random: Random = Random) = random.nextDouble() * 100 < outOf100
}