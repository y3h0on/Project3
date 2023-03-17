package com.example.project3;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.text.DecimalFormat;


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
            if(errorsInRoster(info)) {
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


    private void enrollStudent(String[] array){
        if(errorsInRoster(array)){
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
        try {
            String a = eDOB.getValue().toString();
            String b = dateConverter(a);
            info[2] = b;
            info[3] = creditsEnrolled.getText();
            enrollStudent(info);
        }catch (NullPointerException e){
            textArea.appendText("DOB is empty" + "\n");
        }
    }

    private void dropStudent(String[] array){
        if(errorsInRosterForRemove(array)){
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
        try {
            String a = eDOB.getValue().toString();
            String b = dateConverter(a);
            info[2] = b;
            dropStudent(info);
        }catch (NullPointerException e){
            textArea.appendText("DOB is empty" + "\n");
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

    private void awardScholarship(String[] array) {
        if(errorsInRosterForRemove(array)) {
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
    }

    @FXML
    void updateScholarship(ActionEvent event){
        String[] info = new String[3];
        info[0] = sfname.getText();
        info[1] = slname.getText();
        try{
            String a = sDOB.getValue().toString();
            info[2] = dateConverter(a);
            awardScholarship(info);
        }catch (NullPointerException e){
            textArea.appendText("DOB is empty" + "\n");
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
        if(studentArray.getSize()!=0){
            DecimalFormat df = new DecimalFormat("0.00");
            textArea.appendText("** Tuition due*" + "\n");
            if(enrolledStudents.getSize()!=0){
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
                }}else{
                textArea.appendText("Roster is empty" + "\n");
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

    private void helpForSemesterEnd() {
        textArea.appendText("Credit completed has been updated." + "\n");
        textArea.appendText("** list of students eligible for graduation **" + "\n");
        for(int i = 0; i < enrolledStudents.getSize(); i++){
            Profile profile = enrolledStudents.returnProfile(i);
            Student student = new Resident(profile, (com.example.project3.Major) null, 0);
            int a = enrolledStudents.getCreditsEnrolled(i) + studentArray.returnStudent(student).getCreditCompleted();
            studentArray.returnStudent(student).setCreditCompleted(a);
            if(a>=120){
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
        helpForSemesterEnd();
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
            textArea.appendText("no students in RBS");
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
            textArea.appendText("no students in SAS");
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
            textArea.appendText("no students in SOE");
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
            textArea.appendText("no students in SC&I");
        }else{
            textArea.appendText("* students in " + s + "**" + "\n");
            for(int i =0; i < studentArray.getSize(); i++){
                if(studentArray.printBySchool(i).getMajor().getSchool().equalsIgnoreCase(s)){
                    textArea.appendText(studentArray.printBySchool(i).toString() + "\n");
                }
            }
            textArea.appendText("*end of roster**" + "\n");}
    }



































}