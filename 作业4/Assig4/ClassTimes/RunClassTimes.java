// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2018T2
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;

public class RunClassTimes {

    public static void main(String[] args){
        ClassTimes ct = new ClassTimes();
        // Methods for the core
        UI.addButton("Print All", ct::printAll);
        UI.addButton("Course", ct::doPrintCourse );
        UI.addButton("Room", ct::doPrintRoom );
        UI.addButton("StartTime", ct::doPrintAtStartTime) ;

        //Methods for the completion
        UI.addButton("Rooms On Day", ct::doPrintInRoomsOnDay );
        UI.addButton("Room Booking File", ct::doBookingsFileForRoom );
        UI.addButton("In Building After", ct::doInBuildingAfterTime );

        //Methods for the challenge
        UI.addButton("Mean Class Length", ct::doMeanClassLength );
        UI.addButton("Potential Disruptions", ct::doPotentialDisruptions );

        UI.setDivider(1.0);
        UI.addButton("Quit", UI::quit);
    }
}
