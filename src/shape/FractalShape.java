package shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;

/**
 * Created by milanbojovic on 11/18/15.
 */
public abstract class FractalShape {

    private int maxDepth;
    private int currentDepth;
    private int currentDepthHistory;
    protected Canvas canvas;
    protected double canvasWidth, canvasHeight;
    protected GraphicsContext gContext;

    abstract void drawLevel0();
    public abstract void drawNextDepthLevel();
    public abstract void drawPrevDepthLevel();

    public FractalShape(int maxDepth, Canvas canvas) {
        this.maxDepth = maxDepth;
        this.canvas = canvas;
        this.gContext = canvas.getGraphicsContext2D();

        canvasWidth = canvas.getWidth();
        canvasHeight= canvas.getHeight();
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    protected int getCurrentDepth() {
        return currentDepth;
    }

    public void currentDepthInc() {
        if(currentDepth < maxDepth)
            currentDepthHistory = currentDepth++;
        System.out.println("(INC) currDep=" + currentDepth + " (" + currentDepthHistory + ")");
    }

    public void currentDepthDec() {
        if(currentDepth > 0)
            //currentDepthHistory = currentDepthHistory < currentDepth ? currentDepth -= 2 : currentDepth--;
            currentDepthHistory = --currentDepth;
        System.out.println("(DEC) currDep=" + currentDepth + " (" + currentDepthHistory + ")");
    }
}
