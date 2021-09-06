package StudentPortal.src.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class AdminTeacherController implements Initializable {
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
    private JFXButton adminNotificationsAdd;

    @FXML
    private JFXButton adminEventsAdd;

    // Students

    @FXML
    private TableView adminTeacherTable;

    @FXML
    private void changeScene(ActionEvent event) {
        if (event.getSource() == adminDashboard) {
            ac.loadStage("/StudentPortal/src/fxml/Admin-Dashboard.fxml", event);
        } else if (event.getSource() == adminApprovals) {
            ac.loadWindow("/StudentPortal/src/fxml/Admin-Table-Window.fxml");
        } else if (event.getSource() == adminStudents) {
            ac.loadStage("/StudentPortal/src/fxml/Admin-Students-New.fxml", event);
        } else if (event.getSource() == adminTeachers) {
            ac.loadStage("/StudentPortal/src/fxml/Admin-Teachers-New.fxml", event);
        } else if (event.getSource() == adminLogout) {
            ac.loadStage("/StudentPortal/src/fxml/Login-Signup.fxml", event);
        } else if (event.getSource() == adminNotificationsAdd) {
            ac.loadWindow("/StudentPortal/src/fxml/Admin-Notifications-Window.fxml");
        } else if (event.getSource() == adminEventsAdd) {
            ac.loadWindow("/StudentPortal/src/fxml/Admin-Events-Window.fxml");
        }
    }

    Object queryId;
    String query;
    String[] userData;

    @FXML
    private void deleteAccount(ActionEvent event) {
        try {
            queryId = (adminTeacherTable.getSelectionModel().getSelectedItem());
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

            query = "DELETE FROM Teachers WHERE Email_Address = " + "'" + userData[2] + "'";
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/MainDB.accdb");
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }

            prepStatDB = connectDB.prepareStatement(query);
            prepStatDB.executeUpdate();

            AdminController ad = new AdminController();
            ad.loadStage("/StudentPortal/src/fxml/Admin-Teachers-New.fxml", event);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    Connection connectDB;
    PreparedStatement prepStatDB;

    public void connectorDB(String location) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connectDB = DriverManager.getConnection(location);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    ObservableList<ObservableList> data;

    public void fetCol(TableView table, String sql) {
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

    public void fetRow(TableView table, String sql) {
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
                data.add(row);

            }
            // stdTable.setItems(data);
            table.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    AdminController ac = new AdminController();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        connectorDB("jdbc:ucanaccess://StudentPortal/src/database/MainDB.accdb");
        fetCol(adminTeacherTable, "SELECT * FROM Teachers");
        fetRow(adminTeacherTable, "SELECT * FROM Teachers");

    }

}
