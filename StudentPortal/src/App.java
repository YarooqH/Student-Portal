package StudentPortal.src;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}

// Person class
class Person {
    public final String name;
    public String role;
    public final String CNIC;
    public String email;

    public Person(String name, String role, String CNIC) {
        this.name = name;
        this.role = role;
        this.CNIC = CNIC;
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

    public void addEmail(String email) {
        this.email = email;
    }
}

// Student Class
class Student extends Person {
    /** Student Attributes */
    /** Constant Attributes */
    public final String fatherName;
    public final String seatNumber;
    public final int batch;

    /** Variable Attributes */
    public int totalCourses;
    public int Semester;
    public ArrayList<Course> Courses;
    public ArrayList<Course> CoursesPassed;
    public ArrayList<Fee> fee;
    public Marksheet semesterMarksheet;
    // public Marksheet commulativeMarksheet;

    /** Class Constructor */
    public Student(String name, String fatherName, String seatNumber, int batch, String CNIC) {
        super(name, "Student", CNIC);
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

    public int getBatch() {
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

/** For undergrad Students */
class Undergraduate extends Student {
    public String department;

    public Undergraduate(String name, String fatherName, String seatNumber, int batch, String CNIC, String department) {
        super(name, fatherName, seatNumber, batch, CNIC);
        this.department = department;
        fee = new ArrayList<>();
        semesterMarksheet = new Marksheet();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

/** For post_grad students */
class Postgraduate extends Student {
    public String major;

    public Postgraduate(String name, String fatherName, String seatNumber, int batch, String CNIC, String major) {
        super(name, fatherName, seatNumber, batch, CNIC);
        this.major = major;
        fee = new ArrayList<>();
        semesterMarksheet = new Marksheet();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

// Teacher class
class Teacher extends Person {
    public final String TeacherID;
    private ArrayList<String> qualifications = new ArrayList<>();
    public ArrayList<Student> students;
    public Course course;
    public int numOfClasses;

    public Teacher(String name, String TeacherID, Course course, int numOfClasses, String CNIC) {
        super(name, "Teacher", CNIC);
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

    public void addQualification(String qualification) {
        qualifications.add(qualification);
    }

}

class Lecturer extends Teacher {
    public Lecturer(String name, String TeacherID, Course course, int numOfClasses, String CNIC) {
        super(name, TeacherID, course, numOfClasses, CNIC);
    }
}

class AssistantProfessor extends Teacher {
    public AssistantProfessor(String name, String TeacherID, Course course, int numOfClasses, String CNIC) {
        super(name, TeacherID, course, numOfClasses, CNIC);
    }
}

// Admin class
class Admin extends Person {
    public ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    public ArrayList<Student> students = new ArrayList<Student>();
    public ArrayList<Course> courses = new ArrayList<Course>();

    public Admin(String name, String CNIC) {
        super(name, "Admin", CNIC);
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

class Course {
    public String courseID;
    public String courseName;
    public Teacher courseInstructor;
    public ArrayList<Student> studentList = new ArrayList<>();
    public int theoryMarks;
    public int assignmentMarks;
    public int labMarks;
    public Marksheet marksheet;

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
    private int totalMarks = 100;
    public boolean labPass;
    public boolean theoryPass;
    public char grade;
    public double GPA;

    public Marksheet() {
    }

    public Marksheet(String courseName, String studentName, String studentSeatNum) {
        this.courseName = courseName;
        this.studentName = studentName;
        this.studentSeatNum = studentSeatNum;
    }

    public Marksheet(String courseName, String studentName, String studentSeatNum, boolean labPass, boolean theoryPass,
            char grade, double GPA) {
        this.courseName = courseName;
        this.studentName = studentName;
        this.studentSeatNum = studentSeatNum;
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
}