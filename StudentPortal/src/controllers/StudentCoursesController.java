package StudentPortal.src.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;

import com.jfoenix.controls.JFXButton;

import java.sql.*;
import java.util.ResourceBundle;

public class StudentCoursesController implements Initializable {
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

    // Events

    @FXML
    public VBox stdDashboardEventsContainer;

    // Courses

    @FXML
    private VBox stdDashboardCoursesContainer;

    @FXML
    private TableView stdCoursesTable;

    StudentController sc = new StudentController();

    @FXML
    private void changeScene(ActionEvent event) {
        if (event.getSource() == stdDashboard) {
            sc.loadStage("/StudentPortal/src/fxml/Student-Dashboard.fxml", event);
        } else if (event.getSource() == stdNotifications) {
            sc.loadStage("/StudentPortal/src/fxml/Student-Notifications-New.fxml", event);
        } else if (event.getSource() == stdCourses) {
            sc.loadStage("/StudentPortal/src/fxml/Student-Courses.fxml", event);
        } else if (event.getSource() == stdGrades) {
            sc.loadStage("/StudentPortal/src/fxml/Student-Grades.fxml", event);
        } else if (event.getSource() == stdLogout) {
            sc.loadStage("/StudentPortal/src/fxml/Login-Signup.fxml", event);
        }
    }

    Connection connectDB;
    PreparedStatement prepStatDB;

    ObservableList<ObservableList> data;

    public void fetCol() {
        try {
            String sql = "SELECT * FROM Semester4";

            if (StudentController.studentObj.getSemester() == 3) {
                sql = "SELECT * FROM Semester3";
            } else if (StudentController.studentObj.getSemester() == 4) {
                sql = "SELECT * FROM Semester4";
            }
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/Courses.accdb");
            ResultSet resultSetDB = connectDB.createStatement().executeQuery(sql);

            for (int i = 0; i < resultSetDB.getMetaData().getColumnCount(); i++) {
                // We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(resultSetDB.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setCellValueFactory(
                        new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                                return new SimpleStringProperty((String) param.getValue().get(j));
                            }
                        });
                stdCoursesTable.getColumns().addAll(col);
            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void fetRow() {
        data = FXCollections.observableArrayList();
        ResultSet rs;

        String sql = "SELECT * FROM Semester4";

        if (StudentController.studentObj.getSemester() == 3) {
            sql = "SELECT * FROM Semester3";
        } else if (StudentController.studentObj.getSemester() == 4) {
            sql = "SELECT * FROM Semester4";
        }

        try {
            rs = connectDB.createStatement().executeQuery(sql);

            while (rs.next()) {
                // Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    // Iterate Column
                    row.add(rs.getString(i));
                }
                data.add(row);

            }
            // stdTable.setItems(data);
            stdCoursesTable.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // WindowController wc = new WindowController();
        // wc.WindowController("jdbc:ucanaccess://StudentPortal/src/database/MainDB.accdb");
        fetCol();
        fetRow();
    }

}
