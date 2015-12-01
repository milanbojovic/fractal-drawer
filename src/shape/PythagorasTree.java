package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 21.11.15..
 */
public class PythagorasTree extends FractalShape {

    double x1, y1, x2, y2;
    double a;
    double tanphi = 1.0;

    public PythagorasTree(int maxDepth, Canvas canvas, WebView webView) {
        super(maxDepth, canvas, webView);
        a = 120;
        x1 = canvasWidth / 2 - a/2;
        y1 = canvasHeight - a/2;
        x2 = canvasWidth / 2 + a/2;
        y2 = y1;

        setCurrentDepth(0);
        p = 2;
        s = 1.41; //2/Sqrt(2)
    }

    @Override
    void drawLevel0() {}

    @Override
    public void drawNextDepthLevel() {
        if(getCurrentDepth() != getMaxDepth()) {
            currentDepthInc();
            clearCanvas();
            drawPythagorasTree(getCurrentDepth(), x1, y1, x2, y2);
            updateFractalDimension(getCurrentDepth());
        }
    }

    @Override
    public void drawPrevDepthLevel() {
        if(getCurrentDepth() > 0){
            clearCanvas();
            currentDepthDec();
            drawPythagorasTree(getCurrentDepth(), x1, y1, x2, y2);
            updateFractalDimension(getCurrentDepth());
        }
    }

    public void drawPythagorasTree(int n, double x1, double  y1, double x2, double y2){
        if(n > 0) {
                // (1) determine vertices
                double dx = x2 - x1;
                double dy = y1 - y2;
                double x3 = x1 - dy;
                double y3 = y1 - dx;
                double x4 = x2 - dy;
                double y4 = y2 - dx;

                //Drawing // (2) Square vollstndiges
                gContext.strokeLine((int)x1, (int)y1, (int)x2, (int)y2);
                gContext.strokeLine((int)x2, (int)y2, (int)x4, (int)y4);
                gContext.strokeLine((int)x4, (int)y4, (int)x3, (int)y3);
                gContext.strokeLine((int)x1, (int)y1, (int)x3, (int)y3);

                // Calculate (3) coordinates of the new vertex
                double v = (x3 + x4) / 2 - (dy / 2 * tanphi);
                double w = (y3 + y4) / 2 - (dx / 2 * tanphi);

                if  (dx * dx + dy * dy > 2) {
                    // (4) draw small Teilbume
                    drawPythagorasTree(n-1, x3, y3, v, w);
                    drawPythagorasTree(n-1, v, w, x4, y4);
                }
        }
    }
}
