package ru.mikescherbakov.filters.universal

fun main() {
    val list: List<TestKotlinClass> = listOf(TestKotlinClass(123, "abc"))
    list.uFilter(
        UFilter.andGroup()
            .add(FilterItem("length", ComparisonType.EQUALS, 3))
    )
}
