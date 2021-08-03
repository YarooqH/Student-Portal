package StudentPortal.src.model;

public class StudentModel {
    String firstName, lastName, emailAddress, CNIC, seatNumber, password;
    int semester, batch, numOfCourses;

    public StudentModel(String firstName, String lastName, String emailAddress, String CNIC, int semester, int batch,
            String seatNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.CNIC = CNIC;
        this.seatNumber = seatNumber;
        this.semester = semester;
        this.batch = batch;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCNIC() {
        return this.CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSemester() {
        return this.semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getBatch() {
        return this.batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getNumOfCourses() {
        return this.numOfCourses;
    }

    public void setNumOfCourses(int numOfCourses) {
        this.numOfCourses = numOfCourses;
    }

}
