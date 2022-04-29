package ru.mikescherbakov.filters.universal

import java.lang.reflect.Field
import java.lang.reflect.Method

fun resolveFieldValue(field: String, item: Any?): Any? {
    if (item == null) return null

    val pathParts = field.split(".")
    var curClass = item::class.java
    var curValue: Any? = item
    var methodClass: Method? = null
    var fieldClass: Field? = null
    pathParts.forEachIndexed { _, elem_ ->
        var elem = elem_
        if (elem_.endsWith("()")) {
            elem = elem_.replace("()", "")
        }

        if (elem == "it") return@forEachIndexed

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
                    throw MethodInvokeException(
                        e,
                        "Couldn't invoke the method ${methodClass!!.name} on value: $curValue ($curClass)"
                    )
                }
            }
            fieldClass != null -> {
                fieldClass!!.isAccessible = true
                try {
                    curValue = fieldClass!!.get(curValue)
                } catch (e: Exception) {
                    throw FieldGetException(
                        e,
                        "Couldn't get the property ${fieldClass!!.name} of value: $curValue ($curClass)"
                    )
                }
            }
            else ->
                throw PathResolveException(
                    "Method or Field with name $elem_ wasn't resolved on value: $curValue ($curClass)"
                )
        }
        curClass = curValue!!::class.java
    }

    return curValue
}
