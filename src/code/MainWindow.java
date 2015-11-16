package code;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable{

    public AnchorPane canwrap;

    public Canvas canvas;
    private GraphicsContext gContext;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas = new Canvas(200,200);
        gContext = canvas.getGraphicsContext2D();
        /*double w = canvas.getWidth();
        double h = canvas.getHeight();*/

        gContext.setStroke(Color.BLUE);
        gContext.setLineWidth(5);
        gContext.strokeLine(0, 0, 40, 40);

        canwrap.getChildren().add(canvas);

        System.out.println("Initialise method has been executed");
    }

    @FXML
    public void drawRectangle(){
        System.out.println("Draw rectangle method called!");

        gContext.setFill(Color.GREEN);
        gContext.setStroke(Color.BLUE);
        gContext.setLineWidth(5);
        gContext.strokeLine(40, 10, 10, 40);
        gContext.fillOval(10, 60, 30, 30);
        gContext.strokeOval(60, 60, 30, 30);
        gContext.fillRoundRect(110, 60, 30, 30, 10, 10);
        gContext.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gContext.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gContext.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gContext.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gContext.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gContext.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gContext.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gContext.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gContext.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gContext.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
    }

    public void OpenAboutPage(){
        System.out.println("Opening about page");
        About.display();
    }











}
