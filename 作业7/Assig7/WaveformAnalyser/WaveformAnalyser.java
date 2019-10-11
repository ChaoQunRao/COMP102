
// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2018T2
 * Name:Eric
 * Username:xmut_1712409237
 * ID:1712409237
 */

import ecs100.*;
import java.io.*;
import java.util.*;
import java.awt.Color;
import javax.swing.*;
/**
 * This is related to your program from assignment 3 which analysed temperature levels
 * However, instead of reading data from the user, it reads the data from
 * a file into an ArrayList.
 * It can also cope with much larger sets of numbers.
 * The numbers are guaranteed to be integers but the values can be
 *   negative and the signal swings above and below zero.
 *
 * The methods you are to complete all focus on the ArrayList of data.
 * CORE
 *  read:              reads numbers into an ArrayList.
 *  display:           displays the waveform.
 *  showSpread:        displays the spread with two horizontal lines and returns its value.
 * COMPLETION
 *  displayDistortion: shows in red the distorted part of the signal.
 *  highlightPeaks:    plots the peaks with small green circles.
 *  normalise:         normalises all the values down so there is no distortion.
 * CHALLENGE
 *  doEnvelope:        displays the upper envelope.
 *  save:              saves the current waveform values into a file.
 *  select and edit:   let the user select a regions of the waveform with the mouse
 *                     to remove them.  Add a save button to save the edited values.
 */

public class WaveformAnalyser{
    // Fields 
    private ArrayList<Double> waveform;   // the field to hold the ArrayList of values
    
    // Constant: the threshold for the distortion level
    public static final double THRESHOLD = 200;

    // Constants: the dimensions of the graph for the display method
    public static final int GRAPH_LEFT = 10;
    public static final int ZERO_LINE = 300;

    // Constant fields holding the size of the circles for the highlightPeaks method
    public static final int SIZE_CIRCLE = 10;
    private double Size = 1;
    private double MaxSize = 1;
    /*#To change the color of "mouse" button*/
    JButton mouse;
    /** Constructor:
     */
    public WaveformAnalyser(){
        this.setupGUI();
    }

    /** Set up the nine buttons and mouselistener */
    public void setupGUI(){
        //core
        UI.addButton("Read Data", this::read);
        UI.addButton("Display Waveform", this::display);
        UI.addButton("Spread", this::showSpread);
        //completion
        UI.addButton("Display Distortion", this::displayDistortion);
        UI.addButton("Peaks", this::highlightPeaks);
        UI.addButton("Normalise", this::normalise);
        //challenge
        UI.addButton("Envelope", this::doEnvelope);
        UI.addButton("Save", this::save);
        /*#Make a new button to Turn on mouse listener*/
        mouse = UI.addButton("Delete",this::Mouse);
        UI.addButton("Quit", UI::quit);
    }
    /*#I need uss the "dragged" action so I changed the code*/
    public void Mouse(){
        UI.setMouseMotionListener(this::doMouse);   // only for challenge
        this.mouse.setBackground(Color.pink);
        this.mouse.setLabel("Drag the mouse to choose a region");
    }
    /**
     * [CORE]
     * Clears the panes, 
     * Creates an ArrayList stored in a field, then
     * Asks user for a waveform file (eg waveform1.txt) 
     * Reads data from the file into the ArrayList
     */
    public void read(){
        UI.clearPanes();
        this.waveform = new ArrayList<Double>();
        String fname = UIFileChooser.open();
        /*# YOUR CODE HERE */
        try{
            Scanner sc = new Scanner(new File(fname)); 
            /*#Read data from the file and add it into a arraylist*/
            for(int i = 0;sc.hasNextDouble();i++){
                this.waveform.add(i,sc.nextDouble());
            }
        }catch(IOException e){UI.println("File read failed:" + e);}
        UI.println("Read "+ this.waveform.size()+" data points from " + fname);
    }

    /**
     * [CORE]
     * Displays the waveform as a line graph,
     * The n'th value in waveform is displayed at
     *    x-position is GRAPH_LEFT + n
     *    y-position is ZERO_LINE - the value
     * Plots a line graph of all the points with a blue line between
     *  each pair of adjacent points
     * Draw the horizontal line representing the value zero.
     * Uses GRAPH_LEFT and ZERO_LINE for the dimensions and positions of the graph.
     * Don't worry if the lines go off the window
     */
    public void display(){
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        UI.clearGraphics();

        // draw x axis (showing where the value 0 will be)
        UI.setColor(Color.black);
        UI.drawLine(GRAPH_LEFT, ZERO_LINE, GRAPH_LEFT + this.waveform.size() , ZERO_LINE); 

        // plot points: blue line between each pair of values
        /*# YOUR CODE HERE */
        /*#Using for loop to read from the waveform and according to the value to draw a line*/
        UI.setColor(Color.blue);
        for(int i = 0;i<this.waveform.size()-1;i++){
            UI.drawLine(i,this.ZERO_LINE-this.waveform.get(i),i+1,this.ZERO_LINE-this.waveform.get(i+1));
        }
        UI.drawLine(0,this.ZERO_LINE,Double.POSITIVE_INFINITY,this.ZERO_LINE);
    }

    /**
     * [CORE]
     * The spread is the difference between the maximum and minimum values of the waveform.
     * Finds the maximum and minimum values, then
     * Displays the spread by drawing two horizontal lines on top of the waveform: 
     *   one green line for the maximum value, and
     *   one red line for the minimum value.
     */
    public void showSpread() {
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        this.display();
        /*# YOUR CODE HERE */
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;
        for(double num:this.waveform){
            if(num>max&&num>=0){
                max = num;
            }
            if(num<min&&num<=0){
                min = num;
            }
        }
        UI.println("THE MAX:"+max);
        UI.println("THE MIN:"+min);
        UI.setColor(Color.red.darker());
        UI.drawLine(0,this.ZERO_LINE-max,Double.POSITIVE_INFINITY,this.ZERO_LINE-max);
        UI.setColor(Color.green.darker());                  
        UI.drawLine(0,this.ZERO_LINE-min,Double.POSITIVE_INFINITY,this.ZERO_LINE-min);
    }

    /**
     * [COMPLETION]  [Fancy version of display]
     * Display the waveform as a line graph. 
     * Draw a line between each pair of adjacent points
     *   * If neither of the points is distorted, the line is BLUE
     *   * If either of the two end points is distorted, the line is RED
     * Draw the horizontal lines representing the value zero and thresholds values.
     * Uses THRESHOLD to determine distorted values.
     * Uses GRAPH_LEFT and ZERO_LINE for the dimensions and positions of the graph.
     * [Hint] You may find Math.abs(int a) useful for this method.
     * You may assume that all the values are between -250 and +250.
     */
    public void displayDistortion() {
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        UI.clearGraphics();

        // draw zero axis
        UI.setColor(Color.black);
        UI.drawLine(GRAPH_LEFT, ZERO_LINE, GRAPH_LEFT + this.waveform.size() , ZERO_LINE); 

        // draw thresholds
        /*# YOUR CODE HERE */
        for(int i = 0;i<this.waveform.size()-1;i++){
            if(Math.abs(this.waveform.get(i))>this.THRESHOLD){
                UI.setColor(Color.red);
            }else{
                UI.setColor(Color.blue);
            }
            UI.drawLine(i,this.ZERO_LINE-this.waveform.get(i),i+1,this.ZERO_LINE-this.waveform.get(i+1));
        }
        UI.drawLine(0,this.ZERO_LINE-this.THRESHOLD,Double.POSITIVE_INFINITY,this.ZERO_LINE-this.THRESHOLD);
        UI.drawLine(0,this.ZERO_LINE+this.THRESHOLD,Double.POSITIVE_INFINITY,this.ZERO_LINE+this.THRESHOLD);
        /*# This code is the old, but I rewrite it so now my code was more shorter
           but both of those two ways do the same thing.
        */
       /* double startX = 0;
        double startY = 0;
        double endX = 0,endY = 0;
        for(double wave:waveform){
            endX++;
            endY = wave;
            UI.setColor(Color.blue);
            if(Math.abs(wave)>this.THRESHOLD){
                UI.setColor(Color.red);
                UI.drawLine(startX,this.ZERO_LINE-startY,endX,this.ZERO_LINE-endY);
            }else{
                UI.setColor(Color.blue);
                UI.drawLine(startX,this.ZERO_LINE-startY,endX,this.ZERO_LINE-endY);
            }
            startX = endX;
            startY = endY;
            UI.drawLine(0,this.ZERO_LINE-this.THRESHOLD,Double.POSITIVE_INFINITY,this.ZERO_LINE-this.THRESHOLD);
            UI.drawLine(0,this.ZERO_LINE+this.THRESHOLD,Double.POSITIVE_INFINITY,this.ZERO_LINE+this.THRESHOLD);
        }
        */
    }

    /**
     * [COMPLETION]
     * Plots the peaks with small green circles. 
     *    A peak is defined as a value that is greater or equals to both its
     *    neighbouring values.
     * Note the size of the circle is in the constant SIZE_CIRCLE
     * You may assume that peaks values differ from their neighbouring points.
     */
    public void highlightPeaks() {
        this.displayDistortion(); //use display if displayDistortion isn't complete
        /*# YOUR CODE HERE */
        double oldValue = 0;
        UI.setColor(Color.green);
        for(int i = 0;i<this.waveform.size()-1;i++){
            /*#Add a condition of peaks and draw a oval in that point*/
            if(this.waveform.get(i) >= oldValue && this.waveform.get(i) >= this.waveform.get(i+1)){
                UI.drawOval(i+1-this.SIZE_CIRCLE/2,this.ZERO_LINE- this.waveform.get(i) -this.SIZE_CIRCLE/2,this.SIZE_CIRCLE,this.SIZE_CIRCLE);
            }
            oldValue = this.waveform.get(i);
        }
    }

    /**
     * [COMPLETION]
     * Finds the largest value (positive or negative) in the waveform, and
     * "normalises" all the values - multiplies them by threshold/maximum - so
     * that the largest value is now equal to the distortion threshold.
     * Then redraws the waveform.
     */
    public void normalise() {
        /*# YOUR CODE HERE */
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        /*#Caculate the max value (absolutely)*/
        double maxWave = Double.NEGATIVE_INFINITY;
        for(double wave:this.waveform){
            if(Math.abs(wave)>maxWave){
                maxWave = Math.abs(wave);
            }
        }
        /*# Change the value of each data*/
        for(int i = 0;i<=this.waveform.size()-1;i++){
            this.waveform.set(i, this.THRESHOLD*this.waveform.get(i)/maxWave);
        }
        this.displayDistortion();
    }

    /**
     * [CHALLENGE]
     * Displays the upper envelope with GREEN lines connecting all the peaks.
     *    A peak is defined as a point that is greater or equal to *both* neighbouring points.
     */
    public void doEnvelope(){
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        this.display();  // display the waveform,
        
        /*# YOUR CODE HERE */
        ArrayList<Double> peakY = new ArrayList<Double>();
        ArrayList<Integer> peakX = new ArrayList<Integer>();
        UI.setColor(Color.green);
        double oldValue = 0;
        UI.setColor(Color.green);
        for(int i = 0;i<this.waveform.size()-1;i++){
            /*#Add a condition of peaks and draw a oval in that point*/
            if(this.waveform.get(i) >= oldValue && this.waveform.get(i) >= this.waveform.get(i+1)){
                //UI.drawOval(i+1-this.SIZE_CIRCLE/2,this.ZERO_LINE- this.waveform.get(i) -this.SIZE_CIRCLE/2,this.SIZE_CIRCLE,this.SIZE_CIRCLE);
                peakY.add(this.waveform.get(i));
                peakX.add(i);
            }
            oldValue = this.waveform.get(i);
        }
        for(int i = 0;i<peakY.size()-1;i++){
            UI.drawLine(peakX.get(i),this.ZERO_LINE-peakY.get(i),peakX.get(i+1),this.ZERO_LINE-peakY.get(i+1));
        }
        /*#The old code but it's too long and defined many variables so I changed the code.*/
        /*int index = 0;
        double a = 0;
        double b,c;
        double X = 0;
        for(int i = 0;i<this.waveform.size()-1;i++){
            X++;
            b = this.waveform.get(i);
            if(i < this.waveform.size()-1){
                c = this.waveform.get(i+1);
            }else{
                c = 0;
            }
            if(b>=a&&b>=c){
                UI.drawOval(X-this.SIZE_CIRCLE/2,this.ZERO_LINE-b-this.SIZE_CIRCLE/2,this.SIZE_CIRCLE,this.SIZE_CIRCLE);
                peakY.add(b);
                peakX.add(i);
            }
            a = b;
        }*/
    }

    /**
     * [CHALLENGE]
     * Saves the current waveform values into a file
     */
    public void save(){
        /*# YOUR CODE HERE */
        try{
            /*#Just let user to choose a file and save the waveform into it*/
            PrintStream waveOut = new PrintStream(UIFileChooser.open());
            for(double wave:this.waveform){
                waveOut.println(wave);
            }
            waveOut.close();
        }catch(IOException e){UI.println("File read failed :" + e);}
    }

    private int index1;
    /**
     * [CHALLENGE]
     * Lets user select a region of the waveform with the mouse
     * and deletes that section of the waveform.
     */
    double startX,startY;
    double endX = 0,endY = 0;
    public void doMouse(String action, double x, double y){
        /*# YOUR CODE HERE */
        /*# If this arrayList is null than do nothing*/
        if (this.waveform != null){ 
            /*# When user clicked the mouse first , record the start Position*/
            if(action.equals("pressed")){
                startX = x;
                startY = y;
            }
            /*# When the mouse moved than record the position every time*/
            if(action.equals("moved")){
                endX = x;
                endY = y;
            }
            /*# When the mouse dragged than draw a rectangular to let user know the region*/
            if(action.equals("dragged")){
               UI.invertRect(startX,startY,endX-startX,endY-startY);
               endX = x;
               endY = y;
               UI.invertRect(startX,startY,endX-startX,endY-startY);
            }
            /*# When user released the mouse delete the value in the region and print some messages
             * to let user konw which data were deleted and display the new waveform*/
            if(action.equals("released")){
                int j = 0,num = 1;
                UI.println("");
                UI.println("//////////////////////////////////////////");
                UI.println("");
                for(int i = 0;i<this.waveform.size()-1;i++){
                    if(i>=startX&&i<=endX&&startY <= this.ZERO_LINE-this.waveform.get(i)&&endY >= this.ZERO_LINE-this.waveform.get(i)){
                        UI.println("############################");
                        UI.println("("+num+")");
                        UI.println("You just deleted the "+ j +"th value in this wave");
                        UI.println("    And the value of it is " + this.waveform.get(i));
                        this.waveform.remove(i);
                        i--;
                        num++;
                    }
                    j++;
                }
                if(num == 1){
                    UI.println("You have deleted nothing!");
                }
                this.display();
            }
        }
    }
    // Main
    public static void main(String[] arguments){
        new WaveformAnalyser();
    }   

}
