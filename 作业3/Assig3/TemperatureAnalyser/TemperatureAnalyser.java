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
import java.util.*;

/** The program contains several methods for analysing the readings of the temperature levels over the course of a day.
 *  There are several things about the temperature levels that a user may be interested in: 
 *    The average temperature level.
 *    How the temperatures rose and fell over the day.
 *    The maximum and the minimum temperature levels during the day.
 */
public class TemperatureAnalyser{

    /* analyse reads a sequence of temperature levels from the user and prints out
     *    average, maximum, and minimum level and plots all the levels
     *    by calling appropriate methods
     */
    public void analyse(){
        UI.clearPanes();
        ArrayList<Double> listOfNumbers = UI.askNumbers("Enter levels, end with 'done': ");
        if (listOfNumbers.size() != 0) {
            this.printAverage(listOfNumbers);
            this.plotLevels(listOfNumbers);
 
            UI.printf("Maximum level was:  %f\n", this.maximumOfList(listOfNumbers));
            UI.printf("Minimum level was:  %f\n", this.minimumOfList(listOfNumbers));
        }
        else {
            UI.println("No readings");
        }
    }

    /** Print the average level
     *   - There is guaranteed to be at least one level,
     *   - The method will need a variable to keep track of the sum, which 
     *     needs to be initialised to an appropriate value.
     *  CORE
     */
    public void printAverage(ArrayList<Double> listOfNumbers) {
        /*# YOUR CODE HERE */
        double sum = 0;
        for(int i = 0;i<listOfNumbers.size();i++){
            sum = sum + listOfNumbers.get(i);
        }
        double average = sum/listOfNumbers.size();
        UI.print(sum);
        UI.print("The average temperature is:"+average);
    }

    /**
     * Plot a bar graph of the sequence of levels,
     * using narrow rectangles whose heights are equal to the level.
     * [Core]
     *   - Plot the bars.
     * [Completion]
     *   - Draws a horizontal line for the x-axis (or baseline) without any labels.
     *   - Any level greater than 400 should be plotted as if it were just 400, putting an
     *         asterisk ("*") above it to show that it has been cut off.
     * [Challenge:] 
     *   - The graph should also have labels on the axes, roughly every 50 pixels.
     *   - The graph should also draw negative temperature levels correctly.
     *   - Scale the y-axis and the bars so that the largest numbers and the smallest just fit on the graph.
     *     The numbers on the y axis should reflect the scaling.
     *   - Scale the x-axis so that all the bars fit in the window.
     */
    public void plotLevels(ArrayList<Double> listOfNumbers) {
        int base = 420;              //base of the graph
        int left = 50;               //left of the graph
        int step = 25;               //distance between plotted points
        
        /*# YOUR CODE HERE */
        int l = 300;
        int wid = 800;
        double st = (0.5*wid)/listOfNumbers.size();
        double x[] = new double[listOfNumbers.size()];
        double y[] = new double[listOfNumbers.size()];
        double max;
        if(Math.abs(this.minimumOfList(listOfNumbers))>this.maximumOfList(listOfNumbers)){
            max = Math.abs(this.minimumOfList(listOfNumbers));
        }
        else{
            max = this.maximumOfList(listOfNumbers);
        }
        if(max>=400){
            max = 400; 
        }
       
        this.draw(max,left,base,l);
        UI.drawLine(left,base,800,base);
        UI.drawLine(left,0,left,800);     
        for(int i = 0;i<listOfNumbers.size();i++){
            int R = (int)(Math.random()*255);
            int G = (int)(Math.random()*255);
            int B = (int)(Math.random()*255);            
            Color r = new Color(R,G,B);            
            UI.setColor(r);
            x[i] = 2*st*i+left;
            y[i] = listOfNumbers.get(i);
            String str = Double.toString(y[i]);
            UI.println(str);
            if(y[i]>=400){
                 UI.fillRect(x[i],base-l,st,l);
                 UI.drawString("****",x[i],base-l);
                 UI.drawString(str,x[i]+0.3*st,base+20);
            }
            else if(y[i]>0){
                 UI.fillRect(x[i],base-l*(y[i]/max),st,l*(y[i]/max));
                 UI.drawString(str,x[i]+0.3*st,base+20);                 
            }
            else if(y[i]<-400){
                 UI.fillRect(x[i],base,st,l);
                 UI.drawString("****",x[i],base+l);
                 UI.drawString(str,x[i]+0.3*st,base-10);
            }
            else{
                 UI.fillRect(x[i],base,st,-l*(y[i]/max));
                 UI.drawString(str,x[i]+0.3*st,base-10);
            }           
            if(i>0){
                UI.setColor(Color.BLACK);
                UI.setLineWidth(5);
                UI.drawLine(x[i-1],base-l*(y[i-1]/max),x[i],base-l*(y[i]/max)); 
            }         
        }        
        UI.println("Finished plotting");
    }
    public void draw(double max,int left,int base,int l){
        String[] str = new String[4];
        for(int i = 1;i<5;i++){
            double h = 0.25*i;
            double m = max*h;
            str[4-i] = Double.toString(m);
            UI.setLineWidth(3);
            UI.drawLine(left,base-h*l,left-10,base-h*l);
            UI.drawString(str[4-i],left-30,base-h*l);
            UI.drawLine(left,base+h*l,left-10,base+h*l);
            UI.drawString("-"+str[4-i],left-30,base+h*l);
        }     
    }
    /** Find and return the maximum level in the list
     *   - There is guaranteed to be at least one level,
     *   - The method will need a variable to keep track of the maximum, which
     *     needs to be initialised to an appropriate value.
     *  COMPLETION
     */
    public double maximumOfList(ArrayList<Double> listOfNumbers) {
        /*# YOUR CODE HERE */
        double max = 0;
        for(int i = 0;i<listOfNumbers.size();i++){
            if(max<=listOfNumbers.get(i)){
                max = listOfNumbers.get(i);
            }
        }
        return max;
    }

    /** Find and return the minimum level in the list
     *   - There is guaranteed to be at least one level,
     *   - The method will need a variable to keep track of the minimum, which
     *     needs to be initialised to an appropriate value.
     *  COMPLETION
     */
    public double minimumOfList(ArrayList<Double> listOfNumbers) {        
        /*# YOUR CODE HERE */
        double min = 450;
        for(int i = 0;i<listOfNumbers.size();i++){
            if(min>=listOfNumbers.get(i)){
                min = listOfNumbers.get(i);
            }
        }
        return min;
    }



}
