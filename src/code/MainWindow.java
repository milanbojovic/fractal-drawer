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
import shape.CantorSet;
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

    @FXML
    private ListView<String> listView;
    private ObservableList<String> listViewData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas = new Canvas(300, 300);
        gContext = canvas.getGraphicsContext2D();
        canwrap.getChildren().add(canvas);

        listViewData.add("Horizontal Circles");
        listViewData.add("Serpinski Triangle");
        listViewData.add("Cantor Set");

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
                return new HorizontalCircles(7, canvas);

            case "Serpinski Triangle":
                return new SerpinskiTriangle(7, canvas);

            case "Cantor Set":
                return new CantorSet(17, canvas);

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