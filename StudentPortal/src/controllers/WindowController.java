package StudentPortal.src.controllers;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import StudentPortal.src.controllers.AdminController;

public class WindowController implements Initializable {
    @FXML
    private TableView studentTable;
    @FXML
    private TableView teacherTable;

    static Connection connectDB;
    static PreparedStatement prepStatDB;
    static ResultSet rs;

    static ObservableList<ObservableList> data;
    String SQLStudent = "SELECT * from Student_Approvals";
    String SQLTeacher = "SELECT * from Teacher_Approvals";
    String studentDBLocation = "jdbc:ucanaccess://StudentPortal/src/database/Students.accdb";
    String teacherDBLocation = "jdbc:ucanaccess://StudentPortal/src/database/Teachers.accdb";

    Object queryId;
    String query;

    String[] userData;

    public static void WindowController(String fileLocation) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connectDB = DriverManager.getConnection(fileLocation);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: " + e);
        }
    }

    public static void fetCol(TableView table, String sql) {
        try {
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
                table.getColumns().addAll(col);
            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public static void fetRow(TableView table, String sql) {
        data = FXCollections.observableArrayList();
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
            table.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void acceptStdAccount(TableView table, String DBTableName, String fileLocation, String sql) {
        try {
            queryId = (table.getSelectionModel().getSelectedItem());
            userData = queryId.toString().split(",");
            int i = 0;
            for (String string : userData) {
                userData[i] = string.strip();
                if (string.contains("[")) {
                    userData[i] = string.replace("[", "");
                } else if (string.contains("]")) {
                    string = string.replace("]", "");
                    userData[i] = string.strip();
                }
                i++;
            }

            query = "DELETE FROM `" + DBTableName + "` WHERE Email_Address = " + "'" + userData[2] + "'";

            WindowController(fileLocation);
            prepStatDB = connectDB.prepareStatement(query);
            prepStatDB.executeUpdate();

            WindowController("jdbc:ucanaccess://StudentPortal/src/database/MainDB.accdb");
            prepStatDB = connectDB.prepareStatement(
                    "INSERT INTO Students(First_Name, Last_Name, Email_Address, Seat_Number, CNIC, Password, Batch, Number_Of_Courses, Semester) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            prepStatDB.setString(1, userData[0]);
            prepStatDB.setString(2, userData[1]);
            prepStatDB.setString(3, userData[2]);
            prepStatDB.setString(4, userData[3]);
            prepStatDB.setString(5, userData[4]);
            prepStatDB.setString(6, userData[5]);
            prepStatDB.setInt(7, Integer.parseInt(userData[6]));
            prepStatDB.setInt(8, Integer.parseInt(userData[7]));
            prepStatDB.setInt(9, Integer.parseInt(userData[8]));

            prepStatDB.executeUpdate();

            // Email Functionality

            // preparedStatement = connection.prepareStatement(query);
            // preparedStatement.execute();
            // WindowController(fileLocation);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void acceptTeacherAccount(TableView table, String DBTableName, String fileLocation, String sql) {
        try {
            queryId = (table.getSelectionModel().getSelectedItem());
            userData = queryId.toString().split(",");
            int i = 0;
            for (String string : userData) {
                userData[i] = string.strip();
                if (string.contains("[")) {
                    userData[i] = string.replace("[", "");
                } else if (string.contains("]")) {
                    string = string.replace("]", "");
                    userData[i] = string.strip();
                }
                i++;
            }

            for (String string : userData) {
                System.out.println(string);
            }

            query = "DELETE FROM `" + DBTableName + "` WHERE Email_Address = " + "'" + userData[2] + "'";

            WindowController(fileLocation);
            prepStatDB = connectDB.prepareStatement(query);
            prepStatDB.executeUpdate();

            WindowController("jdbc:ucanaccess://StudentPortal/src/database/MainDB.accdb");
            prepStatDB = connectDB.prepareStatement(
                    "INSERT INTO Teachers(First_Name, Last_Name, Email_Address, Password, Semesters, Post, CNIC, Courses, Number_Of_Classes) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            prepStatDB.setString(1, userData[0]);
            prepStatDB.setString(2, userData[1]);
            prepStatDB.setString(3, userData[2]);
            prepStatDB.setString(4, userData[3]);
            prepStatDB.setString(5, userData[4]);
            prepStatDB.setString(6, userData[5]);
            prepStatDB.setString(7, userData[6]);
            prepStatDB.setString(8, userData[7]);
            prepStatDB.setInt(9, Integer.parseInt(userData[8]));

            prepStatDB.executeUpdate();

            // Email Functionality

            // preparedStatement = connection.prepareStatement(query);
            // preparedStatement.execute();
            // WindowController(fileLocation);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void declineAccount(TableView table, String DBTableName, String fileLocation, String sql) {
        try {
            queryId = (table.getSelectionModel().getSelectedItem());
            int i = 0;
            for (String string : userData) {
                userData[i] = string.strip();
                i++;
            }
            String email = userData[2];
            query = "DELETE FROM `" + DBTableName + "` WHERE Email_Address = " + "'" + email + "'";
            System.out.println(query);

            WindowController(fileLocation);
            prepStatDB = connectDB.prepareStatement(query);
            prepStatDB.executeUpdate();

            // Email Functionality
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @FXML
    void acceptStdAccountBtn(ActionEvent event) {
        acceptStdAccount(studentTable, "Student_Approvals", studentDBLocation, SQLStudent);
        AdminController ad = new AdminController();
        ad.loadStage("/StudentPortal/src/fxml/Admin-Table-Window.fxml", event);
        // fetCol(studentTable, SQLStudent);
        // fetRow(studentTable, SQLStudent);
        // System.out.println("Henlo");
    }

    @FXML
    void acceptTeacherAccountBtn(ActionEvent event) {
        acceptTeacherAccount(teacherTable, "Teacher_Approvals", teacherDBLocation, SQLTeacher);
        AdminController ad = new AdminController();
        ad.loadStage("/StudentPortal/src/fxml/Admin-Table-Window.fxml", event);
        // fetCol(teacherTable, SQLTeacher);
        // fetRow(teacherTable, SQLTeacher);
        // System.out.println("Henlo");
    }

    // Need to add
    @FXML
    void declineStdAccountBtn(ActionEvent event) {
        declineAccount(studentTable, "Student_Approvals", studentDBLocation, SQLStudent);
        AdminController ad = new AdminController();
        ad.loadStage("/StudentPortal/src/fxml/Admin-Table-Window.fxml", event);
    }

    @FXML
    void declineTeacherAccountBtn(ActionEvent event) {
        declineAccount(teacherTable, "Teacher_Approvals", teacherDBLocation, SQLTeacher);
        AdminController ad = new AdminController();
        ad.loadStage("/StudentPortal/src/fxml/Admin-Table-Window.fxml", event);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        WindowController(studentDBLocation);
        fetCol(studentTable, SQLStudent);
        fetRow(studentTable, SQLStudent);

        WindowController(teacherDBLocation);
        fetCol(teacherTable, SQLTeacher);
        fetRow(teacherTable, SQLTeacher);
    }

}
