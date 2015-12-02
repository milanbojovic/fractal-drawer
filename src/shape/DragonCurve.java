package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 21.11.15..
 */
public class DragonCurve extends FractalShape {

    double x1, y1, x3, y3;
    double angleDiff;
    String mode1;
    String mode2;

    public DragonCurve(int maxDepth, Canvas canvas, WebView webView, String mode1, String mode2) {
        super(maxDepth, canvas, webView);

        if(mode2.equals("dragon")){
            x1 = canvasWidth*0.3;
            y1 = canvasHeight/2;
            x3 = canvasWidth-canvasWidth*0.3;
            y3 = y1;
            angleDiff = 45;
        }
        else{
            x1 = canvasWidth*0.3;
            y1 = canvasHeight/3*2;
            x3 = canvasWidth-canvasWidth*0.3;
            y3 = y1;
            angleDiff = 45;
        }
        this.mode1 = mode1;
        this.mode2 = mode2;
    }

    @Override
    void drawLevel0() {gContext.strokeLine(x1, y1, x3, y3);}

    @Override
    public void drawNextDepthLevel() {
        if(getCurrentDepth() != getMaxDepth()) {
            clearCanvas();
            currentDepthInc();
            if(getCurrentDepth() == 0) drawLevel0();
            else {
                if (mode1.equals("single")){
                    drawDragonCurve(getCurrentDepth(), x1, y1, x3, y3, true, Color.BLACK);
                }
                else {
                    drawDragonCurve(getCurrentDepth(), x1, y1, x3, y3, true, Color.GREENYELLOW);
                    drawDragonCurve(getCurrentDepth(), x1, y1, x3, y3, false, Color.BLUE);
                }
            }
            updateFractalDimension(getCurrentDepth());
        }
    }

    @Override
    public void drawPrevDepthLevel() {
        if(getCurrentDepth() > 0){
            clearCanvas();
            currentDepthDec();
            if(getCurrentDepth() == 0) drawLevel0();
            else {
                if (mode1.equals("single")){
                    drawDragonCurve(getCurrentDepth(), x1, y1, x3, y3, true, Color.BLACK);
                }
                else {
                    drawDragonCurve(getCurrentDepth(), x1, y1, x3, y3, false, Color.BLUE);
                    drawDragonCurve(getCurrentDepth(), x1, y1, x3, y3, true, Color.GREENYELLOW);
                }
            }
            updateFractalDimension(getCurrentDepth());
        }
    }

    private void drawDragonCurve(int n, double x1, double y1, double x3, double y3, boolean isClockwise, Color color) {

        if(n > 0) {
            // find vector of original line
            double deltaX = x3 - x1;
            double deltaY = y3 - y1;

            // represent in polar coordinates
            double magnitude = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            double angle = Math.toDegrees(Math.atan(deltaX / deltaY));

            // calculate new distance from origin
            magnitude /= Math.sqrt(2);

            // adjust results of trig function for negative angles
            if (deltaY < 0) { // if y1 is below y3
                angle += 180;
            }
            if (deltaX < 0) { // if x1 is left of x3
                angle += 360;
            }

            // when isClockwise is true, increase angle size (generally by 45deg),
            // else decrease, wrapping around 360deg if result is negative, resulting
            // in polar coordinates for third point
            if(mode2.equals("dragon")){

                if (isClockwise) {
                    angle += angleDiff;
                }
                else {
                    angle -= angleDiff;
                }
            }
            else {
                    angle += angleDiff;
            }

            if (angle < 0) {
                angle += 360;
            }

            // convert back to cartesian vector
            double x2 = magnitude * Math.sin(Math.toRadians(angle));
            double y2 = magnitude * Math.cos(Math.toRadians(angle));

            // recursive calls to repeat process with new coordinates
            drawDragonCurve(n-1, x1, y1, x1 + x2, y1 + y2, true, color);
            drawDragonCurve(n-1, x1 + x2, y1 + y2, x3, y3, false, color);
        }
        else {
            gContext.setStroke(color);
            gContext.strokeLine((int)x1, (int)y1, (int)(x3), (int)(y3));
            gContext.setStroke(Color.BLACK);
        }
    }
}
