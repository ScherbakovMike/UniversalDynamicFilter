package ru.mikescherbakov.filters.universal

import java.lang.reflect.Field
import java.lang.reflect.Method

fun resolveFieldValue(field: String, item: Any): Any? {
    val pathParts = field.split(".")
    var curClass = item::class.java
    var curValue: Any? = null
    var methodClass: Method? = null
    var fieldClass: Field? = null
    pathParts.forEachIndexed { i, elem ->
        val methods = curClass.methods
        methodClass = methods.firstOrNull { it.name == elem }

        val fields = curClass.declaredFields
        fieldClass = fields.firstOrNull { it.name == elem }

        when {
            methodClass != null -> {
                methodClass!!.isAccessible = true
                try {
                    curValue = methodClass!!.invoke(item)
                } catch (e: Exception) {
                    throw MethodInvokeException(e)
                }
            }
            fieldClass != null -> {
                fieldClass!!.isAccessible = true
                curValue = fieldClass!!.get(item)
            }
            else ->
                throw PathResolveException(
                    "Method or Field with name $elem wasn't resolved."
                )
        }
    }

    return curValue
}
