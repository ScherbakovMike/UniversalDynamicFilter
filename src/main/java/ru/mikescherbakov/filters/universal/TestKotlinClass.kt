package ru.mikescherbakov.filters.universal

class TestKotlinClass(val a: Int, val b: String) {
    private fun privateMethod(): String {
        return "private method print"
    }

    fun nonPrivateMethod(): String {
        return "non private method print"
    }
}