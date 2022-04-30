package ru.mikescherbakov.filters.universal

/**
 * Filter item
 * @param field - path to the field or method
 * @param value - value for comparing
 * Supports the next comparing modes:
 * - equals
 * - not equals
 * - filled
 * - not filled
 * - less
 * - less or equal
 * - greater
 * - greater or equal
 * - in the list
 * - not in the list
 */
sealed class FilterItem<T>(val field: String, val value: T? = null) {
    val fieldValue = { item: Any? -> resolveFieldValue(field, item) }

    abstract fun match(item: Any?): Boolean

    class Equals<T>(field_: String, value_: T) : FilterItem<T>(field_, value_) {
        override fun match(item: Any?): Boolean =
            value == fieldValue(item)
    }

    class NotEquals<T>(field_: String, value_: T) : FilterItem<T>(field_, value_) {
        override fun match(item: Any?): Boolean =
            value != fieldValue(item)
    }

    class Filled(field_: String) : FilterItem<Any>(field_) {
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

    class NotFilled(field_: String) : FilterItem<Any>(field_) {
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
            (value as List<T>).contains(fieldValue(item) as T)
    }

    class NotInTheList<T>(field_: String, value_: T) : FilterItem<T>(field_, value_) {
        override fun match(item: Any?): Boolean =
            !(value as List<T>).contains(fieldValue(item) as T)
    }
}
