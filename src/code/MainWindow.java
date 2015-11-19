package code;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import shape.FractalShape;
import shape.HorizontalCircles;
import shape.SerpinskiTriangle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable{

    public AnchorPane canwrap;

    public Canvas canvas;
    private GraphicsContext gContext;
    private FractalShape selectedShape;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas = new Canvas(300, 300);
        gContext = canvas.getGraphicsContext2D();
        canwrap.getChildren().add(canvas);
    }


    public void resizeCanvas(){
        canvas.setWidth(canwrap.getWidth());
        canvas.setHeight(canwrap.getHeight());
    }

    @FXML
    public void drawNextDepthLevel(){
        resizeCanvas();
        createFractalObject();
        selectedShape.drawNextDepthLevel();
    }

    @FXML
    public void drawPrevDepthLevel(){
        resizeCanvas();
        createFractalObject();
        selectedShape.drawPrevDepthLevel();
    }

    void createFractalObject(){
        if(selectedShape == null) selectedShape = new HorizontalCircles(7, canvas);
        //if(selectedShape == null) selectedShape = new SerpinskiTriangle(7, canvas);

    }

    public void OpenAboutPage(){
        System.out.println("Opening about page");
        About.display();
    }

}
