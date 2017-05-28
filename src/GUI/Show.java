package GUI;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Show {
    public void vis(String message) {

        Stage window = new Stage();
        GridPane layout = new GridPane();
        window.setTitle("Tekster");

        window.setMinHeight(450);
        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(8);
        layout.setHgap(6);
        window.initModality(Modality.APPLICATION_MODAL);

        TextArea main_body = new TextArea(message);

        main_body.setStyle("-fx-font-size: 12px;" +
                           "-fx-background-color: gray;" +
                           "-fx-font-family: fantasy");

        main_body.setPrefSize(600,400);
        layout.setConstraints(main_body,0,0);
        main_body.setWrapText(true);

        Button button_return = new Button("Tilbage");
        layout.setConstraints(button_return,0,1);
        layout.setHalignment(button_return, HPos.RIGHT);
        button_return.setOnAction(e->window.close());

        layout.getChildren().addAll(main_body, button_return);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}