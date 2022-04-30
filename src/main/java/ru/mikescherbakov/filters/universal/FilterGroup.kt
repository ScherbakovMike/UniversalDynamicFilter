package ru.mikescherbakov.filters.universal

/**
 * Filter group
 * Types: AND / OR
 * Can contain filter items or other filter groups
 */
class FilterGroup(
    val groupType: GroupType
) {
    val filterItems: MutableList<FilterItem<*>> = mutableListOf()
    val filterGroups: MutableList<FilterGroup> = mutableListOf()

    fun add(item: FilterItem<*>): FilterGroup {
        filterItems.add(item)
        return this
    }
    fun add(group: FilterGroup): FilterGroup {
        filterGroups.add(group)
        return this
    }

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
