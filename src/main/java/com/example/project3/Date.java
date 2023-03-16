package com.example.project3;

import java.util.Calendar;
import java.util.StringTokenizer;

/** */
public class Date implements Comparable<Date> {

    private int year;
    private int month;
    private int day;

    private static final int JANUARY = 1;
    private static final int FEBRUARY =2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;
    private static final int QUADRENNIAL = 4;
    private static final int MONTH_END = 31;
    private static final int MONTH_START = 1;
    private static final int DIFF_MONTH_START = 30;
    private static final int LEAP_YEAR = 29;
    private static final int NON_LEAP_YEAR = 28;
    private static final int AGE = 16;
    private static final int APR_JUN_SEP_NOV = 30;
    private static final int JAN_MAR_MAY_JUL_AUG_OCT_DEC = 31;
    private static final int FEB_NON_LEAP_YEAR = 28;
    private static final int FEB_LEAP_YEAR = 29;
    private static final int CENTENNIAL = 100;
    private static final int QUATERCENTENNIAL = 400;





    /**
     * Creates a new Date object with the specified month, day, and year.
     * @param month the month of the date
     * @param day the day of the date
     * @param year the year of the date
     */
    public Date(int month, int day, int year) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Creates a new Date object with the current date.
     */
    public Date() {
        Calendar currentDate =  Calendar.getInstance();
        this.year = currentDate.get(Calendar.YEAR);
        this.month = currentDate.get(Calendar.MONTH)+1;
        this.day = currentDate.get(Calendar.DAY_OF_MONTH);

    }

    /**
     * Creates a new Date object with the date specified in the string.
     * @param date a string representation of the date in the format "MM/DD/YYYY"
     */
    public Date(String date) {

        Integer[] arrayOfDate = new Integer[3];
        StringTokenizer givenDate = new StringTokenizer(date, "/");
        for (int i = 0; i < 3; i++) {
            arrayOfDate[i] = Integer.parseInt(givenDate.nextToken());

        }
        this.year = arrayOfDate[2];
        this.month = arrayOfDate[0];
        this.day = arrayOfDate[1];
        Date newDate = new Date(year, month, day);
    }


    /**
     * Returns a string representation of the date in the format "MM/DD/YYYY".
     * @return a string representation of the date
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /**
     * Compares this Date object to another Date object.
     * @param d the other Date object
     * @return 0 if the two dates are equal, a negative number if this date is earlier than the other date,
     * or a positive number if this date is later than the other date
     */
    @Override
    public int compareTo(Date d) {
        if ((month == d.month) && (day == d.day) && (year == d.year)) {
            return 0;
        }

        if(year>d.year){
            return 1;
        }else{
            if(year<d.year){
                return -1;}
        }

        if(year==d.year){
            if(month>d.month){
                return 1;
            }else{
                if(month< d.month){
                    return -1;
                }
            }
        }
        if(year==d.year){
            if(month==d.month){
                if(day>d.day){
                    return 1;
                }
                else{
                    if(day<d.day){
                        return -1;
                    }
                }
            }
        }
        return 0;
    }
    /**
     Returns true if this date is equal to another date, false otherwise
     @param o - an object that will be explicitly casted into date
     @return true if equal, false if not equal
     */
    @Override
    public boolean equals(Object o){
        Date d = (Date) o;
        if((month == d.month) && (day == d.day) && (year == d.year)){
            return true;
        }else{
            return false;
        }
    }
    public boolean isYoung(){
        Calendar currentDate1 = Calendar.getInstance();
        if(currentDate1.get(Calendar.YEAR)-year<AGE){
            return true;}
        if(currentDate1.get(Calendar.YEAR)-year==AGE && month>currentDate1.get(Calendar.MONTH) + 1) {       // && day>= currentDate1.get(Calendar.DAY_OF_MONTH)){
            return true;
        }else{
            if(currentDate1.get(Calendar.YEAR)-year==AGE &&month==currentDate1.get(Calendar.MONTH)+1 && day>currentDate1.get(Calendar.DAY_OF_MONTH)){
                return true;
            }
        }
        return false;
    }

    public boolean isLeapYear(){
        if(month==FEBRUARY){
            if((year%QUADRENNIAL==0)){
                if(day>=MONTH_START && day<=LEAP_YEAR)
                {return true;}
            }else{
                if(day>=MONTH_START && day<=NON_LEAP_YEAR){return true;}
            }
        }
        return false;
    }



    /**
     Checks to see if the date is a valid calendar date, or age is less than 16 years
     @return true if equal, false if not equal
     */
    public boolean isValid() {
        Calendar currentDate1 = Calendar.getInstance();
        if(isYoung()){
            return false;
        }
        if(isLeapYear()){
            return true;
        }
        //check current days and future
        if((month >= currentDate1.get(Calendar.MONTH) + 1) && (day >= currentDate1.get(Calendar.DAY_OF_MONTH)) && (year >=currentDate1.get(Calendar.YEAR))) {
            return false;}
        //checks months with 31 days
        if((month==JANUARY) || (month==MARCH) || (month==MAY) || (month== JULY) || (month==AUGUST) || (month==OCTOBER)|| (month==DECEMBER)) {
            if (day <= MONTH_END && day>=MONTH_START) {
                return true;}}
        //check months with 30 days
        if ((month == APRIL) || (month == JUNE) || (month == SEPTEMBER) || (month == NOVEMBER)) {
            if(day>=MONTH_START&& day<=DIFF_MONTH_START){
                return true;}}
        return false;}

    /**
     A testbed for the Date class.
     This testbed checks the validity of several dates to make sure they are within 16 years of the current date,
     have valid months and days, and aren't future dates. It also tests the toString, compareTo, and equals methods.
     */
    /*public static void main(String[] args) {
        Date date1 = new Date("5/15/2008"); // is not 16 years old yet
        Date date2 = new Date("13/10/2001"); // incorrect month
        Date date3 = new Date("4/31/1998"); // incorrect day
        Date date4 = new Date(10, 5, 2025); // checks for a future year
        Date date5 = new Date("2/29/2003"); // checks for non-leap year
        Date date6 = new Date(); // returns false if current date
        Date date7 = new Date("4/30/1998"); // checks if april has 30 days
        Date date8 = new Date("2/29/2004"); // valid dates
        System.out.println(date1.isValid());
        System.out.println(date2.isValid());
        System.out.println(date3.isValid());
        System.out.println(date4.isValid());
        System.out.println(date5.isValid());
        System.out.println(date6.isValid());
        System.out.println(date7.isValid());
        System.out.println(date1.compareTo(date2));
    }*/

}