/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentPortal.src.controllers;

import java.io.IOException;
import java.net.URL;
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
    private TextField teacherID;

    @FXML
    private TextField teacherRole;

    @FXML
    private TextField teacherCNIC;

    @FXML
    private TextField teacherCOE;

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
        } else if (event.getSource() == createAccount) {
            loadStage("/StudentPortal/src/fxml/Signup-Success.fxml", event);
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

    public void stdLogin(ActionEvent e) {
        if (stdLoginEmailAddress.getText().equals("owais@uok.com") && stdLoginPassword.getText().equals("1234")) {
            loadStage("/StudentPortal/src/fxml/Student-Dashboard.fxml", e);
        } else {
            stdLoginError.setText("Error! Email or Password do not match with our records \n Please Try Again!");
        }
    }

    public void teacherLogin(ActionEvent e) {
        if (teacherLoginEmailAddress.getText().equals("owais@uok.com")
                && teacherLoginPassword.getText().equals("1234")) {
            loadStage("/StudentPortal/src/fxml/Teacher-Dashboard.fxml", e);
        } else {
            teacherLoginError.setText("Error! Email or Password do not match with our records \n Please Try Again!");
        }
    }

    public void adminLogin(ActionEvent e) {
        if (adminLoginEmailAddress.getText().equals("admin") && adminLoginPassword.getText().equals("1234")) {
            loadStage("/StudentPortal/src/fxml/Teacher-Dashboard.fxml", e);
        } else {
            adminLoginError.setText("Error! Email or Password do not match with our records \n Please Try Again!");
        }
    }

    @FXML
    void btnHoverEffectEnd(MouseEvent event) {

    }

    @FXML
    void btnHoverEffectStart(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
