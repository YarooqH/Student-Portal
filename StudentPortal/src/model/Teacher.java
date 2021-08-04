package StudentPortal.src.model;

import java.util.ArrayList;

// Teacher class
public class Teacher extends Person {
    public final String TeacherID;
    private ArrayList<String> qualifications = new ArrayList<>();
    public  ArrayList<Student> students;
    public Course course;
    public int numOfClasses;

    public Teacher(String name, String TeacherID, Course course, int numOfClasses, String CNIC, String email) {
        super(name,"Teacher",CNIC,email);
        this.TeacherID = TeacherID;
        this.course = course;
        this.numOfClasses = numOfClasses;
    }

    public String getTeacherID() {
        return this.TeacherID;
    }

    public ArrayList<String> getQualifications() {
        return this.qualifications;
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getNumOfClasses() {
        return this.numOfClasses;
    }

    public void setNumOfClasses(int numOfClasses) {
        this.numOfClasses = numOfClasses;
    }


    public void addQualification(String qualification){
        qualifications.add(qualification);
    }
    
}
