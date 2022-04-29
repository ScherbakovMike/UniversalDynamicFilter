package ru.mikescherbakov.filters.universal

class UFilter {
    lateinit var group: FilterGroup

    companion object {
        fun andGroup(): UFilter {
            return UFilter().apply {
                group = FilterGroup(GroupType.AND)
            }
        }

        fun orGroup(): UFilter {
            return UFilter().apply {
                group = FilterGroup(GroupType.OR)
            }
        }
    }

    fun execute(it: Any): Boolean {
        return this.group.execute(it)
    }

    fun add(item: FilterItem<*>): UFilter {
        group.add(item)
        return this
    }

    fun add(group: FilterGroup): UFilter {
        group.add(group)
        return this
    }
}
