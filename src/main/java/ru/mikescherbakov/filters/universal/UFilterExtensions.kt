package ru.mikescherbakov.filters.universal

fun <T : Any> Iterable<T>.uFilter(filter: UFilter): Iterable<T> {
    val result: MutableList<T> = mutableListOf()
    this.forEach {
        if (filter.execute(it))
            result.add(it)
    }
    return result
}