package StudentPortal.src.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import com.jfoenix.controls.JFXButton;

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
