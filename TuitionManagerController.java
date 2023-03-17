package com.example.project3;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;


public class TuitionManagerController {


    private Major m;
    @FXML
    private RadioButton rbEE, rbITI, rbCS, rbMATH, rbBAIT;
    @FXML
    private RadioButton Resident;
    @FXML
    private TextField fname, lname, creditsCompleted;
    @FXML
    private Button ChangeMajor;
    @FXML
    private Button remove;
    @FXML
    private Button addStudent;
    @FXML
    private TextField efname, elname;
    @FXML
    private DatePicker eDOB, sDOB, rosterDOB;
    @FXML
    private TextField creditsEnrolled, amount;
    @FXML
    private Button enroll;
    @FXML
    private Button drop;
    @FXML
    private TextField sfname, slname;
    @FXML
    private Button updateScholarship;
    @FXML
    private RadioButton NonResident, International, Tristate;
    @FXML
    private RadioButton Ct;
    @FXML
    private RadioButton Ny;
    @FXML
    private CheckBox isStudyAbroad;
    @FXML
    private TextArea textArea;
    @FXML
    private ToggleGroup Major, TriState, CheckResident;
    private Roster studentArray = new Roster();
    private Enrollment enrolledStudents = new Enrollment();

    private static final int SENIOR_UPPER_END = 120;

    @FXML
    // figure this out
    protected void setResident() {
        this.International.setDisable(true);
        this.Tristate.setDisable(true);
        this.Ny.setDisable(true);
        this.Ct.setDisable(true);
        this.isStudyAbroad.setDisable(true);
    }



    private boolean areCreditsValid(String s) {
        try {
            if ((Integer.parseInt(s) >= 0 && Integer.parseInt(s) <= SENIOR_UPPER_END) == true) ;
            return true;
        } catch (Exception e) {
            textArea.appendText("Credits completed invalid: not an integer!" + "\n");
            return false;
        }
    }
    private boolean NewIsValid(Date date){
        if(date.isYoung()){
            textArea.appendText("DOB Invalid " + date + " is younger than 16 years" + "\n");
            return false;
        }
        if(!date.isValid()){
            textArea.appendText("DOB Invalid " + date + " is not a calendar date" + "\n");
            return false;
        }
        return true;
    }
    private boolean errorsInRoster(String[] info){
        if(info[0].isBlank()){
            textArea.appendText("first name is empty" + "\n");
            return false;
        }
        if(info[1].isBlank()){
            textArea.appendText("last name is empty" + "\n");
            return false;
        }
        if(info[3].isBlank()){
            textArea.appendText("credits Completed is empty" + "\n");
            return false;
        }
        return true;
    }

    @FXML
    void EE(ActionEvent event){
        m = com.example.project3.Major.valueOf(rbEE.getText());
    }
    @FXML
    void BAIT(ActionEvent event){
        m = com.example.project3.Major.valueOf(rbBAIT.getText());
    }
    @FXML
    void ITI(ActionEvent event){
        m = com.example.project3.Major.valueOf(rbITI.getText());
    }
    @FXML
    void CS(ActionEvent event){
        m = com.example.project3.Major.valueOf(rbCS.getText());
    }
    @FXML
    void MATH(ActionEvent event){
        m = com.example.project3.Major.valueOf(rbMATH.getText());
    }

    private void addResidentStudent(String[] tokens) {
        if(errorsInRoster(tokens)) {
            Date date = new Date(tokens[2]);
            if (NewIsValid(date)) {
                if (areCreditsValid(tokens[3])) {
                    if (Integer.parseInt(tokens[3]) >= 0 && Integer.parseInt(tokens[3]) <= SENIOR_UPPER_END) {
                        Student student = new Resident(new Profile(tokens[1], tokens[0], tokens[2]), m.name(), Integer.parseInt(tokens[3]));
                        if (!studentArray.contains(student)) {
                            studentArray.add(student);
                            textArea.appendText(student.getProfile() + " resident added to the roster" + "\n");
                        } else {
                            textArea.appendText(student.getProfile() + " already exists in roster " + "\n");
                        }
                    } else {
                        textArea.appendText("Credits completed invalid: cannot be negative!" + "\n");
                    }
                }
            }
        }
    }
    private void addInternationalStudentStudyAbroad(String[] tokens){
        if(errorsInRoster(tokens)) {
            Date date = new Date(tokens[2]);
            if (date.isValid()) {
                if (areCreditsValid(tokens[3])) {
                    if (Integer.parseInt(tokens[3]) >= 0 && Integer.parseInt(tokens[3]) <= SENIOR_UPPER_END) {
                        Student student = new International(new Profile(tokens[1], tokens[0], tokens[2]), m.name(), Integer.parseInt(tokens[3]), true);
                        if (!studentArray.contains(student)) {
                            studentArray.add(student);
                            textArea.appendText(student.getProfile() + " added to the roster" + "\n");
                        } else {
                            textArea.appendText(student.getProfile() + " already exists in roster " + "\n");
                        }
                    } else {
                        textArea.appendText("Credits completed invalid: cannot be negative!" + "\n");
                    }
                }
            } else {
                textArea.appendText("DOB invalid: " + tokens[2] + " is not a valid calendar date" + "\n");
            }
        }
    }
    private void addInternationalStudent(String[] tokens){
        if(errorsInRoster(tokens)) {
            Date date = new Date(tokens[2]);
            if (date.isValid()) {
                if (areCreditsValid(tokens[3])) {
                    if (Integer.parseInt(tokens[3]) >= 0 && Integer.parseInt(tokens[3]) <= SENIOR_UPPER_END) {
                        Student student = new International(new Profile(tokens[1], tokens[0], tokens[2]), m.name(), Integer.parseInt(tokens[3]));
                        if (!studentArray.contains(student)) {
                            studentArray.add(student);
                            textArea.appendText(student.getProfile() + " added to the roster" + "\n");
                        } else {
                            textArea.appendText(student.getProfile() + " already exists in roster " + "\n");
                        }
                    } else {
                        textArea.appendText("Credits completed invalid: cannot be negative!" + "\n");
                    }
                }
            } else {
                textArea.appendText("DOB invalid: " + tokens[2] + " is not a valid calendar date" + "\n");
            }
        }
    }

    private void addNonResidentStudent(String[] tokens){
        if(errorsInRoster(tokens)) {
            Date date = new Date(tokens[2]);
            if (date.isValid()) {
                if (areCreditsValid(tokens[3])) {
                    if (Integer.parseInt(tokens[3]) >= 0 && Integer.parseInt(tokens[3]) <= SENIOR_UPPER_END) {
                        Student student = new NonResident(new Profile(tokens[1], tokens[0], tokens[2]), m.name(), Integer.parseInt(tokens[3]));
                        if (!studentArray.contains(student)) {
                            studentArray.add(student);
                            textArea.appendText(student.getProfile() + " non resident student added to the roster" + "\n");
                        } else {
                            textArea.appendText(student.getProfile() + " already exists in roster " + "\n");
                        }
                    } else {
                        textArea.appendText("Credits completed invalid: cannot be negative!" + "\n");
                    }
                }
            } else {
                textArea.appendText("DOB invalid: " + tokens[3] + " is not a valid calendar date" + "\n");
            }
        }
    }
    private void addTriStateStudent(String[] tokens, boolean b){
        if(errorsInRoster(tokens)) {
                if (areCreditsValid(tokens[3])) {
                        if (Integer.parseInt(tokens[3]) >= 0 && Integer.parseInt(tokens[3]) <= SENIOR_UPPER_END) {
                            if (b == true) {
                                Student student = new TriState(new Profile(tokens[1], tokens[0], tokens[2]), m.name(), Integer.parseInt(tokens[3]), "NY");

                                if (!studentArray.contains(student)) {
                                    studentArray.add(student);
                                    textArea.appendText(student.getProfile() + " added to the roster " + "\n");
                                } else {
                                    textArea.appendText(student.getProfile() + " already exists in roster " + "\n");
                                }
                            }else{
                                Student student = new TriState(new Profile(tokens[1], tokens[0], tokens[2]), m.name(), Integer.parseInt(tokens[3]), "CT");

                                if (!studentArray.contains(student)) {
                                    studentArray.add(student);
                                    textArea.appendText(student.getProfile() + " added to the roster " + "\n");
                                } else {
                                    textArea.appendText(student.getProfile() + " already exists in roster " + "\n");
                                }
                            }
                            } else {
                                textArea.appendText("Credits completed invalid: cannot be negative!" + "\n");
                            }

                        }
                }
    }

    private String dateConverter(String a ){
        String[] date = a.split("-");
        return date[1] + "/" + date[2] + "/" + date[0];
    }

    @FXML
    void add(ActionEvent action){
        String[] info = new String[5];
        info[0] = fname.getText();
        info[1] = lname.getText();

        try{
            String a = rosterDOB.getValue().toString();
            String b = dateConverter(a);
            info[2] = b;
            info[3] = creditsCompleted.getText();
            info[4] = m.name();
            if(Resident.isSelected()){
                addResidentStudent(info);}
            else{
                if(NonResident.isSelected() && International.isSelected() && isStudyAbroad.isSelected()){
                    addInternationalStudentStudyAbroad(info);}
                else{
                    if(NonResident.isSelected() && International.isSelected()){
                        addInternationalStudent(info);}
                    else{
                        if(NonResident.isSelected() && (Ny.isSelected() || Ct.isSelected())){
                            if(Ny.isSelected()){
                                addTriStateStudent(info, true);
                            }else{
                                addTriStateStudent(info, false);
                            }
                        }
                        else{
                            if(NonResident.isSelected()){
                                addNonResidentStudent(info);
                            }
                        }
                    }

                }
            }
        }catch (NullPointerException e){
            textArea.appendText("DOB is empty" + "\n");
        }
    }
    private void remove(String[] array){
        if(errorsInRosterForRemove(array)){
            Profile profile = new Profile(array[1], array[0], array[2]);
            Student student = new Resident(profile, 0);
            if (!studentArray.contains(student)) {
                textArea.appendText(profile.toString() + " is not in the roster.." + "\n");
            } else {
                studentArray.remove(student);
                textArea.appendText(profile.toString() + " removed from the roster." + "\n");
            }
        }
    }

    private boolean errorsInRosterForRemove(String[] info){
        if(info[0].isBlank()){
            textArea.appendText("first name is empty" + "\n");
            return false;
        }
        if(info[1].isBlank()){
            textArea.appendText("last name is empty" + "\n");
            return false;
        }
        return true;
    }
    @FXML
    void remove(ActionEvent action){
        String[] info = new String[3];
        info[0] = fname.getText();
        info[1] = lname.getText();
        try{
            String a = rosterDOB.getValue().toString();
            info[2] = dateConverter(a);
            remove(info);
        }catch (NullPointerException e){
            textArea.appendText("DOB is empty" + "\n");
        }
    }






































}