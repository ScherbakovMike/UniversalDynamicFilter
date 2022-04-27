package ru.mikescherbakov.filters.universal

class MethodInvokeException(val source:Exception): Exception(source) {}
class FieldGetException(val source:Exception): Exception(source)
class PathResolveException(override val message:String): Exception(message)