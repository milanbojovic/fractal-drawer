package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 * Created by milanbojovic on 21.11.15..
 */
public class KochCurve extends FractalShape {

    double x1, y1, x5, y5;

    public KochCurve(int maxDepth, Canvas canvas) {
        super(maxDepth, canvas);
        x1 = 10;
        y1 = canvasHeight/2;
        x5 = canvasWidth - 10;
        y5 = y1;
    }

    @Override
    void drawLevel0() {
        gContext.strokeLine(x1, y1, x5, y5);
    }

    @Override
    public void drawNextDepthLevel() {
        if(getCurrentDepth() != getMaxDepth()) {
            currentDepthInc();
            drawLevel0();
            drawSnowFlake(1, x1, y1, x5, y5);
        }
    }

    @Override
    public void drawPrevDepthLevel() {
        if(getCurrentDepth() > 0){
            clearCanvas();
            currentDepthDec();
            drawLevel0();
            drawSnowFlake(1, x1, y1, x5, y5);
        }
    }

    public void drawSnowFlake(int n, double x1, double  y1, double x5, double y5){
        double deltaX, deltaY, x2, y2, x3, y3, x4, y4;

        if(getCurrentDepth() > 0) {
            if (getCurrentDepth() <= getMaxDepth()) {

                deltaX = x5 - x1;
                deltaY = y5 - y1;

                //Calculate new dots
                x2 = x1 + deltaX / 3;
                y2 = y1 + deltaY / 3;

                x3 = (int) (0.5 * (x1+x5) + Math.sqrt(3) * (y1-y5)/6);
                y3 = (int) (0.5 * (y1+y5) + Math.sqrt(3) * (x5-x1)/6);

                x4 = x1 + 2 * deltaX /3;
                y4 = y1 + 2 * deltaY /3;

                //draw new shape
                gContext.strokeLine(x1, y1, x2, y2);
                gContext.strokeLine(x2, y2, x3, y3);
                gContext.strokeLine(x3, y3, x4, y4);
                gContext.strokeLine(x4, y4, x5, y5);

                gContext.setStroke(Color.WHITE);
                gContext.strokeLine(x2, y2, x4, y4);
                gContext.setStroke(Color.BLACK);

                if(getCurrentDepth() > n) {
                    drawSnowFlake(n+1, x1, y1, x2, y2);
                    drawSnowFlake(n+1, x2, y2, x3, y3);
                    drawSnowFlake(n+1, x3, y3, x4, y4);
                    drawSnowFlake(n+1, x4, y4, x5, y5);
                }
            }
        }

    }
}
