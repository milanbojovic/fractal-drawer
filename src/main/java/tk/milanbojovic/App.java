package tk.milanbojovic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * JavaFX App
 */

public class App extends Application {
    private static Scene scene;

    public final int SCREEN_WIDTH   = 1024;
    public final int SCREEN_HEIGHT  = 768;

    static {
        Path cacheDir = Paths.get(System.getProperty("user.dir"), "target", "javafx-cache");
        try {
            Files.createDirectories(cacheDir);
            System.setProperty("javafx.cachedir", cacheDir.toString());
        } catch (IOException e) {
            System.err.println("Unable to prepare JavaFX cache dir: " + e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainWindow.fxml"), SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
