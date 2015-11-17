package code;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable{

    public AnchorPane canwrap;

    public Canvas canvas;
    private GraphicsContext gContext;

    double cWidth;
    double cHeight;

    int level, prevLevel = 0;
    int drawCirclesCount = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas = new Canvas(300, 300);
        gContext = canvas.getGraphicsContext2D();
        canwrap.getChildren().add(canvas);

        System.out.println("Initialise method has been executed");
        System.out.println("Init method - anchor width:" + canwrap.getWidth());
        System.out.println("Init method - anchor height:" + canwrap.getHeight());
    }

    public void resizeCanvas(){
        cWidth  = canwrap.getWidth();
        cHeight = canwrap.getHeight();
        canvas.setWidth(cWidth);
        canvas.setHeight(cHeight);
        }
    @FXML
    public void levelInc(){
        resizeCanvas();
        drawCircle();
        prevLevel = level;
        level++;
    }

    @FXML
    public void levelDec(){
        double radius=cWidth/5; //radius of first circle
        double xMid=cWidth/2, yMid=cHeight/2; //center point (x,y) of circle

        resizeCanvas();
        if(level >= 1) {
            gContext.clearRect(0, 0, cWidth, cHeight);
            //Draw first circle and horisontal line
            gContext.strokeLine(0, yMid, cWidth-1, yMid);
            gContext.strokeOval(xMid-radius,yMid-radius,radius*2,radius*2);
            if(prevLevel < level) {
                prevLevel = level;
                level-=2;
            }
            else {
                prevLevel = level;
                level--;
            }
            drawCircle();
        }
    }


    public void drawCircle(){
        System.out.println("Draw circle method called!");
        double radius=cWidth/5; //radius of first circle
        double xMid=cWidth/2, yMid=cHeight/2; //center point (x,y) of circle

        if(level == 0){
            //Draw first line
            gContext.setStroke(Color.BLUE);
            gContext.setLineWidth(2);

            //Draw first circle and horisontal line
            gContext.strokeLine(0, yMid, cWidth-1, yMid);
            gContext.strokeOval(xMid-radius,yMid-radius,radius*2,radius*2);
        }
        else{
            drawCircles(xMid, yMid, radius, 1);
        }
    }

    void drawCircles (double xMid, double yMid, double radius, int depth){

            if (radius >= 5 && level <= 6) {
                //used to position left and right circles
                double xLeft = xMid - radius;
                double yLeft = yMid;

                double xRight = xMid + radius;
                double yRight = yMid;

                //draw circle to the left
                gContext.strokeOval(xLeft - radius / 2, yLeft - radius / 2, radius, radius);

                //draw circle to the right
                gContext.strokeOval(xRight - radius / 2, yRight - radius / 2, radius, radius);

                if(level > depth) {
                    drawCircles(xLeft, yLeft, radius / 2, depth+1);
                    drawCircles(xRight, yRight, radius / 2, depth+1);
                }
            }
        //}
    }

    public void OpenAboutPage(){
        System.out.println("Opening about page");
        About.display();
    }











}
