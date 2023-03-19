package com.example.project3;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class TuitionManagerController {


    private Major m = com.example.project3.Major.EE;
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
    private TextField loadFile;
    @FXML
    private ToggleGroup Major, TriState, CheckResident;
    private Roster studentArray = new Roster();
    private Enrollment enrolledStudents = new Enrollment();

    private static final int SENIOR_UPPER_END = 120;


    @FXML
    protected void setResident() {
        this.International.setDisable(true);
        this.Tristate.setDisable(true);
        this.Ny.setDisable(true);
        this.Ct.setDisable(true);
        this.isStudyAbroad.setDisable(true);
    }
    @FXML
    protected void setNonResident(){
        this.International.setDisable(false);
        this.Tristate.setDisable(false);
        this.Ny.setDisable(false);
        this.Ct.setDisable(false);
        this.isStudyAbroad.setDisable(false);
    }
    @FXML
    protected void setTriState(){
        this.isStudyAbroad.setDisable(true);
        this.Ny.setDisable(false);
        this.Ct.setDisable(false);
    }

    @FXML
    protected void setInternational(){
        this.Ny.setDisable(true);
        this.Ct.setDisable(true);
        this.isStudyAbroad.setDisable(false);
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
        try{
            rosterDOB.getValue().toString();
        }catch (NullPointerException e){
            if(info[1].isBlank() && info[0].isBlank() && info[3].isBlank()){
                textArea.appendText("first name, last name, credits text field, and DOB is empty" + "\n");
                return false;}
            if(info[0].isBlank() && info[3].isBlank()){
                textArea.appendText("first name, credits text field, and DOB is empty" + "\n");
                return false;}
            if(info[1].isBlank() && info[3].isBlank()){
                textArea.appendText("last name, credits text field, and DOB is empty" + "\n");
                return false;}
            if(info[3].isBlank()) {
                textArea.appendText("credits text field and DOB is empty" + "\n");
                return false;}
            if(info[0].isBlank()) {
                textArea.appendText("first name and DOB is empty" + "\n");
                return false;}
            if(info[1].isBlank()) {
                textArea.appendText("last name and DOB is empty" + "\n");
                return false;}
            textArea.appendText("DOB is empty" + "\n");
            return false;}
        if(info[0].isBlank() && info[1].isBlank() && info[3].isBlank()){
            textArea.appendText("first name, last name, and credits text field is empty." + "\n");
            return false;}
        if(info[0].isBlank() && info[3].isBlank()){
            textArea.appendText("first name and credits text field is empty" + "\n");return false;}
        if(info[1].isBlank() && info[3].isBlank()){
            textArea.appendText("last name and credits text field is empty" + "\n");return false;}
        if(info[0].isBlank() && info[1].isBlank()){textArea.appendText("first name and last name is empty" + "\n");return false;}
        if(info[0].isBlank()){textArea.appendText("first name is empty" + "\n");return false;}
        if(info[1].isBlank()){textArea.appendText("last name is empty" + "\n");return false;}
        if(info[3].isBlank()){textArea.appendText(" credits text field is empty" + "\n");return false;}
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
    private void addInternationalStudentStudyAbroad(String[] tokens){
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
    private void addInternationalStudent(String[] tokens){
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

    private void addNonResidentStudent(String[] tokens){
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
    private void addTriStateStudent(String[] tokens, boolean b){
                if (areCreditsValid(tokens[3])) {
                        if (Integer.parseInt(tokens[3]) >= 0 && Integer.parseInt(tokens[3]) <= SENIOR_UPPER_END) {
                            if (b == true) {
                                Student student = new TriState(new Profile(tokens[1], tokens[0], tokens[2]), m.name(), Integer.parseInt(tokens[3]), "NY");

                                if (!studentArray.contains(student)) {
                                    studentArray.add(student);
                                    textArea.appendText(student.getProfile() + " (TriState NY) student added to the roster " + "\n");
                                } else {
                                    textArea.appendText(student.getProfile() + " already exists in roster " + "\n");
                                }
                            }else{
                                Student student = new TriState(new Profile(tokens[1], tokens[0], tokens[2]), m.name(), Integer.parseInt(tokens[3]), "CT");

                                if (!studentArray.contains(student)) {
                                    studentArray.add(student);
                                    textArea.appendText(student.getProfile() + " (TriState CT) student added to the roster " + "\n");
                                } else {
                                    textArea.appendText(student.getProfile() + " already exists in roster " + "\n");
                                }
                            }
                            } else {
                                textArea.appendText("Credits completed invalid: cannot be negative!" + "\n");
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
            info[3] = creditsCompleted.getText();
            info[4] = m.name();
            if(errorsInRoster(info)) {
                info[2] = dateConverter(rosterDOB.getValue().toString());
                if (Resident.isSelected()) {
                    addResidentStudent(info);
                } else {
                    if (NonResident.isSelected() && International.isSelected() && isStudyAbroad.isSelected()) {
                        addInternationalStudentStudyAbroad(info);
                    } else {
                        if (NonResident.isSelected() && International.isSelected()) {
                            addInternationalStudent(info);
                        } else {
                            if (NonResident.isSelected() && (Ny.isSelected() || Ct.isSelected())) {
                                if (Ny.isSelected()) {
                                    addTriStateStudent(info, true);
                                } else {
                                    addTriStateStudent(info, false);
                                }
                            } else {
                                if (NonResident.isSelected()) {
                                    addNonResidentStudent(info);
                                }
                            }
                        }

                    }
                }
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
        try{
            rosterDOB.getValue().toString();
        }catch (NullPointerException e){
            if(info[1].isBlank() && info[0].isBlank()){
                textArea.appendText("first name, last name, and DOB is empty" + "\n");
                return false;
            }
            if(info[0].isBlank()){
                textArea.appendText("first name and DOB is empty" + "\n");
                return false;
            }
            if(info[1].isBlank()){
                textArea.appendText("last name and DOB is empty" + "\n");
                return false;
            }
            textArea.appendText("DOB is empty" + "\n");
            return false;
        }
        if(info[0].isBlank() && info[1].isBlank()){
            textArea.appendText("first name and last name is empty.");
            return false;
        }
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
        if(errorsInRosterForRemove(info)) {
            String a = rosterDOB.getValue().toString();
            info[2] = dateConverter(a);
            remove(info);
        }
    }

    private void changeMajor(String[] array){
        if(errorsInRosterForRemove(array)) {
            Profile profile = new Profile(array[1], array[0], array[2]);
            Student student = new Resident(profile, (com.example.project3.Major) null, 0);
            Major majorEnum;
            if (!studentArray.contains(student)) {
                textArea.appendText(profile.toString() + " is not in the roster." + "\n");
            }
            if (studentArray.contains(student)) {
                majorEnum = m;
                student.setMajor(majorEnum);
                studentArray.changedMajor(student);
                textArea.appendText(profile.toString() + " major changed to " + m.name() + "\n");
            }
        }
    }
    @FXML
    void changeMajor(ActionEvent event){
        String[] info = new String[3];
        info[0] = fname.getText();
        info[1] = lname.getText();
        try{
            String a = rosterDOB.getValue().toString();
            info[2] = dateConverter(a);
            changeMajor(info);
        }catch (NullPointerException e){
            textArea.appendText("DOB is empty" + "\n");
        }
    }
    private boolean errorsInRosterForEnroll(String[] info){
        try{
            eDOB.getValue().toString();
        }catch (NullPointerException e){
            if(info[1].isBlank() && info[0].isBlank() && info[3].isBlank()){
                textArea.appendText("first name, last name, credits text field, and DOB is empty" + "\n");
                return false;}
            if(info[0].isBlank() && info[3].isBlank()){
                textArea.appendText("first name, credits text field, and DOB is empty" + "\n");
                return false;}
            if(info[1].isBlank() && info[3].isBlank()){
                textArea.appendText("last name, credits text field, and DOB is empty" + "\n");
                return false;}
            if(info[3].isBlank()) {
                textArea.appendText("credits text field and DOB is empty" + "\n");
                return false;}
            if(info[0].isBlank()) {
                textArea.appendText("first name and DOB is empty" + "\n");
                return false;}
            if(info[1].isBlank()) {
                textArea.appendText("last name and DOB is empty" + "\n");
                return false;}
            textArea.appendText("DOB is empty" + "\n");
            return false;}
        if(info[0].isBlank() && info[1].isBlank() && info[3].isBlank()){
            textArea.appendText("first name, last name, and credits text field is empty." + "\n");
            return false;}
        if(info[0].isBlank() && info[3].isBlank()){
            textArea.appendText("first name and credits text field is empty" + "\n");return false;}
        if(info[1].isBlank() && info[3].isBlank()){
            textArea.appendText("last name and credits text field is empty" + "\n");return false;}
        if(info[0].isBlank() && info[1].isBlank()){textArea.appendText("first name and last name is empty" + "\n");return false;}
        if(info[0].isBlank()){textArea.appendText("first name is empty" + "\n");return false;}
        if(info[1].isBlank()){textArea.appendText("last name is empty" + "\n");return false;}
        if(info[3].isBlank()){textArea.appendText(" credits text field is empty" + "\n");return false;}
        return true;
    }

    private boolean errorsInRosterForDrop(String[] info){
        try{
            eDOB.getValue().toString();
        }catch (NullPointerException e){
            if(info[1].isBlank() && info[0].isBlank()){
                textArea.appendText("first name, last name, and DOB is empty" + "\n");
                return false;
            }
            if(info[0].isBlank()){
                textArea.appendText("first name and DOB is empty" + "\n");
                return false;
            }
            if(info[1].isBlank()){
                textArea.appendText("last name and DOB is empty" + "\n");
                return false;
            }
            textArea.appendText("DOB is empty" + "\n");
            return false;
        }
        if(info[0].isBlank() && info[1].isBlank()){
            textArea.appendText("first name and last name is empty.");
            return false;
        }
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

    private void enrollStudent(String[] array){
        if(errorsInRosterForEnroll(array)){
            if(areCreditsValid(array[3])){
                Profile profile = new Profile(array[1], array[0], array[2]);
                Student student = new Resident(profile, (com.example.project3.Major) null, 0);
                if(studentArray.contains(student)){
                    if(studentArray.returnStudent(student) instanceof Resident){
                        if(studentArray.returnStudent(student).isValid(Integer.parseInt(array[3]))){
                            EnrollStudent Nstudent = new EnrollStudent(profile, Integer.parseInt(array[3]));
                            enrolledStudents.add(Nstudent);
                            textArea.appendText(array[0] + " " + array[1] + " enrolled " + array[3] + " credits." + "\n");
                        }else{
                            textArea.appendText("(Resident) " + array[3] + " : invalid credit hours" + "\n");}
                    }else{
                        if(studentArray.returnStudent(student) instanceof International){
                            if(studentArray.returnStudent(student).isValid(Integer.parseInt(array[3]))){
                                EnrollStudent Nstudent = new EnrollStudent(profile, Integer.parseInt(array[3]));
                                enrolledStudents.add(Nstudent);
                                textArea.appendText(array[0] + " " + array[1] + " enrolled " + array[3] + " credits." + "\n");
                            }else{
                                textArea.appendText("(International ) " + array[3] + " : invalid credit hours" + "\n");}
                        }else{
                            if(studentArray.returnStudent(student) instanceof TriState){
                                if(studentArray.returnStudent(student).isValid(Integer.parseInt(array[3]))){
                                    EnrollStudent Nstudent = new EnrollStudent(profile, Integer.parseInt(array[3]));
                                    enrolledStudents.add(Nstudent);
                                    textArea.appendText(array[0] + " " + array[1] + " enrolled " + array[3] + " credits." + "\n");
                                }else{
                                    textArea.appendText("(TriState) " + array[3] + " : invalid credit hours" + "\n");}
                            }else{
                                if(studentArray.returnStudent(student) instanceof NonResident){
                                    if(studentArray.returnStudent(student).isValid(Integer.parseInt(array[3]))){
                                        EnrollStudent Nstudent = new EnrollStudent(profile, Integer.parseInt(array[3]));
                                        enrolledStudents.add(Nstudent);
                                        textArea.appendText(array[0] + " " + array[1] + " enrolled " + array[3] + " credits." + "\n");
                                    }else{
                                        textArea.appendText("(Non-Resident) " + array[3] + " : invalid credit hours" + "\n");
                                    }}}}}
                }else{
                    textArea.appendText("student is not in roster" + "\n");}}}}

    @FXML
    void enroll(ActionEvent event){
        String[] info = new String[4];
        info[0] = efname.getText();
        info[1] = elname.getText();
        info[3] = creditsEnrolled.getText();
        if(errorsInRosterForEnroll(info)){
            String a = eDOB.getValue().toString();
            String b = dateConverter(a);
            info[2] = b;
            enrollStudent(info);
        }
    }

    private void dropStudent(String[] array){
        if(errorsInRosterForDrop(array)){
        Profile profile = new Profile(array[1], array[0], array[2]);
        EnrollStudent student = new EnrollStudent(profile, 0);
        if(enrolledStudents.contains(student)){
            enrolledStudents.remove(student);
            textArea.appendText( profile.toString() + " dropped" + "\n");
        }else{
            textArea.appendText(profile.toString()+ " is not enrolled" + "\n");
        }}
    }

    @FXML
    void drop(ActionEvent event){
        String[] info = new String[3];
        info[0] = efname.getText();
        info[1] = elname.getText();
        if(errorsInRosterForDrop(info)){
            String a = eDOB.getValue().toString();
            String b = dateConverter(a);
            info[2] = b;
            dropStudent(info);
        }
    }
    private boolean checkScholarship(String s){
        try{
            if(Integer.parseInt(s)>=0 && Integer.parseInt(s)<=10000){
                return true;
            }
            if(Integer.parseInt(s)>10000 || Integer.parseInt(s)<0){
                textArea.appendText(s + ": invalid amount" + "\n");
                return false;
            }
        }
        catch (Exception e){
            textArea.appendText("Amount is not an integer" + "\n");
            return false;
        }
        return false;
    }
    private boolean errorsInRosterForScholarship(String[] info){
        try{
            sDOB.getValue().toString();
        }catch (NullPointerException e){
            if(info[1].isBlank() && info[0].isBlank() && info[3].isBlank()){
                textArea.appendText("first name, last name, amount text field, and DOB is empty" + "\n");
                return false;}
            if(info[0].isBlank() && info[3].isBlank()){
                textArea.appendText("first name, amount text field, and DOB is empty" + "\n");
                return false;}
            if(info[1].isBlank() && info[3].isBlank()){
                textArea.appendText("last name, amount text field, and DOB is empty" + "\n");
                return false;}
            if(info[3].isBlank()) {
                textArea.appendText("amount text field and DOB is empty" + "\n");
                return false;}
            if(info[0].isBlank()) {
                textArea.appendText("first name and DOB is empty" + "\n");
                return false;}
            if(info[1].isBlank()) {
                textArea.appendText("last name and DOB is empty" + "\n");
                return false;}
            textArea.appendText("DOB is empty" + "\n");
            return false;}
        if(info[0].isBlank() && info[1].isBlank() && info[3].isBlank()){
            textArea.appendText("first name, last name, and amount text field is empty." + "\n");
            return false;}
        if(info[0].isBlank() && info[3].isBlank()){
            textArea.appendText("first name and amount text field is empty" + "\n");return false;}
        if(info[1].isBlank() && info[3].isBlank()){
            textArea.appendText("last name and amount text field is empty" + "\n");return false;}
        if(info[0].isBlank() && info[1].isBlank()){textArea.appendText("first name and last name is empty" + "\n");return false;}
        if(info[0].isBlank()){textArea.appendText("first name is empty" + "\n");return false;}
        if(info[1].isBlank()){textArea.appendText("last name is empty" + "\n");return false;}
        if(info[3].isBlank()){textArea.appendText(" amount text field is empty" + "\n");return false;}
        return true;
    }

    private void awardScholarship(String[] array) {
            Profile profile = new Profile(array[1], array[0], array[2]);
            Student student = new Resident(profile, (com.example.project3.Major) null, 0);
            if (checkScholarship(amount.getText())) {
                if (studentArray.contains(student)) {
                    if (studentArray.returnStudent(student) instanceof Resident) {
                        EnrollStudent Nstudent = (new EnrollStudent(profile, 0));
                        if (enrolledStudents.contains(Nstudent)) {
                            ((Resident) studentArray.returnStudent(student)).setScholarship(Integer.parseInt(amount.getText()));
                            textArea.appendText(profile.toString() + ": scholarship amount updated" + "\n");
                        } else {
                            textArea.appendText("student is not enrolled" + "\n");
                        }
                    } else {
                        if (studentArray.returnStudent(student) instanceof International) {
                            textArea.appendText(profile.toString() + " (International) is not eligible for scholarship " + "\n");
                        } else {
                            if (studentArray.returnStudent(student) instanceof TriState) {
                                textArea.appendText(profile.toString() + " (TriState) is not eligible for scholarship " + "\n");
                            } else {
                                if (studentArray.returnStudent(student) instanceof NonResident) {
                                    textArea.appendText(profile.toString() + " (Non-Resident) is not eligible for scholarship " + "\n");
                                }
                            }
                        }
                    }
                } else {
                    textArea.appendText(profile.toString() + " is not in the roster" + "\n");
                }
            }
    }

    @FXML
    void updateScholarship(ActionEvent event){
        String[] info = new String[4];
        info[0] = sfname.getText();
        info[1] = slname.getText();
        info[3] = amount.getText();
        if(errorsInRosterForScholarship(info)){
            info[2] = dateConverter(sDOB.getValue().toString());
            awardScholarship(info);
        }
    }

    private void helperForPrint() {
        if (this.studentArray.getSize() > 0) {
            textArea.appendText("* Student roster sorted by first name, last name, DOB **" + "\n");
            for (int i = 0; i < studentArray.getSize(); i++) {
                textArea.appendText(studentArray.print(i).toString() + "\n");
            }
            textArea.appendText("* end of roster ** " + "\n");
        } else {
            textArea.appendText("Student roster is empty!" + "\n");
        }
    }
    @FXML
    void print(ActionEvent event){
        textArea.clear();
        helperForPrint();
    }

    private void helperForPrintByStanding() {
        if (this.studentArray.getSize() > 0) {
            textArea.appendText("* Student roster sorted by Standing **" + "\n");
            Student[] a = studentArray.printByStanding();
            for(int i = 0;  i< a.length; i++){
                textArea.appendText(a[i].toString() + "\n");
            }
            textArea.appendText("*end of roster**" + "\n");

        } else {
            textArea.appendText("Student roster is empty" + "\n");
        }
    }
    @FXML
    void printByStanding(ActionEvent event){
        textArea.clear();
        helperForPrintByStanding();
    }

    private void helpForPrintBySchoolMajor() {
        if (this.studentArray.getSize() > 0) {
            textArea.appendText("* student roster sorted by school, major" + "\n");
            for(int i = 0; i < studentArray.getSize(); i++){
                textArea.appendText(studentArray.printBySchoolMajor(i).toString() + "\n");
            }
            textArea.appendText("*end of roster**" + "\n");
        } else {
            textArea.appendText("Student roster is empty!" + "\n");
        }
    }
    @FXML
    void printBySchoolMajor(ActionEvent event){
        textArea.clear();
        helpForPrintBySchoolMajor();
    }

    private void displayTuition(){
        if(enrolledStudents.getSize()!=0){
            DecimalFormat df = new DecimalFormat("0.00");
            textArea.appendText("** Tuition due*" + "\n");
                for(int i = 0; i < enrolledStudents.getSize(); i++){
                    Profile profile = enrolledStudents.returnProfile(i);
                    Student student = new Resident(profile, (com.example.project3.Major) null, 0);
                    if (studentArray.returnStudent(student) instanceof Resident) {
                        textArea.appendText(student.getProfile() + " (Resident) " + "enrolled " + enrolledStudents.getCreditsEnrolled(i)+ " credits: tuition due: "+df.format(studentArray.returnStudent(student).tuitionDue(enrolledStudents.getCreditsEnrolled(i)))  + "\n");
                    } else if (studentArray.returnStudent(student) instanceof International) {
                        textArea.appendText(student.getProfile() + " (International) enrolled " + enrolledStudents.getCreditsEnrolled(i)+ " credits: tuition due: " +df.format(studentArray.returnStudent(student).tuitionDue(enrolledStudents.getCreditsEnrolled(i))) + "\n");
                    } else if (studentArray.returnStudent(student) instanceof TriState) {
                        textArea.appendText(student.getProfile() + " (TriState " + ((TriState) studentArray.returnStudent(student)).getState()
                                + ") enrolled "+ enrolledStudents.getCreditsEnrolled(i)+ " credits: tuition due: "+df.format(studentArray.returnStudent(student).tuitionDue(enrolledStudents.getCreditsEnrolled(i))) + "\n");
                    } else if (studentArray.returnStudent(student) instanceof NonResident) {
                        textArea.appendText(student.getProfile() + " (Non-Resident) enrolled "+ enrolledStudents.getCreditsEnrolled(i)+ " credits: tuition due: " + df.format(studentArray.returnStudent(student).tuitionDue(enrolledStudents.getCreditsEnrolled(i))) + "\n");
                    }
                }
            textArea.appendText("* end of tuition due *" + "\n");
        }else{
            textArea.appendText("Student roster is empty!" + "\n");
        }}

    @FXML
    void tuitionDue(ActionEvent event){
        textArea.clear();
        displayTuition();
    }

    private void printEnroll(){
        if(enrolledStudents.getSize()==0){
            textArea.appendText("Enrollment is empty!" + "\n");
        }else{
            textArea.appendText("** Enrollment * " + "\n");

            for(int i = 0; i < enrolledStudents.getSize(); i++){
                textArea.appendText(enrolledStudents.print(i).toString() + "\n");
            }

            textArea.appendText("* end of Enrollment **" + "\n");
        }
    }
    @FXML
    void enrollmentList(ActionEvent event){
        textArea.clear();
        printEnroll();
    }

    private int updateCredits(){
        int count = 0;
        for(int i = 0; i < enrolledStudents.getSize(); i++) {
            Profile profile = enrolledStudents.returnProfile(i);
            Student student = new Resident(profile, (com.example.project3.Major) null, 0);
            int a = enrolledStudents.getCreditsEnrolled(i) + studentArray.returnStudent(student).getCreditCompleted();
            studentArray.returnStudent(student).setCreditCompleted(a);
            if(a>=120){
                count++;
            }
        }
        return count;
    }

    private void helpForSemesterEnd() {
        textArea.appendText("Credit completed has been updated." + "\n");
        textArea.appendText("** list of students eligible for graduation **" + "\n");
        for(int i = 0; i < enrolledStudents.getSize(); i++){
            Profile profile = enrolledStudents.returnProfile(i);
            Student student = new Resident(profile, (com.example.project3.Major) null, 0);
            /*int a = enrolledStudents.getCreditsEnrolled(i) + studentArray.returnStudent(student).getCreditCompleted();
            studentArray.returnStudent(student).setCreditCompleted(a);*/
            if(studentArray.returnStudent(student).getCreditCompleted()>=120){
                if (studentArray.returnStudent(student) instanceof Resident) {
                    textArea.appendText(student.getProfile() + " credits completed: " +studentArray.returnStudent(student).getCreditCompleted()+ " (Senior) (Resident)" + "\n" );
                } else if (studentArray.returnStudent(student) instanceof International) {
                    textArea.appendText(student.getProfile() + " credits completed: " +studentArray.returnStudent(student).getCreditCompleted()+ " (Senior) (International) (Non-Resident)" + "\n" );
                } else if (studentArray.returnStudent(student) instanceof TriState) {
                    textArea.appendText(student.getProfile() + " credits completed: " +studentArray.returnStudent(student).getCreditCompleted()+ " (Senior) (Non-Resident) (TriState " + ((TriState) studentArray.returnStudent(student)).getState() + ")"  + "\n");
                } else if (studentArray.returnStudent(student) instanceof NonResident) {
                    textArea.appendText(student.getProfile() + " credits completed: " +studentArray.returnStudent(student).getCreditCompleted()+ " (Senior) (Non-Resident)" + "\n");
                }
            }
        }textArea.appendText("** end of list *" + "\n");
    }
    @FXML
    void SemesterEnd(ActionEvent event){
        textArea.clear();
        int a = updateCredits();
        if(a!=0){
            helpForSemesterEnd();
        }else{
            textArea.appendText("no students eligible for graduation!" + "\n");
        }

    }

    @FXML
    void printRBS(ActionEvent event){
        textArea.clear();
        String s = "RBS";
        int count =0;
        for(int i =0; i < studentArray.getSize(); i++){
            if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                count++;
            }}
        if(count==0){
            textArea.appendText("no students in RBS" + "\n");
        }else{
        textArea.appendText("* students in " + s + "**" + "\n");
        for(int i =0; i < studentArray.getSize(); i++){
            if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                textArea.appendText(studentArray.printBySchool(i).toString() + "\n");
            }
        }
        textArea.appendText("*end of roster**" + "\n");}
    }
    @FXML
    void printSAS(ActionEvent event){
        textArea.clear();
        String s = "SAS";
        int count =0;
        for(int i =0; i < studentArray.getSize(); i++){
            if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                count++;
            }}
        if(count==0){
            textArea.appendText("no students in SAS" + "\n");
        }else{
            textArea.appendText("* students in " + s + "**" + "\n");
            for(int i =0; i < studentArray.getSize(); i++){
                if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                    textArea.appendText(studentArray.printBySchool(i).toString() + "\n");
                }
            }
            textArea.appendText("*end of roster**" + "\n");}
    }

    @FXML
    void printSOE(ActionEvent event){
        textArea.clear();
        String s = "SOE";
        int count =0;
        for(int i =0; i < studentArray.getSize(); i++){
            if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                count++;
            }}
        if(count==0){
            textArea.appendText("no students in SOE" + "\n");
        }else{
            textArea.appendText("* students in " + s + "**" + "\n");
            for(int i =0; i < studentArray.getSize(); i++){
                if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                    textArea.appendText(studentArray.printBySchool(i).toString() + "\n");
                }
            }
            textArea.appendText("*end of roster**" + "\n");}
    }
    @FXML
    void printSCI(ActionEvent event){
        textArea.clear();
        String s = "SC&I";
        int count =0;
        for(int i =0; i < studentArray.getSize(); i++){
            if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                count++;
            }}
        if(count==0){
            textArea.appendText("no students in SC&I" + "\n");
        }else{
            textArea.appendText("* students in " + s + "**" + "\n");
            for(int i =0; i < studentArray.getSize(); i++){
                if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                    textArea.appendText(studentArray.printBySchool(i).toString() + "\n");
                }
            }
            textArea.appendText("*end of roster**" + "\n");}
    }

    private void addResidentStudentFromRoster(String[] tokens) {
        if (tokens.length == 6) {
            Date date = new Date(tokens[3]);
            if (NewIsValid(date)) {
                if (com.example.project3.Major.isMajorValid(tokens[4].toUpperCase())) {
                    if (areCreditsValid(tokens[5])) {
                        if (Integer.parseInt(tokens[5]) >= 0 && Integer.parseInt(tokens[5]) <= SENIOR_UPPER_END) {
                            Student student = new Resident(new Profile(tokens[2], tokens[1], tokens[3]), com.example.project3.Major.valueOf(tokens[4].toUpperCase()), Integer.parseInt(tokens[5]));
                            if (!studentArray.contains(student)) {
                                studentArray.add(student);
                            }
                        }
                    }
                }
            }
        }
    }
    private void addInternationalStudentFromRoster(String[] tokens){
        if(tokens.length>=6) {
            Date date = new Date(tokens[3]);
            if (date.isValid()) {
                if (com.example.project3.Major.isMajorValid(tokens[4].toUpperCase())) {
                    if (areCreditsValid(tokens[5])) {
                        if (Integer.parseInt(tokens[5]) >= 0 && Integer.parseInt(tokens[5]) <= SENIOR_UPPER_END) {
                            if(tokens.length==7){
                                Student student = new International(new Profile(tokens[2], tokens[1], tokens[3]), com.example.project3.Major.valueOf(tokens[4].toUpperCase()), Integer.parseInt(tokens[5]), Boolean.parseBoolean(tokens[6]));
                                if (!studentArray.contains(student)) {
                                    studentArray.add(student);
                                }
                            }else {
                                Student student = new International(new Profile(tokens[2], tokens[1], tokens[3]), com.example.project3.Major.valueOf(tokens[4].toUpperCase()), Integer.parseInt(tokens[5]));
                                if (!studentArray.contains(student)) {
                                    studentArray.add(student);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void addNonResidentStudentsFromRoster(String[] tokens){
        if(tokens.length==6) {
            Date date = new Date(tokens[3]);
            if (date.isValid()) {
                if (com.example.project3.Major.isMajorValid(tokens[4].toUpperCase())) {
                    if (areCreditsValid(tokens[5])) {
                        if (Integer.parseInt(tokens[5]) >= 0 && Integer.parseInt(tokens[5]) <= SENIOR_UPPER_END) {
                            Student student = new NonResident(new Profile(tokens[2], tokens[1], tokens[3]), com.example.project3.Major.valueOf(tokens[4].toUpperCase()), Integer.parseInt(tokens[5]));
                            if (!studentArray.contains(student)) {
                                studentArray.add(student);
                            }
                        }
                    }
                }
            }
        }
    }

    private void addTriStateStudentFromRoster(String[] tokens){
        if(tokens.length==7) {
            Date date = new Date(tokens[3]);
            if (date.isValid()) {
                if (com.example.project3.Major.isMajorValid(tokens[4].toUpperCase())) {
                    if (areCreditsValid(tokens[5])) {
                        if(tokens[6].equalsIgnoreCase("NY") || tokens[6].equalsIgnoreCase("CT")) {
                            if (Integer.parseInt(tokens[5]) >= 0 && Integer.parseInt(tokens[5]) <= SENIOR_UPPER_END) {
                                Student student = new TriState(new Profile(tokens[2], tokens[1], tokens[3]), com.example.project3.Major.valueOf(tokens[4].toUpperCase()), Integer.parseInt(tokens[5]), tokens[6]);
                                if (!studentArray.contains(student)) {
                                    studentArray.add(student);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @FXML
    void loadFromFile(ActionEvent event){
        String a = loadFile.getText();
        try{
            File file = new File(a);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                String status = tokens[0];
                switch (status) {
                    case "R":
                        addResidentStudentFromRoster(tokens);
                        break;
                    case "N":
                        addNonResidentStudentsFromRoster(tokens);
                        break;
                    case "T":
                        addTriStateStudentFromRoster(tokens);
                        break;
                    case "I":
                        addInternationalStudentFromRoster(tokens);
                        break;
                    default:
                        textArea.appendText("Invalid student status in file." + "\n");
                        break;
                }
            }
            scanner.close();
            textArea.appendText("Student loaded to the roster." + "\n");
        }catch(FileNotFoundException e){
            textArea.appendText("File not Found" + "\n");
        }
    }

}