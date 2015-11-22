package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 21.11.15..
 */
public class KochSnowFlake extends FractalShape {

    double x1, y1, x2, y2, x3, y3;
    double spacing = 100;

    public KochSnowFlake(int maxDepth, Canvas canvas, WebView webView) {
        super(maxDepth, canvas, webView);
        setCurrentDepth(0);

        x1 = spacing;
        y1 = (int)canvasHeight - spacing - spacing/6*5;
        x2 = (int)canvasWidth - spacing;
        y2 = y1;
        x3 = (int)canvasWidth / 2;
        y3 = spacing - spacing/6*5;

        p = 4;
        s = 3;
    }

    @Override
    void drawLevel0() {
        //not used
    }

    @Override
    public void drawNextDepthLevel() {
        if(getCurrentDepth() != getMaxDepth()) {
            currentDepthInc();
            clearCanvas();
            drawSnowFlake(getCurrentDepth(), x1, y1, x2, y2);
            drawSnowFlake(getCurrentDepth(), x2, y2, x3, y3);
            drawSnowFlake(getCurrentDepth(), x3, y3, x1, y1);
            updateFractalDimension(getCurrentDepth());
        }

    }

    @Override
    public void drawPrevDepthLevel() {
        if(getCurrentDepth() > 1){
            currentDepthDec();
            clearCanvas();
            drawSnowFlake(getCurrentDepth(), x1, y1, x2, y2);
            drawSnowFlake(getCurrentDepth(), x2, y2, x3, y3);
            drawSnowFlake(getCurrentDepth(), x3, y3, x1, y1);
            updateFractalDimension(getCurrentDepth());
        }
    }

    public void drawSnowFlake(int n, double x1, double  y1, double x5, double y5){
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

            x3 = (int) (0.5 * (x1+x5) + Math.sqrt(3)/6 * (y1-y5));
            y3 = (int) (0.5 * (y1+y5) + Math.sqrt(3)/6 * (x5-x1));

            x4 = x1 + 2 * deltaX /3;
            y4 = y1 + 2 * deltaY /3;

            drawSnowFlake(n-1, x1, y1, x2, y2);
            drawSnowFlake(n-1, x2, y2, x3, y3);
            drawSnowFlake(n-1, x3, y3, x4, y4);
            drawSnowFlake(n-1, x4, y4, x5, y5);
        }
    }
}
