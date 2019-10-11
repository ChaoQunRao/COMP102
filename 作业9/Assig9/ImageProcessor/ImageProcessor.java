// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2018T2
 * Name:Eric
 * Username:xmut_1712409237
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.awt.Color;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
/** ImageProcessor allows the user to display and edit a
 *  greyscale image in a number of ways.
 *  The program represents the image as a 2D array of integers, which must
 *   all be between 0 (black) and 255 (white).
 *  The class includes three methods (already written for you) that will
 *   - read a png or jpeg image file and store a 2D array of greyscale values
 *     into the image field.
 *   - render (display) the 2D array of greyscale values in the image field on the graphics pane
 *   - write the 2D array of greyscale values in the image field to a png file.
 *  
 *  You are to complete the methods to modify an image:
 *   - darken the image
 *   - increase the contrast
 *   - Rotate the image 180 degrees and 90 degrees
 *   - flip the image horizontally or vertically.
 *   - merge another image with the current image
 *
 */
public class ImageProcessor{
    // the current image (initialised to a very small 3x3 image)
    private int[][] image = new int[][]{{80,80,80},{80, 200, 80},{80,80,80}}; 
    private ArrayList<int[][]>  PreviousImage = new ArrayList<int[][]>(); 
    private int step;
    // current selected point
    private int selectedRow = 0;
    private int selectedCol = 0;

    private double pixelX = 1;  // the size of the pixels as drawn on screen
    private double pixelY = 1;  // the size of the pixels as drawn on screen
    
    /**
     * Construct a new ImageProcessor object
     * and set up the GUI
     */
    public ImageProcessor(){
        this.setupGui();
        this.computeGreyColours();   // compute table of grey colours for converting images to greyscale.
    }
    ArrayList<JButton> button = new ArrayList<JButton>();
    JButton mouse = new JButton();
    public void setupGui(){
        UI.initialise();
        UI.setMouseMotionListener(this::doMouse);
        this.addButton("Load",       this::loadImage );
        this.addButton("Save",       this::saveImage ); 
        this.addButton("<-Back",      this::back ); 
        this.addButton("Darken",     this::darkenImage );
        this.addButton("Contrast",   this::contrastImage );    
        this.addButton("Flip Horiz", this::flipImageHorizontally );
        this.addButton("Flip Vert",  this::flipImageVertically );
        this.addButton("Rotate 180", this::rotateImage180 );     
        this.addButton("Rotate 90",  this::rotateImage90 );   
        this.addButton("Expand",     this::expandImage );
        this.mouse = UI.addButton("ExpandWithMouse",     this::expandMouse );
        this.addButton("Merge",      this::mergeImage ); 
        this.addButton("Quit", UI::quit );    
        this.setButton();
        UI.addSlider("length", 0, 1000 , 300 , this::setPixelX);
        UI.addSlider("width", 0, 1000 , 300 , this::setPixelY);
    }
    public void addButton(String name,UIButtonListener controller){
        this.button.add(UI.addButton(name,controller));
    }
    public void setButton(){
        mouse.setBackground(Color.white);
        for(JButton b:this.button){
            b.setBackground(Color.white);
            b.addActionListener(event ->
            b.setBackground(Color.orange));
            /*# Add the mouseListener to the button, and when the mouse enter the button, set its color to pink
               when the mouse exit the button, set its color to white.
               */
            b.addMouseListener(new java.awt.event.MouseAdapter(){
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    Color col = Color.getHSBColor((float)Math.random(),1,1);
                    b.setBackground(col);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    b.setBackground(Color.white);
                }
            });
        }
    }
    public void setPixelX(double x){
        this.pixelX = x/300;
        this.redisplayImage();
    }
    public void setPixelY(double y){
        this.pixelY = y/300;
        this.redisplayImage();
    }
    
    public void save(){
        int rows = this.image.length;
        int cols = this.image[0].length;
        int[][] newImage = new int[rows][cols];
        for(int i = 0;i<rows;i++){
            for(int j = 0;j<cols;j++){
                newImage[i][j] = this.image[i][j];
            }
        }
        this.PreviousImage.add(newImage);
        this.step++;
    }
    
    public void back(){
        int rows = this.image.length;
        int cols = this.image[0].length;
        if(this.step <= 0){
            UI.println("You can't return any more!");
            return;
        }
        this.PreviousImage.remove(this.step);
        step--;
        for(int i = 0;i<rows;i++){
            for(int j = 0;j<cols;j++){
                this.image[i][j] = this.PreviousImage.get(this.step)[i][j];
            }
        }
        this.redisplayImage();
    }
    
    /** CORE:
     * Make all pixels in the image darker by 20 greylevels.
     * but make sure that you never go below 0
     */
    public void darkenImage(){
        /*# YOUR CODE HERE */
        this.save();
        for(int i = 0;i<this.image.length;i++){
            for(int j = 0;j<this.image[0].length;j++){
                if(this.image[i][j]>=20){
                    this.image[i][j] = this.image[i][j] - 20;
                }
            }
        }
        this.redisplayImage();
    }  

    /** CORE:
     * Increase the contrast of the image -
     * make all lighter pixels in the image (above 128) even lighter (by 20%)
     * and make all darker pixels even darker (by 20%)
     * For example, a pixel value of 158 is 30 levels above 128. It should be lightened by 20% of 30 (= 6) to 164. 
     *              A pixel value of 48 is 80 levels below 128. It should be darkened by 20% of 80 (= 16) to 32. 
     */
    public void contrastImage(){
        /*# YOUR CODE HERE */
        this.save();
        for(int i = 0;i<this.image.length;i++){
            for(int j = 0;j<this.image[0].length;j++){
                if(this.image[i][j]>=128 && this.image[i][j]<=255){
                    this.image[i][j] = (int)(this.image[i][j] + 0.2*(this.image[i][j] - 128));
                }else if(this.image[i][j] >= 0){
                    this.image[i][j] = (int)(this.image[i][j] - 0.2*(128 - this.image[i][j]));
                }
                if(this.image[i][j] > 255){
                    this.image[i][j] = 255; 
                }
                if(this.image[i][j] < 0){
                    this.image[i][j] = 0;
                }
            }
        }
        this.redisplayImage();
    } 

    /** CORE:
     * Flip the image horizontally
     *   exchange the values on the left half of the image
     *   with the corresponding values on the right half
     */
    public void flipImageHorizontally(){
        /*# YOUR CODE HERE */
        this.save();
        int rows = this.image.length;
        int cols = this.image[0].length;
        for(int i = 0;i<rows;i++){
            for(int j = 0;j<cols/2;j++){
                int color = this.image[i][j];
                this.image[i][j] = this.image[i][cols-j-1];
                this.image[i][cols-j-1] = color;
            }
        }
        this.redisplayImage();
    }

    /** CORE:
     * Flip the image vertically
     *   exchange the values on the top half of the image
     *   with the corresponding values on the bottom half
     */
    public void flipImageVertically(){
        /*# YOUR CODE HERE */
        this.save();
        int rows = this.image.length;
        int cols = this.image[0].length;
        for(int i = 0;i<rows/2;i++){
            for(int j = 0;j<cols;j++){
                int color = this.image[i][j];
                this.image[i][j] = this.image[rows-i-1][j];
                this.image[rows-i-1][j] = color;
            }
        }
        this.redisplayImage();
    }

    /** COMPLETION:
     * Rotate the image 180 degrees
     * Each cell is swapped with the corresponding cell
     *  on the other side of the center of the images.
     * It is easier to make a new array, the same size as image, then
     *   copy each pixel in image to the right place in the new array
     *   and then assign the new array to the image field.
     */
    public void rotateImage180(){
        /*# YOUR CODE HERE */
        this.save();
        int rows = this.image.length;
        int cols = this.image[0].length;
        for(int i = 0;i<rows/2;i++){
            for(int j = 0;j<cols;j++){
                int color = this.image[i][j];
                this.image[i][j] = this.image[rows-i-1][j];
                this.image[rows-i-1][j] = color;
            }
        }
        for(int i = 0;i<rows;i++){
            for(int j = 0;j<cols/2;j++){
                int color = this.image[i][j];
                this.image[i][j] = this.image[i][cols-j-1];
                this.image[i][cols-j-1] = color;
            }
        }
        this.redisplayImage();
    }

    /**  COMPLETION:
     * Rotate the image 90 degrees anticlockwise
     * Note, the resulting image will have different dimensions:
     *  the width of the new image will be the height of the old image.
     *  the height of the new image will be the width of the old image.
     * You will need to make a new image array of the new dimensions,
     *  fill it with the correct pixel values from the original array, 
     *  and then set the image field to contain the new image.
     */
    public void rotateImage90(){
        /*# YOUR CODE HERE */
        this.save();
        int rows = this.image.length;
        int cols = this.image[0].length;
        int[][] newImage = new int[cols][rows];
        for(int i = 0;i<cols;i++){
            for(int j = 0;j<rows;j++){
                newImage[i][j] = this.image[j][cols - i- 1];
            }
        }
        this.image = newImage;
        this.redisplayImage();
    }

    /** CHALLENGE:
     * Expand the top left quarter of the image to fill the whole image
     * each pixel in the top left quarter will be copied to four pixels
     * in the new image.
     * Be careful not to try to access elements past the edge of the array!
     * Hint: It is actually easier to work backwards from the bottom right corner.
     */
    public void expandImage(){
        /*# YOUR CODE HERE */
        this.save();
        int rows = this.image.length;
        int cols = this.image[0].length;
        int[][] newImage = new int[rows][cols];
        for(int i = 0;i<rows/2;i++){
            for(int j = 0;j<cols/2;j++){
                newImage[i*2][j*2] = this.image[i][j];
                newImage[i*2+1][j*2+1] = this.image[i][j];
                newImage[i*2][j*2+1] = this.image[i][j];
                newImage[i*2+1][j*2] = this.image[i][j];
            }
        }
        this.image = newImage;
        this.redisplayImage();
    }
    public void doExpand(){
        this.save();
        int rows = this.image.length;
        int cols = this.image[0].length;
        int[][] newImage = new int[rows][cols];
        if(this.selectedRow > 3*rows/4){
            this.selectedRow = 3*rows/4;
        }else if(this.selectedRow < rows/4){
            this.selectedRow = rows/4;
        }
        if(this.selectedCol > 3*cols/4){
            this.selectedCol = 3*cols/4;
        }else if(this.selectedRow < rows/4){
            this.selectedCol = cols/4;
        }
        for(int i = 0;i<rows/2;i++){
            for(int j = 0;j<cols/2;j++){
                int color = this.image[this.selectedRow + i - rows/4][this.selectedCol + j - cols/4];
                newImage[i*2][j*2] = color;
                newImage[i*2+1][j*2+1] = color;
                newImage[i*2][j*2+1] = color;
                newImage[i*2+1][j*2] = color;
            }
        }
        this.image = newImage;
        this.redisplayImage();
    }
    private boolean IsMouseExpend = false;
    public void expandMouse(){
        this.IsMouseExpend = !this.IsMouseExpend;
        if(this.IsMouseExpend){
            this.mouse.setLabel("Mouse On");
            UI.println("Click on the area you want to zoom in with the mouse");
            this.mouse.setBackground(Color.CYAN);
        }else{
            this.mouse.setLabel("Mouse Off");
            this.mouse.setBackground(Color.GRAY);
        }
    }
    /** CHALLENGE:
     * Merge two images 
     * Ask the user to select another image file, and load it into another array.
     *  Work out the rows and columns shared by the images
     *  For each pixel value in the shared region, replace the current pixel value
     *  by the average of the pixel value in current image and the corresponding
     *  pixel value in the other image.
     */
    public void mergeImage(){
        this.save();
        int [][] other = this.loadAnImage(UIFileChooser.open());
        int rows = Math.min(this.image.length, other.length);       // rows and cols
        int cols = Math.min(this.image[0].length, other[0].length); // common to both
        //only change image in region 0..rows-1, 0..cols-1
        /*# YOUR CODE HERE */
        for(int i = 0;i<rows;i++){
            for(int j = 0;j<cols;j++){
                this.image[i][j] = (int)(this.image[i][j]+other[i][j])/2;
            }
        }
        this.redisplayImage();
    }

    //=========================================================================
    // Methods below here are already written for you -
    // for redisplaying the image array on the graphics pane,
    // for loading an image file into the image array,
    // for saving the image array into a file,
    // for setting the mouse position.

    /** field and helper methods to precompute and store all the possible grey colours,
     *  so the redisplay method does not have to constantly construct new color objects
     */
    private Color[] greyColors = new Color[256];

    /** Display the image on the screen with each pixel as a square of size pixelSize.
     *  To speed it up, all the possible colours from 0 - 255 have been precalculated.
     */
    public void redisplayImage(){
        UI.clearGraphics();
        UI.setImmediateRepaint(false);
        for(int row=0; row<this.image.length; row++){
            double y = row * this.pixelY;
            for(int col=0; col<this.image[0].length; col++){
                double x = col * this.pixelX;
                UI.setColor(this.greyColor(this.image[row][col]));
                UI.fillRect(x, y, this.pixelX, this.pixelY);
            }
        }
        UI.setColor(Color.red);
        UI.fillRect(this.selectedCol,this.selectedRow,2,2);
        UI.repaintGraphics();
    }

    /** Get and return an image as a two-dimensional grey-scale image (from 0-255).
     *  This method will cause the image to be returned as a grey-scale image,
     *  regardless of the original colouration.
     */
    public int[][] loadAnImage(String imageName) {
        int[][] ans = null;
        if (imageName==null) return null;
        try {
            BufferedImage img = ImageIO.read(new File(imageName));
            UI.printMessage("loaded image height(rows)= " + img.getHeight() +
                "  width(cols)= " + img.getWidth());
            ans = new int[img.getHeight()][img.getWidth()];
            for (int row = 0; row < img.getHeight(); row++){
                for (int col = 0; col < img.getWidth(); col++){
                    Color c = new Color(img.getRGB(col, row), true);
                    // Use a common algorithm to move to greyscale
                    ans[row][col] = (int)Math.round((0.3 * c.getRed()) + (0.59 * c.getGreen())
                        + (0.11 * c.getBlue()));
                }
            }
        } catch(IOException e){UI.println("Image reading failed: "+e);}
        return ans;
    }

    /** Ask user for an image file, and load it into the current image */
    public void loadImage(){
        this.image = this.loadAnImage(UIFileChooser.open());
        this.PreviousImage = new ArrayList<int[][]>();
        this.save();
        this.step = 0;
        this.pixelX = 1;
        this.pixelY = 1;
        this.redisplayImage();
    }

    /** Write the current greyscale image to the specified filename */
    public  void saveImage() {
        // For speed, we'll assume every row of the image is the same length!
        int height = this.image.length;
        int width = this.image[0].length;
        
        while(this.pixelX >= 2){
            int[][] newImage = new int[this.image.length*2][this.image[0].length];
            for(int i = 0;i<this.image.length;i++){
                for(int j = 0;j<this.image[0].length;j++){
                    newImage[2*i][j] = this.image[i][j];
                    newImage[2*i+1][j] = this.image[i][j];
                }
            }
            this.image = newImage;
            this.pixelX = this.pixelX - 2;
        }

        while(this.pixelY >= 2){
            int[][] newImage = new int[this.image.length][this.image[0].length*2];
            for(int j = 0;j<this.image[0].length;j++){
                for(int i = 0;i<this.image.length;i++){
                    newImage[i][2*j] = this.image[i][j];
                    newImage[i][2*j + 1] = this.image[i][j];
                }
            }
            this.image = newImage;
            this.pixelY = this.pixelY - 2;
        }
        
        BufferedImage img = new BufferedImage(this.image[0].length,this.image.length, BufferedImage.TYPE_INT_ARGB);
        for (int row = 0; row < this.image.length; row++) {
            for (int col = 0; col < this.image[0].length; col++) {
                int greyscaleValue = this.image[row][col];
                Color c = new Color(greyscaleValue, greyscaleValue, greyscaleValue);
                img.setRGB(col, row, c.getRGB());
            }
        }
        try {
            String fname = UIFileChooser.save("save to png image file");
            if (fname==null) return;
            File imageFile = new File(fname);
            ImageIO.write(img, "png", new File(fname));
        } catch(IOException e){UI.println("Image reading failed: "+e);}
    }

    private void computeGreyColours(){
        for (int i=0; i<256; i++){
            this.greyColors[i] = new Color(i, i, i);
        }
    }

    private Color greyColor(int grey){
        if (grey < 0){
            return Color.blue;
        }
        else if (grey > 255){
            return Color.red;
        }
        else {
            return this.greyColors[grey];
        }
    }
    
    public void doMouse(String a, double x, double y){
        if(a.equals("released")) {
            this.redisplayImage();
        }
        if(a.equals("clicked")&&this.IsMouseExpend){
            this.setPos(x, y);
            this.doExpand();
        }
    }

    /** Set the selected Row and Col to the pixel on the mouse position x, y */
    public void setPos(double x, double y){
        int row = (int)(y/this.pixelY);
        int col = (int)(x/this.pixelX);
        if (this.image != null && row < this.image.length && col < this.image[0].length){
            this.selectedRow = row;
            this.selectedCol = col;
            this.redisplayImage();
        }
    }

    // Main
    public static void main(String[] arguments){
        ImageProcessor ob = new ImageProcessor();
        UI.println("If you have any button functions that are not clear");
        UI.println("Please open reflection for details first");
        UI.println("Please do not click the button in quick succession!");
    }   

}
