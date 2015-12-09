package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 1.12.15..
 */

public class HilbertCurve extends FractalShape {

    double x, y, xI, yI, xJ, yJ;
    double cx, cy = 0;
    boolean fresh;

    public HilbertCurve(int maxDepth, Canvas canvas, WebView webView) {
        super(maxDepth, canvas, webView);
        setCurrentDepth(0);

        x = 0;
        y = 0;

        xI = canvasWidth;
        xJ = y;

        yI = 0;
        yJ = canvasHeight;
        fresh = true;

        p = 4;
        s = 2;
    }

    @Override
    void drawLevel0() {}

    @Override
    public void drawCurrentLevel() {
        drawHilbertCurve(getCurrentDepth(), x, y, xI, xJ, yI, yJ);
        updateFractalDimension(getCurrentDepth());
    }

    public void drawHilbertCurve(int n, double x, double  y, double xI, double xJ, double yI, double yJ){
        /* x and y are the coordinates of the bottom left corner */
        /* xis & xjs are the i & j components of the unit x vector this frame */
        /* similarly yis and yjs */

        if (n == 0) {
            lineTo(x + (xI + yI) / 2, y + (xJ + yJ) / 2);
        }
        else {
            drawHilbertCurve(n-1, x, y, yI/2, yJ/2, xI/2, xJ/2);
            drawHilbertCurve(n-1, x + xI/2, y+xJ/2 ,xI/2, xJ/2, yI/2, yJ/2);
            drawHilbertCurve(n-1, x+xI/2+yI/2, y+xJ/2+yJ/2, xI/2, xJ/2, yI/2, yJ/2);
            drawHilbertCurve(n-1, x+xI/2+yI, y+xJ/2+yJ, -yI/2,-yJ/2, -xI/2, -xJ/2);
        }
    }

    void lineTo(double x, double y) {
        if (fresh) {
            fresh = false;
        } else {
            if((Math.abs(cx-x) == 0) || (Math.abs(cy-y) == 0)) gContext.strokeLine(cx, cy, x, y);
        }
        cx = x;
        cy = y;
    }
}
