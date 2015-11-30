package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 21.11.15..
 */
public class KochCurveQuadratic2 extends FractalShape {

    double x1, y1, x9, y9;

    public KochCurveQuadratic2(int maxDepth, Canvas canvas, WebView webView) {
        super(maxDepth, canvas, webView);
        x1 = canvasWidth * 0.040;
        y1 = canvasHeight / 2;
        x9 = canvasWidth - canvasWidth * 0.050;
        y9 = y1;

        p = 5;
        s = 3;
    }

    @Override
    void drawLevel0() {gContext.strokeLine(x1, y1, x9, y9);}

    @Override
    public void drawNextDepthLevel() {
        if(getCurrentDepth() != getMaxDepth()) {
            currentDepthInc();
            if(getCurrentDepth() == 0) drawLevel0();
            else drawKochCurve(getCurrentDepth(), x1, y1, x9, y9);
            updateFractalDimension(getCurrentDepth());
        }
    }

    @Override
    public void drawPrevDepthLevel() {
        if(getCurrentDepth() > 0){
            clearCanvas();
            currentDepthDec();
            if(getCurrentDepth() == 0) drawLevel0();
            else drawKochCurve(getCurrentDepth(), x1, y1, x9, y9);
            updateFractalDimension(getCurrentDepth());
        }
    }

    public int left(int a, int degree){
        return a - degree;
    }

    public int right(int a, int degree){
        return a + degree;
    }

    public void drawKochCurve(int n, double x1, double  y1, double x9, double y9){
        double length, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6, x7, y7, x8, y8;
        int angle;

        if(n > 0) {
            if(x9-x1 != 0){
                //horisontal line
                length = (x9-x1)/4;
                angle = 0;
            }
            else{
                //vertical line
                length = Math.abs(y9-y1)/4;
                if(y1 > y9) angle = -90;
                else        angle =  90;
            }

            //Calculate new dots
            x2 = x1 + Math.cos(angle * Math.PI / 180) * length;
            y2 = y1 + Math.sin(angle * Math.PI / 180) * length;
            //draw new shape
            angle = left(angle, 90);
            x3 = x2 + Math.cos(angle * Math.PI / 180) * length;
            y3 = y2 + Math.sin(angle * Math.PI / 180) * length;
            angle = right(angle, 90);
            x4 = x3 + Math.cos(angle * Math.PI / 180) * length;
            y4 = y3 + Math.sin(angle * Math.PI / 180) * length;
            angle = right(angle, 90);
            x5 = x4 + Math.cos(angle * Math.PI / 180) * length;
            y5 = y4 + Math.sin(angle * Math.PI / 180) * length;

            x6 = x5 + Math.cos(angle * Math.PI / 180) * length;
            y6 = y5 + Math.sin(angle * Math.PI / 180) * length;
            angle = left(angle, 90);
            x7 = x6 + Math.cos(angle * Math.PI / 180) * length;
            y7 = y6 + Math.sin(angle * Math.PI / 180) * length;
            angle = left(angle, 90);
            x8 = x7 + Math.cos(angle * Math.PI / 180) * length;
            y8 = y7 + Math.sin(angle * Math.PI / 180) * length;

            //erase one line from old fractal
            gContext.setStroke(Color.WHITE);
            gContext.setLineWidth(gContext.getLineWidth()+1);
            gContext.strokeLine(x1, y1, x9, y9);
            gContext.setLineWidth(gContext.getLineWidth()-1);
            gContext.setStroke(Color.BLACK);



            drawKochCurve(n-1, x1, y1, x2, y2);
            drawKochCurve(n-1, x2, y2, x3, y3);
            drawKochCurve(n-1, x3, y3, x4, y4);
            drawKochCurve(n-1, x4, y4, x5, y5);
            drawKochCurve(n-1, x5, y5, x6, y6);
            drawKochCurve(n-1, x6, y6, x7, y7);
            drawKochCurve(n-1, x7, y7, x8, y8);
            drawKochCurve(n-1, x8, y8, x9, y9);
        }
        else if(n == 0){
            gContext.strokeLine(x1, y1, x9, y9);
        }
    }
}
