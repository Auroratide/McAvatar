package testbukkit.entity

import org.bukkit.EntityEffect
import org.bukkit.Location
import org.bukkit.Server
import org.bukkit.World
import org.bukkit.block.BlockFace
import org.bukkit.block.PistonMoveReaction
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Pose
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.metadata.MetadataValue
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionAttachment
import org.bukkit.permissions.PermissionAttachmentInfo
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.plugin.Plugin
import org.bukkit.util.BoundingBox
import org.bukkit.util.Vector
import java.util.*

class TestEntity(private val world: World) : Entity {
    private var location: Location = Location(world, 0.0, 0.0, 0.0)

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

    override fun isOp(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setOp(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isPermissionSet(p0: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun isPermissionSet(p0: Permission): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasPermission(p0: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasPermission(p0: Permission): Boolean {
        TODO("Not yet implemented")
    }

    override fun addAttachment(p0: Plugin, p1: String, p2: Boolean): PermissionAttachment {
        TODO("Not yet implemented")
    }

    override fun addAttachment(p0: Plugin): PermissionAttachment {
        TODO("Not yet implemented")
    }

    override fun addAttachment(p0: Plugin, p1: String, p2: Boolean, p3: Int): PermissionAttachment? {
        TODO("Not yet implemented")
    }

    override fun addAttachment(p0: Plugin, p1: Int): PermissionAttachment? {
        TODO("Not yet implemented")
    }

    override fun removeAttachment(p0: PermissionAttachment) {
        TODO("Not yet implemented")
    }

    override fun recalculatePermissions() {
        TODO("Not yet implemented")
    }

    override fun getEffectivePermissions(): MutableSet<PermissionAttachmentInfo> {
        TODO("Not yet implemented")
    }

    override fun sendMessage(p0: String) {
        TODO("Not yet implemented")
    }

    override fun sendMessage(vararg p0: String?) {
        TODO("Not yet implemented")
    }

    override fun sendMessage(p0: UUID?, p1: String) {
        TODO("Not yet implemented")
    }

    override fun sendMessage(p0: UUID?, vararg p1: String?) {
        TODO("Not yet implemented")
    }

    override fun getServer(): Server {
        TODO("Not yet implemented")
    }

    override fun getName(): String {
        TODO("Not yet implemented")
    }

    override fun spigot(): Entity.Spigot {
        TODO("Not yet implemented")
    }

    override fun getCustomName(): String? {
        TODO("Not yet implemented")
    }

    override fun setCustomName(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun getPersistentDataContainer(): PersistentDataContainer {
        TODO("Not yet implemented")
    }

    override fun getLocation() = location

    override fun getLocation(p0: Location?): Location? {
        TODO("Not yet implemented")
    }

    override fun setVelocity(p0: Vector) {
        TODO("Not yet implemented")
    }

    override fun getVelocity(): Vector {
        TODO("Not yet implemented")
    }

    override fun getHeight(): Double {
        TODO("Not yet implemented")
    }

    override fun getWidth(): Double {
        TODO("Not yet implemented")
    }

    override fun getBoundingBox(): BoundingBox {
        TODO("Not yet implemented")
    }

    override fun isOnGround(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isInWater(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getWorld() = world

    override fun setRotation(p0: Float, p1: Float) {
        TODO("Not yet implemented")
    }

    override fun teleport(p0: Location): Boolean {
        TODO("Not yet implemented")
    }

    override fun teleport(p0: Location, p1: PlayerTeleportEvent.TeleportCause): Boolean {
        TODO("Not yet implemented")
    }

    override fun teleport(p0: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun teleport(p0: Entity, p1: PlayerTeleportEvent.TeleportCause): Boolean {
        TODO("Not yet implemented")
    }

    override fun getNearbyEntities(p0: Double, p1: Double, p2: Double): MutableList<Entity> {
        TODO("Not yet implemented")
    }

    override fun getEntityId(): Int {
        TODO("Not yet implemented")
    }

    override fun getFireTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun getMaxFireTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun setFireTicks(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun setVisualFire(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isVisualFire(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getFreezeTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun getMaxFreezeTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun setFreezeTicks(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun isFrozen(): Boolean {
        TODO("Not yet implemented")
    }

    override fun remove() {
        TODO("Not yet implemented")
    }

    override fun isDead(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isPersistent(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setPersistent(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getPassenger(): Entity? {
        TODO("Not yet implemented")
    }

    override fun setPassenger(p0: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPassengers(): MutableList<Entity> {
        TODO("Not yet implemented")
    }

    override fun addPassenger(p0: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun removePassenger(p0: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun eject(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getFallDistance(): Float {
        TODO("Not yet implemented")
    }

    override fun setFallDistance(p0: Float) {
        TODO("Not yet implemented")
    }

    override fun setLastDamageCause(p0: EntityDamageEvent?) {
        TODO("Not yet implemented")
    }

    override fun getLastDamageCause(): EntityDamageEvent? {
        TODO("Not yet implemented")
    }

    override fun getUniqueId(): UUID {
        TODO("Not yet implemented")
    }

    override fun getTicksLived(): Int {
        TODO("Not yet implemented")
    }

    override fun setTicksLived(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun playEffect(p0: EntityEffect) {
        TODO("Not yet implemented")
    }

    override fun getType(): EntityType {
        TODO("Not yet implemented")
    }

    override fun isInsideVehicle(): Boolean {
        TODO("Not yet implemented")
    }

    override fun leaveVehicle(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getVehicle(): Entity? {
        TODO("Not yet implemented")
    }

    override fun setCustomNameVisible(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isCustomNameVisible(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setGlowing(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isGlowing(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setInvulnerable(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isInvulnerable(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isSilent(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSilent(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun hasGravity(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setGravity(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getPortalCooldown(): Int {
        TODO("Not yet implemented")
    }

    override fun setPortalCooldown(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun getScoreboardTags(): MutableSet<String> {
        TODO("Not yet implemented")
    }

    override fun addScoreboardTag(p0: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeScoreboardTag(p0: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPistonMoveReaction(): PistonMoveReaction {
        TODO("Not yet implemented")
    }

    override fun getFacing(): BlockFace {
        TODO("Not yet implemented")
    }

    override fun getPose(): Pose {
        TODO("Not yet implemented")
    }

    class Builder(val entity: TestEntity) {
        fun at(x: Number, y: Number, z: Number) {
            entity.location = Location(entity.world, x.toDouble(), y.toDouble(), z.toDouble())
        }
    }
}