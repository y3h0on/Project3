package com.example.project3;

/**
 * Create a enrollment class to represent a container of all student in the current sesmeter
 * @author Yehun Kim , Apurva Desai
 */

public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;
    private static final int INITIAL_SIZE_OF_ARRAY = 4;
    private static final int NOT_FOUND = -1;

    /**
     * getter method to get a size
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * Empty constructor
     */
    public Enrollment() {
        enrollStudents = new EnrollStudent[INITIAL_SIZE_OF_ARRAY]; //initialize the array with 10
        size = 0;
    }

    /**
     * find method will try to find an enrollstudent in the Enrollment class
     * @param enrollStudent
     * @return if enrollStudent equal to enrollStudent in the Enrollment, return array, return NOT_FOUND otherwise
     */
    public int find(EnrollStudent enrollStudent){
        for(int i = 0; i < size; i ++){
            if(enrollStudents[i].equals(enrollStudent)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Helper method to check the size of the EnrollStudent array
     */
    public void grow(){
        EnrollStudent[] newRoster = new EnrollStudent[enrollStudents.length +4];
        System.arraycopy(enrollStudents,0,newRoster,0,size);
        enrollStudents = newRoster;
    }

    /**
     * If the profile of the student is in the EnrollmentStudent, it will add a enrollStudent and will update the enrollStudent
     * in the array.
     * @param enrollStudent
     */
    //add to the end of array
    public void add(EnrollStudent enrollStudent) {
        if (size >= enrollStudents.length) {
            grow();
        } else if (contains(enrollStudent)) {
            enrollStudents[find(enrollStudent)] = enrollStudent;
        } else {
            enrollStudents[size] = enrollStudent;
            size++;
        }
    }

    /**
     * remove student and update the array after removed the student in the Enrollment
     * @param enrollStudent
     */
    //move the last one in the array to replace the deleting index position
    public void remove(EnrollStudent enrollStudent) {
        int index = find(enrollStudent);
        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                enrollStudents[i] = enrollStudents[i + 1];
            }
            size--;
        }
    }

    /**
     * check if a enrollStudent is in the Enrollment or not
     * @param enrollStudent
     * @return ture is in the Enrollment, false otherwise
     */
    public boolean contains(EnrollStudent enrollStudent) {
        for(int i = 0; i < size; i ++){
            if(enrollStudents[i].equals(enrollStudent)){
                return true;
            }
        }
        return false;
    }

    /**
     * If student list is not an empty, print the student list.
     */
    public void print() {
        if(size == 0){
            System.out.println("Enrollment is empty!");
        }else{
            System.out.println("** Enrollment * ");
            for (int i = 0; i < size; i++) {
                System.out.println(enrollStudents[i].toString());
            }
            System.out.println("* end of Enrollment **");
        }
    }

    // display the current enrollment list, based on their order in the array
    public void displayCurrentEnrollment() {
        if(size == 0){
            System.out.println("Student roster is empty!");
        }else{
            System.out.println("Current enrollment list:");
            print();
        }
    }

    /**
     * return enrollStudent's profile using getProfile()
     * @param i
     * @return enrollStudents profile
     */
    public Profile returnProfile(int i) {
        return enrollStudents[i].getProfile();
    }

    /**
     * return enrollStudent's credit using getCreditsEnrolled()
     * @param i
     * @return enrollStudents creditEnrolled
     */
    public int getCreditsEnrolled(int i){
        return enrollStudents[i].getCreditsEnrolled();
    }
}