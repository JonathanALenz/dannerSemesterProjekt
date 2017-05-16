package GUI;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Logic.*;

/**
 * Created by bruger on 11-05-2017.
 */
public class OpretBruger
{

    public void createUser()
    {

        Stage window = new Stage();
        GridPane layout = new GridPane();
        window.setTitle("Opret Bruger");


        layout.setPadding(new Insets(8,10,10,10));
        layout.setVgap(8);
        layout.setHgap(6);
        window.initModality(Modality.APPLICATION_MODAL);

        ErrorMessage errorMessage = new ErrorMessage();

        Label label_brugernavn = new Label("Brugernavn: ");
        GridPane.setConstraints(label_brugernavn,0,0);

        TextField textField_brugernavn = new TextField();
        GridPane.setConstraints(textField_brugernavn,1,0);

        Label label_password = new Label("Password: ");
        GridPane.setConstraints(label_password,0,1);

        PasswordField passwordField_password = new PasswordField();
        GridPane.setConstraints(passwordField_password, 1,1);

        Label label_gentag_password = new Label("Gentag password: ");
        GridPane.setConstraints(label_gentag_password,0,2);

        PasswordField passwordField_gentag_password = new PasswordField();
        GridPane.setConstraints(passwordField_gentag_password, 1,2);

        Label label_level = new Label("Rolle: ");
        GridPane.setConstraints(label_level,0,3);

        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("Admin", "Bruger");
        GridPane.setConstraints(choiceBox, 1,3);

        Button button_opret = new Button("Opret");
        GridPane.setConstraints(button_opret,1,4);
        GridPane.setHalignment(button_opret, HPos.RIGHT);
        button_opret.setOnAction(e->
        {
            if (passwordField_password.getText().equals(passwordField_gentag_password.getText()))
            {
                Logic logic = new Logic();
                logic.createNewUser(textField_brugernavn.getText(), passwordField_gentag_password.getText(),
                                    choiceBox.getValue());
                ErrorMessage errorMessage1 = new ErrorMessage();
                errorMessage1.wrongCredentials(textField_brugernavn.getText() + " er nu oprettet som " +
                                               choiceBox.getValue());
                window.close();
            }
            else
            {
                errorMessage.wrongCredentials("Password stemmer ikke overens.");
            }
        });


        Button button_return = new Button("Return");
        GridPane.setConstraints(button_return,0,4);
        button_return.setOnAction(e->window.close());


        layout.getChildren().addAll(label_brugernavn, label_gentag_password, label_level, label_password,
                textField_brugernavn, passwordField_password, passwordField_gentag_password, choiceBox,
                button_opret, button_return);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}