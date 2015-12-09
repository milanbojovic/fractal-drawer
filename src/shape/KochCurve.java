package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 21.11.15..
 */
public class KochCurve extends FractalShape {

    double x1, y1, x5, y5;

    public KochCurve(int maxDepth, Canvas canvas, WebView webView) {
        super(maxDepth, canvas, webView);

        double square = Math.min(canvasWidth, canvasHeight);

        x1 = 10;
        y1 = canvasHeight/3*2;
        x5 = canvasWidth - 10;
        y5 = y1;

        p = 4;
        s = 3;
    }

    @Override
    void drawLevel0() {gContext.strokeLine(x1, y1, x5, y5);}

    @Override
    public void drawCurrentLevel() {
        if(getCurrentDepth() == 0) drawLevel0();
        else drawKochCurve(getCurrentDepth(), x1, y1, x5, y5);
        updateFractalDimension(getCurrentDepth());
    }

    public void drawKochCurve(int n, double x1, double  y1, double x5, double y5){
        double deltaX, deltaY, x2, y2, x3, y3, x4, y4;

        if(n > 0) {
            deltaX = x5 - x1;
            deltaY = y5 - y1;

            //Calculate new dots
            x2 = x1 + deltaX / 3;
            y2 = y1 + deltaY / 3;

            x3 = (int) (0.5 * (x1+x5) - (Math.sqrt(3)/6) * (y1-y5));
            y3 = (int) (0.5 * (y1+y5) - (Math.sqrt(3)/6) * (x5-x1));

            x4 = x1 + 2 * deltaX /3;
            y4 = y1 + 2 * deltaY /3;

            drawKochCurve(n-1, x1, y1, x2, y2);
            drawKochCurve(n-1, x2, y2, x3, y3);
            drawKochCurve(n-1, x3, y3, x4, y4);
            drawKochCurve(n-1, x4, y4, x5, y5);
        }
        else{
            gContext.strokeLine(x1, y1, x5, y5);
        }
    }
}
