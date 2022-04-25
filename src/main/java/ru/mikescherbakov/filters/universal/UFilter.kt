package ru.mikescherbakov.filters.universal

class UFilter {
    fun <T> execute(it: T): Boolean {
        return applyFilter(this.group, it)
    }

    private fun <T> applyFilter(group: FilterGroup, it: T): Boolean {
        var result:Boolean = true

        return result
    }

    companion object {
        fun andGroup():UFilter {
            return UFilter().apply {
                group = FilterGroup(GroupType.AND)
            }
        }

        fun orGroup():UFilter {
            return UFilter().apply {
                group = FilterGroup(GroupType.OR)
            }
        }
    }

    lateinit var group: FilterGroup
}

fun UFilter.add(item: FilterItem):UFilter {
    group.add(item)
    return this
}

fun UFilter.add(group: FilterGroup):UFilter {
    group.add(group)
    return this
}

fun <T>Iterable<T>.uFilter(filter:UFilter):Iterable<T> {
    val result:MutableList<T> = mutableListOf()
    this.forEach {
        if(filter.execute(it))
            result.add(it)
    }
    return result
}

/*
list.uFilter(
UFilter.
.add(FilterItem("elem.length", ComparisonType.EQUALS, 3))
)
 */
