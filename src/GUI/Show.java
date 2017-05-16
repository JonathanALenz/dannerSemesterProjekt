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
        window.setTitle("tekster");

        window.setMinWidth(1000);
        window.setMinHeight(650);
        layout.setPadding(new Insets(2,10,10,10));
        layout.setVgap(8);
        layout.setHgap(6);
        window.initModality(Modality.APPLICATION_MODAL);

        TextArea main_body = new TextArea(message);


        //kopi start
        // header_body.setStyle(""
        //       + "-fx-font-size: 30px;"
        //     + "-fx-font-style: italic;"
        //   + "-fx-font-weight: bold;"
        // + "-fx-font-family: fantasy;"
        // + "-fx-text-fill: blue;"
        // + "-fx-background-color: aqua");

        main_body.setStyle(""
                + "-fx-font-size: 18px;"
                + "-fx-font-style: italic;"
                //  + "-fx-font-weight: bold;"
                + "-fx-font-family: fantasy;");
        //kopi end

        main_body.setPrefSize(1000,600);
        GridPane.setConstraints(main_body,0,1);
        main_body.setWrapText(true);

        //Label label_main = new Label(message );
        //GridPane.setConstraints(label_main,0,1);



        Button button_return = new Button("Return");
        GridPane.setConstraints(button_return,1,2);
        button_return.setOnAction(e->window.close());



        layout.getChildren().addAll(main_body, button_return);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}

