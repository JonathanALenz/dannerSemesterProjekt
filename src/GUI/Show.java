package GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Show {
    public static void vis(String message) {

        Stage window = new Stage();
        GridPane layout = new GridPane();
        window.setTitle("Tekster");

        window.setMinWidth(700);
        window.setMinHeight(450);
        layout.setPadding(new Insets(2,10,10,10));
        layout.setVgap(8);
        layout.setHgap(6);
        window.initModality(Modality.APPLICATION_MODAL);

        TextArea main_body = new TextArea(message);

        main_body.setStyle("-fx-font-size: 12px;" +
                           "-fx-background-color: gray;" +
                           "-fx-font-family: fantasy");

        main_body.setPrefSize(600,400);
        GridPane.setConstraints(main_body,0,1);
        main_body.setWrapText(true);


        Button button_return = new Button("Return");
        GridPane.setConstraints(button_return,1,2);
        button_return.setOnAction(e->window.close());

        layout.getChildren().addAll(main_body, button_return);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}

