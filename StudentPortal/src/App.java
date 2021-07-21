package StudentPortal.src;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}

class Person {
    String name;
    String role;

    public Person(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

class Student {
    /** Student Attributes */
    /** Constant Attributes */
    public final String name;
    public final String fatherName;
    public final String seatNumber;
    public final int batch;
    public final int CNIC;
    /** Variable Attributes */
    public int TotalCourses;
    public int Semester;
    public ArrayList<String> Courses;
    public ArrayList<String> CoursesPassed;



    /** Class Constructors */
    public Student(String name, String fatherName, String seatNumber, int batch, int CNIC) {
        this.seatNumber = seatNumber;
        this.batch = batch;
        this.name = name;
        this.fatherName = fatherName;
        this.CNIC = CNIC;
    }
}
