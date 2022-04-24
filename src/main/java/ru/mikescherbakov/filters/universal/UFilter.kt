package ru.mikescherbakov.filters.universal

class UFilter() {
    private val group: FilterGroup = FilterGroup(mutableListOf(), GroupType.AND)

    fun add(item: FilterItem): UFilter {
        group.add(item)
        return this
    }
}

/*
list.uFilter(
UFilter()
.add(FilterItem("elem.length", ComparisonType.EQUALS, 3))
)
 */
