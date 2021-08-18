package StudentPortal.src.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import java.sql.*;

import StudentPortal.src.model.Teacher;

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

    // Events
    @FXML
    private VBox teacherDashboardEventsContainer;

    // Classes
    @FXML
    private VBox teacherDashboardClassesContainer;

    // Courses
    @FXML
    private VBox teacherDashboardCoursesContainer;

    // Notifications

    @FXML
    public VBox teacherNotificationBtnHolder;

    @FXML
    public TextArea teacherNotificationTextBox;

    // Lectures

    @FXML
    private TextField lectureTopicName;

    @FXML
    private TextField lectureCourseName;

    @FXML
    private TextField lectureCourseNum;

    @FXML
    private TextField lectureSemesterNum;

    @FXML
    private TextField lectureLink;

    @FXML
    private Label lectureAlertLabel;

    @FXML
    private JFXButton addLectureBtn;

    // Results

    @FXML
    private TextField resultsCourseName;

    @FXML
    private TextField resultsCourseNum;

    @FXML
    private TextField resultsSemesterNum;

    @FXML
    private JFXButton addResultBtn;

    @FXML
    private Label fileAddress;

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

    static String teacherEmail;
    Teacher teacherObj;
    ArrayList<String> teacher = new ArrayList<>();

    public static void getTeacherEmail(String email) {
        teacherEmail = email;
    }

    public void getTeacher() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/MainDB.accdb");

            prepStatDB = connectDB.prepareStatement("SELECT * FROM Teachers WHERE Email_Address = ?");
            prepStatDB.setString(1, teacherEmail);
            ResultSet resultSetDB = prepStatDB.executeQuery();

            while (resultSetDB.next()) {
                teacher.add(resultSetDB.getString("First_Name"));
                teacher.add(resultSetDB.getString("Last_Name"));
                teacher.add(resultSetDB.getString("Email_Address"));
                teacher.add(resultSetDB.getString("Password"));
                teacher.add(resultSetDB.getString("Semesters"));
                teacher.add(resultSetDB.getString("Post"));
                teacher.add(resultSetDB.getString("CNIC"));
                teacher.add(resultSetDB.getString("Courses"));
                teacher.add(resultSetDB.getString("Number_Of_Classes"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void makeTeacherObject() {
        String name = teacher.get(0) + " " + teacher.get(1);

        teacherObj = new Teacher(name, teacher.get(2), teacher.get(3), teacher.get(5), teacher.get(6), teacher.get(4),
                teacher.get(7));
    }

    private void addClasses() {
        makeTeacherObject();
        ArrayList<Integer> sem = teacherObj.getSemesterArr();
        for (int i = 0; i < sem.size(); i++) {
            try {
                Label l = new Label("BSCS " + sem.get(i) + " Semester");
                l.setStyle("-fx-font-size: 16;" + "-fx-font-weight: bold;");

                teacherDashboardClassesContainer.getChildren().add(l);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

    private void addCourses() {
        makeTeacherObject();
        ArrayList<String> courses = teacherObj.getMyCoursesArr();
        for (String string : courses) {
            System.out.println(string);
        }
        for (int i = 0; i < courses.size(); i++) {
            try {
                Label l = new Label("BSCS " + courses.get(i));
                l.setStyle("-fx-font-size: 16;" + "-fx-font-weight: bold;");

                teacherDashboardCoursesContainer.getChildren().add(l);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

    private File f;
    private Path to;
    private Path from;
    private String sql;

    private void fileCopy() throws IOException {
        from = Paths.get(f.toURI());
        if (resultsSemesterNum.getText().equals("4")) {
            to = Paths.get("./StudentPortal/src/database/Semester4/" + f.getName());
            sql = "jdbc:ucanaccess://StudentPortal/src/database/Semester4/Results.accdb";
        } else if (resultsSemesterNum.getText().equals("3")) {
            to = Paths.get("./StudentPortal/src/database/Semester3/" + f.getName());
            sql = "jdbc:ucanaccess://StudentPortal/src/database/Semester3/Results.accdb";
        }

        Files.copy(from, to);

        File renamed = new File("./StudentPortal/src/database/Semester4/" + resultsCourseName.getText() + "-" + "BSCS "
                + resultsCourseNum.getText() + "-" + teacherObj.getName() + ".xlsx");

        File newFile = to.toFile();
        newFile.renameTo(renamed);
        updateResultAlert();

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connectDB = DriverManager.getConnection(sql);

            String insert = "INSERT INTO Results(Course_Name, Course_Number, Teacher_Name, File_Location) values (?, ?, ?, ?)";

            prepStatDB = connectDB.prepareStatement(insert);

            prepStatDB.setString(1, resultsCourseName.getText());
            prepStatDB.setString(2, "BSCS " + resultsCourseNum.getText());
            prepStatDB.setString(3, teacherObj.getName());
            prepStatDB.setString(4, renamed.getPath());

            prepStatDB.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    void updateResultAlert() {
        fileAddress.setText("File Uploaded Successfully!");
        fileAddress.setStyle("-fx-text-fill: green;" + "-fx-text-align: center;");
    }

    @FXML
    private void addResult(ActionEvent event) {
        try {
            fileCopy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void fileBrowse(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Excel Files", "*.xlsx"));

        f = fc.showOpenDialog(null);

        if (f != null) {
            fileAddress.setText(f.getAbsolutePath());
        }
    }

    // public void teacherClasses() {

    // }

    public ArrayList<String> arrFrom = new ArrayList<>();
    public ArrayList<String> arrMessage = new ArrayList<>();
    public ArrayList<String> arrDate = new ArrayList<>();

    public void notificationGen(TextArea textBox) {
        for (int i = 0; i < 9; i++) {
            try {
                HBox h = new HBox();
                h.setPrefWidth(337);

                Label l = new Label(arrMessage.get(i));
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

    public ArrayList<String> arrEventMsg = new ArrayList<>();
    public ArrayList<String> arrEventDate = new ArrayList<>();

    Connection connectDB = null;
    PreparedStatement prepStatDB;

    public void getEvents() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/Events.accdb");

            Statement statement = connectDB.createStatement();
            ResultSet resultSetDB = statement
                    .executeQuery("Select Message, Date_Time from Events ORDER BY Date_Added DESC");
            while (resultSetDB.next()) {
                arrEventMsg.add(resultSetDB.getString("Message"));
                arrEventDate.add(resultSetDB.getString("Date_Time"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    public void eventGen() {
        for (int i = 0; i < 4; i++) {
            try {
                teacherDashboardEventsContainer.setSpacing(5);
                HBox hb = new HBox();

                hb.setSpacing(5);

                Label msg = new Label(arrEventMsg.get(i));
                Label date = new Label(arrEventDate.get(i));

                msg.setStyle("-fx-padding: 2 2 2 2;" + "-fx-font-size: 14;" + "-fx-font-weight: bold;");
                date.setStyle("-fx-padding: 2 2 2 2;" + "-fx-font-size: 14;" + "-fx-font-weight: bold;");
                hb.getChildren().add(msg);
                hb.getChildren().add(date);

                teacherDashboardEventsContainer.getChildren().add(hb);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

    @FXML
    void addLecture(ActionEvent event) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            connectDB = DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/MainDB.accdb");
            String semesterInfo = lectureSemesterNum.getText();
            String SQL = "empty";
            if (semesterInfo.equals("4")) {
                SQL = "INSERT INTO Semester4(Topic_Name, Course_Name, Course_Number, Link, Teacher_Name) values (?, ?, ?, ?, ?)";
            } else if (semesterInfo.equals("3")) {
                SQL = "INSERT INTO Semester3(Topic_Name, Course_Name, Course_Number, Link, Teacher_Name) values (?, ?, ?, ?, ?)";
            } else {
                System.out.println("Error! Wrong Semester Input");
            }

            System.out.println(SQL);

            prepStatDB = connectDB.prepareStatement(SQL);

            prepStatDB.setString(1, lectureTopicName.getText());
            prepStatDB.setString(2, lectureCourseName.getText());
            prepStatDB.setString(3, "BSCS " + lectureCourseNum.getText());
            prepStatDB.setString(4, lectureLink.getText());
            prepStatDB.setString(5, teacherObj.getName());

            prepStatDB.executeUpdate();

            lectureAlertLabel.setText("Lecture Uploaded!");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // ArrayList<String> arrClasses = new ArrayList<>();

    // public void getClasses() {
    // try {
    // Connection connectDB = null;
    // PreparedStatement prepStatDB;

    // Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

    // connectDB =
    // DriverManager.getConnection("jdbc:ucanaccess://StudentPortal/src/database/MainDB.accdb");

    // Statement statement = connectDB.createStatement();
    // ResultSet resultSetDB = statement.executeQuery("Select Course_Name from
    // Classes");
    // while (resultSetDB.next()) {
    // arrClasses.add(resultSetDB.getString("Course_Name"));
    // }
    // } catch (Exception e) {
    // System.out.println("Error: " + e);
    // }
    // }

    // public void ClassesGen() {
    // for (int i = 0; i < 6; i++) {
    // try {
    // teacherDashboardClassesContainer.setSpacing(5);
    // HBox hb = new HBox();

    // hb.setSpacing(5);

    // Label course = new Label(arrClasses.get(i));

    // course.setStyle("-fx-padding: 2 0 2 2;" + "-fx-font-size: 12;" +
    // "-fx-font-weight: bold;");

    // hb.getChildren().add(course);

    // teacherDashboardClassesContainer.getChildren().add(hb);
    // } catch (Exception e) {
    // System.out.println("Error: " + e);
    // }
    // }
    // }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // All these methods can be made static
        getTeacher();
        getEvents();
        getNotications();
        addClasses();
        addCourses();
        eventGen();
        notificationGen(teacherNotificationTextBox);
    }

}
