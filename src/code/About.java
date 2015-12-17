package code;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Created by milanbojovic on 11/14/15.
 */
public class About{

    public static void display(){
        final Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("About");
        window.setMinWidth(500);
        window.setMinHeight(150);

        Label label = new Label();
        label.setText(  "Faculty of Electrical Engineering\n" +
                        "\tMentor: Branko Malesevic\n" +
                        "\tStudent: Milan Bojovic\n"
                      );


        Button closeButton = new Button("Close the window");

        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                window.close();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);

    window.setScene(scene);
        window.showAndWait();
    }
}
