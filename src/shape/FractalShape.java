package shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 11/18/15.
 */
public abstract class FractalShape {

    protected double p;
    protected double s;

    private int maxDepth;
    private int currentDepth;
    protected Canvas canvas;
    protected WebView webView;
    protected double canvasWidth, canvasHeight;
    protected GraphicsContext gContext;

    abstract void drawLevel0();

    public void drawNextDepthLevel(){
        if(getCurrentDepth() != getMaxDepth()){
            currentDepthInc();
            clearCanvas();
            drawCurrentLevel();
        }
    }

    public void drawPrevDepthLevel(){
        if(getCurrentDepth() > 0){
            clearCanvas();
            currentDepthDec();
            drawCurrentLevel();
        }
    }

    public abstract void drawCurrentLevel();

    public FractalShape(int maxDepth, Canvas canvas, WebView webView) {
        this.maxDepth = maxDepth;
        this.canvas = canvas;
        this.webView = webView;
        gContext = canvas.getGraphicsContext2D();
        currentDepth = -1;

        initCanvasWidthHeight();
    }

    public void initCanvasWidthHeight(){
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
    }

    public void setCurrentDepth(int currentDepth) {
        this.currentDepth = currentDepth;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public int getCurrentDepth() {
        return currentDepth;
    }

    public void currentDepthInc() {
        currentDepth++;
        System.out.println("(INC EVENT) currDep=" + currentDepth);
    }

    public void currentDepthDec() {
        if (currentDepth > 0)
            currentDepth--;
        System.out.println("(DEC EVENT) currDep=" + currentDepth);
    }

    protected void clearCanvas() {
        gContext.clearRect(0, 0, canvasWidth, canvasHeight);
    }

    public void updateFractalDimension(int level){
        webView.getEngine().loadContent(
                "      <math xmlns=\"http://www.w3.org/1998/Math/MathML\">\t\n" +
                        "         <mrow>\n" +
                        "            <mi>d</mi>\n" +
                        "            <mtext> </mtext>\n" +
                        "            <mo>=</mo>\n" +
                        "            <mtext> </mtext>\n" +
                        "            <mfrac>\n" +
                        "               <mrow>\n" +
                        "                  <mtext>log</mtext>\n" +
                        "                  <mo>&InvisibleTimes;</mo>\n" +
                        "                     <mfenced>\n" +
                        "                           <mtext>P</mtext>\n" +
                        "                     </mfenced>\n" +
                        "               </mrow>\n" +
                        "               <mrow>\n" +
                        "                  <mtext>log</mtext>\n" +
                        "                  <mo>&InvisibleTimes;</mo>\n" +
                        "                  <mfenced>\n" +
                        "                     <mtext>S</mtext>\n" +
                        "                  </mfenced>\n" +
                        "               </mrow>\n" +
                        "            </mfrac>\n" +
                        "            <mtext> </mtext>\n" +
                        "            <mo>=</mo>\n" +
                        "            <mtext> </mtext>\n" +
                        "            <mfrac>\n" +
                        "               <mrow>\n" +
                        "                  <mtext>log</mtext>\n" +
                        "                  <mo>&InvisibleTimes;</mo>\n" +
                        "                  <mfenced>\n" +
                        "                     <mtext>" + p + "</mtext>\n" +
                        "                  </mfenced>\n" +
                        "               </mrow>\n" +
                        "               <mrow>\n" +
                        "                  <mtext>log</mtext>\n" +
                        "                  <mo>&InvisibleTimes;</mo>\n" +
                        "                  <mfenced>\n" +
                        "                     <mtext>" + s + "</mtext>\n" +
                        "                  </mfenced>\n" +
                        "               </mrow>\n" +
                        "            </mfrac>\n" +
                        "            <mtext> </mtext>\n" +
                        "            <mo>=</mo>\n" +
                        "            <mtext> </mtext>\n" +
                        "            <mn>" + Math.log(p) / Math.log(s) + "</mn>\n" +
                        "         </mrow>\n" +
                        "      </math>"
        );
    }

    public void updateFractalDimension(String mathMl){
        webView.getEngine().loadContent(mathMl);
    }

    public static String emptyFractalDimension =
            "      <math xmlns=\"http://www.w3.org/1998/Math/MathML\">\t\n" +
            "         <mrow>\n" +
            "            <mi>d</mi>\n" +
            "            <mtext> </mtext>\n" +
            "            <mo>=</mo>\n" +
            "            <mtext> </mtext>\n" +
            "            <mfrac>\n" +
            "               <mrow>\n" +
            "                  <mtext>log</mtext>\n" +
            "                  <mo>&InvisibleTimes;</mo>\n" +
            "                     <mfenced>\n" +
            "                           <mtext>P</mtext>\n" +
            "                     </mfenced>\n" +
            "               </mrow>\n" +
            "               <mrow>\n" +
            "                  <mtext>log</mtext>\n" +
            "                  <mo>&InvisibleTimes;</mo>\n" +
            "                  <mfenced>\n" +
            "                     <mtext>S</mtext>\n" +
            "                  </mfenced>\n" +
            "               </mrow>\n" +
            "            </mfrac>\n";
}