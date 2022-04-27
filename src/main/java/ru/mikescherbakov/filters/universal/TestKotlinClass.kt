package ru.mikescherbakov.filters.universal

class TestKotlinClass(val a: Int, val b: String) {
    private fun privateMethod(){
        println("private method print")
    }

    fun nonPrivateMethod() {
        println("non private method print")
    }
}