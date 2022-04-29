package ru.mikescherbakov.filters.universal

fun main() {
    val list1: List<TestKotlinClass> = listOf(TestKotlinClass(123, "abc"))
    list1.uFilter(
        UFilter.andGroup()
            .add(FilterItem.Equals("privateMethod().length",20))
    ).forEach(::println)

    val list2: List<TestJavaClass> = listOf(TestJavaClass(123, "abc"))
    list2.uFilter(
        UFilter.andGroup()
            .add(FilterItem.Equals("stringField.length", 3))
    ).forEach(::println)

    val list3: List<String> = listOf("123", "456", "7890")
    list3.uFilter(
        UFilter.orGroup()
            .add(FilterItem.GreaterOrEqual("it", "456"))).forEach(::println)

}
