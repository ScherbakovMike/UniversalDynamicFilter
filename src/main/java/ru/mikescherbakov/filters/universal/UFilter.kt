package ru.mikescherbakov.filters.universal

class UFilter {
    lateinit var group: FilterGroup

    companion object {
        fun andGroup() = FilterGroup(GroupType.AND)
        fun orGroup() = FilterGroup(GroupType.OR)
    }

    fun execute(it: Any): Boolean {
        return this.group.execute(it)
    }

    fun add(item: FilterItem<*>): UFilter {
        group.add(item)
        return this
    }
    fun add(group: FilterGroup): UFilter {
        this.group = group
        return this
    }
}
