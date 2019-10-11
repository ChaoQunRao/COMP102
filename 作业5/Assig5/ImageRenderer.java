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
import java.io.*;
import java.awt.Color;

/** Renders plain ppm images onto the graphics panel
 *  ppm images are the simplest possible colour image format.
 */

public class ImageRenderer{
    public static final int TOP = 20;   // top edge of the image
    public static final int LEFT = 20;  // left edge of the image
    public static final int PIXEL_SIZE = 2;  

    /** Core:
     * Renders a ppm image file.
     * Asks for the name of the file, then calls renderImageHelper.
     */
    public void renderImageCore(){
        /*# YOUR CODE HERE */
        File f = new File(UIFileChooser.open());
        try{
            Scanner sc = new Scanner(f);
            this.renderImageHelper(sc);
        }catch(IOException e){UI.println("Read "+ f+" failed!");UI.println("Error:"+ e);}
    }

    /** Core:
     * Renders a ppm image file.
     * Renders the image at position (LEFT, TOP).
     * Each pixel of the image  is rendered by a square of size PIXEL_SIZE
     * Assumes that
     * - the colour depth is 255,
     * - there is just one image in the file (not "animated"), and
     * - there are no comments in the file.
     * The first four tokens are "P3", number of columns, number of rows, 255
     * The remaining tokens are the pixel values (red, green, blue for each pixel)
     */
    public void renderImageHelper(Scanner sc){
        /*# YOUR CODE HERE */
        //Read the type of files and number of row and column.
        String type = sc.next();
        //There I made a mistake, the first number should be column.
        //But in case of anyone copy my code, I change the condition of the for loop.
        //So I will not change the name of these two.
        int rowNum = sc.nextInt();
        int columnNum = sc.nextInt();
        int maxColor = sc.nextInt();
        int r,g,b = 0;
        int bNum = 0;
        //Judge the type of the file.
        if(type.equals("P3")){
            //Use for loop to draw image from file.
            for(int i = 0;i<columnNum;i++){
                for(int j = 0;j<rowNum;j++){
                    //Ensure that there have three numbers for us to read and save it to the variable
                    //I can't find any more effictive method to do it, so just repeat.
                    if(sc.hasNext()){
                        r = sc.nextInt()*(255/maxColor);
                    }else{
                        break;
                    }
                    if(sc.hasNext()){
                        g = sc.nextInt()*(255/maxColor);
                    }else{
                        break;
                    }
                    if(sc.hasNext()){
                        b = sc.nextInt()*(255/maxColor);
                    }else{
                        break;
                    }
                    //Set different color and draw.
                    Color c = new Color(r,g,b);
                    UI.setColor(c);
                    UI.fillRect(LEFT+PIXEL_SIZE*j,TOP+PIXEL_SIZE*i,PIXEL_SIZE,PIXEL_SIZE);
                }
            }
        }else if(type.equals("P2")){
            for(int i = 0;i<columnNum;i++){
                for(int j = 0;j<rowNum;j++){
                    //To draw the "pgm" file.
                    if(sc.hasNext()){
                        bNum = sc.nextInt()*(255/maxColor);
                    }
                    Color c = new Color(bNum,bNum,bNum);
                    UI.setColor(c);
                    UI.fillRect(LEFT+PIXEL_SIZE*j,TOP+PIXEL_SIZE*i,PIXEL_SIZE,PIXEL_SIZE);
                }
            }    
        }else{
            UI.println("Can not read this file!(Type: "+ type + ")");
            return;
        }
    }

    /** Completion
     * Renders a ppm image file which may be animated (multiple images in the file)
     * Asks for the name of the file, then renders the image at position (LEFT, TOP).
     * Each pixel of the image  is rendered by a square of size PIXEL_SIZE
     * Renders each image in the file in turn with 200 mSec delay.
     * Repeats the sequence 3 times.
     */
    public void renderAnimatedImage(){
        /*# YOUR CODE HERE */
        File f = new File(UIFileChooser.open());
        //To run this method over and over again
        while(true){
            this.runAgain(f);
        }
    }
    public void runAgain(File f){
        try{
            Scanner sc = new Scanner(f);
            //Using while loop can ensure the file will be read compeletly.
            while(sc.hasNext()){
                this.renderImageHelper(sc);
            }
        }catch(IOException e){UI.println("Read "+ f+" failed!");UI.println("Error:"+ e);}
    }
    /** Challenge
    * This method is not a good way to solve it,it is just satisfied about this assigment
    * Butit takes some time to run the program because I copied the file completely ( without the comments )
    * and then read it according to the above code. 
    * And for this part I changed the code of renderImageHelper method(To let it can read "pgm" file)
    */
    public void Challenge(){
       //Let user choose a file;
       File f = new File(UIFileChooser.open());
       try{
           //Made a new file to copy.
           PrintStream ps = new PrintStream("file");
           String str = "";
           Scanner sc = new Scanner(f);
           // Make sure there are no comments in the new document.
           while(sc.hasNext()){
                str = sc.next();
                if(str.contains("#")){
                    str = sc.nextLine();
                    str = "";
                }
                ps.print(str + " ");
           }
           //Close the newfile.
           ps.close();
           //Read the file and call the method to draw the picture.
           File file = new File("file");
           Scanner scan = new Scanner(file);
           this.renderImageHelper(scan);
           //Delete the new file.
           file.delete();
       }catch(IOException e){UI.println("Error: "+ e);}
    }
}
