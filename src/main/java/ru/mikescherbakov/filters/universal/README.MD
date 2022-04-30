**_Universal filter for collections_**
----------------------

Current version: 1.0

@Copiright Mike Scherbakov, scherbakov.mike@gmail.com


**Description:**

This is the universal library for filtering the iterable collection.

Supports the custom specification of filtering fields.

**Examples of using:**

1. List of strings. The task is filtering of elements with length, less or equal 10:

val sourceList: List<String> = listOf("First", "Second", "Super long string")  
val filteredList = sourceList.uFilter(  
UFilter()
.add(
andGroup()  
.add(FilterItem.LessOrEqual("it.length", 10))  
).toList()  
)

2. List of complex objects. The task is filtering of elements with custom condition:

val sourceList: List<OpenRequest> = listOf(  
Request("name1", 123),  
Request("name2", 345),  
Request("name3", 5678),  
Request("name3", 7890),  
)  
val filteredList = testJavaClassList.uFilter(  
UFilter()  
.add(  
andGroup()  
.add(FilterItem.Greater("intField", 123))  
.add(FilterItem.NotEquals("intField", 345))  
.add(  
orGroup()  
.add(FilterItem.Equals("name", intField + 1))  
.add(FilterItem.Equals("stringField.length", stringField.length))  
)

)  
).toList()

3. Condition on entry field value to the list:

val sourceList: List<OpenRequest> = listOf(  
Request("name1", 123),  
Request("name2", 345),  
Request("name3", 5678),  
Request("name3", 7890),  
)  
val filteredList = testJavaClassList.uFilter(  
UFilter()  
.add(  
andGroup()  
.add(FilterItem.InTheList("intField", listOf(345, 5678))      )  
).toList()

**Supported comparison conditions:**

FilterItem.Equals("intField", 123)

FilterItem.NotEquals("intField", 123)

FilterItem.Greater("intField", 123450)

FilterItem.GreaterOrEqual("intField", 123456)

FilterItem.Less("intField", 1234567)

FilterItem.LessOrEqual("intField", 1234567)

FilterItem.InTheList("intField", listOf(123456, 7890))

FilterItem.NotInTheList("intField", listOf(112, 7890))

FilterItem.Filled("listField")

FilterItem.NotFilled("listField")


