package ru.mikescherbakov.filters.universal

sealed class FilterItem<T>(val field: String, val value: T? = null) {
    val fieldValue = { item:Any? -> resolveFieldValue(field, item) }

    abstract fun match(item:Any?): Boolean

    class Equals<T>(field_: String, value_: T) : FilterItem<T>(field_, value_) {
        override fun match(item: Any?): Boolean =
            value == fieldValue(item)
    }

    class NotEquals<T>(field_: String, value_: T) : FilterItem<T>(field_, value_) {
        override fun match(item: Any?): Boolean =
            value != fieldValue(item)
    }

    class Filled<T>(field_: String) : FilterItem<T>(field_) {
        override fun match(item: Any?): Boolean {
            val fieldValue_ = fieldValue(item)
            return when {
                fieldValue_ is List<*> -> (fieldValue_).isNotEmpty()
                fieldValue_ is String -> (fieldValue_).isNotBlank()
                fieldValue_ is Number -> (fieldValue_).toDouble() != 0.0
                fieldValue_ is Boolean -> true
                else -> true
            }
        }
    }

    class NotFilled<T>(field_: String) : FilterItem<T>(field_) {
        override fun match(item: Any?): Boolean {
            val fieldValue_ = fieldValue(item)
            return when {
                fieldValue_ is List<*> -> (fieldValue_).isEmpty()
                fieldValue_ is String -> (fieldValue_).isBlank()
                fieldValue_ is Number -> (fieldValue_).toDouble() == 0.0
                fieldValue_ is Boolean -> false
                else -> true
            }
        }
    }

    class Less<T>(field_: String, value_: T) : FilterItem<T>(field_, value_) {
        override fun match(item: Any?): Boolean =
            (value as Comparable<T>) > fieldValue(item) as T
    }

    class LessOrEqual<T>(field_: String, value_: T) : FilterItem<T>(field_, value_) {
        override fun match(item: Any?): Boolean =
            (value as Comparable<T>) >= fieldValue(item) as T
    }

    class Greater<T>(field_: String, value_: T) : FilterItem<T>(field_, value_) {
        override fun match(item: Any?): Boolean =
            (value as Comparable<T>) < fieldValue(item) as T
    }

    class GreaterOrEqual<T>(field_: String, value_: T) : FilterItem<T>(field_, value_) {
        override fun match(item: Any?): Boolean =
            (value as Comparable<T>) <= fieldValue(item) as T
    }

    class InTheList<T>(field_: String, value_: T) : FilterItem<T>(field_, value_) {
        override fun match(item: Any?): Boolean =
            (fieldValue(item) as List<T>).contains(value)
    }

    class NotInTheList<T>(field_: String, value_: T) : FilterItem<T>(field_, value_) {
        override fun match(item: Any?): Boolean =
            !(fieldValue(item) as List<T>).contains(value)
    }
}

//    inline fun <reified T> EQUALS(a: T, b: T) = a == b
//    inline fun <reified T> NOTEQUALS(a: T, b: T) = a != b
//    inline fun <reified T : Comparable<T>> LESS(a: T, b: T) = a < b
//    inline fun <reified T : Comparable<T>> LESSOREQUAL(a: T, b: T) = a <= b
//    inline fun <reified T : Comparable<T>> GREATER(a: T, b: T) = a > b
//    inline fun <reified T : Comparable<T>> GREATEROREQUAL(a: T, b: T) = a >= b
//    inline fun <reified T> FILLED(a: T): Boolean =
//        when {
//            a is List<*> -> a.isNotEmpty()
//            a is String -> a.isNotBlank()
//            a is Number -> a.toDouble() > 0.0
//            a is Boolean -> true
//            else -> true
//        }
//
//    inline fun <reified T> NOTFILLED(a: T): Boolean =
//        when {
//            a is List<*> -> a.isEmpty()
//            a is String -> a.isBlank()
//            a is Number -> a.toDouble() == 0.0
//            a is Boolean -> false
//            else -> false
//        }
//
//    inline fun <reified V> INTHELIST(a: V, list: List<V>) = list.contains(a)
//    inline fun <reified V> NOTINTHELIST(a: V, list: List<V>) = !list.contains(a)
//
//    fun execute(item: Any): Boolean {
//        val fieldValue = resolveFieldValue(field, item)
//
//        return when (comparisonType) {
//            ComparisonType.EQUALS -> EQUALS(fieldValue, value)
//            ComparisonType.NOTEQUALS -> NOTEQUALS(fieldValue, value)
//            ComparisonType.LESS -> LESS(fieldValue as Comparable<Any>, value as Comparable<Any>)
//            ComparisonType.LESSOREQUAL -> LESSOREQUAL(fieldValue as Comparable<Any>, value as Comparable<Any>)
//            ComparisonType.GREATER -> GREATER(fieldValue as Comparable<Any>, value as Comparable<Any>)
//            ComparisonType.GREATEROREQUAL -> GREATEROREQUAL(
//                fieldValue as Comparable<Any>,
//                value as Comparable<Any>
//            )
//            ComparisonType.FILLED -> FILLED(value)
//            ComparisonType.NOTFILLED -> NOTFILLED(value)
//            ComparisonType.INTHELIST -> INTHELIST(value, fieldValue as List<Any>)
//            ComparisonType.NOTINTHELIST -> NOTINTHELIST(value, fieldValue as List<Any>)
//        }
//    }
//
//}