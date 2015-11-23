package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 22.11.15..
 */
public class Tree extends FractalShape{

    public Tree(int maxDepth, Canvas canvas, WebView webView) {
        super(maxDepth, canvas, webView);
        setCurrentDepth(1);
        //this.p = 8;
        //this.s = 3;
    }

    @Override

    void drawLevel0() {}

    @Override
    public void drawNextDepthLevel() {
        if(getCurrentDepth() != getMaxDepth()) {
            currentDepthInc();
            clearCanvas();
            drawTree(getCurrentDepth(), canvasWidth/2, canvasHeight,-Math.PI/2, 150);
            updateFractalDimension(getCurrentDepth());
        }

    }

    @Override
    public void drawPrevDepthLevel() {
        if(getCurrentDepth() > 1){
            currentDepthDec();
            clearCanvas();
            drawTree(getCurrentDepth(), canvasWidth/2, canvasHeight,-Math.PI/2, 150);
            updateFractalDimension(getCurrentDepth());
        }
    }

    public void drawTree(int n, double x, double y, double a, double branchRadius) {
        double bendAngle = Math.toRadians(15);
        double branchAngle = Math.toRadians(37);
        double branchRatio = .65;

        if(n > 0){
            double cx = x + Math.cos(a) * branchRadius;
            double cy = y + Math.sin(a) * branchRadius;
            gContext.strokeLine(x, y, cx, cy);
            if (n == 0) return;

            drawTree(n - 1, cx, cy, a + bendAngle - branchAngle, branchRadius * branchRatio);
            drawTree(n - 1, cx, cy, a + bendAngle + branchAngle, branchRadius * branchRatio);
            drawTree(n - 1, cx, cy, a + bendAngle, branchRadius * (1 - branchRatio));
        }
    }
}
