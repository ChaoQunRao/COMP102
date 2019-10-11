// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2018T2
 * Name:Eric
 * Username:xmut_1712409237
 * ID:1712409237
 */

import ecs100.*;

/** Program to create simple animated cartoon strips using the
 *  CartoonCharacter class.  
 */

public class CartoonStrip{

    /** animate creates two cartoon characters on the window.
     *  Then animates them according to a fixed script by calling a series
     *  of methods on the characters.
     */
    public void animate(){
        /*# YOUR CODE HERE */
        CartoonCharacter Eric = new CartoonCharacter(100,100,"blue");
        CartoonCharacter Chara = new CartoonCharacter(500,100,"yellow");
        Chara.lookLeft();
        Chara.walkSlowly(150);
        Chara.speakShort("Hello,Eric!");
        Chara.speakShort("What are you doing here?");
        Eric.smile();
        Eric.speakShort("Nothing,just walking here.");
        Eric.speakShort("How about you, Chara?");
        Chara.speakLong("In fact, I came to you on purpose");
        Eric.speakShort("What do you mean?");
        Chara.speakLong("I want to copy your code about Java homework");
        Eric.frown();
        Eric.think("Well.......","right");
        Eric.speakLong("Look there! There are an UFO!");
        Chara.lookRight();
        Eric.lookLeft();
        Chara.speakShort("Where is the UFO?!");
        Eric.walk0(200);
        Chara.lookLeft();
        Chara.frown();
        Chara.walkSlowly(300);
        Chara.think("What an unsympathetic person!","left");
        Chara.walk0(500);
    }

    /** threeDancers creates three cartoon characters on the window.
     *  Then makes each character do the same little dance in turn,
     *  by calling the dance method.
     */
    public void threeDancers(){
        /*# YOUR CODE HERE */
        CartoonCharacter Eric = new CartoonCharacter(100,100,"blue");
        CartoonCharacter Chara = new CartoonCharacter(500,100,"yellow");
        CartoonCharacter Firs = new CartoonCharacter(300,100,"green");
        this.dance(Eric);
        this.dance(Chara);
        this.dance(Firs);
    }

    /** Makes a character do a little dance.
     * Has one parameter - a CartoonCharacter object
     */
    public void dance(CartoonCharacter face){
        /*# YOUR CODE HERE */
        face.walk0(100);
        face.speakShort("Turn left");
        face.lookLeft();
        
        face.walkSlowly(100);
        face.speakShort("Turn right");
        face.lookRight();
        
        face.walk0(100);
        face.speakShort("Turn left");
        face.lookLeft();
    }
    /**
     * This is the first code, but I improved it, so the real code is below.
     * 
    public void yourStory(){
        CartoonCharacter Eric = new CartoonCharacter(100,100,"blue");
        CartoonCharacter Chara = new CartoonCharacter(500,100,"yellow");
        String cha = null;
        String action = null;
        String word = null;
        String think = null;
        int dist = 0;
        String dir = "right";
        Boolean finish = false;
        for(int i = 0;i<= 100000;i++){
            cha = null;
            action = null;
            word = null;
            think = null;
            dist = 0;
            dir = null;
            finish = false;
            cha = UI.askString("Who do you want to start the action on?(Eric or Chara)");
            if(cha == "Eric"){
                this.story(action,word,think,dir,dist,Eric);
                finish = UI.askBoolean("Finished?");
                if(finish == true){
                    break;
                }
            }
            else{
                this.story(action,word,think,dir,dist,Chara);
                finish = UI.askBoolean("Finished?");
                if(finish == true){
                    break;
                }
            }
        }
    }
    public void story(String action,String word,String think,String dir,int dist,CartoonCharacter face){
        action = UI.askString("What do you want him to do?(Speak or Turn or Walk or Think)");
        if(action == "Speak"){
            word = UI.askString("Say what?");
            face.speakLong(word);
        }
        if(action == "Think"){
            think = UI.askString("Think what?");
            face.think(think,dir);
        }
        if (action == "Walk"){
            dist = UI.askInt("How far to go?");
        }
        if(action == "Turn"){
            dir = UI.askString("Where to turn?");
        }
    }*/
    public void doChanlledge(){
        CartoonStrip cs = new CartoonStrip();
        UI.addButton("Eric", cs::Eric);
        UI.addButton("Chara", cs::Chara);
        UI.println("Please press the newly generated button to generate a character");
    }
    public void Eric(){
        CartoonCharacter Eric = new CartoonCharacter(100,100,"blue");
        String dir = "right";
        CartoonStrip cs = new CartoonStrip();
        UI.addButton("speak(Eric)",()->cs.speak(Eric));
        UI.addButton("think(Eric)",()->cs.think(Eric,dir));
        UI.addButton("walk(Eric)",()->cs.walk(Eric));
        UI.addButton("turn(Eric)",()->cs.turn(Eric,dir));
        UI.println("You can control the character by pressing the button with the name of Eric");
    }
    public void Chara(){
        CartoonCharacter Chara = new CartoonCharacter(500,100,"yellow");
        String dir = "right";
        CartoonStrip cs = new CartoonStrip();
        UI.addButton("speak(Chara)",()->cs.speak(Chara));
        UI.addButton("think(Chara)",()->cs.think(Chara,dir));
        UI.addButton("walk(Chara)",()->cs.walk(Chara));
        UI.addButton("turn(Chara)",()->cs.turn(Chara,dir));
        UI.println("You can control the character by pressing the button with the name of the Chara");
    }
    public void speak(CartoonCharacter people){
        String words = null;
        words = UI.askString("Say what?");
        people.speakLong(words);
    }
    public void think(CartoonCharacter people,String dir){
        String words = null;
        words = UI.askString("Think what?");
        people.think(words,dir);
    }
    public void walk(CartoonCharacter people){
        int dist = UI.askInt("How far to go?");
        people.walkSlowly(dist);
    }
    public void turn(CartoonCharacter people,String dir){
        if(dir == "left"){
            dir = "right";
            people.lookRight();
        }
        else{
            dir = "left";
            people.lookLeft();
        }
    }
}

