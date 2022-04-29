package ru.mikescherbakov.filters.universal

import TestJavaClass
import TestKotlinClass
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UFilterTest {
    companion object {
        lateinit var testJavaClass: TestJavaClass
        lateinit var testKotlinClass: TestKotlinClass
        lateinit var testJavaClassList: List<TestJavaClass>
        lateinit var testKotlinClassList: List<TestKotlinClass>

        val intField: Int = 123456
        val stringField = "Sample text"
        val booleanField = true

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
    }

    @DisplayName("Java lists test")
    class TestJavaList {
        @Test
        @DisplayName("One and group - Int field - Equals")
        fun checkJavaListAndGroupIntFieldEquals() {
            setUp()
            assertEquals(
                testJavaClassList.uFilter(
                    UFilter.andGroup()
                        .add(FilterItem.Equals("intField", 123456))
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
                    UFilter.andGroup()
                        .add(FilterItem.Greater("intField", 123450))
                ).toList().size,
                3
            )
        }
    }


}