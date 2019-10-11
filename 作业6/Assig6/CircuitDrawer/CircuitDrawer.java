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
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class CircuitDrawer{

    // fields to store data:
    //  - the tool that the user has selected (which control what component
    //     will be drawn by the mouse)
    //    The tools are "resistor", "capacitor", "source", "wire", "label", or "eraser"
    //  - the mode: whether the component should be horizontal or vertical
    //  - the contents of the label
    //  - the position the mouse was pressed, 
    /*# YOUR CODE HERE *///tools are used to know what button is pressed by user.And dir is used to 
    //know the dirertion,WIDTH and HEIGHT is the size about all image and the oval.
    private String tool = "Notring";    
    private String label;
    private String dir = "horizontal";
    boolean direction= false;
    private double startX,startY,x,y;
    private double eraseWidth = 25;
    private final double WIDTH = 25;
    private final double HEIGHT = 25;
    /**I think put those frame and buttons into field isn't a good idea but in order
       to change the color about our buttons, I can't find any other ways to solve it*/
    JFrame jf = UI.getFrame();
    ArrayList<JButton> button = new ArrayList<JButton>();
    JButton clear= this.addButton("Clear", UI::clearGraphics);
    JButton resistor = this.addButton("Resistor",this::doSetResistor);
    JButton capacrtor = this.addButton("Capacrtor",this::doSetCapacitor);
    JButton source = this.addButton("Source",this::doSetSource);
    JButton wire = this.addButton("Wire",this::doSetWire);
    JButton eraser = this.addButton("Eraser",this::doSetEraser);
    JButton switchDir = this.addButton("SwitchDirection",this::doSwitchDirection);
    JButton quit = this.addButton("Quit", UI::quit);  
    //JTextField lab = UI.addTextField("Label",this::doSetLabel); // the UI method don't return a textField so I can't do this.
    Color col = Color.getHSBColor((float)Math.random(),1,1);
    //Constructor
    public CircuitDrawer(){
        this.setupGUI();
    }
    public JButton addButton(String name,UIButtonListener controller){
        this.button.add(UI.addButton(name,controller));
        return this.button.get(this.button.size()-1);
    }
    public void onLoad(){
        col = Color.getHSBColor((float)Math.random(),1,1);
        for(JButton b : this.button){
            b.setBackground(Color.white);
            b.addMouseListener(new java.awt.event.MouseAdapter(){
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    col = Color.getHSBColor((float)Math.random(),1,1);
                    b.setBackground(col);
                }
            });
        }
    }
    /** Sets up the user interface - mouselistener, buttons, and (completion only) textField */
    public void setupGUI(){
        //To set up mouse monitoring, you can monitor mouse movement events and double-click events
        UI.setMouseMotionListener( this::doMouse );
        //UI.addButton("Clear", UI::clearGraphics); 
        /*# YOUR CODE HERE */
        /**I put all of them into field because of I need change their color....*/
        UI.addSlider("eraseWidth", 0, 51, 25,this::doSetEraseWidth);
        UI.addTextField("Label",this::doSetLabel);
        this.onLoad();
        this.switchDir.setBackground(Color.white);
        //UI.addButton("Quit", UI::quit);
        UI.setDivider(0.0);  // Hide the text area.
    }
    /**Change Color when button was clicked*/
    public void changeColor(){
       this.onLoad();
       if(this.tool.equals("Resistor")){
            this.resistor.setBackground(col);
       }
       if(this.tool.equals("Capacitor")){
           this.capacrtor.setBackground(col);
       }
       if(this.tool.equals("Source")){           
           this.onLoad();
           this.source.setBackground(col);
       }
       if(this.tool.equals("Wire")){
            this.wire.setBackground(col);
       }
       if(this.tool.equals("Eraser")){
           this.eraser.setBackground(col);
       }
    }
    // Methods to change the tool that controls what will be drawn next
    // These methods just save information to the fields.
    /**Respond to the setLineWidth */
    public void doSetEraseWidth(double width){
        this.eraseWidth = width;
    }
    /** Respond to the resistor button */
    public void doSetResistor(){
        /*# YOUR CODE HERE */
        this.tool = "Resistor";
        this.changeColor();
    }
    
    /** Respond to the capacitor button */
    public void doSetCapacitor(){
        /*# YOUR CODE HERE */
        this.tool = "Capacitor";
        this.changeColor();
    }

    /** Respond to the source button */
    public void doSetSource(){
        /*# YOUR CODE HERE */
        this.tool = "Source";
        this.changeColor();
    }

    /** Respond to the wire button */
    public void doSetWire(){
        /*# YOUR CODE HERE */
        this.tool = "Wire";
        this.changeColor();
    }

    /** Respond to the eraser button */
    public void doSetEraser(){
        /*# YOUR CODE HERE */
        this.tool = "Eraser";
        this.changeColor();
    }

    /** Respond to the text field (completion only) */
    public void doSetLabel(String v){
        /*# YOUR CODE HERE */
        this.label = v;
        this.tool = "label";
    }

    /**
     * Respond to the horiz/vert button (completion only)
     * Switches the mode from horizontal to vertical, or back
     * Ideally, (Challenge) it should show the user what the current state is,
     * eg by drawing a horizonal/vertical bar in the top left corner,
     * or by calling setText on the button to change the label
     */
    public void doSwitchDirection(){
        /*# YOUR CODE HERE */
        if(this.dir.equals("horizontal")){
            this.dir = "vertical";
            this.switchDir.setBackground(col);
            this.switchDir.setLabel(this.dir);
        }else{
            this.dir = "horizontal";
            this.switchDir.setBackground(col);
            this.switchDir.setLabel(this.dir);
        }
    }

    /**
     * Respond to mouse events
     * When pressed, remember the position.
     * When released, draw what is specified by current tool
     * Uses the value stored in the field to determine which kind of component to draw (or to erase)
     *  It should call the drawWire, drawResistor, drawCapacitor, drawSource, drawLabel, 
     *  or doErase, methods passing the x and y where the mouse was released.
     */
    public void doMouse(String action, double x, double y) {
        /*# YOUR CODE HERE */
        if(action.equals("pressed")){
           if(this.tool.equals("Resistor")){
               this.x = x;
               this.y = y;
               this.drawResistor(this.x,this.y);
           }
           if(this.tool.equals("Capacitor")){
               this.x = x;
               this.y = y;
               this.drawCapacitor(this.x,this.y);
           }
           if(this.tool.equals("Source")){
               this.x = x;
               this.y = y;
               this.drawSource(this.x,this.y);
           }
           if(this.tool.equals("Wire")){
               this.startX = x;
               this.startY = y;
           }
           if(this.tool.equals("Eraser")){
               this.x = x;
               this.y = y;
               UI.invertOval(this.x-eraseWidth/2,this.y-eraseWidth/2,this.eraseWidth,this.eraseWidth);
           }
           if(this.tool.equals("label")){
               this.x = x;
               this.y = y;
               this.drawLabel(this.x,this.y);
           }
        }       
        /**Test the event "released", but I don't need it in this assigment*/
        if(action.equals("released")){
            if(this.tool.equals("Eraser")){
               this.x = x;
               this.y = y;
               this.doErase(this.x,this.y);       
            }       
        }
        if(action.equals("moved")){
           if(this.tool.equals("Wire")||this.tool.equals("Eraser")){       
               this.x = x;
               this.y = y;
           }
        }
        if(action.equals("dragged")){
           if(this.tool.equals("Wire")){
              UI.invertLine(this.startX,this.startY,this.x,this.startY);
              UI.invertLine(this.x,this.startY,this.x,this.y);            
              this.x = x;
              this.y = y;
              this.drawWire(this.x,this.y);
           }
           if(this.tool.equals("Eraser")){
               UI.invertOval(this.x-eraseWidth/2,this.y-eraseWidth/2,this.eraseWidth,this.eraseWidth);
               this.x = x;
               this.y = y;
               this.doErase(this.x,this.y);
               UI.invertOval(x-eraseWidth/2,y-eraseWidth/2,eraseWidth,eraseWidth);
           }
        }   
          
        /**Test the event "clicked, but I on't need it in this assigment*/
        /*if(action.equals("clicked")){
              if(this.tool.equals("Eraser")){
                  this.x = x;       
                  this.y = y;
                  this.doErase(this.x,this.y);
              }
        }*/    
    }
    /**A method for add two lines for the element*/
    public void drawLine(double stub){        
       UI.setLineWidth(2);
       if(dir.equals("horizontal")){
            UI.drawLine(this.x-this.WIDTH/2,this.y-1,this.x-this.WIDTH/2-stub,this.y-1);
            UI.drawLine(this.x+this.WIDTH/2,this.y-1,this.x+this.WIDTH/2+stub,this.y-1);
       }else{
           UI.drawLine(this.x-1,this.y-this.HEIGHT/2,this.x-1,this.y-this.HEIGHT/2-stub);
           UI.drawLine(this.x-1,this.y+this.HEIGHT/2,this.x-1,this.y+this.HEIGHT/2+stub);
       }
    }
    /**
     * Draw a resistor centered at the point x, y.
     * (either a thin rectangle with short wires, or a zig-zag.)
     * Core: only horizontal required
     * Completion: horizontal or vertical depending on the mode.
     */
    public void drawResistor(double x, double y){
        double length = 31;    // size in the longer  dimension
        double width = 11;     // size in the shorter dimension 
        double stub = 10;      // the length of the wires on each end
        /*# YOUR CODE HERE */
        if(dir.equals("horizontal")){
            UI.drawImage(tool+"-"+this.dir+".png",this.x-length/2,this.y-width/2,length,width);
        }else{
            UI.drawImage(tool+"-"+this.dir+".png",this.x-width/2,this.y-length/2,width,length);
        }
        this.drawLine(stub);
    }

    /**
     * Draw a capacitor centered at the point x, y.
     *  (Two parallel lines with wires on each side)
     * Core: only horizontal required
     * Completion: horizontal or a vertical, depending on the mode.
     */
    public void drawCapacitor(double x, double y){
        /*# YOUR CODE HERE */
        if(dir.equals("horizontal")){
            UI.drawImage(tool+"-"+this.dir+".png",this.x-HEIGHT/2,this.y-WIDTH/2,HEIGHT,WIDTH);
        }else{
            UI.drawImage(tool+"-"+this.dir+".png",this.x-WIDTH/2,this.y-HEIGHT/2,WIDTH,HEIGHT);
        }
        this.drawLine(10);
    }

    /**
     * Draw a source centered at the point x, y.
     *  (Circle with wires on each side)
     * Core: only horizontal required
     * Completion: horizontal or vertical, depending on the mode.
     */
    public void drawSource(double x, double y){
        /*# YOUR CODE HERE */
        //Must set the width of the line first!
        UI.setLineWidth(2);
        UI.drawOval(x-WIDTH/2,y-HEIGHT/2,WIDTH,HEIGHT);
        this.drawLine(10);
    }
    /**
     * Draw a wire from the point where the user pressed the mouse to the point x,y.
     * Core: may be a straight line.
     * Completion: The wire should have a horizontal part followed by a vertical part
     * If the distance between the two points is very small, it just puts a circle at (x y)
     */
    public void drawWire(double x, double y){
        /*# YOUR CODE HERE */
        UI.setLineWidth(2);
        //there also have some bugs but it does not affect use.
        UI.invertLine(x,startY,x,y);
        UI.invertLine(startX,startY,x,startY);
    }

    /**
     * Erase a circular region around the position x, y
     * Should be big enough to erase a single component.
     */
    public void doErase(double x, double y){
        /*# YOUR CODE HERE */
        UI.eraseOval(x-eraseWidth/2,y-eraseWidth/2,eraseWidth,eraseWidth);
    }

    /**
     * Draw a label at position x, y.  Always horizontal.
     * Uses the label that was stored in a field.
     * Completion only.
     */
    public void drawLabel(double x, double y){
        /*# YOUR CODE HERE */
        int len = label.length();
        UI.drawString(label,x-len*3,y+3);
    }

    // Main:  constructs a new CircuitDrawer object
    public static void main(String[] arguments){
        new CircuitDrawer();
    }   

}
