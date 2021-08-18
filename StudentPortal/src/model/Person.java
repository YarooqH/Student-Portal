package StudentPortal.src.model;

public class Person {
    // Person class
    public final String name;
    public String role;
    public final String CNIC;
    public String email;
    
    public Person(String name, String role, String CNIC, String email) {
        this.name = name;
        this.role = role;
        this.CNIC = CNIC;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void addEmail(String email){
        this.email = email;
    }
}