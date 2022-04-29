import java.util.List;

public class TestJavaClass {
    public final String stringConstant = "String constant";

    private Integer intField;
    public String stringField;
    public Boolean booleanField;
    public List<TestKotlinClass> listField;

    public TestJavaClass(Integer intField, String stringField, Boolean booleanField) {
        this.intField = intField;
        this.stringField = stringField;
        this.booleanField = booleanField;
        this.listField = null;
    }
    public void setListField(List<TestKotlinClass> listField) {
        this.listField = listField;
    }

    public List<TestKotlinClass> getListField() {
        return listField;
    }

    public void publicMethod() {
        System.out.println("public method print");
    }

    private void privateMethod() {
        System.out.println("private method print");
    }
}
