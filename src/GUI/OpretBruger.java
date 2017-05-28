package GUI;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Logic.*;

public class OpretBruger
{

    public void createUser(Logic logic)
    {

        Stage window = new Stage();
        GridPane layout = new GridPane();
        window.setTitle("Opret Bruger");


        layout.setPadding(new Insets(8,10,10,10));
        layout.setVgap(8);
        layout.setHgap(6);
        window.initModality(Modality.APPLICATION_MODAL);

        Label label_brugernavn = new Label("Brugernavn: ");
        layout.setConstraints(label_brugernavn,0,0);

        TextField textField_brugernavn = new TextField();
        layout.setConstraints(textField_brugernavn,1,0);

        Label label_password = new Label("Kodeord: ");
        layout.setConstraints(label_password,0,1);

        PasswordField passwordField_password = new PasswordField();
        layout.setConstraints(passwordField_password, 1,1);

        Label label_gentag_password = new Label("Gentag kodeord: ");
        layout.setConstraints(label_gentag_password,0,2);

        PasswordField passwordField_gentag_password = new PasswordField();
        layout.setConstraints(passwordField_gentag_password, 1,2);

        Label label_level = new Label("Rolle: ");
        layout.setConstraints(label_level,0,3);

        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("Admin", "Bruger");
        choiceBox.getSelectionModel().select("Bruger");
        layout.setConstraints(choiceBox, 1,3);

        Button button_opret = new Button("Opret");
        layout.setConstraints(button_opret,1,4);
        layout.setHalignment(button_opret, HPos.RIGHT);

        button_opret.setOnAction(e->
        {
            if (!textField_brugernavn.getText().equals(""))
            {
                if(!passwordField_password.getText().equals(""))
                {
                    if (!logic.checkUserName(textField_brugernavn.getText()))
                    {
                        if (passwordField_password.getText().equals(passwordField_gentag_password.getText()))
                        {
                            logic.createNewUser(textField_brugernavn.getText(), passwordField_password.getText(),
                                    choiceBox.getValue());
                            NotificationMessage notificationMessage1 = new NotificationMessage(textField_brugernavn.getText() + " " +
                                    "er nu oprettet som " + choiceBox.getValue(), "Ny bruger");
                            window.close();
                        }
                        else
                        {
                            NotificationMessage notificationMessage = new NotificationMessage("Kodeord skal være ens.", "Fejl");
                        }
                    }
                    else
                    {
                        NotificationMessage notificationMessage = new NotificationMessage("Brugernavn findes allerede, vælg et andet.", "Fejl");
                    }
                }
                else
                {
                    NotificationMessage notificationMessage3 = new NotificationMessage("Kodeord må ikke være tomt.", "Fejl");
                }
            }
            else
            {
                NotificationMessage notificationMessage4 =new NotificationMessage("Brugernavn må ikke være tomt.","Fejl");
            }
        });


        Button button_return = new Button("Tilbage");
        layout.setConstraints(button_return,0,4);
        button_return.setOnAction(e->window.close());


        layout.getChildren().addAll(label_brugernavn, label_gentag_password, label_level, label_password,
                textField_brugernavn, passwordField_password, passwordField_gentag_password, choiceBox,
                button_opret, button_return);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}