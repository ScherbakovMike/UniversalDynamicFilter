package ru.mikescherbakov.filters.universal;

public class TestJavaClass {
    public final String ABC = "ABC";

    private Integer intField = 123;
    public String stringField = "Vasya";

    public TestJavaClass(Integer intField, String stringField) {
        this.intField = intField;
        this.stringField = stringField;
    }

    public void publicMethod() {
        System.out.println("public method print");
    }

    private void privateMethod() {
        System.out.println("private method print");
    }
}
