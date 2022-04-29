package ru.mikescherbakov.filters.universal

import java.util.logging.Filter

class FilterGroup(
    val groupType: GroupType
) {
    val filterItems: MutableList<FilterItem<*>> = mutableListOf()
    val filterGroups: MutableList<FilterGroup> = mutableListOf()

    fun add(item: FilterItem<*>) = filterItems.add(item)
    fun add(group: FilterGroup) = filterGroups.add(group)

    fun execute(item: Any): Boolean {
        val result: Boolean = this.groupType == GroupType.AND

        val itemsResults: MutableMap<FilterItem<*>, Boolean> = mutableMapOf()
        this.filterItems.forEach {
            val itemResult: Boolean = it.match(item)
            itemsResults[it] = itemResult
            if (!itemResult && this.groupType == GroupType.AND) {
                return false
            }
            if (itemResult && this.groupType == GroupType.OR) {
                return true
            }
        }

        val groupsResults: MutableMap<FilterGroup, Boolean> = mutableMapOf()
        this.filterGroups.forEach {
            val groupResult: Boolean = it.execute(item)
            groupsResults[it] = groupResult
            if (!groupResult && this.groupType == GroupType.AND) {
                return false
            }
            if (groupResult && this.groupType == GroupType.OR) {
                return true
            }
        }
        return result
    }
}
