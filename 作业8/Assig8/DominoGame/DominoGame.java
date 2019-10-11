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
import java.awt.Color;
import javax.swing.*;


/**
 *  Lets a player play a simple Solitaire dominoes game.
 *  Dominoes are rectangular tiles with two numbers from 0 to 6 on
 *  them (shown with dots).
 *  The player has a "hand" which can contain up to six dominoes.
 *  They can reorder the dominoes in their hand, they can place dominoes
 *  from their hand onto the table, and they can pick up more dominoes from a bag
 *  to fill the gaps in their "hand".
 *  The core and completion do not involve any of the matching and scoring
 *  of real dominoes games. 
 *
 *  PROGRAM DESIGN
 *  The dominoes are represented by objects of the Domino class.
 *  The Domino constructor will construct a new, random domino.
 *  Dominos have a draw(double x, double y) method that will draw the
 *  Domino on the graphics pane at the specified position.
 *  
 *  The program has two key fields:
 *    hand:  an array that can hold 6 Dominos. 
 *    table: an arrayList of the Dominos that have been placed on the table.
 *    
 *  The hand should be displayed near the top of the Graphics pane with a
 *   rectangular border and each domino drawn at its place in the hand.
 *  Empty spaces in the hand should be represented by nulls and displayed as empty.
 *
 *  The user can select a position on the hand using the mouse.
 *  The selected domino (or empty space) should be highlighted with
 *  a border around it.
 *  
 *  The user can use the "Left" or "Right" button to move the selected domino
 *  (or the space) to the left or the right, in which case the domino is
 *  swapped with the contents of the adjacent position in the hand.
 *  If the selected position contains a domino, the user
 *  can use the "Place" button to move the selected domino to the table.
 *  
 *  If there are any empty positions on the hand, the user can use the
 *  "Pickup" button to get a new (random) domino which will be added to
 *  the hand at the leftmost empty position.
 *
 *  The table is represented by an ArrayList of dominos.
 *  At the beginning of the game the table should be empty.
 *  Dominos should be added to the end of the table.
 *  The table should be displayed in rows at the top of the graphics pane.
 */

public class DominoGame{
    public static final int NUM_HAND = 6;    // Number of dominos in hand

    // Fields
    private Domino[] hand;            // the hand (fixed size array of Dominos)
    private ArrayList<Domino> table;  // the table (variable sized list of Dominos)

    private int selectedPos = 0;      //  selected position in the hand.

    // (You shouldn't add any more fields for core or completion)

    // constants for the layout
    public static final int HAND_LEFT = 60; // x-position of the leftmost Domino in the hand
    public static final int HAND_TOP = 5;   // y-Position of all the Dominos in the hand 
    public static final int DOMINO_SPACING = 104; 
    //spacing is the distance from left side of Domino to left side of next domino
    public static final int DOMINO_HEIGHT = 50; 

    public static final int TABLE_LEFT = 10;                
    public static final int TABLE_TOP = 120;   

    /**  Constructor:
     * Initialise the hand field to have an array that will hold NUM_HAND Dominos
     * Initialise the table field to have an ArrayList of Dominos,
     * set up the GUI (call setupGUI method)
     *  restart the game
     */
    public DominoGame(){
        /*# YOUR CODE HERE */
        /*# Restart the game and set the GUI*/
        this.restart();
        this.setupGUI();
        this.redraw();
    }
    // Set up the GUI (buttons and mouse)
    public void setupGUI(){
        /*# YOUR CODE HERE */
        /*# Set up mouse monitoring*/
        UI.setMouseMotionListener(this::doMouse);
        /*# This is another method used to set button*/
        this.SetButton();
    }
    /*# A field to save the button*/
    private ArrayList<JButton> button = new ArrayList<JButton>();
    public void SetButton(){
        /*#Add all button at first*/
        button.add(UI.addButton("Pick",this::pickup));
        button.add(UI.addButton("Place",this::placeDomino));
        button.add(UI.addButton("Flip",this::flipDomino));
        button.add(UI.addButton("MoveLeft",this::moveLeft));
        button.add(UI.addButton("MoveRight",this::moveRight));
        button.add(UI.addButton("Suggestion",this::suggestDomino));
        button.add(UI.addButton("Restart",this::restart));
        /*# For each button, set their color and some other actions*/
        for(JButton button:this.button){
            /*# set its color and an action */
            button.setBackground(Color.white);
            button.addActionListener(event ->
            button.setBackground(Color.orange));
            /*# Add the mouseListener to the button, and when the mouse enter the button, set its color to pink
               when the mouse exit the button, set its color to white.
               */
            button.addMouseListener(new java.awt.event.MouseAdapter(){
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(Color.pink);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(Color.white);
                }
            });
            /*# Just test the move listener*/
            /*button.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                public void mouseMoved(java.awt.event.MouseEvent evt) {
                    button.setBackground(Color.red);
                }
            });*/
        }
    }
    /**
     * Restart the game:
     *  set the table to be empty,
     *  set the hand to have no dominos
     */
    public void restart(){
        /*# YOUR CODE HERE */
        /*# Initialise the two array and clear the text pane*/
        this.hand = new Domino[NUM_HAND];
        this.table = new ArrayList<Domino>();
        UI.clearText();
        this.redraw();
    }

    /**
     * If there is at least one empty position on the hand, then
     * create a new random domino and put it into the first empty
     * position on the hand.
     * (needs to search along the array for an empty position.)
     */
    public void pickup(){
        /*# YOUR CODE HERE */
        for(int i = 0;i<this.hand.length;i++){
            /*# if there have at least a null in this array , get a new domino and add it to the first position*/
            if(this.hand[i] == null){
                this.hand[i] = new Domino();
                /*# if all array have not any double, then change the last domino to be a double*/
                while(i == 5 && this.hand[i].getLeft() != this.hand[i].getRight()&&this.table.isEmpty()){
                    this.hand[i] = new Domino();
                }
                break;
            }
        }
        this.redraw();
    }

    /**
     * Draws the outline of the hand,
     * draws all the Dominos in the hand,
     * highlights the selected position in some way
     * This needs to use the constants:
     *   DOMINO_SPACING, DOMINO_HEIGHT, HAND_LEFT, HAND_TOP
     */
    public void drawHand(){
        /*# YOUR CODE HERE */
        int i = 0;
        for(Domino hand:this.hand){
            /*# if there have any position is null, draw a empty rectangle or it's not null, draw
               the domino and the selected rectangle with green*/
            if(hand != null){
                hand.draw(HAND_LEFT + i*DOMINO_SPACING,HAND_TOP);
                if(i == this.selectedPos){
                    UI.setColor(Color.green);
                    UI.setLineWidth(3);
                    UI.drawRect(HAND_LEFT + i*DOMINO_SPACING,HAND_TOP,hand.WIDTH,hand.HEIGHT);
                }
            }else{
                UI.setLineWidth(1);
                UI.setColor(Color.black);
                UI.drawRect(HAND_LEFT + i*DOMINO_SPACING,HAND_TOP,hand.WIDTH,hand.HEIGHT);
            }
            i++;
        }
    }

    /**
     * Move domino from selected position on hand (if there is domino there) to the table
     * The selectedPos field contains the index of the selected domino.
     */
    public void placeDomino(){
        /*# YOUR CODE HERE */
        /*#there have three rules to place the domino into table*/
        if(this.hand[this.selectedPos] == null){
            UI.println("This place have not any domino!");
            return;
        }
        if(this.table.isEmpty() && this.hand[this.selectedPos].getLeft() != this.hand[this.selectedPos].getRight()){
            UI.println("First, Only a double can be placed on the table!");
            return;
        }
        if(!this.table.isEmpty()&&this.hand[this.selectedPos].getLeft() != this.table.get(this.table.size()-1).getRight()){
            UI.println("A domino can only be placed next to another domino if they have matching numbers!");
            return;
        }
        /*#If all conditions are met, the object is added to the table arraylist, and set the hand array to null in that position*/
        this.table.add(hand[this.selectedPos]);
        hand[this.selectedPos] = null;
        this.redraw();
    }

    /**
     * Draws the list of Dominos on the table, 7 to a row
     * Note, has to wrap around to a new row when it gets to the
     * edge of the table
     * This needs to use the constants:
     *   DOMINO_SPACING, DOMINO_HEIGHT, TABLE_LEFT, TABLE_TOP
     */
    public void drawTable(){
        /*# YOUR CODE HERE */
        int i = 0;
        int j = 0;
        for(Domino ta:this.table){
            if(ta != null){
                ta.draw(TABLE_LEFT + i*DOMINO_SPACING,TABLE_TOP + j*DOMINO_HEIGHT);
                i++;
            }
            /*# If there are 7 dominos then set the Y position to the next line and the x position to 0*/
            if(i%7 == 0){
                j++;
                i = 0;
            }
        }
    }

    /**
     * If there is a domino at the selected position in the hand, 
     * flip it over.
     */
    public void flipDomino(){
        /*# YOUR CODE HERE */
        if(this.hand[this.selectedPos] == null){
            UI.println("This place have not any domino!");
            return;
        }
        this.hand[this.selectedPos].flip();
        this.redraw();
    }

    /**
     * Swap the contents of the selected position on hand with the
     * position on its left (if there is such a position)
     * and also decrement the selected position to follow the domino 
     */
    public void moveLeft(){
        /*# YOUR CODE HERE */
        if(this.selectedPos == 0){
            UI.println("You can't move left in this position");
            return;
        }
        /*# Just swapped positions selected and left*/
        Domino d;
        d = this.hand[this.selectedPos];
        this.hand[this.selectedPos] = this.hand[this.selectedPos-1];
        this.hand[this.selectedPos-1] = d;
        this.selectedPos--;
        this.redraw();
    }

    /**
     * Swap the contents of the selected position on hand with the
     *  position on its right (if there is such a position)
     *  and also increment the selected position to follow the domino 
     */
    public void moveRight(){
        /*# YOUR CODE HERE */
        if(this.selectedPos == this.hand.length - 1){
            UI.println("You can't move right in this position");
            return;
        }
        /*# Just swapped positions selected and right*/
        Domino d;
        d = this.hand[this.selectedPos];
        this.hand[this.selectedPos] = this.hand[this.selectedPos+1];
        this.hand[this.selectedPos+1] = d;
        this.selectedPos++;
        this.redraw();
    }

    /**
     * If the table is empty, only a double (left and right the same) can be suggested.
     * If the table is not empty, see if one domino has a number that matches the right 
     *    number of the last domino on the table.
     */
    public void suggestDomino(){
        /*# YOUR CODE HERE */
        int i = 0;
        boolean CanSuggest = false;
        for(Domino player:this.hand){
            /*# Add those rules into "if" statement and then draw the red rectangle into the domino*/
            if(player != null&&((player.getLeft() == player.getRight()&&this.table.isEmpty())
            ||(!this.table.isEmpty()
            &&(this.table.get(this.table.size()-1).getRight() == player.getLeft()
            ||this.table.get(this.table.size()-1).getRight() == player.getRight()))
            )){
                UI.setColor(Color.red);
                UI.setLineWidth(3);
                UI.drawRect(HAND_LEFT + i*DOMINO_SPACING,HAND_TOP,player.WIDTH,player.HEIGHT);
                UI.println("Suggesting [ " + player.getLeft() + " | " +  player.getRight() +  " ]");
                CanSuggest = true;
            } 
            i++;
        }
        /*# If can find the suggestion , print a message*/
        if(!CanSuggest){
            UI.println("No suggestion");
        }
    }

    /** ---------- The code below is already written for you ---------- **/

    /** Allows the user to select a position in the hand using the mouse.
     * If the mouse is released over the hand, then sets  selectedPos
     * to be the index into the hand array.
     * Redraws the hand and table */
    /*# Some fields in the doMouse method(used to draw the line)*/
    private int endPos = 0;
    private double LineX;
    private double LineY;
    private double endX;
    public void doMouse(String action, double x, double y){
        if (action.equals("released")){
            /*# if released the mouse, set the end position and fliped the end and selected position*/
            if (y >= HAND_TOP && y <= HAND_TOP+DOMINO_HEIGHT && 
            x >= HAND_LEFT && x <= HAND_LEFT + NUM_HAND*DOMINO_SPACING) {
                this.endPos = (int) ((x-HAND_LEFT)/DOMINO_SPACING);
                UI.clearText();
                if(this.selectedPos != endPos){
                    UI.println("fliped "+(this.selectedPos+1) + " and " + (endPos+1));
                }
                Domino d = this.hand[endPos];
                this.hand[endPos] = this.hand[selectedPos];
                this.hand[selectedPos] = d;
                this.selectedPos = this.endPos;
                UI.println("selected "+this.selectedPos);
                this.redraw();
            }
        }
        if (action.equals("pressed")){
            /*# if pressed the mouse, set the selected position and fliped the end and selected position*/
            this.LineX = x;
            this.LineY = HAND_TOP+DOMINO_HEIGHT;
            if (y >= HAND_TOP && y <= HAND_TOP+DOMINO_HEIGHT && 
            x >= HAND_LEFT && x <= HAND_LEFT + NUM_HAND*DOMINO_SPACING) {
                this.selectedPos = (int) ((x-HAND_LEFT)/DOMINO_SPACING);
                UI.setColor(Color.white);
                UI.setLineWidth(1);
                /*# Draw a prompt line*/
                UI.drawLine(x,HAND_TOP+DOMINO_HEIGHT,x,HAND_TOP+DOMINO_HEIGHT+20);
                UI.drawLine(x,HAND_TOP+DOMINO_HEIGHT,x-10,HAND_TOP+DOMINO_HEIGHT+10);
                UI.drawLine(x,HAND_TOP+DOMINO_HEIGHT,x+10,HAND_TOP+DOMINO_HEIGHT+10);
            }
        }
        /*# Draw a prompt line , looks like a lot of lines of code, actually just to draw prompt lines*/
        if(action.equals("moved")){
           this.endX = x;
        }
        if(action.equals("dragged")){
           if (y >= HAND_TOP && y <= HAND_TOP+DOMINO_HEIGHT && 
            x >= HAND_LEFT && x <= HAND_LEFT + NUM_HAND*DOMINO_SPACING
            ) {
               UI.setColor(Color.black);
               UI.setLineWidth(1);
               UI.invertLine(LineX,LineY+20,endX,LineY+20);
               UI.invertLine(endX,HAND_TOP+DOMINO_HEIGHT,endX,HAND_TOP+DOMINO_HEIGHT+20);
               UI.invertLine(endX,HAND_TOP+DOMINO_HEIGHT,endX-10,HAND_TOP+DOMINO_HEIGHT+10);
               UI.invertLine(endX,HAND_TOP+DOMINO_HEIGHT,endX+10,HAND_TOP+DOMINO_HEIGHT+10);
               this.endX = x;
               UI.invertLine(LineX,LineY+20,x,LineY+20);
               UI.invertLine(x,HAND_TOP+DOMINO_HEIGHT,x,HAND_TOP+DOMINO_HEIGHT+20);
               UI.invertLine(x,HAND_TOP+DOMINO_HEIGHT,x-10,HAND_TOP+DOMINO_HEIGHT+10);
               UI.invertLine(x,HAND_TOP+DOMINO_HEIGHT,x+10,HAND_TOP+DOMINO_HEIGHT+10);
            }
        }
    }

    /**
     *  Redraw the table and the hand.
     */
    public void redraw(){
        UI.clearGraphics();
        this.drawHand();
        this.drawTable();
    }

    public static void main(String[] args){
        DominoGame obj = new DominoGame();
    }   
}
