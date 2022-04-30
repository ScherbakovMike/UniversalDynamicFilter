package ru.mikescherbakov.filters.universal

/**
 * The main method for execute filter on collection
 * @param filter - union of filter groups or/and filter items
 * @return filtered collection
 */

fun <T : Any> Iterable<T>.uFilter(filter: UFilter): Iterable<T> {
    val result: MutableList<T> = mutableListOf()
    this.forEach {
        if (filter.execute(it))
            result.add(it)
    }
    return result
}
