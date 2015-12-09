package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 21.11.15..
 */
public class KochSnowFlake extends FractalShape {

    double x1, y1, x2, y2, x3, y3;
    double spacing;
    String mode;

    public KochSnowFlake(int maxDepth, Canvas canvas, WebView webView, String mode) {
        super(maxDepth, canvas, webView);
        double a;
        setCurrentDepth(0);
        this.mode = mode;

        double squareSize = Math.min(canvasHeight, canvasWidth);
        spacing = squareSize * 0.15;

        if(mode.equals("snowflake")){
            spacing = squareSize * 0.15;
            x1 = canvasWidth/2 - squareSize/2 + spacing;
            y1 = canvasHeight/2 + squareSize/2 - spacing*2;
            x2 = canvasWidth/2 + squareSize/2 - spacing;
            a  = x2 - x1;
            y2 = y1;
            x3 = x1 + a/2;
            y3 = y1 - (Math.sqrt(Math.pow(a,2) - Math.pow(a/2,2)));
        }
        else{
            x1 = canvasWidth/2 - squareSize/2 + spacing/2;
            y1 = canvasHeight/2 + squareSize/2 - spacing;
            x2 = canvasWidth/2 + squareSize/2 - spacing/2;
            a  = x2 - x1;
            y2 = y1;
            x3 = x1 + a/2;
            y3 = y1 - (Math.sqrt(Math.pow(a,2) - Math.pow(a/2,2)));
        }

        p = 4;
        s = 3;
    }

    @Override
    void drawLevel0() {
        //not used
    }

    @Override
    public void drawCurrentLevel() {
        drawSnowFlake(getCurrentDepth(), x1, y1, x2, y2);
        drawSnowFlake(getCurrentDepth(), x2, y2, x3, y3);
        drawSnowFlake(getCurrentDepth(), x3, y3, x1, y1);
        updateFractalDimension(getCurrentDepth());
    }

    public void drawSnowFlake(int n, double x1, double  y1, double x5, double y5){
        double deltaX, deltaY, x2, y2, x3, y3, x4, y4;

        if(n == 1){
            gContext.strokeLine(x1, y1, x5, y5);
        }
        else{
            deltaX = x5 - x1;
            deltaY = y5 - y1;

            x2 = x1 + deltaX / 3;
            y2 = y1 + deltaY / 3;

            if(mode == "snowflake") {
                //Calculate new dots
                x3 = (int) (0.5 * (x1+x5) + Math.sqrt(3)/6 * (y1-y5));
                y3 = (int) (0.5 * (y1+y5) + Math.sqrt(3)/6 * (x5-x1));
            }
            else{   //mode == antisnowflake
                //Calculate new dots
                x3 = (int) (0.5 * (x1+x5) - (Math.sqrt(3)/6) * (y1-y5));
                y3 = (int) (0.5 * (y1+y5) - (Math.sqrt(3)/6) * (x5-x1));
            }

            x4 = x1 + 2 * deltaX /3;
            y4 = y1 + 2 * deltaY /3;

            drawSnowFlake(n-1, x1, y1, x2, y2);
            drawSnowFlake(n-1, x2, y2, x3, y3);
            drawSnowFlake(n-1, x3, y3, x4, y4);
            drawSnowFlake(n-1, x4, y4, x5, y5);
        }
    }
}
