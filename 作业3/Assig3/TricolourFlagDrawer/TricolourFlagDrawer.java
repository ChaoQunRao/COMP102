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
import javax.swing.JColorChooser;

/** TricolourFlagDrawer: draws a series of tricolour flags */
public class TricolourFlagDrawer{

    public static final double WIDTH = 200;
    public static final double HEIGHT = 133;

    /**   CORE
     * asks user for a position and three colours, then calls the 
     * drawThreeColourFlagCore method, passing the appropriate arguments
     */
    public void doCore(){
        double left = UI.askDouble("left of flag");
        double top = UI.askDouble("top of flag");
        UI.println("Now choose the colours");
        Color stripe1 = JColorChooser.showDialog(null, "First Stripe", Color.white);
        Color stripe2 = JColorChooser.showDialog(null, "Second Stripe", Color.white);
        Color stripe3 = JColorChooser.showDialog(null, "Third Stripe", Color.white);
        this.drawThreeColourFlagCore(left, top, stripe1, stripe2, stripe3 );
    }

    /**   CORE
     * draws a three colour flag consisting of three vertical equal-width
     * stripes at the given position
     */
    public void drawThreeColourFlagCore(/*# YOUR CODE HERE */double x,double y,Color c1,Color c2,Color c3){
        /*# YOUR CODE HERE */
        UI.setColor(c1);
        UI.fillRect(x,y,HEIGHT,WIDTH);
        UI.setColor(c2);
        UI.fillRect(x+HEIGHT,y,HEIGHT,WIDTH);
        UI.setColor(c3);
        UI.fillRect(x+2*HEIGHT,y,HEIGHT,WIDTH);       
    }

    /**   COMPLETION
     * draws multiple flag made up of three equal size stripes by calling the
     * drawThreeColourFlagCompletion method, passing the appropriate arguments
     */
    public void doCompletion(){
        this.drawThreeColourFlagCompletion(true, 20, 50, Color.black, Color.yellow, Color.red);               // Belgium
        this.drawThreeColourFlagCompletion(false, 250, 100, Color.black, Color.red, Color.yellow);            // Germany
        this.drawThreeColourFlagCompletion(true, 140, 430, Color.blue, Color.white, Color.red);               // France
        this.drawThreeColourFlagCompletion(true, 290, 270, Color.red, Color.yellow, Color.green.darker());    // Guinea
        this.drawThreeColourFlagCompletion(false, 470, 30, Color.red, Color.white, Color.blue);               // The Netherlands
        this.drawThreeColourFlagCompletion(false, 50, 250, Color.white, Color.blue, Color.red);               // Russia
    }

    /**   COMPLETION
     * draws a three colour flag consisting of three equal-size stripes
     * at the given position
     * The stripes can be either vertical or horizontal
     */
    public void drawThreeColourFlagCompletion(/*# YOUR CODE HERE */boolean bol,double x,double y,Color c1,Color c2,Color c3){
        /*# YOUR CODE HERE */
        double height = HEIGHT/2;
        double width = WIDTH/2;
        if(bol){
           UI.setColor(c1);
           UI.fillRect(x,y,height,width);
           UI.setColor(c2);
           UI.fillRect(x+height,y,height,width);
           UI.setColor(c3);
           UI.fillRect(x+2*height,y,height,width);
           UI.setColor(Color.BLACK);
           UI.drawRect(x,y,3*height,width);
        }
        else{ 
           UI.setColor(c1);
           UI.fillRect(x,y,3*height,width/3);
           UI.setColor(c2);
           UI.fillRect(x,y+width/3,3*height,width/3);
           UI.setColor(c3);
           UI.fillRect(x,y+width/3,3*height,width/3); 
           UI.setColor(Color.BLACK); 
           UI.drawRect(x,y,3*height,width);
        }
    }
    public void doChallenge(){
        this.drawThreeColourFlagChallenge(100,50);
    }
    /**
     *At first, I wanted to imitate the above method, 
     *but I felt that if nine parameters were passed in nine lines of code would be needed, 
     *so I deleted most of the parameters in the method and kept only the x and y coordinates.
     *Then I just need to create an array of these parameters and use loops to help me pass in the corresponding parameters.
    */
    public void drawThreeColourFlagChallenge(double x,double y){
        String name[] = {"France","Italy","Austria","Germany","Guinea","Russia","The Netherlands","Roman Republic","Moresnet"};
        Boolean bol[] = {true,true,false,false,true,false,false,true,false};
        Color c1[] = {Color.blue,Color.green,Color.yellow,Color.black,Color.red,Color.white,Color.red,Color.black,Color.black};
        Color c2[] = {Color.white,Color.white,Color.red,Color.red,Color.yellow,Color.blue,Color.white,Color.white,Color.white};
        Color c3[] = {Color.red,Color.red,Color.white,Color.yellow,Color.green.darker(),Color.red,Color.blue,Color.red,Color.blue};
        double height = HEIGHT/2;
        double width = WIDTH/2;
        double x0 = x;
        for(int i = 0;i<9;i++){
            if(i%3 == 0){
                y = y + 1.5*width;
                x = x0;
            }
            this.drawThreeColourFlagCompletion(bol[i],x,y,c1[i],c2[i],c3[i]);
            UI.drawString(name[i],x+height,y+1.2*width);
            x = x + 4*height;
        }
    }
}
