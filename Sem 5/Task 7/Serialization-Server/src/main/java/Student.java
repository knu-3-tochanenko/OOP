import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int age;
    private String faculty;

    public Student() {
    }

    public Student(String name, int age, String faculty) {
        this.name = name;
        this.age = age;
        this.faculty = faculty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Student {\n" +
                "\tName: " + name + ";\n" +
                "\tAge: " + age + ";\n" +
                "\tFaculty: " + faculty + ";\n" +
                "}\n";
    }
}
