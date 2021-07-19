package StudentPortal.src;

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
    static final String seatNumber;
    static final int batch;

    public Student(String seatNumber) {
        this.seatNumber = seatNumber;
    }

}
