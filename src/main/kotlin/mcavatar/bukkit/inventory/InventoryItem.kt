package mcavatar.bukkit.inventory

import org.bukkit.Material
import org.bukkit.inventory.Inventory

fun Inventory.item(material: Material) = InventoryItem(this, material)

class InventoryItem(inventory: Inventory, material: Material) {
    private val all = inventory.all(material)

    fun count() =
        all.values.sumBy { it.amount }

    fun removeOne() =
        all.values.find { it.amount > 0 }?.let {
            --it.amount
        }
}
