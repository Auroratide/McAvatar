package mcavatar.bukkit.entity

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.bukkit.Material
import org.junit.jupiter.api.Test
import testbukkit.TestBukkit

class EntityTest {
    @Test
    fun `entity is in the air`() {
        TestBukkit.world {
            val ground = block(Material.DIRT) {
                at(0, 0, 0)
            }

            val player = entity {
                at(0, ground.y + 1.5, 0)
            }

            assertThat(player located inAir).isTrue()
            assertThat(player located onGround).isFalse()
        }
    }

    @Test
    fun `entity is on the ground`() {
        TestBukkit.world {
            val ground = block(Material.DIRT) {
                at(0, 0, 0)
            }

            val player = entity {
                at(0, ground.y + 1, 0)
            }

            assertThat(player located inAir).isFalse()
            assertThat(player located onGround).isTrue()
        }
    }

    @Test
    fun `entity is underwater`() {
        TestBukkit.world {
            val water = block(Material.WATER) {
                at(0, 0, 0)
            }

            val player = entity {
                at(0, water.y + 0.5, 0)
            }

            assertThat(player located inAir).isFalse()
            assertThat(player located onGround).isFalse()
        }
    }
}