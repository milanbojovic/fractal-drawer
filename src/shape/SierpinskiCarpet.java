package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
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
        this.side = Math.min(canvasWidth, canvasHeight);

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
            gContext.fillRect(x - side/2, y - side/2, side, side);
            drawCarpet(getCurrentDepth(), x, y, side);
            updateFractalDimension(getCurrentDepth());
        }
    }

    @Override
    public void drawPrevDepthLevel() {
        if(getCurrentDepth() > 0){
            currentDepthDec();
            clearCanvas();
            gContext.fillRect(x - side/2, y - side/2, side, side);
            drawCarpet(getCurrentDepth(), x, y, side);
            updateFractalDimension(getCurrentDepth());
        }
    }

    public void drawCarpet(int n, double x, double y, double side) {
        double sub = side / 3; // length of sub-squares

        if(n >= 0) {
            gContext.setFill(Color.WHITE);
            gContext.fillRect(x - sub/2, y - sub/2, sub, sub);
            gContext.setFill(Color.BLACK);

            if(n > 0){
                // now draw eight sub-gaskets around the white square
                drawCarpet(n - 1, x - sub,           y - sub, sub);
                drawCarpet(n - 1, x ,     y - sub, sub);
                drawCarpet(n - 1, x + sub, y - sub, sub);
                drawCarpet(n - 1, x-sub,           y, sub);
                drawCarpet(n - 1, x + sub, y, sub);
                drawCarpet(n - 1, x - sub,           y + sub, sub);
                drawCarpet(n - 1, x ,     y + sub, sub);
                drawCarpet(n - 1, x + sub, y + sub, sub);
            }
        }
    }
}