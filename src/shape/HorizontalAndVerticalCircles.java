package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 11/18/15.
 * University graduate work "Fractal-Drawer"
 */
public class HorizontalAndVerticalCircles extends FractalShape {
    double radius;      //radius of first circle
    double xMid, yMid;  //center point (x,y) of circle

    public HorizontalAndVerticalCircles(int maxDepth, Canvas canvas, WebView webView) {
        super(maxDepth, canvas, webView);

        radius  = Math.min(canvasWidth,canvasHeight)/4;
        xMid    = canvasWidth/2;
        yMid    = canvasHeight/2;

        p = 4;
        s = 2;
    }

    @Override
    public void drawCurrentLevel() {
        drawLevel0();
        draw(xMid, yMid, radius, 1);
        updateFractalDimension(getCurrentDepth());
    }

    @Override
    void drawLevel0() {
        //gContext.strokeLine(0, yMid, canvasWidth-1, yMid);
        gContext.strokeOval(xMid-radius,yMid-radius,radius*2,radius*2);
    }

    void draw(double xMid, double yMid, double radius, int n) {
        if(getCurrentDepth() > 0) {
            if (getCurrentDepth() <= getMaxDepth()) {

                //used to position left and right circles
                double xLeft = xMid - radius;   //draw left circle
                double xRight = xMid + radius;  //draw right circle

                double yUp = yMid - radius;     //draw upper circle
                double yDown = yMid + radius;   //draw down circle


                //draw circle to the left
                gContext.strokeOval(xLeft - radius / 2, yMid - radius / 2, radius, radius);

                //draw circle to the right
                gContext.strokeOval(xRight - radius / 2, yMid - radius / 2, radius, radius);

                //draw upper circle
                gContext.strokeOval(xMid - radius / 2, yUp - radius / 2, radius, radius);
                //draw bottom circle
                gContext.strokeOval(xMid - radius / 2, yDown - radius / 2, radius, radius);

                if(getCurrentDepth() > n) {
                    draw(xLeft, yMid, radius / 2, n+1);
                    draw(xRight, yMid, radius / 2, n+1);

                    draw(xMid, yUp, radius / 2, n+1);
                    draw(xMid, yDown, radius / 2, n+1);
                }
            }
        }
    }
}
