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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.jfoenix.controls.JFXButton;

import StudentPortal.src.model.Student;

import java.sql.*;
import java.util.ResourceBundle;

public class StudentLectureController implements Initializable {
    @FXML
    private VBox lectureContainer;

    ArrayList<String> topicNameArr = new ArrayList<>();
    ArrayList<String> lectureCourseNameArr = new ArrayList<>();
    ArrayList<String> lectureCourseNumberArr = new ArrayList<>();
    ArrayList<String> lectureTeacherNameArr = new ArrayList<>();
    ArrayList<String> lectureLinkArr = new ArrayList<>();

    Connection connectDB = null;
    PreparedStatement prepStatDB;

    private void getLectures() {
        try {
            String sql = "SELECT Topic_Name, Course_Name, Course_Number, Link, Teacher_Name FROM Semester4";

            if (StudentController.studentObj.getSemester() == 3) {
                sql = "SELECT Topic_Name, Course_Name, Course_Number, Link, Teacher_Name FROM Semester3";
            } else if (StudentController.studentObj.getSemester() == 4) {
                sql = "SELECT Topic_Name, Course_Name, Course_Number, Link, Teacher_Name FROM Semester4";
            }

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/MainDB.accdb");

            Statement statement = connectDB.createStatement();
            ResultSet resultSetDB = statement.executeQuery(sql);

            while (resultSetDB.next()) {
                topicNameArr.add(resultSetDB.getString("Topic_Name"));
                lectureCourseNameArr.add(resultSetDB.getString("Course_Name"));
                lectureCourseNumberArr.add(resultSetDB.getString("Course_Number"));
                lectureLinkArr.add(resultSetDB.getString("Link"));
                lectureTeacherNameArr.add(resultSetDB.getString("Teacher_Name"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void lecturesGen() {
        for (int i = 0; i < lectureRowCount; i++) {
            try {
                HBox hb = new HBox(65);

                hb.setStyle("-fx-padding: 5 0 5 35;" + "-fx-background-color: white;" + "-fx-allignment: bottom-left;");

                Label lectureTopicNameLabel = new Label(topicNameArr.get(i));
                Label lectureCourseNameLabel = new Label(lectureCourseNameArr.get(i));
                Label lectureCourseNumberLabel = new Label(lectureCourseNumberArr.get(i));
                Label lectureTeacherNameLabel = new Label(lectureTeacherNameArr.get(i));
                // Label fileLocationLabel = new Label(fileLocationArr.get(i));
                lectureTopicNameLabel.setMinSize(lectureTopicNameLabel.USE_PREF_SIZE,
                        lectureTopicNameLabel.USE_PREF_SIZE);

                lectureCourseNameLabel.setMinSize(lectureCourseNameLabel.USE_PREF_SIZE,
                        lectureTopicNameLabel.USE_PREF_SIZE);

                lectureCourseNumberLabel.setMinSize(lectureCourseNumberLabel.USE_PREF_SIZE,
                        lectureTopicNameLabel.USE_PREF_SIZE);

                lectureTeacherNameLabel.setMinSize(lectureTeacherNameLabel.USE_PREF_SIZE,
                        lectureTopicNameLabel.USE_PREF_SIZE);

                lectureTopicNameLabel
                        .setStyle("-fx-font-size: 15;" + "-fx-font-weight: bold;" + "-fx-text-overrun: clip;");
                lectureCourseNameLabel.setStyle("-fx-font-size: 15;");
                lectureCourseNumberLabel.setStyle("-fx-font-size: 15;");
                lectureTeacherNameLabel.setStyle("-fx-font-size: 15;" + "-fx-padding: 0 0 0 10;");

                JFXButton playLectureBtn = new JFXButton("Play Lecture");

                playLectureBtn.setStyle("-fx-background-color: #8DC248;" + "-fx-text-fill: white;" + "-fx-cursor: hand;"
                        + "-fx-font-weight: bold;" + "-fx-font-Size: 12;");

                final int j = i;

                playLectureBtn.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            System.out.println(lectureLinkArr.get(j));

                            VBox vb = new VBox();
                            WebView wb = new WebView();

                            wb.getEngine().load(lectureLinkArr.get(j));

                            vb.getChildren().add(wb);

                            lectureLoad(vb);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                hb.getChildren().add(lectureTopicNameLabel);
                hb.getChildren().add(lectureCourseNameLabel);
                hb.getChildren().add(lectureCourseNumberLabel);
                hb.getChildren().add(lectureTeacherNameLabel);
                hb.getChildren().add(playLectureBtn);

                lectureContainer.getChildren().add(hb);

                // Make getResultsFromDB and Get Details from there
            } catch (Exception e) {
                System.out.println("error: " + e);
            }
        }
    }

    int lectureRowCount;

    private void getLectureRowCount() {
        // "sad '"+var+"'sad'"

        String sql = "SELECT COUNT(*) AS count FROM Semester4";

        if (StudentController.studentObj.getSemester() == 3) {
            sql = "SELECT COUNT(*) AS count FROM Semester3";
        } else if (StudentController.studentObj.getSemester() == 4) {
            sql = "SELECT COUNT(*) AS count FROM Semester4";
        }

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/MainDB.accdb");

            prepStatDB = connectDB.prepareStatement(sql);
            ResultSet resultSetDB = prepStatDB.executeQuery();

            while (resultSetDB.next()) {
                lectureRowCount = resultSetDB.getInt("count");
            }

        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void lectureLoad(VBox vb) throws Exception {
        Scene scene = new Scene(vb);

        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        getLectures();
        getLectureRowCount();
        lecturesGen();
    }

}
