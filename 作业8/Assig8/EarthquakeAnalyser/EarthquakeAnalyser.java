// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2018T2
 * Name:Eric
 * Username:xmut_1712409237
 * ID:1712409237
 */

import ecs100.*;
import java.util.*;
import java.io.*;

/**
 * EarthquakeAnalyser
 * Analyses data about a collection of 4335 NZ earthquakes from May 2016 to May 2017
 * Each line of the file "earthquake-data.txt" has a description of one earthquake:
 *   ID time longitude  latitude magnitude depth region
 * Data is from http://quakesearch.geonet.org.nz/
 *  Note bigearthquake-data.txt has just the 421 earthquakes of magnitude 4.0 and above
 *   which may be useful for testing, since it is not as big as the full file.
 * 
 * Core:  two methods:
 *   loadData
 *      Loads the data from a file into a field containing an
 *      ArrayList of Earthquake objects.
 *      Hint : to make an Earthquake object, read one line from the file
 *              and pass it as the argument to the Earthquake constructor
 *             Make sure any previous values in the field are removed
 *   findBigOnes
 *      Lists all the earthquakes in the ArrayList that have a magnitude 5.5 and over.
 *      Hint: see the methods in the Earthquake class, especially getMagnitude and toString
 *   
 * Completion: one method:
 *   findPairs
 *  Compares each Earthquake in the list with every other Earthquake
 *      and finds every pair of "close" earthquakes - earthquakes that
 *        - are within 1km of each other, and
 *        - have depths within 1km of each other, and
 *        - are separated by at least 24 hours days 
 *      For each pair, prints
 *        - their ID's,
 *        - the distance between them
 *        - the depth difference
 *        - the number of days between them.

 * Challenge: one method
 *  findFollowOns;
 *      Constructs a new ArrayList containing every earthquake with a magnitude that is at least 6.0
 *      For each such earthquake on this list
 *       - finds a list of all the "follow-on" earthquakes:
 *         later earthquakes within a distance of 10km and depth difference <= 10km.
 *       - If there are at least two follow-on earthquakes, then it prints out
 *          the full details of the big earthquake followed by
 *          ID, magnitude and days since the big one for each follow-on earthquake
 *
 * The file "example-output.txt" has sample output for the "bigearthquake-data.txt"
 *   file, which only contains earthquakes with magnitude 4 and above.
 */

public class EarthquakeAnalyser{
    
    private ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();

    /** Construct a new EarthquakeAnalyser object and initialise the interface */
    public EarthquakeAnalyser(){
        this.setupGUI();
    }

    public void setupGUI(){
        UI.initialise();
        UI.addButton("Load", this::loadData);
        UI.addButton("Big ones",  this::findBigOnes);
        UI.addButton("Pairs", this::findPairs);
        UI.addButton("Follow-ons", this::findFollowOns);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1.0);  //text pane only 
    }

    /*
     * Load data from the data file into the earthquakes field
     */
    public void loadData(){
        String filename = UIFileChooser.open("Data File");
        /*# YOUR CODE HERE */
        try{
            /*# Read the file and send each line into the constructor to create a new Earthquake objects
               And add them into a arrayList*/
            Scanner sc = new Scanner(new File(filename));
            while(sc.hasNext()){
                this.earthquakes.add(new Earthquake(sc.nextLine()));
            }
        }catch(IOException e){UI.println("File read failed: "+ e);}
        UI.printf("Loaded %d earthquakes into list\n", this.earthquakes.size());
        UI.println("----------------------------");
    }

    /**
     * Print details of all earthquakes with a magnitude of 5.5 or more
     */
    public void findBigOnes(){
        UI.println("Earthquakes 5.5 and above");
        /*# YOUR CODE HERE */
        for(Earthquake EQ:this.earthquakes){
            if(EQ.getMagnitude()>=5.5){
                /*# Use the toString mothod to print the message of each earthquake*/
                UI.println(EQ.toString());
            }
        }
        UI.println("------------------------");
    }

    /**
     * Print all pairs of earthquakes within 1km of each other and within 1km depth from each other
     * and separated by at least 1 day;
     */
    public void findPairs(){
        UI.println("Close earthquakes");
        /*# YOUR CODE HERE */
        int num = 1;
        for(int i = 0;i<this.earthquakes.size();i++){
            for(int j = i+1;j<this.earthquakes.size();j++){
                /*# There are three conditions to find the pairs(depth distance time)*/
                if(Math.abs(this.earthquakes.get(i).getDepth()-this.earthquakes.get(j).getDepth())<=1
                &&this.earthquakes.get(i).distanceTo(this.earthquakes.get(j))<=1
                &&this.earthquakes.get(i).timeBetween(this.earthquakes.get(j))/1440>=1
                ){
                    UI.println("("+num+")");
                    UI.println("Pair between "+this.earthquakes.get(i).getID()+" and " + this.earthquakes.get(j).getID());
                    UI.println("Depth : " + Math.abs(this.earthquakes.get(i).getDepth()-this.earthquakes.get(j).getDepth()));
                    UI.println("Distance : "+this.earthquakes.get(i).distanceTo(this.earthquakes.get(j)));
                    UI.println("Days"+ (int)this.earthquakes.get(i).timeBetween(this.earthquakes.get(j))/(24*60));
                    num++;
                }
            }
        }
        UI.println("----------------------------");
    }

    /**
     * Constructs a new ArrayList containing every earthquake with a magnitude that is at least 6 
     * For each earthquake on this list
     * - finds a list of all the "follow-on" earthquakes:
     *   later earthquakes within a distance of 10km and depth difference <= 10km.
     * - If there are at least two follow-on earthquakes, then it prints out
     *     the full details of the big earthquake followed by
     *    ID, magnitude and days since the big one for each follow-on earthquake
     */

    public void findFollowOns(){
        UI.println("Big earthquakes and their follow-on earthquakes");
        /*# YOUR CODE HERE */
        /*# Create two arraylist to save the big earthquake and follow earthquake*/
        ArrayList<Earthquake> bigEarthquake = new ArrayList<Earthquake>();
        ArrayList<Earthquake> followOn = new ArrayList<Earthquake>();
        /*# Try to find the big earthquakes and add them into an arraylist*/
        for(int i = 0;i<this.earthquakes.size();i++){
            if(this.earthquakes.get(i).getMagnitude()>=6){
                bigEarthquake.add(this.earthquakes.get(i));
            }
        }
        for(int i = 0;i<bigEarthquake.size();i++){
            /*# Sum is used to caculate how many follow earthquakes*/
            int sum = 0;
            /*# Try to find the follow earthquakes of big earthquakes add them into another arraylist*/
            for(int j = 0;j<this.earthquakes.size();j++){
                if(bigEarthquake.get(i).timeBetween(this.earthquakes.get(j))>0
                &&this.earthquakes.get(j).distanceTo(bigEarthquake.get(i))<=10
                &&Math.abs(bigEarthquake.get(i).getDepth()-this.earthquakes.get(j).getDepth())<=10
                ){
                    sum++;
                    followOn.add(this.earthquakes.get(j));
                }
            }
            /*# If there are more than two follow earthquakes then print them */
            if(sum>=2){
                UI.println(bigEarthquake.get(i).toString());
                for(Earthquake EQ:followOn){
                    UI.println(EQ.getID() + "mag " + EQ.getMagnitude() + " Days later "  + (int)bigEarthquake.get(i).timeBetween(EQ)/1440);
                }
                UI.println("------------------------");
            }
            /*# Clear the followOn arraylist*/
            followOn.clear();
        }
        UI.println("-------------------------------------");
    }

    public static void main(String[] arguments){
        EarthquakeAnalyser obj = new EarthquakeAnalyser();
    }   

}
