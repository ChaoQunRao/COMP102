import ecs100.*;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
public class question{
    public question(){
        this.PrintingAnswers();
    }
    public void First(int sum , int x){
        int y = 5;
        if( x > 5 ){
            while( y < sum ){
                if( y < x ){
                    y = y + 4;
                }
                UI.println("y :" + y);
                y = y + 2;
            }
        }else{
            y = x*2;
            for(int i = x;i<sum;i+=2){
                if(y > i){
                    UI.println("y " + y);
                    y = y - 5;
                }
                UI.println("y " + y);
                y++;
            }
        }
        UI.println("Finished!");
    }
    public void Second(double left,double top,double height){
        int size = 20;
        for(int i = 0;i<height/20;i++){
            for(int j = i;j<height/20;j++){
                UI.drawRect(left + size*i,top + size*j,size,size);
            }
        }
    }
    public void Third(){
        try{
            File file = new File("Assigment2_marks.txt"); 
            Scanner scan = new Scanner (file); 
            int count = 0;
            while(scan.hasNext()){
                String message = scan.next() +" "+ scan.next() +" "+ scan.nextInt();
                int mark = (scan.nextInt() + scan.nextInt() + scan.nextInt())/3;
                int isCopy = scan.nextInt();
                if(isCopy == 0){
                    mark = 0;
                    UI.println(message + "copied");
                    count--;
                }
                count++;
            }
            UI.println("Count: " + count);
            scan.close (); 
        } catch(IOException e){UI.println ("Fail: " + e);}
    }
    public void Fourth(){
        try{
            File file = new File("Assigment2_marks.txt"); 
            Scanner scan = new Scanner(file);
            PrintStream out = new PrintStream("Assigment2_marks_analyze.txt");
            int num = 0;
            int sum = 0;
            int maxMark = 0;
            while(scan.hasNext()){
                String message = scan.next() +" "+ scan.next() +" "+ scan.nextInt();
                int mark = (scan.nextInt() + scan.nextInt() + scan.nextInt())/3;
                int isCopy = scan.nextInt();
                if(isCopy == 0){
                    mark = 0;
                }
                if(mark>maxMark){
                    maxMark = mark;
                }
                sum = sum + mark;
                num++;
            }
            out.println("Assignment2_marks");
            out.println("Numbers: " + num);
            out.printf("Average: %.2f ",(double)sum/num);
            out.println();
            out.println("Maximum mark: " + maxMark);
            UI.println("Success!");
            scan.close();
            out.close();
        }catch(IOException e){UI.println ("Fail: " + e);}
    }
    public void Fifth(){
        soldier White = new soldier(20,10,150,"White");
        soldier Facker = new soldier(30,18,100,"Facker");
        Facker.EquipGun();
        Facker.fightWith(White);
        if(Facker.HP<=0){
            UI.println("HP of White:" + White.HP);
        }else{
            UI.println("HP of Facker:" + Facker.HP);
        }
    }
    public void Print(){
    	int sum = 20;int x = 5;
    	this.First(20,5);
    }
    public void drawBrokes(){
    	double left = 10; double top = 20;double height = this.Height;
    	this.Second(left,top,height);
    }
    public void printPerson(){
    	this.Third();
    }
    public void  AssignmentAnalyzer(){
    	this.Fourth();
    }
    public void Battlefield(){
    	this.Fifth();
    }
    private double Height = 0;
      /*# Your Answer Here*/
    public void ChangeHeight(double height){
        this.Height = height;
    }
    public void PrintingAnswers(){
        UI.addButton("Clear",UI::clearGraphics);
        UI.addButton("Print",this::Print);
	UI.addButton("drawBrokes",this::drawBrokes);
	UI.addSlider("ChangesHeight",0,200,20,this::ChangeHeight);
	UI.addButton("printPerson",this::printPerson);
	UI.addButton("AssignmentAnalyzer",this::AssignmentAnalyzer);
	UI.addButton("Battlefield",this::Battlefield);
    }
    public static void main(String [] args){
        new question();
    }
}
