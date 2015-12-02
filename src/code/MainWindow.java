package code;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import shape.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable{

    public AnchorPane canwrap, fracDimensionWrap;

    public Canvas canvas;
    public WebView webView;
    private GraphicsContext gContext;
    private FractalShape selectedShape;


    @FXML
    private ListView<String> listView;
    private ObservableList<String> listViewData = FXCollections.observableArrayList();


    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Init Canvas
        canvas = new Canvas();
        gContext = canvas.getGraphicsContext2D();
        canwrap.getChildren().add(canvas);

        //Init Fractal dimension WebView
        webView = new WebView();
        fracDimensionWrap.getChildren().add(webView);

        //Init Fractal shapes list
        listViewData.add("Horizontal Circles");
        listViewData.add("Horizontal and Vertical Circles");
        listViewData.add("Sierpinski Triangle");
        listViewData.add("Sierpinski Carpet");
        listViewData.add("Cantor Set");
        listViewData.add("Koch Curve");
        listViewData.add("Koch Curve (Quadratic_1)");
        listViewData.add("Koch Curve (Quadratic_2)");
        listViewData.add("Dragon Curve");
        listViewData.add("Twin Dragon Curve");
        listViewData.add("Levy C curve");
        listViewData.add("Koch Snowflake");
        listViewData.add("Koch Anti Snowflake");
        listViewData.add("Koch Coastline");
        listViewData.add("Mandelbrot Set");
        listViewData.add("Tree");
        listViewData.add("Tree 60 degree");
        listViewData.add("Tree 90 degree");
        listViewData.add("Pythagoras tree");
        listViewData.add("Hilbert Curve");
        listViewData.add("TSquare");

        listView.setItems(listViewData);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {
                resetCanvas();
                resizeCanvas();
                selectedShape = createFractalObject(getCurrentSelection());
            }

        });

    }

    public void resizeCanvas(){
        canvas.setWidth(canwrap.getWidth());
        canvas.setHeight(canwrap.getHeight());
    }

    public void resetCanvas(){
        gContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if(selectedShape != null) selectedShape = null;
        webView.getEngine().loadContent(FractalShape.emptyFractalDimension);
    }

    @FXML
    public void drawNextDepthLevel(){
        if(selectedShape != null){
            resizeCanvas();
            selectedShape.drawNextDepthLevel();
        }
        else{
            System.out.println("Please select SHAPE from the list first!!!");
        }
    }

    @FXML
    public void drawPrevDepthLevel(){
        if(selectedShape != null){
            resizeCanvas();
            selectedShape.drawPrevDepthLevel();
        }
        else{
            System.out.println("Please select SHAPE from the list first!!!");
        }
    }

    FractalShape createFractalObject(String strShape){
        switch (strShape){
            case "Horizontal Circles":
                return new HorizontalCircles(7, canvas, webView);

            case "Sierpinski Triangle":
                return new SierpinskiTriangle(7, canvas, webView);

            case "Sierpinski Carpet":
                return new SierpinskiCarpet(5, canvas, webView);

            case "Cantor Set":
                return new CantorSet(7, canvas, webView);

            case "Horizontal and Vertical Circles":
                return new HorizontalAndVerticalCircles(7, canvas, webView);

            case "Koch Curve":
                return new KochCurve(6, canvas, webView);

            case "Koch Curve (Quadratic_1)":
                return new KochCurveQuadratic1(6, canvas, webView);

            case "Koch Curve (Quadratic_2)":
                return new KochCurveQuadratic2(6, canvas, webView);

            case "Dragon Curve":
                return new DragonCurve(17, canvas, webView, "single", "dragon");

            case "Twin Dragon Curve":
                return new DragonCurve(17, canvas, webView, "double", "dragon");

            case "Levy C curve":
                return new DragonCurve(17, canvas, webView, "single", "cCurve");

            case "Koch Snowflake":
                return new KochSnowFlake(7, canvas, webView, "snowflake");

            case "Koch Anti Snowflake":
                return new KochSnowFlake(7, canvas, webView, "antisnowflake");

            case "Koch Coastline":
                return new KochCoastline(9, canvas, webView);

            case "Mandelbrot Set":
                return new MandelbrotSet(7, canvas, webView);

            case "Tree":
                return new Tree(10, canvas, webView, 15, 37, .65);

            case "Tree 60 degree":
                return new Tree(10, canvas, webView, 0, 60, .50);

            case "Tree 90 degree":
                return new Tree(10, canvas, webView, 0, 90, .50);

            case "Pythagoras tree":
                return new PythagorasTree(14, canvas, webView);

            case "Hilbert Curve":
                return new HilbertCurve(9, canvas, webView);

            case "TSquare":
                return new TSquare(9, canvas, webView);

            default:
                System.out.println("Selected shape doesn't exist in list !!!");
                return null;
        }
    }

    private String getCurrentSelection(){
        return listView.getSelectionModel().getSelectedItem();
    }

    public void OpenAboutPage(){
        System.out.println("Opening about page");
        About.display();
    }
}