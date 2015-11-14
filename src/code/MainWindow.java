package code;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainWindow {

    @FXML
    private Button dugme;

    public void method1(){
        System.out.println("method1");

    }

    public void method2(){
        System.out.println("method2");

    }

    public void OpenAboutPage(){
        System.out.println("Opening about page");
        About.display();
    }
}