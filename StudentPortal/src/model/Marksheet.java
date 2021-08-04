package StudentPortal.src.model;

public class Marksheet {
    private String courseName;
    private String studentName;
    private String studentSeatNum;
    private int totalMarks = 100;
    public boolean labPass;
    public boolean theoryPass;
    public char grade;
    public double GPA;
    

    public Marksheet() {}

    public Marksheet(String courseName, String studentName, String studentSeatNum) {
        this.courseName = courseName;
        this.studentName = studentName;
        this.studentSeatNum = studentSeatNum;
    }

    public Marksheet(String courseName, String studentName, String studentSeatNum, boolean labPass, boolean theoryPass, char grade, double GPA) {
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
