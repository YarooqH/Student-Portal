package StudentPortal.src.model;

import java.util.ArrayList;

public class Course {
    public String courseID;
    public String courseName;
    public Teacher courseInstructor;
    public ArrayList<Student> studentList = new ArrayList<>();
    public int theoryMarks;
    public int assignmentMarks;
    public int labMarks;
    public Marksheet marksheet;

    public Course() {}
    
    public Course(String courseName, String courseID, Teacher courseInstructor) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.courseInstructor = courseInstructor;
    }
    
    public void addStudent(Student std) {
        studentList.add(std);
    }
    
    public void setAssignmentMarks(int assignmentMarks) {
        this.assignmentMarks = assignmentMarks;
    }
    
    public void setLabMarks(int labMarks) {
        this.labMarks = labMarks;
    }
    
    public void setCourseInstructor(Teacher insName) {
        courseInstructor = insName;
    }

    public void setCourseName(String name) {
        courseName = name;
    }
    
    public void setCourseID(String id) {
        courseID = id;
    }
    
    
    public void setTheoryMarks(int theoryMarks) {
        this.theoryMarks = theoryMarks;
    }
    
    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public String getCourseID() {
        return courseID;
    }

    public Teacher getCourseInstructor() {
        return courseInstructor;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }
    
    public int getLabMarks() {
        return labMarks;
    }

    public int getTheoryMarks() {
        return theoryMarks;
    }

    public int getAssignmentMarks() {
        return assignmentMarks;
    }
}
