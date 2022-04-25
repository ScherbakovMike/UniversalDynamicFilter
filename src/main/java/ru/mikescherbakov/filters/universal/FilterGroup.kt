package ru.mikescherbakov.filters.universal

class FilterGroup(
    val groupType: GroupType
) {
    val filterItems: MutableList<FilterItem> = mutableListOf()
    val filterGroups: MutableList<FilterGroup> = mutableListOf()

    fun add(item: FilterItem) = filterItems.add(item)
    fun add(group: FilterGroup) = filterGroups.add(group)
}
