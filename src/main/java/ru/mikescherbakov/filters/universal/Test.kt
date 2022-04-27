package ru.mikescherbakov.filters.universal

fun main() {
    val list1: List<TestKotlinClass> = listOf(TestKotlinClass(123, "abc"))
    list1.uFilter(
        UFilter.andGroup()
            .add(FilterItem("a", ComparisonType.GREATER, 12))
    )

//    val list2: List<TestJavaClass> = listOf(TestJavaClass(123, "abc"))
//    list2.uFilter(
//        UFilter.andGroup()
//            .add(FilterItem("length", ComparisonType.EQUALS, 3))
//    )
}
