package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 21.11.15..
 */
public class Hexaflake extends FractalShape {

    double centerX, centerY;
    double X1, X2, Y1, Y2;
    double REDUCE, square;

    public Hexaflake(int maxDepth, Canvas canvas, WebView webView) {
        super(maxDepth, canvas, webView);

        square = Math.min(canvasHeight, canvasWidth);

        centerX = canvasWidth/2;
        centerY = canvasHeight/2;

        X1 = 0.5;
        X2 = 0.25;
        Y1 = Math.sqrt(3)/4;
        Y2 = 0;
        REDUCE = 0.3333;

        p = 7;
        s = 3;
    }

    @Override
    void drawLevel0() {
    }

    @Override
    public void drawCurrentLevel() {
        drawHexaflake(getCurrentDepth(), centerX, centerY, square - square * 0.1);
        updateFractalDimension(getCurrentDepth());
    }

    public void drawHexaflake(int n, double cx, double  cy, double sz){
        double x0, y0, x1, y1, x2, y2, x3, y3, x4, y4, x5, y5;

        x0 = cx;
        y0 = cy + (X1 * sz);
        x1 = cx - (Y1 *sz);
        y1 = cy + (X2 * sz);
        x2 = cx - (Y1 *sz);
        y2 = cy - (X2 * sz);
        x3 = cx;
        y3 = cy - (X1 * sz);
        x4 = cx + (Y1 *sz);
        y4 = cy - (X2 * sz);
        x5 = x4;
        y5 = cy + (X2 * sz);

        double[] xArr = {x0, x1, x2, x3, x4, x5};
        double[] yArr = {y0, y1, y2, y3, y4, y5};

        if(n > 0) {
            drawHexaflake(n-1, cx, cy, sz * REDUCE);
            drawHexaflake(n-1, x0, cy + (y0 - y3)/3, sz * REDUCE);
            drawHexaflake(n-1, cx - (x4 - x3)/3*2, cy + (y0 - y3)/6, sz * REDUCE);
            drawHexaflake(n-1, cx - (x4 - x3)/3*2, cy - (y0 - y3)/6, sz * REDUCE);
            drawHexaflake(n-1, x3, cy - (y0 - y3)/3, sz * REDUCE);
            drawHexaflake(n-1, cx + (x4 - x3)/3*2, cy - (y0 - y3)/6, sz * REDUCE);
            drawHexaflake(n-1, cx + (x4 - x3)/3*2, cy + (y0 - y3)/6, sz * REDUCE);
        }
        else {
            gContext.fillPolygon(xArr, yArr, 6);
        }
    }
}