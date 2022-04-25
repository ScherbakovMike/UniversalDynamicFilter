package ru.mikescherbakov.filters.universal

class FilterItem(
    val field: String,
    val comparisonType: ComparisonType,
    val value: Any
) {
    fun execute(item: Any): Boolean {
        val clazz = item::class
        val members = clazz.members
        return true
    }

}