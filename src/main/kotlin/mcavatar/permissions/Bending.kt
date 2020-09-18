package mcavatar.permissions

enum class Bending {
    Earth, Fire, Water, Air;

    val permission = Permission("abilities", name.toLowerCase())
}