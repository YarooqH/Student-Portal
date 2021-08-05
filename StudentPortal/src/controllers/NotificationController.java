package StudentPortal.src.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.management.Notification;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import java.util.ArrayList;

import org.apache.commons.lang3.ObjectUtils.Null;

import java.sql.*;
import java.util.ResourceBundle;

public class NotificationController implements Initializable {
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
    private VBox stdNotificationBtnHolder;

    @FXML
    private TextArea stdNotificationTextBox;

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

    ArrayList<String> arrFrom = new ArrayList<>();
    ArrayList<String> arrMessage = new ArrayList<>();
    ArrayList<String> arrDate = new ArrayList<>();

    public void notificationGen() {
        Node[] node = new Node[9];
        for (int i = 0; i < node.length; i++) {
            try {
                HBox h = new HBox();
                h.setPrefWidth(337);

                Label l = new Label(arrMessage.get(i));
                l.getStylesheets().add("/StudentPortal/src/css/styles.css");
                l.getStyleClass().add("notification-label");

                JFXButton a = new JFXButton(arrFrom.get(i), l);
                a.setContentDisplay(ContentDisplay.RIGHT);

                a.getStylesheets().add("/StudentPortal/src/css/styles.css");
                a.getStyleClass().add("notification-from");

                final int j = i;

                a.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stdNotificationTextBox.setText(
                                "From: " + arrFrom.get(j) + "\n" + "Date: " + arrDate.get(j).replace(".000000", "")
                                        + "\n\n" + "Message: " + "\n" + arrMessage.get(j));
                    }
                });

                h.getChildren().add(a);

                stdNotificationBtnHolder.getChildren().add(h);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void getNotications() {
        try {
            Connection connectDB = null;
            PreparedStatement prepStatDB;

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/Notifications.accdb");

            Statement statement = connectDB.createStatement();
            ResultSet resultSetDB = statement
                    .executeQuery("Select Sent_By, Message, Date_Time from Notifications ORDER BY Date_Time DESC");
            while (resultSetDB.next()) {
                arrFrom.add(resultSetDB.getString("Sent_By"));
                arrMessage.add(resultSetDB.getString("Message"));
                arrDate.add(resultSetDB.getString("Date_Time"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        getNotications();
        notificationGen();
    }

}
