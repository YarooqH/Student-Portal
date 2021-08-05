package StudentPortal.src.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.management.Notification;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;

import org.apache.commons.lang3.ObjectUtils.Null;

import java.sql.*;
import java.util.ResourceBundle;

import StudentPortal.src.controllers.NotificationController;

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

    // @FXML
    // private VBox nodeItems = null;

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }

}
