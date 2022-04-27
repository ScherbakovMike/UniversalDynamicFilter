package ru.mikescherbakov.filters.universal

class UFilter {
    fun execute(it: Any): Boolean {
        return this.group.execute(it)
    }

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

    lateinit var group: FilterGroup
}

fun UFilter.add(item: FilterItem): UFilter {
    group.add(item)
    return this
}

fun UFilter.add(group: FilterGroup): UFilter {
    group.add(group)
    return this
}

fun <T : Any> Iterable<T>.uFilter(filter: UFilter): Iterable<T> {
    val result: MutableList<T> = mutableListOf()
    this.forEach {
        if (filter.execute(it))
            result.add(it)
    }
    return result
}