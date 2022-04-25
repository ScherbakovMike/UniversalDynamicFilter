package ru.mikescherbakov.filters.universal

class Test {
    fun main() {
        val list: List<String> = listOf("123", "456", "7890")
        list.uFilter(UFilter.andGroup()
                                .add(FilterItem("length", ComparisonType.EQUALS, 3)))
    }
}
