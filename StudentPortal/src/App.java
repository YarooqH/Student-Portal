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

    public Person() {
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
class Student extends Person {
    /** Student Attributes */
    /** Constant Attributes */

    public final String fatherName;
    public final String seatNumber;
    public final int batch;
    public final int CNIC;
    /** Variable Attributes */

    public final int TotalCourses;
    public int Semester;
    public ArrayList<String> Courses;
    public ArrayList<String> CoursesPassed;

    /** Class Constructors */

    public Student(String name, String fatherName, String seatNumber, int TotalCourses, int batch, int CNIC) {
        super(name, "Student");
        this.seatNumber = seatNumber;
        this.batch = batch;
        this.fatherName = fatherName;
        this.CNIC = CNIC;
        this.TotalCourses = TotalCourses;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public int getBatch() {
        return this.batch;
    }

    public int getCNIC() {
        return this.CNIC;
    }

    public int getTotalCourses() {
        return this.TotalCourses;
    }

    public int getSemester() {
        return this.Semester;
    }

    public void setSemester(int Semester) {
        this.Semester = Semester;
    }

    public ArrayList<String> getCourses() {
        return this.Courses;
    }

    public void setCourses(ArrayList<String> Courses) {
        this.Courses = Courses;
    }

    public ArrayList<String> getCoursesPassed() {
        return this.CoursesPassed;
    }

    public void setCoursesPassed(ArrayList<String> CoursesPassed) {
        this.CoursesPassed = CoursesPassed;
    }

}

// Teacher class
class Teacher extends Person {
    public final String TeacherID;
    private ArrayList<String> qualifications;
    public String courseName;
    public int numOfClasses;

    public Teacher(String name, String TeacherID, String courseName, int numOfClasses,
            ArrayList<String> qualifications) {
        super(name, "Teacher");
        this.qualifications = qualifications;
        this.TeacherID = TeacherID;
        this.courseName = courseName;
        this.numOfClasses = numOfClasses;
    }

    public String getTeacherId() {
        return TeacherID;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getnumOfClasses() {
        return numOfClasses;
    }

}

// Admin class
class Admin extends Person {
    public ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    public ArrayList<Student> students = new ArrayList<Student>();
    public ArrayList<Course> courses = new ArrayList<Course>();

    public Admin(String name) {
        super(name, "Admin");
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

    public void removeTeacher(String TeacherID) {
        for (Teacher teacher : teachers) {
            if (teacher.TeacherID == TeacherID) {
                teachers.remove(teacher);
            }
        }
    }

    public void removeStudent(String StudentID) {
        for (Student student : students) {
            if (student.seatNumber == StudentID) {
                students.remove(student);
            }
        }
    }
}

class Undergraduate extends Student {

    public Undergraduate(String name, String fatherName, String seatNumber, int TotalCourses, int batch, int CNIC) {
        super(name, fatherName, seatNumber, TotalCourses, batch, CNIC);
    }

}

class Postgraduate extends Student {
    public String major;

    public Postgraduate(String name, String fatherName, String seatNumber, String major, int TotalCourses, int batch,
            int CNIC) {
        super(name, fatherName, seatNumber, TotalCourses, batch, CNIC);
        this.major = major;
    }

}

class Lecturer extends Teacher {

    public Lecturer(String name, String TeacherID, String courseName, int numOfClasses,
            ArrayList<String> qualifications) {
        super(name, TeacherID, courseName, numOfClasses, qualifications);
        // TODO Auto-generated constructor stub
    }

}

class AssistantLecturer extends Teacher {

    public AssistantLecturer(String name, String TeacherID, String courseName, int numOfClasses,
            ArrayList<String> qualifications) {
        super(name, TeacherID, courseName, numOfClasses, qualifications);
        // TODO Auto-generated constructor stub
    }

}

class Course {
    public String courseID;
    public String courseName;
    public Teacher courseInstructor;
    public ArrayList<Student> studentList = new ArrayList<>();

    public Course() {
    }

    public Course(String courseName, String courseID, Teacher courseInstructor) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.courseInstructor = courseInstructor;
    }

    public void addStudent(Student std) {
        studentList.add(std);
    }

    public void setCourseName(String name) {
        courseName = name;
    }

    public void setCourseID(String id) {
        courseID = id;
    }

    public void setCourseInstructor(Teacher insName) {
        courseInstructor = insName;
    }
}

class Fee {
    private double tuitionFee;
    private double examinationFee;
    private double totalFee;
    private boolean feePaid;
    private double feeDue;

    public Fee() {
    }

    public Fee(double tuitionFee, double examinationFee) {
        this.tuitionFee = tuitionFee;
        this.examinationFee = examinationFee;
        totalFee = tuitionFee + examinationFee;
    }

    public boolean getFeeClear() {
        return feePaid;
    }

    public double getFeeDue() {
        return feeDue;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public double getExaminationFee() {
        return examinationFee;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTuitionFee(double fee) {
        tuitionFee = fee;
    }

    public void setExaminationFee(double fee) {
        examinationFee = fee;
    }

    public void setFeePaid(boolean x) {
        feePaid = x;
    }
}

class Marksheet {
    private String courseName;
    private String studentName;
    private String studentSeatNum;
    private int totalMarks;
    private int examMarks;
    private int assignmentMarks;
    private int labMarks;
    private boolean labPass;
    private boolean theoryPass;
    private char grade;
    private double GPA;
    double tuitionFee;

    public Marksheet() {
    }

    public Marksheet(String courseName, String studentName, String studentSeatNum) {
        this.courseName = courseName;
        this.studentName = studentName;
        this.studentSeatNum = studentSeatNum;
    }

    public Marksheet(String courseName, String studentName, String studentSeatNum, int totalMarks, int examMarks,
            int assignmentMarks, int labMarks, boolean labPass, boolean theoryPass, char grade, double GPA) {
        this.courseName = courseName;
        this.studentName = studentName;
        this.studentSeatNum = studentSeatNum;
        this.totalMarks = totalMarks;
        this.examMarks = examMarks;
        this.assignmentMarks = assignmentMarks;
        this.labMarks = labMarks;
        this.labPass = labPass;
        this.theoryPass = theoryPass;
        this.grade = grade;
        this.GPA = GPA;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSeatNum() {
        return this.studentSeatNum;
    }

    public void setStudentSeatNum(String studentSeatNum) {
        this.studentSeatNum = studentSeatNum;
    }

    public int getTotalMarks() {
        return this.totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public int getExamMarks() {
        return this.examMarks;
    }

    public void setExamMarks(int examMarks) {
        this.examMarks = examMarks;
    }

    public int getAssignmentMarks() {
        return this.assignmentMarks;
    }

    public void setAssignmentMarks(int assignmentMarks) {
        this.assignmentMarks = assignmentMarks;
    }

    public int getLabMarks() {
        return this.labMarks;
    }

    public void setLabMarks(int labMarks) {
        this.labMarks = labMarks;
    }

    public boolean isLabPass() {
        return this.labPass;
    }

    public boolean getLabPass() {
        return this.labPass;
    }

    public void setLabPass(boolean labPass) {
        this.labPass = labPass;
    }

    public boolean isTheoryPass() {
        return this.theoryPass;
    }

    public boolean getTheoryPass() {
        return this.theoryPass;
    }

    public void setTheoryPass(boolean theoryPass) {
        this.theoryPass = theoryPass;
    }

    public char getGrade() {
        return this.grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public double getGPA() {
        return this.GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public double getTuitionFee() {
        return this.tuitionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

}
