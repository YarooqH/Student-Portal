package StudentPortal.src.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import com.jfoenix.controls.JFXButton;

import StudentPortal.src.controllers.WindowController;

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

    Connection connectDB;
    PreparedStatement prepStatDB;

    public AdminController() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/Students.accdb");
        } catch (Exception e) {
            // TODO: handle exception
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
            // TODO: handle exception
        }
    }

    private void loadWindow(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Parent root1;
            root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ;
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
    public void initialize(URL arg0, ResourceBundle arg1) { // TODO Auto-generated method stub

    }

}
