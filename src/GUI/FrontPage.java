package GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import Logic.*;

public class FrontPage
{

    /*  Den primære GUI klasse, der indeholder alt fra vores forside.
     *
     *
     */
    private Logic logic;

    public FrontPage()
    {
        logic = new Logic();
    }

    public void mainProgram(int user_level)
    {
        Stage window = new Stage();
        GridPane layout = new GridPane();
        BorderPane borderPane = new BorderPane();
        HBox topstuff = new HBox();
        HBox buttomright = new HBox();

        buttomright.setPadding(new Insets(10,10,10,10));
        buttomright.setSpacing(8);

        layout.setPadding(new Insets(10,10,10,10));

        layout.setHgap(29);
        layout.setVgap(8);

        //Knapper i top layer
        //Button button_home = new Button ("home");

        Button button_netraadgivning_og_metode = new Button("Netrådgivning og metode");
        button_netraadgivning_og_metode.setOnAction(e->
        {
            Show.vis("Generelt\n" + logic.genLogic(0,0) +
                    "Gode formuleringer:\n" + logic.specLogic(5,0) + "\n\n" +
                    "Vores rolle er:\n" + logic.specLogic(6,0) +"\n\n" +
                    "Undgå at:\n" + logic.specLogic(4,0) + "\n\n");
        });

        Button button_besvarelse = new Button ("Besvarelse");
        button_besvarelse.setOnAction(e->
        {
            Show.vis("Generelt\n" + logic.genLogic(0,1) +
                    "Indledning - Sådan indleder du en besvarelse:\n" +
                    "Eksempler på tekster\n" + logic.specLogic(13,1) + "\n\n" +
                    "Midte - Sådan rådgiver du:\n" + logic.genLogic(17,1) +
                    "Afslutning - Sådan afslutter du en besvarelse\n\nAfslutningsvis åbner vi for " +
                    "at indskriver altid er velkommen til at tage kontakt igen.\n" + logic.specLogic(14,1));
        });

        Button button_krisevurdering = new Button("Krisevurdering");
        button_krisevurdering.setOnAction(e->
        {
            Show.vis("Generelt\n" + logic.genLogic(0,2) +
                    "Eksempler på tekster:\n" + logic.specLogic(1,2) + "\n\n" +
                    "Henvisninger:\n" + logic.specLogic(2,2));
        });

        Button button_generalt = new Button ("Generelt");
        button_generalt.setOnAction(e ->
        {
            Show.vis("Generelt\n" + logic.genLogic(0,0) +
                     "Gode formuleringer\n\n" + logic.specLogic(5,0) +
                     "Vores rolle er:\n\n" + logic.specLogic(6,0) +
                     "Undgå at:\n\n" + logic.specLogic(4,0));
        });

        Button button_henvisningsliste = new Button ("Henvisningsliste");
        button_henvisningsliste.setOnAction(e->
        {
            Show.vis(logic.specLogic(2));

        });



        //Checkboxe samt Labels

        Label label_all_vold = new Label("Vold");
        GridPane.setConstraints(label_all_vold,0,0);
        label_all_vold.setFont(Font.font("Verdana", FontWeight.BOLD, 13));


        label_all_vold.setOnMouseClicked(e->
        {

//            String message = logic.specLinks(3);

//            Show.vis(message);
        });

        CheckBox checkBox_vold_generalt = new CheckBox("Generelt");
        GridPane.setConstraints(checkBox_vold_generalt,0,1);

        CheckBox checkBox_vold_sex = new CheckBox("Sex");
        GridPane.setConstraints(checkBox_vold_sex,0,2);

        CheckBox checkBox_vold_psygisk = new CheckBox("Psykisk");
        GridPane.setConstraints(checkBox_vold_psygisk,0,3);

        CheckBox checkBox_vold_fysisk = new CheckBox("Fysisk");
        GridPane.setConstraints(checkBox_vold_fysisk,0,4);

        CheckBox checkBox_vold_materiel = new CheckBox("Materiel");
        GridPane.setConstraints(checkBox_vold_materiel,0,5);

        CheckBox checkBox_vold_okonomisk = new CheckBox("Økonomisk");
        GridPane.setConstraints(checkBox_vold_okonomisk,0,6);

        Label label_born_vold = new Label("Børn & vold");
        GridPane.setConstraints(label_born_vold,1,0);
        label_born_vold.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_born_vold_generalt = new CheckBox("Generelt");
        GridPane.setConstraints(checkBox_born_vold_generalt,1,1);

        CheckBox checkBox_born_vold_VI = new CheckBox("Voksen indskriver");
        GridPane.setConstraints(checkBox_born_vold_VI,1,2);

        CheckBox checkBox_born_vold_BI = new CheckBox("Barn indskriver");
        GridPane.setConstraints(checkBox_born_vold_BI,1,3);

        Label label_skilsmisse = new Label("Skilsmisse");
        GridPane.setConstraints(label_skilsmisse,3,0);
        label_skilsmisse.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_skilsmisse_gen = new CheckBox("Generelt");
        GridPane.setConstraints(checkBox_skilsmisse_gen,3,1);


        Label label_misbrugere_og_psykiske_syge = new Label("Misbrugere & psykiske syge");
        GridPane.setConstraints(label_misbrugere_og_psykiske_syge,3,8);
        label_misbrugere_og_psykiske_syge.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_misbrugere_og_psykiske_syge = new CheckBox("Generelt");
        GridPane.setConstraints(checkBox_misbrugere_og_psykiske_syge,3,9);

        Label label_maend = new Label("Mænd");
        GridPane.setConstraints(label_maend,2,0);
        label_maend.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_maend_generalt = new CheckBox("Generelt");
        GridPane.setConstraints(checkBox_maend_generalt,2,1);

        CheckBox checkBox_maend_Hjælp_til_udøvere = new CheckBox("Hjælp til udøvere af vold");
        GridPane.setConstraints(checkBox_maend_Hjælp_til_udøvere,2,2);

        CheckBox checkBox_maend_Hjælp_til_ofre = new CheckBox("Hjælp til mænd udsat for vold");
        GridPane.setConstraints(checkBox_maend_Hjælp_til_ofre,2,3);

        Label label_etniske_kvinder = new Label("Etniske kvinder");
        GridPane.setConstraints(label_etniske_kvinder,4,8);
        label_etniske_kvinder.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_etniske_kvinder_generalt = new CheckBox("Generelt");
        GridPane.setConstraints(checkBox_etniske_kvinder_generalt,4,9);

        Label label_netvaerk = new Label("Netværk");
        GridPane.setConstraints(label_netvaerk,2,8);
        label_netvaerk.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_netvaerk_generelt = new CheckBox("Generelt");
        GridPane.setConstraints(checkBox_netvaerk_generelt,2,9);

        Label label_tom = new Label(" ");
        GridPane.setConstraints(label_tom,0,7);

        Label label_unge = new Label("Unge");
        GridPane.setConstraints(label_unge,0,8);
        label_unge.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_unge_generalt = new CheckBox("Generelt");
        GridPane.setConstraints(checkBox_unge_generalt,0,9);

        Label label_pro_hjaelp = new Label("Professionel hjælp");
        GridPane.setConstraints(label_pro_hjaelp,4,0);
        label_pro_hjaelp.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_pro_laege = new CheckBox("Læge");
        GridPane.setConstraints(checkBox_pro_laege,4,1);

        CheckBox checkBox_pro_psykolog = new CheckBox("Psykolog");
        GridPane.setConstraints(checkBox_pro_psykolog,4,2);

        CheckBox checkBox_pro_politi = new CheckBox("Politi");
        GridPane.setConstraints(checkBox_pro_politi,4,3);

        CheckBox checkBox_pro_retshjaelp = new CheckBox("Retshjælp");
        GridPane.setConstraints(checkBox_pro_retshjaelp,4,4);

        CheckBox checkBox_pro_sygehus = new CheckBox("Sygehus");
        GridPane.setConstraints(checkBox_pro_sygehus,4,5);

        CheckBox checkBox_pro_oekonomi = new CheckBox("Økonomi");
        GridPane.setConstraints(checkBox_pro_oekonomi,4,6);

        Label label_venner_skriver = new Label("Når venner skriver");
        GridPane.setConstraints(label_venner_skriver,1,8);
        label_venner_skriver.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_venner_skriver = new CheckBox("Generelt");
        GridPane.setConstraints(checkBox_venner_skriver,1,9);

        Button button_search = new Button("Vis");
        button_search.setPrefSize(70,40);
        button_search.setOnAction(e->
        {


            //sender status for alle checkboxer til (option_choosesend)
            String message = logic.option_choosedsend(checkBox_vold_generalt, checkBox_vold_sex, checkBox_vold_psygisk,
                    checkBox_vold_fysisk, checkBox_vold_materiel, checkBox_vold_okonomisk, checkBox_unge_generalt,
                    checkBox_born_vold_generalt, checkBox_born_vold_VI, checkBox_born_vold_BI, checkBox_venner_skriver,
                    checkBox_maend_generalt, checkBox_maend_Hjælp_til_udøvere, checkBox_maend_Hjælp_til_ofre,
                    checkBox_netvaerk_generelt, checkBox_skilsmisse_gen, checkBox_misbrugere_og_psykiske_syge, checkBox_pro_laege,
                    checkBox_pro_psykolog, checkBox_pro_politi, checkBox_pro_retshjaelp, checkBox_pro_sygehus,
                    checkBox_pro_oekonomi, checkBox_etniske_kvinder_generalt);

            Show.vis(message);
        });

        OpretBruger opretBruger = new OpretBruger();
        Button button_opret = new Button("Opret bruger");
        button_opret.setPrefHeight(40);

        button_opret.setOnAction(e-> {

            opretBruger.createUser();
        });

        //rediger knappen chekker userlevel, hvis userlevel ikke er 1, så kan man ikke redigere.
        Button button_rediger = new Button("Rediger");
        button_rediger.setPrefSize(70,40);
        //hvis user_level (hentet fra Login) er 1 så bliver man vidresendt til redigerings vinduet
        button_rediger.setOnAction(e->
        {
//            AdminRediger.rediger();
        });

        layout.getChildren().addAll(
                label_all_vold,
                checkBox_vold_generalt, checkBox_vold_sex, checkBox_vold_psygisk,checkBox_vold_fysisk,
                checkBox_vold_materiel,checkBox_vold_okonomisk,

                label_born_vold,
                checkBox_born_vold_generalt, checkBox_born_vold_VI, checkBox_born_vold_BI,

                label_maend,
                checkBox_maend_generalt, checkBox_maend_Hjælp_til_udøvere, checkBox_maend_Hjælp_til_ofre,

                label_skilsmisse,
                checkBox_skilsmisse_gen,

                label_pro_hjaelp,
                checkBox_pro_laege, checkBox_pro_psykolog, checkBox_pro_politi, checkBox_pro_retshjaelp,
                checkBox_pro_sygehus, checkBox_pro_oekonomi,

                label_unge,
                checkBox_unge_generalt,

                label_venner_skriver,
                checkBox_venner_skriver,

                label_netvaerk,
                checkBox_netvaerk_generelt,

                label_misbrugere_og_psykiske_syge,
                checkBox_misbrugere_og_psykiske_syge,

                label_etniske_kvinder,
                checkBox_etniske_kvinder_generalt,

                label_tom
        );

        topstuff.getChildren().addAll(button_netraadgivning_og_metode, button_besvarelse, button_krisevurdering,
                button_generalt, button_henvisningsliste);

        buttomright.getChildren().addAll(button_search);

        if (user_level == 1)
        {
            buttomright.getChildren().addAll(button_rediger, button_opret);
        }

        borderPane.setBottom(buttomright);
        borderPane.setTop(topstuff);
        borderPane.setCenter(layout);

        Scene scene = new Scene(borderPane, 950,420);

        window.setScene(scene);
        window.show();
    }
}
