# McAvatar

McAvatar is a Minecraft Spigot plugin for granting players elemental bending abilities. The plugin is not complete, but currently offers earthbending and firebending.

## Development

The code is in Kotlin instead of Java because Kotlin is kool (aka, code tends to be more expressive, readable, and concise).

A custom Spigot gradle plugin is used to automatically download the current Minecraft version. In theory, all you have to do is this to get started:

```
# Download Spigot
./gradlew spigotBuild

# Run the Server
./gradlew spigotRun
```

If running for the first time, you may need to accept the EULA.

## Testing

Testing a spigot plugin is admittedly difficult, but the World can be faked using a test double. Here's an example test:

```kotlin
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
```
