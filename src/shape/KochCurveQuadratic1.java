package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 21.11.15..
 */
public class KochCurveQuadratic1 extends FractalShape {

    double x1, y1, x6, y6;

    public KochCurveQuadratic1(int maxDepth, Canvas canvas, WebView webView) {
        super(maxDepth, canvas, webView);

        x1 = canvasWidth*0.03;
        x6 = canvasWidth-canvasWidth*0.03;

        y1 = canvasHeight - (canvasHeight - (x6-x1)/2)/2;
        y6 = y1;

        p = 5;
        s = 3;
    }

    @Override
    void drawLevel0() {gContext.strokeLine(x1, y1, x6, y6);}

    @Override
    public void drawCurrentLevel() {
        if(getCurrentDepth() == 0) drawLevel0();
        else drawKochCurve(getCurrentDepth(), x1, y1, x6, y6);
        updateFractalDimension(getCurrentDepth());
    }


    public int left(int a, int degree){
        return a - degree;
    }

    public int right(int a, int degree){
        return a + degree;
    }

    public void drawKochCurve(int n, double x1, double  y1, double x6, double y6){
        double length, x2, y2, x3, y3, x4, y4, x5, y5;
        int angle;

        if(n > 0) {
            if(x6-x1 != 0){
                //horisontal line
                length = (x6-x1);
                angle = 0;
            }
            else{
                //vertical line
                length = Math.abs(y6-y1);
                if(y1 > y6) angle = -90;
                else        angle =  90;
            }

            //Calculate new dots
            double piDiv180 = Math.PI / 180;
            double newLen = length/3;

            x2 = x1 + Math.cos(angle * piDiv180) * newLen;
            y2 = y1 + Math.sin(angle * piDiv180) * newLen;
            //draw new shape
            angle = left(angle, 90);
            x3 = x2 + Math.cos(angle * piDiv180) * newLen;
            y3 = y2 + Math.sin(angle * piDiv180) * newLen;
            angle = right(angle, 90);
            x4 = x3 + Math.cos(angle * piDiv180) * newLen;
            y4 = y3 + Math.sin(angle * piDiv180) * newLen;
            angle = right(angle, 90);
            x5 = x4 + Math.cos(angle * piDiv180) * newLen;
            y5 = y4 + Math.sin(angle * piDiv180) * newLen;

            drawKochCurve(n-1, x1, y1, x2, y2);
            drawKochCurve(n-1, x2, y2, x3, y3);
            drawKochCurve(n-1, x3, y3, x4, y4);
            drawKochCurve(n-1, x4, y4, x5, y5);
            drawKochCurve(n-1, x5, y5, x6, y6);
        }
        else if(n == 0){
            gContext.strokeLine(x1, y1, x6, y6);
        }
    }
}
