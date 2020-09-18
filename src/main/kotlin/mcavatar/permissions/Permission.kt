package mcavatar.permissions

data class Permission(val category: String, val name: String) {
    val qualifiedName get() = "mcavatar.$category.$name"
}