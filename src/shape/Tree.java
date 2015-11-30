package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 22.11.15..
 */
public class Tree extends FractalShape{

    double bendAngle;
    double branchAngle;
    double branchRatio;

    double treeX    = canvasWidth/2;
    double treeY    = canvasHeight;
    double treeLen  = canvasHeight/3;


    public Tree(int maxDepth, Canvas canvas, WebView webView, double bendAngle, double branchAngle, double branchRatio) {
        super(maxDepth, canvas, webView);
        setCurrentDepth(1);

        this.bendAngle = Math.toRadians(bendAngle);
        this.branchAngle = Math.toRadians(branchAngle);
        this.branchRatio = branchRatio;

        this.p = 3;
        this.s = 2;
    }

    @Override

    void drawLevel0() {}

    @Override
    public void drawNextDepthLevel() {
        if(getCurrentDepth() != getMaxDepth()) {
            currentDepthInc();
            clearCanvas();
            drawTree(getCurrentDepth(), treeX, treeY,-Math.PI/2, treeLen);
            updateFractalDimension(getCurrentDepth());
        }
    }

    @Override
    public void drawPrevDepthLevel() {
        if(getCurrentDepth() > 1){
            currentDepthDec();
            clearCanvas();
            drawTree(getCurrentDepth(), treeX, treeY,-Math.PI/2, treeLen);
            updateFractalDimension(getCurrentDepth());
        }
    }

    public void drawTree(int n, double x, double y, double a, double branchRadius) {

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
