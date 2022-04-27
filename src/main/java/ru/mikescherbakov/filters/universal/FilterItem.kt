package ru.mikescherbakov.filters.universal

class FilterItem(
    val field: String,
    val comparisonType: ComparisonType,
    val value: Any? = null
) {

    inline fun <reified T> EQUALS(a: T, b: T) = a == b
    inline fun <reified T> NOTEQUALS(a: T, b: T) = a != b
    inline fun <reified T : Comparable<T>> LESS(a: T, b: T) = a < b
    inline fun <reified T : Comparable<T>> LESSOREQUAL(a: T, b: T) = a <= b
    inline fun <reified T : Comparable<T>> GREATER(a: T, b: T) = a > b
    inline fun <reified T : Comparable<T>> GREATEROREQUAL(a: T, b: T) = a >= b
    inline fun <reified T> FILLED(a: T): Boolean =
        when {
            a is List<*> -> a.isNotEmpty()
            a is String -> a.isNotBlank()
            a is Number -> a.toDouble() > 0.0
            a is Boolean -> true
            else -> true
        }

    inline fun <reified T> NOTFILLED(a: T): Boolean =
        when {
            a is List<*> -> a.isEmpty()
            a is String -> a.isBlank()
            a is Number -> a.toDouble() == 0.0
            a is Boolean -> false
            else -> false
        }

    inline fun <reified V> INTHELIST(a: V, list: List<V>) = list.contains(a)
    inline fun <reified V> NOTINTHELIST(a: V, list: List<V>) = !list.contains(a)

    fun execute(item: Any): Boolean {
        val fieldValue = resolveFieldValue(field, item)

        return when (comparisonType) {
            ComparisonType.EQUALS -> EQUALS(fieldValue, value)
            ComparisonType.NOTEQUALS -> NOTEQUALS(fieldValue, value)
            ComparisonType.LESS -> LESS(fieldValue as Comparable<Any>, value as Comparable<Any>)
            ComparisonType.LESSOREQUAL -> LESSOREQUAL(fieldValue as Comparable<Any>, value as Comparable<Any>)
            ComparisonType.GREATER -> GREATER(fieldValue as Comparable<Any>, value as Comparable<Any>)
            ComparisonType.GREATEROREQUAL -> GREATEROREQUAL(
                fieldValue as Comparable<Any>,
                value as Comparable<Any>
            )
            ComparisonType.FILLED -> FILLED(value)
            ComparisonType.NOTFILLED -> NOTFILLED(value)
            ComparisonType.INTHELIST -> INTHELIST(value, fieldValue as List<Any>)
            ComparisonType.NOTINTHELIST -> NOTINTHELIST(value, fieldValue as List<Any>)
        }
    }

}