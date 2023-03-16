package com.example.project3;

/**
 The Student Class makes a student object which is used to store in the roster
 @author Apurva Desai, Yehun Kim
  * */
public abstract class Student implements Comparable<Student>{
    private Profile profile;
    private Major major;
    private int creditCompleted;
    public Major getMajor() {
        return major;
    }
    private static final int freshmanLowerEnd = 0;
    private static final int freshmanUpperEnd = 29;
    private static final int sophomoreLowerEnd = 30;
    private static final int sophomoreUpperEnd = 59;
    private static final int juniorLowerEnd = 60;
    private static final int juniorUpperEnd = 89;
    private static final int seniorLowerEnd = 90;
    private static final int seniorUpperEnd = 120;
    int MIN_CREDITS = 3;
    int MAX_CREDITS = 24;

    /**
     This is a getter method which returns the credits Completed by a student.
     */
    public int getCreditCompleted(){
        return creditCompleted;
    }

    /**
     This is a constructor which takes in a profile and makes a student object
     @param profile the profile used to make an object
     */
    public Student(Profile profile){
        this.profile = profile;
    }

    public void setMajor(Major newMajor){
        this.major = newMajor;
    }

    /**
     This is a getter method that returns a profile.
     */
    public Profile getProfile(){
        return profile;
    }

    /**
     This is a helper method that returns a String mentioning whether the student is a freshman, junior, senior,
     or sophomore
     @param p the profile used to find the student's credits
     @return returns a string if a student's credits lie between 0 and 120 otherwise return null
     */
    private String giveStanding(Profile p) {
        if(this.creditCompleted>=freshmanLowerEnd && this.creditCompleted<=freshmanUpperEnd){
            return "freshman";
        }
        if(this.creditCompleted>=sophomoreLowerEnd && this.creditCompleted<=sophomoreUpperEnd){
            return "sophomore";
        }
        if(this.creditCompleted>=juniorLowerEnd && this.creditCompleted<=juniorUpperEnd){
            return "junior";
        }
        if(this.creditCompleted>=seniorLowerEnd && this.creditCompleted<=seniorUpperEnd){
            return "senior";
        }
        return null;

    }




    /**
     This is a constructor which takes a profile major and credits as parameters and create a student object
     @param profile the profile of the student object to be created
     @param major the major of the student object to be created
     @param creditCompleted the credits of the student object to be created*/
    public Student(Profile profile, Major major, int creditCompleted){
        this.profile=profile;
        this.major=major;
        this.creditCompleted=creditCompleted;
    }

    public void setCreditCompleted(int newCredits){
        this.creditCompleted = newCredits;
    }


    //override methods

    /**
     this method converts the student object into a string
     @return returns a string of the object
     */

    @Override
    public String toString(){
        return profile + " (" + major.getEnum()+ " "+  major+ ")" +  " Credits Completed:"+ creditCompleted + " (" + giveStanding(profile) + ")";
    }

    /**
     this method checks if two student objects are equals
     @param o is the object that is explicitly converted to student object
     @return returns a boolean if objects are equal then true otherwise false
     */
    @Override
    public boolean equals(Object o){
        Student s = (Student) o;
        if(profile.equals(s.profile)){
            return true;
        }else{
            return false;
        }
    }

    /**
     this method compare 2 student objects and returns 0 if they are equal, 1 if s1 >s2, and -1 if s1 <s2
     @return returns an integer 0,1,-1 depending on the objects
     @params s is the student that is being compared with
     */
    @Override
    public int compareTo(Student s){
        if(profile.compareTo(s.profile)==0){
            return 0;
        }
        if(profile.compareTo(s.profile)>0){
            return 1;
        }else{
            return -1;
        }
    }

    /**
     this method compares the major of two student objects
     @param s s is the student which is to be compared
     @return it returns an integer depending on the two objects. it returns either positive or negative integer
     depending on the student objects
     */
    public int compareByMajor(Student s){
        return this.major.name().compareTo(s.major.name());
    }

    /**
     this method compares the schools of two students and returns an integer
     @param s  the second student being compared with the initial student
     @return returns a positive or negative integer depending on the student objects
     */
    public int compareBySchool(Student s){
        return this.major.getSchool().compareTo(s.major.getSchool());
    }

    public boolean isValid(int creditsEnrolled){
        if(creditsEnrolled>=MIN_CREDITS && creditsEnrolled<=MAX_CREDITS){
            return true;
        }
        else{
            return false;
        }
    }

    public abstract double tuitionDue(int creditsEnrolled);
    public abstract boolean isResident();
    /**
     this is the testbed main() method to check compareTo of the student class
     */
    /*public static void main(String[] args) {
        //test cases for the student class
        Student student1 = new Student(new Profile("desai", "ace", "10/5/2001"), Major.CS, 89);
        Student student2 = new Student(new Profile("desai", "ace", "10/5/2001"), Major.CS, 89);
        Student student3 = new Student(new Profile("boulder", "john", "7/5/2001"), Major.BAIT, 50);
        Student student4 = new Student(new Profile("doe", "jane", "4/15/2001"), Major.ITI, 62);
        Student student5 = new Student(new Profile("doe", "jane", "9/6/2000"), Major.CS, 47);
        Student student6 = new Student(new Profile("doe", "john", "10/6/1996"), Major.CS, 89);
        Student student7 = new Student(new Profile("doe", "jan", "10/7/2002"), Major.CS, 89);
        System.out.println(student1.compareTo(student2));
        System.out.println(student2.compareTo(student3));
        System.out.println(student3.compareTo(student2));
        System.out.println(student4.compareTo(student5));
        System.out.println(student5.compareTo(student4));
        System.out.println(student6.compareTo(student7));
        System.out.println(student7.compareTo(student6));
    }*/

}
