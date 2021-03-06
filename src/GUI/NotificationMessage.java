package GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NotificationMessage
{
    private Stage window;

    public Stage getWindow() {
        return window;
    }

    public NotificationMessage(){}

    public NotificationMessage(String message, String title)
    {
        this.window = new Stage();
        wrongCredentials(message, title);
    }

    public void wrongCredentials(String message, String title)
    {
        GridPane layout = new GridPane();
        window.setTitle(title);

        layout.setPadding(new Insets(2,10,10,10));
        layout.setVgap(8);
        layout.setHgap(6);
        window.initModality(Modality.APPLICATION_MODAL);

        Label label_error_msg = new Label(message);
        GridPane.setConstraints(label_error_msg,0,0);

        Button button_return = new Button("Return");
        GridPane.setConstraints(button_return,0,1);

        button_return.setOnAction(e->window.close());

        layout.getChildren().addAll(label_error_msg, button_return);

        Scene scene = new Scene(layout, 230, 100);
        window.setScene(scene);

        if (!message.equals("henter...")) {
            window.showAndWait();
        }
        else
        {
           window.show();
        }
    }
}



