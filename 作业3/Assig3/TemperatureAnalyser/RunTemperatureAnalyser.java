// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2018T2
 * Name:Eric
 * Username:xmut_1712409237
 * ID:1712409237
 */

import ecs100.*;

public class RunTemperatureAnalyser{

    public static void main(String[] arguments) {
        TemperatureAnalyser ta = new TemperatureAnalyser();
        UI.initialise();
        UI.addButton("Analyse", ta::analyse );
        UI.addButton("Quit", UI::quit );
    }
}
