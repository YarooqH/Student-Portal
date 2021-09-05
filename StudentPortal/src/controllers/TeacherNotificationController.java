package StudentPortal.src.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import java.sql.*;

public class TeacherNotificationController implements Initializable {

    @FXML
    private JFXButton teacherDashboard;

    @FXML
    private JFXButton teacherNotifications;

    @FXML
    private JFXButton teacherClasses;

    @FXML
    private JFXButton teacherResults;

    @FXML
    private JFXButton teacherLogout;

    // Events
    @FXML
    private VBox teacherDashboardEventsContainer;

    // Classes
    @FXML
    private VBox teacherDashboardClassesContainer;

    // Notifications

    @FXML
    public VBox teacherNotificationBtnHolder;

    @FXML
    public TextArea teacherNotificationTextBox;

    // username

    @FXML
    private Label teacherUserNameLbl;

    @FXML
    private void changeScene(ActionEvent event) {
        if (event.getSource() == teacherDashboard) {
            loadStage("/StudentPortal/src/fxml/Teacher-Dashboard.fxml", event);
        } else if (event.getSource() == teacherNotifications) {
            loadStage("/StudentPortal/src/fxml/Teacher-Notifications.fxml", event);
        } else if (event.getSource() == teacherClasses) {
            loadStage("/StudentPortal/src/fxml/Teacher-Lectures.fxml", event);
        } else if (event.getSource() == teacherResults) {
            loadStage("/StudentPortal/src/fxml/Teacher-Results.fxml", event);
        } else if (event.getSource() == teacherLogout) {
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

    public ArrayList<String> arrFrom = new ArrayList<>();
    public ArrayList<String> arrMessage = new ArrayList<>();
    public ArrayList<String> arrDate = new ArrayList<>();

    public void notificationGen(TextArea textBox) {
        for (int i = 0; i < 9; i++) {
            try {
                HBox h = new HBox();
                h.setPrefWidth(337);

                String msg = arrMessage.get(i).substring(0, 5) + "...";

                Label l = new Label(msg);
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
                        textBox.setText(
                                "From: " + arrFrom.get(j) + "\n" + "Date: " + arrDate.get(j).replace(".000000", "")
                                        + "\n\n" + "Message: " + "\n" + arrMessage.get(j));
                    }
                });

                h.getChildren().add(a);

                teacherNotificationBtnHolder.getChildren().add(h);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getNotications() {
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
        notificationGen(teacherNotificationTextBox);
        teacherUserNameLbl.setText(TeacherController.teacherName);
    }

}
