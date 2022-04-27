package ru.mikescherbakov.filters.universal

fun main() {
//    val list1: List<TestKotlinClass> = listOf(TestKotlinClass(123, "abc"))
//    list1.uFilter(
//        UFilter.andGroup()
//            .add(FilterItem("privateMethod().length", ComparisonType.EQUALS, 20))
//    ).forEach(::println)

    val list2: List<TestJavaClass> = listOf(TestJavaClass(123, "abc"))
    list2.uFilter(
        UFilter.andGroup()
            .add(FilterItem("stringField", ComparisonType.NOTFILLED, 3))
    ).forEach(::println)
}
