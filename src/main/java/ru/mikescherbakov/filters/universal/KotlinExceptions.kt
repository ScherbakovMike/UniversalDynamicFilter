package ru.mikescherbakov.filters.universal

/**
 * Exceptions:
 * MethodInvokeException - exception on invoking the method
 * FieldGetException - exception on getting field value
 * PathResolveException - exception on resolving field or method path
 */
class MethodInvokeException(source: Exception, message: String) : Exception(message, source)
class FieldGetException(source: Exception, message: String) : Exception(message, source)
class PathResolveException(message: String) : Exception(message)
