package StudentPortal.src.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.awt.Desktop;
import javafx.css.Size;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.jfoenix.controls.JFXButton;

import StudentPortal.src.model.Student;

import java.sql.*;
import java.util.ResourceBundle;

public class StudentGradesController implements Initializable {

    @FXML
    private JFXButton stdDashboard;

    @FXML
    private JFXButton stdNotifications;

    @FXML
    private JFXButton stdCourses;

    @FXML
    private JFXButton stdGrades;

    @FXML
    private JFXButton stdLogout;

    @FXML
    private VBox gradesContainer;

    @FXML
    private JFXButton semesterSchedule;

    @FXML
    private Label stdUserNameLbl;

    AdminController ac = new AdminController();

    @FXML
    private void changeScene(ActionEvent event) {
        if (event.getSource() == stdDashboard) {
            loadStage("/StudentPortal/src/fxml/Student-Dashboard.fxml", event);
        } else if (event.getSource() == stdNotifications) {
            loadStage("/StudentPortal/src/fxml/Student-Notifications-New.fxml", event);
        } else if (event.getSource() == stdCourses) {
            loadStage("/StudentPortal/src/fxml/Student-Courses.fxml", event);
        } else if (event.getSource() == stdGrades) {
            loadStage("/StudentPortal/src/fxml/Student-Grades.fxml", event);
        } else if (event.getSource() == stdLogout) {
            loadStage("/StudentPortal/src/fxml/Login-Signup.fxml", event);
        } else if (event.getSource() == semesterSchedule) {
            ac.loadWindow("/StudentPortal/src/fxml/Timetable.fxml");
        }
    }

    ArrayList<String> courseNameArr = new ArrayList<>();
    ArrayList<String> courseNumArr = new ArrayList<>();
    ArrayList<String> teacherNameArr = new ArrayList<>();
    ArrayList<String> fileLocationArr = new ArrayList<>();

    Connection connectDB = null;
    PreparedStatement prepStatDB;

    private void getGrades() {
        try {
            String sql = "jdbc:ucanaccess://StudentPortal/src/database/Semester4/Results.accdb";

            if (StudentController.studentObj.getSemester() == 3) {
                sql = "jdbc:ucanaccess://StudentPortal/src/database/Semester3/Results.accdb";
            } else if (StudentController.studentObj.getSemester() == 4) {
                sql = "jdbc:ucanaccess://StudentPortal/src/database/Semester4/Results.accdb";
            }

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connectDB = DriverManager.getConnection(sql);

            Statement statement = connectDB.createStatement();
            ResultSet resultSetDB = statement
                    .executeQuery("Select Course_Name, Course_Number, Teacher_Name, File_Location from Results");

            while (resultSetDB.next()) {
                courseNameArr.add(resultSetDB.getString("Course_Name"));
                courseNumArr.add(resultSetDB.getString("Course_Number"));
                teacherNameArr.add(resultSetDB.getString("Teacher_Name"));
                fileLocationArr.add(resultSetDB.getString("File_Location"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void buttonsGen() {
        for (int i = 0; i < rowCount; i++) {
            try {
                HBox hb = new HBox(100);

                hb.setStyle("-fx-padding: 5 0 5 40;" + "-fx-background-color: white;" + "-fx-allignment: bottom-left;");

                Label courseNameLabel = new Label(courseNameArr.get(i));
                Label courseNumLabel = new Label(courseNumArr.get(i));
                Label teacherNameLabel = new Label(teacherNameArr.get(i));
                // Label fileLocationLabel = new Label(fileLocationArr.get(i));
                courseNameLabel.setStyle("-fx-font-size: 15;" + "-fx-font-weight: bold;");
                courseNumLabel.setStyle("-fx-font-size: 15;");
                teacherNameLabel.setStyle("-fx-font-size: 15;");

                JFXButton openFileBtn = new JFXButton("Open File");

                openFileBtn.setStyle("-fx-background-color: #8DC248;" + "-fx-text-fill: white;" + "-fx-cursor: hand;"
                        + "-fx-font-weight: bold;" + "-fx-font-Size: 12;");

                final int j = i;

                openFileBtn.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Desktop desktop = Desktop.getDesktop();
                            File f = new File(fileLocationArr.get(j));

                            desktop.open(f);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                hb.getChildren().add(courseNameLabel);
                hb.getChildren().add(courseNumLabel);
                hb.getChildren().add(teacherNameLabel);
                hb.getChildren().add(openFileBtn);

                gradesContainer.getChildren().add(hb);

                // Make getResultsFromDB and Get Details from there
            } catch (Exception e) {
                System.out.println("error: " + e);
            }
        }
    }

    int rowCount;

    private void getRowCount() {
        String sql = "jdbc:ucanaccess://StudentPortal/src/database/Semester4/Results.accdb";

        if (StudentController.studentObj.getSemester() == 3) {
            sql = "jdbc:ucanaccess://StudentPortal/src/database/Semester3/Results.accdb";
        } else if (StudentController.studentObj.getSemester() == 4) {
            sql = "jdbc:ucanaccess://StudentPortal/src/database/Semester4/Results.accdb";
        }

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connectDB = DriverManager.getConnection(sql);

            prepStatDB = connectDB.prepareStatement("SELECT COUNT(*) AS count FROM Results");
            ResultSet resultSetDB = prepStatDB.executeQuery();

            while (resultSetDB.next()) {
                rowCount = resultSetDB.getInt("count");
            }

        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void loadStage(String fxml, ActionEvent event) {
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        getRowCount();
        getGrades();
        buttonsGen();
        stdUserNameLbl.setText(StudentController.studentName);
    }

}
