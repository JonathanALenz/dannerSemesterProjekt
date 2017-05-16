package GUI;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Logic.*;
import Database.*;

public class Login
{

    public void first_login (Stage primaryStage) throws Exception
    {
        Logic logic = new Logic();

        GridPane layout_login = new GridPane();

        layout_login.setPadding(new Insets(10,10,10,10));

        layout_login.setHgap(8);
        layout_login.setVgap(8);

        Label label_user = new Label("User");
        GridPane.setConstraints(label_user,0,0);

        Label label_password = new Label("Password");
        GridPane.setConstraints(label_password,0,1);

        TextField textField_user = new TextField();
        GridPane.setConstraints(textField_user,1,0);
        textField_user.setPromptText("Username");

        PasswordField textField_password = new PasswordField();
        GridPane.setConstraints(textField_password,1,1);

        textField_password.setPromptText("Password");

        Button button_login = new Button("Login");
        GridPane.setConstraints(button_login,1,2);

        FrontPage frontPage = new FrontPage();

        int[] user_level = {0};

        button_login.setOnAction(e->
        {
            //hver gang der trykkes på button_login, så bliver der checket om det er en admin der logger ind, hvis det er
            //så bliver user_level[0] sat til 1, hvilket er admin rettigheder
            User user = logic.userIsValidLogin(textField_user.getText(), textField_password.getText());

            if(user != null)
            {
                if (user.getRole().equalsIgnoreCase("admin"))
                {
                    user_level[0] = 1;
                }

                //main programmet starter op, henter user_level og sender det videre til main
                frontPage.mainProgram(user_level[0]);
                //lukker login viduet
                primaryStage.close();
            }
            else
            {
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.wrongCredentials("Forkert login. Prøv igen.");
            }
        });


        layout_login.getChildren().addAll(label_user, label_password, textField_user, textField_password, button_login);


        primaryStage.setTitle("Login");
        Scene scene_login = new Scene(layout_login);
        primaryStage.setScene(scene_login);
        primaryStage.show();
    }


}

