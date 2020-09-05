package mcavatar.bukkit.block

import org.bukkit.block.BlockFace

fun BlockFace.perpendicular(): BlockFace {
    return when(this) {
        BlockFace.NORTH -> BlockFace.WEST
        BlockFace.WEST -> BlockFace.SOUTH
        BlockFace.SOUTH -> BlockFace.EAST
        BlockFace.EAST -> BlockFace.NORTH
        BlockFace.NORTH_EAST -> BlockFace.NORTH_WEST
        BlockFace.NORTH_WEST -> BlockFace.SOUTH_WEST
        BlockFace.SOUTH_WEST -> BlockFace.SOUTH_EAST
        BlockFace.SOUTH_EAST -> BlockFace.NORTH_EAST
        BlockFace.NORTH_NORTH_EAST -> BlockFace.WEST_NORTH_WEST
        BlockFace.WEST_NORTH_WEST -> BlockFace.SOUTH_SOUTH_WEST
        BlockFace.SOUTH_SOUTH_WEST -> BlockFace.EAST_SOUTH_EAST
        BlockFace.EAST_SOUTH_EAST -> BlockFace.NORTH_NORTH_EAST
        BlockFace.EAST_NORTH_EAST -> BlockFace.NORTH_NORTH_WEST
        BlockFace.NORTH_NORTH_WEST -> BlockFace.WEST_SOUTH_WEST
        BlockFace.WEST_SOUTH_WEST -> BlockFace.SOUTH_SOUTH_EAST
        BlockFace.SOUTH_SOUTH_EAST -> BlockFace.EAST_NORTH_EAST
        BlockFace.UP -> BlockFace.NORTH
        BlockFace.DOWN -> BlockFace.SOUTH
        BlockFace.SELF -> BlockFace.SELF
    }
}