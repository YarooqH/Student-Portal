package StudentPortal.src.model;

import java.util.ArrayList;

public class Student extends Person {
    /** Student Attributes */
    /** Constant Attributes */
    public final String fatherName;
    public final String seatNumber;
    public final String batch;

    /** Variable Attributes */
    public int totalCourses;
    public int Semester;
    public ArrayList<Course> Courses;
    public ArrayList<Course> CoursesPassed;
    public ArrayList<Fee> fee;
    public Marksheet semesterMarksheet;
    // public Marksheet commulativeMarksheet;

    /** Class Constructor */
    public Student(String name, String fatherName, String seatNumber, String batch, String CNIC, String email) {
        super(name,"Student",CNIC, email);
        this.seatNumber = seatNumber;
        this.batch = batch;
        this.fatherName = fatherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getBatch() {
        return batch;
    }

    public String getCNIC() {
        return CNIC;
    }

    public int getTotalCourses() {
        return totalCourses;
    }

    public int getSemester() {
        return Semester;
    }

    public ArrayList<Course> getCoursesPassed() {
        return CoursesPassed;
    }

    public ArrayList<Course> getCourses() {
        return this.Courses;
    }

    public void setSemester(int Semester) {
        this.Semester = Semester;
    }

    public void addCourse(Course course) {
        Courses.add(course);
        totalCourses++;
    }

    public void setCoursePassed(Course course) {
        CoursesPassed.add(course);
    }
}
