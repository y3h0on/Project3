package com.example.project3;

/**
 * @author Apurva Desai, Yehun Kim
 * Resident extends class, extends the Students class and makes an object of a resident student
 */
public class Resident extends Student{


    private int scholarship;
    private static final int PER_CREDIT_FEE = 404;
    private static final int TUITION = 12536;
    private static final int UNIVERSITY_FEE = 3268;
    private static final double PART_TIME_UNIVERSITY_FEE = (UNIVERSITY_FEE*0.8);
    private static final int FULL_TIME_STUDENT_CREDITS = 12;
    private static final int REGULAR_TUITION_CREDITS = 16;


    /**
     * Parameter the constructor, creates the new Resident with calling the super method
     * @param profile lname, fname, dob, major and credits of resident student
     * @param major major of the resident student
     * @param creditCompleted of the resident student
     */
    public Resident(Profile profile, Major major, int creditCompleted){
        super(profile, major, creditCompleted);
        this.scholarship=0;
    }

    /**
     * Parameter the constructor,
     * @param profile lname, fname, dob of resident
     * @param scholarship and scholarship of resident
     */
    public Resident(Profile profile, int scholarship){
        super(profile);
        this.scholarship=scholarship;
    }

    /**
     * setter method that save the value of scholarship
     * @param scholarship amount
     */
    public void setScholarship(int scholarship) {
        this.scholarship = scholarship;
    }

    /**
     * check if scholarship is accplicable and calculates the amount of tuition due for R student
     * @param creditsEnrolled # of credits for Resident student has enrolled.
     * @return tuition after calculated
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        if(creditsEnrolled<FULL_TIME_STUDENT_CREDITS){
            scholarship=0;
            double tuition =0;
            if(isValid(creditsEnrolled) && isResident()){
                if(creditsEnrolled>=FULL_TIME_STUDENT_CREDITS &&creditsEnrolled<=REGULAR_TUITION_CREDITS){
                    tuition = TUITION + UNIVERSITY_FEE - scholarship;
                }
                if(creditsEnrolled<FULL_TIME_STUDENT_CREDITS){
                    tuition = ((creditsEnrolled*PER_CREDIT_FEE) + PART_TIME_UNIVERSITY_FEE - scholarship);
                }
                if(creditsEnrolled>REGULAR_TUITION_CREDITS){
                    int extraCredits = creditsEnrolled-REGULAR_TUITION_CREDITS;
                    tuition = (TUITION+ UNIVERSITY_FEE+ (PER_CREDIT_FEE*extraCredits) - scholarship);
                }
                return tuition;
            }
        }else {
            double tuition = 0;
            if (isValid(creditsEnrolled) && isResident()) {
                if (creditsEnrolled >= FULL_TIME_STUDENT_CREDITS && creditsEnrolled <= REGULAR_TUITION_CREDITS) {
                    tuition = TUITION + UNIVERSITY_FEE - scholarship;
                }
                if (creditsEnrolled < FULL_TIME_STUDENT_CREDITS) {
                    tuition = ((creditsEnrolled * PER_CREDIT_FEE) + PART_TIME_UNIVERSITY_FEE - scholarship);
                }
                if (creditsEnrolled > REGULAR_TUITION_CREDITS) {
                    int extraCredits = creditsEnrolled - REGULAR_TUITION_CREDITS;
                    tuition = (TUITION + UNIVERSITY_FEE + (PER_CREDIT_FEE * extraCredits) - scholarship);
                }
                return tuition;
            }

        }
        return 0;
    }
    /**
     * will print a resident student object
     * @return true
     */
    public String toString(){
        return super.toString() + "(Resident) ";
    }

    /**
     * check the resident student if they are a resident
     * @return true, if they are resident student
     */
    @Override
    public boolean isResident() {
        return true;
    }
}
