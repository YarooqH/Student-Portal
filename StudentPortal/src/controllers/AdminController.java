package StudentPortal.src.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;

import StudentPortal.src.model.Admin;

public class AdminController implements Initializable {
    @FXML
    private JFXButton adminDashboard;

    @FXML
    private JFXButton adminApprovals;

    @FXML
    private JFXButton adminStudents;

    @FXML
    private JFXButton adminTeachers;

    @FXML
    private JFXButton adminLogout;

    // Notifications

    @FXML
    private TextField adminNotificationMsg;

    @FXML
    private JFXButton adminNotificationSendBtn;

    @FXML
    private Label adminNotificationStatus;

    @FXML
    private JFXButton adminNotificationsAdd;

    // Events

    @FXML
    private JFXButton adminEventsAdd;

    @FXML
    private TextField adminEventMsg;

    @FXML
    private JFXButton adminEventSendBtn;

    @FXML
    private Label adminEventStatus;

    @FXML
    private DatePicker adminEventDate;

    // Students

    @FXML
    private TableView adminStudentTable;

    // Teachers

    @FXML
    private TableView adminTeacherTable;

    // Fee

    @FXML
    private JFXButton adminFeeAdd;

    @FXML
    private TextField adminFeeAmount;

    @FXML
    private Label adminFeeStatus;

    @FXML
    private JFXButton adminFeeUpdateBtn;

    @FXML
    private TextField adminFeeSemesterNum;

    @FXML
    private DatePicker AdminFeeDueDate;

    // Courses

    @FXML
    private JFXButton adminViewCourses;

    // Database Connection

    public Connection connectDB;
    public PreparedStatement prepStatDB;

    public void connectorDB(String location) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connectDB = DriverManager.getConnection(location);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @FXML
    private void changeScene(ActionEvent event) {
        if (event.getSource() == adminDashboard) {
            loadStage("/StudentPortal/src/fxml/Admin-Dashboard.fxml", event);
        } else if (event.getSource() == adminApprovals) {
            loadWindow("/StudentPortal/src/fxml/Admin-Table-Window.fxml");
        } else if (event.getSource() == adminStudents) {
            loadStage("/StudentPortal/src/fxml/Admin-Students.fxml", event);
        } else if (event.getSource() == adminTeachers) {
            loadStage("/StudentPortal/src/fxml/Admin-Teachers.fxml", event);
        } else if (event.getSource() == adminLogout) {
            loadStage("/StudentPortal/src/fxml/Login-Signup.fxml", event);
        } else if (event.getSource() == adminNotificationsAdd) {
            loadWindow("/StudentPortal/src/fxml/Admin-Notifications-Window.fxml");
        } else if (event.getSource() == adminEventsAdd) {
            loadWindow("/StudentPortal/src/fxml/Admin-Events-Window.fxml");
        } else if (event.getSource() == adminFeeAdd) {
            loadWindow("/StudentPortal/src/fxml/Admin-Fee-Window.fxml");
        } else if (event.getSource() == adminViewCourses) {
            loadWindow("/StudentPortal/src/fxml/Admin-Courses-Window.fxml");
        }
    }

    private void makeAdminObject() {
        Admin admin = new Admin("AdminUBIT", "42101-4642266-5", "ubit.studentportal@gmail.com");
    }

    @FXML
    private void updateFee(ActionEvent event) {
        try {
            String feeMsg;
            feeMsg = "Semester Fee : " + adminFeeAmount.getText() + " Only.\n" + "For Semester : "
                    + adminFeeSemesterNum.getText() + "\n" + "Due Date : " + AdminFeeDueDate.getValue() + "\n\n"
                    + "Can be paid at any branch of HBL and Sindh Bank and can also be paid online.";

            Connection connectDB = null;
            PreparedStatement prepStatDB;

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/Notifications.accdb");

            String SQL = "INSERT INTO Notifications(Sent_By, Message) values (?, ?)";

            prepStatDB = connectDB.prepareStatement(SQL);

            prepStatDB.setString(1, "Admin");
            prepStatDB.setString(2, feeMsg);

            if ((adminFeeAmount.getText().isEmpty()) && (adminFeeSemesterNum.getText().isEmpty())) {
                adminFeeStatus.setTextFill(Color.RED);
                adminFeeStatus.setText("Error! Please Fill All Fields!");
            } else {
                adminFeeStatus.setTextFill(Color.GREEN);
                adminFeeStatus.setText("Notification Sent!");

                prepStatDB.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @FXML
    void sendNotification(ActionEvent event) {
        try {
            String notification;
            notification = adminNotificationMsg.getText();

            Connection connectDB = null;
            PreparedStatement prepStatDB;

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/Notifications.accdb");

            String SQL = "INSERT INTO Notifications(Sent_By, Message) values (?, ?)";

            prepStatDB = connectDB.prepareStatement(SQL);

            prepStatDB.setString(1, "Admin");
            prepStatDB.setString(2, notification);

            if (notification.isEmpty()) {
                adminNotificationStatus.setTextFill(Color.RED);
                adminNotificationStatus.setText("Error! Please Enter Some Text!");
            } else {
                adminNotificationStatus.setTextFill(Color.GREEN);
                adminNotificationStatus.setText("Notification Sent!");

                prepStatDB.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @FXML
    private void sendEvent(ActionEvent event) {
        try {
            String evnt = adminEventMsg.getText();

            LocalDate ld = adminEventDate.getValue();

            Connection connectDB = null;
            PreparedStatement prepStatDB;

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/Events.accdb");

            String SQL = "INSERT INTO Events(Message, Date_Time) values (?, ?)";

            prepStatDB = connectDB.prepareStatement(SQL);

            if ((evnt.isEmpty()) || (ld == null)) {
                adminEventStatus.setTextFill(Color.RED);
                adminEventStatus.setText("Error! Please Fill All Fields!");
            } else {
                adminEventStatus.setTextFill(Color.GREEN);
                adminEventStatus.setText("Message Sent Successfully!");

                prepStatDB.setString(1, evnt);
                prepStatDB.setString(2, ld.toString());

                prepStatDB.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void loadWindow(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Parent root1;
            root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
        makeAdminObject();
    }

}
