package com.example.project3;

/**
 * International class, extends the NonResident class
 * @author Yehun Kim, Apurva Desai
 *
 */

public class International extends NonResident{

    private boolean isStudyAbroad;
    private static final int MAX_CREDITS_STUDY_ABROAD = 12;
    private static final int MIN_CREDITS_STUDY_ABROAD = 3;
    private static final int MAX_CREDIT_FULLTIME = 16;
    private static final int TUITION_INTERNATIONAL = 29737;
    private static final int HEALTH_INSURANCE_FEE = 2650;
    private static final int UNIVERSITY_FEE = 3268;
    private static final int PER_CREDIT_FEE = 966;

    /**
     * Parameter constructor: creates a new International std by super method
     * @param profile fname, lname, and dob
     * @param major major of International Student
     * @param creditsCompleted credit of International student
     */
    public International(Profile profile, Major major, int creditsCompleted){
        super(profile, major, creditsCompleted);
        this.isStudyAbroad = false;
    }

    /**
     *
     * Parameter constructor: creates a new International std by super method
     * @param profile fname, lname, and dob
     * @param major major of International Student
     * @param creditsCompleted credit of International student
     * @param isStudyAbroad to check if the student is study abroad or not
     */
    public International(Profile profile, Major major, int creditsCompleted, boolean isStudyAbroad){
        super(profile, major, creditsCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }

    /**
     * Print a International student object
     * @return the string representation of the International std
     */
    public String toString(){
        if(isStudyAbroad){
            return super.toString() + ("(International: Study Abroad)");
        }else{
            return super.toString() + "(International) ";
        }
    }

    /**
     * check the student's credit valid
     * @param creditsEnrolled of international student credit
     * @return true if students take allowed credits amount, false otherwise
     */
    @Override
    public boolean isValid(int creditsEnrolled) {
        if(isStudyAbroad){
            if(creditsEnrolled>=MIN_CREDITS_STUDY_ABROAD && creditsEnrolled<=MAX_CREDITS_STUDY_ABROAD){
                return true;
            }
        }
        else{
            if(creditsEnrolled>=MAX_CREDITS_STUDY_ABROAD && creditsEnrolled<=MAX_CREDITS){
                return true;
            }
        }
        return false;
    }

    /**
     * calculate the student tuition by credit & study abroad.
     * @param creditsEnrolled the credits enrolled by the student
     * @return tution that calculated
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        int tuition;
        if(isStudyAbroad){
            tuition = UNIVERSITY_FEE+HEALTH_INSURANCE_FEE;
            return tuition;
        }
        if(creditsEnrolled>=MAX_CREDITS_STUDY_ABROAD && creditsEnrolled<=MAX_CREDIT_FULLTIME){
            tuition = TUITION_INTERNATIONAL + UNIVERSITY_FEE + HEALTH_INSURANCE_FEE;
            return tuition;
        }
        if(creditsEnrolled>16){
            int extraCredits = creditsEnrolled-16;
            tuition = TUITION_INTERNATIONAL + UNIVERSITY_FEE + HEALTH_INSURANCE_FEE + (extraCredits*PER_CREDIT_FEE);
            return tuition;
        }
        return 0;

    }

    /**
     * check student is study abroad or not
     * @return ture if study abroad, false otherwise
     */
    public boolean isNormal(){
        if(isStudyAbroad){
            return true;
        }else{
            return false;
        }
    }
}
