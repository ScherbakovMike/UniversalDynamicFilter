package ru.mikescherbakov.filters.universal

class MethodInvokeException(source: Exception, message:String) : Exception(message, source)
class FieldGetException(source: Exception, message:String) : Exception(message, source)
class PathResolveException(message: String) : Exception(message)