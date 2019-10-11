// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2018T2
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
/**
 * Draws various symbols: flags, signs, and car logos
 *
 * You can find lots of flag details (including the correct dimensions and colours)
 * from  http://www.crwflags.com/fotw/flags/    
 */
public class SymbolDrawer{

    /**   CORE
     * Draw the flag of France.
     * The flag has three vertical stripes;
     * The left is blue, the right is red and the middle is white.
     * The flag is 2/3 as high as it is wide (ratio 2:3).
     */
    public void drawFranceFlag(double left, double top, double width){
        /*# YOUR CODE HERE */
           UI.setLineWidth(1);
           double w = width;
           UI.setColor(Color.black);
           UI.drawRect(left,top,w,2*w/3);
           Color red = new Color(190,0,39);
           Color blue = new Color(59,90,163);
           UI.setColor(blue);
           UI.fillRect(left,top,w/3,2*w/3);
           UI.setColor(red);
           UI.fillRect(2*w/3+left,top,w/3,2*w/3);
    }

    /**   CORE
     * Draw the hospital sign - a blue square with a big white centred H.
     * The H is made of 3 rectangular strips
     */
    public void drawHospitalSign(double left, double top, double size) {
        /*# YOUR CODE HERE */
        UI.setLineWidth(1);
        double s = size;
        Color blue = new Color(0,0,255);
        UI.drawRect(left,top,s,s);
        UI.setColor(blue);
        UI.fillRect(left,top,s,s);
        UI.setColor(Color.white);
        UI.fillRect(left+s/4,top+s/5,s*2/15,s*3/5);
        UI.fillRect(left+s/4+s/2-s*2/15,top+s/5,s*2/15,s*3/5);
        UI.fillRect(left+s/4+s*2/15,top+s/5+s*3/10-s/15,s/2-s*4/15,s*2/15);
    } 

    /**   CORE
     * Draw the flag of Laos.
     * The flag has three horizontal stripes with a white circle in the centre;
     * See the assignment for the dimensions.
     */
    public void drawLaosFlag(double left, double top, double width) {
        /*# YOUR CODE HERE */
        UI.setLineWidth(1);
        double w = width;
        double h = width*2/3;
        Color blue = new Color(0,0,153);
        Color red = new Color(255,0,0);
        UI.drawRect(left,top,w,h);
        UI.setColor(red);
        UI.fillRect(left,top,w,h/4);
        UI.fillRect(left,top+h*3/4,w,h/4);
        UI.setColor(blue);
        UI.fillRect(left,top+h/4,w,h/2);
        UI.setColor(Color.white);
        UI.fillOval(left+w/2-h/5,top+h*3/10,h*2/5,h*2/5);
        
    }

    /**   COMPLETION
     * Draw the flag of the United Arab Emirates.
     * The flag has a vertical red stripe on the left, and
     * three horizontal stripes (green, white, black) on the right.
     * See the assignment for dimensions and details.
     */
    public void drawUaeFlag(double left, double top, double width) {
        /*# YOUR CODE HERE */
        UI.setLineWidth(1);
        double l = width;
        double w = width/2;
        double redl = width/4;
        Color red = new Color(255,0,0);
        Color green = new Color(0,153,0);
        UI.setColor(Color.black);
        UI.drawRect(left,top,l,w);
        UI.fillRect(left+redl,top+w*2/3,l-redl,w/3);
        UI.setColor(red);
        UI.fillRect(left,top,redl,w);
        UI.setColor(green);
        UI.fillRect(left+redl,top,l-redl,w/3);
    }

    /**   COMPLETION
     * Draw the flag of Greenland.
     * The top half of the flag is white, and the bottom half is red.
     * There is a circle in the middle (off-set to left)  which is
     * also half white/red but on the opposite sides.
     * See the assignment for dimensions
     */
    public void drawGreenlandFlag(double left, double top, double width) {
        /*# YOUR CODE HERE */
        UI.setLineWidth(1);
        double l = width;
        double w = l*2/3;
        double o = l/6;
        double t = l/8;
        double r = l*5/12;
        UI.setColor(Color.black);
        UI.drawRect(left,top,l,w);
        UI.setColor(Color.red);
        UI.fillRect(left,top+w/2,l,w/2);
        UI.setColor(Color.white);
        UI.fillArc(left+o,top+t,r,r,180,180);
        UI.setColor(Color.red);
        UI.fillArc(left+o,top+t,r,r,0,180);
    }

    /**   CHALLENGE
     * Draw the Misubishi Logo.
     */
    public void drawMitsubishiLogo(double left, double top, double size) {
        /*# YOUR CODE HERE */
        UI.setLineWidth(1);
        double p = size/7;
        double q = 2.5*p;
        double u = 0.25*p;
        double w = Math.sqrt(3);
        double []x1 = {left+q+p,left+q,left+q+p,left+2*p+q};
        double []y1 = {top+u,top+u+p*w,top+u+2*p*w,top+u+p*w};
        
        double []x2 = {left+q+p,left+q-p,left+q-2*p,left+q};
        double []y2 = {top+u+2*p*w,top+u+2*p*w,top+u+3*w*p,top+u+3*w*p};
        
        double []x3 = {left+q+p,left+q+3*p,left+q+4*p,left+q+2*p};
        double []y3 = {top+u+2*p*w,top+u+2*p*w,top+u+3*w*p,top+u+3*w*p};
        
        UI.setColor(Color.black);
        UI.drawRect(left,top,7*p,6*p);
        UI.setColor(Color.red);
        UI.fillPolygon(x1,y1,4);
        UI.fillPolygon(x2,y2,4);
        UI.fillPolygon(x3,y3,4);
    }

    /**   CHALLENGE
     * Draw the Koru Flag.
     * It was one of the new flag designs for the 2016 referendum,
     * designed by Sven Baker from Wellington
     * The flag is 1/2 as high as it is wide (ratio 1:2).
     */
    public void drawKoruFlag(double left, double top, double width) {
        /*# YOUR CODE HERE */
        UI.setLineWidth(1);
        Color lightred = new Color(234,27,35);
        Color navyblue = new Color(27,50,118);
        
        double l = width;
        double w = width/2;
        double rb = 0.38*l;
        double r1 = 0.55*l;
        double r2 = 0.42*l;
        double r3 = 0.17*l;
        
        UI.setColor(lightred);
        UI.fillRect(left,top,rb,w);
        UI.setColor(navyblue);
        UI.fillRect(left+0.62*l,top,rb,w);
        
        UI.setColor(Color.white);
        UI.fillOval(left+0.225*l,top-0.025*l,r1,r1);
        
        UI.setColor(navyblue);
        UI.fillOval(left+0.29*l,top+0.04*l,r2,r2);
        
        UI.setColor(navyblue);
        UI.setLineWidth(0.025*l);
        UI.drawArc(left+0.58*l,top+0.1525*l,0.2*l,0.195*l,180,180);
        
        UI.setColor(Color.white);
        UI.fillOval(left+0.605*l,top+0.165*l,r3,r3);
       
    }


}
