package tk.milanbojovic.shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 11/18/15.
 * Uviversity graduate work "Fractal-Drawer"
 */

public class SierpinskiTriangle extends FractalShape {

    double x1, y1, x2, y2, x3, y3;
    double spacing, a;

    public SierpinskiTriangle(int maxDepth, Canvas canvas, WebView webView) {
        super(maxDepth, canvas, webView);
        init();
    }

    public void init(){
        double squareSize = Math.min(canvasHeight, canvasWidth);

        spacing = squareSize * 0.025;
        x1 = canvasWidth/2 - squareSize/2 + spacing;
        y1 = canvasHeight/2 + squareSize/2 - spacing*2;
        x2 = canvasWidth/2 + squareSize/2 - spacing;
        a  = x2 - x1;
        y2 = y1;
        x3 = x1 + a/2;
        y3 = y1 - (Math.sqrt(Math.pow(a,2) - Math.pow(a/2,2)));

        p = 3;
        s = 2;
    }

    public void drawCurrentLevel(){
        drawLevel0();
        //Call the recursive function that'll draw all the rest. The 3 corners of it are always the centers of sides, so they're averages
        if(getCurrentDepth() > 0) {
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
        updateFractalDimension(getCurrentDepth());
    }

    @Override
    void drawLevel0() {
        //Draw the 3 sides of the triangle as black lines
        gContext.strokeLine((int)x1, (int)y1, (int)x2, (int)y2);
        gContext.strokeLine((int)x1, (int)y1, (int)x3, (int)y3);
        gContext.strokeLine((int)x2, (int)y2, (int)x3, (int)y3);
    }

    //The recursive function that'll draw all the upside down triangles
    void subTriangle(int n, double x1, double y1, double x2, double y2, double x3, double y3)
    {
        //Draw the 3 sides as black lines
        gContext.strokeLine(x1, y1, x2, y2);
        gContext.strokeLine(x1, y1, x3, y3);
        gContext.strokeLine(x2, y2, x3, y3);

        //Calls itself 3 times with new corners, but only if the current number of recursions is smaller than the maximum depth
        if(n < getMaxDepth() && n < getCurrentDepth())
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
}
