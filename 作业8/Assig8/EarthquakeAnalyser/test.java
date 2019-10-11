import ecs100.*;
public class test{
    public void testTopic(){
        this.drawCircles(10,20,200);
    }
    public void drawCircles(double left,double top,double height){
        int size = 20;
        double num = height/20;
        for(int i = 0;i<num;i++){
            UI.drawOval(left,top + i*size,size,size);
        }
    }
}
