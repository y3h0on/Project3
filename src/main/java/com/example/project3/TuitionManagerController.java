package com.example.project3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;




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
    private Label welcomeText;
    @FXML
    private RadioButton Resident;
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
    void Resident(ActionEvent event) {
        setResident();
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("CS213 Project 3");
    }
}