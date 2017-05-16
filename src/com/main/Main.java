package com.main;

import GUI.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Login login = new Login();
        login.first_login(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

