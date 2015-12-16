package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.web.WebView;

import java.util.Random;

/**
 * Created by milanbojovic on 21.11.15..
 */
public class KochCoastline extends FractalShape {

    double x1, y1, x2, y2, x3, y3;
    double spacing = 150;
    Random rand;

    public KochCoastline(int maxDepth, Canvas canvas, WebView webView) {
        super(maxDepth, canvas, webView);
        setCurrentDepth(0);

        x1 = spacing;
        y1 = (int)canvasHeight - spacing - spacing/6*5;
        x2 = (int)canvasWidth - spacing;
        y2 = y1;
        x3 = (int)canvasWidth / 2;
        y3 = spacing - spacing/6 * 5;

        rand = new Random();
    }

    @Override
    void drawLevel0() {
    }

    public void drawPrevDepthLevel(){
        if(getCurrentDepth() > 1){
            clearCanvas();
            currentDepthDec();
            drawCurrentLevel();
        }
    }

    @Override
    public void drawCurrentLevel() {
        drawCoast(getCurrentDepth(), x1, y1, x2, y2);
        drawCoast(getCurrentDepth(), x2, y2, x3, y3);
        drawCoast(getCurrentDepth(), x3, y3, x1, y1);
    }

    public void drawCoast(int n, double x1, double  y1, double x5, double y5){
        double deltaX, deltaY, x2, y2, x3, y3, x4, y4;

        if(n == 1){
            gContext.strokeLine(x1, y1, x5, y5);
        }
        else{
            deltaX = x5 - x1;
            deltaY = y5 - y1;

            //Calculate new dots
            x2 = x1 + deltaX / 3;
            y2 = y1 + deltaY / 3;

            x3 = (int) (0.5 * (x1+x5) + Math.sqrt(3)/(rand.nextInt(10)+3) * (y1-y5));
            y3 = (int) (0.5 * (y1+y5) + Math.sqrt(3)/(rand.nextInt(10)+3) * (x5-x1));

            x4 = x1 + 2 * deltaX /3;
            y4 = y1 + 2 * deltaY /3;

            drawCoast(n-1, x1, y1, x2, y2);
            drawCoast(n-1, x2, y2, x3, y3);
            drawCoast(n-1, x3, y3, x4, y4);
            drawCoast(n-1, x4, y4, x5, y5);
        }
    }
}
