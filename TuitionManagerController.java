package com.example.project3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;


public class TuitionManagerController {
    @FXML
    protected void setResident() {
        this.International.setDisable(true);
        this.Tristate.setDisable(true);
        this.Ny.setDisable(true);
        this.Ct.setDisable(true);
        this.StudyAbroad.setDisable(true);
    }

    /**
     * now connect this with the GUI
     */
    @FXML
    private RadioButton Resident;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField creditsCompleted;
    @FXML
    private Button ChangeMajor;
    @FXML
    private Button remove;
    @FXML
    private Button addStudent;
    @FXML
    private TextField efname;
    @FXML
    private TextField elname;
    @FXML
    private DatePicker eDOB;
    @FXML
    private TextField credtsEnrolled;
    @FXML
    private Button enroll;
    @FXML
    private Button drop;
    @FXML
    private TextField sfname;
    @FXML
    private TextField slname;
    @FXML
    private TextField amount;
    @FXML
    private DatePicker sDOB;
    @FXML
    private Button UpdateScholarship;
    @FXML
    private DatePicker rosterDOB;
    @FXML
    private RadioButton NonResident;
    @FXML
    private RadioButton International;
    @FXML
    private RadioButton Tristate;
    @FXML
    private RadioButton Ct;
    @FXML
    private RadioButton Ny;
    @FXML
    private CheckBox StudyAbroad;
    @FXML
    private ToggleGroup Major;
    @FXML
    private ToggleGroup TriState;
    @FXML
    private ToggleGroup checkResident;
    private Roster studentArray = new Roster();
    private Enrollment enrolledStudents = new Enrollment();


    @FXML
    void add(ActionEvent action){

    }

}