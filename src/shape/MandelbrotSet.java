package shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

/**
 * Created by milanbojovic on 21.11.15..
 */
public class MandelbrotSet extends FractalShape {

    // Values for the Mandelbro set
    private static double MANDELBROT_RE_MIN = -2;
    private static double MANDELBROT_RE_MAX = 1;
    private static double MANDELBROT_IM_MIN = -1.2;
    private static double MANDELBROT_IM_MAX = 1.2;

    public MandelbrotSet(int maxDepth, Canvas canvas, WebView webView) {
        super(maxDepth, canvas, webView);
    }

    @Override
    void drawLevel0() {
    }

    @Override
    public void drawNextDepthLevel() {
            clearCanvas();
            drawMandelbrot(
                    MANDELBROT_RE_MIN,
                    MANDELBROT_RE_MAX,
                    MANDELBROT_IM_MIN,
                    MANDELBROT_IM_MAX
                    );
            updateFractalDimension(getCurrentDepth());
    }

    @Override
    public void drawPrevDepthLevel() {
        drawNextDepthLevel();
    }

    private void drawMandelbrot(double reMin, double reMax, double imMin, double imMax) {
        double precision = Math.max((reMax - reMin) / canvasWidth, (imMax - imMin) / canvasHeight);
        int convergenceSteps = 50;
        for (double c = reMin, xR = 0; xR < canvasWidth; c = c + precision, xR++) {
            for (double ci = imMin, yR = 0; yR < canvasHeight; ci = ci + precision, yR++) {
                double convergenceValue = checkConvergence(ci, c, convergenceSteps);
                double t1 = convergenceValue / convergenceSteps;
                double c1 = Math.min(255 * 2 * t1, 255);
                double c2 = Math.max(255 * (2 * t1 - 1), 0);

                if (convergenceValue != convergenceSteps) {
                    gContext.setFill(Color.WHITE); // Convergence Color
                    //gContext.setFill(Color.color(c2 / 255.0, c1 / 255.0, c2 / 255.0));
                } else {
                    gContext.setFill(Color.BLACK);
                    //gContext.setFill(Color.PURPLE); // Convergence Color
                }
                gContext.fillRect(xR, yR, 1, 1);
            }
        }
        gContext.setFill(Color.BLACK); //revert color to base
    }

    /**
     * Checks the convergence of a coordinate (c, ci) The convergence factor
     * determines the color of the point.
     */
    private int checkConvergence(double ci, double c, int convergenceSteps) {
        double z = 0;
        double zi = 0;
        for (int i = 0; i < convergenceSteps; i++) {
            double ziT = 2 * (z * zi);
            double zT = z * z - (zi * zi);
            z = zT + c;
            zi = ziT + ci;

            if (z * z + zi * zi >= 4.0) {
                return i;
            }
        }
        return convergenceSteps;
    }
}
