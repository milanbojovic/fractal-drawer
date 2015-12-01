package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 21.11.15..
 */
public class TSquare extends FractalShape {

    double x1, y1, a;

    public TSquare(int maxDepth, Canvas canvas, WebView webView) {
        super(maxDepth, canvas, webView);
        x1 = canvasWidth / 2;
        y1 = canvasHeight/ 2;

        a = Math.min(canvasHeight, canvasWidth) * 0.5;

        p = 4;
        s = 2;
    }

    @Override
    void drawLevel0() {}

    @Override
    public void drawNextDepthLevel() {
        if(getCurrentDepth() != getMaxDepth()) {
            currentDepthInc();
            clearCanvas();
            drawTSquare(getCurrentDepth(), x1, y1, a);
            updateFractalDimension(getCurrentDepth());
        }
    }

    @Override
    public void drawPrevDepthLevel() {
        if(getCurrentDepth() > 0){
            clearCanvas();
            currentDepthDec();
            drawTSquare(getCurrentDepth(), x1, y1, a);
            updateFractalDimension(getCurrentDepth());
        }
    }

    public void drawTSquare(int n, double x, double  y, double a){
        double x1, y1, x2, y2, x3, y3, x4, y4;

        gContext.fillRect(x - a/2, y - a/2, a, a);

        x1 = x - a/2;
        y1 = y - a/2;

        x2 = x + a/2;
        y2 = y1;

        x3 = x1;
        y3 = y + a/2;

        x4 = x2;
        y4 = y3;

        if(n > 0){
            drawTSquare(n-1, x1, y1, a/2);
            drawTSquare(n-1, x2, y2, a/2);
            drawTSquare(n-1, x3, y3, a/2);
            drawTSquare(n-1, x4, y4, a/2);
        }
    }
}
