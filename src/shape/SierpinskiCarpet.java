package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 22.11.15..
 */
public class SierpinskiCarpet extends FractalShape{

    double x, y, side;

    public SierpinskiCarpet(int maxDepth, Canvas canvas, WebView webView) {
        super(maxDepth, canvas, webView);
        this.x = canvasWidth / 2;
        this.y = canvasHeight / 2;
        this.side = canvasWidth - canvasWidth*0.25;

        this.p = 8;
        this.s = 3;
    }

    @Override
    void drawLevel0() {}

    @Override
    public void drawNextDepthLevel() {
        if(getCurrentDepth() != getMaxDepth()) {
            currentDepthInc();
            clearCanvas();
            drawCarpet(getCurrentDepth(), x - side/2, y - side/2, side);
            updateFractalDimension(getCurrentDepth());
        }

    }

    @Override
    public void drawPrevDepthLevel() {
        if(getCurrentDepth() > 0){
            currentDepthDec();
            clearCanvas();
            drawCarpet(getCurrentDepth(), x - side/2, y - side/2, side);
            updateFractalDimension(getCurrentDepth());
        }
    }

    public void drawCarpet(int n, double x, double y, double side) {
        if(n >= 0){
            double sub = side / 3; // length of sub-squares
            gContext.fillRect(x + sub, y + sub, sub - 1, sub - 1);

            // now draw eight sub-gaskets around the white square
            drawCarpet(n - 1, x,           y, sub);
            drawCarpet(n - 1, x + sub,     y, sub);
            drawCarpet(n - 1, x + 2 * sub, y, sub);
            drawCarpet(n - 1, x,           y + sub, sub);
            drawCarpet(n - 1, x + 2 * sub, y + sub, sub);
            drawCarpet(n - 1, x,           y + 2 * sub, sub);
            drawCarpet(n - 1, x + sub,     y + 2 * sub, sub);
            drawCarpet(n - 1, x + 2 * sub, y + 2 * sub, sub);
        }
    }
}
