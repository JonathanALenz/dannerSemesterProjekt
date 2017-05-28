package GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import Logic.*;

public class FrontPage
{

    //Den primære GUI klasse, der indeholder alt fra vores forside.
    private Logic logic;
    private Show show;

    public FrontPage()
    {
        logic = new Logic();
        show = new Show();
    }

    //åbner programmet og tar parameteren "user_level" som bliver brugt under oprettelsen af hovedvinduet (hvis man er
    // admin kan man se 2 knapper mere her)
    public void mainProgram(int user_level)
    {
        Stage window = new Stage();

        //Bruger borderpane til at dele vinduet op, topstuff og buttomstuff er placeret i borderpanes top og buttom
        BorderPane borderPane = new BorderPane();
        BorderPane topstuff = new BorderPane();
        BorderPane buttomstuff = new BorderPane();

        //bruger gridpane_middle til alle checkboxe og labels der står over checkboxe og er placeret i borderpanes center
        GridPane gridpane_middle = new GridPane();

        //Alle de HBox'e og VBox'e indeholder vores knapper, søgefelt og de to labels øverst til venstre
        //De er plaveret i BorderPanesne: "topstuff" og "buttomstuff".
        VBox midright = new VBox();
        HBox buttomright = new HBox();
        HBox buttomleft = new HBox();
        VBox topleft = new VBox();
        HBox topright = new HBox();

        //sørger for at ingen elemeter er indenfor 10 pixels af vinduets rammer (med undtagelse labelsne i top left som
        //er 2 pixels from toppen). Desuden sørger (.setSpacing) for at alle elementer ikke er klumpet sammen.
        buttomright.setPadding(new Insets(10,10,10,10));
        buttomright.setSpacing(8);
        buttomleft.setPadding(new Insets(10,10,10,10));
        buttomleft.setSpacing(8);
        midright.setPadding(new Insets(10,10,10,10));
        midright.setSpacing(4);
        topright.setPadding(new Insets(10,10,10,10));
        topleft.setPadding(new Insets(2,10,10,10));

        gridpane_middle.setPadding(new Insets(10,10,10,10));

        //laver vertikalt og horisontalt gap for checkboxe og labels i Gridpane.
        gridpane_middle.setHgap(29);
        gridpane_middle.setVgap(8);

        //Knapper til højre i vinduet
        Label label_overskrift = new Label("Manual for Danners Netrådgivning");
        label_overskrift.setFont(Font.font("",22));

        Label label_underoverskrift = new Label("Vælg problematikker for at få vist information og klik på \"Vis\".");
        label_underoverskrift.setFont(Font.font("",16));

        Button button_netraadgivning_og_metode = new Button("Netrådgivning og metode");
        gridpane_middle.setConstraints(button_netraadgivning_og_metode,5,0);
        button_netraadgivning_og_metode.setPrefSize(154,15);
                button_netraadgivning_og_metode.setOnAction(e->
        {
            show.vis("GENERELT\n" + logic.getCellTextFormat(0,0) +
                    "GODE FORMULERINGER:\n" + logic.getCellText(5,0) + "\n\n" +
                    "VORES ROLLE ER:\n" + logic.getCellText(6,0) +"\n\n" +
                    "UNDGÅ AT:\n" + logic.getCellText(4,0) + "\n\n");
        });

        Button button_besvarelse = new Button ("Besvarelse");
        gridpane_middle.setConstraints(button_besvarelse,5,1);
        button_besvarelse.setPrefSize(154,15);
        button_besvarelse.setOnAction(e->
        {
            show.vis("GENERELT\n" + logic.getCellTextFormat(0,1) +
                    "INDLEDNING - SÅDAN INDLEDER DU EN BESVARELSE:\n" +
                    "EKSEMPLER PÅ TEKSTER\n" + logic.getCellText(13,1) + "\n\n" +
                    "MIDTE - SÅDAN RÅDGIVER DU:\n" + logic.getCellTextFormat(17,1) +
                    "AFSLUTNING - SÅDAN AFSLUTTER DU DIN BESVARELSE\n\nAFSLUTNINGSVIS ÅBNER VI FOR " +
                    "AT INDSKRIVER ALTID ER VELKOMMEN TIL AT TAGE KONTAKT IGEN\n" + logic.getCellText(14,1));
        });

        Button button_krisevurdering = new Button("Krisevurdering");
        gridpane_middle.setConstraints(button_krisevurdering,5,2);
        button_krisevurdering.setPrefSize(154,15);
        button_krisevurdering.setOnAction(e->
        {
            show.vis("GENERELT\n" + logic.getCellTextFormat(0,2) +
                    "EKSEMPLER PÅ TEKSTER:\n" + logic.getCellText(1,2) + "\n\n" +
                    "HENVISNINGER:\n" + logic.getCellText(2,2));
        });

        Button button_generelt = new Button ("Generelt");
        gridpane_middle.setConstraints(button_generelt,5,3);
        button_generelt.setPrefSize(154,15);
        button_generelt.setOnAction(e ->
        {
            show.vis("GENERELT\n" + logic.getCellTextFormat(0,0) +
                     "GODE FORMULERINGER\n\n" + logic.getCellText(5,0) +
                     "VORES ROLLE ER:\n\n" + logic.getCellText(6,0) +
                     "UNDGÅ AT:\n\n" + logic.getCellText(4,0));
        });

        Button button_henvisningsliste = new Button ("Henvisningsliste");
        gridpane_middle.setConstraints(button_henvisningsliste,5,4);
        button_henvisningsliste.setPrefSize(154,5);
        button_henvisningsliste.setOnAction(e->
        {
            show.vis(logic.getCellText(2));

        });

        //laver et tomp felt under henvisningsliste knappen på 50 pixel
        Region region = new Region();
        region.setPrefHeight(50);

        //Søgefelt samt søgeknap oprettes
        TextField textFieldsoeg = new TextField();
        textFieldsoeg.setPromptText("Indtast søgeord...");

        Button button_soeg = new Button("Søg");
        button_soeg.setOnAction(e->
        {
            String word = textFieldsoeg.getText();
            String msg = logic.getSearchedWord(word);
            if(!msg.equals(""))
            {
                show.vis(msg);
            }
            else
            {
                ErrorMessage errorMessage = new ErrorMessage("Kunne ikke finde " + word, "Fejl");
            }
        });

        //Checkboxe samt Labels

        Label label_all_vold = new Label("Vold");
        gridpane_middle.setConstraints(label_all_vold,0,0);
        label_all_vold.setFont(Font.font("Verdana", FontWeight.BOLD, 13));


        CheckBox checkBox_vold_generelt = new CheckBox("Generelt");
        gridpane_middle.setConstraints(checkBox_vold_generelt,0,1);

        CheckBox checkBox_vold_sex = new CheckBox("Sex");
        gridpane_middle.setConstraints(checkBox_vold_sex,0,2);

        CheckBox checkBox_vold_psygisk = new CheckBox("Psykisk");
        gridpane_middle.setConstraints(checkBox_vold_psygisk,0,3);

        CheckBox checkBox_vold_fysisk = new CheckBox("Fysisk");
        gridpane_middle.setConstraints(checkBox_vold_fysisk,0,4);

        CheckBox checkBox_vold_materiel = new CheckBox("Materiel");
        gridpane_middle.setConstraints(checkBox_vold_materiel,0,5);

        CheckBox checkBox_vold_okonomisk = new CheckBox("Økonomisk");
        gridpane_middle.setConstraints(checkBox_vold_okonomisk,0,6);

        Label label_born_vold = new Label("Børn & vold");
        gridpane_middle.setConstraints(label_born_vold,1,0);
        label_born_vold.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_born_vold_generelt = new CheckBox("Generelt");
        gridpane_middle.setConstraints(checkBox_born_vold_generelt,1,1);

        CheckBox checkBox_born_vold_VI = new CheckBox("Voksen indskriver");
        gridpane_middle.setConstraints(checkBox_born_vold_VI,1,2);

        CheckBox checkBox_born_vold_BI = new CheckBox("Barn indskriver");
        gridpane_middle.setConstraints(checkBox_born_vold_BI,1,3);

        Label label_skilsmisse = new Label("Skilsmisse");
        gridpane_middle.setConstraints(label_skilsmisse,3,0);
        label_skilsmisse.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_skilsmisse_gen = new CheckBox("Generelt");
        gridpane_middle.setConstraints(checkBox_skilsmisse_gen,3,1);

        Label label_misbrugere_og_psykiske_syge = new Label("Misbrugere & psykiske syge");
        gridpane_middle.setConstraints(label_misbrugere_og_psykiske_syge,3,8);
        label_misbrugere_og_psykiske_syge.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_misbrugere_og_psykiske_syge = new CheckBox("Generelt");
        gridpane_middle.setConstraints(checkBox_misbrugere_og_psykiske_syge,3,9);

        Label label_maend = new Label("Mænd");
        gridpane_middle.setConstraints(label_maend,2,0);
        label_maend.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_maend_generelt = new CheckBox("Generelt");
        gridpane_middle.setConstraints(checkBox_maend_generelt,2,1);

        CheckBox checkBox_maend_Hjælp_til_udøvere = new CheckBox("Hjælp til udøvere af vold");
        gridpane_middle.setConstraints(checkBox_maend_Hjælp_til_udøvere,2,2);

        CheckBox checkBox_maend_Hjælp_til_ofre = new CheckBox("Hjælp til mænd udsat for vold");
        gridpane_middle.setConstraints(checkBox_maend_Hjælp_til_ofre,2,3);

        Label label_etniske_kvinder = new Label("Etniske kvinder");
        gridpane_middle.setConstraints(label_etniske_kvinder,4,8);
        label_etniske_kvinder.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_etniske_kvinder_generelt = new CheckBox("Generelt");
        gridpane_middle.setConstraints(checkBox_etniske_kvinder_generelt,4,9);

        Label label_netvaerk = new Label("Netværk");
        gridpane_middle.setConstraints(label_netvaerk,2,8);
        label_netvaerk.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_netvaerk_generelt = new CheckBox("Generelt");
        gridpane_middle.setConstraints(checkBox_netvaerk_generelt,2,9);

        Label label_tom = new Label(" ");
        gridpane_middle.setConstraints(label_tom,0,7);

        Label label_unge = new Label("Unge");
        gridpane_middle.setConstraints(label_unge,0,8);
        label_unge.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_unge_generelt = new CheckBox("Generelt");
        gridpane_middle.setConstraints(checkBox_unge_generelt,0,9);

        Label label_pro_hjaelp = new Label("Professionel hjælp");
        gridpane_middle.setConstraints(label_pro_hjaelp,4,0);
        label_pro_hjaelp.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_pro_laege = new CheckBox("Læge");
        gridpane_middle.setConstraints(checkBox_pro_laege,4,1);

        CheckBox checkBox_pro_psykolog = new CheckBox("Psykolog");
        gridpane_middle.setConstraints(checkBox_pro_psykolog,4,2);

        CheckBox checkBox_pro_politi = new CheckBox("Politi");
        gridpane_middle.setConstraints(checkBox_pro_politi,4,3);

        CheckBox checkBox_pro_retshjaelp = new CheckBox("Retshjælp");
        gridpane_middle.setConstraints(checkBox_pro_retshjaelp,4,4);

        CheckBox checkBox_pro_sygehus = new CheckBox("Sygehus");
        gridpane_middle.setConstraints(checkBox_pro_sygehus,4,5);

        CheckBox checkBox_pro_oekonomi = new CheckBox("Økonomi");
        gridpane_middle.setConstraints(checkBox_pro_oekonomi,4,6);

        Label label_venner_skriver = new Label("Når venner skriver");
        gridpane_middle.setConstraints(label_venner_skriver,1,8);
        label_venner_skriver.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        CheckBox checkBox_venner_skriver = new CheckBox("Generelt");
        gridpane_middle.setConstraints(checkBox_venner_skriver,1,9);

        Button button_search = new Button("Vis");
        button_search.setPrefSize(70,40);
        button_search.setOnAction(e->
        {
            //sender status for alle checkboxer til (option_choosesend)
            String message = logic.optionChosenSend(checkBox_vold_generelt, checkBox_vold_sex, checkBox_vold_psygisk,
                    checkBox_vold_fysisk, checkBox_vold_materiel, checkBox_vold_okonomisk, checkBox_unge_generelt,
                    checkBox_born_vold_generelt, checkBox_born_vold_VI, checkBox_born_vold_BI, checkBox_venner_skriver,
                    checkBox_maend_generelt, checkBox_maend_Hjælp_til_udøvere, checkBox_maend_Hjælp_til_ofre,
                    checkBox_netvaerk_generelt, checkBox_skilsmisse_gen, checkBox_misbrugere_og_psykiske_syge, checkBox_pro_laege,
                    checkBox_pro_psykolog, checkBox_pro_politi, checkBox_pro_retshjaelp, checkBox_pro_sygehus,
                    checkBox_pro_oekonomi, checkBox_etniske_kvinder_generelt);
            show.vis(message);
        });

        OpretBruger opretBruger = new OpretBruger();
        Button button_opret = new Button("Opret bruger");
        button_opret.setPrefHeight(40);

        button_opret.setOnAction(e->
        {
            opretBruger.createUser(logic);
        });


        Button button_rediger = new Button("Rediger");
        button_rediger.setPrefSize(70,40);

        button_rediger.setOnAction(e->
        {
//            AdminRediger.rediger();

        });

        //afmarkére alle checkboxe
        Button button_all_off = new Button("Afmarkér");
        button_all_off.setPrefWidth(154);
        button_all_off.setOnAction(e->{

            checkBox_born_vold_BI.setSelected(false);
            checkBox_born_vold_generelt.setSelected(false);
            checkBox_born_vold_VI.setSelected(false);
            checkBox_etniske_kvinder_generelt.setSelected(false);
            checkBox_maend_generelt.setSelected(false);
            checkBox_maend_Hjælp_til_ofre.setSelected(false);
            checkBox_maend_Hjælp_til_udøvere.setSelected(false);
            checkBox_misbrugere_og_psykiske_syge.setSelected(false);
            checkBox_netvaerk_generelt.setSelected(false);
            checkBox_pro_laege.setSelected(false);
            checkBox_pro_oekonomi.setSelected(false);
            checkBox_pro_politi.setSelected(false);
            checkBox_pro_psykolog.setSelected(false);
            checkBox_pro_retshjaelp.setSelected(false);
            checkBox_pro_sygehus.setSelected(false);
            checkBox_skilsmisse_gen.setSelected(false);
            checkBox_unge_generelt.setSelected(false);
            checkBox_venner_skriver.setSelected(false);
            checkBox_vold_fysisk.setSelected(false);
            checkBox_vold_generelt.setSelected(false);
            checkBox_vold_materiel.setSelected(false);
            checkBox_vold_okonomisk.setSelected(false);
            checkBox_vold_psygisk.setSelected(false);
            checkBox_vold_sex.setSelected(false);

        });

        //lukker program
        Button button_luk = new Button("Luk");
        button_luk.setPrefSize(70,40);
        button_luk.setOnAction(e->
        {
            window.close();
        });

        //placere alle labels/checkboxe/knapper/textfields i de respektive HBoxe, VBoxe og Gridpane
        gridpane_middle.getChildren().addAll(
                label_all_vold,
                checkBox_vold_generelt, checkBox_vold_sex, checkBox_vold_psygisk,checkBox_vold_fysisk,
                checkBox_vold_materiel,checkBox_vold_okonomisk,

                label_born_vold,
                checkBox_born_vold_generelt, checkBox_born_vold_VI, checkBox_born_vold_BI,

                label_maend,
                checkBox_maend_generelt, checkBox_maend_Hjælp_til_udøvere, checkBox_maend_Hjælp_til_ofre,

                label_skilsmisse,
                checkBox_skilsmisse_gen,

                label_pro_hjaelp,
                checkBox_pro_laege, checkBox_pro_psykolog, checkBox_pro_politi, checkBox_pro_retshjaelp,
                checkBox_pro_sygehus, checkBox_pro_oekonomi,

                label_unge,
                checkBox_unge_generelt,

                label_venner_skriver,
                checkBox_venner_skriver,

                label_netvaerk,
                checkBox_netvaerk_generelt,

                label_misbrugere_og_psykiske_syge,
                checkBox_misbrugere_og_psykiske_syge,

                label_etniske_kvinder,
                checkBox_etniske_kvinder_generelt,

                label_tom
        );

        topleft.getChildren().addAll(label_overskrift, label_underoverskrift);

        topright.getChildren().addAll(textFieldsoeg, button_soeg);

        midright.getChildren().addAll(button_netraadgivning_og_metode, button_besvarelse, button_krisevurdering,
                button_generelt, button_henvisningsliste, region, button_all_off);

        //lige meget hvilket user_level brugeren har, vil button_search blive oprettet
        buttomleft.getChildren().addAll(button_search);

        //når vinduet åbnes checkes der om user_level er 1, hvis det er 1, så betyder det at man er admin. Derfor vil
        //button_rediger og button_opret også blive generet til højre for button_search.
        if (user_level == 1)
        {
            buttomleft.getChildren().addAll(button_rediger, button_opret);
        }
        buttomright.getChildren().addAll(button_luk);

        //de forskellige HBoxe, VBoxe, GridPane, BorderPane bliver sat sammen her
        buttomstuff.setLeft(buttomleft);
        buttomstuff.setRight(buttomright);

        topstuff.setLeft(topleft);
        topstuff.setRight(topright);

        borderPane.setRight(midright);
        borderPane.setBottom(buttomstuff);
        borderPane.setTop(topstuff);
        borderPane.setCenter(gridpane_middle);

        Scene scene = new Scene(borderPane, 1060,450);

        window.setScene(scene);
        window.setTitle("Danner - semesterprojekt alpha v0.9");
        window.show();
    }
}
