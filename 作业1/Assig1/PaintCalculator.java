// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2018T2
 * Name:Eric
 * Username:xmut_1712409237
 * ID:1712409237
 */

import ecs100.*;

/** Program for calculating amount of paint required to paint a room */

public class PaintCalculator{

    public static final double DOOR_HEIGHT = 2.1;        // Height of the doors
    public static final double DOOR_WIDTH = 0.8;         // Width of the doors
    public static final double SQ_METERS_PER_LITRE = 15.0; // Area covered by 1 litre of paint
    public double AREA;
    /** Calculates and prints litres of paint needed to paint a room
     *  with four walls (excluding the doors, floor, and ceiling)
     */
    public void calculatePaintCore(){
        /*# YOUR CODE HERE */
        double WALL_HEIGHT = UI.askDouble("How tall is your room?( in meters )");
        double WALL_WIDTH = UI.askDouble("How wide is your room?( in meters )");
        double WALL_LENGTH = UI.askDouble("How long is your room( in meters )?");
        int DOOR_NUMBER = UI.askInt("How many doors are there?");
        
        double DOOR_AREA = DOOR_HEIGHT*DOOR_WIDTH;
        double WALL_AREA1 =  WALL_HEIGHT*WALL_WIDTH;
        double WALL_AREA2 =  WALL_HEIGHT*WALL_LENGTH;
        
        
        AREA = 2*WALL_AREA1+2*WALL_AREA2-DOOR_AREA*DOOR_NUMBER;
        System.out.println("*********************************************************");
        System.out.println("Core");
        System.out.println();
        if(AREA<0){
            System.out.println("Your room's area is negative!");
        }
        else{
            System.out.println("The paint required for the room is "+AREA/SQ_METERS_PER_LITRE+"  litres");
        }
        System.out.println("Please click the"+" Challenge(CORE) "+"button to see the most economical set of tins of paint required to cover the wall.");
        PaintCalculator pc = new PaintCalculator();         
        UI.addButton("Challenge(CORE)",
            ()->pc.calculatePaintChallenge(AREA));
    }

    /** Calculates and prints litres of paint needed to paint
     *  - the four walls of a room (excluding the doors and windows)
     *  - the ceiling (different type of paint)
     */
    public void calculatePaintCompletion(){
        /*# YOUR CODE HERE */
        double WALL_HEIGHT = UI.askDouble("How tall is your room?( in meters )");
        double WALL_WIDTH = UI.askDouble("How wide is your room?( in meters )");
        double WALL_LENGTH = UI.askDouble("How long is your room?( in meters )");
        int DOOR_NUMBER = UI.askInt("How many doors are there?");
        int WINDOWS_NUMBER = UI.askInt("How many windows are there?");
        double WINDOWS_WIDTH = UI.askDouble("How wide is your windows?( in meters )");
        double WINDOWS_LENGTH = UI.askDouble("How long is your windows?( in meters )");        
        
        double DOOR_AREA = DOOR_HEIGHT*DOOR_WIDTH*DOOR_NUMBER;
        double WALL_AREA1 =  2*WALL_HEIGHT*WALL_WIDTH;
        double WALL_AREA2 =  2*WALL_HEIGHT*WALL_LENGTH;
        double WINDOWS_AREA = WINDOWS_WIDTH*WINDOWS_LENGTH*WINDOWS_NUMBER;
        double CEILING_AREA = WALL_WIDTH*WALL_LENGTH;
        
        AREA = WALL_AREA1+WALL_AREA2-DOOR_AREA-WINDOWS_AREA;
               
        System.out.println("*********************************************************");
        System.out.println("Completion");
        System.out.println();
        if(AREA<0){
            System.out.println("Your room's area is negative!");
        }
        else{
            System.out.println("The paint required for the wall is "+AREA/SQ_METERS_PER_LITRE+"  litres");
        }
        System.out.println("The paint required for the ceiling is "+CEILING_AREA/SQ_METERS_PER_LITRE+"  litres");
        System.out.println("***************************************************");
        System.out.println("Please click the"+" Challenge(Completion) "+"button to see the most economical set of tins of paint required to cover the wall.");
        PaintCalculator pc = new PaintCalculator();        
        UI.addButton("Challenge(Completion)",
            ()->pc.calculatePaintChallenge(AREA));    
    }
    /** I made a method to judge how to choose the size of the paint
     * tin is the most cost-effective.And I add a button called "Challenge" to start it.
     * -You can enter a value for each size that are cheaper than the next smaller size.
     * - The amount of paint it calculates for a room depends on the value calculated by the previous program.
     * 
     */
    public void calculatePaintChallenge(double AREA0){        
        double dsct = UI.askDouble("How much cheaper is the size of each paint tin per liter than the next smaller size? ");        
        double dsct2 = 1-dsct;
        AREA = AREA0;
        if(dsct2<0){
            System.out.println("The large paint tin cannot be more expensive than a small one");
            return;
        }
        System.out.println("*********************************************************");
        System.out.println("Challenge");
        System.out.println();
        System.out.println("If each size is"+dsct*100+"%cheaper per liter than the next smaller size");
        System.out.println(",and the paint required for the wall is"+AREA/15+"  litres");
        System.out.println();
        System.out.println("The most economical way to cover a wall is to choose:");
        double LI0 = AREA/15.0;
        int num1 = 0,num2 = 0,num4 = 0,num10 = 0;       
        double dsct4 = dsct2*dsct2;
        double dsct10 = dsct2*dsct2*dsct2;
        double LI10 = 10*dsct10/dsct4;
        double LI4 = 4*dsct4/dsct2;
        double LI2 = 2*dsct2;
        
        for(int i = 0;i<=1000;i++){
            if(LI0<10){
                break;
            }
            LI0 = LI0-10;
            num10 = num10+1;
        }
        if(LI0>LI10){
            num10 = num10+1;
            System.out.println(num10+"  10-liter paint tins ");           
        }
        else{
                for(int i = 0;i<=1000;i++){
                    if(LI0<4){
                        break;
                    }
                    LI0 = LI0-4;
                    num4 = num4+1;                    
                }
                if(LI0>LI4){
                    num4 = num4+1;
                    System.out.println(num10+"  10-liter paint tins ");
                    System.out.println(num4+"  4-liter paint tins ");                    
                }
                else{
                    for(int i = 0;i<=1000;i++){
                         if(LI0<2){
                            break;
                        }
                        LI0 = LI0-2;
                        num2 = num2+1;                      
                    }
                    if(LI0>LI2){
                        num2 = num2+1;
                        System.out.println(num10+"  10-liter paint tins");
                        System.out.println(num4+ "  4-liter paint tins ");   
                        System.out.println(num2+"  2-liter paint tins "); 
                    }
                    else if(LI0>1){
                        num1 = 2;
                        System.out.println(num10+"  10-liter paint tins ");
                        System.out.println(num4+"  4-liter paint tins ");   
                        System.out.println(num2+"  2-liter paint tins "); 
                        System.out.println(num1+"  1-liter paint tins ");
                    }
                    else{
                        num1 = 1;
                        System.out.println(num10+"  10-liter paint tins ");
                        System.out.println(num4+"  4-liter paint tins ");   
                        System.out.println(num2+"  2-liter paint tins "); 
                        System.out.println(num1+"  1-liter paint tins ");
                    }
                
                } 
        }                                
    }
}
