package ru.mikescherbakov.filters.universal

class FilterItem(
    val field: String,
    val comparisonType: ComparisonType,
    val value: Any
) {
    fun execute(item: Any): Boolean {
        val value = resolveFieldValue(field, item)
        return true
    }

}