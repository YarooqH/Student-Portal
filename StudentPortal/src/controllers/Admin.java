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

public class Admin implements Initializable {
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

    @FXML
    private void changeScene(ActionEvent event) {
        if (event.getSource() == adminDashboard) {
            loadStage("/StudentPortal/src/fxml/Admin-Dashboard.fxml", event);
        } else if (event.getSource() == adminApprovals) {
            loadStage("/StudentPortal/src/fxml/Admin-Approvals.fxml", event);
        } else if (event.getSource() == adminStudents) {
            loadStage("/StudentPortal/src/fxml/Admin-Students.fxml", event);
        } else if (event.getSource() == adminTeachers) {
            loadStage("/StudentPortal/src/fxml/Admin-Teachers.fxml", event);
        } else if (event.getSource() == adminLogout) {
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
