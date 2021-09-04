package StudentPortal.src.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class TeacherAboutController implements Initializable {

    @FXML
    private Label teacherNameLbl;

    @FXML
    private Label teacherEmailLbl;

    @FXML
    private Label teacherRoleLbl;

    @FXML
    private Label teacherCNICLbl;

    @FXML
    private Label teacherCoursesLbl;

    ArrayList<String> coursesArr = TeacherController.teacherObj.getMyCoursesArr();
    // coursesArr.forEach(action);

    public void kol() {
        System.out.println(TeacherController.teacherObj.getName());
        System.out.println(TeacherController.teacherObj.getRole());
        System.out.println(TeacherController.teacherObj.getCNIC());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        kol();
        teacherNameLbl.setText(TeacherController.teacherObj.getName());
        teacherEmailLbl.setText(TeacherController.teacherEmail);
        teacherRoleLbl.setText(TeacherController.teacherObj.getRole());
        teacherCNICLbl.setText(TeacherController.teacherObj.getCNIC());
        teacherCoursesLbl.setText(coursesArr.toString().replace("[", "").replace("]", ""));

    }

}
