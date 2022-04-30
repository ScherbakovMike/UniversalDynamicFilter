import kotlin.properties.Delegates

class TestKotlinClass {
    var intField by Delegates.notNull<Int>()
    lateinit var stringField: String
    var booleanField by Delegates.notNull<Boolean>()
    var listField: List<TestJavaClass?>? = null

    private fun privateMethod(): String {
        return "private method print"
    }

    fun nonPrivateMethod(): String {
        return "non private method print"
    }

    fun nonPrivateMethodWithList(): List<TestJavaClass?>? = listField
}
