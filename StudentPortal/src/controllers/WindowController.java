package StudentPortal.src.controllers;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import StudentPortal.src.controllers.AdminController;
import StudentPortal.src.App;

public class WindowController implements Initializable {
    @FXML
    private TableView studentTable;
    @FXML
    private TableView teacherTable;

    Connection connectDB;
    PreparedStatement prepStatDB;

    ObservableList<ObservableList> data;
    String SQLStudent = "SELECT * from Student_Approvals";
    String SQLTeacher = "SELECT * from Teacher_Approvals";
    String studentDBLocation = "jdbc:ucanaccess://StudentPortal/src/database/Students.accdb";
    String teacherDBLocation = "jdbc:ucanaccess://StudentPortal/src/database/Teachers.accdb";

    Object queryId;
    String query;

    String[] userData;

    public void WindowController(String fileLocation) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connectDB = DriverManager.getConnection(fileLocation);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: " + e);
        }
    }

    private void fetCol(TableView table, String sql) {
        try {
            ResultSet resultSetDB = connectDB.createStatement().executeQuery(sql);

            System.out.println("Connected");

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
                System.out.println("Adding Columns");
                table.getColumns().addAll(col);

                System.out.println("Column [" + i + "] ");

            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    private void fetRow(TableView table, String sql) {
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = connectDB.createStatement().executeQuery(sql);

            while (rs.next()) {
                // Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    // Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }
            // stdTable.setItems(data);
            table.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void acceptAccount(TableView table, String DBTableName, String fileLocation, String sql) {
        try {
            queryId = (table.getSelectionModel().getSelectedItem());
            userData = queryId.toString().split(",");
            int i = 0;
            for (String string : userData) {
                userData[i] = string.strip();
                i++;
            }
            String firstName = userData[0].replace("[", "");
            String lastName = userData[1];
            String email = userData[2];
            String seatNum = userData[3];
            String CNIC = userData[4];
            String password = userData[5];
            String batch = userData[6];
            String courses = userData[7];
            String semester = userData[8].replace("]", "");

            // Make Object HERE

            // for (String s : userData) {
            // System.out.println(s);
            // }

            query = "DELETE FROM `" + DBTableName + "` WHERE Email_Address = " + "'" + email + "'";
            System.out.println(query);
            // connection = DbConnect.getConnect();
            WindowController(fileLocation);
            prepStatDB = connectDB.prepareStatement(query);
            prepStatDB.executeUpdate();

            // preparedStatement = connection.prepareStatement(query);
            // preparedStatement.execute();
            // WindowController(fileLocation);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @FXML
    void acceptStdAccountBtn(ActionEvent event) {
        acceptAccount(studentTable, "Student_Approvals", studentDBLocation, SQLStudent);
        AdminController ad = new AdminController();
        ad.loadStage("/StudentPortal/src/fxml/Admin-Table-Window.fxml", event);
        // fetCol(studentTable, SQLStudent);
        // fetRow(studentTable, SQLStudent);
        // System.out.println("Henlo");
    }

    @FXML
    void acceptTeacherAccountBtn(ActionEvent event) {
        acceptAccount(teacherTable, "Teacher_Approvals", teacherDBLocation, SQLTeacher);
        AdminController ad = new AdminController();
        ad.loadStage("/StudentPortal/src/fxml/Admin-Table-Window.fxml", event);
        // fetCol(teacherTable, SQLTeacher);
        // fetRow(teacherTable, SQLTeacher);
        // System.out.println("Henlo");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        WindowController(studentDBLocation);
        fetCol(studentTable, SQLStudent);
        fetRow(studentTable, SQLStudent);

        WindowController(teacherDBLocation);
        fetCol(teacherTable, SQLTeacher);
        fetRow(teacherTable, SQLTeacher);
    }

}
