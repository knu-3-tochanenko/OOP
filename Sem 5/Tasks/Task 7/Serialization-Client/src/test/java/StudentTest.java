import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    private String name = "Taya";
    private int age = 19;
    private String faculty = "cybernetics";

    private Student emptyStudent;
    private Student student;

    @Before
    public void setUp() {
        emptyStudent = new Student();
        student = new Student(name, age, faculty);
    }

    @Test
    public void testToString() {
        assertEquals(emptyStudent.toString(), getString(null, 0, null));
        assertEquals(student.toString(), getString(name, age, faculty));
    }

    @Test
    public void setName() {
        String newName = "Name";
        emptyStudent.setName(newName);
        student.setName(newName);

        assertEquals(emptyStudent.toString(), getString(newName, 0, null));
        assertEquals(student.toString(), getString(newName, age, faculty));
    }

    @Test
    public void setAge() {
        int newAge = 20;
        emptyStudent.setAge(newAge);
        student.setAge(newAge);

        assertEquals(emptyStudent.toString(), getString(name, newAge, faculty));
        assertEquals(student.toString(), getString(null, newAge, null));
    }

    @Test
    public void setFaculty() {
        String newFaculty = "Faculty";
        emptyStudent.setFaculty(newFaculty);
        student.setFaculty(newFaculty);

        assertEquals(emptyStudent.toString(), getString(name, 0, newFaculty));
        assertEquals(student.toString(), getString(null, age, newFaculty));
    }

    private String getString(String newName, int newAge, String newFaculty) {
        return "Student {\n" +
                "Name: " + newName + ";\n" +
                "Age: " + newAge + ";\n" +
                "Faculty: " + newFaculty + ";\n" +
                "}\n";
    }

}