package StudentPortal.src.model;

import java.util.ArrayList;

public class Student extends Person {
    /** Student Attributes */
    /** Constant Attributes */
    public final String seatNumber;
    public String batch;
    public String emailAddress;
    public String password;
    public String CNIC;

    /** Variable Attributes */
    public int totalCourses;
    public int Semester;
    // public Marksheet semesterMarksheet;
    // public Marksheet commulativeMarksheet;

    /** Class Constructor */
    public Student(String name, String emailAddress, String seatNumber, String CNIC, String password, String batch,
            int Semester) {
        super(name, "Student", CNIC, emailAddress);
        this.seatNumber = seatNumber;
        this.CNIC = CNIC;
        this.password = password;
        this.batch = batch;
        this.Semester = Semester;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public String getBatch() {
        return this.batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTotalCourses() {
        return this.totalCourses;
    }

    public void setTotalCourses(int totalCourses) {
        this.totalCourses = totalCourses;
    }

    public int getSemester() {
        return this.Semester;
    }

    public void setSemester(int Semester) {
        this.Semester = Semester;
    }

}
