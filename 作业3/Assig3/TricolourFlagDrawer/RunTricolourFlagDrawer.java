// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2018T2
 * Name:Eric
 * Username:xmut_1712409237
 * ID:1712409237
 */

import ecs100.*;

/** Run TricolourFlagDrawer methods */
public class RunTricolourFlagDrawer{

    public static void main(String[] arguments){
        TricolourFlagDrawer tfd = new TricolourFlagDrawer ();
        UI.initialise();
        UI.addButton("Clear", UI::clearPanes );
        UI.addButton("Core", tfd::doCore );
        UI.addButton("Completion", tfd::doCompletion );
        UI.addButton("Challenge", tfd::doChallenge );
        UI.addButton("Quit", UI::quit );
    }
}
