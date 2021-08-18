package StudentPortal.src.model;

import java.util.ArrayList;

public class Course {
    public String courseID;
    public String courseName;
    public Teacher courseInstructor;
    public Marksheet marksheet;

    public Course() {
    }

    public Course(String courseName, String courseID, Teacher courseInstructor) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.courseInstructor = courseInstructor;
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

    public String getCourseName() {
        return courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public Teacher getCourseInstructor() {
        return courseInstructor;
    }

}
