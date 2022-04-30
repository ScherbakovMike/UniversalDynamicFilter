package ru.mikescherbakov.filters.universal

import TestJavaClass
import TestKotlinClass
import org.junit.jupiter.api.* // ktlint-disable no-wildcard-imports
import org.junit.jupiter.api.Assertions.* // ktlint-disable no-wildcard-imports
import ru.mikescherbakov.filters.universal.UFilter.Companion.andGroup
import ru.mikescherbakov.filters.universal.UFilter.Companion.orGroup

class UFilterTest {
    companion object {
        fun setUp() {
            testJavaClass = TestJavaClass(intField, stringField, booleanField)
            testKotlinClass = TestKotlinClass().also {
                it.intField = intField
                it.stringField = stringField
                it.booleanField = booleanField
            }

            testJavaClassList = listOf(testJavaClass, testJavaClass, testJavaClass)
            testKotlinClassList = listOf(testKotlinClass, testKotlinClass, testKotlinClass)

            testJavaClass.setListField(testKotlinClassList)
            testKotlinClass.listField = testJavaClassList
        }

        lateinit var testJavaClass: TestJavaClass
        lateinit var testKotlinClass: TestKotlinClass
        lateinit var testJavaClassList: List<TestJavaClass>
        lateinit var testKotlinClassList: List<TestKotlinClass>

        val intField: Int = 123456
        val stringField = "Sample text"
        val booleanField = true
    }

    @DisplayName("Java lists test")
    class TestJavaList {
        class OneAndGroup {
            @Test
            @DisplayName("One and group - Int field - Not Equals")
            fun checkJavaListAndGroupIntFieldNotEquals() {
                setUp()
                assertEquals(
                    testJavaClassList.uFilter(
                        UFilter()
                            .add(
                                andGroup()
                                    .add(FilterItem.NotEquals("intField", 123))
                            )
                    ).toList().size,
                    3
                )
            }
            @Test
            @DisplayName("One and group - Int field - Greater")
            fun checkJavaListAndGroupIntFieldGreater() {
                setUp()
                assertEquals(
                    testJavaClassList.uFilter(
                        UFilter()
                            .add(
                                andGroup()
                                    .add(FilterItem.Greater("intField", 123450))
                            )
                    ).toList().size,
                    3
                )
            }
            @Test
            @DisplayName("One and group - Int field - Greater or equals")
            fun checkJavaListAndGroupIntFieldGreaterOrEquals() {
                setUp()
                assertEquals(
                    testJavaClassList.uFilter(
                        UFilter().add(
                            andGroup()
                                .add(FilterItem.GreaterOrEqual("intField", 123456))
                        )
                    ).toList().size,
                    3
                )
            }
            @Test
            @DisplayName("One and group - Int field - Less")
            fun checkJavaListAndGroupIntFieldLess() {
                setUp()
                assertEquals(
                    testJavaClassList.uFilter(
                        UFilter().add(
                            andGroup()
                                .add(FilterItem.Less("intField", 1234567))
                        )
                    ).toList().size,
                    3
                )
            }
            @Test
            @DisplayName("One and group - Int field - Less or equals")
            fun checkJavaListAndGroupIntFieldLessOrEquals() {
                setUp()
                assertEquals(
                    testJavaClassList.uFilter(
                        UFilter().add(
                            andGroup()
                                .add(FilterItem.LessOrEqual("intField", 1234567))
                        )
                    ).toList().size,
                    3
                )
            }
            @Test
            @DisplayName("One and group - Int field - In the list")
            fun checkJavaListAndGroupIntFieldInTheList() {
                setUp()
                assertEquals(
                    testJavaClassList.uFilter(
                        UFilter().add(
                            andGroup()
                                .add(FilterItem.InTheList("intField", listOf(123456, 7890)))
                        )
                    ).toList().size,
                    3
                )
            }
            @Test
            @DisplayName("One and group - Int field - Not in the list")
            fun checkJavaListAndGroupIntFieldNotInTheList() {
                setUp()
                assertEquals(
                    testJavaClassList.uFilter(
                        UFilter().add(
                            andGroup()
                                .add(FilterItem.NotInTheList("intField", listOf(112, 7890)))
                        )
                    ).toList().size,
                    3
                )
            }
            @Test
            @DisplayName("One and group - List field - Filled")
            fun checkJavaListAndGroupListFieldField() {
                setUp()
                assertEquals(
                    testJavaClassList.uFilter(
                        UFilter().add(
                            andGroup()
                                .add(FilterItem.Filled("listField"))
                        )
                    ).toList().size,
                    3
                )
            }
        }

        class SeveralNestedGroups {
            @Test
            @DisplayName("Java - Several nested groups")
            fun checkJavaSeveralNestedGroups() {
                setUp()
                assertEquals(
                    testJavaClassList.uFilter(
                        UFilter()
                            .add(
                                andGroup()
                                    .add(FilterItem.Equals("intField", intField))
                                    .add(FilterItem.NotEquals("intField", 123))
                                    .add(
                                        orGroup()
                                            .add(FilterItem.Equals("intField", intField + 1))
                                            .add(FilterItem.Equals("stringField.length", stringField.length))
                                    )
                            )
                    ).toList().size,
                    3
                )
            }
        }
    }
}
