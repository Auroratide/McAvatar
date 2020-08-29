package mcavatar.scheduler

import java.time.Duration
import kotlin.math.roundToLong

const val MILLIS_IN_TICK = 50L
val Int.ticks: Duration get() = Duration.ofMillis(this * MILLIS_IN_TICK)
val Long.ticks: Duration get() = Duration.ofMillis(this * MILLIS_IN_TICK)
fun Duration.toTicks() = toMillis() / MILLIS_IN_TICK

operator fun Duration.times(multiplicand: Float): Duration =
    Duration.ofNanos((toNanos().toDouble() * multiplicand).roundToLong())