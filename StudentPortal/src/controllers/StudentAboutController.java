package StudentPortal.src.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class StudentAboutController implements Initializable {

    @FXML
    private Label stdNameLbl;

    @FXML
    private Label stdEmailLbl;

    @FXML
    private Label stdSeatNumberLbl;

    @FXML
    private Label stdCNICLbl;

    @FXML
    private Label stdBatchLbl;

    @FXML
    private Label stdSemesterLbl;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        stdNameLbl.setText(StudentController.studentObj.getName());
        stdEmailLbl.setText(StudentController.studentEmail);
        stdSeatNumberLbl.setText(StudentController.studentObj.getSeatNumber());
        stdCNICLbl.setText(StudentController.studentObj.getCNIC());
        stdBatchLbl.setText(StudentController.studentObj.getBatch());
        stdSemesterLbl.setText(String.valueOf(StudentController.studentObj.getSemester()));
    }

}
