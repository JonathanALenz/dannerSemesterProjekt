package GUI;

import Database.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Logic.*;

public class Login
{

    public void first_login(Stage primaryStage)
    {
        Logic logic = new Logic();
        FrontPage frontPage = new FrontPage(logic);

        //Bruger gridpane som layout for at alle felter står ved siden af og under hinanden.
        GridPane layout_login = new GridPane();

        //sørger for at alle elementer i vinduet bliver sat 10 pixels fra top/bund/venstre/højre.
        layout_login.setPadding(new Insets(10,10,10,10));

        //Sætter 10 pixel mellem hvert label, textfield og knap.
        layout_login.setHgap(8);
        layout_login.setVgap(8);


        //opretter elementer samt placere dem i forhold til hinanden med (.setConstraints)
        Label label_user = new Label("Bruger");
        layout_login.setConstraints(label_user,0,0);

        Label label_password = new Label("Kodeord");
        layout_login.setConstraints(label_password,0,1);

        TextField textField_user = new TextField();
        layout_login.setConstraints(textField_user,1,0);
        textField_user.setPromptText("Brugernavn");

        PasswordField textField_password = new PasswordField();
        layout_login.setConstraints(textField_password,1,1);

        textField_password.setPromptText("Kodeord");

        Button button_login = new Button("Login");
        layout_login.setConstraints(button_login,1,2);

        //opretter int userlevel som bliver brugt til at se admin status
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
                NotificationMessage notificationMessage = new NotificationMessage
                        ("Forkert bruger eller password.\nPrøv igen.", "Fejl");
            }
        });

        layout_login.getChildren().addAll(label_user, label_password, textField_user,
                textField_password, button_login);

        primaryStage.setTitle("Login");
        Scene scene_login = new Scene(layout_login);
        primaryStage.setScene(scene_login);
        primaryStage.show();
    }
}