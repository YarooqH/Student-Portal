package StudentPortal.src.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.*;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TableView;

public class AdminCoursesController implements Initializable {

    @FXML
    private TableView semester3Table;

    @FXML
    private TableView semester4Table;

    Connection connectDB;
    PreparedStatement prepStatDB;

    ObservableList<ObservableList> data;
    String SQLSemester3 = "SELECT * FROM Semester3";
    String SQLSemester4 = "SELECT * FROM Semester4";
    String dbLocation = "jdbc:ucanaccess://StudentPortal/src/database/Courses.accdb";

    Object queryId;
    String query;

    String[] userData;

    @FXML
    private void openAddCourseWindow(ActionEvent event) {
        AdminController ad = new AdminController();
        ad.loadStage("/StudentPortal/src/fxml/Admin-Add-Course-Window.fxml", event);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        WindowController.WindowController(dbLocation);
        WindowController.fetCol(semester3Table, SQLSemester3);
        WindowController.fetRow(semester3Table, SQLSemester3);

        WindowController.WindowController(dbLocation);
        WindowController.fetCol(semester4Table, SQLSemester4);
        WindowController.fetRow(semester4Table, SQLSemester4);

    }

}
