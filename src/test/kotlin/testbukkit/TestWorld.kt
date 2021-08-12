package testbukkit

import mcavatar.math.scaleBy
import org.bukkit.*
import org.bukkit.block.Biome
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import org.bukkit.boss.DragonBattle
import org.bukkit.entity.*
import org.bukkit.generator.BlockPopulator
import org.bukkit.generator.ChunkGenerator
import org.bukkit.inventory.ItemStack
import org.bukkit.material.MaterialData
import org.bukkit.metadata.MetadataValue
import org.bukkit.plugin.Plugin
import org.bukkit.util.BoundingBox
import org.bukkit.util.Consumer
import org.bukkit.util.RayTraceResult
import org.bukkit.util.Vector
import testbukkit.block.TestBlock
import testbukkit.entity.TestEntity
import java.io.File
import java.util.*
import java.util.function.Predicate

class TestWorld : World {
    private val entities: MutableList<Entity> = mutableListOf()
    private val blocks: MutableList<Block> = mutableListOf()

    override fun sendPluginMessage(p0: Plugin, p1: String, p2: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun getListeningPluginChannels(): MutableSet<String> {
        TODO("Not yet implemented")
    }

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

    override fun getBlockAt(x: Int, y: Int, z: Int): Block {
        return blocks.find {
            it.x == x && it.y == y && it.z == z
        } ?: TestBlock(this, type = Material.AIR, x = x, y = y, z = z)
    }

    override fun getBlockAt(location: Location): Block {
        return getBlockAt(location.blockX, location.blockY, location.blockZ)
    }

    override fun getHighestBlockYAt(p0: Int, p1: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockYAt(p0: Location): Int {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockYAt(p0: Int, p1: Int, p2: HeightMap): Int {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockYAt(p0: Location, p1: HeightMap): Int {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockAt(p0: Int, p1: Int): Block {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockAt(p0: Location): Block {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockAt(p0: Int, p1: Int, p2: HeightMap): Block {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockAt(p0: Location, p1: HeightMap): Block {
        TODO("Not yet implemented")
    }

    override fun getChunkAt(p0: Int, p1: Int): Chunk {
        TODO("Not yet implemented")
    }

    override fun getChunkAt(p0: Location): Chunk {
        TODO("Not yet implemented")
    }

    override fun getChunkAt(p0: Block): Chunk {
        TODO("Not yet implemented")
    }

    override fun isChunkLoaded(p0: Chunk): Boolean {
        TODO("Not yet implemented")
    }

    override fun isChunkLoaded(p0: Int, p1: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getLoadedChunks(): Array<Chunk> {
        TODO("Not yet implemented")
    }

    override fun loadChunk(p0: Chunk) {
        TODO("Not yet implemented")
    }

    override fun loadChunk(p0: Int, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun loadChunk(p0: Int, p1: Int, p2: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun isChunkGenerated(p0: Int, p1: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun isChunkInUse(p0: Int, p1: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun unloadChunk(p0: Chunk): Boolean {
        TODO("Not yet implemented")
    }

    override fun unloadChunk(p0: Int, p1: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun unloadChunk(p0: Int, p1: Int, p2: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun unloadChunkRequest(p0: Int, p1: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun regenerateChunk(p0: Int, p1: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun refreshChunk(p0: Int, p1: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun isChunkForceLoaded(p0: Int, p1: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun setChunkForceLoaded(p0: Int, p1: Int, p2: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getForceLoadedChunks(): MutableCollection<Chunk> {
        TODO("Not yet implemented")
    }

    override fun addPluginChunkTicket(p0: Int, p1: Int, p2: Plugin): Boolean {
        TODO("Not yet implemented")
    }

    override fun removePluginChunkTicket(p0: Int, p1: Int, p2: Plugin): Boolean {
        TODO("Not yet implemented")
    }

    override fun removePluginChunkTickets(p0: Plugin) {
        TODO("Not yet implemented")
    }

    override fun getPluginChunkTickets(p0: Int, p1: Int): MutableCollection<Plugin> {
        TODO("Not yet implemented")
    }

    override fun getPluginChunkTickets(): MutableMap<Plugin, MutableCollection<Chunk>> {
        TODO("Not yet implemented")
    }

    override fun dropItem(p0: Location, p1: ItemStack): Item {
        TODO("Not yet implemented")
    }

    override fun dropItem(p0: Location, p1: ItemStack, p2: Consumer<Item>?): Item {
        TODO("Not yet implemented")
    }

    override fun dropItemNaturally(p0: Location, p1: ItemStack): Item {
        TODO("Not yet implemented")
    }

    override fun dropItemNaturally(p0: Location, p1: ItemStack, p2: Consumer<Item>?): Item {
        TODO("Not yet implemented")
    }

    override fun spawnArrow(p0: Location, p1: Vector, p2: Float, p3: Float): Arrow {
        TODO("Not yet implemented")
    }

    override fun <T : AbstractArrow?> spawnArrow(p0: Location, p1: Vector, p2: Float, p3: Float, p4: Class<T>): T {
        TODO("Not yet implemented")
    }

    override fun generateTree(p0: Location, p1: TreeType): Boolean {
        TODO("Not yet implemented")
    }

    override fun generateTree(p0: Location, p1: TreeType, p2: BlockChangeDelegate): Boolean {
        TODO("Not yet implemented")
    }

    override fun spawnEntity(p0: Location, p1: EntityType): Entity {
        TODO("Not yet implemented")
    }

    override fun strikeLightning(p0: Location): LightningStrike {
        TODO("Not yet implemented")
    }

    override fun strikeLightningEffect(p0: Location): LightningStrike {
        TODO("Not yet implemented")
    }

    override fun getEntities(): MutableList<Entity> {
        return entities
    }

    override fun getLivingEntities(): MutableList<LivingEntity> {
        TODO("Not yet implemented")
    }

    override fun <T : Entity?> getEntitiesByClass(vararg p0: Class<T>?): MutableCollection<T> {
        TODO("Not yet implemented")
    }

    override fun <T : Entity?> getEntitiesByClass(p0: Class<T>): MutableCollection<T> {
        TODO("Not yet implemented")
    }

    override fun getEntitiesByClasses(vararg p0: Class<*>?): MutableCollection<Entity> {
        TODO("Not yet implemented")
    }

    override fun getPlayers(): MutableList<Player> {
        TODO("Not yet implemented")
    }

    override fun getNearbyEntities(p0: Location, p1: Double, p2: Double, p3: Double): MutableCollection<Entity> {
        TODO("Not yet implemented")
    }

    override fun getNearbyEntities(
        p0: Location,
        p1: Double,
        p2: Double,
        p3: Double,
        p4: Predicate<Entity>?
    ): MutableCollection<Entity> {
        TODO("Not yet implemented")
    }

    override fun getNearbyEntities(p0: BoundingBox): MutableCollection<Entity> {
        TODO("Not yet implemented")
    }

    override fun getNearbyEntities(p0: BoundingBox, p1: Predicate<Entity>?): MutableCollection<Entity> {
        TODO("Not yet implemented")
    }

    override fun rayTraceEntities(p0: Location, p1: Vector, p2: Double): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceEntities(p0: Location, p1: Vector, p2: Double, p3: Double): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceEntities(p0: Location, p1: Vector, p2: Double, p3: Predicate<Entity>?): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceEntities(
        p0: Location,
        p1: Vector,
        p2: Double,
        p3: Double,
        p4: Predicate<Entity>?
    ): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceBlocks(p0: Location, p1: Vector, p2: Double): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceBlocks(p0: Location, p1: Vector, p2: Double, p3: FluidCollisionMode): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceBlocks(
        start: Location,
        direction: Vector,
        maxDistance: Double,
        fluidCollisionMode: FluidCollisionMode,
        ignorePassableBlocks: Boolean
    ): RayTraceResult? {
        val step = direction.clone().normalize().scaleBy(0.005)
        val currentLocation = start.clone()

        while (currentLocation.clone().subtract(start).length() < maxDistance) {
            val block = currentLocation.block
            val isColliding = block.type.isSolid || (fluidCollisionMode == FluidCollisionMode.ALWAYS && block.type == Material.WATER)
            if (isColliding) {
                return RayTraceResult(currentLocation.toVector(), block, null)
            }

            currentLocation.add(step)
        }

        return null
    }

    override fun rayTrace(
        p0: Location,
        p1: Vector,
        p2: Double,
        p3: FluidCollisionMode,
        p4: Boolean,
        p5: Double,
        p6: Predicate<Entity>?
    ): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun getName(): String {
        TODO("Not yet implemented")
    }

    override fun getUID(): UUID {
        TODO("Not yet implemented")
    }

    override fun getSpawnLocation(): Location {
        TODO("Not yet implemented")
    }

    override fun setSpawnLocation(p0: Location): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSpawnLocation(p0: Int, p1: Int, p2: Int, p3: Float): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSpawnLocation(p0: Int, p1: Int, p2: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getTime(): Long {
        TODO("Not yet implemented")
    }

    override fun setTime(p0: Long) {
        TODO("Not yet implemented")
    }

    override fun getFullTime(): Long {
        TODO("Not yet implemented")
    }

    override fun setFullTime(p0: Long) {
        TODO("Not yet implemented")
    }

    override fun getGameTime(): Long {
        TODO("Not yet implemented")
    }

    override fun hasStorm(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setStorm(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getWeatherDuration(): Int {
        TODO("Not yet implemented")
    }

    override fun setWeatherDuration(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun isThundering(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setThundering(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getThunderDuration(): Int {
        TODO("Not yet implemented")
    }

    override fun setThunderDuration(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun isClearWeather(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setClearWeatherDuration(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun getClearWeatherDuration(): Int {
        TODO("Not yet implemented")
    }

    override fun createExplosion(p0: Double, p1: Double, p2: Double, p3: Float): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(p0: Double, p1: Double, p2: Double, p3: Float, p4: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(p0: Double, p1: Double, p2: Double, p3: Float, p4: Boolean, p5: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(
        p0: Double,
        p1: Double,
        p2: Double,
        p3: Float,
        p4: Boolean,
        p5: Boolean,
        p6: Entity?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(p0: Location, p1: Float): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(p0: Location, p1: Float, p2: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(p0: Location, p1: Float, p2: Boolean, p3: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(p0: Location, p1: Float, p2: Boolean, p3: Boolean, p4: Entity?): Boolean {
        TODO("Not yet implemented")
    }

    override fun getEnvironment(): World.Environment {
        TODO("Not yet implemented")
    }

    override fun getSeed(): Long {
        TODO("Not yet implemented")
    }

    override fun getPVP(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setPVP(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getGenerator(): ChunkGenerator? {
        TODO("Not yet implemented")
    }

    override fun save() {
        TODO("Not yet implemented")
    }

    override fun getPopulators(): MutableList<BlockPopulator> {
        TODO("Not yet implemented")
    }

    override fun <T : Entity?> spawn(p0: Location, p1: Class<T>): T {
        TODO("Not yet implemented")
    }

    override fun <T : Entity?> spawn(p0: Location, p1: Class<T>, p2: Consumer<T>?): T {
        TODO("Not yet implemented")
    }

    override fun spawnFallingBlock(p0: Location, p1: MaterialData): FallingBlock {
        TODO("Not yet implemented")
    }

    override fun spawnFallingBlock(p0: Location, p1: BlockData): FallingBlock {
        TODO("Not yet implemented")
    }

    override fun spawnFallingBlock(p0: Location, p1: Material, p2: Byte): FallingBlock {
        TODO("Not yet implemented")
    }

    override fun playEffect(p0: Location, p1: Effect, p2: Int) {
        TODO("Not yet implemented")
    }

    override fun playEffect(p0: Location, p1: Effect, p2: Int, p3: Int) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> playEffect(p0: Location, p1: Effect, p2: T?) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> playEffect(p0: Location, p1: Effect, p2: T?, p3: Int) {
        TODO("Not yet implemented")
    }

    override fun getEmptyChunkSnapshot(p0: Int, p1: Int, p2: Boolean, p3: Boolean): ChunkSnapshot {
        TODO("Not yet implemented")
    }

    override fun setSpawnFlags(p0: Boolean, p1: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getAllowAnimals(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllowMonsters(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getBiome(p0: Int, p1: Int): Biome {
        TODO("Not yet implemented")
    }

    override fun getBiome(p0: Int, p1: Int, p2: Int): Biome {
        TODO("Not yet implemented")
    }

    override fun setBiome(p0: Int, p1: Int, p2: Biome) {
        TODO("Not yet implemented")
    }

    override fun setBiome(p0: Int, p1: Int, p2: Int, p3: Biome) {
        TODO("Not yet implemented")
    }

    override fun getTemperature(p0: Int, p1: Int): Double {
        TODO("Not yet implemented")
    }

    override fun getTemperature(p0: Int, p1: Int, p2: Int): Double {
        TODO("Not yet implemented")
    }

    override fun getHumidity(p0: Int, p1: Int): Double {
        TODO("Not yet implemented")
    }

    override fun getHumidity(p0: Int, p1: Int, p2: Int): Double {
        TODO("Not yet implemented")
    }

    override fun getMinHeight(): Int {
        TODO("Not yet implemented")
    }

    override fun getMaxHeight(): Int {
        TODO("Not yet implemented")
    }

    override fun getLogicalHeight(): Int {
        TODO("Not yet implemented")
    }

    override fun isNatural(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isBedWorks(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasSkyLight(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasCeiling(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isPiglinSafe(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isRespawnAnchorWorks(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasRaids(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isUltraWarm(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getSeaLevel(): Int {
        TODO("Not yet implemented")
    }

    override fun getKeepSpawnInMemory(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setKeepSpawnInMemory(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isAutoSave(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setAutoSave(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setDifficulty(p0: Difficulty) {
        TODO("Not yet implemented")
    }

    override fun getDifficulty(): Difficulty {
        TODO("Not yet implemented")
    }

    override fun getWorldFolder(): File {
        TODO("Not yet implemented")
    }

    override fun getWorldType(): WorldType? {
        TODO("Not yet implemented")
    }

    override fun canGenerateStructures(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isHardcore(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setHardcore(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getTicksPerAnimalSpawns(): Long {
        TODO("Not yet implemented")
    }

    override fun setTicksPerAnimalSpawns(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun getTicksPerMonsterSpawns(): Long {
        TODO("Not yet implemented")
    }

    override fun setTicksPerMonsterSpawns(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun getTicksPerWaterSpawns(): Long {
        TODO("Not yet implemented")
    }

    override fun setTicksPerWaterSpawns(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun getTicksPerWaterAmbientSpawns(): Long {
        TODO("Not yet implemented")
    }

    override fun setTicksPerWaterAmbientSpawns(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun getTicksPerAmbientSpawns(): Long {
        TODO("Not yet implemented")
    }

    override fun setTicksPerAmbientSpawns(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun getMonsterSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun setMonsterSpawnLimit(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun getAnimalSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun setAnimalSpawnLimit(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun getWaterAnimalSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun setWaterAnimalSpawnLimit(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun getWaterAmbientSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun setWaterAmbientSpawnLimit(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun getAmbientSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun setAmbientSpawnLimit(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun playSound(p0: Location, p1: Sound, p2: Float, p3: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(p0: Location, p1: String, p2: Float, p3: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(p0: Location, p1: Sound, p2: SoundCategory, p3: Float, p4: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(p0: Location, p1: String, p2: SoundCategory, p3: Float, p4: Float) {
        TODO("Not yet implemented")
    }

    override fun getGameRules(): Array<String> {
        TODO("Not yet implemented")
    }

    override fun getGameRuleValue(p0: String?): String? {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> getGameRuleValue(p0: GameRule<T>): T? {
        TODO("Not yet implemented")
    }

    override fun setGameRuleValue(p0: String, p1: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun isGameRule(p0: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> getGameRuleDefault(p0: GameRule<T>): T? {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> setGameRule(p0: GameRule<T>, p1: T): Boolean {
        TODO("Not yet implemented")
    }

    override fun getWorldBorder(): WorldBorder {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(p0: Particle, p1: Location, p2: Int) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(p0: Particle, p1: Double, p2: Double, p3: Double, p4: Int) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(p0: Particle, p1: Location, p2: Int, p3: T?) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(p0: Particle, p1: Double, p2: Double, p3: Double, p4: Int, p5: T?) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(p0: Particle, p1: Location, p2: Int, p3: Double, p4: Double, p5: Double) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(
        p0: Particle,
        p1: Double,
        p2: Double,
        p3: Double,
        p4: Int,
        p5: Double,
        p6: Double,
        p7: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        p0: Particle,
        p1: Location,
        p2: Int,
        p3: Double,
        p4: Double,
        p5: Double,
        p6: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        p0: Particle,
        p1: Double,
        p2: Double,
        p3: Double,
        p4: Int,
        p5: Double,
        p6: Double,
        p7: Double,
        p8: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(p0: Particle, p1: Location, p2: Int, p3: Double, p4: Double, p5: Double, p6: Double) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(
        p0: Particle,
        p1: Double,
        p2: Double,
        p3: Double,
        p4: Int,
        p5: Double,
        p6: Double,
        p7: Double,
        p8: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        p0: Particle,
        p1: Location,
        p2: Int,
        p3: Double,
        p4: Double,
        p5: Double,
        p6: Double,
        p7: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        p0: Particle,
        p1: Double,
        p2: Double,
        p3: Double,
        p4: Int,
        p5: Double,
        p6: Double,
        p7: Double,
        p8: Double,
        p9: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        p0: Particle,
        p1: Location,
        p2: Int,
        p3: Double,
        p4: Double,
        p5: Double,
        p6: Double,
        p7: T?,
        p8: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        p0: Particle,
        p1: Double,
        p2: Double,
        p3: Double,
        p4: Int,
        p5: Double,
        p6: Double,
        p7: Double,
        p8: Double,
        p9: T?,
        p10: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override fun locateNearestStructure(p0: Location, p1: StructureType, p2: Int, p3: Boolean): Location? {
        TODO("Not yet implemented")
    }

    override fun getViewDistance(): Int {
        TODO("Not yet implemented")
    }

    override fun spigot(): World.Spigot {
        TODO("Not yet implemented")
    }

    override fun locateNearestRaid(p0: Location, p1: Int): Raid? {
        TODO("Not yet implemented")
    }

    override fun getRaids(): MutableList<Raid> {
        TODO("Not yet implemented")
    }

    override fun getEnderDragonBattle(): DragonBattle? {
        TODO("Not yet implemented")
    }

    class Builder(val world: TestWorld) {
        fun entity(build: TestEntity.Builder.() -> Unit): TestEntity {
            return TestEntity.Builder(TestEntity(world)).apply(build).entity.also {
                world.entities.add(it)
            }
        }

        fun block(type: Material, build: TestBlock.Builder.() -> Unit): TestBlock {
            return TestBlock.Builder(TestBlock(world, type)).apply(build).block.also {
                world.blocks.add(it)
            }
        }
    }
}