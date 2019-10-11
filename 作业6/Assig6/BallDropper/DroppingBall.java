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

/** DroppingBall represents a ball that falls towards the ground.
 *    Each time the step() method is called, it will take one step.  
 * For the Challenge part, the ball bounces when it reaches the ground.
 */

public class DroppingBall{
    // Fields to store
    //   the state of the ball:  x, height, stepX, stepY, colour
    //   other constants for the ball: size, position of the ground
    /*# YOUR CODE HERE */
    //Used to record the coordinates of the ball
    private double xPos;
    private double yPos;
    private double height;
    //Used to note the horizontal and vertical velocity of the ball
    private double stepX;
    private double stepY;    
    public static final double acc = 0.10;  //Acceleration of the ball
    Color c;    //The random color of the ball
    public static final double dt = 1;     //The unit of time in this ball program(just for challedge)
    public static final int GROUND = 450;   // ground level.
    public static final int SIZE = 30;      // diameter of the ball

    // Constructor
    /** Construct a new DroppingBall object.
     *  Parameters are the initial x position, the height above the ground,
     *  the initial speed to the right (ie, the horizontal step size), and 
     *  the colour.
     *  Stores the parameters into fields (computing the y position from the height)
     *  and initialises the other fields,
     */
    public DroppingBall(double x, double h, double s, Color c){
        /*# YOUR CODE HERE */
        //Initializes the basic properties of the ball
        this.xPos = x;
        this.height = h-this.SIZE;
        this.yPos = this.GROUND - this.height-this.SIZE;
        this.stepX = s;
        this.stepY = 0;
        this.c = c;
    } 

    // Methods
    /** Move the ball one step.
     *  Changes its height and x position using the vertical and horizonal steps
     *  If it would go below ground, then change its y position to ground level
     */
    public void step(){
        /*# YOUR CODE HERE */
        //Erase the old ball
        UI.eraseOval(this.xPos,this.yPos,this.SIZE,this.SIZE);
        //Set the new position of the ball
        this.xPos = this.xPos+this.stepX;
        this.yPos = this.yPos+this.stepY;
        //Let the ball accelerate
        this.stepY += acc;
        //Draw a new ball
        UI.fillOval(this.xPos,this.yPos,this.SIZE,this.SIZE);
    }
    /**I want to make a special explanation for this method.
      In fact, the Challenger and other parts share the above method method at the beginning, 
      but when I try to run it, I found that the rebound height of the ball will increase a little 
      every time after a period of time, which does not conform to the law of daily life. In theory, 
      the bounce height of each ball should be the same as the last time.Then, I carefully checked all possible bug spots
      , and finally I still didn't find out which program was out of order, but the next day I figured out why this happened.
      In Java, our time unit is "UI.sleep (time);".The actual parameters passed, such as UI. sleep (10), if I add this line of code 
      every time I update the ball position and speed, the program will stop for 0.01 seconds, but in fact the acceleration 
      and position of the ball are changing at any time, which results in the acceleration of the ball actually falling less 
      than the original acceleration, then it takes more time to reach the lower end, and then the landing speed is too 
      high due to the time increase. And the time for the speed to decrease to zero will not change as it rises, 
      but it has traveled a greater distance ( because acceleration does not actually act on the ball at all times ). 
      So in the end, my ball will play higher and higher, even if we only set the time to accelerate the ball 
      for 0.01 seconds, once the ball passes about 10 seconds, its speed and maximum height will increase 
      significantly. I didn't think of a way to make the ball in this program act on acceleration all the 
      time as in real life, but I came up with a compromise, which is to make the unit time in the program 
      approximate to real life as much as possible. I split 0.01 second into 10 0.001 seconds and accelerated 
      the ball every 0.001 second ( I tried a smaller time, but the computer couldn't react,
      so the picture came out very smoothly ) and update its position according to its new speed, 
      and then, in order to prevent the program from not judging or not judging whether the ball is 
      already on the floor when it hits the floor, I put the sentence of judging whether the ball hits the 
      floor or the wall into the loop ( if not, the ball will often fall some distance below the floor 
      and then bounce back ). Finally, I finished this method. In fact, I don't quite agree with this method, 
      because obviously this method will make my computer work very hard. If you have any better solution, 
      you can inform me through the feedback of your work, thank you!
     */
    public void stepForChalledge(){
        double time = 0;
        double smallerDt = 1;
        UI.eraseOval(this.xPos,this.yPos,this.SIZE,this.SIZE);
        while(time<this.dt){
           this.height = this.GROUND-this.yPos-this.SIZE;
           if (this.getHeight()<= 0) {
                this.setStepY();
           }
           if (this.getX() >= 570||this.getX() <= 0) {
                this.setStepX();
           }
           
           this.xPos = this.xPos+this.stepX*(smallerDt/this.dt);
           this.yPos = this.yPos+this.stepY*(smallerDt/this.dt);
           this.stepY += acc*(smallerDt/this.dt);
           
           time = time + (smallerDt/this.dt);
           UI.sleep(smallerDt);
        }
        UI.fillOval(this.xPos,this.yPos,this.SIZE,this.SIZE);
    }
    /** Return the height of the ball above the ground */
    public double getHeight(){
        /*# YOUR CODE HERE */
        this.height = this.GROUND-this.yPos-this.SIZE;
        return this.height;
    }
    public double getX(){
        /*# YOUR CODE HERE */
        return this.xPos;
    }   
    public void setStepY(){
        this.stepY = -this.stepY;
    }
    public void setStepX(){
        this.stepX = -this.stepX;
    }
    /** Draw the ball on the Graphics Pane in its current position */
    public void draw(){
        /*# YOUR CODE HERE */
        UI.setColor(this.c);
        UI.fillOval(this.xPos,this.yPos,this.SIZE,this.SIZE);
    }
}
