package tk.milanbojovic.shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 11/20/15.
 * Uviversity graduate work "Fractal-Drawer"
 */
public class CantorSet extends FractalShape {

    double x1, x2, y;
    float lineSpacing = 30; //spacing between vertical lines

    public CantorSet(int maxDepth, Canvas canvas, WebView webView) {
        super(maxDepth, canvas, webView);


        double square = Math.min(canvasWidth, canvasHeight);

        this.x1 = canvasWidth/2 - square/2 + square*0.1;
        this.x2 = canvasWidth/2 + square/2 - square*0.1;
        this.y = square/3;

        p = 2;
        s = 3;
    }

    @Override
    void drawLevel0() {
        gContext.strokeLine(x1, y, x2, y);
    }

    @Override
    public void drawCurrentLevel() {
        drawLevel0();
        drawCantorset(1, x1, x2, y);
        updateFractalDimension(getCurrentDepth());
    }

    public void drawCantorset(int n, double x1, double x2, double y){
        if(getCurrentDepth() > 0) {
            if (getCurrentDepth() <= getMaxDepth()) {
                y += lineSpacing;
                double  newLineLen = (x2 - x1)/3;

                //draw left line
                gContext.strokeLine(x1, y, x1+newLineLen, y);

                //draw right line
                gContext.strokeLine(x2-newLineLen, y, x2, y);


                if(getCurrentDepth() > n) {
                    drawCantorset(n+1, x1, x1+newLineLen, y);
                    drawCantorset(n+1, x2-newLineLen, x2, y);
                }
            }
        }
    }

}
