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
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;

public class TeacherController implements Initializable {

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

    @FXML
    private void changeScene(ActionEvent event) {
        if (event.getSource() == teacherDashboard) {
            loadStage("/StudentPortal/src/fxml/Teacher-Dashboard.fxml", event);
        } else if (event.getSource() == teacherNotifications) {
            loadStage("/StudentPortal/src/fxml/Teacher-Notifications.fxml", event);
        } else if (event.getSource() == teacherClasses) {
            loadStage("/StudentPortal/src/fxml/Teacher-Classes.fxml", event);
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }

}
