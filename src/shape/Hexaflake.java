package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 21.11.15..
 */
public class Hexaflake extends FractalShape {

    double centerX, centerY;
    double X1 = 0.5;
    double X2 = 0.25;
    double Y1 = Math.sqrt(3)/4;
    double Y2 = 0;
    double REDUCE = 0.3333;


    public Hexaflake(int maxDepth, Canvas canvas, WebView webView) {
        super(maxDepth, canvas, webView);

        centerX = canvasWidth/2;
        centerY = canvasHeight/2;

        X1 = 0.5;
        X2 = 0.25;
        Y1 = Math.sqrt(3)/4;
        Y2 = 0;
        REDUCE = 0.3333;

        //p = 4;
        //s = 3;
    }

    @Override
    void drawLevel0() {
    }

    @Override
    public void drawNextDepthLevel() {
        if(getCurrentDepth() != getMaxDepth()) {
            currentDepthInc();
            clearCanvas();
            drawHexaflake(getCurrentDepth(), centerX, centerY, canvasWidth/2);
            updateFractalDimension(getCurrentDepth());
        }
    }

    @Override
    public void drawPrevDepthLevel() {
        if(getCurrentDepth() > 0){
            clearCanvas();
            currentDepthDec();
            drawHexaflake(getCurrentDepth(), centerX, centerY, canvasWidth/2);
            updateFractalDimension(getCurrentDepth());
        }
    }

    public void drawHexaflake(int n, double cx, double  cy, double sz){
        double x0, y0, x1, y1, x2, y2, x3, y3, x4, y4, x5, y5;

        x0 = cx - (X1 * sz);
        y0 = cy;
        x1 = cx - (X2 * sz);
        y1 = cy  - (Y1 *sz);
        x2 = cx + (X2 * sz);
        y2 = cy  - (Y1 *sz);
        x3 =  cx + (X1 * sz);
        y3 = cy;
        x4 = x2;
        y4 = cy + (Y1 * sz);
        x5 = x1;
        y5 = cy + (Y1 * sz);

        double[] xArr = {x0, x1, x2, x3, x4, x5};
        double[] yArr = {y0, y1, y2, y3, y4, y5};

        if(n > 0) {
            drawHexaflake(n-1, cx, cy, sz * REDUCE);
            drawHexaflake(n-1, x0, y0, sz * REDUCE);
            drawHexaflake(n-1, x1, y1, sz * REDUCE);
            drawHexaflake(n-1, x2, y2, sz * REDUCE);
            drawHexaflake(n-1, x3, y3, sz * REDUCE);
            drawHexaflake(n-1, x4, y4, sz * REDUCE);
            drawHexaflake(n-1, x5, y5, sz * REDUCE);
        }
        else {
            gContext.strokePolygon(xArr, yArr, 6);
        }
    }
}