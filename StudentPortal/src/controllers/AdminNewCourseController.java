package StudentPortal.src.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import StudentPortal.src.model.Admin;

import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class AdminNewCourseController implements Initializable {

    @FXML
    private Label newCourseStatus;

    @FXML
    private TextField newCourseNumber;

    @FXML
    private TextField newCourseName;

    @FXML
    private TextField newTeacherName;

    @FXML
    private TextField newCourseCreditHours;

    @FXML
    private ComboBox<Integer> newCourseSemesterNum;

    Connection connectDB;
    PreparedStatement prepStatDB;

    private void connectorDB(String fileLocation) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connectDB = DriverManager.getConnection(fileLocation);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: " + e);
        }
    }

    private void getData() {
        try {
            connectorDB("jdbc:ucanaccess://StudentPortal/src/database/Courses.accdb");

            String dbName = "Semester4";

            if (newCourseSemesterNum.getValue() == 3) {
                dbName = "Semester3";
            } else if (newCourseSemesterNum.getValue() == 4) {
                dbName = "Semester4";
            }

            String SQL = "INSERT INTO " + dbName
                    + "(Course_Number, Course_Name, Credit_Hours, Teacher_Name) values (?, ?, ?, ?)";

            prepStatDB = connectDB.prepareStatement(SQL);

            prepStatDB.setString(1, newCourseNumber.getText());
            prepStatDB.setString(2, newCourseName.getText());
            prepStatDB.setString(3, newCourseCreditHours.getText());
            prepStatDB.setString(4, newTeacherName.getText());

            if ((newCourseNumber.getText().isEmpty()) || (newCourseName.getText().isEmpty())
                    || (newCourseCreditHours.getText().isEmpty()) || (newTeacherName.getText().isEmpty())) {
                newCourseStatus.setTextFill(Color.RED);
                newCourseStatus.setText("Error! Please Fill All Fields!");
            } else {
                newCourseStatus.setTextFill(Color.GREEN);
                newCourseStatus.setText("Course Added!");

                prepStatDB.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @FXML
    private void addCourse(ActionEvent event) {
        getData();
        AdminController ad = new AdminController();
        ad.loadStage("/StudentPortal/src/fxml/Admin-Courses-Window-New.fxml", event);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        newCourseSemesterNum.getItems().addAll(3, 4);
        // TODO Auto-generated method stub

    }

}
