import ecs100.*;
import java.util.*;
import java.awt.Color;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
public class test{
   private ArrayList<int[][]>  PreviousImage = new ArrayList<int[][]>(); 
   private int[][] image = new int[][]{{80,80,80},{80, 200, 80},{80,80,80}}; 
   public void test(){
       this.PreviousImage.add(this.image);
       UI.println(this.PreviousImage.get(0)[0][0]);
   }
}
