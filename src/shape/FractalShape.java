package shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;

/**
 * Created by milanbojovic on 11/18/15.
 */
public abstract class FractalShape {

    private int maxDepth;
    private int currentDepth;
    private int previousDepth;
    protected Canvas canvas;
    protected double canvasWidth, canvasHeight;
    protected GraphicsContext gContext;

    abstract void drawLevel0();
    public abstract void drawNextDepthLevel();
    public abstract void drawPrevDepthLevel();

    public FractalShape(int maxDepth, Canvas canvas) {
        this.maxDepth = maxDepth;
        this.canvas = canvas;
        gContext = canvas.getGraphicsContext2D();
        currentDepth = -1;

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
        if(currentDepth < maxDepth) previousDepth = currentDepth++;
        System.out.println("(INC EVENT) currDep=" + currentDepth + " (" + previousDepth + ")");
    }

    public void currentDepthDec() {
        if(currentDepth > 0)
            previousDepth = currentDepth--;
        System.out.println("(DEC EVENT) currDep=" + currentDepth + " (" + previousDepth + ")");
    }

    protected void clearCanvas(){
        gContext.clearRect(0, 0, canvasWidth, canvasHeight);
    }
}
