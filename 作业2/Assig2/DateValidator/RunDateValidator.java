// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2018T2
 * Name:Eric
 * Username:xmut_1712409237
 * ID:1712409237
 */

import ecs100.*;

/** Run DateValidator methods */

public class RunDateValidator {

    public static void main(String[] args){
        DateValidator dv = new DateValidator();
        UI.initialise();
        UI.addButton("Clear", UI::clearText );
        UI.addButton("Validate Date Core", dv::doCore );
        UI.addButton("Validate Date Completion", dv::doCompletion );
         UI.addButton("Validate Date Challenge", dv::doChallenge );
        UI.addButton("Quit", UI::quit );
        UI.setDivider(1);       // Expand the text area
    }
}
