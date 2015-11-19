package shape;

import javafx.scene.canvas.Canvas;

/**
 * Created by milanbojovic on 11/18/15.
 * University graduate work "Fractal-Drawer"
 */
public class HorizontalCircles extends FractalShape {
    double radius;      //radius of first circle
    double xMid, yMid;  //center point (x,y) of circle

    public HorizontalCircles(int maxDepth, Canvas canvas) {
        super(maxDepth, canvas);

        radius  = canvasWidth/5;
        xMid    = canvasWidth/2;
        yMid    = canvasHeight/2;
    }

    @Override
    public void drawNextDepthLevel() {
        if(getCurrentDepth() != getMaxDepth()) {
            currentDepthInc();
            drawLevel0();
            draw(xMid, yMid, radius, 1);
        }
    }

    @Override
    public void drawPrevDepthLevel() {
        if(getCurrentDepth() > 0){
            clearCanvas();
            currentDepthDec();
            drawLevel0();
            draw(xMid, yMid, radius, 1);
        }
    }

    @Override
    void drawLevel0() {
        gContext.strokeLine(0, yMid, canvasWidth-1, yMid);
        gContext.strokeOval(xMid-radius,yMid-radius,radius*2,radius*2);
    }

    void draw(double xMid, double yMid, double radius, int n) {
        if(getCurrentDepth() > 0) {
            if (getCurrentDepth() <= getMaxDepth()) {

                //used to position left and right circles
                double xLeft = xMid - radius;
                double yLeft = yMid;

                double xRight = xMid + radius;
                double yRight = yMid;

                //draw circle to the left
                gContext.strokeOval(xLeft - radius / 2, yLeft - radius / 2, radius, radius);

                //draw circle to the right
                gContext.strokeOval(xRight - radius / 2, yRight - radius / 2, radius, radius);

                if(getCurrentDepth() > n) {
                    draw(xLeft, yLeft, radius / 2, n+1);
                    draw(xRight, yRight, radius / 2, n+1);
                }
            }
        }
    }
}
