package code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public final int SCREEN_WIDTH   = 1024;
    public final int SCREEN_HEIGHT  = 768;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Fractal Drawer");

        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
