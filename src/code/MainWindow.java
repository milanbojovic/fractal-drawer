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
/*            //Draw first circle and horisontal line
            gContext.strokeLine(0, yMid, cWidth-1, yMid);
            gContext.strokeOval(xMid-radius,yMid-radius,radius*2,radius*2);*/
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

        //if(level == 0){
            //Draw first line
            gContext.setStroke(Color.BLUE);
            gContext.setLineWidth(2);

            //Draw first circle and horisontal line
            gContext.strokeLine(0, yMid, cWidth-1, yMid);
            gContext.strokeOval(xMid-radius,yMid-radius,radius*2,radius*2);
        //}
        //else{
            drawCircles(xMid, yMid, radius, 1);
        //}
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
    }


    @FXML
    void setDrawSiepenskiTriangleMinus(){
        resizeCanvas();
        if(level >= 1) {
            gContext.clearRect(0, 0, cWidth, cHeight);

            //Draw first triangle
            /*gContext.strokeLine((int)x1, (int)y1, (int)x2, (int)y2);
            gContext.strokeLine((int)x1, (int)y1, (int)x3, (int)y3);
            gContext.strokeLine((int)x2, (int)y2, (int)x3, (int)y3);*/

            if(prevLevel < level) {
                prevLevel = level;
                level-=2;
            }
            else {
                prevLevel = level;
                level--;
            }
            drawSierpinski(10, (int)cHeight - 10, (int)cWidth - 10, (int)cHeight - 10, (int)cWidth / 2, 10); //Call the sierpinski function (works with any corners inside the screen)
        }


    }

    @FXML
    void drawSerpinskiTrianglePlus(){
        resizeCanvas();

        drawSierpinski(10, (int)cHeight - 10, (int)cWidth - 10, (int)cHeight - 10, (int)cWidth / 2, 10); //Call the sierpinski function (works with any corners inside the screen)
        prevLevel = level;
        level++;
    }

    //This function will draw only one triangle, the outer triangle (the only not upside down one), and then start the recursive function
    void drawSierpinski(float x1, float y1, float x2, float y2, float x3, float y3)
    {
        //Draw the 3 sides of the triangle as black lines
        gContext.strokeLine((int)x1, (int)y1, (int)x2, (int)y2);
        gContext.strokeLine((int)x1, (int)y1, (int)x3, (int)y3);
        gContext.strokeLine((int)x2, (int)y2, (int)x3, (int)y3);

        //Call the recursive function that'll draw all the rest. The 3 corners of it are always the centers of sides, so they're averages
        if(level > 0) {
            subTriangle
                    (
                            1, //This represents the first recursion
                            (x1 + x2) / 2, //x coordinate of first corner
                            (y1 + y2) / 2, //y coordinate of first corner
                            (x1 + x3) / 2, //x coordinate of second corner
                            (y1 + y3) / 2, //y coordinate of second corner
                            (x2 + x3) / 2, //x coordinate of third corner
                            (y2 + y3) / 2  //y coordinate of third corner
                    );
        }
    }

    //depth is the number of recursive steps
    int depth = 7;

    //The recursive function that'll draw all the upside down triangles
    void subTriangle(int n, float x1, float y1, float x2, float y2, float x3, float y3)
    {
        //Draw the 3 sides as black lines
        gContext.strokeLine((int)x1, (int)y1, (int)x2, (int)y2);
        gContext.strokeLine((int)x1, (int)y1, (int)x3, (int)y3);
        gContext.strokeLine((int)x2, (int)y2, (int)x3, (int)y3);

        //Calls itself 3 times with new corners, but only if the current number of recursions is smaller than the maximum depth
        if(n < depth && n < level)
        {
            //Smaller triangle 1
            subTriangle
                    (
                            n+1, //Number of recursions for the next call increased with 1
                            (x1 + x2) / 2 + (x2 - x3) / 2, //x coordinate of first corner
                            (y1 + y2) / 2 + (y2 - y3) / 2, //y coordinate of first corner
                            (x1 + x2) / 2 + (x1 - x3) / 2, //x coordinate of second corner
                            (y1 + y2) / 2 + (y1 - y3) / 2, //y coordinate of second corner
                            (x1 + x2) / 2, //x coordinate of third corner
                            (y1 + y2) / 2  //y coordinate of third corner
                    );
            //Smaller triangle 2
            subTriangle
                    (
                            n+1, //Number of recursions for the next call increased with 1
                            (x3 + x2) / 2 + (x2 - x1) / 2, //x coordinate of first corner
                            (y3 + y2) / 2 + (y2 - y1) / 2, //y coordinate of first corner
                            (x3 + x2) / 2 + (x3 - x1) / 2, //x coordinate of second corner
                            (y3 + y2) / 2 + (y3 - y1) / 2, //y coordinate of second corner
                            (x3 + x2) / 2, //x coordinate of third corner
                            (y3 + y2) / 2  //y coordinate of third corner
                    );
            //Smaller triangle 3
            subTriangle
                    (
                            n+1, //Number of recursions for the next call increased with 1
                            (x1 + x3) / 2 + (x3 - x2) / 2, //x coordinate of first corner
                            (y1 + y3) / 2 + (y3 - y2) / 2, //y coordinate of first corner
                            (x1 + x3) / 2 + (x1 - x2) / 2, //x coordinate of second corner
                            (y1 + y3) / 2 + (y1 - y2) / 2, //y coordinate of second corner
                            (x1 + x3) / 2, //x coordinate of third corner
                            (y1 + y3) / 2  //y coordinate of third corner
                    );
        }
    }



























    public void OpenAboutPage(){
        System.out.println("Opening about page");
        About.display();
    }











}
