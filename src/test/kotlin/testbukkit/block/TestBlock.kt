package testbukkit.block

import org.bukkit.*
import org.bukkit.block.*
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.MetadataValue
import org.bukkit.plugin.Plugin
import org.bukkit.util.BoundingBox
import org.bukkit.util.RayTraceResult
import org.bukkit.util.Vector
import org.bukkit.util.VoxelShape

class TestBlock(
    private val world: World,
    private var type: Material,
    private var x: Int = 0,
    private var y: Int = 0,
    private var z: Int = 0,
) : Block {
    override fun setMetadata(p0: String, p1: MetadataValue) {
        TODO("Not yet implemented")
    }

    override fun getMetadata(p0: String): MutableList<MetadataValue> {
        TODO("Not yet implemented")
    }

    override fun hasMetadata(p0: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeMetadata(p0: String, p1: Plugin) {
        TODO("Not yet implemented")
    }

    override fun getData(): Byte {
        TODO("Not yet implemented")
    }

    override fun getBlockData(): BlockData {
        TODO("Not yet implemented")
    }

    override fun getRelative(p0: Int, p1: Int, p2: Int): Block {
        TODO("Not yet implemented")
    }

    override fun getRelative(p0: BlockFace): Block {
        TODO("Not yet implemented")
    }

    override fun getRelative(p0: BlockFace, p1: Int): Block {
        TODO("Not yet implemented")
    }

    override fun getType() = type

    override fun getLightLevel(): Byte {
        TODO("Not yet implemented")
    }

    override fun getLightFromSky(): Byte {
        TODO("Not yet implemented")
    }

    override fun getLightFromBlocks(): Byte {
        TODO("Not yet implemented")
    }

    override fun getWorld() = world

    override fun getX() = x
    override fun getY() = y
    override fun getZ() = z

    override fun getLocation(): Location {
        TODO("Not yet implemented")
    }

    override fun getLocation(p0: Location?): Location? {
        TODO("Not yet implemented")
    }

    override fun getChunk(): Chunk {
        TODO("Not yet implemented")
    }

    override fun setBlockData(p0: BlockData) {
        TODO("Not yet implemented")
    }

    override fun setBlockData(p0: BlockData, p1: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setType(p0: Material) {
        TODO("Not yet implemented")
    }

    override fun setType(p0: Material, p1: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getFace(p0: Block): BlockFace? {
        TODO("Not yet implemented")
    }

    override fun getState(): BlockState {
        TODO("Not yet implemented")
    }

    override fun getBiome(): Biome {
        TODO("Not yet implemented")
    }

    override fun setBiome(p0: Biome) {
        TODO("Not yet implemented")
    }

    override fun isBlockPowered(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isBlockIndirectlyPowered(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isBlockFacePowered(p0: BlockFace): Boolean {
        TODO("Not yet implemented")
    }

    override fun isBlockFaceIndirectlyPowered(p0: BlockFace): Boolean {
        TODO("Not yet implemented")
    }

    override fun getBlockPower(p0: BlockFace): Int {
        TODO("Not yet implemented")
    }

    override fun getBlockPower(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isLiquid(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getTemperature(): Double {
        TODO("Not yet implemented")
    }

    override fun getHumidity(): Double {
        TODO("Not yet implemented")
    }

    override fun getPistonMoveReaction(): PistonMoveReaction {
        TODO("Not yet implemented")
    }

    override fun breakNaturally(): Boolean {
        TODO("Not yet implemented")
    }

    override fun breakNaturally(p0: ItemStack?): Boolean {
        TODO("Not yet implemented")
    }

    override fun applyBoneMeal(p0: BlockFace): Boolean {
        TODO("Not yet implemented")
    }

    override fun getDrops(): MutableCollection<ItemStack> {
        TODO("Not yet implemented")
    }

    override fun getDrops(p0: ItemStack?): MutableCollection<ItemStack> {
        TODO("Not yet implemented")
    }

    override fun getDrops(p0: ItemStack, p1: Entity?): MutableCollection<ItemStack> {
        TODO("Not yet implemented")
    }

    override fun isPreferredTool(p0: ItemStack): Boolean {
        TODO("Not yet implemented")
    }

    override fun getBreakSpeed(p0: Player): Float {
        TODO("Not yet implemented")
    }

    override fun isPassable(): Boolean {
        TODO("Not yet implemented")
    }

    override fun rayTrace(p0: Location, p1: Vector, p2: Double, p3: FluidCollisionMode): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun getBoundingBox(): BoundingBox {
        TODO("Not yet implemented")
    }

    override fun getCollisionShape(): VoxelShape {
        TODO("Not yet implemented")
    }

    class Builder(val block: TestBlock) {
        fun at(x: Int, y: Int, z: Int) {
            block.x = x
            block.y = y
            block.z = z
        }
    }
}