package StudentPortal.src.model;

import java.util.ArrayList;

// Teacher class
public class Teacher extends Person {
    String emailAddress, password, role, CNIC, semesters, myCourses;
    ArrayList<Integer> semesterArr = new ArrayList<>();
    ArrayList<String> myCoursesArr = new ArrayList<>();

    public Teacher(String name, String emailAddress, String password, String role, String CNIC, String semesters,
            String myCourses) {
        super(name, "Teacher", CNIC, emailAddress);
        this.password = password;
        this.role = role;

        String[] semesterStringArr = semesters.strip().split(",");

        for (String string : semesterStringArr) {
            semesterArr.add(Integer.parseInt(string));
        }

        String[] myCoursesStringArr = myCourses.strip().split(",");

        for (String string : myCoursesStringArr) {
            myCoursesArr.add(string);
        }

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

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCNIC() {
        return this.CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public ArrayList<Integer> getSemesterArr() {
        return this.semesterArr;
    }

    public void setSemesterArr(ArrayList<Integer> semesterArr) {
        this.semesterArr = semesterArr;
    }

    public ArrayList<String> getMyCoursesArr() {
        return this.myCoursesArr;
    }

    public void setMyCoursesArr(ArrayList<String> myCoursesArr) {
        this.myCoursesArr = myCoursesArr;
    }
}
