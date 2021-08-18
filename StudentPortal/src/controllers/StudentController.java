package StudentPortal.src.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import java.awt.Desktop;
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
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.jfoenix.controls.JFXButton;

import StudentPortal.src.model.Student;

import java.sql.*;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
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
    private JFXButton semesterSchedule;

    // Events

    @FXML
    public VBox stdDashboardEventsContainer;

    // Courses

    @FXML
    private VBox stdDashboardCoursesContainer;

    @FXML
    private TableView stdCoursesTable;

    // Lectures

    @FXML
    private JFXButton lectureBtn;

    AdminController ac = new AdminController();

    @FXML
    private void changeScene(ActionEvent event) throws IOException {
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
            openSchedule();
        } else if (event.getSource() == lectureBtn) {
            ac.loadWindow("/StudentPortal/src/fxml/Student-Lectures.fxml");
        }
    }

    private void openSchedule() throws IOException {
        String scheduleLocation = "./StudentPortal/src/database/TimeTable-4.jpeg";

        if (studentObj.getSemester() == 3) {
            scheduleLocation = "./StudentPortal/src/database/TimeTable-3.png";
        } else if (studentObj.getSemester() == 4) {
            scheduleLocation = "./StudentPortal/src/database/TimeTable-4.jpeg";
        }

        Desktop desktop = Desktop.getDesktop();
        File schedule = new File(scheduleLocation);

        desktop.open(schedule);

    }

    static String studentEmail;
    static Student studentObj;
    ArrayList<String> student = new ArrayList<>();

    public static void getStudentEmail(String stdEmail) {
        studentEmail = stdEmail;
    }

    public void getStudent() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/MainDB.accdb");

            prepStatDB = connectDB.prepareStatement("SELECT * FROM Students WHERE Email_Address = ?");
            prepStatDB.setString(1, studentEmail);
            ResultSet resultSetDB = prepStatDB.executeQuery();

            while (resultSetDB.next()) {
                student.add(resultSetDB.getString("First_Name"));
                student.add(resultSetDB.getString("Last_Name"));
                student.add(resultSetDB.getString("Email_Address"));
                student.add(resultSetDB.getString("Seat_Number"));
                student.add(resultSetDB.getString("CNIC"));
                student.add(resultSetDB.getString("Password"));
                student.add(resultSetDB.getString("Batch"));
                student.add(resultSetDB.getString("Semester"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void makeStudentObject() {
        String name = student.get(0) + " " + student.get(1);

        studentObj = new Student(name, student.get(2), student.get(3), student.get(4), student.get(5), student.get(6),
                Integer.parseInt(student.get(7)));
    }

    public ArrayList<String> arrEventMsg = new ArrayList<>();
    public ArrayList<String> arrEventDate = new ArrayList<>();

    Connection connectDB = null;
    PreparedStatement prepStatDB;

    public void getEvents() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/Events.accdb");

            Statement statement = connectDB.createStatement();
            ResultSet resultSetDB = statement
                    .executeQuery("Select Message, Date_Time from Events ORDER BY Date_Added DESC");
            while (resultSetDB.next()) {
                arrEventMsg.add(resultSetDB.getString("Message"));
                arrEventDate.add(resultSetDB.getString("Date_Time"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    public void eventGen() {
        for (int i = 0; i < 4; i++) {
            try {
                stdDashboardEventsContainer.setSpacing(5);
                HBox hb = new HBox();

                hb.setSpacing(5);

                Label msg = new Label(arrEventMsg.get(i));
                Label date = new Label(arrEventDate.get(i));

                msg.setStyle("-fx-padding: 2 2 2 2;" + "-fx-font-size: 14;" + "-fx-font-weight: bold;");
                date.setStyle("-fx-padding: 2 2 2 2;" + "-fx-font-size: 14;" + "-fx-font-weight: bold;");
                hb.getChildren().add(msg);
                hb.getChildren().add(date);

                stdDashboardEventsContainer.getChildren().add(hb);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

    ArrayList<String> arrCourses = new ArrayList<>();

    public void getCourses() {
        try {
            Connection connectDB = null;
            PreparedStatement prepStatDB;

            String sql = "SELECT Course_Name FROM Semester4";

            if (studentObj.getSemester() == 3) {
                sql = "SELECT Course_Name FROM Semester3";
            } else if (studentObj.getSemester() == 4) {
                sql = "SELECT Course_Name FROM Semester4";
            }

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/Courses.accdb");

            Statement statement = connectDB.createStatement();
            ResultSet resultSetDB = statement.executeQuery(sql);
            while (resultSetDB.next()) {
                arrCourses.add(resultSetDB.getString("Course_Name"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void coursesGen() {
        for (int i = 0; i < 6; i++) {
            try {
                stdDashboardCoursesContainer.setSpacing(5);
                HBox hb = new HBox();

                hb.setSpacing(5);

                Label course = new Label(arrCourses.get(i));

                course.setStyle("-fx-padding: 2 0 2 2;" + "-fx-font-size: 12;" + "-fx-font-weight: bold;");

                hb.getChildren().add(course);

                stdDashboardCoursesContainer.getChildren().add(hb);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
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
        getStudent();
        makeStudentObject();
        getEvents();
        getCourses();
        eventGen();
        coursesGen();
    }

}
