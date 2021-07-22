package StudentPortal.src;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}

// Person class
class Person {
    public String name;
    public String role;

    public Person(){
    }

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

// Student Class
class Student extends Person{
    /** Student Attributes */
    /** Constant Attributes */
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
    public Student(String name,String fatherName, String seatNumber, int batch, int CNIC) {
        super(name,"Student");
        this.seatNumber = seatNumber;
        this.batch = batch;
        this.fatherName = fatherName;
        this.CNIC = CNIC;
    }
}
// Teacher class
class Teacher extends Person {
    public final int TeacherID;
    public String courseName;
    public int numOFclasses;

    public Teacher(String name, int TeacherID, String courseName, int numOFclasses){
        super(name, "Teacher");
        this.TeacherID = TeacherID;
        this.courseName = courseName;
        this.numOFclasses = numOFclasses;
    }

    public String getTeacherId() {
        return TeacherID;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getnumOFclasses() {
        return numOFclasses;
    }

}
// Admin class
class Admin extends Person{
    public ArrayList<Teacher> teachers = new ArrayList<>();
    public ArrayList<Student> students = new ArrayList<>();
    public ArrayList<String> courses = new ArrayList<>();

    public Admin(String name) {
        super(name, "Admin");
    }

    public  ArrayList<> getTeachers(){
        return teachers;
    }

    public  ArrayList<> getStudents(){
        return students;
    }

    public  ArrayList<> getCourses(){
        return courses;
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }

    public void removeTeacher(int TeacherID){
        for (Teacher x:teachers){
            if (x.TeacherID == TeacherID){
                teachers.remove(x);
            }
        }
    }

    public void removeStudent(int StudentID){
        for (Student x:teachers){
            if (x.seatNumber == StudentID){
                students.remove(x);
            }
        }
    }
}


class Course {
    public String courseID;
    public String courseName;
    public Teacher courseInstructor;
    public ArrayList<Student> studentList =  new ArrayList<>();

    public Course(){}

    public Course(String courseName,String courseID, Teacher courseInstructor){
        this.courseName = courseName;
        this.courseID = courseID;
        this.courseInstructor = courseInstructor;
    }

    public void addStudent(Student std){
        studentList.add(std);
    }

    public void setCourseName(String name){
        courseName = name;
    }

    public void setCourseID(String id){
        courseID = id;
    }

    public void setCourseInstructor(String insName){
        courseInstructor = insName;
    }
}


class Fee {
    private double tuitionFee;
    private double examinationFee;
    private double totalFee;
    private boolean feePaid;
    private double feeDue;

    public Fee(){}
    public Fee(double tuitionFee, double examinationFee){
        this.tuitionFee = tuitionFee;
        this.examinationFee = examinationFee;
        totalFee = tuitionFee + examinationFee;
    }

    public boolean getFeeClear(){
        return feePaid;
    }
    
    public double getFeeDue(){
        return feeDue;
    }

    public double getTuitionFee(){
        return tuitionFee;
    }

    public double getExaminationFee(){
        return examinationFee;
    }

    public double getTotalFee(){
        return totalFee;
    }

    public void setTuitionFee(double fee){
        tuitionFee = fee;
    }

    public void setExaminationFee(double fee){
        examinationFee = fee;
    }

    public void setFeePaid(boolean x){
        feePaid = x;
    }
}

class Marksheet {
    private String courseName;
    private String studentName;
    private int studentSeatNum;
    private int totalMarks;
    private int examMarks;
    private int assignmentMarks;
    private int labMarks;
    private boolean labPass;
    private boolean theoryPass;
    private char grade;
    private double GPA;


    public Marksheet(){}
    public Marksheet(String courseName, String studentName, String studentSeatNum){
        this.courseName = courseName;
        this.studentName = studentName;
        this.studentSeatNum = studentSeatNum;
    }

    public String getCourseName(){
        return courseName;
    }

    public String getStudentName(){
        return studentName;
    }

    public int getStudentSeatNum(){
        return studentSeatNum;
    }

    public int getExamMarks(){
        return examMarks;
    }

    public int getLabMarks(){
        return labMarks;
    }

    public int getAssignmentMarks(){
        return assignmentMarks;
    }

    public void setTuitionFee(double fee){
        tuitionFee = fee;
    }
}