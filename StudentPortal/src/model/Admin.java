package StudentPortal.src.model;

import java.util.ArrayList;

public class Admin extends Person {
    public ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    public ArrayList<Student> students = new ArrayList<Student>();
    public ArrayList<Course> courses = new ArrayList<Course>();

    public Admin(String name, String CNIC, String email) {
        super(name, "Admin", CNIC, email);
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }
}