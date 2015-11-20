package shape;

import javafx.scene.canvas.Canvas;

/**
 * Created by milanbojovic on 11/20/15.
 * Uviversity graduate work "Fractal-Drawer"
 */
public class CantorSet extends FractalShape {

    float x1, x2, y;
    float lineSpacing = 30; //spacing between vertical lines

    public CantorSet(int maxDepth, Canvas canvas) {
        super(maxDepth, canvas);
        this.x1 = 40;
        this.x2 = (int)canvasWidth - 40;
        this.y = 50;
    }

    @Override
    void drawLevel0() {
        gContext.strokeLine((int)x1, (int) y, (int)x2, (int) y);
    }

    @Override
    public void drawNextDepthLevel() {
        if(getCurrentDepth() != getMaxDepth()) {
            currentDepthInc();
            drawLevel0();
            drawCantorset(1, x1, x2, y);
        }
    }

    @Override
    public void drawPrevDepthLevel() {
        if(getCurrentDepth() > 0){
            clearCanvas();
            currentDepthDec();
            drawLevel0();
            drawCantorset(1, x1, x2, y);
        }
    }

    public void drawCantorset(int n, double x1, double x2, double y){
        if(getCurrentDepth() > 0) {
            if (getCurrentDepth() <= getMaxDepth()) {
                double newY = y + lineSpacing;
                double  newLineLen = (x2 - x1)/3;

                //draw left line
                gContext.strokeLine(x1, newY, x1+newLineLen, newY);

                //draw right line
                gContext.strokeLine(x2-newLineLen, newY, x2, newY);


                if(getCurrentDepth() > n) {
                    drawCantorset(n+1, x1, x1+newLineLen, newY);
                    drawCantorset(n+1, x2-newLineLen, x2, newY);
                }
            }
        }
    }

}
