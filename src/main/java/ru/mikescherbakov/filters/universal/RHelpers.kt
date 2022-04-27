package ru.mikescherbakov.filters.universal

import java.lang.reflect.Field
import java.lang.reflect.Method

fun resolveFieldValue(field: String, item: Any): Any? {
    val pathParts = field.split(".")
    var curClass = item::class.java
    var curValue: Any? = item
    var methodClass: Method? = null
    var fieldClass: Field? = null
    pathParts.forEachIndexed { i, elem_ ->
        var elem = elem_
        if (elem_.endsWith("()")) {
            elem = elem_.replace("()", "")
        }
        val declaredMethods = curClass.declaredMethods
        val methods = curClass.methods
        methodClass = declaredMethods.firstOrNull { it.name == elem }
            ?: methods.firstOrNull { it.name == elem }

        val fields = curClass.declaredFields
        fieldClass = fields.firstOrNull { it.name == elem }

        when {
            methodClass != null -> {
                methodClass!!.isAccessible = true
                try {
                    curValue = methodClass!!.invoke(curValue)
                } catch (e: Exception) {
                    throw MethodInvokeException(e)
                }
            }
            fieldClass != null -> {
                fieldClass!!.isAccessible = true
                curValue = fieldClass!!.get(curValue)
            }
            else ->
                throw PathResolveException(
                    "Method or Field with name $elem wasn't resolved."
                )
        }
        curClass = curValue!!::class.java
    }

    return curValue
}
