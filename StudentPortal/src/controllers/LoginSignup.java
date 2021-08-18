/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentPortal.src.controllers;

import java.io.IOException;
import java.net.URL;
import java.beans.Statement;
import java.sql.*;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;

// import StudentPortal.src.controllers.StudentController;

/**
 *
 * @author Dell
 */
public class LoginSignup implements Initializable {

    // Main Screen

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXButton signUpBtn;

    // Main Login Screen

    @FXML
    private Button backBtnLogin;

    @FXML
    private JFXButton loginStudentBtn;

    @FXML
    private JFXButton loginTeacherBtn;

    @FXML
    private JFXButton loginAdminBtn;

    // Login Forms

    @FXML
    private Button backBtnLoginForm;

    // Student Login

    @FXML
    private TextField stdLoginEmailAddress;

    @FXML
    private PasswordField stdLoginPassword;

    @FXML
    private Label stdLoginError;

    // Teacher Login

    @FXML
    private TextField teacherLoginEmailAddress;

    @FXML
    private PasswordField teacherLoginPassword;

    @FXML
    private Label teacherLoginError;

    // Admin Login

    @FXML
    private TextField adminLoginEmailAddress;

    @FXML
    private PasswordField adminLoginPassword;

    @FXML
    private Label adminLoginError;

    // Main Signup Screen

    @FXML
    private Button backBtnSignup;

    @FXML
    private JFXButton signupStudentBtn;

    @FXML
    private JFXButton signupTeacherBtn;

    // Student Signup

    @FXML
    private Button backBtnSignupForm;

    @FXML
    private TextField stdFirstName;

    @FXML
    private TextField stdLastName;

    @FXML
    private TextField stdEmailAddress;

    @FXML
    private TextField stdBatch;

    @FXML
    private TextField stdSeatNum;

    @FXML
    private TextField stdCNIC;

    @FXML
    private TextField stdSemester;

    @FXML
    private TextField stdNumOfCourses;

    @FXML
    private PasswordField stdConfirmPassword;

    @FXML
    private PasswordField stdPassword;

    // Teacher Signup

    @FXML
    private TextField teacherFirstName;

    @FXML
    private TextField teacherLastName;

    @FXML
    private TextField teacherEmailAddress;

    @FXML
    private TextField teacherSemester;

    @FXML
    private TextField teacherRole;

    @FXML
    private TextField teacherCNIC;

    @FXML
    private TextField teacherCourses;

    @FXML
    private TextField teacherNumOfClasses;

    @FXML
    private JFXButton createAccount;

    @FXML
    private PasswordField teacherPassword;

    @FXML
    private PasswordField teacherConfirmPassword;

    // Signup Success

    @FXML
    private Button backBtnSignupSuccess;

    @FXML
    private void handleButtonClicks(ActionEvent event) {
        if (event.getSource() == loginBtn) {
            loadStage("/StudentPortal/src/fxml/Login.fxml", event);
        } else if (event.getSource() == signUpBtn) {
            loadStage("/StudentPortal/src/fxml/Signup.fxml", event);
        } else if (event.getSource() == loginStudentBtn) {
            loadStage("/StudentPortal/src/fxml/LoginForm-Student.fxml", event);
        } else if (event.getSource() == loginTeacherBtn) {
            loadStage("/StudentPortal/src/fxml/LoginForm-Teacher.fxml", event);
        } else if (event.getSource() == loginAdminBtn) {
            loadStage("/StudentPortal/src/fxml/LoginForm-Admin.fxml", event);
        } else if (event.getSource() == backBtnLogin) {
            loadStage("/StudentPortal/src/fxml/Login-Signup.fxml", event);
        } else if (event.getSource() == backBtnLoginForm) {
            loadStage("/StudentPortal/src/fxml/Login.fxml", event);
        } else if (event.getSource() == backBtnSignup) {
            loadStage("/StudentPortal/src/fxml/Login-Signup.fxml", event);
        } else if (event.getSource() == signupStudentBtn) {
            loadStage("/StudentPortal/src/fxml/Student-Signup.fxml", event);
        } else if (event.getSource() == signupTeacherBtn) {
            loadStage("/StudentPortal/src/fxml/Teacher-Signup.fxml", event);
        } else if (event.getSource() == backBtnSignupForm) {
            loadStage("/StudentPortal/src/fxml/Signup.fxml", event);
        } else if (event.getSource() == backBtnSignupSuccess) {
            loadStage("/StudentPortal/src/fxml/Login-Signup.fxml", event);
        }
    }

    private void loadStage(String fxml, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Scene scene = new Scene(root);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Object queryId;
    String[] userData;

    Connection connectDB;
    PreparedStatement prepStatDB;
    ResultSet resultSet = null;

    private void conDB() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/MainDB.accdb");
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @FXML
    private void logInStd(ActionEvent e) {
        String email = stdLoginEmailAddress.getText();
        String password = stdLoginPassword.getText();
        if (email.isEmpty() || password.isEmpty()) {
            stdLoginError.setText("Error! Email or Password do not match with our records \n Please Try Again!");
        } else {
            conDB();
            // query
            String sql = "SELECT * FROM Students Where Email_Address = ? and Password = ?";
            try {
                prepStatDB = connectDB.prepareStatement(sql);
                prepStatDB.setString(1, email);
                prepStatDB.setString(2, password);
                resultSet = prepStatDB.executeQuery();
                if (!resultSet.next()) {
                    stdLoginError
                            .setText("Error! Email or Password do not match with our records \n Please Try Again!");
                } else {
                    StudentController.getStudentEmail(email);
                    loadStage("/StudentPortal/src/fxml/Student-Dashboard.fxml", e);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void logInTeacher(ActionEvent e) {
        String email = teacherLoginEmailAddress.getText();
        String password = teacherLoginPassword.getText();
        if (email.isEmpty() || password.isEmpty()) {
            teacherLoginError.setText("Error! Email or Password do not match with our records \n Please Try Again!");
        } else {
            conDB();
            // query
            String sql = "SELECT * FROM Teachers Where Email_Address = ? and Password = ?";
            try {
                prepStatDB = connectDB.prepareStatement(sql);
                prepStatDB.setString(1, email);
                prepStatDB.setString(2, password);
                resultSet = prepStatDB.executeQuery();
                if (!resultSet.next()) {
                    teacherLoginError
                            .setText("Error! Email or Password do not match with our records \n Please Try Again!");
                } else {
                    TeacherController.getTeacherEmail(email);
                    loadStage("/StudentPortal/src/fxml/Teacher-Dashboard.fxml", e);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public void adminLogin(ActionEvent e) {
        if (adminLoginEmailAddress.getText().equals("admin") && adminLoginPassword.getText().equals("1234")) {
            loadStage("/StudentPortal/src/fxml/Admin-Dashboard.fxml", e);
        } else {
            adminLoginError.setText("Error! Email or Password do not match with our records \n Please Try Again!");
        }
    }

    public void submitStudent(ActionEvent event) throws Exception {

        // Database Connectivity

        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/Students.accdb");

        System.out.println("Connected");

        // Inserting Data into the Database

        String insert = "insert into Student_Approvals(First_Name, Last_Name, Email_Address, Batch, Seat_Number, CNIC, Number_of_Courses, Password, Semester) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        prepStatDB = connectDB.prepareStatement(insert);

        prepStatDB.setString(1, stdFirstName.getText());
        prepStatDB.setString(2, stdLastName.getText());
        prepStatDB.setString(3, stdEmailAddress.getText());
        prepStatDB.setInt(4, Integer.parseInt(stdBatch.getText()));
        prepStatDB.setString(5, stdSeatNum.getText());
        prepStatDB.setString(6, stdCNIC.getText());
        prepStatDB.setInt(7, Integer.parseInt(stdNumOfCourses.getText()));
        prepStatDB.setString(8, stdPassword.getText());
        prepStatDB.setInt(9, Integer.parseInt(stdSemester.getText()));

        prepStatDB.executeUpdate();

        System.out.println("Database Updated");

        // Screen Change

        loadStage("/StudentPortal/src/fxml/Signup-Success.fxml", event);
    }

    public void submitTeacher(ActionEvent event) throws Exception {
        // Database Connectivity

        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/Teachers.accdb");

        System.out.println("Connected");

        // Inserting Data into the Database

        String insert = "insert into Teacher_Approvals(First_Name, Last_Name, Email_Address, Password, Semesters, Post, CNIC, Courses, Number_Of_Classes) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        prepStatDB = connectDB.prepareStatement(insert);

        prepStatDB.setString(1, teacherFirstName.getText());
        prepStatDB.setString(2, teacherLastName.getText());
        prepStatDB.setString(3, teacherEmailAddress.getText());
        prepStatDB.setString(4, teacherPassword.getText());
        prepStatDB.setString(5, teacherSemester.getText());
        prepStatDB.setString(6, teacherRole.getText());
        prepStatDB.setString(7, teacherCNIC.getText());
        prepStatDB.setString(8, teacherCourses.getText());
        prepStatDB.setInt(9, Integer.parseInt(teacherNumOfClasses.getText()));

        prepStatDB.executeUpdate();

        System.out.println("Database Updated");

        // Screen Change

        loadStage("/StudentPortal/src/fxml/Signup-Success.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
