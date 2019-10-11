// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2018T2
 * Name:Eric
 * Username:xmut_1712409237
 * ID:1712409237
 */

import ecs100.*;
import java.awt.Color;

/**
 * Reads a date from the user as three integers, and then checks that the date is valid
 */

public class DateValidator {
    int wm = 0;
    /**
     * Asks user for three integer and then checks if it is a valid date.
     */
    public void doCore(){
        int day = UI.askInt("Day: ");
        int month = UI.askInt("Month: ");
        int year = UI.askInt("Year: ");
        this.validateDateCore(day, month, year);
    }

    /** CORE
     * Determines whether the date specified by the three integers is a valid date.
     * For the Core, you may assume that
     * - All months have 31 days, numbered 1 to 31
     * - The months run from 1 to 12
     * - Years start from 1 
     */
    public void validateDateCore(int day, int month, int year){
        /*# YOUR CODE HERE */
        if(year>0&month>0&day>0){
            UI.println("Datevalidator(core):");
            if(month<13){
                if(day<32){
                    UI.println("The date you entered is reasonable!");
                }
                else{
                    UI.println("The number of days of one month cannot be greater than 31!");
                }
            }
            else{
                UI.println("The number of months in a year cannot be greater than 12!");
            }
        }
        else{
          UI.println("The three values of the date you entered cannot be less than 1!");  
        }
        UI.println("****************************************************************");
        UI.println("");
    }

    /**
     * Asks user for three integer and then checks if it is a valid date.
     */
    public void doCompletion(){
        int day = UI.askInt("Day: ");
        int month = UI.askInt("Month: ");
        int year = UI.askInt("Year (4 digits): ");
        this.validateDateCompletion(day, month, year);
    }

    /** COMPLETION
     * Determines whether the date specified by the three integers is a valid date.
     * For the Completion, you should check that
     * - Months have the correct number of days
     * - Years have 4 digits (between 1000 and 9999)
     * - On leap years February should have 29 days.
     *    A year is a leap year if:
     *       - The year can be evenly divided by 4 but not 100,  OR
     *       - The year can be evenly divided by 400 
     */
    public void validateDay(int day,int month,int year){
        if(month == (1|3|4|7|8|10|12)){
             if(day>31){
                   UI.println("There are only 31 days at most in the month you entered!");
                   wm++;
             }
        }
        else if(month == 2){
            if((year%4 == 0&year%100!=0)||(year%400 == 0)){
                   if(day>29){
                       UI.println("In a leap year February is only 29 days at most!");
                       wm++;
                   }
            }

            else{
                 if(day>28){
                     UI.println("In a non-leap year, February has only 28 days at most!");
                     wm++;
                 }
            }
        }    
        else{
             if(day>30){
                   UI.println("There are only 30 days at most in the month you entered!");
                   wm++;
             }
        }
    }
    public void validateDateCompletion(int day, int month, int year){
        /*# YOUR CODE HERE */
        wm = 0;
        UI.println("Datevalidator: ");
        if(year>999&year<10000&day>0&month>0){
            this.validateDay(day,month,year);
        }
        else{
            if(year<1000|year>9999){
                UI.println("Year must be 4 digits!");
                wm++;
                this.validateDay(day,month,year);
            }
            else{
                UI.println("The month and days in the date must be greater than 0!");
                wm++;
            }          
        }
        
        /** Determine if there are any errors,
         * print out the number of errors if there are any, 
         * and print no errors if there are no errors*/
        if(wm == 0){
            UI.println("The date you entered is reasonable!");
        }
        else{
            UI.println("There are "+wm+" mistakes in your date!");
        }
        UI.println("****************************************************************");
        UI.println("");
    } 
     /**
     * Asks user for three integer and then checks if it is a valid date.
     */
    public void doChallenge(){
        int day = UI.askInt("Day: ");        
        int month = UI.askInt("Month: ");
        int year = UI.askInt("Year (4 digits): ");
        int week = UI.askInt("Weekday");
        this.validateDateChallenge(day,week,month,year);
    }
    /**
     * Suppose January 1st of this year ( 2018 ) is Monday ( actually it is )
     * then we can start our programing.
       */
    public void validateDateChallenge(int day,int week, int month, int year){
        int daynum;
        int monthnum = 0;
        int yearnum;
        String weekarr[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String weekday = null;
        
        /**Calculates the number of days in the year of difference*/
        int years = year-2018;
        int yearc = year-2000;
        if(years>0){
           yearnum = years*365 + (int)Math.floor((years+2)/4)  - (int)Math.floor(yearc/100) + (int)Math.floor(yearc/400);
        }
        else if(years<0){
           yearnum = years*365 + (int)Math.floor((years-2)/4) + 1 -(int)Math.floor(yearc/100) + (int)Math.floor(years/400);
        }
        else{
            yearnum =0;
        }
        /**Help us to debug with our code
        UI.println(years);
        UI.println(yearnum);
        */
        /**Calculates the number of days in the month of difference*/
        int mon[] = new int[12];
        for(int i = 0;i<12;i++){
            if(i == 3|i == 5|i == 8|i == 10){
                mon[i] = 30;
            }
            else if(i == 1){
                mon[i] = 28;
            }
            else{
                mon[i] = 31;
            }
            /**To help us to debug with our code
            UI.println(mon[i]);
            */
        }
        for(int j = 0;j<month-1;j++){
            monthnum = monthnum + mon[j];
        }
        /**Calculate how many days have passed since January 1, 2018*/
        daynum = day + monthnum + yearnum-1;
        
        /**Judge the date*/
        
        if(daynum >=0){
            weekday = weekarr[daynum%7];
        }
        else{
            weekday = weekarr[6+daynum%7];
        }
        if(weekarr[week-1] == weekday){
            this.validateDateCompletion(day, month, year);
            UI.println("The number of weeks you entered is correct! ");
        }
        else{
            wm++;
            this.validateDateCompletion(day, month, year);
            UI.println("And the number of weeks you entered is incorrect!");
            UI.println("If the date you entered is reasonable,it should be :  "+weekday);
        }
        UI.println("****************************************************************");
        UI.println("");
        /**To help us to debug with our code
        * (It's very helpful when my program didn't meet the requirements I wanted,
        * By using these four lines of code, I have corrected at least 10 mistake. 
        UI.println(monthnum);
        UI.println(daynum);
        UI.println(weekday); 
        UI.println(yearnum);
        */
    }
    
}

