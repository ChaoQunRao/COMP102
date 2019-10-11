// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2018T2
 * Name:Eric
 * Username:xmut_1712409237
 * ID:1712409237
 */

import ecs100.*;

public class RunImageRenderer{
    public static void main(String[] args){
        ImageRenderer ir = new ImageRenderer();
        UI.initialise();
        UI.addButton("Clear", UI::clearGraphics );
        UI.addButton("Render (core)", ir::renderImageCore );
        UI.addButton("Render (compl)", ir::renderAnimatedImage );
        UI.addButton("Render (chanlenge)", ir::Challenge );
        UI.addButton("Quit", UI::quit );
        UI.setWindowSize(850, 700);
        UI.setDivider(0.0);
    }
}
