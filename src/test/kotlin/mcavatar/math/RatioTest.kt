package mcavatar.math

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class RatioTest {
    @Test
    fun `different ratios map to a number in range`() {
        assertThat(Ratio(0, 6).numberInRange(0..100)).isEqualTo(0)
        assertThat(Ratio(1, 6).numberInRange(0..100)).isEqualTo(17)
        assertThat(Ratio(2, 6).numberInRange(0..100)).isEqualTo(33)
        assertThat(Ratio(3, 6).numberInRange(0..100)).isEqualTo(50)
        assertThat(Ratio(4, 6).numberInRange(0..100)).isEqualTo(67)
        assertThat(Ratio(5, 6).numberInRange(0..100)).isEqualTo(83)
        assertThat(Ratio(6, 6).numberInRange(0..100)).isEqualTo(100)
    }

    @Test
    fun `a ratio maps to the correct number in different ranges`() {
        assertThat(Ratio(2, 7).numberInRange(0..1)).isEqualTo(0)
        assertThat(Ratio(2, 7).numberInRange(0..10)).isEqualTo(3)
        assertThat(Ratio(2, 7).numberInRange(0..20)).isEqualTo(6)
        assertThat(Ratio(2, 7).numberInRange(0..30)).isEqualTo(9)
        assertThat(Ratio(2, 7).numberInRange(0..50)).isEqualTo(14)
        assertThat(Ratio(2, 7).numberInRange(0..70)).isEqualTo(20)
        assertThat(Ratio(2, 7).numberInRange(30..100)).isEqualTo(50)
    }
}